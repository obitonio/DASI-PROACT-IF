/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.dao.DAOUtilisateur;
import com.mycompany.proactif.dao.JpaUtil;
import java.util.Date;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author antoinemathat
 */
public class IHM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utilisateur u1 = new Utilisateur("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456");
        Utilisateur u2 = new Utilisateur("Jean", "Hameau", new Date(), "0690239405", "jHameau@insa-lyon.fr", "123456");
        
        JpaUtil.init();
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
  
        DAOUtilisateur daoTest = new DAOUtilisateur();
        daoTest.create(u1);
        daoTest.create(u2);
        
        JpaUtil.validerTransaction();
        
        daoTest.findById((long)2);
        Utilisateur uN = daoTest.getLocalObject();
        System.out.println(uN.getPrenom() + " " + uN.getNom() + " " + uN.getTelephone());
        
        JpaUtil.fermerEntityManager();
        
        System.out.println("PROACT'IF");
    }
}
