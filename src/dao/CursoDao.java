/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.CursoJpaController;
import entity.Curso;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class CursoDao extends CursoJpaController{
    
    public CursoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Curso u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Curso> buscaTodasCursos() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Curso u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idCurso) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Curso u WHERE u.pkCurso = :pkCurso")
                    .setParameter("idCurso", idCurso);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Curso >  getDescricaoByIdProfissao(int idCurso) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Curso u WHERE u.pkCurso = :pkCurso")
                    .setParameter("pkCurso", idCurso);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Curso   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Curso u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Curso> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Curso(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Curso u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Curso> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkCurso();
            }
            return 0;
            
    }
     
    
     
     
     public Curso getCursoByDesignacao(String  designacao ){
 
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Curso a WHERE  a.designacao = :designacao")
                .setParameter("designacao",designacao);      
        List<Curso> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result.get(0);
        return null;
        
    }
     
     
     public boolean exist_curso (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Curso u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     
     
    
}
