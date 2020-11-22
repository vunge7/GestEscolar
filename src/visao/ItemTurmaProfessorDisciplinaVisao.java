/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ProvinciaVisao.java
 *
 * Created on 27/12/2013, 12:24:14
 */
package visao;

import dao.TurmaDao;
import dao.DisciplinaDao;
import dao.ProfessorDao;
import dao.ItemTurmaProfDisciplinaDao;
import dao.UsuarioDao;

import entity.Turma;
import entity.Disciplina;
import entity.Professor;
import entity.ItemTurmaProfessorDisciplina;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.JPAEntntityMannagerFactoryUtil;

/**
 *
 * @author Necia Caculo
 */
public class ItemTurmaProfessorDisciplinaVisao extends javax.swing.JFrame
{

    private EntityManagerFactory emf = JPAEntntityMannagerFactoryUtil.em;

    private Turma turmas;
    private Disciplina disciplinas;
    private TurmaDao turmaDao = new TurmaDao( emf );
    private DisciplinaDao disciplinaDao = new DisciplinaDao( emf );
    private ProfessorDao professorDao = new ProfessorDao( emf );
    private ItemTurmaProfDisciplinaDao ivmDao = new ItemTurmaProfDisciplinaDao( emf );
    private ItemTurmaProfessorDisciplina itemTurmaProfessor;
    private UsuarioDao usuarioDao = new UsuarioDao( emf );
    private DefaultListModel lista_professor_local = new DefaultListModel();
    private int cod_turma = 0;
    private String nomeCompleto;
    private int idUser = 0;

    public ItemTurmaProfessorDisciplinaVisao( int idUser )
    {

        initComponents();
        setLocationRelativeTo( null );
        this.idUser = idUser;
        jListProfessor.setModel( lista_professor_local );
        setCampos();

    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLCargo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListProfessor = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbTurma = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbDisciplina = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ERP_UAN");

        jPanel7.setBackground(new java.awt.Color(192, 208, 232));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("                ADICIONAR TURMAS E DISCIPLINAS AOS PROFESSORES");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jListProfessor.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListProfessorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListProfessor);

        jLabel3.setText("Dirigentes");

        cmbTurma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- SELECIONE --" }));
        cmbTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTurmaActionPerformed(evt);
            }
        });

        jLabel1.setText("Turma:");

        jLabel7.setText("Disciplina: ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Button-Add-icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/2934_32x32.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turma", "Disciplina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        }

        cmbDisciplina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- SELECIONE --" }));
        cmbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDisciplinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cmbTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(10, 10, 10))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220)
                .addComponent(jLCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 959, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Logout 32x32.png"))); // NOI18N
        jButton3.setText("Saír");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if ( evt.getClickCount() >= 2 )
        {

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cmbTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTurmaActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_cmbTurmaActionPerformed

    private void jListProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListProfessorMouseClicked
        // TODO add your handling code here:
//        setTurmasByIdProfessor();
        buscar_todos_turmas_by_professor();
    }//GEN-LAST:event_jListProfessorMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvar_item_turma_professor();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        remover_linha_tabela();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDisciplinaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] )
    {
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new ItemTurmaProfessorDisciplinaVisao( 1 ).setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDisciplina;
    private javax.swing.JComboBox cmbTurma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jListProfessor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void setDadosProdutoModelo()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();
        this.cod_turma = Integer.parseInt( String.valueOf( modelo.getValueAt( jTable1.getSelectedRow(), 0 ) ) );

    }

//    private void setCargoProfessor()
//    {
//         Professor professor = professorDao.getProfessorByNomeAndSobreNome(nome, sobrenome);
//         
//         jLCargo.setText( professor.getFkCargo().getDesignacao());
//    
//    }
//    
    public void procedimento_salvar()
    {
        if ( validar_manutencao() )
        {

            //salvar_manutencao();
            salvar_item_turma_professor();
            limpar_tabela();

        }

    }

    private String getDescricaoProfessor()
    {
        return String.valueOf( jListProfessor.getSelectedValue() );
    }

    private int getIdTurma()
    {
        return turmaDao.getTurmaByDescricao( cmbTurma.getSelectedItem().toString() ).getPkTurma();
    }

    private int getIdDisciplina()
    {
        return disciplinaDao.getDisciplinaByDescricao( cmbDisciplina.getSelectedItem().toString() ).getPkDisciplina();
    }

    private void setCampos()
    {
        setProfessors();
        setTurmasByIdProfessor();

    }

    public void setDadosTurma()
    {

    }

    public void alterar()
    {

    }

    public void limpar()
    {

    }

    private void setProfessors()
    {
        List<Professor> lista = professorDao.getAllProfessor();

        for ( int i = 0; i < lista.size(); i++ )
        {
            Professor get = lista.get( i );

            lista_professor_local.addElement( get.getNomeComplero() );

        }

    }

    private String getNomeProfessorSelecionado()
    {
        return jListProfessor.getSelectedValue();
    }

    private int getIdProfessor()
    {
        String nome_completo = jListProfessor.getSelectedValue();
        return professorDao.getIdByProfessor( nome_completo );
    }

    //Seta a combo dos turmas
    private void setTurmasByIdProfessor()
    {
//           Professor professor =   professorDao.findProfessor(getIdProfessor());
//            cmbTurma.setModel(   new DefaultComboBoxModel(   (Vector) turmaDao.buscaTodasTurma() ) );
        cmbTurma.setModel( new DefaultComboBoxModel( turmaDao.buscaTodos() ) );
        cmbDisciplina.setModel( new DefaultComboBoxModel( disciplinaDao.buscaTodos() ) );

    }

    private void adicionar_tabela()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();

        if ( validar() )
        {

            modelo.addRow( new Object[]
            {
                getNomeProfessorSelecionado(),
                cmbTurma.getSelectedItem(),
                cmbDisciplina.getSelectedItem()

//                        txtValor.getText()
            } );

//                    MetodosUtil.getDataString(get.dc_data_inicio());
//                  txtValor.setText("");
//                  txtDescricao.requestFocus();           
        }
    }

    private void remover_linha_tabela()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();
        try
        {
            modelo.removeRow( jTable1.getSelectedRow() );

        }
        catch ( Exception e )
        {
            JOptionPane.showMessageDialog( null, "Impossivel eliminar não ha dados selecionados", "AVISO", JOptionPane.WARNING_MESSAGE );
        }

    }

