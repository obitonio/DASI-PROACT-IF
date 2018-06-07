/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.entites.Livraison;
import com.mycompany.proactif.services.Services;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author antoinemathat
 */
public class IHM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        // TODO 
        /**
         * Tout séparer dans des méthodes
         * Faire un scenario employé (authentification + cloturer intervention)
         * Faire un scenario client (authentification + créer intervention)
         * Utiliser les méthodes de Saisie.java
         */
        
        
        JpaUtil.init();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        // Initialisation de quelques employés
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adrEmp3 = new Adresse(46, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Employe employe1 = new Employe("Jean", "Neymar", format.parse("04/10/1980"), "0690239405", "jhameau@insa-lyon.fr", "1234567", "9NKL675", 1300,1);
        Employe employe2 = new Employe("Théo", "Benzenma", format.parse("17/02/1990"), "0923849605", "tt@gmail.com", "1234567", "6ABC965", 1600,1); 
        Employe employe3 = new Employe("Léo", "Pomp",format.parse("01/12/1976"), "0678301074", "lp@gmail.com", "1234567", "0NJE304", 1400,1); 
        employe1.setAdresse(adresseVrai2);
        employe2.setAdresse(adresseFausse2);
        employe3.setAdresse(adrEmp3);
        Services.creerUtilisateur(employe1);
        Services.creerUtilisateur(employe2);
        Services.creerUtilisateur(employe3);
        // ====
      
           
        // Création du client 
        Client u1 = new Client("Antoine", "Mathat", format.parse("29/08/1997"), "0677500460", "amathat@insa-lyon.fr", "123456", format.parse("7/06/2018"), 6);
        Adresse adrCli3 = new Adresse(45, "Rue de bruxelles", "69100", "Villeurbanne", "");
        u1.setAdresse(adrCli3);
        Services.creerUtilisateur(u1);
        // ====
        
        // Création de l'intervention 
        Intervention i1 = new Livraison(u1, "Livraison G", "Le livreur passe à 18h30", "Jean", "01/06/2018 - 18:30", "093KS38U375");
        // ====
        
        System.out.println("Les employés actuellement présent :");
        System.out.println(employe1.toString());
        System.out.println(employe2.toString());
        System.out.println(employe3.toString());
        
        System.out.println("Tentative de connexion :");
        System.out.println("Identifiant : amathat@insa-lyon.fr");
        System.out.println("Mot de passe : 123456");
        System.out.println("Authentification");
        Client u2 = (Client) Services.authentifier("amathat@insa-lyon.fr", "123456");
        System.out.println("Utilisateur connecté :\n" + u2.toString());
        
        System.out.println("Création d'une intervention :");
        System.out.println(i1.toString());
        Services.creerDemandeIntervention(u2, i1);
        System.out.println("----> Intervention créée");
 
    }
    
    private static void scenarioClient() {
        
    }
    
    private static void scenarioEmploye() {
        
    }
    
    private static void initialiserJeuEssai() {
        
    }
}
