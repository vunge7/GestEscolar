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
import entity.Nota;
import java.util.ArrayList;
import java.util.List;
import entity.ItemTurmaProfessorDisciplina;
import entity.Professor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ProfessorJpaController implements Serializable
{

    public ProfessorJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Professor professor )
    {
        if ( professor.getNotaList() == null )
        {
            professor.setNotaList( new ArrayList<Nota>() );
        }
        if ( professor.getItemTurmaProfessorDisciplinaList() == null )
        {
            professor.setItemTurmaProfessorDisciplinaList( new ArrayList<ItemTurmaProfessorDisciplina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : professor.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            professor.setNotaList( attachedNotaList );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaList = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach : professor.getItemTurmaProfessorDisciplinaList() )
            {
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaList.add( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach );
            }
            professor.setItemTurmaProfessorDisciplinaList( attachedItemTurmaProfessorDisciplinaList );
            em.persist( professor );
            for ( Nota notaListNota : professor.getNotaList() )
            {
                Professor oldFkProfessorOfNotaListNota = notaListNota.getFkProfessor();
                notaListNota.setFkProfessor( professor );
                notaListNota = em.merge( notaListNota );
                if ( oldFkProfessorOfNotaListNota != null )
                {
                    oldFkProfessorOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkProfessorOfNotaListNota = em.merge( oldFkProfessorOfNotaListNota );
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina : professor.getItemTurmaProfessorDisciplinaList() )
            {
                Professor oldFkProfessorOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getFkProfessor();
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.setFkProfessor( professor );
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                if ( oldFkProfessorOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina != null )
                {
                    oldFkProfessorOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                    oldFkProfessorOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( oldFkProfessorOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
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

    public void edit( Professor professor ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Professor persistentProfessor = em.find( Professor.class, professor.getPkProfessor() );
            List<Nota> notaListOld = persistentProfessor.getNotaList();
            List<Nota> notaListNew = professor.getNotaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOld = persistentProfessor.getItemTurmaProfessorDisciplinaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListNew = professor.getItemTurmaProfessorDisciplinaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkProfessor field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOldItemTurmaProfessorDisciplina + " since its fkProfessor field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<Nota> attachedNotaListNew = new ArrayList<Nota>();
            for ( Nota notaListNewNotaToAttach : notaListNew )
            {
                notaListNewNotaToAttach = em.getReference( notaListNewNotaToAttach.getClass(), notaListNewNotaToAttach.getPkNota() );
                attachedNotaListNew.add( notaListNewNotaToAttach );
            }
            notaListNew = attachedNotaListNew;
            professor.setNotaList( notaListNew );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaListNew = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach : itemTurmaProfessorDisciplinaListNew )
            {
                itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaListNew.add( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach );
            }
            itemTurmaProfessorDisciplinaListNew = attachedItemTurmaProfessorDisciplinaListNew;
            professor.setItemTurmaProfessorDisciplinaList( itemTurmaProfessorDisciplinaListNew );
            professor = em.merge( professor );
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    Professor oldFkProfessorOfNotaListNewNota = notaListNewNota.getFkProfessor();
                    notaListNewNota.setFkProfessor( professor );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkProfessorOfNotaListNewNota != null && !oldFkProfessorOfNotaListNewNota.equals( professor ) )
                    {
                        oldFkProfessorOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkProfessorOfNotaListNewNota = em.merge( oldFkProfessorOfNotaListNewNota );
                    }
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListNew )
            {
                if ( !itemTurmaProfessorDisciplinaListOld.contains( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina ) )
                {
                    Professor oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getFkProfessor();
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.setFkProfessor( professor );
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                    if ( oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina != null && !oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.equals( professor ) )
                    {
                        oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                        oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( oldFkProfessorOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
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
                Integer id = professor.getPkProfessor();
                if ( findProfessor( id ) == null )
                {
                    throw new NonexistentEntityException( "The professor with id " + id + " no longer exists." );
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
            Professor professor;
            try
            {
                professor = em.getReference( Professor.class, id );
                professor.getPkProfessor();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The professor with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = professor.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Professor (" + professor + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkProfessor field." );
            }
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOrphanCheck = professor.getItemTurmaProfessorDisciplinaList();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Professor (" + professor + ") cannot be destroyed since the ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina + " in its itemTurmaProfessorDisciplinaList field has a non-nullable fkProfessor field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( professor );
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

    public List<Professor> findProfessorEntities()
    {
        return findProfessorEntities( true, -1, -1 );
    }

    public List<Professor> findProfessorEntities( int maxResults, int firstResult )
    {
        return findProfessorEntities( false, maxResults, firstResult );
    }

    private List<Professor> findProfessorEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Professor.class ) );
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

    public Professor findProfessor( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Professor.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getProfessorCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Professor> rt = cq.from( Professor.class );
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
