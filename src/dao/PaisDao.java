/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.PaisJpaController;
import entity.Pais;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class PaisDao extends PaisJpaController{
    
    public PaisDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Pais u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Pais> buscaTodasPaiss() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Pais u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idPais) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Pais u WHERE u.pkPais = :pkPais")
                    .setParameter("idPais", idPais);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Pais >  getDescricaoByIdProfissao(int idPais) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Pais u WHERE u.pkPais = :pkPais")
                    .setParameter("pkPais", idPais);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Pais   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Pais u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Pais> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Pais(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Pais u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Pais> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkPais();
            }
            return 0;
            
    }
     
    
     
     
     public Pais getPaisByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Pais a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Pais> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     public boolean exist_pais (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Pais u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     
     
    
}
