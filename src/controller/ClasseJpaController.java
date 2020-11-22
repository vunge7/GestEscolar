/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Classe;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.PrecoPropina;
import java.util.ArrayList;
import java.util.List;
import entity.Turma;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class ClasseJpaController implements Serializable
{

    public ClasseJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Classe classe )
    {
        if ( classe.getPrecoPropinaList() == null )
        {
            classe.setPrecoPropinaList( new ArrayList<PrecoPropina>() );
        }
        if ( classe.getTurmaList() == null )
        {
            classe.setTurmaList( new ArrayList<Turma>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrecoPropina> attachedPrecoPropinaList = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListPrecoPropinaToAttach : classe.getPrecoPropinaList() )
            {
                precoPropinaListPrecoPropinaToAttach = em.getReference( precoPropinaListPrecoPropinaToAttach.getClass(), precoPropinaListPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaList.add( precoPropinaListPrecoPropinaToAttach );
            }
            classe.setPrecoPropinaList( attachedPrecoPropinaList );
            List<Turma> attachedTurmaList = new ArrayList<Turma>();
            for ( Turma turmaListTurmaToAttach : classe.getTurmaList() )
            {
                turmaListTurmaToAttach = em.getReference( turmaListTurmaToAttach.getClass(), turmaListTurmaToAttach.getPkTurma() );
                attachedTurmaList.add( turmaListTurmaToAttach );
            }
            classe.setTurmaList( attachedTurmaList );
            em.persist( classe );
            for ( PrecoPropina precoPropinaListPrecoPropina : classe.getPrecoPropinaList() )
            {
                Classe oldFkClasseOfPrecoPropinaListPrecoPropina = precoPropinaListPrecoPropina.getFkClasse();
                precoPropinaListPrecoPropina.setFkClasse( classe );
                precoPropinaListPrecoPropina = em.merge( precoPropinaListPrecoPropina );
                if ( oldFkClasseOfPrecoPropinaListPrecoPropina != null )
                {
                    oldFkClasseOfPrecoPropinaListPrecoPropina.getPrecoPropinaList().remove( precoPropinaListPrecoPropina );
                    oldFkClasseOfPrecoPropinaListPrecoPropina = em.merge( oldFkClasseOfPrecoPropinaListPrecoPropina );
                }
            }
            for ( Turma turmaListTurma : classe.getTurmaList() )
            {
                Classe oldFkClasseOfTurmaListTurma = turmaListTurma.getFkClasse();
                turmaListTurma.setFkClasse( classe );
                turmaListTurma = em.merge( turmaListTurma );
                if ( oldFkClasseOfTurmaListTurma != null )
                {
                    oldFkClasseOfTurmaListTurma.getTurmaList().remove( turmaListTurma );
                    oldFkClasseOfTurmaListTurma = em.merge( oldFkClasseOfTurmaListTurma );
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

    public void edit( Classe classe ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe persistentClasse = em.find( Classe.class, classe.getPkClasse() );
            List<PrecoPropina> precoPropinaListOld = persistentClasse.getPrecoPropinaList();
            List<PrecoPropina> precoPropinaListNew = classe.getPrecoPropinaList();
            List<Turma> turmaListOld = persistentClasse.getTurmaList();
            List<Turma> turmaListNew = classe.getTurmaList();
            List<String> illegalOrphanMessages = null;
            for ( PrecoPropina precoPropinaListOldPrecoPropina : precoPropinaListOld )
            {
                if ( !precoPropinaListNew.contains( precoPropinaListOldPrecoPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain PrecoPropina " + precoPropinaListOldPrecoPropina + " since its fkClasse field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain Turma " + turmaListOldTurma + " since its fkClasse field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<PrecoPropina> attachedPrecoPropinaListNew = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListNewPrecoPropinaToAttach : precoPropinaListNew )
            {
                precoPropinaListNewPrecoPropinaToAttach = em.getReference( precoPropinaListNewPrecoPropinaToAttach.getClass(), precoPropinaListNewPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaListNew.add( precoPropinaListNewPrecoPropinaToAttach );
            }
            precoPropinaListNew = attachedPrecoPropinaListNew;
            classe.setPrecoPropinaList( precoPropinaListNew );
            List<Turma> attachedTurmaListNew = new ArrayList<Turma>();
            for ( Turma turmaListNewTurmaToAttach : turmaListNew )
            {
                turmaListNewTurmaToAttach = em.getReference( turmaListNewTurmaToAttach.getClass(), turmaListNewTurmaToAttach.getPkTurma() );
                attachedTurmaListNew.add( turmaListNewTurmaToAttach );
            }
            turmaListNew = attachedTurmaListNew;
            classe.setTurmaList( turmaListNew );
            classe = em.merge( classe );
            for ( PrecoPropina precoPropinaListNewPrecoPropina : precoPropinaListNew )
            {
                if ( !precoPropinaListOld.contains( precoPropinaListNewPrecoPropina ) )
                {
                    Classe oldFkClasseOfPrecoPropinaListNewPrecoPropina = precoPropinaListNewPrecoPropina.getFkClasse();
                    precoPropinaListNewPrecoPropina.setFkClasse( classe );
                    precoPropinaListNewPrecoPropina = em.merge( precoPropinaListNewPrecoPropina );
                    if ( oldFkClasseOfPrecoPropinaListNewPrecoPropina != null && !oldFkClasseOfPrecoPropinaListNewPrecoPropina.equals( classe ) )
                    {
                        oldFkClasseOfPrecoPropinaListNewPrecoPropina.getPrecoPropinaList().remove( precoPropinaListNewPrecoPropina );
                        oldFkClasseOfPrecoPropinaListNewPrecoPropina = em.merge( oldFkClasseOfPrecoPropinaListNewPrecoPropina );
                    }
                }
            }
            for ( Turma turmaListNewTurma : turmaListNew )
            {
                if ( !turmaListOld.contains( turmaListNewTurma ) )
                {
                    Classe oldFkClasseOfTurmaListNewTurma = turmaListNewTurma.getFkClasse();
                    turmaListNewTurma.setFkClasse( classe );
                    turmaListNewTurma = em.merge( turmaListNewTurma );
                    if ( oldFkClasseOfTurmaListNewTurma != null && !oldFkClasseOfTurmaListNewTurma.equals( classe ) )
                    {
                        oldFkClasseOfTurmaListNewTurma.getTurmaList().remove( turmaListNewTurma );
                        oldFkClasseOfTurmaListNewTurma = em.merge( oldFkClasseOfTurmaListNewTurma );
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
                Integer id = classe.getPkClasse();
                if ( findClasse( id ) == null )
                {
                    throw new NonexistentEntityException( "The classe with id " + id + " no longer exists." );
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
            Classe classe;
            try
            {
                classe = em.getReference( Classe.class, id );
                classe.getPkClasse();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The classe with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<PrecoPropina> precoPropinaListOrphanCheck = classe.getPrecoPropinaList();
            for ( PrecoPropina precoPropinaListOrphanCheckPrecoPropina : precoPropinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Classe (" + classe + ") cannot be destroyed since the PrecoPropina " + precoPropinaListOrphanCheckPrecoPropina + " in its precoPropinaList field has a non-nullable fkClasse field." );
            }
            List<Turma> turmaListOrphanCheck = classe.getTurmaList();
            for ( Turma turmaListOrphanCheckTurma : turmaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Classe (" + classe + ") cannot be destroyed since the Turma " + turmaListOrphanCheckTurma + " in its turmaList field has a non-nullable fkClasse field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( classe );
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

    public List<Classe> findClasseEntities()
    {
        return findClasseEntities( true, -1, -1 );
    }

    public List<Classe> findClasseEntities( int maxResults, int firstResult )
    {
        return findClasseEntities( false, maxResults, firstResult );
    }

    private List<Classe> findClasseEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Classe.class ) );
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

    public Classe findClasse( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Classe.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getClasseCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Classe> rt = cq.from( Classe.class );
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
