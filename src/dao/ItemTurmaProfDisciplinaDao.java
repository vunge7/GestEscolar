/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.ItemTurmaProfessorDisciplinaJpaController;
import controller.exceptions.NonexistentEntityException;
import entity.ItemTurmaProfessorDisciplina;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Necia Caculo
 */
public class ItemTurmaProfDisciplinaDao extends ItemTurmaProfessorDisciplinaJpaController{

    public ItemTurmaProfDisciplinaDao(EntityManagerFactory emf) {
        super(emf);
    }
    
     public Vector <String > buscaTodos () 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p");
            Vector<String>  result  =  (Vector)query.getResultList();
           
           result.add(0,"-- SELECIONE --");         
           return  result;
    }
     
     public List <ItemTurmaProfessorDisciplina > buscaTodosItemTurmaProfessorDisciplina() 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p");
            return query.getResultList();
    }
     public List <ItemTurmaProfessorDisciplina > buscaTodosItemTurmaProfessorDisciplina(int pkProfessor) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE P.fkProfessor.pkProfessor = :pkProfessor")
                    .setParameter("pkProfessor", pkProfessor);
            return query.getResultList();
    }
     
      public List <ItemTurmaProfessorDisciplina > buscaTodosItemTurmaProfessorDisciplinaByTurma(int pkTurma) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.fkTurma.pkTurma = :pkTurma")
                    .setParameter("pkTurma", pkTurma);
            return query.getResultList();
    }
      
      public List <ItemTurmaProfessorDisciplina > buscaTodosItemTurmaProfessorDisciplinaByDisciplina(int pkDisciplina) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.fkDisciplina.pkDisciplina = :pkDisciplina")
                    .setParameter("pkDisciplina", pkDisciplina);
            return query.getResultList();
    }
      
      public List <ItemTurmaProfessorDisciplina > buscaTodosItemTurmaProfessorDisciplinaByModelo(int pkModelo) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.fkModelo.pkModelo = :pkModelo")
                    .setParameter("pkModelo", pkModelo);
            return query.getResultList();
    }
     
     
     public String  getMatriculaById(long idItemTurmaProfessorDisciplina) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p WHERE p.pkitemTurmaProfessor = :idItemTurmaProfessorDisciplina")
                    .setParameter("pkitemTurmaProfessor", idItemTurmaProfessorDisciplina);
            
            List   list = query.getResultList();
            
            if( list!=null){
                    return  String.valueOf( list.get(0) );
            }
            return "";
    }
    
     
     public ItemTurmaProfessorDisciplina  getItemTurmaProfessorDisciplinaByMatricula(String descricao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.descricao = :descricao")
                    .setParameter("descricao", descricao);
            
            List<ItemTurmaProfessorDisciplina>   list = query.getResultList();
            
            if( !list.isEmpty() ){
                    return  list.get(0) ;
            }
            return new ItemTurmaProfessorDisciplina(0);
    }
    
     
     public  List <ItemTurmaProfessorDisciplina >  getDescricaoByIdVeiculo(int idTipoProduto) 
     {         
          System.out.println("cheguei aqui Necia :"    + idTipoProduto);
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p WHERE p.fkTipoProduto.pkTipoItemTurmaProfessorDisciplina = :idTipoProduto")
                    .setParameter("idTipoProduto", idTipoProduto);
     
            return query.getResultList();
    }
     
    public List<ItemTurmaProfessorDisciplina> getAllItemTurmaProfessorDisciplinaByIdTipoProduto(int idtipoProduto)            
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p FROM ItemTurmaProfessorDisciplina");
            
            
            
            List<ItemTurmaProfessorDisciplina> lista = query.getResultList();
            
            if ( !lista.isEmpty() ) {
               return lista;
        }
            
            
            return null;
    
    
    }
     
   
     
   
     public boolean exist_curso (String descricao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.fkTurma.descricao = :descricao")
                    .setParameter("descricao", descricao);
            
            List result = query.getResultList();
            
            if( !result.isEmpty() )
                return  true;
            return false;
    }
     
     
     
     
    public List<String> getAllItemTurmaProfessorDisciplina()
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p  ORDER BY p.descricao ");            
            List<String>  lista =    query.getResultList();          
            lista.add(0, "-- Seleccione --");            
            return lista;
            
 
    }

    public List<ItemTurmaProfessorDisciplina> getProdutoByDesgnacao(String  descricao )
    {
           
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p WHERE  p.descricao LIKE :descricao ORDER BY p.descricao")
                .setParameter("descricao",descricao +"%");       
        List<ItemTurmaProfessorDisciplina> result = query.getResultList();
        em.close();       
        if( result!=null )
                return result;
        return null;
    }
     
    
    public int getIdProdutoByNome(String descricao)
    {
           
        System.out.println("NOME DO PRODUTO " +descricao);
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.descricao = :descricao")
                .setParameter("descricao", descricao);
      
        List<ItemTurmaProfessorDisciplina> result = query.getResultList();
         em.close();
        if( !result.isEmpty() )
                return result.get(0).getPkProfessorDisciplina();
        return 0;
        
    }
    
    public List<ItemTurmaProfessorDisciplina> getAllItemTurmaProfessorDisciplinaByVeiculo(int pkVeiculo) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM ItemTurmaProfessorDisciplina s WHERE s.fkVeiculo.pkVeiculo = :pkVeiculo ORDER BY s.descricao ASC")
                .setParameter("pkVeiculo", pkVeiculo);       
        List<ItemTurmaProfessorDisciplina> lista = query.getResultList();
        
        if ( !lista.isEmpty() ) {
               return lista;
        }
        return null;
        
    }
    
    public List<ItemTurmaProfessorDisciplina> getAllItemTurmaProfessorDisciplinaByModelo(int pkModelo) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM ItemTurmaProfessorDisciplina s WHERE s.fkModelo.pkModelo = :pkModelo ORDER BY s.descricao ASC")
                .setParameter("pkModelo", pkModelo);       
        List<ItemTurmaProfessorDisciplina> lista = query.getResultList();
        
        if ( !lista.isEmpty() ) {
               return lista;
        }
        return null;
        
    }
         
    public int getIdByDescricao (String descricao) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT f.pkItemTurmaProfessorDisciplina FROM ItemTurmaProfessorDisciplina f WHERE f.descricao = :descricao")
                    .setParameter("descricao", descricao);
            
            List result = query.getResultList();
            
            if( result!= null )
            {
              
                return  Integer.parseInt(  String.valueOf( result.get(0) ) )  ;
            }
            return 0;
            
    }
    
    
      public  Vector <String>  getDesigncaoByIdVeiculo(int idVeiculo) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p WHERE p.fkVeiculo.pkVeiculo = :idVeiculo")
                    .setParameter("pkVeiculo", idVeiculo);
              Vector<String>  result  =  (Vector)query.getResultList();
            return result;
    }
      
     public  Vector <String>  getDesigncaoByIdModelo(int idModelo) 
     {         
            EntityManager em = getEntityManager();
            Query query = em.createQuery ("SELECT p.descricao FROM ItemTurmaProfessorDisciplina p WHERE p.fkModelo.pkModelo = :idModelo")
                    .setParameter("pkModelo", idModelo);
              Vector<String>  result  =  (Vector)query.getResultList();
            return result;
    }
     
     
     public List<ItemTurmaProfessorDisciplina> getAllManutencao(Date data_inicio, Date data_fim) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT s FROM ItemTurmaProfessorDisciplina s WHERE s.fkManutencao.dataMantencao BETWEEN  :data_inicio AND :data_fim GROUP BY S.fkManutencao.pkManutencao")
                .setParameter("data_inicio", data_inicio)    
                .setParameter("data_fim", data_fim);       
        List<ItemTurmaProfessorDisciplina> lista = query.getResultList();
        
        if ( !lista.isEmpty() ) {
               return lista;
        }
        return null;
        
    }
     
     
    public void elimnar_all_item_by_id_manutencao( int idManutencao)
    {
            List<ItemTurmaProfessorDisciplina>  list = getAllItemTurmaProfessorDisciplinaByIdManutencao(idManutencao);
              
            for (int i = 0; i < list.size(); i++) 
            {
                  
                  try {   
                
                      destroy( list.get(i).getPkProfessorDisciplina());
                      
                  } catch (NonexistentEntityException ex) {
                     
                     
                  }            
            }
        
    }
    
    public List<ItemTurmaProfessorDisciplina> getAllItemTurmaProfessorDisciplinaByIdManutencao(int idManutencao)
    {
           
        EntityManager em = getEntityManager();       
        Query query = em.createQuery("SELECT p FROM ItemTurmaProfessorDisciplina p WHERE p.fkManutencao.pkManutencao = :idManutencao")
                .setParameter("idManutencao", idManutencao);
 
        List<ItemTurmaProfessorDisciplina> result = query.getResultList();
        em.close();
       
        if( !result.isEmpty() )
                return result;
        return null;
        
    }
    
     
     
}
