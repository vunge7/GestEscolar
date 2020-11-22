/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Mes;
import entity.PrecoPropina;
import entity.Propina;
import entity.ReciboPropina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class PropinaJpaController implements Serializable
{

    public PropinaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Propina propina )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Mes fkMes = propina.getFkMes();
            if ( fkMes != null )
            {
                fkMes = em.getReference( fkMes.getClass(), fkMes.getPkMes() );
                propina.setFkMes( fkMes );
            }
            PrecoPropina fkPrecoPropina = propina.getFkPrecoPropina();
            if ( fkPrecoPropina != null )
            {
                fkPrecoPropina = em.getReference( fkPrecoPropina.getClass(), fkPrecoPropina.getPkPrecoPropina() );
                propina.setFkPrecoPropina( fkPrecoPropina );
            }
            ReciboPropina fkReciboPropina = propina.getFkReciboPropina();
            if ( fkReciboPropina != null )
            {
                fkReciboPropina = em.getReference( fkReciboPropina.getClass(), fkReciboPropina.getPkReciboPropina() );
                propina.setFkReciboPropina( fkReciboPropina );
            }
            em.persist( propina );
            if ( fkMes != null )
            {
                fkMes.getPropinaList().add( propina );
                fkMes = em.merge( fkMes );
            }
            if ( fkPrecoPropina != null )
            {
                fkPrecoPropina.getPropinaList().add( propina );
                fkPrecoPropina = em.merge( fkPrecoPropina );
            }
            if ( fkReciboPropina != null )
            {
                fkReciboPropina.getPropinaList().add( propina );
                fkReciboPropina = em.merge( fkReciboPropina );
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

    public void edit( Propina propina ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Propina persistentPropina = em.find( Propina.class, propina.getPkPropina() );
            Mes fkMesOld = persistentPropina.getFkMes();
            Mes fkMesNew = propina.getFkMes();
            PrecoPropina fkPrecoPropinaOld = persistentPropina.getFkPrecoPropina();
            PrecoPropina fkPrecoPropinaNew = propina.getFkPrecoPropina();
            ReciboPropina fkReciboPropinaOld = persistentPropina.getFkReciboPropina();
            ReciboPropina fkReciboPropinaNew = propina.getFkReciboPropina();
            if ( fkMesNew != null )
            {
                fkMesNew = em.getReference( fkMesNew.getClass(), fkMesNew.getPkMes() );
                propina.setFkMes( fkMesNew );
            }
            if ( fkPrecoPropinaNew != null )
            {
                fkPrecoPropinaNew = em.getReference( fkPrecoPropinaNew.getClass(), fkPrecoPropinaNew.getPkPrecoPropina() );
                propina.setFkPrecoPropina( fkPrecoPropinaNew );
            }
            if ( fkReciboPropinaNew != null )
            {
                fkReciboPropinaNew = em.getReference( fkReciboPropinaNew.getClass(), fkReciboPropinaNew.getPkReciboPropina() );
                propina.setFkReciboPropina( fkReciboPropinaNew );
            }
            propina = em.merge( propina );
            if ( fkMesOld != null && !fkMesOld.equals( fkMesNew ) )
            {
                fkMesOld.getPropinaList().remove( propina );
                fkMesOld = em.merge( fkMesOld );
            }
            if ( fkMesNew != null && !fkMesNew.equals( fkMesOld ) )
            {
                fkMesNew.getPropinaList().add( propina );
                fkMesNew = em.merge( fkMesNew );
            }
            if ( fkPrecoPropinaOld != null && !fkPrecoPropinaOld.equals( fkPrecoPropinaNew ) )
            {
                fkPrecoPropinaOld.getPropinaList().remove( propina );
                fkPrecoPropinaOld = em.merge( fkPrecoPropinaOld );
            }
            if ( fkPrecoPropinaNew != null && !fkPrecoPropinaNew.equals( fkPrecoPropinaOld ) )
            {
                fkPrecoPropinaNew.getPropinaList().add( propina );
                fkPrecoPropinaNew = em.merge( fkPrecoPropinaNew );
            }
            if ( fkReciboPropinaOld != null && !fkReciboPropinaOld.equals( fkReciboPropinaNew ) )
            {
                fkReciboPropinaOld.getPropinaList().remove( propina );
                fkReciboPropinaOld = em.merge( fkReciboPropinaOld );
            }
            if ( fkReciboPropinaNew != null && !fkReciboPropinaNew.equals( fkReciboPropinaOld ) )
            {
                fkReciboPropinaNew.getPropinaList().add( propina );
                fkReciboPropinaNew = em.merge( fkReciboPropinaNew );
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if ( msg == null || msg.length() == 0 )
            {
                Integer id = propina.getPkPropina();
                if ( findPropina( id ) == null )
                {
                    throw new NonexistentEntityException( "The propina with id " + id + " no longer exists." );
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

    public void destroy( Integer id ) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Propina propina;
            try
            {
                propina = em.getReference( Propina.class, id );
                propina.getPkPropina();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The propina with id " + id + " no longer exists.", enfe );
            }
            Mes fkMes = propina.getFkMes();
            if ( fkMes != null )
            {
                fkMes.getPropinaList().remove( propina );
                fkMes = em.merge( fkMes );
            }
            PrecoPropina fkPrecoPropina = propina.getFkPrecoPropina();
            if ( fkPrecoPropina != null )
            {
                fkPrecoPropina.getPropinaList().remove( propina );
                fkPrecoPropina = em.merge( fkPrecoPropina );
            }
            ReciboPropina fkReciboPropina = propina.getFkReciboPropina();
            if ( fkReciboPropina != null )
            {
                fkReciboPropina.getPropinaList().remove( propina );
                fkReciboPropina = em.merge( fkReciboPropina );
            }
            em.remove( propina );
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

    public List<Propina> findPropinaEntities()
    {
        return findPropinaEntities( true, -1, -1 );
    }

    public List<Propina> findPropinaEntities( int maxResults, int firstResult )
    {
        return findPropinaEntities( false, maxResults, firstResult );
    }

    private List<Propina> findPropinaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Propina.class ) );
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

    public Propina findPropina( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Propina.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getPropinaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propina> rt = cq.from( Propina.class );
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
