/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.ClasseJpaController;
import entity.Classe;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class ClasseDao extends ClasseJpaController{
    
    public ClasseDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Classe u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Classe> buscaTodasClasses() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Classe u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idClasse) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Classe u WHERE u.pkClasse = :pkClasse")
                    .setParameter("idClasse", idClasse);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Classe >  getDescricaoByIdProfissao(int idClasse) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Classe u WHERE u.pkClasse = :pkClasse")
                    .setParameter("pkClasse", idClasse);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Classe   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Classe u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Classe> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Classe(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Classe u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Classe> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkClasse();
            }
            return 0;
            
    }
     
     
     public int getPkByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Classe u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Classe> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkClasse();
            }
            return 0;
            
    }
     
     
     public Classe getClasseByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Classe a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Classe> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     
     
     
     
     
     
     public boolean exist_classe (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Classe u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
