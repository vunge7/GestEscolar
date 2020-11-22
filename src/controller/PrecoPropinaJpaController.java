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
import entity.Classe;
import entity.Curso;
import entity.PrecoPropina;
import entity.Usuario;
import entity.Propina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class PrecoPropinaJpaController implements Serializable
{

    public PrecoPropinaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( PrecoPropina precoPropina )
    {
        if ( precoPropina.getPropinaList() == null )
        {
            precoPropina.setPropinaList( new ArrayList<Propina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe fkClasse = precoPropina.getFkClasse();
            if ( fkClasse != null )
            {
                fkClasse = em.getReference( fkClasse.getClass(), fkClasse.getPkClasse() );
                precoPropina.setFkClasse( fkClasse );
            }
            Curso fkCurso = precoPropina.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso = em.getReference( fkCurso.getClass(), fkCurso.getPkCurso() );
                precoPropina.setFkCurso( fkCurso );
            }
            Usuario fkUsuario = precoPropina.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario = em.getReference( fkUsuario.getClass(), fkUsuario.getPkUsuario() );
                precoPropina.setFkUsuario( fkUsuario );
            }
            List<Propina> attachedPropinaList = new ArrayList<Propina>();
            for ( Propina propinaListPropinaToAttach : precoPropina.getPropinaList() )
            {
                propinaListPropinaToAttach = em.getReference( propinaListPropinaToAttach.getClass(), propinaListPropinaToAttach.getPkPropina() );
                attachedPropinaList.add( propinaListPropinaToAttach );
            }
            precoPropina.setPropinaList( attachedPropinaList );
            em.persist( precoPropina );
            if ( fkClasse != null )
            {
                fkClasse.getPrecoPropinaList().add( precoPropina );
                fkClasse = em.merge( fkClasse );
            }
            if ( fkCurso != null )
            {
                fkCurso.getPrecoPropinaList().add( precoPropina );
                fkCurso = em.merge( fkCurso );
            }
            if ( fkUsuario != null )
            {
                fkUsuario.getPrecoPropinaList().add( precoPropina );
                fkUsuario = em.merge( fkUsuario );
            }
            for ( Propina propinaListPropina : precoPropina.getPropinaList() )
            {
                PrecoPropina oldFkPrecoPropinaOfPropinaListPropina = propinaListPropina.getFkPrecoPropina();
                propinaListPropina.setFkPrecoPropina( precoPropina );
                propinaListPropina = em.merge( propinaListPropina );
                if ( oldFkPrecoPropinaOfPropinaListPropina != null )
                {
                    oldFkPrecoPropinaOfPropinaListPropina.getPropinaList().remove( propinaListPropina );
                    oldFkPrecoPropinaOfPropinaListPropina = em.merge( oldFkPrecoPropinaOfPropinaListPropina );
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

    public void edit( PrecoPropina precoPropina ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            PrecoPropina persistentPrecoPropina = em.find( PrecoPropina.class, precoPropina.getPkPrecoPropina() );
            Classe fkClasseOld = persistentPrecoPropina.getFkClasse();
            Classe fkClasseNew = precoPropina.getFkClasse();
            Curso fkCursoOld = persistentPrecoPropina.getFkCurso();
            Curso fkCursoNew = precoPropina.getFkCurso();
            Usuario fkUsuarioOld = persistentPrecoPropina.getFkUsuario();
            Usuario fkUsuarioNew = precoPropina.getFkUsuario();
            List<Propina> propinaListOld = persistentPrecoPropina.getPropinaList();
            List<Propina> propinaListNew = precoPropina.getPropinaList();
            List<String> illegalOrphanMessages = null;
            for ( Propina propinaListOldPropina : propinaListOld )
            {
                if ( !propinaListNew.contains( propinaListOldPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Propina " + propinaListOldPropina + " since its fkPrecoPropina field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkClasseNew != null )
            {
                fkClasseNew = em.getReference( fkClasseNew.getClass(), fkClasseNew.getPkClasse() );
                precoPropina.setFkClasse( fkClasseNew );
            }
            if ( fkCursoNew != null )
            {
                fkCursoNew = em.getReference( fkCursoNew.getClass(), fkCursoNew.getPkCurso() );
                precoPropina.setFkCurso( fkCursoNew );
            }
            if ( fkUsuarioNew != null )
            {
                fkUsuarioNew = em.getReference( fkUsuarioNew.getClass(), fkUsuarioNew.getPkUsuario() );
                precoPropina.setFkUsuario( fkUsuarioNew );
            }
            List<Propina> attachedPropinaListNew = new ArrayList<Propina>();
            for ( Propina propinaListNewPropinaToAttach : propinaListNew )
            {
                propinaListNewPropinaToAttach = em.getReference( propinaListNewPropinaToAttach.getClass(), propinaListNewPropinaToAttach.getPkPropina() );
                attachedPropinaListNew.add( propinaListNewPropinaToAttach );
            }
            propinaListNew = attachedPropinaListNew;
            precoPropina.setPropinaList( propinaListNew );
            precoPropina = em.merge( precoPropina );
            if ( fkClasseOld != null && !fkClasseOld.equals( fkClasseNew ) )
            {
                fkClasseOld.getPrecoPropinaList().remove( precoPropina );
                fkClasseOld = em.merge( fkClasseOld );
            }
            if ( fkClasseNew != null && !fkClasseNew.equals( fkClasseOld ) )
            {
                fkClasseNew.getPrecoPropinaList().add( precoPropina );
                fkClasseNew = em.merge( fkClasseNew );
            }
            if ( fkCursoOld != null && !fkCursoOld.equals( fkCursoNew ) )
            {
                fkCursoOld.getPrecoPropinaList().remove( precoPropina );
                fkCursoOld = em.merge( fkCursoOld );
            }
            if ( fkCursoNew != null && !fkCursoNew.equals( fkCursoOld ) )
            {
                fkCursoNew.getPrecoPropinaList().add( precoPropina );
                fkCursoNew = em.merge( fkCursoNew );
            }
            if ( fkUsuarioOld != null && !fkUsuarioOld.equals( fkUsuarioNew ) )
            {
                fkUsuarioOld.getPrecoPropinaList().remove( precoPropina );
                fkUsuarioOld = em.merge( fkUsuarioOld );
            }
            if ( fkUsuarioNew != null && !fkUsuarioNew.equals( fkUsuarioOld ) )
            {
                fkUsuarioNew.getPrecoPropinaList().add( precoPropina );
                fkUsuarioNew = em.merge( fkUsuarioNew );
            }
            for ( Propina propinaListNewPropina : propinaListNew )
            {
                if ( !propinaListOld.contains( propinaListNewPropina ) )
                {
                    PrecoPropina oldFkPrecoPropinaOfPropinaListNewPropina = propinaListNewPropina.getFkPrecoPropina();
                    propinaListNewPropina.setFkPrecoPropina( precoPropina );
                    propinaListNewPropina = em.merge( propinaListNewPropina );
                    if ( oldFkPrecoPropinaOfPropinaListNewPropina != null && !oldFkPrecoPropinaOfPropinaListNewPropina.equals( precoPropina ) )
                    {
                        oldFkPrecoPropinaOfPropinaListNewPropina.getPropinaList().remove( propinaListNewPropina );
                        oldFkPrecoPropinaOfPropinaListNewPropina = em.merge( oldFkPrecoPropinaOfPropinaListNewPropina );
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
                Integer id = precoPropina.getPkPrecoPropina();
                if ( findPrecoPropina( id ) == null )
                {
                    throw new NonexistentEntityException( "The precoPropina with id " + id + " no longer exists." );
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
            PrecoPropina precoPropina;
            try
            {
                precoPropina = em.getReference( PrecoPropina.class, id );
                precoPropina.getPkPrecoPropina();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The precoPropina with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Propina> propinaListOrphanCheck = precoPropina.getPropinaList();
            for ( Propina propinaListOrphanCheckPropina : propinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This PrecoPropina (" + precoPropina + ") cannot be destroyed since the Propina " + propinaListOrphanCheckPropina + " in its propinaList field has a non-nullable fkPrecoPropina field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Classe fkClasse = precoPropina.getFkClasse();
            if ( fkClasse != null )
            {
                fkClasse.getPrecoPropinaList().remove( precoPropina );
                fkClasse = em.merge( fkClasse );
            }
            Curso fkCurso = precoPropina.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso.getPrecoPropinaList().remove( precoPropina );
                fkCurso = em.merge( fkCurso );
            }
            Usuario fkUsuario = precoPropina.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario.getPrecoPropinaList().remove( precoPropina );
                fkUsuario = em.merge( fkUsuario );
            }
            em.remove( precoPropina );
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

    public List<PrecoPropina> findPrecoPropinaEntities()
    {
        return findPrecoPropinaEntities( true, -1, -1 );
    }

    public List<PrecoPropina> findPrecoPropinaEntities( int maxResults, int firstResult )
    {
        return findPrecoPropinaEntities( false, maxResults, firstResult );
    }

    private List<PrecoPropina> findPrecoPropinaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( PrecoPropina.class ) );
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

    public PrecoPropina findPrecoPropina( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( PrecoPropina.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getPrecoPropinaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecoPropina> rt = cq.from( PrecoPropina.class );
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
