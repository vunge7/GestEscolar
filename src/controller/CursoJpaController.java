/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Curso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.PrecoConfirmacao;
import java.util.ArrayList;
import java.util.List;
import entity.PrecoPropina;
import entity.Turma;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class CursoJpaController implements Serializable
{

    public CursoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Curso curso )
    {
        if ( curso.getPrecoConfirmacaoList() == null )
        {
            curso.setPrecoConfirmacaoList( new ArrayList<PrecoConfirmacao>() );
        }
        if ( curso.getPrecoPropinaList() == null )
        {
            curso.setPrecoPropinaList( new ArrayList<PrecoPropina>() );
        }
        if ( curso.getTurmaList() == null )
        {
            curso.setTurmaList( new ArrayList<Turma>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrecoConfirmacao> attachedPrecoConfirmacaoList = new ArrayList<PrecoConfirmacao>();
            for ( PrecoConfirmacao precoConfirmacaoListPrecoConfirmacaoToAttach : curso.getPrecoConfirmacaoList() )
            {
                precoConfirmacaoListPrecoConfirmacaoToAttach = em.getReference( precoConfirmacaoListPrecoConfirmacaoToAttach.getClass(), precoConfirmacaoListPrecoConfirmacaoToAttach.getPkPrecoConfirmacao() );
                attachedPrecoConfirmacaoList.add( precoConfirmacaoListPrecoConfirmacaoToAttach );
            }
            curso.setPrecoConfirmacaoList( attachedPrecoConfirmacaoList );
            List<PrecoPropina> attachedPrecoPropinaList = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListPrecoPropinaToAttach : curso.getPrecoPropinaList() )
            {
                precoPropinaListPrecoPropinaToAttach = em.getReference( precoPropinaListPrecoPropinaToAttach.getClass(), precoPropinaListPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaList.add( precoPropinaListPrecoPropinaToAttach );
            }
            curso.setPrecoPropinaList( attachedPrecoPropinaList );
            List<Turma> attachedTurmaList = new ArrayList<Turma>();
            for ( Turma turmaListTurmaToAttach : curso.getTurmaList() )
            {
                turmaListTurmaToAttach = em.getReference( turmaListTurmaToAttach.getClass(), turmaListTurmaToAttach.getPkTurma() );
                attachedTurmaList.add( turmaListTurmaToAttach );
            }
            curso.setTurmaList( attachedTurmaList );
            em.persist( curso );
            for ( PrecoConfirmacao precoConfirmacaoListPrecoConfirmacao : curso.getPrecoConfirmacaoList() )
            {
                Curso oldFkCursoOfPrecoConfirmacaoListPrecoConfirmacao = precoConfirmacaoListPrecoConfirmacao.getFkCurso();
                precoConfirmacaoListPrecoConfirmacao.setFkCurso( curso );
                precoConfirmacaoListPrecoConfirmacao = em.merge( precoConfirmacaoListPrecoConfirmacao );
                if ( oldFkCursoOfPrecoConfirmacaoListPrecoConfirmacao != null )
                {
                    oldFkCursoOfPrecoConfirmacaoListPrecoConfirmacao.getPrecoConfirmacaoList().remove( precoConfirmacaoListPrecoConfirmacao );
                    oldFkCursoOfPrecoConfirmacaoListPrecoConfirmacao = em.merge( oldFkCursoOfPrecoConfirmacaoListPrecoConfirmacao );
                }
            }
            for ( PrecoPropina precoPropinaListPrecoPropina : curso.getPrecoPropinaList() )
            {
                Curso oldFkCursoOfPrecoPropinaListPrecoPropina = precoPropinaListPrecoPropina.getFkCurso();
                precoPropinaListPrecoPropina.setFkCurso( curso );
                precoPropinaListPrecoPropina = em.merge( precoPropinaListPrecoPropina );
                if ( oldFkCursoOfPrecoPropinaListPrecoPropina != null )
                {
                    oldFkCursoOfPrecoPropinaListPrecoPropina.getPrecoPropinaList().remove( precoPropinaListPrecoPropina );
                    oldFkCursoOfPrecoPropinaListPrecoPropina = em.merge( oldFkCursoOfPrecoPropinaListPrecoPropina );
                }
            }
            for ( Turma turmaListTurma : curso.getTurmaList() )
            {
                Curso oldFkCursoOfTurmaListTurma = turmaListTurma.getFkCurso();
                turmaListTurma.setFkCurso( curso );
                turmaListTurma = em.merge( turmaListTurma );
                if ( oldFkCursoOfTurmaListTurma != null )
                {
                    oldFkCursoOfTurmaListTurma.getTurmaList().remove( turmaListTurma );
                    oldFkCursoOfTurmaListTurma = em.merge( oldFkCursoOfTurmaListTurma );
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

    public void edit( Curso curso ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find( Curso.class, curso.getPkCurso() );
            List<PrecoConfirmacao> precoConfirmacaoListOld = persistentCurso.getPrecoConfirmacaoList();
            List<PrecoConfirmacao> precoConfirmacaoListNew = curso.getPrecoConfirmacaoList();
            List<PrecoPropina> precoPropinaListOld = persistentCurso.getPrecoPropinaList();
            List<PrecoPropina> precoPropinaListNew = curso.getPrecoPropinaList();
            List<Turma> turmaListOld = persistentCurso.getTurmaList();
            List<Turma> turmaListNew = curso.getTurmaList();
            List<String> illegalOrphanMessages = null;
            for ( PrecoConfirmacao precoConfirmacaoListOldPrecoConfirmacao : precoConfirmacaoListOld )
            {
                if ( !precoConfirmacaoListNew.contains( precoConfirmacaoListOldPrecoConfirmacao ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain PrecoConfirmacao " + precoConfirmacaoListOldPrecoConfirmacao + " since its fkCurso field is not nullable." );
                }
            }
            for ( PrecoPropina precoPropinaListOldPrecoPropina : precoPropinaListOld )
            {
                if ( !precoPropinaListNew.contains( precoPropinaListOldPrecoPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain PrecoPropina " + precoPropinaListOldPrecoPropina + " since its fkCurso field is not nullable." );
                }
            }
            for ( Turma turmaListOldTurma : turmaListOld )
            {
                if ( !turmaListNew.contains( turmaListOldTurma ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Turma " + turmaListOldTurma + " since its fkCurso field is not nullable." );
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
            curso.setPrecoConfirmacaoList( precoConfirmacaoListNew );
            List<PrecoPropina> attachedPrecoPropinaListNew = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListNewPrecoPropinaToAttach : precoPropinaListNew )
            {
                precoPropinaListNewPrecoPropinaToAttach = em.getReference( precoPropinaListNewPrecoPropinaToAttach.getClass(), precoPropinaListNewPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaListNew.add( precoPropinaListNewPrecoPropinaToAttach );
            }
            precoPropinaListNew = attachedPrecoPropinaListNew;
            curso.setPrecoPropinaList( precoPropinaListNew );
            List<Turma> attachedTurmaListNew = new ArrayList<Turma>();
            for ( Turma turmaListNewTurmaToAttach : turmaListNew )
            {
                turmaListNewTurmaToAttach = em.getReference( turmaListNewTurmaToAttach.getClass(), turmaListNewTurmaToAttach.getPkTurma() );
                attachedTurmaListNew.add( turmaListNewTurmaToAttach );
            }
            turmaListNew = attachedTurmaListNew;
            curso.setTurmaList( turmaListNew );
            curso = em.merge( curso );
            for ( PrecoConfirmacao precoConfirmacaoListNewPrecoConfirmacao : precoConfirmacaoListNew )
            {
                if ( !precoConfirmacaoListOld.contains( precoConfirmacaoListNewPrecoConfirmacao ) )
                {
                    Curso oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao = precoConfirmacaoListNewPrecoConfirmacao.getFkCurso();
                    precoConfirmacaoListNewPrecoConfirmacao.setFkCurso( curso );
                    precoConfirmacaoListNewPrecoConfirmacao = em.merge( precoConfirmacaoListNewPrecoConfirmacao );
                    if ( oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao != null && !oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao.equals( curso ) )
                    {
                        oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao.getPrecoConfirmacaoList().remove( precoConfirmacaoListNewPrecoConfirmacao );
                        oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao = em.merge( oldFkCursoOfPrecoConfirmacaoListNewPrecoConfirmacao );
                    }
                }
            }
            for ( PrecoPropina precoPropinaListNewPrecoPropina : precoPropinaListNew )
            {
                if ( !precoPropinaListOld.contains( precoPropinaListNewPrecoPropina ) )
                {
                    Curso oldFkCursoOfPrecoPropinaListNewPrecoPropina = precoPropinaListNewPrecoPropina.getFkCurso();
                    precoPropinaListNewPrecoPropina.setFkCurso( curso );
                    precoPropinaListNewPrecoPropina = em.merge( precoPropinaListNewPrecoPropina );
                    if ( oldFkCursoOfPrecoPropinaListNewPrecoPropina != null && !oldFkCursoOfPrecoPropinaListNewPrecoPropina.equals( curso ) )
                    {
                        oldFkCursoOfPrecoPropinaListNewPrecoPropina.getPrecoPropinaList().remove( precoPropinaListNewPrecoPropina );
                        oldFkCursoOfPrecoPropinaListNewPrecoPropina = em.merge( oldFkCursoOfPrecoPropinaListNewPrecoPropina );
                    }
                }
            }
            for ( Turma turmaListNewTurma : turmaListNew )
            {
                if ( !turmaListOld.contains( turmaListNewTurma ) )
                {
                    Curso oldFkCursoOfTurmaListNewTurma = turmaListNewTurma.getFkCurso();
                    turmaListNewTurma.setFkCurso( curso );
                    turmaListNewTurma = em.merge( turmaListNewTurma );
                    if ( oldFkCursoOfTurmaListNewTurma != null && !oldFkCursoOfTurmaListNewTurma.equals( curso ) )
                    {
                        oldFkCursoOfTurmaListNewTurma.getTurmaList().remove( turmaListNewTurma );
                        oldFkCursoOfTurmaListNewTurma = em.merge( oldFkCursoOfTurmaListNewTurma );
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
                Integer id = curso.getPkCurso();
                if ( findCurso( id ) == null )
                {
                    throw new NonexistentEntityException( "The curso with id " + id + " no longer exists." );
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
            Curso curso;
            try
            {
                curso = em.getReference( Curso.class, id );
                curso.getPkCurso();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The curso with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<PrecoConfirmacao> precoConfirmacaoListOrphanCheck = curso.getPrecoConfirmacaoList();
            for ( PrecoConfirmacao precoConfirmacaoListOrphanCheckPrecoConfirmacao : precoConfirmacaoListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Curso (" + curso + ") cannot be destroyed since the PrecoConfirmacao " + precoConfirmacaoListOrphanCheckPrecoConfirmacao + " in its precoConfirmacaoList field has a non-nullable fkCurso field." );
            }
            List<PrecoPropina> precoPropinaListOrphanCheck = curso.getPrecoPropinaList();
            for ( PrecoPropina precoPropinaListOrphanCheckPrecoPropina : precoPropinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Curso (" + curso + ") cannot be destroyed since the PrecoPropina " + precoPropinaListOrphanCheckPrecoPropina + " in its precoPropinaList field has a non-nullable fkCurso field." );
            }
            List<Turma> turmaListOrphanCheck = curso.getTurmaList();
            for ( Turma turmaListOrphanCheckTurma : turmaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Curso (" + curso + ") cannot be destroyed since the Turma " + turmaListOrphanCheckTurma + " in its turmaList field has a non-nullable fkCurso field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( curso );
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

    public List<Curso> findCursoEntities()
    {
        return findCursoEntities( true, -1, -1 );
    }

    public List<Curso> findCursoEntities( int maxResults, int firstResult )
    {
        return findCursoEntities( false, maxResults, firstResult );
    }

    private List<Curso> findCursoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Curso.class ) );
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

    public Curso findCurso( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Curso.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getCursoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from( Curso.class );
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
