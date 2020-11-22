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
import entity.PrecoConfirmacao;
import java.util.ArrayList;
import java.util.List;
import entity.ConfirmacaoMatricula;
import entity.TipoConfirmacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class TipoConfirmacaoJpaController implements Serializable
{

    public TipoConfirmacaoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( TipoConfirmacao tipoConfirmacao )
    {
        if ( tipoConfirmacao.getPrecoConfirmacaoList() == null )
        {
            tipoConfirmacao.setPrecoConfirmacaoList( new ArrayList<PrecoConfirmacao>() );
        }
        if ( tipoConfirmacao.getConfirmacaoMatriculaList() == null )
        {
            tipoConfirmacao.setConfirmacaoMatriculaList( new ArrayList<ConfirmacaoMatricula>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrecoConfirmacao> attachedPrecoConfirmacaoList = new ArrayList<PrecoConfirmacao>();
            for ( PrecoConfirmacao precoConfirmacaoListPrecoConfirmacaoToAttach : tipoConfirmacao.getPrecoConfirmacaoList() )
            {
                precoConfirmacaoListPrecoConfirmacaoToAttach = em.getReference( precoConfirmacaoListPrecoConfirmacaoToAttach.getClass(), precoConfirmacaoListPrecoConfirmacaoToAttach.getPkPrecoConfirmacao() );
                attachedPrecoConfirmacaoList.add( precoConfirmacaoListPrecoConfirmacaoToAttach );
            }
            tipoConfirmacao.setPrecoConfirmacaoList( attachedPrecoConfirmacaoList );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaList = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatriculaToAttach : tipoConfirmacao.getConfirmacaoMatriculaList() )
            {
                confirmacaoMatriculaListConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaList.add( confirmacaoMatriculaListConfirmacaoMatriculaToAttach );
            }
            tipoConfirmacao.setConfirmacaoMatriculaList( attachedConfirmacaoMatriculaList );
            em.persist( tipoConfirmacao );
            for ( PrecoConfirmacao precoConfirmacaoListPrecoConfirmacao : tipoConfirmacao.getPrecoConfirmacaoList() )
            {
                TipoConfirmacao oldFkTipoConfirmacaoOfPrecoConfirmacaoListPrecoConfirmacao = precoConfirmacaoListPrecoConfirmacao.getFkTipoConfirmacao();
                precoConfirmacaoListPrecoConfirmacao.setFkTipoConfirmacao( tipoConfirmacao );
                precoConfirmacaoListPrecoConfirmacao = em.merge( precoConfirmacaoListPrecoConfirmacao );
                if ( oldFkTipoConfirmacaoOfPrecoConfirmacaoListPrecoConfirmacao != null )
                {
                    oldFkTipoConfirmacaoOfPrecoConfirmacaoListPrecoConfirmacao.getPrecoConfirmacaoList().remove( precoConfirmacaoListPrecoConfirmacao );
                    oldFkTipoConfirmacaoOfPrecoConfirmacaoListPrecoConfirmacao = em.merge( oldFkTipoConfirmacaoOfPrecoConfirmacaoListPrecoConfirmacao );
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatricula : tipoConfirmacao.getConfirmacaoMatriculaList() )
            {
                TipoConfirmacao oldFkTipoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula = confirmacaoMatriculaListConfirmacaoMatricula.getFkTipoConfirmacao();
                confirmacaoMatriculaListConfirmacaoMatricula.setFkTipoConfirmacao( tipoConfirmacao );
                confirmacaoMatriculaListConfirmacaoMatricula = em.merge( confirmacaoMatriculaListConfirmacaoMatricula );
                if ( oldFkTipoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula != null )
                {
                    oldFkTipoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListConfirmacaoMatricula );
                    oldFkTipoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula = em.merge( oldFkTipoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula );
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

    public void edit( TipoConfirmacao tipoConfirmacao ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoConfirmacao persistentTipoConfirmacao = em.find( TipoConfirmacao.class, tipoConfirmacao.getPkTipoConfirmacao() );
            List<PrecoConfirmacao> precoConfirmacaoListOld = persistentTipoConfirmacao.getPrecoConfirmacaoList();
            List<PrecoConfirmacao> precoConfirmacaoListNew = tipoConfirmacao.getPrecoConfirmacaoList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOld = persistentTipoConfirmacao.getConfirmacaoMatriculaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListNew = tipoConfirmacao.getConfirmacaoMatriculaList();
            List<String> illegalOrphanMessages = null;
            for ( PrecoConfirmacao precoConfirmacaoListOldPrecoConfirmacao : precoConfirmacaoListOld )
            {
                if ( !precoConfirmacaoListNew.contains( precoConfirmacaoListOldPrecoConfirmacao ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain PrecoConfirmacao " + precoConfirmacaoListOldPrecoConfirmacao + " since its fkTipoConfirmacao field is not nullable." );
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOldConfirmacaoMatricula : confirmacaoMatriculaListOld )
            {
                if ( !confirmacaoMatriculaListNew.contains( confirmacaoMatriculaListOldConfirmacaoMatricula ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ConfirmacaoMatricula " + confirmacaoMatriculaListOldConfirmacaoMatricula + " since its fkTipoConfirmacao field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<PrecoConfirmacao> attachedPrecoConfirmacaoListNew = new ArrayList<PrecoConfirmacao>();
            for ( PrecoConfirmacao precoConfirmacaoListNewPrecoConfirmacaoToAttach : precoConfirmacaoListNew )
            {
                precoConfirmacaoListNewPrecoConfirmacaoToAttach = em.getReference( precoConfirmacaoListNewPrecoConfirmacaoToAttach.getClass(), precoConfirmacaoListNewPrecoConfirmacaoToAttach.getPkPrecoConfirmacao() );
                attachedPrecoConfirmacaoListNew.add( precoConfirmacaoListNewPrecoConfirmacaoToAttach );
            }
            precoConfirmacaoListNew = attachedPrecoConfirmacaoListNew;
            tipoConfirmacao.setPrecoConfirmacaoList( precoConfirmacaoListNew );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaListNew = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach : confirmacaoMatriculaListNew )
            {
                confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaListNew.add( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach );
            }
            confirmacaoMatriculaListNew = attachedConfirmacaoMatriculaListNew;
            tipoConfirmacao.setConfirmacaoMatriculaList( confirmacaoMatriculaListNew );
            tipoConfirmacao = em.merge( tipoConfirmacao );
            for ( PrecoConfirmacao precoConfirmacaoListNewPrecoConfirmacao : precoConfirmacaoListNew )
            {
                if ( !precoConfirmacaoListOld.contains( precoConfirmacaoListNewPrecoConfirmacao ) )
                {
                    TipoConfirmacao oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao = precoConfirmacaoListNewPrecoConfirmacao.getFkTipoConfirmacao();
                    precoConfirmacaoListNewPrecoConfirmacao.setFkTipoConfirmacao( tipoConfirmacao );
                    precoConfirmacaoListNewPrecoConfirmacao = em.merge( precoConfirmacaoListNewPrecoConfirmacao );
                    if ( oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao != null && !oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao.equals( tipoConfirmacao ) )
                    {
                        oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao.getPrecoConfirmacaoList().remove( precoConfirmacaoListNewPrecoConfirmacao );
                        oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao = em.merge( oldFkTipoConfirmacaoOfPrecoConfirmacaoListNewPrecoConfirmacao );
                    }
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatricula : confirmacaoMatriculaListNew )
            {
                if ( !confirmacaoMatriculaListOld.contains( confirmacaoMatriculaListNewConfirmacaoMatricula ) )
                {
                    TipoConfirmacao oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula = confirmacaoMatriculaListNewConfirmacaoMatricula.getFkTipoConfirmacao();
                    confirmacaoMatriculaListNewConfirmacaoMatricula.setFkTipoConfirmacao( tipoConfirmacao );
                    confirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( confirmacaoMatriculaListNewConfirmacaoMatricula );
                    if ( oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula != null && !oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula.equals( tipoConfirmacao ) )
                    {
                        oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListNewConfirmacaoMatricula );
                        oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( oldFkTipoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula );
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
                Integer id = tipoConfirmacao.getPkTipoConfirmacao();
                if ( findTipoConfirmacao( id ) == null )
                {
                    throw new NonexistentEntityException( "The tipoConfirmacao with id " + id + " no longer exists." );
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
            TipoConfirmacao tipoConfirmacao;
            try
            {
                tipoConfirmacao = em.getReference( TipoConfirmacao.class, id );
                tipoConfirmacao.getPkTipoConfirmacao();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The tipoConfirmacao with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<PrecoConfirmacao> precoConfirmacaoListOrphanCheck = tipoConfirmacao.getPrecoConfirmacaoList();
            for ( PrecoConfirmacao precoConfirmacaoListOrphanCheckPrecoConfirmacao : precoConfirmacaoListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This TipoConfirmacao (" + tipoConfirmacao + ") cannot be destroyed since the PrecoConfirmacao " + precoConfirmacaoListOrphanCheckPrecoConfirmacao + " in its precoConfirmacaoList field has a non-nullable fkTipoConfirmacao field." );
            }
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOrphanCheck = tipoConfirmacao.getConfirmacaoMatriculaList();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula : confirmacaoMatriculaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This TipoConfirmacao (" + tipoConfirmacao + ") cannot be destroyed since the ConfirmacaoMatricula " + confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula + " in its confirmacaoMatriculaList field has a non-nullable fkTipoConfirmacao field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( tipoConfirmacao );
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

    public List<TipoConfirmacao> findTipoConfirmacaoEntities()
    {
        return findTipoConfirmacaoEntities( true, -1, -1 );
    }

    public List<TipoConfirmacao> findTipoConfirmacaoEntities( int maxResults, int firstResult )
    {
        return findTipoConfirmacaoEntities( false, maxResults, firstResult );
    }

    private List<TipoConfirmacao> findTipoConfirmacaoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( TipoConfirmacao.class ) );
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

    public TipoConfirmacao findTipoConfirmacao( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( TipoConfirmacao.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getTipoConfirmacaoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoConfirmacao> rt = cq.from( TipoConfirmacao.class );
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
