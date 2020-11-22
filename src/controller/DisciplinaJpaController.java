/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Disciplina;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Nota;
import java.util.ArrayList;
import java.util.List;
import entity.ItemTurmaProfessorDisciplina;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class DisciplinaJpaController implements Serializable
{

    public DisciplinaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Disciplina disciplina )
    {
        if ( disciplina.getNotaList() == null )
        {
            disciplina.setNotaList( new ArrayList<Nota>() );
        }
        if ( disciplina.getItemTurmaProfessorDisciplinaList() == null )
        {
            disciplina.setItemTurmaProfessorDisciplinaList( new ArrayList<ItemTurmaProfessorDisciplina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : disciplina.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            disciplina.setNotaList( attachedNotaList );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaList = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach : disciplina.getItemTurmaProfessorDisciplinaList() )
            {
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaList.add( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplinaToAttach );
            }
            disciplina.setItemTurmaProfessorDisciplinaList( attachedItemTurmaProfessorDisciplinaList );
            em.persist( disciplina );
            for ( Nota notaListNota : disciplina.getNotaList() )
            {
                Disciplina oldFkDisciplinaOfNotaListNota = notaListNota.getFkDisciplina();
                notaListNota.setFkDisciplina( disciplina );
                notaListNota = em.merge( notaListNota );
                if ( oldFkDisciplinaOfNotaListNota != null )
                {
                    oldFkDisciplinaOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkDisciplinaOfNotaListNota = em.merge( oldFkDisciplinaOfNotaListNota );
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina : disciplina.getItemTurmaProfessorDisciplinaList() )
            {
                Disciplina oldFkDisciplinaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getFkDisciplina();
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.setFkDisciplina( disciplina );
                itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                if ( oldFkDisciplinaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina != null )
                {
                    oldFkDisciplinaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
                    oldFkDisciplinaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina = em.merge( oldFkDisciplinaOfItemTurmaProfessorDisciplinaListItemTurmaProfessorDisciplina );
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

    public void edit( Disciplina disciplina ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplina persistentDisciplina = em.find( Disciplina.class, disciplina.getPkDisciplina() );
            List<Nota> notaListOld = persistentDisciplina.getNotaList();
            List<Nota> notaListNew = disciplina.getNotaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOld = persistentDisciplina.getItemTurmaProfessorDisciplinaList();
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListNew = disciplina.getItemTurmaProfessorDisciplinaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkDisciplina field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOldItemTurmaProfessorDisciplina + " since its fkDisciplina field is not nullable." );
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
            disciplina.setNotaList( notaListNew );
            List<ItemTurmaProfessorDisciplina> attachedItemTurmaProfessorDisciplinaListNew = new ArrayList<ItemTurmaProfessorDisciplina>();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach : itemTurmaProfessorDisciplinaListNew )
            {
                itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach = em.getReference( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getClass(), itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach.getPkProfessorDisciplina() );
                attachedItemTurmaProfessorDisciplinaListNew.add( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplinaToAttach );
            }
            itemTurmaProfessorDisciplinaListNew = attachedItemTurmaProfessorDisciplinaListNew;
            disciplina.setItemTurmaProfessorDisciplinaList( itemTurmaProfessorDisciplinaListNew );
            disciplina = em.merge( disciplina );
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    Disciplina oldFkDisciplinaOfNotaListNewNota = notaListNewNota.getFkDisciplina();
                    notaListNewNota.setFkDisciplina( disciplina );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkDisciplinaOfNotaListNewNota != null && !oldFkDisciplinaOfNotaListNewNota.equals( disciplina ) )
                    {
                        oldFkDisciplinaOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkDisciplinaOfNotaListNewNota = em.merge( oldFkDisciplinaOfNotaListNewNota );
                    }
                }
            }
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListNew )
            {
                if ( !itemTurmaProfessorDisciplinaListOld.contains( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina ) )
                {
                    Disciplina oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getFkDisciplina();
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.setFkDisciplina( disciplina );
                    itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                    if ( oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina != null && !oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.equals( disciplina ) )
                    {
                        oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina.getItemTurmaProfessorDisciplinaList().remove( itemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
                        oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina = em.merge( oldFkDisciplinaOfItemTurmaProfessorDisciplinaListNewItemTurmaProfessorDisciplina );
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
                Integer id = disciplina.getPkDisciplina();
                if ( findDisciplina( id ) == null )
                {
                    throw new NonexistentEntityException( "The disciplina with id " + id + " no longer exists." );
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
            Disciplina disciplina;
            try
            {
                disciplina = em.getReference( Disciplina.class, id );
                disciplina.getPkDisciplina();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The disciplina with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = disciplina.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Disciplina (" + disciplina + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkDisciplina field." );
            }
            List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaListOrphanCheck = disciplina.getItemTurmaProfessorDisciplinaList();
            for ( ItemTurmaProfessorDisciplina itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina : itemTurmaProfessorDisciplinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Disciplina (" + disciplina + ") cannot be destroyed since the ItemTurmaProfessorDisciplina " + itemTurmaProfessorDisciplinaListOrphanCheckItemTurmaProfessorDisciplina + " in its itemTurmaProfessorDisciplinaList field has a non-nullable fkDisciplina field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( disciplina );
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

    public List<Disciplina> findDisciplinaEntities()
    {
        return findDisciplinaEntities( true, -1, -1 );
    }

    public List<Disciplina> findDisciplinaEntities( int maxResults, int firstResult )
    {
        return findDisciplinaEntities( false, maxResults, firstResult );
    }

    private List<Disciplina> findDisciplinaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Disciplina.class ) );
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

    public Disciplina findDisciplina( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Disciplina.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getDisciplinaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disciplina> rt = cq.from( Disciplina.class );
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
