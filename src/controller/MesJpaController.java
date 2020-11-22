/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Mes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Propina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class MesJpaController implements Serializable
{

    public MesJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Mes mes )
    {
        if ( mes.getPropinaList() == null )
        {
            mes.setPropinaList( new ArrayList<Propina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Propina> attachedPropinaList = new ArrayList<Propina>();
            for ( Propina propinaListPropinaToAttach : mes.getPropinaList() )
            {
                propinaListPropinaToAttach = em.getReference( propinaListPropinaToAttach.getClass(), propinaListPropinaToAttach.getPkPropina() );
                attachedPropinaList.add( propinaListPropinaToAttach );
            }
            mes.setPropinaList( attachedPropinaList );
            em.persist( mes );
            for ( Propina propinaListPropina : mes.getPropinaList() )
            {
                Mes oldFkMesOfPropinaListPropina = propinaListPropina.getFkMes();
                propinaListPropina.setFkMes( mes );
                propinaListPropina = em.merge( propinaListPropina );
                if ( oldFkMesOfPropinaListPropina != null )
                {
                    oldFkMesOfPropinaListPropina.getPropinaList().remove( propinaListPropina );
                    oldFkMesOfPropinaListPropina = em.merge( oldFkMesOfPropinaListPropina );
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

    public void edit( Mes mes ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Mes persistentMes = em.find( Mes.class, mes.getPkMes() );
            List<Propina> propinaListOld = persistentMes.getPropinaList();
            List<Propina> propinaListNew = mes.getPropinaList();
            List<String> illegalOrphanMessages = null;
            for ( Propina propinaListOldPropina : propinaListOld )
            {
                if ( !propinaListNew.contains( propinaListOldPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Propina " + propinaListOldPropina + " since its fkMes field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Propina> attachedPropinaListNew = new ArrayList<Propina>();
            for ( Propina propinaListNewPropinaToAttach : propinaListNew )
            {
                propinaListNewPropinaToAttach = em.getReference( propinaListNewPropinaToAttach.getClass(), propinaListNewPropinaToAttach.getPkPropina() );
                attachedPropinaListNew.add( propinaListNewPropinaToAttach );
            }
            propinaListNew = attachedPropinaListNew;
            mes.setPropinaList( propinaListNew );
            mes = em.merge( mes );
            for ( Propina propinaListNewPropina : propinaListNew )
            {
                if ( !propinaListOld.contains( propinaListNewPropina ) )
                {
                    Mes oldFkMesOfPropinaListNewPropina = propinaListNewPropina.getFkMes();
                    propinaListNewPropina.setFkMes( mes );
                    propinaListNewPropina = em.merge( propinaListNewPropina );
                    if ( oldFkMesOfPropinaListNewPropina != null && !oldFkMesOfPropinaListNewPropina.equals( mes ) )
                    {
                        oldFkMesOfPropinaListNewPropina.getPropinaList().remove( propinaListNewPropina );
                        oldFkMesOfPropinaListNewPropina = em.merge( oldFkMesOfPropinaListNewPropina );
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
                Integer id = mes.getPkMes();
                if ( findMes( id ) == null )
                {
                    throw new NonexistentEntityException( "The mes with id " + id + " no longer exists." );
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
            Mes mes;
            try
            {
                mes = em.getReference( Mes.class, id );
                mes.getPkMes();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The mes with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Propina> propinaListOrphanCheck = mes.getPropinaList();
            for ( Propina propinaListOrphanCheckPropina : propinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Mes (" + mes + ") cannot be destroyed since the Propina " + propinaListOrphanCheckPropina + " in its propinaList field has a non-nullable fkMes field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( mes );
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

    public List<Mes> findMesEntities()
    {
        return findMesEntities( true, -1, -1 );
    }

    public List<Mes> findMesEntities( int maxResults, int firstResult )
    {
        return findMesEntities( false, maxResults, firstResult );
    }

    private List<Mes> findMesEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Mes.class ) );
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

    public Mes findMes( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Mes.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getMesCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mes> rt = cq.from( Mes.class );
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
