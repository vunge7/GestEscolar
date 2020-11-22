package ireport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.TurmaDao;
import entity.Turma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import util.Definicoes;
import static util.Definicoes.NOME_INTITUCAO;
import static util.Definicoes.REPARTICAO;
import static util.Definicoes.REPUBLICA;
import util.JPAEntntityMannagerFactoryUtil;
import util.MapaApontamentoPropinaUtil;

/**
 *
 * @author Domingos Dala Vunge
 */
public class MapaAunosDevedoresREPORT
{

    private EntityManagerFactory emf = JPAEntntityMannagerFactoryUtil.em;

    private List<MapaApontamentoPropinaUtil> lista_mapa;
    private Turma turma;
    private final String REPORT = "MapaAlunosDevedoresPropina.jrxml";

    public MapaAunosDevedoresREPORT( List<MapaApontamentoPropinaUtil> lista_mapa, Turma turma )
    {
        this.lista_mapa = lista_mapa;
        this.turma = turma;
        mostrar();

    }

    public void mostrar()
    {

        //Meus Parametros
        HashMap hashMap = new HashMap();

        hashMap.put( "REPUBLICA", REPUBLICA );
        hashMap.put( "REPARTICAO", REPARTICAO );
        hashMap.put( "NOME_INTITUCAO", NOME_INTITUCAO );
        hashMap.put( "TURMA", turma.getDesignacao() );
        hashMap.put( "CURSO", turma.getFkCurso().getDesignacao() );
        hashMap.put( "SALA", turma.getFkSala().getDesignacao() );
        hashMap.put( "CLASSE", turma.getFkClasse().getDesignacao() );

        String caminho = getCaminho();
        //Minha Coleção de Dados 
        try
        {

            JasperReport report = JasperCompileManager.compileReport( caminho );
            JasperPrint print;

            print = JasperFillManager.fillReport( report, hashMap, new JRBeanCollectionDataSource( lista_mapa ) );

            JasperViewer jasperViewer = new JasperViewer( print, false );
            jasperViewer.setVisible( true );

        }
        catch ( JRException ex )
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

        List<MapaApontamentoPropinaUtil> lista_exemplo = new ArrayList<>();
        EntityManagerFactory emf = JPAEntntityMannagerFactoryUtil.em;
        TurmaDao turmaDao = new TurmaDao( emf );
        MapaApontamentoPropinaUtil objecto = new MapaApontamentoPropinaUtil();
        objecto.setNumero_matricula( 1234 );
        objecto.setNome_completo( "Domingos Dala Vunge" );
        objecto.setFev( 4500 );
        lista_exemplo.add( objecto );
        Turma turma = turmaDao.findTurma( 1 );

        MapaAunosDevedoresREPORT mapaPropinasREPORT = new MapaAunosDevedoresREPORT( lista_exemplo, turma );
        
    }

}
