/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.UsuarioJpaController;
import entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author 
 */
public class UsuarioDao extends UsuarioJpaController{

    public UsuarioDao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Usuario> getAllUsuarios()
    {
            EntityManager em = getEntityManager();
            Query query = em.createQuery("SELECT u.nomeCompleto FROM Usuario u ORDER BY u.nomeCompleto ");
            
            return query.getResultList();
 
    }
    
    public int getUsuarioByNomeCompleto(String nomeCompleto)
    {
           
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u.pkUsuario FROM Usuario u  WHERE u.nomeCompleto = :nomeCompleto")
                .setParameter("nomeCompleto", nomeCompleto);
      
        List<Integer> result = query.getResultList();
         em.close();
        if( !result.isEmpty() )
                return result.get(0);
        return 0;
        
    }
    
     public List<Usuario> getAllUsuaioByIniciaisNome(String  nomeCompleto )
    {
           
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a.nomeCompleto FROM Usuario a WHERE a.nome LIKE :nomeCompleto")
                .setParameter("nomeCompleto",nomeCompleto +"%");
       
        List<Usuario> result = query.getResultList();
        em.close();
       
        if( result!=null )
                return result;
        return null;
    }
     
    
     
     
//     public Usuario get_professor_nome_sobrenome(String nome, String sobre_nome)
//     {
//         
//        EntityManager em = getEntityManager();
//        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.nome = :nome AND a.sobreNome = :sobreNome")
//                .setParameter("nome", nome).setParameter("sobreNome", sobre_nome);
//        return  (Usuario)  query.getResultList();
//  
//     }
//      
     
      public Usuario getUsuarioByIdAndSenha(int id_usuario, String senha)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.pkUsuario = :id_usuario AND a.senha = :senha")
                .setParameter("id_usuario", id_usuario).setParameter("nomeCompleto", senha);
        return  (Usuario)  query.getResultList();
  
     }
      
     public boolean exist_usuarioByIdAndSenha(int id_usuario, String senha)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.pkUsuario = :id_usuario AND a.senha = :senha")
                .setParameter("id_usuario", id_usuario).setParameter("senha", senha);
        return  !query.getResultList().isEmpty();
  
     }
      
     
     
     
      public boolean exist_aluno_nome_sobrenome(String nomeCompleto)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.nomeCompleto = :nomeCompleto")
                .setParameter("nomeCompleto", nomeCompleto);
        return  !query.getResultList().isEmpty();
  
     }
      
     public boolean exist_user_senha(String user, String senha)
     {
         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.user = :user AND a.senha = :senha")
                .setParameter("user_name", user).setParameter("senha", senha);
        return  !query.getResultList().isEmpty();
               
  
     }
     
     
     public Usuario getUsuario_by_user_senha(String user, String senha)
     {
         
//         System.err.println("USER NAME: " +user);
//         System.err.println("PASSWORD: " +senha);
//         
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.user = :user AND a.senha = :senha")
                .setParameter("user", user).setParameter("senha", senha);
        List<Usuario>   lista =  query.getResultList();
        
         
        if(!lista.isEmpty())
        {
                return lista.get(0);
        }
        return null;       
  
     }
     
}
