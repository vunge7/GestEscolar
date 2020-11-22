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
import entity.Sexo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class SexoJpaController implements Serializable
{

    public SexoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Sexo sexo )
    {
        if ( sexo.getEstudanteList() == null )
        {
            sexo.setEstudanteList( new ArrayList<Estudante>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudante> attachedEstudanteList = new ArrayList<Estudante>();
            for ( Estudante estudanteListEstudanteToAttach : sexo.getEstudanteList() )
            {
                estudanteListEstudanteToAttach = em.getReference( estudanteListEstudanteToAttach.getClass(), estudanteListEstudanteToAttach.getPkEstudante() );
                attachedEstudanteList.add( estudanteListEstudanteToAttach );
            }
            sexo.setEstudanteList( attachedEstudanteList );
            em.persist( sexo );
            for ( Estudante estudanteListEstudante : sexo.getEstudanteList() )
            {
                Sexo oldFkSexoOfEstudanteListEstudante = estudanteListEstudante.getFkSexo();
                estudanteListEstudante.setFkSexo( sexo );
                estudanteListEstudante = em.merge( estudanteListEstudante );
                if ( oldFkSexoOfEstudanteListEstudante != null )
                {
                    oldFkSexoOfEstudanteListEstudante.getEstudanteList().remove( estudanteListEstudante );
                    oldFkSexoOfEstudanteListEstudante = em.merge( oldFkSexoOfEstudanteListEstudante );
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

    public void edit( Sexo sexo ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Sexo persistentSexo = em.find( Sexo.class, sexo.getPkSexo() );
            List<Estudante> estudanteListOld = persistentSexo.getEstudanteList();
            List<Estudante> estudanteListNew = sexo.getEstudanteList();
            List<String> illegalOrphanMessages = null;
            for ( Estudante estudanteListOldEstudante : estudanteListOld )
            {
                if ( !estudanteListNew.contains( estudanteListOldEstudante ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Estudante " + estudanteListOldEstudante + " since its fkSexo field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Estudante> attachedEstudanteListNew = new ArrayList<Estudante>();
            for ( Estudante estudanteListNewEstudanteToAttach : estudanteListNew )
            {
                estudanteListNewEstudanteToAttach = em.getReference( estudanteListNewEstudanteToAttach.getClass(), estudanteListNewEstudanteToAttach.getPkEstudante() );
                attachedEstudanteListNew.add( estudanteListNewEstudanteToAttach );
            }
            estudanteListNew = attachedEstudanteListNew;
            sexo.setEstudanteList( estudanteListNew );
            sexo = em.merge( sexo );
            for ( Estudante estudanteListNewEstudante : estudanteListNew )
            {
                if ( !estudanteListOld.contains( estudanteListNewEstudante ) )
                {
                    Sexo oldFkSexoOfEstudanteListNewEstudante = estudanteListNewEstudante.getFkSexo();
                    estudanteListNewEstudante.setFkSexo( sexo );
                    estudanteListNewEstudante = em.merge( estudanteListNewEstudante );
                    if ( oldFkSexoOfEstudanteListNewEstudante != null && !oldFkSexoOfEstudanteListNewEstudante.equals( sexo ) )
                    {
                        oldFkSexoOfEstudanteListNewEstudante.getEstudanteList().remove( estudanteListNewEstudante );
                        oldFkSexoOfEstudanteListNewEstudante = em.merge( oldFkSexoOfEstudanteListNewEstudante );
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
                Integer id = sexo.getPkSexo();
                if ( findSexo( id ) == null )
                {
                    throw new NonexistentEntityException( "The sexo with id " + id + " no longer exists." );
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
            Sexo sexo;
            try
            {
                sexo = em.getReference( Sexo.class, id );
                sexo.getPkSexo();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The sexo with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Estudante> estudanteListOrphanCheck = sexo.getEstudanteList();
            for ( Estudante estudanteListOrphanCheckEstudante : estudanteListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Sexo (" + sexo + ") cannot be destroyed since the Estudante " + estudanteListOrphanCheckEstudante + " in its estudanteList field has a non-nullable fkSexo field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( sexo );
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

    public List<Sexo> findSexoEntities()
    {
        return findSexoEntities( true, -1, -1 );
    }

    public List<Sexo> findSexoEntities( int maxResults, int firstResult )
    {
        return findSexoEntities( false, maxResults, firstResult );
    }

    private List<Sexo> findSexoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Sexo.class ) );
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

    public Sexo findSexo( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Sexo.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getSexoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sexo> rt = cq.from( Sexo.class );
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
