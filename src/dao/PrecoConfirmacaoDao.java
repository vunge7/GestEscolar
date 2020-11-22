/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.PrecoConfirmacaoJpaController;
import entity.PrecoConfirmacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class PrecoConfirmacaoDao extends PrecoConfirmacaoJpaController{

    public PrecoConfirmacaoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
     public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM PrecoConfirmacao p");
            Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");         
           return  result;
    }
     
     public List <PrecoConfirmacao > buscaTodasPrecoConfirmacaos() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM PrecoConfirmacao p");
            return query.getResultList();
    }
     

     
     public String  getMatriculaById(long idPrecoConfirmacao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM PrecoConfirmacao p WHERE p.pkProduto = :idPrecoConfirmacao")
                    .setParameter("idPrecoConfirmacao", idPrecoConfirmacao);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     
     public PrecoConfirmacao getPrecoConfirmacaoByModelo(String valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM PrecoConfirmacao p WHERE p.fkModelo.valor = :valor")
                    .setParameter("valor", valor);
            
            List<PrecoConfirmacao>   list = query.getResultList();
            
            if( !list.isEmpty() ){
                    return  list.get(0) ;
            }
            return new PrecoConfirmacao(0);
    }
    
     
     
     
     
     public  List <PrecoConfirmacao >  getDescricaoByIdFuncionario(int idTipoProduto) 
     {         
          System.out.println("cheguei aqui Necia :"    + idTipoProduto);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM PrecoConfirmacao p WHERE p.fkTipoProduto.pkTipoPrecoConfirmacao = :idTipoProduto")
                    .setParameter("idTipoProduto", idTipoProduto);
     
            return query.getResultList();
    }
     
    public List<PrecoConfirmacao> getAllPrecoConfirmacaoByIdTipoProduto(int idtipoProduto)            
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p FROM PrecoConfirmacao");
            
            
            
            List<PrecoConfirmacao> lista = query.getResultList();
            
            if ( !lista.isEmpty() ) {
               return lista;
        }
            
            
            return null;
    
    
    }
     
   
     
   
     public boolean exist_precoConfirmacao (Double valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM PrecoConfirmacao p WHERE p.valor = :valor")
                    .setParameter("valor", valor);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     
     
    public List<String> getAllPrecoConfirmacao()
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p.valor FROM PrecoConfirmacao p  ORDER BY p.valor ");            
            List<String>  lista =    query.getResultList();          
            lista.add(0, "-- Seleccione --");            
            return lista;
            
 
    }

    
     
    
    
    
    public List<PrecoConfirmacao> getAllPrecoConfirmacaoByFuncionario(int pkFuncionario) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM PrecoConfirmacao s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.valor ASC")
                .setParameter("pkFuncionario", pkFuncionario);       
        List<PrecoConfirmacao> lista = query.getResultList();
        
        if ( !lista.isEmpty() ) {
               return lista;
        }
        return null;
        
    }
    
    
         
    public int getIdByDescricao (String valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT f.pkPrecoConfirmacao FROM PrecoConfirmacao f WHERE f.valor = :valor")
                    .setParameter("valor", valor);
            
            List result = query.getResultList();
            
            if( result!= null )
            {
              
                return  Integer.parseInt(  String.valueOf( result.get(0) ) )  ;
            }
            return 0;
            
    }
    
    
     public int getLastId(int pkTipoConfirmacao, int pkCurso) { 
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery ("SELECT   MAX(f.pkPrecoConfirmacao) FROM PrecoConfirmacao f WHERE f.fkTipoConfirmacao.pkTipoConfirmacao = :pkTipoConfirmacao AND f.fkCurso.pkCurso = :pkCurso")
                .setParameter("pkTipoConfirmacao", pkTipoConfirmacao)
                .setParameter("pkCurso", pkCurso);

        List<Integer> result = query.getResultList();

        if( !result.isEmpty() ){
            return  result.get(0);
        }
        return 0;
            
    }
    
}

