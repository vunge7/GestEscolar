/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import entity.ConfirmacaoMatricula;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estudante;
import entity.PrecoConfirmacao;
import entity.TipoConfirmacao;
import entity.Turma;
import entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ConfirmacaoMatriculaJpaController implements Serializable
{

    public ConfirmacaoMatriculaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( ConfirmacaoMatricula confirmacaoMatricula )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudante fkEstudante = confirmacaoMatricula.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante = em.getReference( fkEstudante.getClass(), fkEstudante.getPkEstudante() );
                confirmacaoMatricula.setFkEstudante( fkEstudante );
            }
            PrecoConfirmacao fkPrecoConfirmacao = confirmacaoMatricula.getFkPrecoConfirmacao();
            if ( fkPrecoConfirmacao != null )
            {
                fkPrecoConfirmacao = em.getReference( fkPrecoConfirmacao.getClass(), fkPrecoConfirmacao.getPkPrecoConfirmacao() );
                confirmacaoMatricula.setFkPrecoConfirmacao( fkPrecoConfirmacao );
            }
            TipoConfirmacao fkTipoConfirmacao = confirmacaoMatricula.getFkTipoConfirmacao();
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao = em.getReference( fkTipoConfirmacao.getClass(), fkTipoConfirmacao.getPkTipoConfirmacao() );
                confirmacaoMatricula.setFkTipoConfirmacao( fkTipoConfirmacao );
            }
            Turma fkTurma = confirmacaoMatricula.getFkTurma();
            if ( fkTurma != null )
            {
                fkTurma = em.getReference( fkTurma.getClass(), fkTurma.getPkTurma() );
                confirmacaoMatricula.setFkTurma( fkTurma );
            }
            Usuario fkUsuario = confirmacaoMatricula.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario = em.getReference( fkUsuario.getClass(), fkUsuario.getPkUsuario() );
                confirmacaoMatricula.setFkUsuario( fkUsuario );
            }
            em.persist( confirmacaoMatricula );
            if ( fkEstudante != null )
            {
                fkEstudante.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkEstudante = em.merge( fkEstudante );
            }
            if ( fkPrecoConfirmacao != null )
            {
                fkPrecoConfirmacao.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkPrecoConfirmacao = em.merge( fkPrecoConfirmacao );
            }
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkTipoConfirmacao = em.merge( fkTipoConfirmacao );
            }
            if ( fkTurma != null )
            {
                fkTurma.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkTurma = em.merge( fkTurma );
            }
            if ( fkUsuario != null )
            {
                fkUsuario.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkUsuario = em.merge( fkUsuario );
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

    public void edit( ConfirmacaoMatricula confirmacaoMatricula ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfirmacaoMatricula persistentConfirmacaoMatricula = em.find( ConfirmacaoMatricula.class, confirmacaoMatricula.getPkConfirmacaoMatricula() );
            Estudante fkEstudanteOld = persistentConfirmacaoMatricula.getFkEstudante();
            Estudante fkEstudanteNew = confirmacaoMatricula.getFkEstudante();
            PrecoConfirmacao fkPrecoConfirmacaoOld = persistentConfirmacaoMatricula.getFkPrecoConfirmacao();
            PrecoConfirmacao fkPrecoConfirmacaoNew = confirmacaoMatricula.getFkPrecoConfirmacao();
            TipoConfirmacao fkTipoConfirmacaoOld = persistentConfirmacaoMatricula.getFkTipoConfirmacao();
            TipoConfirmacao fkTipoConfirmacaoNew = confirmacaoMatricula.getFkTipoConfirmacao();
            Turma fkTurmaOld = persistentConfirmacaoMatricula.getFkTurma();
            Turma fkTurmaNew = confirmacaoMatricula.getFkTurma();
            Usuario fkUsuarioOld = persistentConfirmacaoMatricula.getFkUsuario();
            Usuario fkUsuarioNew = confirmacaoMatricula.getFkUsuario();
            if ( fkEstudanteNew != null )
            {
                fkEstudanteNew = em.getReference( fkEstudanteNew.getClass(), fkEstudanteNew.getPkEstudante() );
                confirmacaoMatricula.setFkEstudante( fkEstudanteNew );
            }
            if ( fkPrecoConfirmacaoNew != null )
            {
                fkPrecoConfirmacaoNew = em.getReference( fkPrecoConfirmacaoNew.getClass(), fkPrecoConfirmacaoNew.getPkPrecoConfirmacao() );
                confirmacaoMatricula.setFkPrecoConfirmacao( fkPrecoConfirmacaoNew );
            }
            if ( fkTipoConfirmacaoNew != null )
            {
                fkTipoConfirmacaoNew = em.getReference( fkTipoConfirmacaoNew.getClass(), fkTipoConfirmacaoNew.getPkTipoConfirmacao() );
                confirmacaoMatricula.setFkTipoConfirmacao( fkTipoConfirmacaoNew );
            }
            if ( fkTurmaNew != null )
            {
                fkTurmaNew = em.getReference( fkTurmaNew.getClass(), fkTurmaNew.getPkTurma() );
                confirmacaoMatricula.setFkTurma( fkTurmaNew );
            }
            if ( fkUsuarioNew != null )
            {
                fkUsuarioNew = em.getReference( fkUsuarioNew.getClass(), fkUsuarioNew.getPkUsuario() );
                confirmacaoMatricula.setFkUsuario( fkUsuarioNew );
            }
            confirmacaoMatricula = em.merge( confirmacaoMatricula );
            if ( fkEstudanteOld != null && !fkEstudanteOld.equals( fkEstudanteNew ) )
            {
                fkEstudanteOld.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkEstudanteOld = em.merge( fkEstudanteOld );
            }
            if ( fkEstudanteNew != null && !fkEstudanteNew.equals( fkEstudanteOld ) )
            {
                fkEstudanteNew.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkEstudanteNew = em.merge( fkEstudanteNew );
            }
            if ( fkPrecoConfirmacaoOld != null && !fkPrecoConfirmacaoOld.equals( fkPrecoConfirmacaoNew ) )
            {
                fkPrecoConfirmacaoOld.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkPrecoConfirmacaoOld = em.merge( fkPrecoConfirmacaoOld );
            }
            if ( fkPrecoConfirmacaoNew != null && !fkPrecoConfirmacaoNew.equals( fkPrecoConfirmacaoOld ) )
            {
                fkPrecoConfirmacaoNew.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkPrecoConfirmacaoNew = em.merge( fkPrecoConfirmacaoNew );
            }
            if ( fkTipoConfirmacaoOld != null && !fkTipoConfirmacaoOld.equals( fkTipoConfirmacaoNew ) )
            {
                fkTipoConfirmacaoOld.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkTipoConfirmacaoOld = em.merge( fkTipoConfirmacaoOld );
            }
            if ( fkTipoConfirmacaoNew != null && !fkTipoConfirmacaoNew.equals( fkTipoConfirmacaoOld ) )
            {
                fkTipoConfirmacaoNew.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkTipoConfirmacaoNew = em.merge( fkTipoConfirmacaoNew );
            }
            if ( fkTurmaOld != null && !fkTurmaOld.equals( fkTurmaNew ) )
            {
                fkTurmaOld.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkTurmaOld = em.merge( fkTurmaOld );
            }
            if ( fkTurmaNew != null && !fkTurmaNew.equals( fkTurmaOld ) )
            {
                fkTurmaNew.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkTurmaNew = em.merge( fkTurmaNew );
            }
            if ( fkUsuarioOld != null && !fkUsuarioOld.equals( fkUsuarioNew ) )
            {
                fkUsuarioOld.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkUsuarioOld = em.merge( fkUsuarioOld );
            }
            if ( fkUsuarioNew != null && !fkUsuarioNew.equals( fkUsuarioOld ) )
            {
                fkUsuarioNew.getConfirmacaoMatriculaList().add( confirmacaoMatricula );
                fkUsuarioNew = em.merge( fkUsuarioNew );
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if ( msg == null || msg.length() == 0 )
            {
                Integer id = confirmacaoMatricula.getPkConfirmacaoMatricula();
                if ( findConfirmacaoMatricula( id ) == null )
                {
                    throw new NonexistentEntityException( "The confirmacaoMatricula with id " + id + " no longer exists." );
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

    public void destroy( Integer id ) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfirmacaoMatricula confirmacaoMatricula;
            try
            {
                confirmacaoMatricula = em.getReference( ConfirmacaoMatricula.class, id );
                confirmacaoMatricula.getPkConfirmacaoMatricula();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The confirmacaoMatricula with id " + id + " no longer exists.", enfe );
            }
            Estudante fkEstudante = confirmacaoMatricula.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkEstudante = em.merge( fkEstudante );
            }
            PrecoConfirmacao fkPrecoConfirmacao = confirmacaoMatricula.getFkPrecoConfirmacao();
            if ( fkPrecoConfirmacao != null )
            {
                fkPrecoConfirmacao.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkPrecoConfirmacao = em.merge( fkPrecoConfirmacao );
            }
            TipoConfirmacao fkTipoConfirmacao = confirmacaoMatricula.getFkTipoConfirmacao();
            if ( fkTipoConfirmacao != null )
            {
                fkTipoConfirmacao.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkTipoConfirmacao = em.merge( fkTipoConfirmacao );
            }
            Turma fkTurma = confirmacaoMatricula.getFkTurma();
            if ( fkTurma != null )
            {
                fkTurma.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkTurma = em.merge( fkTurma );
            }
            Usuario fkUsuario = confirmacaoMatricula.getFkUsuario();
            if ( fkUsuario != null )
            {
                fkUsuario.getConfirmacaoMatriculaList().remove( confirmacaoMatricula );
                fkUsuario = em.merge( fkUsuario );
            }
            em.remove( confirmacaoMatricula );
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

    public List<ConfirmacaoMatricula> findConfirmacaoMatriculaEntities()
    {
        return findConfirmacaoMatriculaEntities( true, -1, -1 );
    }

    public List<ConfirmacaoMatricula> findConfirmacaoMatriculaEntities( int maxResults, int firstResult )
    {
        return findConfirmacaoMatriculaEntities( false, maxResults, firstResult );
    }

    private List<ConfirmacaoMatricula> findConfirmacaoMatriculaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( ConfirmacaoMatricula.class ) );
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

    public ConfirmacaoMatricula findConfirmacaoMatricula( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( ConfirmacaoMatricula.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getConfirmacaoMatriculaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfirmacaoMatricula> rt = cq.from( ConfirmacaoMatricula.class );
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
