/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.ReciboPropinaJpaController;
import entity.ReciboPropina;
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
public class ReciboPropinaDao extends ReciboPropinaJpaController{

    public ReciboPropinaDao(EntityManagerFactory emf) {
        super(emf);
    }
    
     public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM ReciboPropina p");
            Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");         
           return  result;
    }
     
     public List <ReciboPropina > buscaTodasReciboPropinas() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ReciboPropina p");
            return query.getResultList();
    }
     

     
     public String  getMatriculaById(long idReciboPropina) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM ReciboPropina p WHERE p.pkProduto = :idReciboPropina")
                    .setParameter("idReciboPropina", idReciboPropina);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     
     public ReciboPropina getReciboPropinaByModelo(String valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ReciboPropina p WHERE p.fkModelo.valor = :valor")
                    .setParameter("valor", valor);
            
            List<ReciboPropina>   list = query.getResultList();
            
            if( !list.isEmpty() ){
                    return  list.get(0) ;
            }
            return new ReciboPropina(0);
    }
    
     
     
     
     
     public  List <ReciboPropina >  getDescricaoByIdFuncionario(int idTipoProduto) 
     {         
          System.out.println("cheguei aqui Necia :"    + idTipoProduto);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.valor FROM ReciboPropina p WHERE p.fkTipoProduto.pkTipoReciboPropina = :idTipoProduto")
                    .setParameter("idTipoProduto", idTipoProduto);
     
            return query.getResultList();
    }
     
    public List<ReciboPropina> getAllReciboPropinaByIdTipoProduto(int idtipoProduto)            
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p FROM ReciboPropina");
            
            
            
            List<ReciboPropina> lista = query.getResultList();
            
            if ( !lista.isEmpty() ) {
               return lista;
        }
            
            
            return null;
    
    
    }
     
   
     
   
     public boolean exist_reciboPropina (Double valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ReciboPropina p WHERE p.valor = :valor")
                    .setParameter("valor", valor);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     
     
    public List<String> getAllReciboPropina()
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p.valor FROM ReciboPropina p  ORDER BY p.valor ");            
            List<String>  lista =    query.getResultList();          
            lista.add(0, "-- Seleccione --");            
            return lista;
            
 
    }

    
     
    
    
    
    public List<ReciboPropina> getAllReciboPropinaByFuncionario(int pkFuncionario) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM ReciboPropina s WHERE s.fkFuncionario.pkFuncionario = :pkFuncionario ORDER BY s.valor ASC")
                .setParameter("pkFuncionario", pkFuncionario);       
        List<ReciboPropina> lista = query.getResultList();
        
        if ( !lista.isEmpty() ) {
               return lista;
        }
        return null;
        
    }
    
    
         
    public int getIdByDescricao (String valor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT f.pkReciboPropina FROM ReciboPropina f WHERE f.valor = :valor")
                    .setParameter("valor", valor);
            
            List result = query.getResultList();
            
            if( result!= null )
            {
              
                return  Integer.parseInt(  String.valueOf( result.get(0) ) )  ;
            }
            return 0;
            
    }
    
    
     public int getLastId(int pk_tipo_confirmacao, int pk_curso) { 
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery ("SELECT   MAX(f.pkReciboPropina) FROM ReciboPropina f WHERE f.fkTipoConfirmacao.pkTipoConfirmacao = :pk_tipo_confirmacao AND f.fkCurso.pkCurso = :pk_curso")
                .setParameter("pk_tipo_confirmacao", pk_tipo_confirmacao)
                .setParameter("pk_curso", pk_curso);

        List<Integer> result = query.getResultList();

        if( !result.isEmpty() ){
            return  result.get(0);
        }
        return 0;
            
    }
     
     
     
     public int getLastId() { 
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery ("SELECT   MAX(f.pkReciboPropina) FROM ReciboPropina f ");
        List<Integer> result = query.getResultList();

        if( !result.isEmpty() ){
            return  result.get(0);
        }
        return 0;
            
    }
    
}

