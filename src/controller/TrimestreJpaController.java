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
import entity.Nota;
import entity.Trimestre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class TrimestreJpaController implements Serializable
{

    public TrimestreJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Trimestre trimestre )
    {
        if ( trimestre.getNotaList() == null )
        {
            trimestre.setNotaList( new ArrayList<Nota>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : trimestre.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            trimestre.setNotaList( attachedNotaList );
            em.persist( trimestre );
            for ( Nota notaListNota : trimestre.getNotaList() )
            {
                Trimestre oldFkTrimestreOfNotaListNota = notaListNota.getFkTrimestre();
                notaListNota.setFkTrimestre( trimestre );
                notaListNota = em.merge( notaListNota );
                if ( oldFkTrimestreOfNotaListNota != null )
                {
                    oldFkTrimestreOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkTrimestreOfNotaListNota = em.merge( oldFkTrimestreOfNotaListNota );
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

    public void edit( Trimestre trimestre ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Trimestre persistentTrimestre = em.find( Trimestre.class, trimestre.getPkSemestre() );
            List<Nota> notaListOld = persistentTrimestre.getNotaList();
            List<Nota> notaListNew = trimestre.getNotaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkTrimestre field is not nullable." );
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
            trimestre.setNotaList( notaListNew );
            trimestre = em.merge( trimestre );
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    Trimestre oldFkTrimestreOfNotaListNewNota = notaListNewNota.getFkTrimestre();
                    notaListNewNota.setFkTrimestre( trimestre );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkTrimestreOfNotaListNewNota != null && !oldFkTrimestreOfNotaListNewNota.equals( trimestre ) )
                    {
                        oldFkTrimestreOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkTrimestreOfNotaListNewNota = em.merge( oldFkTrimestreOfNotaListNewNota );
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
                Integer id = trimestre.getPkSemestre();
                if ( findTrimestre( id ) == null )
                {
                    throw new NonexistentEntityException( "The trimestre with id " + id + " no longer exists." );
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
            Trimestre trimestre;
            try
            {
                trimestre = em.getReference( Trimestre.class, id );
                trimestre.getPkSemestre();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The trimestre with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = trimestre.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Trimestre (" + trimestre + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkTrimestre field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( trimestre );
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

    public List<Trimestre> findTrimestreEntities()
    {
        return findTrimestreEntities( true, -1, -1 );
    }

    public List<Trimestre> findTrimestreEntities( int maxResults, int firstResult )
    {
        return findTrimestreEntities( false, maxResults, firstResult );
    }

    private List<Trimestre> findTrimestreEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Trimestre.class ) );
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

    public Trimestre findTrimestre( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Trimestre.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getTrimestreCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trimestre> rt = cq.from( Trimestre.class );
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
