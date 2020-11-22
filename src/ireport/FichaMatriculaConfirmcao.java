/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

import entity.ReciboPropina;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.BDConexao;
import util.Definicoes;

/**
 *
 * @author hphpphphphphpphph
 */


public  class FichaMatriculaConfirmcao {
    
    
    private int pk_recibo = 0;
    private String ano;
    private String curso;
    private int opcao;
    

    public FichaMatriculaConfirmcao(int pk_confirmacao , int opcao) {
        
        
        this.opcao = opcao;
        
        
    
        try {
             mostrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
           
    }
    
    
    
    public void mostrar() throws SQLException, JRException, IOException{

        Connection connection =  BDConexao.getConexao();
        HashMap hashMap = new HashMap();
        
        hashMap.put("parmPkRecibo",pk_recibo);
        hashMap.put("parmAno",ano);
        hashMap.put("parmCurso",curso);
       
  
    
        //obter o path do relatorio
        String relatorio = getCaminho();

        String obterCaminho = new File(relatorio).getAbsoluteFile().getAbsolutePath();
        
        //XLSX(obterCaminho, hashMap, connection);

        try {
            
            JasperFillManager.fillReport(obterCaminho, hashMap, connection);
            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, connection);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "NAO HÁ PAGAMENTO!...");
            }
        } catch (JRException jex) {
            jex.printStackTrace();
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR MOSTRAR!...");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR!...");
        }

   }

    private String getCaminho() {
        
        if ( opcao == Definicoes.MATRICULA) {
            return "relatorio/ficha_matricula.jasper";
        }else {
            return "relatorio/ficha_confirmacao.jasper";
        }
    }
    
    
    





}
