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
//import dao.FuncionarioDao;
import dao.SalaDao;
import dao.TurnoDao;
import dao.AnoLectivoDao;
import dao.CursoDao;
import dao.ClasseDao;

import entity.Turma;
//import entity.Funcionario;
import entity.Turno;
import entity.Sala;

import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.JPAEntntityMannagerFactoryUtil;

/**
 *
 * @author Necia Caculo
 */
public class TurmaVisao extends javax.swing.JFrame
{

    private EntityManagerFactory emf = JPAEntntityMannagerFactoryUtil.em;

    private Turma turmas;
    private TurmaDao turmaDao = new TurmaDao( emf );
    //private FuncionarioDao funcionarioDao = new FuncionarioDao(emf);
    private SalaDao salaDao = new SalaDao( emf );
    private CursoDao cursoDao = new CursoDao( emf );
    private ClasseDao classeDao = new ClasseDao( emf );
    private AnoLectivoDao anolectivoDao = new AnoLectivoDao( emf );
    private TurnoDao turnoDao = new TurnoDao( emf );
    private int cod_turma = 0, idUser = 0;
    private String nome, sobrenome;

    public TurmaVisao( int idUser )
    {

        initComponents();
        setLocationRelativeTo( null );
        setCampos();
        actualizar();
        this.idUser = idUser;

    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbAno = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbSala = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTurno = new javax.swing.JComboBox<String>();
        jLabel8 = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cmbClasse = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ERP_UAN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(192, 208, 232));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("CADASTRO DE TURMA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Ano Lectivo:");

        cmbAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Designacao:* ");

        txtTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTurmaActionPerformed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N.º", "Turma", "Ano Lectivo", "Sala", "Turno", "Curso", "Classe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(50);

        cmbSala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSalaActionPerformed(evt);
            }
        });

        jLabel5.setText("Sala:");

        jLabel6.setText("Turno:");

        cmbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Curso:");

        cmbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCursoActionPerformed(evt);
            }
        });

        jLabel9.setText("Classe:");

        cmbClasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbSala, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbClasse, 0, 251, Short.MAX_VALUE)
                                    .addComponent(cmbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(57, 57, 57))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/6665_32x32.png"))); // NOI18N
        jToggleButton1.setText("Salvar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar_32x32_1.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/2934_32x32.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(5, 5, 5)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTurmaActionPerformed

    }//GEN-LAST:event_txtTurmaActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        salvar();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        alterar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        procedimento_eliminar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if ( evt.getClickCount() >= 2 )
        {

            mostrarDadosTurma();

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cmbAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnoActionPerformed
        // TODO add your handling code here:
        //setCargoFuncionario();

    }//GEN-LAST:event_cmbAnoActionPerformed

    private void cmbSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSalaActionPerformed
        // TODO add your handling code here:

