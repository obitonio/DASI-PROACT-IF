/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.DAOUtilisateur;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.services.Services;
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
        Utilisateur u2 = new Utilisateur("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567");
        
        JpaUtil.init();
        
        Services.ajouterUtilisateur(u1);
        Services.ajouterUtilisateur(u2);
        
        if(Services.authentifier(u1.getEmail(), u1.getMotDePasse()))
            System.out.println("user 1 authentifié");
        if(!Services.authentifier(u1.getEmail(), u2.getMotDePasse()))
            System.out.println("user 2 non authentifié");
    }
}
