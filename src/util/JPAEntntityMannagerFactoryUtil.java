/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Domingos Dala Vunge & Martinho Canhongo Luis
 */
public class JPAEntntityMannagerFactoryUtil {
    
    public static EntityManagerFactory em  =  Persistence.createEntityManagerFactory("Gest_EscolarPU");
    
    public static void main(String[] args) {
        new JPAEntntityMannagerFactoryUtil();
    }
    
}
