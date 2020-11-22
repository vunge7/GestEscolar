/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

import entity.Turma;
import entity.util.MiniPautaUtil;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import util.BDConexao;
import util.Definicoes;
import static util.Definicoes.NOME_INTITUCAO;
import static util.Definicoes.REPARTICAO;
import static util.Definicoes.REPUBLICA;

/**
 *
 * @author Necia Caculo & Domingos Dala Vunge
 */
public class MiniPautaREPORT
{

    private final String REPORT = "mini_pauta_iniciacao.jrxml";
    private String disiciplina = "";

    public MiniPautaREPORT( List<MiniPautaUtil> lista_mini_pauta, Turma turma, String disciplina )
    {

        this.disiciplina = disciplina;
        try
        {
            mostrar( lista_mini_pauta, turma );
        }
        catch ( SQLException | JRException | IOException e )
        {
        }

    }

    public void mostrar( List<MiniPautaUtil> lista_mini_pauta, Turma turma ) throws SQLException, JRException, IOException
    {

        Connection connection = BDConexao.getConexao();
        HashMap hashMap = new HashMap();
        hashMap.put( "REPUBLICA", REPUBLICA );
        hashMap.put( "REPARTICAO", REPARTICAO );
        hashMap.put( "NOME_INTITUCAO", NOME_INTITUCAO );
        hashMap.put( "PARM_TURMA", turma.getDesignacao() );
        hashMap.put( "PARM_CLASSE", turma.getFkClasse().getDesignacao() );
        hashMap.put( "PARM_ANO_LECTIVO", turma.getFkAnoLectivo().getDesignacao() );
        hashMap.put( "PARM_DISCIPLINA", disiciplina );

        //obter o path do relatorio
        String relatorio = "relatorio/mini_pauta_iniciacao.jasper";

        String obterCaminho = new File( relatorio ).getAbsoluteFile().getAbsolutePath();

        String caminho = getCaminho();
        //Minha Coleção de Dados 
        try
        {

            JasperReport report = JasperCompileManager.compileReport( caminho );
            JasperPrint print;

            print = JasperFillManager.fillReport( report, hashMap, new JRBeanCollectionDataSource( lista_mini_pauta ) );

            JasperViewer jasperViewer = new JasperViewer( print, false );
            jasperViewer.setVisible( true );

        }
        catch ( JRException  ex )
        {
            ex.printStackTrace();
        }

    }

    private String getCaminho()
    {
        return Definicoes.CAMINHO_REPORT + REPORT;
    }

    public static void main( String[] args )
    {

    }

}
