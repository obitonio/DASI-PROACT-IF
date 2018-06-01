/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.entites.Animal;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Incident;
import com.mycompany.proactif.entites.Livraison;
import com.mycompany.proactif.services.Services;
import com.mycompany.proactif.util.Comparateur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        
        for(Intervention Inter : u1.getListeDesInterventions()){
            System.out.println("lola");
        }
        
        testTri(u1);
    }
    
    public static void testTri(Client u1)
    {
        List<Intervention> listeInterventions = new ArrayList<>();
        listeInterventions.add(new Incident(u1, "LIvraison A", new Date(), "Le livreur passe à 17h30","Fuite d'eau", "URGENT"));
        listeInterventions.add(new Incident(u1, "LIvraison B", new Date(), "Le livreur passe à 17h30","Toilettes bouchés", "À TRAITER"));
        listeInterventions.add(new Incident(u1, "LIvraison C", new Date(), "Le livreur passe à 17h30","Gouttières bouchées", "URGENT"));
        
        listeInterventions.add(new Animal(u1, "LIvraison D", new Date(), "Le livreur passe à 17h30","Nourir mon chat", "Tigrou", "CHAT"));
        listeInterventions.add(new Animal(u1, "LIvraison E", new Date(), "Le livreur passe à 17h30","Sortir mon chien", "Théo", "CHIEN"));
        listeInterventions.add(new Animal(u1, "LIvraison F", new Date(), "Le livreur passe à 17h30","Nourir mon serpent, attention à pas vous faire mordre", "Rex", "Serpent"));
        
        listeInterventions.add(new Livraison(u1, "Livraison G", new Date(), "Le livreur passe à 17h30","Récupérer colis", "Jean", "01/06/2018 - 11:00", "093KS38U375"));
        listeInterventions.add(new Livraison(u1, "Livraison H", new Date(), "Le livreur passe à 17h30","Récupérer colis", "Faissal", "01/06/2018 - 12:00", "093KS38U374"));
        listeInterventions.add(new Livraison(u1, "Livraison I", new Date(), "Le livreur passe à 17h30","Récupérer colis", "Arthur", "01/06/2018 - 6:00", "093KS38U373"));
        
        Comparateur monComparateur = new Comparateur(Comparateur.FILTRES.INTITULE, true);
        
        Collections.sort(listeInterventions, monComparateur);

        
        for (Intervention ti : listeInterventions) {
            System.out.println(ti.toString());
        }
    }
    
    public static void testClassAdresse()
    {
        Adresse a1 = new Adresse(10, "Rue des pommes", "69100", "Villeurbanne", "Digicode 9029", "Lont: Lat:");
        System.out.println(a1.toString());
    }
}
