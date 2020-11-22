/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.TipoNota;
import entity.AnoLectivo;
import entity.Disciplina;
import entity.Estudante;
import entity.Nota;
import entity.Trimestre;
import entity.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class NotaJpaController implements Serializable
{

    public NotaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Nota nota )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNota fkTipoNota = nota.getFkTipoNota();
            if ( fkTipoNota != null )
            {
                fkTipoNota = em.getReference( fkTipoNota.getClass(), fkTipoNota.getPkTipoNota() );
                nota.setFkTipoNota( fkTipoNota );
            }
            AnoLectivo fkAnoLectivo = nota.getFkAnoLectivo();
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo = em.getReference( fkAnoLectivo.getClass(), fkAnoLectivo.getPkAnoLectivo() );
                nota.setFkAnoLectivo( fkAnoLectivo );
            }
            Disciplina fkDisciplina = nota.getFkDisciplina();
            if ( fkDisciplina != null )
            {
                fkDisciplina = em.getReference( fkDisciplina.getClass(), fkDisciplina.getPkDisciplina() );
                nota.setFkDisciplina( fkDisciplina );
            }
            Estudante fkEstudante = nota.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante = em.getReference( fkEstudante.getClass(), fkEstudante.getPkEstudante() );
                nota.setFkEstudante( fkEstudante );
            }
            Trimestre fkTrimestre = nota.getFkTrimestre();
            if ( fkTrimestre != null )
            {
                fkTrimestre = em.getReference( fkTrimestre.getClass(), fkTrimestre.getPkSemestre() );
                nota.setFkTrimestre( fkTrimestre );
            }
            Professor fkProfessor = nota.getFkProfessor();
            if ( fkProfessor != null )
            {
                fkProfessor = em.getReference( fkProfessor.getClass(), fkProfessor.getPkProfessor() );
                nota.setFkProfessor( fkProfessor );
            }
            em.persist( nota );
            if ( fkTipoNota != null )
            {
                fkTipoNota.getNotaList().add( nota );
                fkTipoNota = em.merge( fkTipoNota );
            }
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo.getNotaList().add( nota );
                fkAnoLectivo = em.merge( fkAnoLectivo );
            }
            if ( fkDisciplina != null )
            {
                fkDisciplina.getNotaList().add( nota );
                fkDisciplina = em.merge( fkDisciplina );
            }
            if ( fkEstudante != null )
            {
                fkEstudante.getNotaList().add( nota );
                fkEstudante = em.merge( fkEstudante );
            }
            if ( fkTrimestre != null )
            {
                fkTrimestre.getNotaList().add( nota );
                fkTrimestre = em.merge( fkTrimestre );
            }
            if ( fkProfessor != null )
            {
                fkProfessor.getNotaList().add( nota );
                fkProfessor = em.merge( fkProfessor );
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

    public void edit( Nota nota ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Nota persistentNota = em.find( Nota.class, nota.getPkNota() );
            TipoNota fkTipoNotaOld = persistentNota.getFkTipoNota();
            TipoNota fkTipoNotaNew = nota.getFkTipoNota();
            AnoLectivo fkAnoLectivoOld = persistentNota.getFkAnoLectivo();
            AnoLectivo fkAnoLectivoNew = nota.getFkAnoLectivo();
            Disciplina fkDisciplinaOld = persistentNota.getFkDisciplina();
            Disciplina fkDisciplinaNew = nota.getFkDisciplina();
            Estudante fkEstudanteOld = persistentNota.getFkEstudante();
            Estudante fkEstudanteNew = nota.getFkEstudante();
            Trimestre fkTrimestreOld = persistentNota.getFkTrimestre();
            Trimestre fkTrimestreNew = nota.getFkTrimestre();
            Professor fkProfessorOld = persistentNota.getFkProfessor();
            Professor fkProfessorNew = nota.getFkProfessor();
            if ( fkTipoNotaNew != null )
            {
                fkTipoNotaNew = em.getReference( fkTipoNotaNew.getClass(), fkTipoNotaNew.getPkTipoNota() );
                nota.setFkTipoNota( fkTipoNotaNew );
            }
            if ( fkAnoLectivoNew != null )
            {
                fkAnoLectivoNew = em.getReference( fkAnoLectivoNew.getClass(), fkAnoLectivoNew.getPkAnoLectivo() );
                nota.setFkAnoLectivo( fkAnoLectivoNew );
            }
            if ( fkDisciplinaNew != null )
            {
                fkDisciplinaNew = em.getReference( fkDisciplinaNew.getClass(), fkDisciplinaNew.getPkDisciplina() );
                nota.setFkDisciplina( fkDisciplinaNew );
            }
            if ( fkEstudanteNew != null )
            {
                fkEstudanteNew = em.getReference( fkEstudanteNew.getClass(), fkEstudanteNew.getPkEstudante() );
                nota.setFkEstudante( fkEstudanteNew );
            }
            if ( fkTrimestreNew != null )
            {
                fkTrimestreNew = em.getReference( fkTrimestreNew.getClass(), fkTrimestreNew.getPkSemestre() );
                nota.setFkTrimestre( fkTrimestreNew );
            }
            if ( fkProfessorNew != null )
            {
                fkProfessorNew = em.getReference( fkProfessorNew.getClass(), fkProfessorNew.getPkProfessor() );
                nota.setFkProfessor( fkProfessorNew );
            }
            nota = em.merge( nota );
            if ( fkTipoNotaOld != null && !fkTipoNotaOld.equals( fkTipoNotaNew ) )
            {
                fkTipoNotaOld.getNotaList().remove( nota );
                fkTipoNotaOld = em.merge( fkTipoNotaOld );
            }
            if ( fkTipoNotaNew != null && !fkTipoNotaNew.equals( fkTipoNotaOld ) )
            {
                fkTipoNotaNew.getNotaList().add( nota );
                fkTipoNotaNew = em.merge( fkTipoNotaNew );
            }
            if ( fkAnoLectivoOld != null && !fkAnoLectivoOld.equals( fkAnoLectivoNew ) )
            {
                fkAnoLectivoOld.getNotaList().remove( nota );
                fkAnoLectivoOld = em.merge( fkAnoLectivoOld );
            }
            if ( fkAnoLectivoNew != null && !fkAnoLectivoNew.equals( fkAnoLectivoOld ) )
            {
                fkAnoLectivoNew.getNotaList().add( nota );
                fkAnoLectivoNew = em.merge( fkAnoLectivoNew );
            }
            if ( fkDisciplinaOld != null && !fkDisciplinaOld.equals( fkDisciplinaNew ) )
            {
                fkDisciplinaOld.getNotaList().remove( nota );
                fkDisciplinaOld = em.merge( fkDisciplinaOld );
            }
            if ( fkDisciplinaNew != null && !fkDisciplinaNew.equals( fkDisciplinaOld ) )
            {
                fkDisciplinaNew.getNotaList().add( nota );
                fkDisciplinaNew = em.merge( fkDisciplinaNew );
            }
            if ( fkEstudanteOld != null && !fkEstudanteOld.equals( fkEstudanteNew ) )
            {
                fkEstudanteOld.getNotaList().remove( nota );
                fkEstudanteOld = em.merge( fkEstudanteOld );
            }
            if ( fkEstudanteNew != null && !fkEstudanteNew.equals( fkEstudanteOld ) )
            {
                fkEstudanteNew.getNotaList().add( nota );
                fkEstudanteNew = em.merge( fkEstudanteNew );
            }
            if ( fkTrimestreOld != null && !fkTrimestreOld.equals( fkTrimestreNew ) )
            {
                fkTrimestreOld.getNotaList().remove( nota );
                fkTrimestreOld = em.merge( fkTrimestreOld );
            }
            if ( fkTrimestreNew != null && !fkTrimestreNew.equals( fkTrimestreOld ) )
            {
                fkTrimestreNew.getNotaList().add( nota );
                fkTrimestreNew = em.merge( fkTrimestreNew );
            }
            if ( fkProfessorOld != null && !fkProfessorOld.equals( fkProfessorNew ) )
            {
                fkProfessorOld.getNotaList().remove( nota );
                fkProfessorOld = em.merge( fkProfessorOld );
            }
            if ( fkProfessorNew != null && !fkProfessorNew.equals( fkProfessorOld ) )
            {
                fkProfessorNew.getNotaList().add( nota );
                fkProfessorNew = em.merge( fkProfessorNew );
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if ( msg == null || msg.length() == 0 )
            {
                Integer id = nota.getPkNota();
                if ( findNota( id ) == null )
                {
                    throw new NonexistentEntityException( "The nota with id " + id + " no longer exists." );
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
            Nota nota;
            try
            {
                nota = em.getReference( Nota.class, id );
                nota.getPkNota();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The nota with id " + id + " no longer exists.", enfe );
            }
            TipoNota fkTipoNota = nota.getFkTipoNota();
            if ( fkTipoNota != null )
            {
                fkTipoNota.getNotaList().remove( nota );
                fkTipoNota = em.merge( fkTipoNota );
            }
            AnoLectivo fkAnoLectivo = nota.getFkAnoLectivo();
            if ( fkAnoLectivo != null )
            {
                fkAnoLectivo.getNotaList().remove( nota );
                fkAnoLectivo = em.merge( fkAnoLectivo );
            }
            Disciplina fkDisciplina = nota.getFkDisciplina();
            if ( fkDisciplina != null )
            {
                fkDisciplina.getNotaList().remove( nota );
                fkDisciplina = em.merge( fkDisciplina );
            }
            Estudante fkEstudante = nota.getFkEstudante();
            if ( fkEstudante != null )
            {
                fkEstudante.getNotaList().remove( nota );
                fkEstudante = em.merge( fkEstudante );
            }
            Trimestre fkTrimestre = nota.getFkTrimestre();
            if ( fkTrimestre != null )
            {
                fkTrimestre.getNotaList().remove( nota );
                fkTrimestre = em.merge( fkTrimestre );
            }
            Professor fkProfessor = nota.getFkProfessor();
            if ( fkProfessor != null )
            {
                fkProfessor.getNotaList().remove( nota );
                fkProfessor = em.merge( fkProfessor );
            }
            em.remove( nota );
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

    public List<Nota> findNotaEntities()
    {
        return findNotaEntities( true, -1, -1 );
    }

    public List<Nota> findNotaEntities( int maxResults, int firstResult )
    {
        return findNotaEntities( false, maxResults, firstResult );
    }

    private List<Nota> findNotaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Nota.class ) );
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

    public Nota findNota( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Nota.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getNotaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nota> rt = cq.from( Nota.class );
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
