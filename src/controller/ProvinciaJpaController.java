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
import entity.Pais;
import entity.Municipio;
import entity.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ProvinciaJpaController implements Serializable
{

    public ProvinciaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Provincia provincia )
    {
        if ( provincia.getMunicipioList() == null )
        {
            provincia.setMunicipioList( new ArrayList<Municipio>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais fkPais = provincia.getFkPais();
            if ( fkPais != null )
            {
                fkPais = em.getReference( fkPais.getClass(), fkPais.getPkPais() );
                provincia.setFkPais( fkPais );
            }
            List<Municipio> attachedMunicipioList = new ArrayList<Municipio>();
            for ( Municipio municipioListMunicipioToAttach : provincia.getMunicipioList() )
            {
                municipioListMunicipioToAttach = em.getReference( municipioListMunicipioToAttach.getClass(), municipioListMunicipioToAttach.getPkmunicipio() );
                attachedMunicipioList.add( municipioListMunicipioToAttach );
            }
            provincia.setMunicipioList( attachedMunicipioList );
            em.persist( provincia );
            if ( fkPais != null )
            {
                fkPais.getProvinciaList().add( provincia );
                fkPais = em.merge( fkPais );
            }
            for ( Municipio municipioListMunicipio : provincia.getMunicipioList() )
            {
                Provincia oldFkProvinciaOfMunicipioListMunicipio = municipioListMunicipio.getFkProvincia();
                municipioListMunicipio.setFkProvincia( provincia );
                municipioListMunicipio = em.merge( municipioListMunicipio );
                if ( oldFkProvinciaOfMunicipioListMunicipio != null )
                {
                    oldFkProvinciaOfMunicipioListMunicipio.getMunicipioList().remove( municipioListMunicipio );
                    oldFkProvinciaOfMunicipioListMunicipio = em.merge( oldFkProvinciaOfMunicipioListMunicipio );
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

    public void edit( Provincia provincia ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find( Provincia.class, provincia.getPkProvincia() );
            Pais fkPaisOld = persistentProvincia.getFkPais();
            Pais fkPaisNew = provincia.getFkPais();
            List<Municipio> municipioListOld = persistentProvincia.getMunicipioList();
            List<Municipio> municipioListNew = provincia.getMunicipioList();
            List<String> illegalOrphanMessages = null;
            for ( Municipio municipioListOldMunicipio : municipioListOld )
            {
                if ( !municipioListNew.contains( municipioListOldMunicipio ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Municipio " + municipioListOldMunicipio + " since its fkProvincia field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkPaisNew != null )
            {
                fkPaisNew = em.getReference( fkPaisNew.getClass(), fkPaisNew.getPkPais() );
                provincia.setFkPais( fkPaisNew );
            }
            List<Municipio> attachedMunicipioListNew = new ArrayList<Municipio>();
            for ( Municipio municipioListNewMunicipioToAttach : municipioListNew )
            {
                municipioListNewMunicipioToAttach = em.getReference( municipioListNewMunicipioToAttach.getClass(), municipioListNewMunicipioToAttach.getPkmunicipio() );
                attachedMunicipioListNew.add( municipioListNewMunicipioToAttach );
            }
            municipioListNew = attachedMunicipioListNew;
            provincia.setMunicipioList( municipioListNew );
            provincia = em.merge( provincia );
            if ( fkPaisOld != null && !fkPaisOld.equals( fkPaisNew ) )
            {
                fkPaisOld.getProvinciaList().remove( provincia );
                fkPaisOld = em.merge( fkPaisOld );
            }
            if ( fkPaisNew != null && !fkPaisNew.equals( fkPaisOld ) )
            {
                fkPaisNew.getProvinciaList().add( provincia );
                fkPaisNew = em.merge( fkPaisNew );
            }
            for ( Municipio municipioListNewMunicipio : municipioListNew )
            {
                if ( !municipioListOld.contains( municipioListNewMunicipio ) )
                {
                    Provincia oldFkProvinciaOfMunicipioListNewMunicipio = municipioListNewMunicipio.getFkProvincia();
                    municipioListNewMunicipio.setFkProvincia( provincia );
                    municipioListNewMunicipio = em.merge( municipioListNewMunicipio );
                    if ( oldFkProvinciaOfMunicipioListNewMunicipio != null && !oldFkProvinciaOfMunicipioListNewMunicipio.equals( provincia ) )
                    {
                        oldFkProvinciaOfMunicipioListNewMunicipio.getMunicipioList().remove( municipioListNewMunicipio );
                        oldFkProvinciaOfMunicipioListNewMunicipio = em.merge( oldFkProvinciaOfMunicipioListNewMunicipio );
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
                Integer id = provincia.getPkProvincia();
                if ( findProvincia( id ) == null )
                {
                    throw new NonexistentEntityException( "The provincia with id " + id + " no longer exists." );
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
            Provincia provincia;
            try
            {
                provincia = em.getReference( Provincia.class, id );
                provincia.getPkProvincia();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The provincia with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Municipio> municipioListOrphanCheck = provincia.getMunicipioList();
            for ( Municipio municipioListOrphanCheckMunicipio : municipioListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Provincia (" + provincia + ") cannot be destroyed since the Municipio " + municipioListOrphanCheckMunicipio + " in its municipioList field has a non-nullable fkProvincia field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Pais fkPais = provincia.getFkPais();
            if ( fkPais != null )
            {
                fkPais.getProvinciaList().remove( provincia );
                fkPais = em.merge( fkPais );
            }
            em.remove( provincia );
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

    public List<Provincia> findProvinciaEntities()
    {
        return findProvinciaEntities( true, -1, -1 );
    }

    public List<Provincia> findProvinciaEntities( int maxResults, int firstResult )
    {
        return findProvinciaEntities( false, maxResults, firstResult );
    }

    private List<Provincia> findProvinciaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Provincia.class ) );
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

    public Provincia findProvincia( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Provincia.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getProvinciaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from( Provincia.class );
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
