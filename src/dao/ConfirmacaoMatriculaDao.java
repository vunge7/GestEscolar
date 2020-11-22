/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.ConfirmacaoMatriculaJpaController;
import entity.ConfirmacaoMatricula;
import entity.Estudante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author mac
 */
public class ConfirmacaoMatriculaDao extends ConfirmacaoMatriculaJpaController {

    public ConfirmacaoMatriculaDao(EntityManagerFactory emf) {
        super(emf);
    }

//     //Estudante ConfirmacaoMatricula
//    public List <ConfirmacaoMatriucla> buscaTodosConfirmacaoMatricula() {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM ConfirmacaoMatriucla u WHERE u.fimContrato = false  ORDER BY u.fkEstudante.pkEstudante DESC");
//            return query.getResultList();
//    }
//    
//    public List <Estudante> buscaEstudanteMatriculados() {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u.fkEstudante FROM ConfirmacaoMatriucla u WHERE u.fimContrato = false  ORDER BY u.fkEstudante.nome ASC");
//            return query.getResultList();
//    }
//public boolean exist_admissao_by_id_funcionario (int pk_funcionario) {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM ConfirmacaoMatriucla u WHERE u.fkEstudante.pkEstudante = :pk_funcionario")
//                    .setParameter("pk_funcionario", pk_funcionario);
//            
//            List result = query.getResultList();
//            
//            if( !result.isEmpty() )
//                return  true;
//            return false;
//    }
    public ConfirmacaoMatricula getConfirmacaoMatByIdEstudanteAnoLetivo(int pkEstudante, int pkAnoLectivo) {

        System.out.println("ID " + pkEstudante);
        System.out.println("ID " + pkAnoLectivo);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM ConfirmacaoMatricula u WHERE u.fkEstudante.pkEstudante = :pkEstudante AND u.fkTurma.fkAnoLectivo.pkAnoLectivo = :pkAnoLectivo")
                .setParameter("pkEstudante", pkEstudante)
                .setParameter("pkAnoLectivo", pkAnoLectivo);

        List<ConfirmacaoMatricula> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
//     
//     public int getLastConfirmacaoMatricula (int pk_funcionario) {         
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT MAX(u.pkConfirmacaoMatricula) FROM ConfirmacaoMatricula u WHERE u.fkEstudante. = :pk_funcionario")
//                    .setParameter("pk_funcionario", pk_funcionario);
//            
//            List<Integer> result = query.getResultList();
//            try {
//               if( !result.isEmpty() )
//                return  result.get(0);
//          
//          } catch (Exception e) {
//              //return 0;
//          }
//            return 0;
//           
//    }
//     
//     public ConfirmacaoMatricula getLastObject(int pk_funcionario){
//            return findConfirmacaoMatricula(getLastConfirmacaoMatricula(pk_funcionario) );
//     
//     } 
//      

    public List<Estudante> getALLEstudanteAdmitidosByNome(String nome) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a.fkEstudante FROM ConfirmacaoMatricula a WHERE  a.fkEstudante.nomeCompleto  LIKE :nome  ORDER BY a.fkEstudante.nomeCompleto ASC")
                .setParameter("nome", "%" + nome + "%");
        List<Estudante> result = query.getResultList();
        em.close();

        if (result != null) {
            return result;
        }
        return null;
    }

    public List<ConfirmacaoMatricula> getAllEstudantesByTurma(int pkTurma) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM ConfirmacaoMatricula s WHERE s.fkTurma.pkTurma = :pkTurma")
                .setParameter("pkTurma", pkTurma);

        List<ConfirmacaoMatricula> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        }
        return null;

    }

    public List<ConfirmacaoMatricula> buscaTodasConfirmacoes() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM ConfirmacaoMatricula u  ORDER BY u.fkEstudante.nomeCompleto ASC");
        return query.getResultList();
    }

    public List<Estudante> buscaTodosEstudanteConfirmados(int pk_ano_lectivo) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.fkEstudante FROM ConfirmacaoMatricula u   WHERE u.fkTurma.fkAnoLectivo.pkAnoLectivo = :pk_ano_lectivo ORDER BY u.fkEstudante.nomeCompleto ASC")
                .setParameter("pk_ano_lectivo", pk_ano_lectivo);
        return query.getResultList();
    }

    public List<Estudante> buscaTodosEstudanteConfirmadosByIdTurma(int pk_turma) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.fkEstudante FROM ConfirmacaoMatricula u   WHERE u.fkTurma.pkTurma = :pk_turma ORDER BY u.fkEstudante.nomeCompleto ASC")
                .setParameter("pk_turma", pk_turma);
        return query.getResultList();
    }

    public boolean exist_MatriculaConfirmacao(String nomeCompleto) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM ConfirmacaoMatricula u WHERE u.fkEstudante.nomeCompleto = :nomeCompleto")
                .setParameter("nomeCompleto", nomeCompleto);

        List result = query.getResultList();

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
