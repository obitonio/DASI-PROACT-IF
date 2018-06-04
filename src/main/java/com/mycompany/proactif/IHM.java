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
        
        testRecherche(u1);
    }
    public static void testRecherche(Client u1)
    {
        List<Intervention> listeInterventions = new ArrayList<>();
        listeInterventions.add(new Incident(u1, "Fuite eau", "De l'eau coule derrière le robinet de la cuisine", "URGENT"));
        listeInterventions.add(new Incident(u1, "Toilettes bouchées", "Mes toilettes sont bouchées", "À TRAITER"));
        listeInterventions.add(new Incident(u1, "Problème avec ma gouttière", "Les feuilles ont bouchés ma gouttière", "URGENT"));
        
        listeInterventions.add(new Animal(u1, "Nourir mon chat", "Les croquettes sont dans le placard d ela cuisine", "Tigrou", "CHAT"));
        listeInterventions.add(new Animal(u1, "Sortir mon chien", "La laisse est sur le porte manteau", "Théo", "CHIEN"));
        listeInterventions.add(new Animal(u1, "Nourir mon serpent", "Attention à pas vous faire mordre", "Rex", "Serpent"));
        
        listeInterventions.add(new Livraison(u1, "Livraison G", "Le livreur passe à 18h30", "Jean", "01/06/2018 - 18:30", "093KS38U375"));
        listeInterventions.add(new Livraison(u1, "Livraison H", "Le livreur passe à 11h30","Faissal", "04/06/2018 - 11:30", "093KS38U374"));
        listeInterventions.add(new Livraison(u1, "Livraison I", "Le livreur passe à 17h00", "Arthur", "12/06/2018 - 17:00", "093KS38U373"));
        
        listeInterventions = Services.rechercher(listeInterventions,"NouRIR");

        
        for (Intervention ti : listeInterventions) {
            System.out.println(ti.toString());
        }
    }
    public static void testTri(Client u1)
    {
        List<Intervention> listeInterventions = new ArrayList<>();
        listeInterventions.add(new Incident(u1, "Fuite eau", new Date(), "Le livreur passe à 17h30", "URGENT"));
        listeInterventions.add(new Incident(u1, "Toilettes bouchées", new Date(), "Le livreur passe à 17h30", "À TRAITER"));
        listeInterventions.add(new Incident(u1, "Problème avec ma gouttière", new Date(), "Le livreur passe à 17h30", "URGENT"));
        
        listeInterventions.add(new Animal(u1, "Nourir mon chat", new Date(), "Le livreur passe à 17h30", "Tigrou", "CHAT"));
        listeInterventions.add(new Animal(u1, "Sortir mon chien", new Date(), "Le livreur passe à 17h30", "Théo", "CHIEN"));
        listeInterventions.add(new Animal(u1, "Nourir mon serpent, attention à pas vous faire mordre", new Date(), "Le livreur passe à 17h30", "Rex", "Serpent"));
        
        listeInterventions.add(new Livraison(u1, "Livraison G", new Date(), "Le livreur passe à 17h30", "Jean", "01/06/2018 - 11:00", "093KS38U375"));
        listeInterventions.add(new Livraison(u1, "Livraison H", new Date(), "Le livreur passe à 17h30","Faissal", "01/06/2018 - 12:00", "093KS38U374"));
        listeInterventions.add(new Livraison(u1, "Livraison I", new Date(), "Le livreur passe à 17h30", "Arthur", "01/06/2018 - 6:00", "093KS38U373"));
        
        Comparateur monComparateur = new Comparateur(Comparateur.FILTRES.INTITULE, true);
        
        Collections.sort(listeInterventions, monComparateur);

        
        for (Intervention ti : listeInterventions) {
            System.out.println(ti.toString());
        }
    }
    
    public static void testClassAdresse()
    {
        Adresse a1 = new Adresse(10, "Rue des pommes", "69100", "Villeurbanne", "Digicode 9029");
        System.out.println(a1.toString());
    }
    
    public static void testCreerUtilisateur() {
        Employe e1 = new Employe("Mathat", "Antoine", new Date("08/29/1997"), "0689093475", "amathat@insa-lyon.fr", "AntMat", "0934", 0, 0);
        Services.creerUtilisateur(e1); 
    }
}
