/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.PrecoPropinaJpaController;
import entity.PrecoPropina;
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
public class PrecoPropinaDao extends PrecoPropinaJpaController {

    public PrecoPropinaDao(EntityManagerFactory emf) {
        super(emf);
    }

    public Vector<String> buscaTodos() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.valor FROM PrecoPropina p");
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");
        return result;
    }

    public List<PrecoPropina> buscaTodasPrecoPropinas() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM PrecoPropina p");
        return query.getResultList();
    }

    public String getMatriculaById(long idPrecoPropina) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.valor FROM PrecoPropina p WHERE p.pkProduto = :idPrecoPropina")
                .setParameter("idPrecoPropina", idPrecoPropina);

        List list = query.getResultList();

        if (list != null) {
            return String.valueOf(list.get(0));
        }
        return "";
    }

    public PrecoPropina getPrecoPropinaByIdCursoAndIdAnoCurricular(int id_curso, int id_ano_curricular) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM PrecoPropina p WHERE p.fkCurso.pkCurso = :id_curso AND p.fkClasse.pkClasse = :id_ano_curricular")
                .setParameter("id_curso", id_curso)
                .setParameter("id_ano_curricular", id_ano_curricular);

        List<PrecoPropina> list = query.getResultList();

        if (!list.isEmpty()) {
            return list.get(0);
        }
        return new PrecoPropina(0);
    }

    public List<PrecoPropina> getDescricaoByIdFuncionario(int idTipoProduto) {
        System.out.println("cheguei aqui Necia :" + idTipoProduto);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.valor FROM PrecoPropina p WHERE p.fkTipoProduto.pkTipoPrecoPropina = :idTipoProduto")
                .setParameter("idTipoProduto", idTipoProduto);

        return query.getResultList();
    }

    public List<PrecoPropina> getAllPrecoPropinaByIdTipoProduto(int idtipoProduto) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM PrecoPropina");

        List<PrecoPropina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }

        return null;

    }

    public boolean exist_precoPropina(Double valor) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM PrecoPropina p WHERE p.valor = :valor")
                .setParameter("valor", valor);

        List result = query.getResultList();

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<String> getAllPrecoPropina() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.valor FROM PrecoPropina p  ORDER BY p.valor ");
        List<String> lista = query.getResultList();
        lista.add(0, "-- Seleccione --");
        return lista;

    }

    public List<PrecoPropina> getAllPrecoPropinaByFuncionario(int pkFuncionario) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM PrecoPropina s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.valor ASC")
                .setParameter("pkFuncionario", pkFuncionario);
        List<PrecoPropina> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }
        return null;

    }

    public int getIdByDescricao(String valor) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT f.pkPrecoPropina FROM PrecoPropina f WHERE f.valor = :valor")
                .setParameter("valor", valor);

        List result = query.getResultList();

        if (result != null) {

            return Integer.parseInt(String.valueOf(result.get(0)));
        }
        return 0;

    }

    public int getLastId(int pkClasse, int pk_curso) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT   MAX(f.pkPrecoPropina) FROM PrecoPropina f WHERE f.fkClasse.pkClasse = :pkClasse AND f.fkCurso.pkCurso = :pk_curso")
                .setParameter("pkClasse", pkClasse)
                .setParameter("pk_curso", pk_curso);

        List<Integer> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return 0;

    }

   

}
