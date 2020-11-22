/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.NotaJpaController;
import entity.Nota;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class NotaDao extends NotaJpaController
{

    public NotaDao( EntityManagerFactory emf )
    {
        super( emf );
    }

    public Vector<String> buscaTodos()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p" );
        Vector<String> result = ( Vector ) query.getResultList();

        result.add( 0, "-- SELECIONE --" );
        return result;
    }

    public List<Nota> buscaTodasNotas()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p" );
        return query.getResultList();
    }

//      public Vector <String > buscaTodosNotaByFuncionario(int pkFuncionario) 
//     {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT p FROM Nota p WHERE p.fkFuncionario.pkFuncionario = :pkFuncionario")
//                    .setParameter("pkFuncionario", pkFuncionario);
//            List<Nota> lista =  query.getResultList();
//            Vector<String>   lista_aux = new Vector<>();
//            
//            lista_aux.add(0,"--SELECIONE--");
//            
//            for (int i = 0; i < lista.size(); i++) {
//                       lista_aux.add(  lista.get(i).getFkModelo().getDesignacao()  +" (" + lista.get(i).getMatricula() +")"  );
//             
//            }
//            
//            return lista_aux;
//    }
//      
    public List<Nota> buscaTodosNotaByModelo( int pkModelo )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p WHERE p.fkModelo.pkModelo = :pkModelo" )
                .setParameter( "pkModelo", pkModelo );
        return query.getResultList();
    }

    public String getMatriculaById( long idNota )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.pkProduto = :idNota" )
                .setParameter( "idNota", idNota );

        List list = query.getResultList();

        if ( list != null )
        {
            return String.valueOf( list.get( 0 ) );
        }
        return "";
    }

    public Nota getNotaByMatricula( String valor )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p WHERE p.valor = :valor" )
                .setParameter( "valor", valor );

        List<Nota> list = query.getResultList();

        if ( !list.isEmpty() )
        {
            return list.get( 0 );
        }
        return new Nota( 0 );
    }

    public Nota getNotaByModelo( String valor )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p WHERE p.fkModelo.valor = :valor" )
                .setParameter( "valor", valor );

        List<Nota> list = query.getResultList();

        if ( !list.isEmpty() )
        {
            return list.get( 0 );
        }
        return new Nota( 0 );
    }

    public List<Nota> getDescricaoByIdFuncionario( int idTipoProduto )
    {
        System.out.println( "cheguei aqui Necia :" + idTipoProduto );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkTipoProduto.pkTipoNota = :idTipoProduto" )
                .setParameter( "idTipoProduto", idTipoProduto );

        return query.getResultList();
    }

    public List<Nota> getAllNotaByIdTipoProduto( int idtipoProduto )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota" );

        List<Nota> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }

        return null;

    }

    public boolean exist_nota( Double valor )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p WHERE p.valor = :valor" )
                .setParameter( "valor", valor );

        List result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return true;
        }
        return false;
    }

    public List<String> getAllNota()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p  ORDER BY p.valor " );
        List<String> lista = query.getResultList();
        lista.add( 0, "-- Seleccione --" );
        return lista;

    }

    public List<Nota> getProdutoByDesgnacao( String valor )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE  p.valor LIKE :valor ORDER BY p.valor" )
                .setParameter( "valor", valor + "%" );
        List<Nota> result = query.getResultList();
        em.close();
        if ( result != null )
        {
            return result;
        }
        return null;
    }

    public int getIdProdutoByNome( String valor )
    {

        System.out.println( "NOME DO PRODUTO " + valor );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Nota p WHERE p.valor = :valor" )
                .setParameter( "valor", valor );

        List<Nota> result = query.getResultList();
        em.close();
        if ( !result.isEmpty() )
        {
            return result.get( 0 ).getPkNota();
        }
        return 0;

    }

    public List<Nota> getAllNotaByFuncionario( int pkFuncionario )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Nota s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.valor ASC" )
                .setParameter( "pkFuncionario", pkFuncionario );
        List<Nota> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    public List<Nota> getAllNotaByModelo( int pkModelo )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Nota s WHERE s.fkModelo.pkModelo = :pkModelo ORDER BY s.valor ASC" )
                .setParameter( "pkModelo", pkModelo );
        List<Nota> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    public int getIdByDescricao( String valor )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT f.pkNota FROM Nota f WHERE f.valor = :valor" )
                .setParameter( "valor", valor );

        List result = query.getResultList();

        if ( result != null )
        {

            return Integer.parseInt( String.valueOf( result.get( 0 ) ) );
        }
        return 0;

    }

    public Vector<String> getDesigncaoByIdAnoLectivo( int idAnoLectivo )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkAnoLectivo.pkAnoLectivo = :idAnoLectivo" )
                .setParameter( "pk_ano_lectivo", idAnoLectivo );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdSala( int idSala )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkSala.pkSala = :idSala" )
                .setParameter( "pkSala", idSala );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdTurno( int idTurno )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkTurno.pkTurno = :idTurno" )
                .setParameter( "pkTurno", idTurno );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdCurso( int idCurso )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkCurso.pkCurso = :idCurso" )
                .setParameter( "pkCurso", idCurso );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdClasse( int idClasse )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkClasse.pkClasse = :idClasse" )
                .setParameter( "pkClasse", idClasse );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdTipoNota( int idTipoNota )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.valor FROM Nota p WHERE p.fkTipoNota.pkTipoNota = :idTipoNota" )
                .setParameter( "pkTipoNota", idTipoNota );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }
    
    //$$-domingos_dala_vunge
    public List<Nota> getAllNotaEstudanteByIdDiciplinaAndAnoLectivoAndIdEstudante( int pkDiciplina, int idAnoLectivo, int idEstudante )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Nota s WHERE s.fkDisciplina.pkDisciplina = :pkDiciplina AND s.fkAnoLectivo.pkAnoLectivo = :idAnoLectivo AND s.fkEstudante.pkEstudante = :idEstudante ORDER BY s.fkTrimestre.pkSemestre ASC" )
                .setParameter( "pkDiciplina", pkDiciplina )
                .setParameter( "idAnoLectivo", idAnoLectivo )
                .setParameter( "idEstudante", idEstudante );
        List<Nota> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;
    }
    
}
