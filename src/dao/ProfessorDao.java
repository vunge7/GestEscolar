/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.ProfessorJpaController;
import entity.Professor;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class ProfessorDao extends ProfessorJpaController{
    
    public ProfessorDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.nomeComplero FROM Professor u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Professor> buscaTodasProfessors() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Professor u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idProfessor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.nomeComplero FROM Professor u WHERE u.pkProfessor = :pkProfessor")
                    .setParameter("idProfessor", idProfessor);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Professor >  getDescricaoByIdProfissao(int idProfessor) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.nomeComplero FROM Professor u WHERE u.pkProfessor = :pkProfessor")
                    .setParameter("pkProfessor", idProfessor);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Professor   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Professor u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Professor> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Professor(0);
//            
//    }
     
     public int getIdByDescricao (String nomeComplero) 
     {         
         
            System.out.println("UNIDADE " +nomeComplero);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Professor u WHERE u.nomeComplero = :nomeComplero")
                    .setParameter("nomeComplero", nomeComplero);
            
            List<Professor> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkProfessor();
            }
            return 0;
            
    }
     
     public Professor getProfessorByDesignacao(String  nomeComplero ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Professor a WHERE  a.nomeComplero = :nomeComplero")
                .setParameter("nomeComplero",nomeComplero);      
        List<Professor> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     public boolean exist_professor (String nomeComplero) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Professor u WHERE u.nomeComplero = :nomeComplero")
                    .setParameter("nomeComplero", nomeComplero);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     public int getIdByProfessor (String nomeComplero) 
     {         
         
            System.out.println("UNIDADE " +nomeComplero);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Professor u WHERE u.nomeComplero = :nomeComplero")
                    .setParameter("nomeComplero", nomeComplero);
            
            List<Professor> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkProfessor();
            }
            return 0;
            
    }
     
     
     
     public List<Professor> getAllProfessor () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT c FROM Professor c  ORDER BY c.nomeComplero");
                    
            List<Professor> result = query.getResultList();
            
            if( !result.isEmpty() )
                return  result;
            return null;
    }
     
    
}
