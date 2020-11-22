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
import entity.Provincia;
import entity.Estudante;
import entity.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class MunicipioJpaController implements Serializable
{

    public MunicipioJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Municipio municipio )
    {
        if ( municipio.getEstudanteList() == null )
        {
            municipio.setEstudanteList( new ArrayList<Estudante>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia fkProvincia = municipio.getFkProvincia();
            if ( fkProvincia != null )
            {
                fkProvincia = em.getReference( fkProvincia.getClass(), fkProvincia.getPkProvincia() );
                municipio.setFkProvincia( fkProvincia );
            }
            List<Estudante> attachedEstudanteList = new ArrayList<Estudante>();
            for ( Estudante estudanteListEstudanteToAttach : municipio.getEstudanteList() )
            {
                estudanteListEstudanteToAttach = em.getReference( estudanteListEstudanteToAttach.getClass(), estudanteListEstudanteToAttach.getPkEstudante() );
                attachedEstudanteList.add( estudanteListEstudanteToAttach );
            }
            municipio.setEstudanteList( attachedEstudanteList );
            em.persist( municipio );
            if ( fkProvincia != null )
            {
                fkProvincia.getMunicipioList().add( municipio );
                fkProvincia = em.merge( fkProvincia );
            }
            for ( Estudante estudanteListEstudante : municipio.getEstudanteList() )
            {
                Municipio oldFkMunicipioOfEstudanteListEstudante = estudanteListEstudante.getFkMunicipio();
                estudanteListEstudante.setFkMunicipio( municipio );
                estudanteListEstudante = em.merge( estudanteListEstudante );
                if ( oldFkMunicipioOfEstudanteListEstudante != null )
                {
                    oldFkMunicipioOfEstudanteListEstudante.getEstudanteList().remove( estudanteListEstudante );
                    oldFkMunicipioOfEstudanteListEstudante = em.merge( oldFkMunicipioOfEstudanteListEstudante );
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

    public void edit( Municipio municipio ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find( Municipio.class, municipio.getPkmunicipio() );
            Provincia fkProvinciaOld = persistentMunicipio.getFkProvincia();
            Provincia fkProvinciaNew = municipio.getFkProvincia();
            List<Estudante> estudanteListOld = persistentMunicipio.getEstudanteList();
            List<Estudante> estudanteListNew = municipio.getEstudanteList();
            List<String> illegalOrphanMessages = null;
            for ( Estudante estudanteListOldEstudante : estudanteListOld )
            {
                if ( !estudanteListNew.contains( estudanteListOldEstudante ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Estudante " + estudanteListOldEstudante + " since its fkMunicipio field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkProvinciaNew != null )
            {
                fkProvinciaNew = em.getReference( fkProvinciaNew.getClass(), fkProvinciaNew.getPkProvincia() );
                municipio.setFkProvincia( fkProvinciaNew );
            }
            List<Estudante> attachedEstudanteListNew = new ArrayList<Estudante>();
            for ( Estudante estudanteListNewEstudanteToAttach : estudanteListNew )
            {
                estudanteListNewEstudanteToAttach = em.getReference( estudanteListNewEstudanteToAttach.getClass(), estudanteListNewEstudanteToAttach.getPkEstudante() );
                attachedEstudanteListNew.add( estudanteListNewEstudanteToAttach );
            }
            estudanteListNew = attachedEstudanteListNew;
            municipio.setEstudanteList( estudanteListNew );
            municipio = em.merge( municipio );
            if ( fkProvinciaOld != null && !fkProvinciaOld.equals( fkProvinciaNew ) )
            {
                fkProvinciaOld.getMunicipioList().remove( municipio );
                fkProvinciaOld = em.merge( fkProvinciaOld );
            }
            if ( fkProvinciaNew != null && !fkProvinciaNew.equals( fkProvinciaOld ) )
            {
                fkProvinciaNew.getMunicipioList().add( municipio );
                fkProvinciaNew = em.merge( fkProvinciaNew );
            }
            for ( Estudante estudanteListNewEstudante : estudanteListNew )
            {
                if ( !estudanteListOld.contains( estudanteListNewEstudante ) )
                {
                    Municipio oldFkMunicipioOfEstudanteListNewEstudante = estudanteListNewEstudante.getFkMunicipio();
                    estudanteListNewEstudante.setFkMunicipio( municipio );
                    estudanteListNewEstudante = em.merge( estudanteListNewEstudante );
                    if ( oldFkMunicipioOfEstudanteListNewEstudante != null && !oldFkMunicipioOfEstudanteListNewEstudante.equals( municipio ) )
                    {
                        oldFkMunicipioOfEstudanteListNewEstudante.getEstudanteList().remove( estudanteListNewEstudante );
                        oldFkMunicipioOfEstudanteListNewEstudante = em.merge( oldFkMunicipioOfEstudanteListNewEstudante );
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
                Integer id = municipio.getPkmunicipio();
                if ( findMunicipio( id ) == null )
                {
                    throw new NonexistentEntityException( "The municipio with id " + id + " no longer exists." );
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
            Municipio municipio;
            try
            {
                municipio = em.getReference( Municipio.class, id );
                municipio.getPkmunicipio();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The municipio with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Estudante> estudanteListOrphanCheck = municipio.getEstudanteList();
            for ( Estudante estudanteListOrphanCheckEstudante : estudanteListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Municipio (" + municipio + ") cannot be destroyed since the Estudante " + estudanteListOrphanCheckEstudante + " in its estudanteList field has a non-nullable fkMunicipio field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Provincia fkProvincia = municipio.getFkProvincia();
            if ( fkProvincia != null )
            {
                fkProvincia.getMunicipioList().remove( municipio );
                fkProvincia = em.merge( fkProvincia );
            }
            em.remove( municipio );
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

    public List<Municipio> findMunicipioEntities()
    {
        return findMunicipioEntities( true, -1, -1 );
    }

    public List<Municipio> findMunicipioEntities( int maxResults, int firstResult )
    {
        return findMunicipioEntities( false, maxResults, firstResult );
    }

    private List<Municipio> findMunicipioEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Municipio.class ) );
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

    public Municipio findMunicipio( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Municipio.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getMunicipioCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from( Municipio.class );
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
