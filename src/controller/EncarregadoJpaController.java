/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Encarregado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Profissao;
import entity.Estudante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class EncarregadoJpaController implements Serializable
{

    public EncarregadoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Encarregado encarregado )
    {
        if ( encarregado.getEstudanteList() == null )
        {
            encarregado.setEstudanteList( new ArrayList<Estudante>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissao fkProfissao = encarregado.getFkProfissao();
            if ( fkProfissao != null )
            {
                fkProfissao = em.getReference( fkProfissao.getClass(), fkProfissao.getPkProfissao() );
                encarregado.setFkProfissao( fkProfissao );
            }
            List<Estudante> attachedEstudanteList = new ArrayList<Estudante>();
            for ( Estudante estudanteListEstudanteToAttach : encarregado.getEstudanteList() )
            {
                estudanteListEstudanteToAttach = em.getReference( estudanteListEstudanteToAttach.getClass(), estudanteListEstudanteToAttach.getPkEstudante() );
                attachedEstudanteList.add( estudanteListEstudanteToAttach );
            }
            encarregado.setEstudanteList( attachedEstudanteList );
            em.persist( encarregado );
            if ( fkProfissao != null )
            {
                fkProfissao.getEncarregadoList().add( encarregado );
                fkProfissao = em.merge( fkProfissao );
            }
            for ( Estudante estudanteListEstudante : encarregado.getEstudanteList() )
            {
                Encarregado oldFkEncarregadoOfEstudanteListEstudante = estudanteListEstudante.getFkEncarregado();
                estudanteListEstudante.setFkEncarregado( encarregado );
                estudanteListEstudante = em.merge( estudanteListEstudante );
                if ( oldFkEncarregadoOfEstudanteListEstudante != null )
                {
                    oldFkEncarregadoOfEstudanteListEstudante.getEstudanteList().remove( estudanteListEstudante );
                    oldFkEncarregadoOfEstudanteListEstudante = em.merge( oldFkEncarregadoOfEstudanteListEstudante );
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

    public void edit( Encarregado encarregado ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Encarregado persistentEncarregado = em.find( Encarregado.class, encarregado.getPkEncarregado() );
            Profissao fkProfissaoOld = persistentEncarregado.getFkProfissao();
            Profissao fkProfissaoNew = encarregado.getFkProfissao();
            List<Estudante> estudanteListOld = persistentEncarregado.getEstudanteList();
            List<Estudante> estudanteListNew = encarregado.getEstudanteList();
            List<String> illegalOrphanMessages = null;
            for ( Estudante estudanteListOldEstudante : estudanteListOld )
            {
                if ( !estudanteListNew.contains( estudanteListOldEstudante ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Estudante " + estudanteListOldEstudante + " since its fkEncarregado field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkProfissaoNew != null )
            {
                fkProfissaoNew = em.getReference( fkProfissaoNew.getClass(), fkProfissaoNew.getPkProfissao() );
                encarregado.setFkProfissao( fkProfissaoNew );
            }
            List<Estudante> attachedEstudanteListNew = new ArrayList<Estudante>();
            for ( Estudante estudanteListNewEstudanteToAttach : estudanteListNew )
            {
                estudanteListNewEstudanteToAttach = em.getReference( estudanteListNewEstudanteToAttach.getClass(), estudanteListNewEstudanteToAttach.getPkEstudante() );
                attachedEstudanteListNew.add( estudanteListNewEstudanteToAttach );
            }
            estudanteListNew = attachedEstudanteListNew;
            encarregado.setEstudanteList( estudanteListNew );
            encarregado = em.merge( encarregado );
            if ( fkProfissaoOld != null && !fkProfissaoOld.equals( fkProfissaoNew ) )
            {
                fkProfissaoOld.getEncarregadoList().remove( encarregado );
                fkProfissaoOld = em.merge( fkProfissaoOld );
            }
            if ( fkProfissaoNew != null && !fkProfissaoNew.equals( fkProfissaoOld ) )
            {
                fkProfissaoNew.getEncarregadoList().add( encarregado );
                fkProfissaoNew = em.merge( fkProfissaoNew );
            }
            for ( Estudante estudanteListNewEstudante : estudanteListNew )
            {
                if ( !estudanteListOld.contains( estudanteListNewEstudante ) )
                {
                    Encarregado oldFkEncarregadoOfEstudanteListNewEstudante = estudanteListNewEstudante.getFkEncarregado();
                    estudanteListNewEstudante.setFkEncarregado( encarregado );
                    estudanteListNewEstudante = em.merge( estudanteListNewEstudante );
                    if ( oldFkEncarregadoOfEstudanteListNewEstudante != null && !oldFkEncarregadoOfEstudanteListNewEstudante.equals( encarregado ) )
                    {
                        oldFkEncarregadoOfEstudanteListNewEstudante.getEstudanteList().remove( estudanteListNewEstudante );
                        oldFkEncarregadoOfEstudanteListNewEstudante = em.merge( oldFkEncarregadoOfEstudanteListNewEstudante );
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
                Integer id = encarregado.getPkEncarregado();
                if ( findEncarregado( id ) == null )
                {
                    throw new NonexistentEntityException( "The encarregado with id " + id + " no longer exists." );
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
            Encarregado encarregado;
            try
            {
                encarregado = em.getReference( Encarregado.class, id );
                encarregado.getPkEncarregado();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The encarregado with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Estudante> estudanteListOrphanCheck = encarregado.getEstudanteList();
            for ( Estudante estudanteListOrphanCheckEstudante : estudanteListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Encarregado (" + encarregado + ") cannot be destroyed since the Estudante " + estudanteListOrphanCheckEstudante + " in its estudanteList field has a non-nullable fkEncarregado field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Profissao fkProfissao = encarregado.getFkProfissao();
            if ( fkProfissao != null )
            {
                fkProfissao.getEncarregadoList().remove( encarregado );
                fkProfissao = em.merge( fkProfissao );
            }
            em.remove( encarregado );
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

    public List<Encarregado> findEncarregadoEntities()
    {
        return findEncarregadoEntities( true, -1, -1 );
    }

    public List<Encarregado> findEncarregadoEntities( int maxResults, int firstResult )
    {
        return findEncarregadoEntities( false, maxResults, firstResult );
    }

    private List<Encarregado> findEncarregadoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Encarregado.class ) );
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

    public Encarregado findEncarregado( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Encarregado.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getEncarregadoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encarregado> rt = cq.from( Encarregado.class );
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
