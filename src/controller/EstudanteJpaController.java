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
import entity.Encarregado;
import entity.Municipio;
import entity.Sexo;
import entity.Nota;
import java.util.ArrayList;
import java.util.List;
import entity.ReciboPropina;
import entity.ConfirmacaoMatricula;
import entity.Estudante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mac
 */
public class EstudanteJpaController implements Serializable
{

    public EstudanteJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Estudante estudante )
    {
        if ( estudante.getNotaList() == null )
        {
            estudante.setNotaList( new ArrayList<Nota>() );
        }
        if ( estudante.getReciboPropinaList() == null )
        {
            estudante.setReciboPropinaList( new ArrayList<ReciboPropina>() );
        }
        if ( estudante.getConfirmacaoMatriculaList() == null )
        {
            estudante.setConfirmacaoMatriculaList( new ArrayList<ConfirmacaoMatricula>() );
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Encarregado fkEncarregado = estudante.getFkEncarregado();
            if ( fkEncarregado != null )
            {
                fkEncarregado = em.getReference( fkEncarregado.getClass(), fkEncarregado.getPkEncarregado() );
                estudante.setFkEncarregado( fkEncarregado );
            }
            Municipio fkMunicipio = estudante.getFkMunicipio();
            if ( fkMunicipio != null )
            {
                fkMunicipio = em.getReference( fkMunicipio.getClass(), fkMunicipio.getPkmunicipio() );
                estudante.setFkMunicipio( fkMunicipio );
            }
            Sexo fkSexo = estudante.getFkSexo();
            if ( fkSexo != null )
            {
                fkSexo = em.getReference( fkSexo.getClass(), fkSexo.getPkSexo() );
                estudante.setFkSexo( fkSexo );
            }
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for ( Nota notaListNotaToAttach : estudante.getNotaList() )
            {
                notaListNotaToAttach = em.getReference( notaListNotaToAttach.getClass(), notaListNotaToAttach.getPkNota() );
                attachedNotaList.add( notaListNotaToAttach );
            }
            estudante.setNotaList( attachedNotaList );
            List<ReciboPropina> attachedReciboPropinaList = new ArrayList<ReciboPropina>();
            for ( ReciboPropina reciboPropinaListReciboPropinaToAttach : estudante.getReciboPropinaList() )
            {
                reciboPropinaListReciboPropinaToAttach = em.getReference( reciboPropinaListReciboPropinaToAttach.getClass(), reciboPropinaListReciboPropinaToAttach.getPkReciboPropina() );
                attachedReciboPropinaList.add( reciboPropinaListReciboPropinaToAttach );
            }
            estudante.setReciboPropinaList( attachedReciboPropinaList );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaList = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatriculaToAttach : estudante.getConfirmacaoMatriculaList() )
            {
                confirmacaoMatriculaListConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaList.add( confirmacaoMatriculaListConfirmacaoMatriculaToAttach );
            }
            estudante.setConfirmacaoMatriculaList( attachedConfirmacaoMatriculaList );
            em.persist( estudante );
            if ( fkEncarregado != null )
            {
                fkEncarregado.getEstudanteList().add( estudante );
                fkEncarregado = em.merge( fkEncarregado );
            }
            if ( fkMunicipio != null )
            {
                fkMunicipio.getEstudanteList().add( estudante );
                fkMunicipio = em.merge( fkMunicipio );
            }
            if ( fkSexo != null )
            {
                fkSexo.getEstudanteList().add( estudante );
                fkSexo = em.merge( fkSexo );
            }
            for ( Nota notaListNota : estudante.getNotaList() )
            {
                Estudante oldFkEstudanteOfNotaListNota = notaListNota.getFkEstudante();
                notaListNota.setFkEstudante( estudante );
                notaListNota = em.merge( notaListNota );
                if ( oldFkEstudanteOfNotaListNota != null )
                {
                    oldFkEstudanteOfNotaListNota.getNotaList().remove( notaListNota );
                    oldFkEstudanteOfNotaListNota = em.merge( oldFkEstudanteOfNotaListNota );
                }
            }
            for ( ReciboPropina reciboPropinaListReciboPropina : estudante.getReciboPropinaList() )
            {
                Estudante oldFkEstudanteOfReciboPropinaListReciboPropina = reciboPropinaListReciboPropina.getFkEstudante();
                reciboPropinaListReciboPropina.setFkEstudante( estudante );
                reciboPropinaListReciboPropina = em.merge( reciboPropinaListReciboPropina );
                if ( oldFkEstudanteOfReciboPropinaListReciboPropina != null )
                {
                    oldFkEstudanteOfReciboPropinaListReciboPropina.getReciboPropinaList().remove( reciboPropinaListReciboPropina );
                    oldFkEstudanteOfReciboPropinaListReciboPropina = em.merge( oldFkEstudanteOfReciboPropinaListReciboPropina );
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListConfirmacaoMatricula : estudante.getConfirmacaoMatriculaList() )
            {
                Estudante oldFkEstudanteOfConfirmacaoMatriculaListConfirmacaoMatricula = confirmacaoMatriculaListConfirmacaoMatricula.getFkEstudante();
                confirmacaoMatriculaListConfirmacaoMatricula.setFkEstudante( estudante );
                confirmacaoMatriculaListConfirmacaoMatricula = em.merge( confirmacaoMatriculaListConfirmacaoMatricula );
                if ( oldFkEstudanteOfConfirmacaoMatriculaListConfirmacaoMatricula != null )
                {
                    oldFkEstudanteOfConfirmacaoMatriculaListConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListConfirmacaoMatricula );
                    oldFkEstudanteOfConfirmacaoMatriculaListConfirmacaoMatricula = em.merge( oldFkEstudanteOfConfirmacaoMatriculaListConfirmacaoMatricula );
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

    public void edit( Estudante estudante ) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudante persistentEstudante = em.find( Estudante.class, estudante.getPkEstudante() );
            Encarregado fkEncarregadoOld = persistentEstudante.getFkEncarregado();
            Encarregado fkEncarregadoNew = estudante.getFkEncarregado();
            Municipio fkMunicipioOld = persistentEstudante.getFkMunicipio();
            Municipio fkMunicipioNew = estudante.getFkMunicipio();
            Sexo fkSexoOld = persistentEstudante.getFkSexo();
            Sexo fkSexoNew = estudante.getFkSexo();
            List<Nota> notaListOld = persistentEstudante.getNotaList();
            List<Nota> notaListNew = estudante.getNotaList();
            List<ReciboPropina> reciboPropinaListOld = persistentEstudante.getReciboPropinaList();
            List<ReciboPropina> reciboPropinaListNew = estudante.getReciboPropinaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOld = persistentEstudante.getConfirmacaoMatriculaList();
            List<ConfirmacaoMatricula> confirmacaoMatriculaListNew = estudante.getConfirmacaoMatriculaList();
            List<String> illegalOrphanMessages = null;
            for ( Nota notaListOldNota : notaListOld )
            {
                if ( !notaListNew.contains( notaListOldNota ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain Nota " + notaListOldNota + " since its fkEstudante field is not nullable." );
                }
            }
            for ( ReciboPropina reciboPropinaListOldReciboPropina : reciboPropinaListOld )
            {
                if ( !reciboPropinaListNew.contains( reciboPropinaListOldReciboPropina ) )
                {
                    if ( illegalOrphanMessages == null )
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add( "You must retain ReciboPropina " + reciboPropinaListOldReciboPropina + " since its fkEstudante field is not nullable." );
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
                    illegalOrphanMessages.add( "You must retain ConfirmacaoMatricula " + confirmacaoMatriculaListOldConfirmacaoMatricula + " since its fkEstudante field is not nullable." );
                }
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            if ( fkEncarregadoNew != null )
            {
                fkEncarregadoNew = em.getReference( fkEncarregadoNew.getClass(), fkEncarregadoNew.getPkEncarregado() );
                estudante.setFkEncarregado( fkEncarregadoNew );
            }
            if ( fkMunicipioNew != null )
            {
                fkMunicipioNew = em.getReference( fkMunicipioNew.getClass(), fkMunicipioNew.getPkmunicipio() );
                estudante.setFkMunicipio( fkMunicipioNew );
            }
            if ( fkSexoNew != null )
            {
                fkSexoNew = em.getReference( fkSexoNew.getClass(), fkSexoNew.getPkSexo() );
                estudante.setFkSexo( fkSexoNew );
            }
            List<Nota> attachedNotaListNew = new ArrayList<Nota>();
            for ( Nota notaListNewNotaToAttach : notaListNew )
            {
                notaListNewNotaToAttach = em.getReference( notaListNewNotaToAttach.getClass(), notaListNewNotaToAttach.getPkNota() );
                attachedNotaListNew.add( notaListNewNotaToAttach );
            }
            notaListNew = attachedNotaListNew;
            estudante.setNotaList( notaListNew );
            List<ReciboPropina> attachedReciboPropinaListNew = new ArrayList<ReciboPropina>();
            for ( ReciboPropina reciboPropinaListNewReciboPropinaToAttach : reciboPropinaListNew )
            {
                reciboPropinaListNewReciboPropinaToAttach = em.getReference( reciboPropinaListNewReciboPropinaToAttach.getClass(), reciboPropinaListNewReciboPropinaToAttach.getPkReciboPropina() );
                attachedReciboPropinaListNew.add( reciboPropinaListNewReciboPropinaToAttach );
            }
            reciboPropinaListNew = attachedReciboPropinaListNew;
            estudante.setReciboPropinaList( reciboPropinaListNew );
            List<ConfirmacaoMatricula> attachedConfirmacaoMatriculaListNew = new ArrayList<ConfirmacaoMatricula>();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach : confirmacaoMatriculaListNew )
            {
                confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach = em.getReference( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getClass(), confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach.getPkConfirmacaoMatricula() );
                attachedConfirmacaoMatriculaListNew.add( confirmacaoMatriculaListNewConfirmacaoMatriculaToAttach );
            }
            confirmacaoMatriculaListNew = attachedConfirmacaoMatriculaListNew;
            estudante.setConfirmacaoMatriculaList( confirmacaoMatriculaListNew );
            estudante = em.merge( estudante );
            if ( fkEncarregadoOld != null && !fkEncarregadoOld.equals( fkEncarregadoNew ) )
            {
                fkEncarregadoOld.getEstudanteList().remove( estudante );
                fkEncarregadoOld = em.merge( fkEncarregadoOld );
            }
            if ( fkEncarregadoNew != null && !fkEncarregadoNew.equals( fkEncarregadoOld ) )
            {
                fkEncarregadoNew.getEstudanteList().add( estudante );
                fkEncarregadoNew = em.merge( fkEncarregadoNew );
            }
            if ( fkMunicipioOld != null && !fkMunicipioOld.equals( fkMunicipioNew ) )
            {
                fkMunicipioOld.getEstudanteList().remove( estudante );
                fkMunicipioOld = em.merge( fkMunicipioOld );
            }
            if ( fkMunicipioNew != null && !fkMunicipioNew.equals( fkMunicipioOld ) )
            {
                fkMunicipioNew.getEstudanteList().add( estudante );
                fkMunicipioNew = em.merge( fkMunicipioNew );
            }
            if ( fkSexoOld != null && !fkSexoOld.equals( fkSexoNew ) )
            {
                fkSexoOld.getEstudanteList().remove( estudante );
                fkSexoOld = em.merge( fkSexoOld );
            }
            if ( fkSexoNew != null && !fkSexoNew.equals( fkSexoOld ) )
            {
                fkSexoNew.getEstudanteList().add( estudante );
                fkSexoNew = em.merge( fkSexoNew );
            }
            for ( Nota notaListNewNota : notaListNew )
            {
                if ( !notaListOld.contains( notaListNewNota ) )
                {
                    Estudante oldFkEstudanteOfNotaListNewNota = notaListNewNota.getFkEstudante();
                    notaListNewNota.setFkEstudante( estudante );
                    notaListNewNota = em.merge( notaListNewNota );
                    if ( oldFkEstudanteOfNotaListNewNota != null && !oldFkEstudanteOfNotaListNewNota.equals( estudante ) )
                    {
                        oldFkEstudanteOfNotaListNewNota.getNotaList().remove( notaListNewNota );
                        oldFkEstudanteOfNotaListNewNota = em.merge( oldFkEstudanteOfNotaListNewNota );
                    }
                }
            }
            for ( ReciboPropina reciboPropinaListNewReciboPropina : reciboPropinaListNew )
            {
                if ( !reciboPropinaListOld.contains( reciboPropinaListNewReciboPropina ) )
                {
                    Estudante oldFkEstudanteOfReciboPropinaListNewReciboPropina = reciboPropinaListNewReciboPropina.getFkEstudante();
                    reciboPropinaListNewReciboPropina.setFkEstudante( estudante );
                    reciboPropinaListNewReciboPropina = em.merge( reciboPropinaListNewReciboPropina );
                    if ( oldFkEstudanteOfReciboPropinaListNewReciboPropina != null && !oldFkEstudanteOfReciboPropinaListNewReciboPropina.equals( estudante ) )
                    {
                        oldFkEstudanteOfReciboPropinaListNewReciboPropina.getReciboPropinaList().remove( reciboPropinaListNewReciboPropina );
                        oldFkEstudanteOfReciboPropinaListNewReciboPropina = em.merge( oldFkEstudanteOfReciboPropinaListNewReciboPropina );
                    }
                }
            }
            for ( ConfirmacaoMatricula confirmacaoMatriculaListNewConfirmacaoMatricula : confirmacaoMatriculaListNew )
            {
                if ( !confirmacaoMatriculaListOld.contains( confirmacaoMatriculaListNewConfirmacaoMatricula ) )
                {
                    Estudante oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula = confirmacaoMatriculaListNewConfirmacaoMatricula.getFkEstudante();
                    confirmacaoMatriculaListNewConfirmacaoMatricula.setFkEstudante( estudante );
                    confirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( confirmacaoMatriculaListNewConfirmacaoMatricula );
                    if ( oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula != null && !oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula.equals( estudante ) )
                    {
                        oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula.getConfirmacaoMatriculaList().remove( confirmacaoMatriculaListNewConfirmacaoMatricula );
                        oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula = em.merge( oldFkEstudanteOfConfirmacaoMatriculaListNewConfirmacaoMatricula );
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
                Integer id = estudante.getPkEstudante();
                if ( findEstudante( id ) == null )
                {
                    throw new NonexistentEntityException( "The estudante with id " + id + " no longer exists." );
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
            Estudante estudante;
            try
            {
                estudante = em.getReference( Estudante.class, id );
                estudante.getPkEstudante();
            }
            catch ( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException( "The estudante with id " + id + " no longer exists.", enfe );
            }
            List<String> illegalOrphanMessages = null;
            List<Nota> notaListOrphanCheck = estudante.getNotaList();
            for ( Nota notaListOrphanCheckNota : notaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Estudante (" + estudante + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable fkEstudante field." );
            }
            List<ReciboPropina> reciboPropinaListOrphanCheck = estudante.getReciboPropinaList();
            for ( ReciboPropina reciboPropinaListOrphanCheckReciboPropina : reciboPropinaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Estudante (" + estudante + ") cannot be destroyed since the ReciboPropina " + reciboPropinaListOrphanCheckReciboPropina + " in its reciboPropinaList field has a non-nullable fkEstudante field." );
            }
            List<ConfirmacaoMatricula> confirmacaoMatriculaListOrphanCheck = estudante.getConfirmacaoMatriculaList();
            for ( ConfirmacaoMatricula confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula : confirmacaoMatriculaListOrphanCheck )
            {
                if ( illegalOrphanMessages == null )
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add( "This Estudante (" + estudante + ") cannot be destroyed since the ConfirmacaoMatricula " + confirmacaoMatriculaListOrphanCheckConfirmacaoMatricula + " in its confirmacaoMatriculaList field has a non-nullable fkEstudante field." );
            }
            if ( illegalOrphanMessages != null )
            {
                throw new IllegalOrphanException( illegalOrphanMessages );
            }
            Encarregado fkEncarregado = estudante.getFkEncarregado();
            if ( fkEncarregado != null )
            {
                fkEncarregado.getEstudanteList().remove( estudante );
                fkEncarregado = em.merge( fkEncarregado );
            }
            Municipio fkMunicipio = estudante.getFkMunicipio();
            if ( fkMunicipio != null )
            {
                fkMunicipio.getEstudanteList().remove( estudante );
                fkMunicipio = em.merge( fkMunicipio );
            }
            Sexo fkSexo = estudante.getFkSexo();
            if ( fkSexo != null )
            {
                fkSexo.getEstudanteList().remove( estudante );
                fkSexo = em.merge( fkSexo );
            }
            em.remove( estudante );
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

    public List<Estudante> findEstudanteEntities()
    {
        return findEstudanteEntities( true, -1, -1 );
    }

    public List<Estudante> findEstudanteEntities( int maxResults, int firstResult )
    {
        return findEstudanteEntities( false, maxResults, firstResult );
    }

    private List<Estudante> findEstudanteEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select( cq.from( Estudante.class ) );
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

    public Estudante findEstudante( Integer id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find( Estudante.class, id );
        }
        finally
        {
            em.close();
        }
    }

    public int getEstudanteCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudante> rt = cq.from( Estudante.class );
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
