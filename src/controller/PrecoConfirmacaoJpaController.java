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
import entity.Curso;
import entity.TipoConfirmacao;
import entity.ConfirmacaoMatricula;
import entity.PrecoConfirmacao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class PrecoConfirmacaoJpaController implements Serializable
{

    public PrecoConfirmacaoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( PrecoConfirmacao precoConfirmacao )
    {
        if ( precoConfirmacao.getConfirmacaoMatriculaList() == null )
        {
            precoConfirmacao.setConfirmacaoMatriculaList( new ArrayList<ConfirmacaoMatricula>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso fkCurso = precoConfirmacao.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso = em.getReference( fkCurso.getClass(), fkCurso.getPkCurso() );
                precoConfirmacao.setFkCurso( fkCurso );
            }
            TipoConfirmacao fkTipoConfirmacao = precoConfirmacao.getFkTipoConfirmacao();
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao = em.getReference( fkTipoConfirmacao.getClass(), fkTipoConfirmacao.getPkTipoConfirmacao() );
                precoConfirmacao.setFkTipoConfirmacao( fkTipoConfirmacao );
            }
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaList = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatriculaToAttach : precoConfirmacao.getConfirmacaoMatriculaList() )
            {
                confirmacaoMatriculaListConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaList.add( confirmacaoMatriculaListConfirmacaoMatriculaToAttach );
            }
            precoConfirmacao.setConfirmacaoMatriculaList( attachedConfirmacaoMatriculaList );
            em.persist( precoConfirmacao );
            if ( fkCurso != null )
            {
                fkCurso.getPrecoConfirmacaoList().add( precoConfirmacao );
                fkCurso = em.merge( fkCurso );
            }
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao.getPrecoConfirmacaoList().add( precoConfirmacao );
                fkTipoConfirmacao = em.merge( fkTipoConfirmacao );
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatricula : precoConfirmacao.getConfirmacaoMatriculaList() )
            {
                PrecoConfirmacao oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula = confirmacaoMatriculaListConfirmacaoMatricula.getFkPrecoConfirmacao();
                confirmacaoMatriculaListConfirmacaoMatricula.setFkPrecoConfirmacao( precoConfirmacao );
                confirmacaoMatriculaListConfirmacaoMatricula = em.merge( confirmacaoMatriculaListConfirmacaoMatricula );
                if ( oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula != null )
                {
                    oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListConfirmacaoMatricula );
                    oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula = em.merge( oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListConfirmacaoMatricula );
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

    public void edit( PrecoConfirmacao precoConfirmacao ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            PrecoConfirmacao persistentPrecoConfirmacao = em.find( PrecoConfirmacao.class, precoConfirmacao.getPkPrecoConfirmacao() );
            Curso fkCursoOld = persistentPrecoConfirmacao.getFkCurso();
            Curso fkCursoNew = precoConfirmacao.getFkCurso();
            TipoConfirmacao fkTipoConfirmacaoOld = persistentPrecoConfirmacao.getFkTipoConfirmacao();
            TipoConfirmacao fkTipoConfirmacaoNew = precoConfirmacao.getFkTipoConfirmacao();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOld = persistentPrecoConfirmacao.getConfirmacaoMatriculaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListNew = precoConfirmacao.getConfirmacaoMatriculaList();
            List<String> illegalOrphanMessages = null;
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOldConfirmacaoMatricula : confirmacaoMatriculaListOld )
            {
                if ( !confirmacaoMatriculaListNew.contains( confirmacaoMatriculaListOldConfirmacaoMatricula ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ConfirmacaoMatricula " + confirmacaoMatriculaListOldConfirmacaoMatricula + " since its fkPrecoConfirmacao field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkCursoNew != null )
            {
                fkCursoNew = em.getReference( fkCursoNew.getClass(), fkCursoNew.getPkCurso() );
                precoConfirmacao.setFkCurso( fkCursoNew );
            }
            if ( fkTipoConfirmacaoNew != null )
            {
                fkTipoConfirmacaoNew = em.getReference( fkTipoConfirmacaoNew.getClass(), fkTipoConfirmacaoNew.getPkTipoConfirmacao() );
                precoConfirmacao.setFkTipoConfirmacao( fkTipoConfirmacaoNew );
            }
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaListNew = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach : confirmacaoMatriculaListNew )
            {
                confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaListNew.add( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach );
            }
            confirmacaoMatriculaListNew = attachedConfirmacaoMatriculaListNew;
            precoConfirmacao.setConfirmacaoMatriculaList( confirmacaoMatriculaListNew );
            precoConfirmacao = em.merge( precoConfirmacao );
            if ( fkCursoOld != null && !fkCursoOld.equals( fkCursoNew ) )
            {
                fkCursoOld.getPrecoConfirmacaoList().remove( precoConfirmacao );
                fkCursoOld = em.merge( fkCursoOld );
            }
            if ( fkCursoNew != null && !fkCursoNew.equals( fkCursoOld ) )
            {
                fkCursoNew.getPrecoConfirmacaoList().add( precoConfirmacao );
                fkCursoNew = em.merge( fkCursoNew );
            }
            if ( fkTipoConfirmacaoOld != null && !fkTipoConfirmacaoOld.equals( fkTipoConfirmacaoNew ) )
            {
                fkTipoConfirmacaoOld.getPrecoConfirmacaoList().remove( precoConfirmacao );
                fkTipoConfirmacaoOld = em.merge( fkTipoConfirmacaoOld );
            }
            if ( fkTipoConfirmacaoNew != null && !fkTipoConfirmacaoNew.equals( fkTipoConfirmacaoOld ) )
            {
                fkTipoConfirmacaoNew.getPrecoConfirmacaoList().add( precoConfirmacao );
                fkTipoConfirmacaoNew = em.merge( fkTipoConfirmacaoNew );
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatricula : confirmacaoMatriculaListNew )
            {
                if ( !confirmacaoMatriculaListOld.contains( confirmacaoMatriculaListNewConfirmacaoMatricula ) )
                {
                    PrecoConfirmacao oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula = confirmacaoMatriculaListNewConfirmacaoMatricula.getFkPrecoConfirmacao();
                    confirmacaoMatriculaListNewConfirmacaoMatricula.setFkPrecoConfirmacao( precoConfirmacao );
                    confirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( confirmacaoMatriculaListNewConfirmacaoMatricula );
                    if ( oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula != null && !oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula.equals( precoConfirmacao ) )
                    {
                        oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListNewConfirmacaoMatricula );
                        oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( oldFkPrecoConfirmacaoOfConfirmacaoMatriculaListNewConfirmacaoMatricula );
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
                Integer id = precoConfirmacao.getPkPrecoConfirmacao();
                if ( findPrecoConfirmacao( id ) == null )
                {
                    throw new NonexistentEntityException( "The precoConfirmacao with id " + id + " no longer exists." );
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
            PrecoConfirmacao precoConfirmacao;
            try
            {
                precoConfirmacao = em.getReference( PrecoConfirmacao.class, id );
                precoConfirmacao.getPkPrecoConfirmacao();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The precoConfirmacao with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOrphanCheck = precoConfirmacao.getConfirmacaoMatriculaList();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula : confirmacaoMatriculaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This PrecoConfirmacao (" + precoConfirmacao + ") cannot be destroyed since the ConfirmacaoMatricula " + confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula + " in its confirmacaoMatriculaList field has a non-nullable fkPrecoConfirmacao field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Curso fkCurso = precoConfirmacao.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso.getPrecoConfirmacaoList().remove( precoConfirmacao );
                fkCurso = em.merge( fkCurso );
            }
            TipoConfirmacao fkTipoConfirmacao = precoConfirmacao.getFkTipoConfirmacao();
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao.getPrecoConfirmacaoList().remove( precoConfirmacao );
                fkTipoConfirmacao = em.merge( fkTipoConfirmacao );
            }
            em.remove( precoConfirmacao );
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

    public List<PrecoConfirmacao> findPrecoConfirmacaoEntities()
    {
        return findPrecoConfirmacaoEntities( true, -1, -1 );
    }

    public List<PrecoConfirmacao> findPrecoConfirmacaoEntities( int maxResults, int firstResult )
    {
        return findPrecoConfirmacaoEntities( false, maxResults, firstResult );
    }

    private List<PrecoConfirmacao> findPrecoConfirmacaoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( PrecoConfirmacao.class ) );
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

    public PrecoConfirmacao findPrecoConfirmacao( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( PrecoConfirmacao.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getPrecoConfirmacaoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecoConfirmacao> rt = cq.from( PrecoConfirmacao.class );
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