//     private void salvar_manutencao()
//     {
//          Manutencao manutencao = new Manutencao();
//          
//          manutencao.setDataMantencao( new Date());
//          manutencao.setHoraManutencao( new Date() );  
//          manutencao.setTotal(  getTotal() );
//          manutencao.setFkUsuario( usuarioDao.findUsuario(   this.idUser ) );
//            
//         try {
//             manutencaoDao.create(manutencao);
//         } catch (Exception e) {
//         }
//          
//          
//     }
    private void salvar_item_turma_professor()
    {
        itemTurmaProfessor = new ItemTurmaProfessorDisciplina();
        itemTurmaProfessor.setFkTurma( turmaDao.findTurma( getIdTurma() ) );
        itemTurmaProfessor.setFkDisciplina( disciplinaDao.findDisciplina( getIdDisciplina() ) );
        itemTurmaProfessor.setFkProfessor( professorDao.findProfessor( getIdProfessor() ) );

        try
        {
            ivmDao.create( itemTurmaProfessor );
            buscar_todos_turmas_by_professor();
            JOptionPane.showMessageDialog( null, "Dados Inseridos com Sucesso! " );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Falha ao adicionar! ", "FALHA", JOptionPane.ERROR_MESSAGE );
        }

    }

    private void limpar_tabela()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();
        modelo.setRowCount( 0 );
    }

    private double getTotal()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();

        double total = 0;
        for ( int i = 0; i < modelo.getRowCount(); i++ )
        {
            total = total + Double.parseDouble( modelo.getValueAt( i, 1 ).toString() );
        }

        return total;

    }

    private String getDescricaoTurma()
    {

        String modelo = cmbTurma.getSelectedItem().toString();

        int ultimo_indice = modelo.lastIndexOf( "(" ) - 1;

        return modelo.substring( 0, ultimo_indice );

    }

    private boolean validar()
    {
        if ( cmbTurma.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog( null, "Pf seleccione a turma", "AVISO", JOptionPane.WARNING_MESSAGE );
            return false;
        }
        else if ( cmbDisciplina.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog( null, "Pf seleccione a disciplina", "AVISO", JOptionPane.WARNING_MESSAGE );
            cmbDisciplina.requestFocus();
            return false;
        }

// Método q valida a nota ..else if ( !valor_numero() ) {
//                
//                   txtValor.setText("");
//                   JOptionPane.showMessageDialog(null, "O custo tem que ser um número", "AVISO", JOptionPane.WARNING_MESSAGE);
//                   return false;
//             
//            }
        return true;

    }

    private boolean validar_manutencao()
    {

        if ( tabela_vazia() )
        {
            JOptionPane.showMessageDialog( null, "Impossivel adicionar", "AVISO", JOptionPane.WARNING_MESSAGE );
            return false;
        }
        else if ( cmbTurma.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog( null, "Pf seleccione o turma", "AVISO", JOptionPane.WARNING_MESSAGE );
            return false;

        }
        else if ( cmbDisciplina.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog( null, "Pf seleccione a Disciplina", "AVISO", JOptionPane.WARNING_MESSAGE );
            return false;
        }

        return true;

    }

//    private boolean valor_numero()
//    {
//            try {
//            Double.parseDouble(txtValor.getText());
//            return true;
//        } catch (Exception e) {
//           return false;
//        }
//    
//    }
//    
    private boolean tabela_vazia()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();
        return modelo.getRowCount() == 0;

    }

//    private int getIdProfessor() {
//        String nome_completo = jListProfessor.getSelectedValue();
////         
////         int meio = nome_completo.indexOf("-");
////         
////         System.err.println("Meio: " +meio);
////         String nome = nome_completo.substring(0, meio);
////         String sobrenome = nome_completo.substring(meio + 1, nome_completo.length());
////         
////         
////         System.err.println("Nome :" + nome);
////         System.err.println("sobrenome :" + sobrenome);
//
//        return professorDao.getProfessorByNome(nome_completo).getPkprofessor();
//
//    }
    private void buscar_todos_turmas_by_professor()
    {
        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();
        modelo.setRowCount( 0 );
        List<ItemTurmaProfessorDisciplina> list = ivmDao.buscaTodosItemTurmaProfessorDisciplina( getIdProfessor() );
        if ( list != null )
        {
            for ( ItemTurmaProfessorDisciplina linha : list )
            {
                modelo.addRow( new Object[]
                {
                    linha.getFkTurma().getDesignacao(),
                    linha.getFkDisciplina().getDesignacao(),
                } );
            }
        }
        else
        {
            System.err.println( "null:falha ao mostrar" );
        }

    }

}
