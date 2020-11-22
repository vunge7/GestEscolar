/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author hphphp
 */
public class RelatorioNotaEstudanteUtil {
    
    private long nota1,nota2,tarefas,recursos;

    public long getNota1() {
        return nota1;
    }

    public void setNota1(long nota1) {
        this.nota1 = nota1;
    }

    public long getNota2() {
        return nota2;
    }

    public void setNota2(long nota2) {
        this.nota2 = nota2;
    }

    public long getTarefas() {
        return tarefas;
    }

    public void setTarefas(long tarefas) {
        this.tarefas = tarefas;
    }

    public long getRecursos() {
        return recursos;
    }

    public void setRecursos(long recursos) {
        this.recursos = recursos;
    }

    public RelatorioNotaEstudanteUtil(long nota1, long nota2, long tarefas, long recursos) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.tarefas = tarefas;
        this.recursos = recursos;
    }

    

    

    public RelatorioNotaEstudanteUtil() {
    }

  
    
    
}
