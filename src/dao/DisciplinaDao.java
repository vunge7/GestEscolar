/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.DisciplinaJpaController;
import entity.Disciplina;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class DisciplinaDao extends DisciplinaJpaController{
    
    public DisciplinaDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Disciplina u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <Disciplina> buscaTodasDisciplinas() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Disciplina u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idDisciplina) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Disciplina u WHERE u.pkDisciplina = :pkDisciplina")
                    .setParameter("idDisciplina", idDisciplina);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <Disciplina >  getDescricaoByIdProfissao(int idDisciplina) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM Disciplina u WHERE u.pkDisciplina = :pkDisciplina")
                    .setParameter("pkDisciplina", idDisciplina);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  Disciplina   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM Disciplina u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<Disciplina> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new Disciplina(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Disciplina u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Disciplina> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkDisciplina();
            }
            return 0;
            
    }
     
     
     
     
     public boolean exist_disciplina (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM Disciplina u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     public Disciplina getDisciplinaByDescricao(String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM Disciplina p WHERE p.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<Disciplina>   list = query.getResultList();
            
            if( !list.isEmpty() ){
                    return  list.get(0) ;
            }
            return new Disciplina(0);
    }
     
    
}
