package util;

/*----------------------------------------------
 *project: SGC
 *fle:	BDConexao.java
 *Desenvolvido por: Domingos Dala Vunge
 *----------------------------------------------*/

import java.sql.*;
import javax.swing.*;

public class BDConexao {

    private Connection connection;
    private Statement statement;
    private static final String ip_address = "localhost";
    private static final String JDBC_URL = "jdbc:mysql://"  +ip_address +":3306/gestescolardb";    
    private static final String USER_NAME = "root", PASS_WORD = "DoV90x?#";
    public static int tipoUser = 0;
   

    
    public BDConexao() {
        try {
            //Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
             statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            //showMessage("Falhou a Conexao com a Base de Dados");
        }
    }
    
     public static void showMessage(String mensagen) 
    {
            JOptionPane.showMessageDialog(null, mensagen);
    
    }

    public static BDConexao getBDConetion() {
        return new BDConexao();
    }
    
    public static Connection conectar()//throws Exception
    {
        Connection mtdoConectar = null;

        try {
           // Class.forName(JDBC_DRIVER);
            mtdoConectar = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);

        } catch (SQLException sqlex) {
            showMessage("Erro ao Estabelecer a Conexao:" + sqlex.getMessage());
        }

        return mtdoConectar;
    }
    
    public static Connection getConexao() throws SQLException {
             return  DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
    }
    
  
    
      protected void finalized() {
        close();
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
             
            return resultSet;
        } catch (Exception ex) {
            ex.printStackTrace();
            showMessage("Falha ao Carregar os Dados");
        }

        return resultSet;
    }

    public boolean executeUpdate(String query) {
        ResultSet result = null;
        try {
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            showMessage("Falha ao Actualizar a BD");

            return false;
        }
        return true;
    }

    
     public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            //sqlException.printStackTrace();
            showMessage("Erro ao Fechar a Conexao");
        }
    }
    
    
    
    // metodo criado para a conexao a utilizar na geracao dos relatorios

    public static void main(String[] args) {
        new BDConexao();
    }
    
    
        
    

}
