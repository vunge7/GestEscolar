/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TipoConfirmacaoJpaController;
import entity.TipoConfirmacao;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class TipoConfirmacaoDao extends TipoConfirmacaoJpaController{
    
    public TipoConfirmacaoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoConfirmacao u");
           Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");
         
           return  result;
    }
     
     public List <TipoConfirmacao> buscaTodasTipoConfirmacaos() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoConfirmacao u");
            return query.getResultList();
    }
     
     
     public String  getDescricaoById(long idTipoConfirmacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoConfirmacao u WHERE u.pkTipoConfirmacao = :pkTipoConfirmacao")
                    .setParameter("idTipoConfirmacao", idTipoConfirmacao);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public  List <TipoConfirmacao >  getDescricaoByIdProfissao(int idTipoConfirmacao) 
     {         
            
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u.designacao FROM TipoConfirmacao u WHERE u.pkTipoConfirmacao = :pkTipoConfirmacao")
                    .setParameter("pkTipoConfirmacao", idTipoConfirmacao);
            
            //List list = query.getResultList();
            
            return query.getResultList();
    }
     
//     public  TipoConfirmacao   getUnidadeByAbreviatura(String abreviatura) 
//     {         
//            
//            EntityManager em = getEntityManager();
//            Query query = em.createQuery ("SELECT u FROM TipoConfirmacao u WHERE u.abreviatura = :abreviatura")
//                    .setParameter("abreviatura", abreviatura);
//            
//            List<TipoConfirmacao> list = query.getResultList();
//            
//            if ( !list.isEmpty()) {
//                   return list.get(0);             
//            }
//            return new TipoConfirmacao(0);
//            
//    }
     
     public int getIdByDescricao (String designacao) 
     {         
         
            System.out.println("UNIDADE " +designacao);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoConfirmacao u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List<TipoConfirmacao> result = query.getResultList();
            
            if( !result.isEmpty() )
            {              
                return  result.get(0).getPkTipoConfirmacao();
            }
            return 0;
            
    }
     
     
     
     
     public boolean exist_tipoconfirmacao (String designacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT u FROM TipoConfirmacao u WHERE u.designacao = :designacao")
                    .setParameter("designacao", designacao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
    
}
