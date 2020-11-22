/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Window;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hphphp
 */
public class MetodosUtil {

    public static final int CARREIRA_DOCENTE = 3,
            LICENCIADO = 13,
            MESTRE = 14,
            DOUTOR = 15;

    public static void fechar_todas_janelas() {
        System.gc();
        for (Window window : Window.getWindows()) {
            window.dispose();
            // por vezes pode ser melhor usar setVisivel(false);
        }

    }

    public static void adicionar_saldo_doacao(long idDoacao, double valor) {

        BDConexao conexao = new BDConexao();
        String sql = "UPDATE tb_doacoes SET saldo =  " + (getSaldoByIdDoacao(idDoacao) + valor) + " WHERE pk_doacoes = " + idDoacao;
        conexao.executeUpdate(sql);

        conexao.close();

    }

    public static void retirar_saldo_doacao(long idDoacao, double valor) {

        BDConexao conexao = new BDConexao();
        String sql = "UPDATE tb_doacoes SET saldo =  " + (getSaldoByIdDoacao(idDoacao) - valor) + " WHERE pk_doacoes = " + idDoacao;
        conexao.executeUpdate(sql);

        conexao.close();

    }

    public static double getSaldoByIdDoacao(long idDoacoes) {

        String sql = "SELECT e.saldo FROM  tb_doacoes e WHERE  e.pk_doacoes = " + idDoacoes;

        // ResultSet rs = new BDConexao().executeQuery(sql);
        ResultSet rs = new BDConexao().executeQuery(sql);

        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return 0;
        }
        return 0;
    }

    public static void adicionar_saldo_projecto(long idProjecto, double valor) {

        BDConexao conexao = new BDConexao();
        String sql = "UPDATE tb_projecto SET saldo =  " + (getSaldoByIdProjecto(idProjecto) + valor) + " WHERE pk_projecto = " + idProjecto;
        conexao.executeUpdate(sql);

        conexao.close();

    }

    public static void retirar_saldo_projecto(long idProjecto, double valor) {

        BDConexao conexao = new BDConexao();
        String sql = "UPDATE tb_projecto SET saldo =  " + (getSaldoByIdProjecto(idProjecto) - valor) + " WHERE pk_projecto = " + idProjecto;
        conexao.executeUpdate(sql);

        conexao.close();

    }

    public static double getSaldoByIdProjecto(long idProjectos) {

        String sql = "SELECT e.saldo FROM  tb_projecto e WHERE  e.pk_projecto = " + idProjectos;
        // ResultSet rs = new BDConexao().executeQuery(sql);
        ResultSet rs = new BDConexao().executeQuery(sql);

        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return 0;
        }
        return 0;
    }

    public static String getDataString(Date date) {
        try {
            return date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900);
        } catch (Exception e) {
            return "";
        }

    }

    public static int getDataNumero(Date date) {

        // String data = date.getDate() + "" +(date.getMonth() + 1) + ""+(date.getYear() + 1900);
        String data = (date.getYear() + 1900) + "" + formatar_numero((date.getMonth() + 1)) + "" + formatar_numero(date.getDate());
        return Integer.parseInt(data);

    }

    public static boolean permitir_admissao(Date data_fim) {
        if (data_fim == null) {
            return true;
        } else {
            return getDataNumero(new Date()) > getDataNumero(data_fim);
        }
    }

    private static String formatar_numero(int numero) {
        if (numero <= 9) {
            return "0" + numero;
        } else {
            return String.valueOf(numero);
        }

    }

    public static Vector<String> getMesesFalta(int ultimo_mes) {

        Vector<String> vector = new Vector<>();

        switch (ultimo_mes) {

            case 0: {

                vector.add("Fevereiro");
                vector.add("Março");
                vector.add("Abril");
                vector.add("Maio");
                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 1: {

                vector.add("Março");
                vector.add("Abril");
                vector.add("Maio");
                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 2: {

                vector.add("Abril");
                vector.add("Maio");
                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 3: {

                vector.add("Maio");
                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 4: {

                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 5: {

                vector.add("Junho");
                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 6: {

                vector.add("Julho");
                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 7: {

                vector.add("Agosto");
                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 8: {

                vector.add("Setembro");
                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 9: {

                vector.add("Outubro");
                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 10: {

                vector.add("Novembro");
                vector.add("Dezembro");

            }
            break;

            case 11: {

                vector.add("Dezembro");

            }
            break;

        }

        return vector;

    }

}
