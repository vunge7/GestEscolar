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
import entity.Disciplina;
import entity.ItemTurmaProfessorDisciplina;
import entity.Professor;
import entity.Turma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ItemTurmaProfessorDisciplinaJpaController implements Serializable
{

    public ItemTurmaProfessorDisciplinaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplina )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplina fkDisciplina = itemTurmaProfessorDisciplina.getFkDisciplina();
            if ( fkDisciplina != null )
            {
                fkDisciplina = em.getReference( fkDisciplina.getClass(), fkDisciplina.getPkDisciplina() );
                itemTurmaProfessorDisciplina.setFkDisciplina( fkDisciplina );
            }
            Professor fkProfessor = itemTurmaProfessorDisciplina.getFkProfessor();
            if ( fkProfessor != null )
            {
                fkProfessor = em.getReference( fkProfessor.getClass(), fkProfessor.getPkProfessor() );
                itemTurmaProfessorDisciplina.setFkProfessor( fkProfessor );
            }
            Turma fkTurma = itemTurmaProfessorDisciplina.getFkTurma();
            if ( fkTurma != null )
            {
                fkTurma = em.getReference( fkTurma.getClass(), fkTurma.getPkTurma() );
                itemTurmaProfessorDisciplina.setFkTurma( fkTurma );
            }
            em.persist( itemTurmaProfessorDisciplina );
            if ( fkDisciplina != null )
            {
                fkDisciplina.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkDisciplina = em.merge( fkDisciplina );
            }
            if ( fkProfessor != null )
            {
                fkProfessor.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkProfessor = em.merge( fkProfessor );
            }
            if ( fkTurma != null )
            {
                fkTurma.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkTurma = em.merge( fkTurma );
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

    public void edit( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplina ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemTurmaProfessorDisciplina persistentItemTurmaProfessorDisciplina = em.find( ItemTurmaProfessorDisciplina.class, itemTurmaProfessorDisciplina.getPkProfessorDisciplina() );
            Disciplina fkDisciplinaOld = persistentItemTurmaProfessorDisciplina.getFkDisciplina();
            Disciplina fkDisciplinaNew = itemTurmaProfessorDisciplina.getFkDisciplina();
            Professor fkProfessorOld = persistentItemTurmaProfessorDisciplina.getFkProfessor();
            Professor fkProfessorNew = itemTurmaProfessorDisciplina.getFkProfessor();
            Turma fkTurmaOld = persistentItemTurmaProfessorDisciplina.getFkTurma();
            Turma fkTurmaNew = itemTurmaProfessorDisciplina.getFkTurma();
            if ( fkDisciplinaNew != null )
            {
                fkDisciplinaNew = em.getReference( fkDisciplinaNew.getClass(), fkDisciplinaNew.getPkDisciplina() );
                itemTurmaProfessorDisciplina.setFkDisciplina( fkDisciplinaNew );
            }
            if ( fkProfessorNew != null )
            {
                fkProfessorNew = em.getReference( fkProfessorNew.getClass(), fkProfessorNew.getPkProfessor() );
                itemTurmaProfessorDisciplina.setFkProfessor( fkProfessorNew );
            }
            if ( fkTurmaNew != null )
            {
                fkTurmaNew = em.getReference( fkTurmaNew.getClass(), fkTurmaNew.getPkTurma() );
                itemTurmaProfessorDisciplina.setFkTurma( fkTurmaNew );
            }
            itemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplina );
            if ( fkDisciplinaOld != null && !fkDisciplinaOld.equals( fkDisciplinaNew ) )
            {
                fkDisciplinaOld.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkDisciplinaOld = em.merge( fkDisciplinaOld );
            }
            if ( fkDisciplinaNew != null && !fkDisciplinaNew.equals( fkDisciplinaOld ) )
            {
                fkDisciplinaNew.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkDisciplinaNew = em.merge( fkDisciplinaNew );
            }
            if ( fkProfessorOld != null && !fkProfessorOld.equals( fkProfessorNew ) )
            {
                fkProfessorOld.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkProfessorOld = em.merge( fkProfessorOld );
            }
            if ( fkProfessorNew != null && !fkProfessorNew.equals( fkProfessorOld ) )
            {
                fkProfessorNew.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkProfessorNew = em.merge( fkProfessorNew );
            }
            if ( fkTurmaOld != null && !fkTurmaOld.equals( fkTurmaNew ) )
            {
                fkTurmaOld.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkTurmaOld = em.merge( fkTurmaOld );
            }
            if ( fkTurmaNew != null && !fkTurmaNew.equals( fkTurmaOld ) )
            {
                fkTurmaNew.getItemTurmaProfessorDisciplinaList().add( itemTurmaProfessorDisciplina );
                fkTurmaNew = em.merge( fkTurmaNew );
            }
            em.getTransaction().commit();
        }
        catch ( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if ( msg == null || msg.length() == 0 )
            {
                Integer id = itemTurmaProfessorDisciplina.getPkProfessorDisciplina();
                if ( findItemTurmaProfessorDisciplina( id ) == null )
                {
                    throw new NonexistentEntityException( "The itemTurmaProfessorDisciplina with id " + id + " no longer exists." );
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
            ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplina;
            try
            {
                itemTurmaProfessorDisciplina = em.getReference( ItemTurmaProfessorDisciplina.class, id );
                itemTurmaProfessorDisciplina.getPkProfessorDisciplina();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The itemTurmaProfessorDisciplina with id " + id + " no longer exists.", enfe );
            }
            Disciplina fkDisciplina = itemTurmaProfessorDisciplina.getFkDisciplina();
            if ( fkDisciplina != null )
            {
                fkDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkDisciplina = em.merge( fkDisciplina );
            }
            Professor fkProfessor = itemTurmaProfessorDisciplina.getFkProfessor();
            if ( fkProfessor != null )
            {
                fkProfessor.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkProfessor = em.merge( fkProfessor );
            }
            Turma fkTurma = itemTurmaProfessorDisciplina.getFkTurma();
            if ( fkTurma != null )
            {
                fkTurma.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplina );
                fkTurma = em.merge( fkTurma );
            }
            em.remove( itemTurmaProfessorDisciplina );
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

    public List<ItemTurmaProfessorDisciplina> findItemTurmaProfessorDisciplinaEntities()
    {
        return findItemTurmaProfessorDisciplinaEntities( true, -1, -1 );
    }

    public List<ItemTurmaProfessorDisciplina> findItemTurmaProfessorDisciplinaEntities( int maxResults, int firstResult )
    {
        return findItemTurmaProfessorDisciplinaEntities( false, maxResults, firstResult );
    }

    private List<ItemTurmaProfessorDisciplina> findItemTurmaProfessorDisciplinaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( ItemTurmaProfessorDisciplina.class ) );
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

    public ItemTurmaProfessorDisciplina findItemTurmaProfessorDisciplina( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( ItemTurmaProfessorDisciplina.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getItemTurmaProfessorDisciplinaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemTurmaProfessorDisciplina> rt = cq.from( ItemTurmaProfessorDisciplina.class );
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
