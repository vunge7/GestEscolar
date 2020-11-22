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
import entity.Encarregado;
import entity.Profissao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ProfissaoJpaController implements Serializable
{

    public ProfissaoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Profissao profissao )
    {
        if ( profissao.getEncarregadoList() == null )
        {
            profissao.setEncarregadoList( new ArrayList<Encarregado>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encarregado> attachedEncarregadoList = new ArrayList<Encarregado>();
            for ( Encarregado encarregadoListEncarregadoToAttach : profissao.getEncarregadoList() )
            {
                encarregadoListEncarregadoToAttach = em.getReference( encarregadoListEncarregadoToAttach.getClass(), encarregadoListEncarregadoToAttach.getPkEncarregado() );
                attachedEncarregadoList.add( encarregadoListEncarregadoToAttach );
            }
            profissao.setEncarregadoList( attachedEncarregadoList );
            em.persist( profissao );
            for ( Encarregado encarregadoListEncarregado : profissao.getEncarregadoList() )
            {
                Profissao oldFkProfissaoOfEncarregadoListEncarregado = encarregadoListEncarregado.getFkProfissao();
                encarregadoListEncarregado.setFkProfissao( profissao );
                encarregadoListEncarregado = em.merge( encarregadoListEncarregado );
                if ( oldFkProfissaoOfEncarregadoListEncarregado != null )
                {
                    oldFkProfissaoOfEncarregadoListEncarregado.getEncarregadoList().remove( encarregadoListEncarregado );
                    oldFkProfissaoOfEncarregadoListEncarregado = em.merge( oldFkProfissaoOfEncarregadoListEncarregado );
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

    public void edit( Profissao profissao ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissao persistentProfissao = em.find( Profissao.class, profissao.getPkProfissao() );
            List<Encarregado> encarregadoListOld = persistentProfissao.getEncarregadoList();
            List<Encarregado> encarregadoListNew = profissao.getEncarregadoList();
            List<String> illegalOrphanMessages = null;
            for ( Encarregado encarregadoListOldEncarregado : encarregadoListOld )
            {
                if ( !encarregadoListNew.contains( encarregadoListOldEncarregado ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Encarregado " + encarregadoListOldEncarregado + " since its fkProfissao field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Encarregado> attachedEncarregadoListNew = new ArrayList<Encarregado>();
            for ( Encarregado encarregadoListNewEncarregadoToAttach : encarregadoListNew )
            {
                encarregadoListNewEncarregadoToAttach = em.getReference( encarregadoListNewEncarregadoToAttach.getClass(), encarregadoListNewEncarregadoToAttach.getPkEncarregado() );
                attachedEncarregadoListNew.add( encarregadoListNewEncarregadoToAttach );
            }
            encarregadoListNew = attachedEncarregadoListNew;
            profissao.setEncarregadoList( encarregadoListNew );
            profissao = em.merge( profissao );
            for ( Encarregado encarregadoListNewEncarregado : encarregadoListNew )
            {
                if ( !encarregadoListOld.contains( encarregadoListNewEncarregado ) )
                {
                    Profissao oldFkProfissaoOfEncarregadoListNewEncarregado = encarregadoListNewEncarregado.getFkProfissao();
                    encarregadoListNewEncarregado.setFkProfissao( profissao );
                    encarregadoListNewEncarregado = em.merge( encarregadoListNewEncarregado );
                    if ( oldFkProfissaoOfEncarregadoListNewEncarregado != null && !oldFkProfissaoOfEncarregadoListNewEncarregado.equals( profissao ) )
                    {
                        oldFkProfissaoOfEncarregadoListNewEncarregado.getEncarregadoList().remove( encarregadoListNewEncarregado );
                        oldFkProfissaoOfEncarregadoListNewEncarregado = em.merge( oldFkProfissaoOfEncarregadoListNewEncarregado );
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
                Integer id = profissao.getPkProfissao();
                if ( findProfissao( id ) == null )
                {
                    throw new NonexistentEntityException( "The profissao with id " + id + " no longer exists." );
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
            Profissao profissao;
            try
            {
                profissao = em.getReference( Profissao.class, id );
                profissao.getPkProfissao();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The profissao with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Encarregado> encarregadoListOrphanCheck = profissao.getEncarregadoList();
            for ( Encarregado encarregadoListOrphanCheckEncarregado : encarregadoListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Profissao (" + profissao + ") cannot be destroyed since the Encarregado " + encarregadoListOrphanCheckEncarregado + " in its encarregadoList field has a non-nullable fkProfissao field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( profissao );
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

    public List<Profissao> findProfissaoEntities()
    {
        return findProfissaoEntities( true, -1, -1 );
    }

    public List<Profissao> findProfissaoEntities( int maxResults, int firstResult )
    {
        return findProfissaoEntities( false, maxResults, firstResult );
    }

    private List<Profissao> findProfissaoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Profissao.class ) );
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

    public Profissao findProfissao( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Profissao.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getProfissaoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profissao> rt = cq.from( Profissao.class );
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
