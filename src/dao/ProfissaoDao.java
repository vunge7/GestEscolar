/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.ProfissaoJpaController;
import entity.Profissao;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class ProfissaoDao extends ProfissaoJpaController{
    
    public ProfissaoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Profissao u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Profissao> buscaTodasProfissaos() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Profissao u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idProfissao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Profissao u WHERE u.pkProfissao = :pkProfissao")
                    .setParameter("idProfissao", idProfissao);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Profissao >  getDescricaoByIdProfissao(int idProfissao) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Profissao u WHERE u.pkProfissao = :pkProfissao")
                    .setParameter("pkProfissao", idProfissao);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Profissao   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Profissao u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Profissao> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Profissao(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Profissao u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Profissao> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkProfissao();
            }
            return 0;
            
    }
     
     
     
     public Profissao getProfissaoByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Profissao a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Profissao> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     public boolean exist_profissao (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Profissao u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
