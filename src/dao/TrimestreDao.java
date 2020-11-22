/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TrimestreJpaController;
import entity.Trimestre;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class TrimestreDao extends TrimestreJpaController{
    
    public TrimestreDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Trimestre u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Trimestre> buscaTodosTrimestres() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Trimestre u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long pkSemestre) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Trimestre u WHERE u.pkSemestre = :pkSemestre")
                    .setParameter("pkSemestre", pkSemestre);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Trimestre >  getDescricaoByIdProfissao(int idTrimestre) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Trimestre u WHERE u.pkSemestre = :pkSemestre")
                    .setParameter("pkSemestre", idTrimestre);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Trimestre   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Trimestre u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Trimestre> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Trimestre(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Trimestre u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Trimestre> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkSemestre();
            }
            return 0;
            
    }
     
     
     
     
     public boolean exist_semestre (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Trimestre u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
