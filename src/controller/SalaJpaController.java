/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Sala;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Turma;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class SalaJpaController implements Serializable
{

    public SalaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Sala sala )
    {
        if ( sala.getTurmaList() == null )
        {
            sala.setTurmaList( new ArrayList<Turma>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turma> attachedTurmaList = new ArrayList<Turma>();
            for ( Turma turmaListTurmaToAttach : sala.getTurmaList() )
            {
                turmaListTurmaToAttach = em.getReference( turmaListTurmaToAttach.getClass(), turmaListTurmaToAttach.getPkTurma() );
                attachedTurmaList.add( turmaListTurmaToAttach );
            }
            sala.setTurmaList( attachedTurmaList );
            em.persist( sala );
            for ( Turma turmaListTurma : sala.getTurmaList() )
            {
                Sala oldFkSalaOfTurmaListTurma = turmaListTurma.getFkSala();
                turmaListTurma.setFkSala( sala );
                turmaListTurma = em.merge( turmaListTurma );
                if ( oldFkSalaOfTurmaListTurma != null )
                {
                    oldFkSalaOfTurmaListTurma.getTurmaList().remove( turmaListTurma );
                    oldFkSalaOfTurmaListTurma = em.merge( oldFkSalaOfTurmaListTurma );
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

    public void edit( Sala sala ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala persistentSala = em.find( Sala.class, sala.getPkSala() );
            List<Turma> turmaListOld = persistentSala.getTurmaList();
            List<Turma> turmaListNew = sala.getTurmaList();
            List<String> illegalOrphanMessages = null;
            for ( Turma turmaListOldTurma : turmaListOld )
            {
                if ( !turmaListNew.contains( turmaListOldTurma ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Turma " + turmaListOldTurma + " since its fkSala field is not nullable." );
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
            sala.setTurmaList( turmaListNew );
            sala = em.merge( sala );
            for ( Turma turmaListNewTurma : turmaListNew )
            {
                if ( !turmaListOld.contains( turmaListNewTurma ) )
                {
                    Sala oldFkSalaOfTurmaListNewTurma = turmaListNewTurma.getFkSala();
                    turmaListNewTurma.setFkSala( sala );
                    turmaListNewTurma = em.merge( turmaListNewTurma );
                    if ( oldFkSalaOfTurmaListNewTurma != null && !oldFkSalaOfTurmaListNewTurma.equals( sala ) )
                    {
                        oldFkSalaOfTurmaListNewTurma.getTurmaList().remove( turmaListNewTurma );
                        oldFkSalaOfTurmaListNewTurma = em.merge( oldFkSalaOfTurmaListNewTurma );
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
                Integer id = sala.getPkSala();
                if ( findSala( id ) == null )
                {
                    throw new NonexistentEntityException( "The sala with id " + id + " no longer exists." );
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
            Sala sala;
            try
            {
                sala = em.getReference( Sala.class, id );
                sala.getPkSala();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The sala with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Turma> turmaListOrphanCheck = sala.getTurmaList();
            for ( Turma turmaListOrphanCheckTurma : turmaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Sala (" + sala + ") cannot be destroyed since the Turma " + turmaListOrphanCheckTurma + " in its turmaList field has a non-nullable fkSala field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( sala );
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

    public List<Sala> findSalaEntities()
    {
        return findSalaEntities( true, -1, -1 );
    }

    public List<Sala> findSalaEntities( int maxResults, int firstResult )
    {
        return findSalaEntities( false, maxResults, firstResult );
    }

    private List<Sala> findSalaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Sala.class ) );
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

    public Sala findSala( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Sala.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getSalaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sala> rt = cq.from( Sala.class );
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
