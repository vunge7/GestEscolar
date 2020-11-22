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
import entity.AnoLectivo;
import entity.Classe;
import entity.Curso;
import entity.Sala;
import entity.Turno;
import entity.ConfirmacaoMatricula;
import java.util.ArrayList;
import java.util.List;
import entity.ItemTurmaProfessorDisciplina;
import entity.Turma;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class TurmaJpaController implements Serializable
{

    public TurmaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Turma turma )
    {
        if ( turma.getConfirmacaoMatriculaList() == null )
        {
            turma.setConfirmacaoMatriculaList( new ArrayList<ConfirmacaoMatricula>() );
        }
        if ( turma.getItemTurmaProfessorDisciplinaList() == null )
        {
            turma.setItemTurmaProfessorDisciplinaList( new ArrayList<ItemTurmaProfessorDisciplina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            AnoLectivo fkAnoLectivo = turma.getFkAnoLectivo();
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo = em.getReference( fkAnoLectivo.getClass(), fkAnoLectivo.getPkAnoLectivo() );
                turma.setFkAnoLectivo( fkAnoLectivo );
            }
            Classe fkClasse = turma.getFkClasse();
            if ( fkClasse != null )
            {
                fkClasse = em.getReference( fkClasse.getClass(), fkClasse.getPkClasse() );
                turma.setFkClasse( fkClasse );
            }
            Curso fkCurso = turma.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso = em.getReference( fkCurso.getClass(), fkCurso.getPkCurso() );
                turma.setFkCurso( fkCurso );
            }
            Sala fkSala = turma.getFkSala();
            if ( fkSala != null )
            {
                fkSala = em.getReference( fkSala.getClass(), fkSala.getPkSala() );
                turma.setFkSala( fkSala );
            }
            Turno fkTurno = turma.getFkTurno();
            if ( fkTurno != null )
            {
                fkTurno = em.getReference( fkTurno.getClass(), fkTurno.getPkTurno() );
                turma.setFkTurno( fkTurno );
            }
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaList = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatriculaToAttach : turma.getConfirmacaoMatriculaList() )
            {
                confirmacaoMatriculaListConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaList.add( confirmacaoMatriculaListConfirmacaoMatriculaToAttach );
            }
            turma.setConfirmacaoMatriculaList( attachedConfirmacaoMatriculaList );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaList = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach : turma.getItemTurmaProfessorDisciplinaList() )
            {
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaList.add( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach );
            }
            turma.setItemTurmaProfessorDisciplinaList( attachedItemTurmaProfessorDisciplinaList );
            em.persist( turma );
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo.getTurmaList().add( turma );
                fkAnoLectivo = em.merge( fkAnoLectivo );
            }
            if ( fkClasse != null )
            {
                fkClasse.getTurmaList().add( turma );
                fkClasse = em.merge( fkClasse );
            }
            if ( fkCurso != null )
            {
                fkCurso.getTurmaList().add( turma );
                fkCurso = em.merge( fkCurso );
            }
            if ( fkSala != null )
            {
                fkSala.getTurmaList().add( turma );
                fkSala = em.merge( fkSala );
            }
            if ( fkTurno != null )
            {
                fkTurno.getTurmaList().add( turma );
                fkTurno = em.merge( fkTurno );
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatricula : turma.getConfirmacaoMatriculaList() )
            {
                Turma oldFkTurmaOfConfirmacaoMatriculaListConfirmacaoMatricula = confirmacaoMatriculaListConfirmacaoMatricula.getFkTurma();
                confirmacaoMatriculaListConfirmacaoMatricula.setFkTurma( turma );
                confirmacaoMatriculaListConfirmacaoMatricula = em.merge( confirmacaoMatriculaListConfirmacaoMatricula );
                if ( oldFkTurmaOfConfirmacaoMatriculaListConfirmacaoMatricula != null )
                {
                    oldFkTurmaOfConfirmacaoMatriculaListConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListConfirmacaoMatricula );
                    oldFkTurmaOfConfirmacaoMatriculaListConfirmacaoMatricula = em.merge( oldFkTurmaOfConfirmacaoMatriculaListConfirmacaoMatricula );
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina : turma.getItemTurmaProfessorDisciplinaList() )
            {
                Turma oldFkTurmaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getFkTurma();
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.setFkTurma( turma );
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                if ( oldFkTurmaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina != null )
                {
                    oldFkTurmaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                    oldFkTurmaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( oldFkTurmaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
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

    public void edit( Turma turma ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Turma persistentTurma = em.find( Turma.class, turma.getPkTurma() );
            AnoLectivo fkAnoLectivoOld = persistentTurma.getFkAnoLectivo();
            AnoLectivo fkAnoLectivoNew = turma.getFkAnoLectivo();
            Classe fkClasseOld = persistentTurma.getFkClasse();
            Classe fkClasseNew = turma.getFkClasse();
            Curso fkCursoOld = persistentTurma.getFkCurso();
            Curso fkCursoNew = turma.getFkCurso();
            Sala fkSalaOld = persistentTurma.getFkSala();
            Sala fkSalaNew = turma.getFkSala();
            Turno fkTurnoOld = persistentTurma.getFkTurno();
            Turno fkTurnoNew = turma.getFkTurno();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOld = persistentTurma.getConfirmacaoMatriculaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListNew = turma.getConfirmacaoMatriculaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOld = persistentTurma.getItemTurmaProfessorDisciplinaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListNew = turma.getItemTurmaProfessorDisciplinaList();
            List<String> illegalOrphanMessages = null;
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOldConfirmacaoMatricula : confirmacaoMatriculaListOld )
            {
                if ( !confirmacaoMatriculaListNew.contains( confirmacaoMatriculaListOldConfirmacaoMatricula ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ConfirmacaoMatricula " + confirmacaoMatriculaListOldConfirmacaoMatricula + " since its fkTurma field is not nullable." );
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListOldItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListOld )
            {
                if ( !itemTurmaProfessorDisciplinaListNew.contains( itemTurmaProfessorDisciplinaListOldItemTurmaProfessorDisciplina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOldItemTurmaProfessorDisciplina + " since its fkTurma field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkAnoLectivoNew != null )
            {
                fkAnoLectivoNew = em.getReference( fkAnoLectivoNew.getClass(), fkAnoLectivoNew.getPkAnoLectivo() );
                turma.setFkAnoLectivo( fkAnoLectivoNew );
            }
            if ( fkClasseNew != null )
            {
                fkClasseNew = em.getReference( fkClasseNew.getClass(), fkClasseNew.getPkClasse() );
                turma.setFkClasse( fkClasseNew );
            }
            if ( fkCursoNew != null )
            {
                fkCursoNew = em.getReference( fkCursoNew.getClass(), fkCursoNew.getPkCurso() );
                turma.setFkCurso( fkCursoNew );
            }
            if ( fkSalaNew != null )
            {
                fkSalaNew = em.getReference( fkSalaNew.getClass(), fkSalaNew.getPkSala() );
                turma.setFkSala( fkSalaNew );
            }
            if ( fkTurnoNew != null )
            {
                fkTurnoNew = em.getReference( fkTurnoNew.getClass(), fkTurnoNew.getPkTurno() );
                turma.setFkTurno( fkTurnoNew );
            }
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaListNew = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach : confirmacaoMatriculaListNew )
            {
                confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaListNew.add( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach );
            }
            confirmacaoMatriculaListNew = attachedConfirmacaoMatriculaListNew;
            turma.setConfirmacaoMatriculaList( confirmacaoMatriculaListNew );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaListNew = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach : itemTurmaProfessorDisciplinaListNew )
            {
                itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaListNew.add( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach );
            }
            itemTurmaProfessorDisciplinaListNew = attachedItemTurmaProfessorDisciplinaListNew;
            turma.setItemTurmaProfessorDisciplinaList( itemTurmaProfessorDisciplinaListNew );
            turma = em.merge( turma );
            if ( fkAnoLectivoOld != null && !fkAnoLectivoOld.equals( fkAnoLectivoNew ) )
            {
                fkAnoLectivoOld.getTurmaList().remove( turma );
                fkAnoLectivoOld = em.merge( fkAnoLectivoOld );
            }
            if ( fkAnoLectivoNew != null && !fkAnoLectivoNew.equals( fkAnoLectivoOld ) )
            {
                fkAnoLectivoNew.getTurmaList().add( turma );
                fkAnoLectivoNew = em.merge( fkAnoLectivoNew );
            }
            if ( fkClasseOld != null && !fkClasseOld.equals( fkClasseNew ) )
            {
                fkClasseOld.getTurmaList().remove( turma );
                fkClasseOld = em.merge( fkClasseOld );
            }
            if ( fkClasseNew != null && !fkClasseNew.equals( fkClasseOld ) )
            {
                fkClasseNew.getTurmaList().add( turma );
                fkClasseNew = em.merge( fkClasseNew );
            }
            if ( fkCursoOld != null && !fkCursoOld.equals( fkCursoNew ) )
            {
                fkCursoOld.getTurmaList().remove( turma );
                fkCursoOld = em.merge( fkCursoOld );
            }
            if ( fkCursoNew != null && !fkCursoNew.equals( fkCursoOld ) )
            {
                fkCursoNew.getTurmaList().add( turma );
                fkCursoNew = em.merge( fkCursoNew );
            }
            if ( fkSalaOld != null && !fkSalaOld.equals( fkSalaNew ) )
            {
                fkSalaOld.getTurmaList().remove( turma );
                fkSalaOld = em.merge( fkSalaOld );
            }
            if ( fkSalaNew != null && !fkSalaNew.equals( fkSalaOld ) )
            {
                fkSalaNew.getTurmaList().add( turma );
                fkSalaNew = em.merge( fkSalaNew );
            }
            if ( fkTurnoOld != null && !fkTurnoOld.equals( fkTurnoNew ) )
            {
                fkTurnoOld.getTurmaList().remove( turma );
                fkTurnoOld = em.merge( fkTurnoOld );
            }
            if ( fkTurnoNew != null && !fkTurnoNew.equals( fkTurnoOld ) )
            {
                fkTurnoNew.getTurmaList().add( turma );
                fkTurnoNew = em.merge( fkTurnoNew );
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatricula : confirmacaoMatriculaListNew )
            {
                if ( !confirmacaoMatriculaListOld.contains( confirmacaoMatriculaListNewConfirmacaoMatricula ) )
                {
                    Turma oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula = confirmacaoMatriculaListNewConfirmacaoMatricula.getFkTurma();
                    confirmacaoMatriculaListNewConfirmacaoMatricula.setFkTurma( turma );
                    confirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( confirmacaoMatriculaListNewConfirmacaoMatricula );
                    if ( oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula != null && !oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula.equals( turma ) )
                    {
                        oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListNewConfirmacaoMatricula );
                        oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( oldFkTurmaOfConfirmacaoMatriculaListNewConfirmacaoMatricula );
                    }
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListNew )
            {
                if ( !itemTurmaProfessorDisciplinaListOld.contains( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina ) )
                {
                    Turma oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getFkTurma();
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.setFkTurma( turma );
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                    if ( oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina != null && !oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.equals( turma ) )
                    {
                        oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                        oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( oldFkTurmaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
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
                Integer id = turma.getPkTurma();
                if ( findTurma( id ) == null )
                {
                    throw new NonexistentEntityException( "The turma with id " + id + " no longer exists." );
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
            Turma turma;
            try
            {
                turma = em.getReference( Turma.class, id );
                turma.getPkTurma();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The turma with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOrphanCheck = turma.getConfirmacaoMatriculaList();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula : confirmacaoMatriculaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Turma (" + turma + ") cannot be destroyed since the ConfirmacaoMatricula " + confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula + " in its confirmacaoMatriculaList field has a non-nullable fkTurma field." );
            }
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOrphanCheck = turma.getItemTurmaProfessorDisciplinaList();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Turma (" + turma + ") cannot be destroyed since the ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina + " in its itemTurmaProfessorDisciplinaList field has a non-nullable fkTurma field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            AnoLectivo fkAnoLectivo = turma.getFkAnoLectivo();
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo.getTurmaList().remove( turma );
                fkAnoLectivo = em.merge( fkAnoLectivo );
            }
            Classe fkClasse = turma.getFkClasse();
            if ( fkClasse != null )
            {
                fkClasse.getTurmaList().remove( turma );
                fkClasse = em.merge( fkClasse );
            }
            Curso fkCurso = turma.getFkCurso();
            if ( fkCurso != null )
            {
                fkCurso.getTurmaList().remove( turma );
                fkCurso = em.merge( fkCurso );
            }
            Sala fkSala = turma.getFkSala();
            if ( fkSala != null )
            {
                fkSala.getTurmaList().remove( turma );
                fkSala = em.merge( fkSala );
            }
            Turno fkTurno = turma.getFkTurno();
            if ( fkTurno != null )
            {
                fkTurno.getTurmaList().remove( turma );
                fkTurno = em.merge( fkTurno );
            }
            em.remove( turma );
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

    public List<Turma> findTurmaEntities()
    {
        return findTurmaEntities( true, -1, -1 );
    }

    public List<Turma> findTurmaEntities( int maxResults, int firstResult )
    {
        return findTurmaEntities( false, maxResults, firstResult );
    }

    private List<Turma> findTurmaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Turma.class ) );
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

    public Turma findTurma( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Turma.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getTurmaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turma> rt = cq.from( Turma.class );
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
