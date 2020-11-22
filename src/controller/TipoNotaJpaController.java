/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Nota;
import entity.TipoNota;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class TipoNotaJpaController implements Serializable
{

    public TipoNotaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( TipoNota tipoNota ) throws PreexistingEntityException, Exception
    {
        if ( tipoNota.getNotaList() == null )
        {
            tipoNota.setNotaList( new ArrayList<Nota>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : tipoNota.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            tipoNota.setNotaList( attachedNotaList );
            em.persist( tipoNota );
            for ( Nota notaListNota : tipoNota.getNotaList() )
            {
                TipoNota oldFkTipoNotaOfNotaListNota = notaListNota.getFkTipoNota();
                notaListNota.setFkTipoNota( tipoNota );
                notaListNota = em.merge( notaListNota );
                if ( oldFkTipoNotaOfNotaListNota != null )
                {
                    oldFkTipoNotaOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkTipoNotaOfNotaListNota = em.merge( oldFkTipoNotaOfNotaListNota );
                }
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            if ( findTipoNota( tipoNota.getPkTipoNota() ) != null )
            {
                throw new PreexistingEntityException( "TipoNota " + tipoNota + " already exists.", ex );
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

    public void edit( TipoNota tipoNota ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNota persistentTipoNota = em.find( TipoNota.class, tipoNota.getPkTipoNota() );
            List<Nota> notaListOld = persistentTipoNota.getNotaList();
            List<Nota> notaListNew = tipoNota.getNotaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkTipoNota field is not nullable." );
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
            tipoNota.setNotaList( notaListNew );
            tipoNota = em.merge( tipoNota );
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    TipoNota oldFkTipoNotaOfNotaListNewNota = notaListNewNota.getFkTipoNota();
                    notaListNewNota.setFkTipoNota( tipoNota );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkTipoNotaOfNotaListNewNota != null && !oldFkTipoNotaOfNotaListNewNota.equals( tipoNota ) )
                    {
                        oldFkTipoNotaOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkTipoNotaOfNotaListNewNota = em.merge( oldFkTipoNotaOfNotaListNewNota );
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
                Integer id = tipoNota.getPkTipoNota();
                if ( findTipoNota( id ) == null )
                {
                    throw new NonexistentEntityException( "The tipoNota with id " + id + " no longer exists." );
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
            TipoNota tipoNota;
            try
            {
                tipoNota = em.getReference( TipoNota.class, id );
                tipoNota.getPkTipoNota();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The tipoNota with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = tipoNota.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This TipoNota (" + tipoNota + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkTipoNota field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( tipoNota );
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

    public List<TipoNota> findTipoNotaEntities()
    {
        return findTipoNotaEntities( true, -1, -1 );
    }

    public List<TipoNota> findTipoNotaEntities( int maxResults, int firstResult )
    {
        return findTipoNotaEntities( false, maxResults, firstResult );
    }

    private List<TipoNota> findTipoNotaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( TipoNota.class ) );
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

    public TipoNota findTipoNota( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( TipoNota.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getTipoNotaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoNota> rt = cq.from( TipoNota.class );
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
