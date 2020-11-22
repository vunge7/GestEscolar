/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author again
 */
public class PictureChooser {

        private Image image;
    private String caminhoImagem = null;
    private File arquivo;
    private JTextField txtImg = new JTextField();

    public PictureChooser() {

    }

    public byte[] getBystebyteImg() {
        
        
    
        try {
            BufferedImage bImage = ImageIO.read(arquivo);

            //OBTEM A IMAGEM E TRANSFORMA EM BYTES[]  
            ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bytesImg);//seta a imagem para bytesImg  \

            bytesImg.flush();//limpa a variável  
            byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[]   
            //bytesImg.close();//fecha a conversão  

            return byteArray;

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo vazio");
        
            System.out.println("Retornando Null..............");
            return null;
        }
        
    }

//    public void LoadImgDataBase(Aluno aluno, JLabel jlbImg) {
//        byte[] foto = null;
//
//        //LENDO E COPIANDO IMAGEM ##############################################  
//        BufferedImage img = null;
//        //reg = regDao.findById(7);  
//
//        try {
//            /* Tem q descomentar esse Esse metodo abaixo */
//            
//            System.out.println("IMAGEM EM BYTE "  +aluno.getPhoto());
//              
//            img = ImageIO.read( new ByteArrayInputStream( aluno.getPhoto() ) );
//            ImageIcon imageIcon_E = new ImageIcon(img);
//            imageIcon_E.setImage(imageIcon_E.getImage().getScaledInstance(jlbImg.getWidth(), jlbImg.getHeight(), 100));
//            jlbImg.setIcon(imageIcon_E);
//            ImageIO.write(img, "jpg", new File("foto.jpg"));
//            
//            
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block  
//            ImageIcon imageIcon_i;
//            imageIcon_i = new ImageIcon();
//            jlbImg.setIcon(null);
//            jlbImg.setVisible(false);
//            //e.printStackTrace();  
//        }
//    }

    public void chooseImg(JLabel lblImgMostrar) {

        JFileChooser fileChooser = new JFileChooser();

        
        if(  lblImgMostrar == null || lblImgMostrar.equals(""))
        {
                JOptionPane.showMessageDialog(null, "Teste Label");
        
        }
        
        
        
        int res = fileChooser.showOpenDialog(null);
 
        if (res == JFileChooser.APPROVE_OPTION)
        {
            arquivo = fileChooser.getSelectedFile();
            //JOptionPane.showMessageDialog(null, "Voce escolheu o arquivo: " + arquivo.getAbsolutePath());
            txtImg.setText("" + fileChooser.getSelectedFile().getAbsolutePath());

            caminhoImagem = "" + fileChooser.getSelectedFile().getAbsolutePath();

            ImageIcon imageIcon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            imageIcon.setImage( imageIcon.getImage().getScaledInstance(138, 140, 100) );

            //Image img = imageIcon.getImage();
            image = imageIcon.getImage();

            lblImgMostrar.setIcon(imageIcon);

            //JOptionPane.showMessageDialog(null, "Voce escolheu o arquivo: " + fileChooser.getSelectedFile().getAbsolutePath() );
        }
                //else
        //JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo."); 
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public JTextField getTxtImg() {
        return txtImg;
    }

    public void setTxtImg(JTextField txtImg) {
        this.txtImg = txtImg;
    }

    public byte[] getBystebyteImg(byte[] photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
