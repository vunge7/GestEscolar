/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.ProvinciaJpaController;
import entity.Provincia;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class ProvinciaDao extends ProvinciaJpaController {

    public ProvinciaDao(EntityManagerFactory emf) {
        super(emf);
    }

    public Vector<String> buscaTodos() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Provincia u");
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");

        return result;
    }

    public List<Provincia> buscaTodasProvincias() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Provincia u");
        return query.getResultList();
    }

    public String getDescricaoById(long idProvincia) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Provincia u WHERE u.pkProvincia = :pkProvincia")
                .setParameter("idProvincia", idProvincia);

        List list = query.getResultList();

        if (list != null) {
            return String.valueOf(list.get(0));
        }
        return "";
    }

    public List<Provincia> getDescricaoByIdProfissao(int idProvincia) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Provincia u WHERE u.pkProvincia = :pkProvincia")
                .setParameter("pkProvincia", idProvincia);

        //List list = query.getResultList();
        return query.getResultList();
    }

//     public  Provincia   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Provincia u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Provincia> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Provincia(0);
//            
//    }
    public int getIdByDescricao(String designacao) {

        System.out.println("UNIDADE " + designacao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Provincia u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Provincia> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0).getPkProvincia();
        }
        return 0;

    }

    public Vector<String> buscaTodos(int pkPais) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Provincia u WHERE u.fkPais.pkPais = :pkPais")
                .setParameter("pkPais", pkPais);
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");

        return result;
    }

    public Provincia getProvinciaByDesignacao(String designacao) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Provincia a WHERE  a.designacao = :designacao")
                .setParameter("designacao", designacao);
        List<Provincia> result = query.getResultList();
        em.close();

        if (result != null) {
            return result.get(0);
        }
        return null;

    }

    public boolean exist_ano(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Provincia u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List result = query.getResultList();

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

}
