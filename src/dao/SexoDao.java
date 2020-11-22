/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.SexoJpaController;
import entity.Sexo;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class SexoDao extends SexoJpaController{
    
    public SexoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Sexo u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Sexo> buscaTodasSexos() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Sexo u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idSexo) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Sexo u WHERE u.pkSexo = :pkSexo")
                    .setParameter("idSexo", idSexo);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Sexo >  getDescricaoByIdProfissao(int idSexo) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Sexo u WHERE u.pkSexo = :pkSexo")
                    .setParameter("pkSexo", idSexo);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Sexo   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Sexo u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Sexo> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Sexo(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Sexo u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Sexo> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkSexo();
            }
            return 0;
            
    }
     
     
     
     public Sexo getSexoByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Sexo a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Sexo> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     public boolean exist_ano (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Sexo u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
