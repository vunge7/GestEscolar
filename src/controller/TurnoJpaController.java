/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Turma;
import entity.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class TurnoJpaController implements Serializable
{

    public TurnoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Turno turno )
    {
        if ( turno.getTurmaList() == null )
        {
            turno.setTurmaList( new ArrayList<Turma>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turma> attachedTurmaList = new ArrayList<Turma>();
            for ( Turma turmaListTurmaToAttach : turno.getTurmaList() )
            {
                turmaListTurmaToAttach = em.getReference( turmaListTurmaToAttach.getClass(), turmaListTurmaToAttach.getPkTurma() );
                attachedTurmaList.add( turmaListTurmaToAttach );
            }
            turno.setTurmaList( attachedTurmaList );
            em.persist( turno );
            for ( Turma turmaListTurma : turno.getTurmaList() )
            {
                Turno oldFkTurnoOfTurmaListTurma = turmaListTurma.getFkTurno();
                turmaListTurma.setFkTurno( turno );
                turmaListTurma = em.merge( turmaListTurma );
                if ( oldFkTurnoOfTurmaListTurma != null )
                {
                    oldFkTurnoOfTurmaListTurma.getTurmaList().remove( turmaListTurma );
                    oldFkTurnoOfTurmaListTurma = em.merge( oldFkTurnoOfTurmaListTurma );
                }
            }
            em.getTransaction().commit();
        }
        finally
        {
            if ( em != null )
            {
                em.close();
            }
        }
    }

    public void edit( Turno turno ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find( Turno.class, turno.getPkTurno() );
            List<Turma> turmaListOld = persistentTurno.getTurmaList();
            List<Turma> turmaListNew = turno.getTurmaList();
            List<String> illegalOrphanMessages = null;
            for ( Turma turmaListOldTurma : turmaListOld )
            {
                if ( !turmaListNew.contains( turmaListOldTurma ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Turma " + turmaListOldTurma + " since its fkTurno field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Turma> attachedTurmaListNew = new ArrayList<Turma>();
            for ( Turma turmaListNewTurmaToAttach : turmaListNew )
            {
                turmaListNewTurmaToAttach = em.getReference( turmaListNewTurmaToAttach.getClass(), turmaListNewTurmaToAttach.getPkTurma() );
                attachedTurmaListNew.add( turmaListNewTurmaToAttach );
            }
            turmaListNew = attachedTurmaListNew;
            turno.setTurmaList( turmaListNew );
            turno = em.merge( turno );
            for ( Turma turmaListNewTurma : turmaListNew )
            {
                if ( !turmaListOld.contains( turmaListNewTurma ) )
                {
                    Turno oldFkTurnoOfTurmaListNewTurma = turmaListNewTurma.getFkTurno();
                    turmaListNewTurma.setFkTurno( turno );
                    turmaListNewTurma = em.merge( turmaListNewTurma );
                    if ( oldFkTurnoOfTurmaListNewTurma != null && !oldFkTurnoOfTurmaListNewTurma.equals( turno ) )
                    {
                        oldFkTurnoOfTurmaListNewTurma.getTurmaList().remove( turmaListNewTurma );
                        oldFkTurnoOfTurmaListNewTurma = em.merge( oldFkTurnoOfTurmaListNewTurma );
                    }
                }
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if ( msg == null || msg.length() == 0 )
            {
                Integer id = turno.getPkTurno();
                if ( findTurno( id ) == null )
                {
                    throw new NonexistentEntityException( "The turno with id " + id + " no longer exists." );
                }
            }
            throw ex;
        }
        finally
        {
            if ( em != null )
            {
                em.close();
            }
        }
    }

    public void destroy( Integer id ) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno;
            try
            {
                turno = em.getReference( Turno.class, id );
                turno.getPkTurno();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The turno with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Turma> turmaListOrphanCheck = turno.getTurmaList();
            for ( Turma turmaListOrphanCheckTurma : turmaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Turno (" + turno + ") cannot be destroyed since the Turma " + turmaListOrphanCheckTurma + " in its turmaList field has a non-nullable fkTurno field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( turno );
            em.getTransaction().commit();
        }
        finally
        {
            if ( em != null )
            {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities()
    {
        return findTurnoEntities( true, -1, -1 );
    }

    public List<Turno> findTurnoEntities( int maxResults, int firstResult )
    {
        return findTurnoEntities( false, maxResults, firstResult );
    }

    private List<Turno> findTurnoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Turno.class ) );
            Query q = em.createQuery( cq );
            if ( !all )
            {
                q.setMaxResults( maxResults );
                q.setFirstResult( firstResult );
            }
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Turno findTurno( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Turno.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getTurnoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from( Turno.class );
            cq.select( em.getCriteriaBuilder().count( rt ) );
            Query q = em.createQuery( cq );
            return ( ( Long ) q.getSingleResult() ).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