//        try {
//            cmbSala.setModel( new DefaultComboBoxModel(   salaDao.buscaTodosByIdAnoLectivo(getIdAnoLectivo() )   ) );
//        } catch (Exception e) {
//            e.printStackTrace();
//            cmbSala.removeAllItems();
//        }
    }//GEN-LAST:event_cmbSalaActionPerformed

    private void cmbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCursoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] )
    {
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new TurmaVisao( 1 ).setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbAno;
    private javax.swing.JComboBox<String> cmbClasse;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JComboBox cmbSala;
    private javax.swing.JComboBox<String> cmbTurno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField txtTurma;
    // End of variables declaration//GEN-END:variables

    private void adicionar_tabela( List<Turma> turmas )
    {

        DefaultTableModel sala = ( DefaultTableModel ) jTable1.getModel();

        sala.setRowCount( 0 );
        for ( int i = 0; i < turmas.size(); i++ )
        {
            sala.addRow( new Object[]
            {
                turmas.get( i ).getPkTurma(),
                turmas.get( i ).getDesignacao(),
                turmas.get( i ).getFkAnoLectivo().getDesignacao(),
                turmas.get( i ).getFkSala().getDesignacao(),
                turmas.get( i ).getFkTurno().getDesignacao(),
                turmas.get( i ).getFkCurso().getDesignacao(),
                turmas.get( i ).getFkClasse().getDesignacao()

            } );
        }

    }

    private void actualizar()
    {
        adicionar_tabela( turmaDao.buscaTodosTurma() );
    }

    public void salvar()
    {
        if ( !vazios_campos() )
        {
            if ( !turmaDao.exist_turma( txtTurma.getText() ) )
            {
                try
                {
                    turmas = new Turma();
                    setDadosTurma();
                    System.out.println( "ID UNIDADE " + turmas.getFkTurno().getPkTurno() );
                    turmaDao.create( turmas );
                    actualizar();
                    limpar();
                    JOptionPane.showMessageDialog( null, "Dados salvos com sucesso!..." );

                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog( null, "Erro ao cadastrar o turma ", "ERRO", JOptionPane.ERROR_MESSAGE );
                }

            }
            else
            {
                JOptionPane.showMessageDialog( null, "Caro Usuário esta turma já existe ", "AVISO", JOptionPane.WARNING_MESSAGE );
                limpar();
            }

        }
    }

    private String getDescricaoSala()
    {
        return String.valueOf( cmbSala.getSelectedItem() );
    }

    private String getDescricaoAnoLectivo()
    {
        return String.valueOf( cmbAno.getSelectedItem() );
    }

    private String getDescricaoTurno()
    {
        return String.valueOf( cmbTurno.getSelectedItem() );
    }

    private String getDescricaoCurso()
    {
        return String.valueOf( cmbCurso.getSelectedItem() );
    }

    private String getDescricaoClasse()
    {
        return String.valueOf( cmbClasse.getSelectedItem() );
    }

    private boolean vazios_campos()
    {

        if ( txtTurma.getText().equals( "" ) )
        {
            JOptionPane.showMessageDialog( null, "Por favor! Digite o nome da da Turma", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }
        else if ( cmbAno.getSelectedIndex() == 0 )
        {

            JOptionPane.showMessageDialog( null, "por favor! Selecione o ano lectivo ", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }
        else if ( cmbSala.getSelectedIndex() == 0 )
        {

            JOptionPane.showMessageDialog( null, "por favor! Selecione a sala desta turma", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }
        else if ( cmbTurno.getSelectedIndex() == 0 )
        {

            JOptionPane.showMessageDialog( null, "por favor! Selecione o turno", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }

        else if ( cmbCurso.getSelectedIndex() == 0 )
        {

            JOptionPane.showMessageDialog( null, "por favor! Selecione o curso", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }

        else if ( cmbClasse.getSelectedIndex() == 0 )
        {

            JOptionPane.showMessageDialog( null, "por favor! Selecione a Classe", "AVISO", JOptionPane.WARNING_MESSAGE );
            txtTurma.requestFocus();
            return true;

        }

        return false;

    }

    private void setCampos()
    {

        cmbAno.setModel( new DefaultComboBoxModel( anolectivoDao.buscaTodos() ) );
        cmbSala.setModel( new DefaultComboBoxModel( salaDao.buscaTodos() ) );
        cmbTurno.setModel( new DefaultComboBoxModel( turnoDao.buscaTodos() ) );
        cmbCurso.setModel( new DefaultComboBoxModel( cursoDao.buscaTodos() ) );
        cmbClasse.setModel( new DefaultComboBoxModel( classeDao.buscaTodos() ) );

    }

    public void setDadosTurma()
    {

        turmas.setDesignacao( txtTurma.getText().toUpperCase() );
        turmas.setFkAnoLectivo( anolectivoDao.findAnoLectivo( anolectivoDao.getIdByDescricao( getDescricaoAnoLectivo() ) ) );
        turmas.setFkSala( salaDao.findSala( salaDao.getIdByDescricao( getDescricaoSala() ) ) );
        turmas.setFkTurno( turnoDao.findTurno( turnoDao.getIdByDescricao( getDescricaoTurno() ) ) );
        turmas.setFkCurso( cursoDao.findCurso( cursoDao.getIdByDescricao( getDescricaoCurso() ) ) );
        turmas.setFkClasse( classeDao.findClasse( classeDao.getIdByDescricao( getDescricaoClasse() ) ) );

    }

    public void mostrarDadosTurma()
    {

        DefaultTableModel modelo = ( DefaultTableModel ) jTable1.getModel();

        String designacao = modelo.getValueAt( jTable1.getSelectedRow(), 1 ).toString();
        turmas = turmaDao.getTurmaByDesignacao( designacao );

        if ( turmas != null )
        {
            txtTurma.setText( turmas.getDesignacao() );
            cmbAno.setSelectedItem( turmas.getFkAnoLectivo().getDesignacao() );
            cmbSala.setSelectedItem( turmas.getFkSala().getDesignacao() );
            cmbTurno.setSelectedItem( turmas.getFkTurno().getDesignacao() );
            cmbCurso.setSelectedItem( turmas.getFkCurso().getDesignacao() );
            cmbClasse.setSelectedItem( turmas.getFkClasse().getDesignacao() );
        }
        else
        {
            System.err.println( "null" );
        }

    }

    public void alterar()
    {

        if ( !vazios_campos() )
        {
            try
            {
                //turmas = turmaDao.findTurma( this.cod_turma );
                setDadosTurma();
                turmaDao.edit( turmas );
                actualizar();
                limpar();
                JOptionPane.showMessageDialog( null, "Dados alterados com sucesso!..." );

            }
            catch ( Exception e )
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog( null, "Erro ao alterar o cliente ", "ERRO", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    public void limpar()
    {
        txtTurma.setText( "" );

        cmbAno.setSelectedIndex( 0 );
        cmbSala.setSelectedIndex( 0 );
        cmbTurno.setSelectedIndex( 0 );
        cmbCurso.setSelectedIndex( 0 );
        cmbClasse.setSelectedIndex( 0 );

        this.cod_turma = 0;

    }

    private int getIdAnoLectivo()
    {
        return anolectivoDao.getIdByDescricao( cmbAno.getSelectedItem().toString() );

    }

    private void procedimento_eliminar()
    {

        if ( this.cod_turma != 0 )
        {

            int opcao = JOptionPane.showConfirmDialog( null, "Tens a certeza que pretendes eliminar esse turma de matricula : " + txtTurma.getText() );

            if ( opcao == JOptionPane.YES_OPTION )
            {

                eliminar();

            }
            else if ( opcao == JOptionPane.NO_OPTION )
            {
                JOptionPane.showMessageDialog( null, "Veículo não eliminado", "INFO", JOptionPane.INFORMATION_MESSAGE );
            }
            else if ( opcao == JOptionPane.CANCEL_OPTION )
            {
                JOptionPane.showMessageDialog( null, "Operação cancelada" );
            }

        }
        else
        {
            JOptionPane.showMessageDialog( null, "Pf seleccione o veículo", "AVISO", JOptionPane.WARNING_MESSAGE );
        }

    }

    private void eliminar()
    {
        try
        {
            turmaDao.destroy( this.cod_turma );
            actualizar();
            limpar();
            JOptionPane.showMessageDialog( null, "Turma eliminado com sucesso!..." );

        }
        catch ( Exception e )
        {
            JOptionPane.showMessageDialog( null, "Erro ao eliminar o turma", "ERRO", JOptionPane.ERROR_MESSAGE );
        }

    }

}
