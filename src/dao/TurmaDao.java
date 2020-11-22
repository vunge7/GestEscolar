/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TurmaJpaController;
import entity.Turma;
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
public class TurmaDao extends TurmaJpaController
{

    public TurmaDao( EntityManagerFactory emf )
    {
        super( emf );
    }

    public Vector<String> buscaTodos()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p" );
        Vector<String> result = ( Vector ) query.getResultList();

        result.add( 0, "-- SELECIONE --" );
        return result;
    }

    public List<Turma> buscaTodosTurma()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p" );
        return query.getResultList();
    }

//      public Vector <String > buscaTodosTurmaByFuncionario(int pkFuncionario) 
//     {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT p FROM Turma p WHERE p.fkFuncionario.pkFuncionario = :pkFuncionario")
//                    .setParameter("pkFuncionario", pkFuncionario);
//            List<Turma> lista =  query.getResultList();
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
    public List<Turma> buscaTodosTurmaByModelo( int pkModelo )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p WHERE p.fkModelo.pkModelo = :pkModelo" )
                .setParameter( "pkModelo", pkModelo );
        return query.getResultList();
    }

    public String getMatriculaById( long idTurma )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.pkProduto = :idTurma" )
                .setParameter( "idTurma", idTurma );

        List list = query.getResultList();

        if ( list != null )
        {
            return String.valueOf( list.get( 0 ) );
        }
        return "";
    }

    public Turma getTurmaByDesignacao( String designacao )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p WHERE p.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List<Turma> list = query.getResultList();

        if ( !list.isEmpty() )
        {
            return list.get( 0 );
        }
        return null;
    }

    public Turma getTurmaByNome( String designacao )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p WHERE p.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List<Turma> list = query.getResultList();

        if ( !list.isEmpty() )
        {
            return list.get( 0 );
        }
        return new Turma( 0 );
    }

    public List<Turma> getDescricaoByIdFuncionario( int idTipoProduto )
    {
        System.out.println( "cheguei aqui Necia :" + idTipoProduto );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkTipoProduto.pkTipoTurma = :idTipoProduto" )
                .setParameter( "idTipoProduto", idTipoProduto );

        return query.getResultList();
    }

    public List<Turma> getAllTurmaByIdTipoProduto( int idtipoProduto )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma" );

        List<Turma> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }

        return null;

    }

    public boolean exist_turma( String designacao )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p WHERE p.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return true;
        }
        return false;
    }

    public List<String> getAllTurma()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p  ORDER BY p.designacao " );
        List<String> lista = query.getResultList();
        lista.add( 0, "-- Seleccione --" );
        return lista;

    }

    public List<Turma> getProdutoByDesgnacao( String designacao )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE  p.designacao LIKE :designacao ORDER BY p.designacao" )
                .setParameter( "designacao", designacao + "%" );
        List<Turma> result = query.getResultList();
        em.close();
        if ( result != null )
        {
            return result;
        }
        return null;
    }

    public int getIdTurmaByNome( String designacao )
    {

        System.out.println( "NOME DO PRODUTO " + designacao );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p FROM Turma p WHERE p.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List<Turma> result = query.getResultList();
        em.close();
        if ( !result.isEmpty() )
        {
            return result.get( 0 ).getPkTurma();
        }
        return 0;

    }

    public List<Turma> getAllTurmaByFuncionario( int pkFuncionario )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Turma s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.designacao ASC" )
                .setParameter( "pkFuncionario", pkFuncionario );
        List<Turma> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    public List<Turma> getAllTurmaByModelo( int pkModelo )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Turma s WHERE s.fkModelo.pkModelo = :pkModelo ORDER BY s.designacao ASC" )
                .setParameter( "pkModelo", pkModelo );
        List<Turma> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    public int getIdByDescricao1( String designacao )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT f.pkTurma FROM Turma f WHERE f.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List result = query.getResultList();

        if ( result != null )
        {

            return Integer.parseInt( String.valueOf( result.get( 0 ) ) );
        }
        return 0;

    }

    public int getIdByDescricao( String designacao )
    {

        System.out.println( "UNIDADE " + designacao );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Turma u WHERE u.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List<Turma> result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return result.get( 0 ).getPkTurma();
        }
        return 0;

    }

    public int getPkByDescricao( String designacao )
    {

        System.out.println( "UNIDADE " + designacao );
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT u FROM Turma u WHERE u.designacao = :designacao" )
                .setParameter( "designacao", designacao );

        List<Turma> result = query.getResultList();

        if ( !result.isEmpty() )
        {
            return result.get( 0 ).getPkTurma();
        }
        return 0;

    }

    public Vector<String> getDesigncaoByIdAnoLectivo( int idAnoLectivo )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkAnoLectivo.pkAnoLectivo = :idAnoLectivo" )
                .setParameter( "pk_ano_lectivo", idAnoLectivo );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdSala( int idSala )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkSala.pkSala = :idSala" )
                .setParameter( "pkSala", idSala );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdTurno( int idTurno )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkTurno.pkTurno = :idTurno" )
                .setParameter( "pkTurno", idTurno );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdCurso( int idCurso )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkCurso.pkCurso = :idCurso" )
                .setParameter( "pkCurso", idCurso );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdClasse( int idClasse )
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT p.designacao FROM Turma p WHERE p.fkClasse.pkClasse = :idClasse" )
                .setParameter( "pkClasse", idClasse );
        Vector<String> result = ( Vector ) query.getResultList();
        return result;
    }

    // query q lista todas as turmas de uma classe, curso, e ano lectivo 
    public List<Turma> getAllTurmaByClasseAnoCurso( int pkClasse, int pkAno, int pkCurso )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Turma s WHERE s.fkClasse.pkClasse = :pkClasse AND s.fkAnoLectivo.pkAnoLectivo = :pkAno AND s.fkCurso.pkCurso = :pkCurso  " )
                .setParameter( "pkClasse", pkClasse )
                .setParameter( "pkAno", pkAno )
                .setParameter( "pkCurso", pkCurso );

        List<Turma> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    // query q lista todas as turmas de uma classe, curso, e ano lectivo 
    public List<Turma> getAllEstudanteByTurmaClasseAnoCursoSalaTurno( int pkTurma, int pkClasse, int pkAno, int pkCurso, int pkSala, int pkTurno )
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery( "SELECT s FROM Turma s WHERE s.pkTurma = :pkTurma AND s.fkClasse.pkClasse = :pkClasse AND s.fkAnoLectivo.pkAnoLectivo = :pkAnoLectivo  AND s.fkCurso.pkCurso = :pkCurso AND s.fkSala.pkSala = :pkSala AND s.fkTurno.pkTurno = :pkTurno " )
                .setParameter( "pkTurma", pkTurma )
                .setParameter( "pkClasse", pkClasse )
                .setParameter( "pkAno", pkAno )
                .setParameter( "pkCurso", pkCurso )
                .setParameter( "pkSala", pkSala )
                .setParameter( "pkTurno", pkTurno );

        List<Turma> lista = query.getResultList();

        if ( !lista.isEmpty() )
        {
            return lista;
        }
        return null;

    }

    public Vector<String> buscaTodasTurmasByCursoAnoClasse( int pkClasse, int pkAno, int pkCurso )
    {
        EntityManager em = getEntityManager();
        Query query;
        query = em.createQuery( "SELECT s.designacao FROM Turma s WHERE s.fkClasse.pkClasse = :pkClasse AND s.fkAnoLectivo.pkAnoLectivo = :pkAno AND s.fkCurso.pkCurso = :pkCurso  " )
                .setParameter( "pkClasse", pkClasse )
                .setParameter( "pkAno", pkAno )
                .setParameter( "pkCurso", pkCurso );
        Vector<String> result = ( Vector ) query.getResultList();

        result.add( 0, "--Selecione --" );

        return result;
    }
    
    
    public Turma getTurmaByDescricao(String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM Turma p WHERE p.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Turma>   list = query.getResultList();
            
            if( !list.isEmpty() ){
                    return  list.get(0) ;
            }
            return new Turma(0);
    }
     

}
