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
import entity.Estudante;
import entity.Usuario;
import entity.Propina;
import entity.ReciboPropina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ReciboPropinaJpaController implements Serializable
{

    public ReciboPropinaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( ReciboPropina reciboPropina )
    {
        if ( reciboPropina.getPropinaList() == null )
        {
            reciboPropina.setPropinaList( new ArrayList<Propina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudante fkEstudante = reciboPropina.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante = em.getReference( fkEstudante.getClass(), fkEstudante.getPkEstudante() );
                reciboPropina.setFkEstudante( fkEstudante );
            }
            Usuario fkUsuario = reciboPropina.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario = em.getReference( fkUsuario.getClass(), fkUsuario.getPkUsuario() );
                reciboPropina.setFkUsuario( fkUsuario );
            }
            List<Propina> attachedPropinaList = new ArrayList<Propina>();
            for ( Propina propinaListPropinaToAttach : reciboPropina.getPropinaList() )
            {
                propinaListPropinaToAttach = em.getReference( propinaListPropinaToAttach.getClass(), propinaListPropinaToAttach.getPkPropina() );
                attachedPropinaList.add( propinaListPropinaToAttach );
            }
            reciboPropina.setPropinaList( attachedPropinaList );
            em.persist( reciboPropina );
            if ( fkEstudante != null )
            {
                fkEstudante.getReciboPropinaList().add( reciboPropina );
                fkEstudante = em.merge( fkEstudante );
            }
            if ( fkUsuario != null )
            {
                fkUsuario.getReciboPropinaList().add( reciboPropina );
                fkUsuario = em.merge( fkUsuario );
            }
            for ( Propina propinaListPropina : reciboPropina.getPropinaList() )
            {
                ReciboPropina oldFkReciboPropinaOfPropinaListPropina = propinaListPropina.getFkReciboPropina();
                propinaListPropina.setFkReciboPropina( reciboPropina );
                propinaListPropina = em.merge( propinaListPropina );
                if ( oldFkReciboPropinaOfPropinaListPropina != null )
                {
                    oldFkReciboPropinaOfPropinaListPropina.getPropinaList().remove( propinaListPropina );
                    oldFkReciboPropinaOfPropinaListPropina = em.merge( oldFkReciboPropinaOfPropinaListPropina );
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

    public void edit( ReciboPropina reciboPropina ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            ReciboPropina persistentReciboPropina = em.find( ReciboPropina.class, reciboPropina.getPkReciboPropina() );
            Estudante fkEstudanteOld = persistentReciboPropina.getFkEstudante();
            Estudante fkEstudanteNew = reciboPropina.getFkEstudante();
            Usuario fkUsuarioOld = persistentReciboPropina.getFkUsuario();
            Usuario fkUsuarioNew = reciboPropina.getFkUsuario();
            List<Propina> propinaListOld = persistentReciboPropina.getPropinaList();
            List<Propina> propinaListNew = reciboPropina.getPropinaList();
            List<String> illegalOrphanMessages = null;
            for ( Propina propinaListOldPropina : propinaListOld )
            {
                if ( !propinaListNew.contains( propinaListOldPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Propina " + propinaListOldPropina + " since its fkReciboPropina field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkEstudanteNew != null )
            {
                fkEstudanteNew = em.getReference( fkEstudanteNew.getClass(), fkEstudanteNew.getPkEstudante() );
                reciboPropina.setFkEstudante( fkEstudanteNew );
            }
            if ( fkUsuarioNew != null )
            {
                fkUsuarioNew = em.getReference( fkUsuarioNew.getClass(), fkUsuarioNew.getPkUsuario() );
                reciboPropina.setFkUsuario( fkUsuarioNew );
            }
            List<Propina> attachedPropinaListNew = new ArrayList<Propina>();
            for ( Propina propinaListNewPropinaToAttach : propinaListNew )
            {
                propinaListNewPropinaToAttach = em.getReference( propinaListNewPropinaToAttach.getClass(), propinaListNewPropinaToAttach.getPkPropina() );
                attachedPropinaListNew.add( propinaListNewPropinaToAttach );
            }
            propinaListNew = attachedPropinaListNew;
            reciboPropina.setPropinaList( propinaListNew );
            reciboPropina = em.merge( reciboPropina );
            if ( fkEstudanteOld != null && !fkEstudanteOld.equals( fkEstudanteNew ) )
            {
                fkEstudanteOld.getReciboPropinaList().remove( reciboPropina );
                fkEstudanteOld = em.merge( fkEstudanteOld );
            }
            if ( fkEstudanteNew != null && !fkEstudanteNew.equals( fkEstudanteOld ) )
            {
                fkEstudanteNew.getReciboPropinaList().add( reciboPropina );
                fkEstudanteNew = em.merge( fkEstudanteNew );
            }
            if ( fkUsuarioOld != null && !fkUsuarioOld.equals( fkUsuarioNew ) )
            {
                fkUsuarioOld.getReciboPropinaList().remove( reciboPropina );
                fkUsuarioOld = em.merge( fkUsuarioOld );
            }
            if ( fkUsuarioNew != null && !fkUsuarioNew.equals( fkUsuarioOld ) )
            {
                fkUsuarioNew.getReciboPropinaList().add( reciboPropina );
                fkUsuarioNew = em.merge( fkUsuarioNew );
            }
            for ( Propina propinaListNewPropina : propinaListNew )
            {
                if ( !propinaListOld.contains( propinaListNewPropina ) )
                {
                    ReciboPropina oldFkReciboPropinaOfPropinaListNewPropina = propinaListNewPropina.getFkReciboPropina();
                    propinaListNewPropina.setFkReciboPropina( reciboPropina );
                    propinaListNewPropina = em.merge( propinaListNewPropina );
                    if ( oldFkReciboPropinaOfPropinaListNewPropina != null && !oldFkReciboPropinaOfPropinaListNewPropina.equals( reciboPropina ) )
                    {
                        oldFkReciboPropinaOfPropinaListNewPropina.getPropinaList().remove( propinaListNewPropina );
                        oldFkReciboPropinaOfPropinaListNewPropina = em.merge( oldFkReciboPropinaOfPropinaListNewPropina );
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
                Integer id = reciboPropina.getPkReciboPropina();
                if ( findReciboPropina( id ) == null )
                {
                    throw new NonexistentEntityException( "The reciboPropina with id " + id + " no longer exists." );
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
            ReciboPropina reciboPropina;
            try
            {
                reciboPropina = em.getReference( ReciboPropina.class, id );
                reciboPropina.getPkReciboPropina();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The reciboPropina with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Propina> propinaListOrphanCheck = reciboPropina.getPropinaList();
            for ( Propina propinaListOrphanCheckPropina : propinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This ReciboPropina (" + reciboPropina + ") cannot be destroyed since the Propina " + propinaListOrphanCheckPropina + " in its propinaList field has a non-nullable fkReciboPropina field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Estudante fkEstudante = reciboPropina.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante.getReciboPropinaList().remove( reciboPropina );
                fkEstudante = em.merge( fkEstudante );
            }
            Usuario fkUsuario = reciboPropina.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario.getReciboPropinaList().remove( reciboPropina );
                fkUsuario = em.merge( fkUsuario );
            }
            em.remove( reciboPropina );
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

    public List<ReciboPropina> findReciboPropinaEntities()
    {
        return findReciboPropinaEntities( true, -1, -1 );
    }

    public List<ReciboPropina> findReciboPropinaEntities( int maxResults, int firstResult )
    {
        return findReciboPropinaEntities( false, maxResults, firstResult );
    }

    private List<ReciboPropina> findReciboPropinaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( ReciboPropina.class ) );
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

    public ReciboPropina findReciboPropina( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( ReciboPropina.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getReciboPropinaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReciboPropina> rt = cq.from( ReciboPropina.class );
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
