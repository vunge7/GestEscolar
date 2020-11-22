/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.EstudanteJpaController;
import entity.Estudante;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author mac
 */
public class EstudanteDao extends EstudanteJpaController
{

    public EstudanteDao( EntityManagerFactory emf )
    {
        super( emf );
    }

    public Vector<String> buscaTodos()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u.nomeCompleto FROM Estudante u" );
        Vector<String> result = ( Vector ) query.getResultList();
        result.add( 0, "-- SELECIONE --" );
        return result;
    }

    public Vector<String> buscaTodosLicenca2()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u.designacao FROM Estudante u" );
        Vector<String> result = ( Vector ) query.getResultList();
        result.add( 0, "-- SELECIONE --" );
        return result;
    }

    public List<Estudante> buscaTodosEstudante()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Estudante u  ORDER BY u.nomeCompleto ASC" );
        return query.getResultList();
    }

    public List<Estudante> buscaTodosEstudanteByIdCategoria( int pkCategoria )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Estudante u  WHERE u.fkCategoria.pkCategoria = :pkCategoria AND u.status = true ORDER BY u.nomeCompleto ASC" )
                .setParameter( "pkCategoria", pkCategoria );
        return query.getResultList();
    }

    public List<Estudante> buscaTodosEstudanteByIdDepartamento( int pkDepartamento )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Estudante u WHERE u.fkDepartamento.pkDepartamento = :pkDepartamento ORDER BY u.nomeCompleto ASC " )
                .setParameter( "pkDepartamento", pkDepartamento );
        return query.getResultList();
    }

    public boolean exist_estudante( String nbi )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Estudante u WHERE u.nbi = :nbi" )
                .setParameter( "nbi", nbi );

        List result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return true;
        }
        return false;
    }

    public List<Estudante> getALLEstudanteByNome( String nomeCompleto )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a.fkEstudante FROM Admissao a WHERE   a.fkEstudante.nomeCompleto  LIKE :nomeCompleto AND a.fkEstudante.status = true ORDER BY a.fkEstudante.nomeCompleto" )
                .setParameter( "nomeCompleto", nomeCompleto + "%" );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( result != null )
        {
            return result;
        }
        return null;

    }

    public List<Estudante> getALLEstudante_LIKE_nomeCompleto( String nomeCompleto )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE a.nomeCompleto  LIKE :nomeCompleto  ORDER BY a.nomeCompleto" )
               .setParameter( "nomeCompleto", "%" + nomeCompleto + "%" );
                //.setParameter( "nomeCompleto",  nomeCompleto + "%" );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( result != null )
        {
            return result;
        }
        return null;

    }

    //getEstudanteByBI
    public Estudante getEstudanteByNome( String nomeCompleto )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE  a.nomeCompleto = :nomeCompleto" )
                .setParameter( "nomeCompleto", nomeCompleto );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( result != null )
        {
            return result.get( 0 );
        }
        return null;

    }

    //Estudante pk
    public Estudante getEstudanteByPkEstudante( int pkEstudante )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE  a.pkEstudante = :pkEstudante" )
                .setParameter( "pkEstudante", pkEstudante );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( result != null )
        {
            return result.get( 0 );
        }
        return null;

    }

    public Estudante getEstudanteByBI( String nbi )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE  a.nbi = :nbi AND a.status = true" )
                .setParameter( "nbi", nbi );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return null;

    }

    public long getNumeroGrauAcademico( int pk_departamento, int limite_inferior, int limite_maximo, int pk_carreira )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE a.fkGrauAcademico.pkGrauAcademico >= :limite_inferior AND a.fkGrauAcademico.pkGrauAcademico <= :limite_maximo  AND a.fkDepartamento.pkDepartamento = :pk_departamento AND a.fkCategoria.fkCarreira.pkCarreira <> :pk_carreira" )
                .setParameter( "pk_departamento", pk_departamento )
                .setParameter( "limite_inferior", limite_inferior )
                .setParameter( "limite_maximo", limite_maximo )
                .setParameter( "pk_carreira", pk_carreira );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public long getNumeroEstudantePorGenero( int pk_departamento, int pk_sexo )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE  a.fkDepartamento.pkDepartamento = :pk_departamento AND a.fkGenero.pkGenero = :pk_sexo" )
                .setParameter( "pk_departamento", pk_departamento )
                .setParameter( "pk_sexo", pk_sexo );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public long getNumeroEstudanteNaoDocente()
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE a.fkCategoria.fkCarreira.pkCarreira <> 3" );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public long getTotalPorGenero( int pk_sexo )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE  a.fkGenero.pkGenero = :pk_sexo" )
                .setParameter( "pk_sexo", pk_sexo );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public long getTotalPorGeneroNaoDocente( int pk_sexo )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE  a.fkGenero.pkGenero = :pk_sexo AND a.fkCategoria.fkCarreira.pkCarreira <> 3" )
                .setParameter( "pk_sexo", pk_sexo );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public long getTotalPorGeneroPorDepartamento( int pk_departamento )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a WHERE  a.fkDepartamento.pkDepartamento = :pk_departamento" )
                .setParameter( "pk_departamento", pk_departamento );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public Estudante getEstudanteByPassPort( String passport )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE a.passport = :passport AND a.status = true" )
                .setParameter( "passport", passport );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return null;

    }

    public Estudante getEstudanteByCartaConducao( String cartaConducao )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT a FROM Estudante a WHERE a.cartaConducao = :cartaConducao" )
                .setParameter( "cartaConducao", cartaConducao );
        List<Estudante> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return null;

    }

    // Total Estudantes 
    public long getTotalEstudante()
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT COUNT(a.pkEstudante) AS TOTAL FROM Estudante a" );

        List<Long> result = query.getResultList();
        em.close();

        if ( !result.isEmpty() )
        {
            return result.get( 0 );
        }
        return 0;

    }

    public int getIdByEstudante( String nomeCompleto )
    {

        System.out.println( "UNIDADE " + nomeCompleto );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Estudante u WHERE u.nomeCompleto = :nomeCompleto" )
                .setParameter( "nomeCompleto", nomeCompleto );

        List<Estudante> result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return result.get( 0 ).getPkEstudante();
        }
        return 0;

    }

}
