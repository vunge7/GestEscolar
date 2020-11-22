/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.MesJpaController;
import entity.Mes;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class MesDao extends MesJpaController{
    
    public MesDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Mes u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
         //  result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Mes> buscaTodasMess() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Mes u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idMes) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Mes u WHERE u.pkMes = :pkMes")
                    .setParameter("idMes", idMes);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Mes >  getDescricaoByIdProfissao(int idMes) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Mes u WHERE u.pkMes = :pkMes")
                    .setParameter("pkMes", idMes);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Mes   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Mes u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Mes> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Mes(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Mes u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Mes> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkMes();
            }
            return 0;
            
    }
     
     
     public int getPkByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Mes u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Mes> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkMes();
            }
            return 0;
            
    }
     
     
     public Mes getMesByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Mes a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Mes> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     
     
     
     
     
     
     public boolean exist_mes (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Mes u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
