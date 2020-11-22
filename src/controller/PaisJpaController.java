/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Pais;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class PaisJpaController implements Serializable
{

    public PaisJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Pais pais )
    {
        if ( pais.getProvinciaList() == null )
        {
            pais.setProvinciaList( new ArrayList<Provincia>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Provincia> attachedProvinciaList = new ArrayList<Provincia>();
            for ( Provincia provinciaListProvinciaToAttach : pais.getProvinciaList() )
            {
                provinciaListProvinciaToAttach = em.getReference( provinciaListProvinciaToAttach.getClass(), provinciaListProvinciaToAttach.getPkProvincia() );
                attachedProvinciaList.add( provinciaListProvinciaToAttach );
            }
            pais.setProvinciaList( attachedProvinciaList );
            em.persist( pais );
            for ( Provincia provinciaListProvincia : pais.getProvinciaList() )
            {
                Pais oldFkPaisOfProvinciaListProvincia = provinciaListProvincia.getFkPais();
                provinciaListProvincia.setFkPais( pais );
                provinciaListProvincia = em.merge( provinciaListProvincia );
                if ( oldFkPaisOfProvinciaListProvincia != null )
                {
                    oldFkPaisOfProvinciaListProvincia.getProvinciaList().remove( provinciaListProvincia );
                    oldFkPaisOfProvinciaListProvincia = em.merge( oldFkPaisOfProvinciaListProvincia );
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

    public void edit( Pais pais ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find( Pais.class, pais.getPkPais() );
            List<Provincia> provinciaListOld = persistentPais.getProvinciaList();
            List<Provincia> provinciaListNew = pais.getProvinciaList();
            List<String> illegalOrphanMessages = null;
            for ( Provincia provinciaListOldProvincia : provinciaListOld )
            {
                if ( !provinciaListNew.contains( provinciaListOldProvincia ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Provincia " + provinciaListOldProvincia + " since its fkPais field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Provincia> attachedProvinciaListNew = new ArrayList<Provincia>();
            for ( Provincia provinciaListNewProvinciaToAttach : provinciaListNew )
            {
                provinciaListNewProvinciaToAttach = em.getReference( provinciaListNewProvinciaToAttach.getClass(), provinciaListNewProvinciaToAttach.getPkProvincia() );
                attachedProvinciaListNew.add( provinciaListNewProvinciaToAttach );
            }
            provinciaListNew = attachedProvinciaListNew;
            pais.setProvinciaList( provinciaListNew );
            pais = em.merge( pais );
            for ( Provincia provinciaListNewProvincia : provinciaListNew )
            {
                if ( !provinciaListOld.contains( provinciaListNewProvincia ) )
                {
                    Pais oldFkPaisOfProvinciaListNewProvincia = provinciaListNewProvincia.getFkPais();
                    provinciaListNewProvincia.setFkPais( pais );
                    provinciaListNewProvincia = em.merge( provinciaListNewProvincia );
                    if ( oldFkPaisOfProvinciaListNewProvincia != null && !oldFkPaisOfProvinciaListNewProvincia.equals( pais ) )
                    {
                        oldFkPaisOfProvinciaListNewProvincia.getProvinciaList().remove( provinciaListNewProvincia );
                        oldFkPaisOfProvinciaListNewProvincia = em.merge( oldFkPaisOfProvinciaListNewProvincia );
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
                Integer id = pais.getPkPais();
                if ( findPais( id ) == null )
                {
                    throw new NonexistentEntityException( "The pais with id " + id + " no longer exists." );
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
            Pais pais;
            try
            {
                pais = em.getReference( Pais.class, id );
                pais.getPkPais();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The pais with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Provincia> provinciaListOrphanCheck = pais.getProvinciaList();
            for ( Provincia provinciaListOrphanCheckProvincia : provinciaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Pais (" + pais + ") cannot be destroyed since the Provincia " + provinciaListOrphanCheckProvincia + " in its provinciaList field has a non-nullable fkPais field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( pais );
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

    public List<Pais> findPaisEntities()
    {
        return findPaisEntities( true, -1, -1 );
    }

    public List<Pais> findPaisEntities( int maxResults, int firstResult )
    {
        return findPaisEntities( false, maxResults, firstResult );
    }

    private List<Pais> findPaisEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Pais.class ) );
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

    public Pais findPais( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Pais.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getPaisCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from( Pais.class );
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
