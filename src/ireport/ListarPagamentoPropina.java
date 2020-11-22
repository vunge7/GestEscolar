/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

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
 * @author Necia Caculo
 */
public  class ListarPagamentoPropina {

    
    
    private Date data_inicio, data_fim;
    
    public ListarPagamentoPropina(Date data_inicio, Date data_fim) {
    
        
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        
        try {
             mostrar();
        } catch (Exception e) {
        }
           
    }
    
    
    
    public void mostrar() throws SQLException {

        Connection connection =  BDConexao.getConexao();
        HashMap hashMap = new HashMap();
        hashMap.put("parmDATA_INICIO", this.data_inicio);
        hashMap.put("parmDATA_FIM", this.data_fim);
        hashMap.put("REPUBLICA",Definicoes.REPUBLICA);
        hashMap.put("REPARTICAO", Definicoes.REPARTICAO);
        hashMap.put("NOME_INTITUCAO", Definicoes.NOME_INTITUCAO);
    
        //obter o path do relatorio
        String relatorio = "relatorio/RelatorioPagamento.jasper";

        String obterCaminho = new File(relatorio).getAbsoluteFile().getAbsolutePath();
        
        
        
        
        //XLSX(obterCaminho, hashMap, connection);

        try {
            
            JasperFillManager.fillReport(obterCaminho, hashMap, connection);
            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, connection);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "NAO EXISTEM PAGAMENTOS!...");
            }
        } catch (JRException jex) {
            jex.printStackTrace();
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR MOSTRAR OS PAGAMENTOS CADASTRADOS!...");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS PAGAMENTOS CADASTRADOS!...");
        }

        }



//        public  void XLSX(String jasperFilename, Map<String, Object> parameters, java.sql.Connection dataSource) throws JRException, IOException 
//        {
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilename, parameters, dataSource);
//            int indexOfPonto = jasperFilename.indexOf('.');
//            String file = jasperFilename.substring(0, indexOfPonto) + ".xlsx" ;
//
//            FileOutputStream output = new FileOutputStream(file);
//
//            JRXlsxExporter docxExporter = new JRXlsxExporter();
//
//            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            docxExporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
//            docxExporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
//            docxExporter.exportReport();        
//
//            Runtime rt = Runtime.getRuntime();  
//            System.out.println(file);        
//
//            rt.exec("RunDLL32.EXE shell32.dll,ShellExec_RunDLL " + file);  
//    }

    
    public static void main(String[] args) {
        new ListarPagamentoPropina(new Date(), new Date());
    }
    
}
