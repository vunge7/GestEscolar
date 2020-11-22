/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.AnoLectivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Nota;
import java.util.ArrayList;
import java.util.List;
import entity.Turma;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class AnoLectivoJpaController implements Serializable
{

    public AnoLectivoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( AnoLectivo anoLectivo )
    {
        if ( anoLectivo.getNotaList() == null )
        {
            anoLectivo.setNotaList( new ArrayList<Nota>() );
        }
        if ( anoLectivo.getTurmaList() == null )
        {
            anoLectivo.setTurmaList( new ArrayList<Turma>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : anoLectivo.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            anoLectivo.setNotaList( attachedNotaList );
            List<Turma> attachedTurmaList = new ArrayList<Turma>();
            for ( Turma turmaListTurmaToAttach : anoLectivo.getTurmaList() )
            {
                turmaListTurmaToAttach = em.getReference( turmaListTurmaToAttach.getClass(), turmaListTurmaToAttach.getPkTurma() );
                attachedTurmaList.add( turmaListTurmaToAttach );
            }
            anoLectivo.setTurmaList( attachedTurmaList );
            em.persist( anoLectivo );
            for ( Nota notaListNota : anoLectivo.getNotaList() )
            {
                AnoLectivo oldFkAnoLectivoOfNotaListNota = notaListNota.getFkAnoLectivo();
                notaListNota.setFkAnoLectivo( anoLectivo );
                notaListNota = em.merge( notaListNota );
                if ( oldFkAnoLectivoOfNotaListNota != null )
                {
                    oldFkAnoLectivoOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkAnoLectivoOfNotaListNota = em.merge( oldFkAnoLectivoOfNotaListNota );
                }
            }
            for ( Turma turmaListTurma : anoLectivo.getTurmaList() )
            {
                AnoLectivo oldFkAnoLectivoOfTurmaListTurma = turmaListTurma.getFkAnoLectivo();
                turmaListTurma.setFkAnoLectivo( anoLectivo );
                turmaListTurma = em.merge( turmaListTurma );
                if ( oldFkAnoLectivoOfTurmaListTurma != null )
                {
                    oldFkAnoLectivoOfTurmaListTurma.getTurmaList().remove( turmaListTurma );
                    oldFkAnoLectivoOfTurmaListTurma = em.merge( oldFkAnoLectivoOfTurmaListTurma );
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

    public void edit( AnoLectivo anoLectivo ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            AnoLectivo persistentAnoLectivo = em.find( AnoLectivo.class, anoLectivo.getPkAnoLectivo() );
            List<Nota> notaListOld = persistentAnoLectivo.getNotaList();
            List<Nota> notaListNew = anoLectivo.getNotaList();
            List<Turma> turmaListOld = persistentAnoLectivo.getTurmaList();
            List<Turma> turmaListNew = anoLectivo.getTurmaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkAnoLectivo field is not nullable." );
                }
            }
            for ( Turma turmaListOldTurma : turmaListOld )
            {
                if ( !turmaListNew.contains( turmaListOldTurma ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Turma " + turmaListOldTurma + " since its fkAnoLectivo field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Nota> attachedNotaListNew = new ArrayList<Nota>();
            for ( Nota notaListNewNotaToAttach : notaListNew )
            {
                notaListNewNotaToAttach = em.getReference( notaListNewNotaToAttach.getClass(), notaListNewNotaToAttach.getPkNota() );
                attachedNotaListNew.add( notaListNewNotaToAttach );
            }
            notaListNew = attachedNotaListNew;
            anoLectivo.setNotaList( notaListNew );
            List<Turma> attachedTurmaListNew = new ArrayList<Turma>();
            for ( Turma turmaListNewTurmaToAttach : turmaListNew )
            {
                turmaListNewTurmaToAttach = em.getReference( turmaListNewTurmaToAttach.getClass(), turmaListNewTurmaToAttach.getPkTurma() );
                attachedTurmaListNew.add( turmaListNewTurmaToAttach );
            }
            turmaListNew = attachedTurmaListNew;
            anoLectivo.setTurmaList( turmaListNew );
            anoLectivo = em.merge( anoLectivo );
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    AnoLectivo oldFkAnoLectivoOfNotaListNewNota = notaListNewNota.getFkAnoLectivo();
                    notaListNewNota.setFkAnoLectivo( anoLectivo );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkAnoLectivoOfNotaListNewNota != null && !oldFkAnoLectivoOfNotaListNewNota.equals( anoLectivo ) )
                    {
                        oldFkAnoLectivoOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkAnoLectivoOfNotaListNewNota = em.merge( oldFkAnoLectivoOfNotaListNewNota );
                    }
                }
            }
            for ( Turma turmaListNewTurma : turmaListNew )
            {
                if ( !turmaListOld.contains( turmaListNewTurma ) )
                {
                    AnoLectivo oldFkAnoLectivoOfTurmaListNewTurma = turmaListNewTurma.getFkAnoLectivo();
                    turmaListNewTurma.setFkAnoLectivo( anoLectivo );
                    turmaListNewTurma = em.merge( turmaListNewTurma );
                    if ( oldFkAnoLectivoOfTurmaListNewTurma != null && !oldFkAnoLectivoOfTurmaListNewTurma.equals( anoLectivo ) )
                    {
                        oldFkAnoLectivoOfTurmaListNewTurma.getTurmaList().remove( turmaListNewTurma );
                        oldFkAnoLectivoOfTurmaListNewTurma = em.merge( oldFkAnoLectivoOfTurmaListNewTurma );
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
                Integer id = anoLectivo.getPkAnoLectivo();
                if ( findAnoLectivo( id ) == null )
                {
                    throw new NonexistentEntityException( "The anoLectivo with id " + id + " no longer exists." );
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
            AnoLectivo anoLectivo;
            try
            {
                anoLectivo = em.getReference( AnoLectivo.class, id );
                anoLectivo.getPkAnoLectivo();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The anoLectivo with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = anoLectivo.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This AnoLectivo (" + anoLectivo + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkAnoLectivo field." );
            }
            List<Turma> turmaListOrphanCheck = anoLectivo.getTurmaList();
            for ( Turma turmaListOrphanCheckTurma : turmaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This AnoLectivo (" + anoLectivo + ") cannot be destroyed since the Turma " + turmaListOrphanCheckTurma + " in its turmaList field has a non-nullable fkAnoLectivo field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( anoLectivo );
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

    public List<AnoLectivo> findAnoLectivoEntities()
    {
        return findAnoLectivoEntities( true, -1, -1 );
    }

    public List<AnoLectivo> findAnoLectivoEntities( int maxResults, int firstResult )
    {
        return findAnoLectivoEntities( false, maxResults, firstResult );
    }

    private List<AnoLectivo> findAnoLectivoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( AnoLectivo.class ) );
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

    public AnoLectivo findAnoLectivo( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( AnoLectivo.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getAnoLectivoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnoLectivo> rt = cq.from( AnoLectivo.class );
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
