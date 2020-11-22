/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.EncarregadoJpaController;
import entity.Encarregado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author 
 */
public class EncarregadoDao extends EncarregadoJpaController{

    public EncarregadoDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Encarregado> getAllEncarregados()
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT u.nome FROM Encarregado u ORDER BY u.nome ");
            
            return query.getResultList();
 
    }
    
    public int getEncarregadoByNomeCompleto(String nomeCompleto)
    {
           
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.pkEncarregado FROM Encarregado u  WHERE u.nomeCompleto = :nomeCompleto")
                .setParameter("nomeCompleto", nomeCompleto);
      
        List<Integer> result = query.getResultList();
         em.close();
        if( !result.isEmpty() )
                return result.get(0);
        return 0;
        
    }
    
     public List<Encarregado> getAllUsuaioByIniciaisNome(String  nome )
    {
           
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a.nome FROM Encarregado a WHERE a.nome LIKE :nome")
                .setParameter("nome",nome +"%");
       
        List<Encarregado> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result;
        return null;
    }
     
    
     
     
//     public Encarregado get_professor_nome_sobrenome(String nome, String sobre_nome)
//     {
//         
//        EntityManager em = getEntityManager();
//        Query query = em.createQuery("SELECT a FROM Encarregado a WHERE a.nome = :nome AND a.sobreNome = :sobreNome")
//                .setParameter("nome", nome).setParameter("sobreNome", sobre_nome);
//        return  (Encarregado)  query.getResultList();
//  
//     }
//      
     
     
      
     
     
     
      public boolean exist_aluno_nome_sobrenome(String nomeCompleto)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Encarregado a WHERE a.nomeCompleto = :nomeCompleto")
                .setParameter("nomeCompleto", nomeCompleto);
        return  !query.getResultList().isEmpty();
  
     }
      
     public boolean exist_username_senha(String username, String senha)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Encarregado a WHERE a.userName = :username AND a.senha = :senha")
                .setParameter("user_name", username).setParameter("senha", senha);
        return  !query.getResultList().isEmpty();
               
  
     }
     
     
     public Encarregado getEncarregado_by_username_senha(String username, String senha)
     {
         
//         System.err.println("USER NAME: " +username);
//         System.err.println("PASSWORD: " +senha);
//         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Encarregado a WHERE a.userName = :username AND a.senha = :senha")
                .setParameter("username", username).setParameter("senha", senha);
        List<Encarregado>   lista =  query.getResultList();
        
         
        if(!lista.isEmpty())
        {
                return lista.get(0);
        }
        return null;       
  
     }
     
     
     public Encarregado getEncarregadoByTelefone(String telefone)
     {
         
//         System.err.println("USER NAME: " +username);
//         System.err.println("PASSWORD: " +senha);
//         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Encarregado a WHERE a.telefone = :telefone")
                .setParameter("telefone", telefone);
        List<Encarregado>   lista =  query.getResultList();
        
         
        if(!lista.isEmpty())
        {
                return lista.get(0);
        }
        return null;       
  
     }
     
}
