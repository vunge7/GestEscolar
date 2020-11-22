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
import entity.ReciboPropina;
import java.util.ArrayList;
import java.util.List;
import entity.ConfirmacaoMatricula;
import entity.PrecoPropina;
import entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class UsuarioJpaController implements Serializable
{

    public UsuarioJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Usuario usuario )
    {
        if ( usuario.getReciboPropinaList() == null )
        {
            usuario.setReciboPropinaList( new ArrayList<ReciboPropina>() );
        }
        if ( usuario.getConfirmacaoMatriculaList() == null )
        {
            usuario.setConfirmacaoMatriculaList( new ArrayList<ConfirmacaoMatricula>() );
        }
        if ( usuario.getPrecoPropinaList() == null )
        {
            usuario.setPrecoPropinaList( new ArrayList<PrecoPropina>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ReciboPropina> attachedReciboPropinaList = new ArrayList<ReciboPropina>();
            for ( ReciboPropina reciboPropinaListReciboPropinaToAttach : usuario.getReciboPropinaList() )
            {
                reciboPropinaListReciboPropinaToAttach = em.getReference( reciboPropinaListReciboPropinaToAttach.getClass(), reciboPropinaListReciboPropinaToAttach.getPkReciboPropina() );
                attachedReciboPropinaList.add( reciboPropinaListReciboPropinaToAttach );
            }
            usuario.setReciboPropinaList( attachedReciboPropinaList );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaList = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatriculaToAttach : usuario.getConfirmacaoMatriculaList() )
            {
                confirmacaoMatriculaListConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaList.add( confirmacaoMatriculaListConfirmacaoMatriculaToAttach );
            }
            usuario.setConfirmacaoMatriculaList( attachedConfirmacaoMatriculaList );
            List<PrecoPropina> attachedPrecoPropinaList = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListPrecoPropinaToAttach : usuario.getPrecoPropinaList() )
            {
                precoPropinaListPrecoPropinaToAttach = em.getReference( precoPropinaListPrecoPropinaToAttach.getClass(), precoPropinaListPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaList.add( precoPropinaListPrecoPropinaToAttach );
            }
            usuario.setPrecoPropinaList( attachedPrecoPropinaList );
            em.persist( usuario );
            for ( ReciboPropina reciboPropinaListReciboPropina : usuario.getReciboPropinaList() )
            {
                Usuario oldFkUsuarioOfReciboPropinaListReciboPropina = reciboPropinaListReciboPropina.getFkUsuario();
                reciboPropinaListReciboPropina.setFkUsuario( usuario );
                reciboPropinaListReciboPropina = em.merge( reciboPropinaListReciboPropina );
                if ( oldFkUsuarioOfReciboPropinaListReciboPropina != null )
                {
                    oldFkUsuarioOfReciboPropinaListReciboPropina.getReciboPropinaList().remove( reciboPropinaListReciboPropina );
                    oldFkUsuarioOfReciboPropinaListReciboPropina = em.merge( oldFkUsuarioOfReciboPropinaListReciboPropina );
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatricula : usuario.getConfirmacaoMatriculaList() )
            {
                Usuario oldFkUsuarioOfConfirmacaoMatriculaListConfirmacaoMatricula = confirmacaoMatriculaListConfirmacaoMatricula.getFkUsuario();
                confirmacaoMatriculaListConfirmacaoMatricula.setFkUsuario( usuario );
                confirmacaoMatriculaListConfirmacaoMatricula = em.merge( confirmacaoMatriculaListConfirmacaoMatricula );
                if ( oldFkUsuarioOfConfirmacaoMatriculaListConfirmacaoMatricula != null )
                {
                    oldFkUsuarioOfConfirmacaoMatriculaListConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListConfirmacaoMatricula );
                    oldFkUsuarioOfConfirmacaoMatriculaListConfirmacaoMatricula = em.merge( oldFkUsuarioOfConfirmacaoMatriculaListConfirmacaoMatricula );
                }
            }
            for ( PrecoPropina precoPropinaListPrecoPropina : usuario.getPrecoPropinaList() )
            {
                Usuario oldFkUsuarioOfPrecoPropinaListPrecoPropina = precoPropinaListPrecoPropina.getFkUsuario();
                precoPropinaListPrecoPropina.setFkUsuario( usuario );
                precoPropinaListPrecoPropina = em.merge( precoPropinaListPrecoPropina );
                if ( oldFkUsuarioOfPrecoPropinaListPrecoPropina != null )
                {
                    oldFkUsuarioOfPrecoPropinaListPrecoPropina.getPrecoPropinaList().remove( precoPropinaListPrecoPropina );
                    oldFkUsuarioOfPrecoPropinaListPrecoPropina = em.merge( oldFkUsuarioOfPrecoPropinaListPrecoPropina );
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

    public void edit( Usuario usuario ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find( Usuario.class, usuario.getPkUsuario() );
            List<ReciboPropina> reciboPropinaListOld = persistentUsuario.getReciboPropinaList();
            List<ReciboPropina> reciboPropinaListNew = usuario.getReciboPropinaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOld = persistentUsuario.getConfirmacaoMatriculaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListNew = usuario.getConfirmacaoMatriculaList();
            List<PrecoPropina> precoPropinaListOld = persistentUsuario.getPrecoPropinaList();
            List<PrecoPropina> precoPropinaListNew = usuario.getPrecoPropinaList();
            List<String> illegalOrphanMessages = null;
            for ( ReciboPropina reciboPropinaListOldReciboPropina : reciboPropinaListOld )
            {
                if ( !reciboPropinaListNew.contains( reciboPropinaListOldReciboPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ReciboPropina " + reciboPropinaListOldReciboPropina + " since its fkUsuario field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain ConfirmacaoMatricula " + confirmacaoMatriculaListOldConfirmacaoMatricula + " since its fkUsuario field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain PrecoPropina " + precoPropinaListOldPrecoPropina + " since its fkUsuario field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            List<ReciboPropina> attachedReciboPropinaListNew = new ArrayList<ReciboPropina>();
            for ( ReciboPropina reciboPropinaListNewReciboPropinaToAttach : reciboPropinaListNew )
            {
                reciboPropinaListNewReciboPropinaToAttach = em.getReference( reciboPropinaListNewReciboPropinaToAttach.getClass(), reciboPropinaListNewReciboPropinaToAttach.getPkReciboPropina() );
                attachedReciboPropinaListNew.add( reciboPropinaListNewReciboPropinaToAttach );
            }
            reciboPropinaListNew = attachedReciboPropinaListNew;
            usuario.setReciboPropinaList( reciboPropinaListNew );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaListNew = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach : confirmacaoMatriculaListNew )
            {
                confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaListNew.add( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach );
            }
            confirmacaoMatriculaListNew = attachedConfirmacaoMatriculaListNew;
            usuario.setConfirmacaoMatriculaList( confirmacaoMatriculaListNew );
            List<PrecoPropina> attachedPrecoPropinaListNew = new ArrayList<PrecoPropina>();
            for ( PrecoPropina precoPropinaListNewPrecoPropinaToAttach : precoPropinaListNew )
            {
                precoPropinaListNewPrecoPropinaToAttach = em.getReference( precoPropinaListNewPrecoPropinaToAttach.getClass(), precoPropinaListNewPrecoPropinaToAttach.getPkPrecoPropina() );
                attachedPrecoPropinaListNew.add( precoPropinaListNewPrecoPropinaToAttach );
            }
            precoPropinaListNew = attachedPrecoPropinaListNew;
            usuario.setPrecoPropinaList( precoPropinaListNew );
            usuario = em.merge( usuario );
            for ( ReciboPropina reciboPropinaListNewReciboPropina : reciboPropinaListNew )
            {
                if ( !reciboPropinaListOld.contains( reciboPropinaListNewReciboPropina ) )
                {
                    Usuario oldFkUsuarioOfReciboPropinaListNewReciboPropina = reciboPropinaListNewReciboPropina.getFkUsuario();
                    reciboPropinaListNewReciboPropina.setFkUsuario( usuario );
                    reciboPropinaListNewReciboPropina = em.merge( reciboPropinaListNewReciboPropina );
                    if ( oldFkUsuarioOfReciboPropinaListNewReciboPropina != null && !oldFkUsuarioOfReciboPropinaListNewReciboPropina.equals( usuario ) )
                    {
                        oldFkUsuarioOfReciboPropinaListNewReciboPropina.getReciboPropinaList().remove( reciboPropinaListNewReciboPropina );
                        oldFkUsuarioOfReciboPropinaListNewReciboPropina = em.merge( oldFkUsuarioOfReciboPropinaListNewReciboPropina );
                    }
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatricula : confirmacaoMatriculaListNew )
            {
                if ( !confirmacaoMatriculaListOld.contains( confirmacaoMatriculaListNewConfirmacaoMatricula ) )
                {
                    Usuario oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula = confirmacaoMatriculaListNewConfirmacaoMatricula.getFkUsuario();
                    confirmacaoMatriculaListNewConfirmacaoMatricula.setFkUsuario( usuario );
                    confirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( confirmacaoMatriculaListNewConfirmacaoMatricula );
                    if ( oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula != null && !oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula.equals( usuario ) )
                    {
                        oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListNewConfirmacaoMatricula );
                        oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( oldFkUsuarioOfConfirmacaoMatriculaListNewConfirmacaoMatricula );
                    }
                }
            }
            for ( PrecoPropina precoPropinaListNewPrecoPropina : precoPropinaListNew )
            {
                if ( !precoPropinaListOld.contains( precoPropinaListNewPrecoPropina ) )
                {
                    Usuario oldFkUsuarioOfPrecoPropinaListNewPrecoPropina = precoPropinaListNewPrecoPropina.getFkUsuario();
                    precoPropinaListNewPrecoPropina.setFkUsuario( usuario );
                    precoPropinaListNewPrecoPropina = em.merge( precoPropinaListNewPrecoPropina );
                    if ( oldFkUsuarioOfPrecoPropinaListNewPrecoPropina != null && !oldFkUsuarioOfPrecoPropinaListNewPrecoPropina.equals( usuario ) )
                    {
                        oldFkUsuarioOfPrecoPropinaListNewPrecoPropina.getPrecoPropinaList().remove( precoPropinaListNewPrecoPropina );
                        oldFkUsuarioOfPrecoPropinaListNewPrecoPropina = em.merge( oldFkUsuarioOfPrecoPropinaListNewPrecoPropina );
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
                Integer id = usuario.getPkUsuario();
                if ( findUsuario( id ) == null )
                {
                    throw new NonexistentEntityException( "The usuario with id " + id + " no longer exists." );
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
            Usuario usuario;
            try
            {
                usuario = em.getReference( Usuario.class, id );
                usuario.getPkUsuario();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The usuario with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<ReciboPropina> reciboPropinaListOrphanCheck = usuario.getReciboPropinaList();
            for ( ReciboPropina reciboPropinaListOrphanCheckReciboPropina : reciboPropinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Usuario (" + usuario + ") cannot be destroyed since the ReciboPropina " + reciboPropinaListOrphanCheckReciboPropina + " in its reciboPropinaList field has a non-nullable fkUsuario field." );
            }
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOrphanCheck = usuario.getConfirmacaoMatriculaList();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula : confirmacaoMatriculaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Usuario (" + usuario + ") cannot be destroyed since the ConfirmacaoMatricula " + confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula + " in its confirmacaoMatriculaList field has a non-nullable fkUsuario field." );
            }
            List<PrecoPropina> precoPropinaListOrphanCheck = usuario.getPrecoPropinaList();
            for ( PrecoPropina precoPropinaListOrphanCheckPrecoPropina : precoPropinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Usuario (" + usuario + ") cannot be destroyed since the PrecoPropina " + precoPropinaListOrphanCheckPrecoPropina + " in its precoPropinaList field has a non-nullable fkUsuario field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            em.remove( usuario );
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

    public List<Usuario> findUsuarioEntities()
    {
        return findUsuarioEntities( true, -1, -1 );
    }

    public List<Usuario> findUsuarioEntities( int maxResults, int firstResult )
    {
        return findUsuarioEntities( false, maxResults, firstResult );
    }

    private List<Usuario> findUsuarioEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Usuario.class ) );
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

    public Usuario findUsuario( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Usuario.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getUsuarioCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from( Usuario.class );
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
