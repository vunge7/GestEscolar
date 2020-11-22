/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class ListarEstudantesPorTurma
{

    private int idTurma = 0;
    private int idAno = 0;
    private int idCurso = 0;
    private int idClasse = 0;

    public ListarEstudantesPorTurma( int idTurma, int idAno, int idClasse, int idCurso )
            throws SQLException
    {
        this.idTurma = idTurma;
        this.idAno = idAno;
        this.idClasse = idClasse;
        this.idCurso = idCurso;

        try
        {
            mostrar();
        }
        catch ( Exception e )
        {
        }

    }

    public void mostrar() throws SQLException, JRException, IOException
    {

        Connection connection = BDConexao.getConexao();
        HashMap hashMap = new HashMap();
        hashMap.put( "parmTurma", this.idTurma );
        hashMap.put( "parmAno", this.idAno );
        hashMap.put( "parmClasse", this.idClasse );
        hashMap.put( "parmCurso", this.idCurso );
        hashMap.put( "REPUBLICA", Definicoes.REPUBLICA );
        hashMap.put( "REPARTICAO", Definicoes.REPARTICAO );
        hashMap.put( "NOME_INTITUCAO", Definicoes.NOME_INTITUCAO );

        //obter o path do relatorio
        String relatorio = "relatorio/ListaEstudanteTurma.jasper";

        String obterCaminho = new File( relatorio ).getAbsoluteFile().getAbsolutePath();

        //XLSX(obterCaminho, hashMap, connection);
        try
        {

            JasperFillManager.fillReport( obterCaminho, hashMap, connection );
            JasperPrint jasperPrint = JasperFillManager.fillReport( obterCaminho, hashMap, connection );

            if ( jasperPrint.getPages().size() >= 1 )
            {
                JasperViewer jasperViewer = new JasperViewer( jasperPrint, false );
                jasperViewer.setVisible( true );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "NAO EXISTEM ESTUDANTES CADASTRADOS!..." );
            }
        }
        catch ( JRException jex )
        {
            jex.printStackTrace();
            JOptionPane.showMessageDialog( null, "FALHA AO TENTAR MOSTRAR OS ESTUDANTES CADASTRADOS!..." );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog( null, "ERRO AO TENTAR MOSTRAR OS ESTUDANTES CADASTRADOS!..." );
        }

    }

    public static void main( String[] args )
    {
        try
        {
            new ListarEstudantesPorTurma( 1, 1, 1, 6 );
        }
        catch ( Exception e )
        {
        }

    }

}
