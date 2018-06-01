/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.entites.Animal;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Incident;
import com.mycompany.proactif.entites.Livraison;
import com.mycompany.proactif.entites.TypeIntervention;
import com.mycompany.proactif.services.Services;
import com.mycompany.proactif.util.Comparateur;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
        

        IHM.testClassTypeIntervention();
        testClassAdresse();
    }
    
    public static void testClassTypeIntervention()
    {
        List<TypeIntervention> listeTypesInterventions = new ArrayList<TypeIntervention>();
        listeTypesInterventions.add(new Incident("Fuite d'eau", "URGENT"));
        listeTypesInterventions.add(new Incident("Toilettes bouchés", "À TRAITER"));
        listeTypesInterventions.add(new Incident("Gouttières bouchées", "URGENT"));
        
        listeTypesInterventions.add(new Animal("Nourir mon chat", "Tigrou", "CHAT"));
        listeTypesInterventions.add(new Animal("Sortir mon chien", "Théo", "CHIEN"));
        listeTypesInterventions.add(new Animal("Nourir mon serpent, attention à pas vous faire mordre", "Rex", "Serpent"));
        
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "01/06/2018 - 10:00", "093KS38U374"));
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "03/06/2018 - 14:30", "0734738U374"));
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "08/06/2018 - 11:00", "1934738U3ZR"));
        
        for (TypeIntervention ti : listeTypesInterventions) {
            System.out.println(ti.toString());
        }
        
        testTri();
    }
    
    public static void testTri()
    {
        List<TypeIntervention> listeTypesInterventions = new List<TypeIntervention>();
        listeTypesInterventions.add(new Incident("Fuite d'eau", "URGENT"));
        listeTypesInterventions.add(new Incident("Toilettes bouchés", "À TRAITER"));
        listeTypesInterventions.add(new Incident("Gouttières bouchées", "URGENT"));
        
        listeTypesInterventions.add(new Animal("Nourir mon chat", "Tigrou", "CHAT"));
        listeTypesInterventions.add(new Animal("Sortir mon chien", "Théo", "CHIEN"));
        listeTypesInterventions.add(new Animal("Nourir mon serpent, attention à pas vous faire mordre", "Rex", "Serpent"));
        
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "01/06/2018 - 10:00", "093KS38U374"));
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "03/06/2018 - 14:30", "0734738U374"));
        listeTypesInterventions.add(new Livraison("Récupérer colis", "Faissal", "08/06/2018 - 11:00", "1934738U3ZR"));
        
        Comparateur monComparateur = new Comparateur(Comparateur.FILTRES.DATE, true);
        
        Collections.sort(listeTypesInterventions, monComparateur);
        
        for (TypeIntervention ti : listeTypesInterventions) {
            System.out.println(ti.toString());
        }
    }
    
    public static void testClassAdresse()
    {
        Adresse a1 = new Adresse(10, "Rue des pommes", "69100", "Villeurbanne", "Digicode 9029", "Lont: Lat:");
        System.out.println(a1.toString());
    }
}
