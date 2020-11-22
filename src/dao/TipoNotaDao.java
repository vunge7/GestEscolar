/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TipoNotaJpaController;
import entity.TipoNota;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class TipoNotaDao extends TipoNotaJpaController{
    
    public TipoNotaDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoNota u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <TipoNota> buscaTodasTipoNotas() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoNota u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idTipoNota) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoNota u WHERE u.pkTipoNota = :pkTipoNota")
                    .setParameter("idTipoNota", idTipoNota);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <TipoNota >  getDescricaoByIdProfissao(int idTipoNota) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoNota u WHERE u.pkTipoNota = :pkTipoNota")
                    .setParameter("pkTipoNota", idTipoNota);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  TipoNota   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM TipoNota u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<TipoNota> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new TipoNota(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoNota u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<TipoNota> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkTipoNota();
            }
            return 0;
            
    }
     
     
     
     
     public boolean exist_tiponota (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoNota u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
