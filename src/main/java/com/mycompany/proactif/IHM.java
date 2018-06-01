/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.services.Services;
import java.util.Date;

/**
 *
 * @author antoinemathat
 */
public class IHM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client u1 = new Client("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456", new Date(), 6);
        Employe u2 = new Employe("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", "696965", 9,8);
        
        JpaUtil.init();
        
        Services.ajouterUtilisateur(u1);
        Services.ajouterUtilisateur(u2);
        
        if(Services.authentifier(u1.getEmail(), u1.getMotDePasse()))
            System.out.println("user 1 authentifié");
        if(!Services.authentifier(u1.getEmail(), u2.getMotDePasse()))
            System.out.println("user 2 non authentifié");
        
        Intervention monIntervention = new Intervention(u1, "LIvraison Colis", new Date(), "Le livreur passe à 17h30");
        
        Services.creerDemandeIntervention(monIntervention);
        
        //TODO :  Créer l'utlisateur en classe abstraite!!
    }
}
