/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.PropinaJpaController;
import entity.Propina;
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
public class PropinaDao extends PropinaJpaController {

    public PropinaDao(EntityManagerFactory emf) {
        super(emf);
    }

    public Vector<String> buscaTodos() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p");
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");
        return result;
    }

    public List<Propina> buscaTodosPropina() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p");
        return query.getResultList();
    }

//      public Vector <String > buscaTodosPropinaByFuncionario(int pkFuncionario) 
//     {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT p FROM Propina p WHERE p.fkFuncionario.pkFuncionario = :pkFuncionario")
//                    .setParameter("pkFuncionario", pkFuncionario);
//            List<Propina> lista =  query.getResultList();
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
    public List<Propina> buscaTodosPropinaByModelo(int pkModelo) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p WHERE p.fkModelo.pkModelo = :pkModelo")
                .setParameter("pkModelo", pkModelo);
        return query.getResultList();
    }

    public String getMatriculaById(long idPropina) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.pkProduto = :idPropina")
                .setParameter("idPropina", idPropina);

        List list = query.getResultList();

        if (list != null) {
            return String.valueOf(list.get(0));
        }
        return "";
    }

    public Propina getPropinaByMatricula(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p WHERE p.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Propina> list = query.getResultList();

        if (!list.isEmpty()) {
            return list.get(0);
        }
        return new Propina(0);
    }

    public Propina getPropinaByModelo(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p WHERE p.fkModelo.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Propina> list = query.getResultList();

        if (!list.isEmpty()) {
            return list.get(0);
        }
        return new Propina(0);
    }

    public int getLastUltimoMes(int pkEstudante) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT MAX(p.fkMes.pkMes) FROM Propina p WHERE p.fkReciboPropina.fkEstudante.pkEstudante = :pkEstudante")
                .setParameter("pkEstudante", pkEstudante);

        List<Integer> list = query.getResultList();

        if (!list.isEmpty()) {
            try {
                return list.get(0);
            } catch (Exception e) {
                return 0;
            }

        }
        return 0;
    }

    public List<Propina> getDescricaoByIdFuncionario(int idTipoProduto) {
        System.out.println("cheguei aqui Necia :" + idTipoProduto);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkTipoProduto.pkTipoPropina = :idTipoProduto")
                .setParameter("idTipoProduto", idTipoProduto);

        return query.getResultList();
    }

    public List<Propina> getAllPropinaByIdTipoProduto(int idtipoProduto) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina");

        List<Propina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }

        return null;

    }

    public boolean exist_reciboPropina(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p WHERE p.designacao = :designacao")
                .setParameter("designacao", designacao);

        List result = query.getResultList();

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<String> getAllPropina() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p  ORDER BY p.designacao ");
        List<String> lista = query.getResultList();
        lista.add(0, "-- Seleccione --");
        return lista;

    }

    public List<Propina> getProdutoByDesgnacao(String designacao) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE  p.designacao LIKE :designacao ORDER BY p.designacao")
                .setParameter("designacao", designacao + "%");
        List<Propina> result = query.getResultList();
        em.close();
        if (result != null) {
            return result;
        }
        return null;
    }

    public int getIdProdutoByNome(String designacao) {

        System.out.println("NOME DO PRODUTO " + designacao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Propina p WHERE p.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Propina> result = query.getResultList();
        em.close();
        if (!result.isEmpty()) {
            return result.get(0).getPkPropina();
        }
        return 0;

    }

    public List<Propina> getAllPropinaByFuncionario(int pkFuncionario) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM Propina s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.designacao ASC")
                .setParameter("pkFuncionario", pkFuncionario);
        List<Propina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }
        return null;

    }

    public List<Propina> getAllPropinaByModelo(int pkModelo) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM Propina s WHERE s.fkModelo.pkModelo = :pkModelo ORDER BY s.designacao ASC")
                .setParameter("pkModelo", pkModelo);
        List<Propina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }
        return null;

    }

    public int getIdByDescricao1(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT f.pkPropina FROM Propina f WHERE f.designacao = :designacao")
                .setParameter("designacao", designacao);

        List result = query.getResultList();

        if (result != null) {

            return Integer.parseInt(String.valueOf(result.get(0)));
        }
        return 0;

    }

    public int getIdByDescricao(String designacao) {

        System.out.println("UNIDADE " + designacao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Propina u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Propina> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0).getPkPropina();
        }
        return 0;

    }

    public int getPkByDescricao(String designacao) {

        System.out.println("UNIDADE " + designacao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Propina u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Propina> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0).getPkPropina();
        }
        return 0;

    }

    public Vector<String> getDesigncaoByIdAnoLectivo(int idAnoLectivo) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkAnoLectivo.pkAnoLectivo = :idAnoLectivo")
                .setParameter("pk_ano_lectivo", idAnoLectivo);
        Vector<String> result = (Vector) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdSala(int idSala) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkSala.pkSala = :idSala")
                .setParameter("pkSala", idSala);
        Vector<String> result = (Vector) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdTurno(int idTurno) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkTurno.pkTurno = :idTurno")
                .setParameter("pkTurno", idTurno);
        Vector<String> result = (Vector) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdCurso(int idCurso) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkCurso.pkCurso = :idCurso")
                .setParameter("pkCurso", idCurso);
        Vector<String> result = (Vector) query.getResultList();
        return result;
    }

    public Vector<String> getDesigncaoByIdClasse(int idClasse) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.designacao FROM Propina p WHERE p.fkClasse.pkClasse = :idClasse")
                .setParameter("pkClasse", idClasse);
        Vector<String> result = (Vector) query.getResultList();
        return result;
    }

    // query q lista todas as reciboPropinas de uma classe, curso, e ano lectivo 
    public List<Propina> getAllPropinaByClasseAnoCurso(int pkClasse, int pkAno, int pkCurso) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM Propina s WHERE s.fkClasse.pkClasse = :pkClasse AND s.fkAnoLectivo.pkAnoLectivo = :pkAno AND s.fkCurso.pkCurso = :pkCurso  ")
                .setParameter("pkClasse", pkClasse)
                .setParameter("pkAno", pkAno)
                .setParameter("pkCurso", pkCurso);

        List<Propina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }
        return null;

    }

    public Vector<String> buscaTodasPropinasByCursoAnoClasse(int pkClasse, int pkAno, int pkCurso) {
        EntityManager em = getEntityManager();
        Query query;
        query = em.createQuery("SELECT s.designacao FROM Propina s WHERE s.fkClasse.pkClasse = :pkClasse AND s.fkAnoLectivo.pkAnoLectivo = :pkAno AND s.fkCurso.pkCurso = :pkCurso  ")
                .setParameter("pkClasse", pkClasse)
                .setParameter("pkAno", pkAno)
                .setParameter("pkCurso", pkCurso);
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "--Selecione --");

        return result;
    }

}
