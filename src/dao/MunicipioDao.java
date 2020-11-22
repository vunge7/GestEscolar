/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.MunicipioJpaController;
import entity.Municipio;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class MunicipioDao extends MunicipioJpaController {

    public MunicipioDao(EntityManagerFactory emf) {
        super(emf);
    }

    public Vector<String> buscaTodos() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Municipio u");
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");

        return result;
    }

    public List<Municipio> buscaTodasMunicipios() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Municipio u");
        return query.getResultList();
    }

    public String getDescricaoById(long idMunicipio) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Municipio u WHERE u.pkMunicipio = :pkMunicipio")
                .setParameter("idMunicipio", idMunicipio);

        List list = query.getResultList();

        if (list != null) {
            return String.valueOf(list.get(0));
        }
        return "";
    }

    public List<Municipio> getDescricaoByIdProfissao(int idMunicipio) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Municipio u WHERE u.pkMunicipio = :pkMunicipio")
                .setParameter("pkMunicipio", idMunicipio);

        //List list = query.getResultList();
        return query.getResultList();
    }

//     public  Municipio   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Municipio u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Municipio> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Municipio(0);
//            
//    }
    public int getIdByDescricao(String designacao) {

        System.out.println("UNIDADE " + designacao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Municipio u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List<Municipio> result = query.getResultList();

        if (!result.isEmpty()) {
            return result.get(0).getPkmunicipio();
        }
        return 0;

    }

    public Municipio getMunicipioByDesignacao(String designacao) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Municipio a WHERE  a.designacao = :designacao")
                .setParameter("designacao", designacao);
        List<Municipio> result = query.getResultList();
        em.close();

        if (result != null) {
            return result.get(0);
        }
        return null;

    }

    public boolean exist_ano(String designacao) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Municipio u WHERE u.designacao = :designacao")
                .setParameter("designacao", designacao);

        List result = query.getResultList();

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

    public Vector<String> buscaTodos(int pkProvincia) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.designacao FROM Municipio u WHERE u.fkProvincia.pkProvincia = :pkProvincia")
                .setParameter("pkProvincia", pkProvincia);
        Vector<String> result = (Vector) query.getResultList();

        result.add(0, "-- SELECIONE --");

        return result;
    }

}
