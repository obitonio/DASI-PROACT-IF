/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.entites.Animal;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Incident;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.entites.Livraison;
import com.mycompany.proactif.services.Services;
import com.mycompany.proactif.services.Services.RetourCreationIntervention;
import com.mycompany.proactif.util.DebugLogger;
import com.mycompany.proactif.util.Saisie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antoinemathat
 */
public class IHM {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
   
         JpaUtil.init();
        
        // À activer lors de la première utilisation pour initialiser les employés
        initialiserJeuEssai();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Adresse adresseVrai = new Adresse(52, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Employe employe1 = new Employe("M","Georges", "Proset", format.parse("05/12/1986"), "0689230495", "proset@test.fr", "proset", true);
        employe1.setAdresse(adresseVrai);
        Services.creerUtilisateur(employe1);
        
        // Consulter la javadoc pour plus de détails 
        //scenarioInscription();
        //scenarioEmploye();
    }
    
    /**
     * Cette méthode permet de simuler un scénario d'inscription et de demande d'intervention
     * Étape 1 : Saisie des informations et inscription d'un client
     * Étape 2 : Authenfication automatique à partir des informations saisies
     * Étape 3 : Saisie et création d'une demande d'intervention
     * @throws ParseException 
     */
    private static void scenarioInscription() throws ParseException {
        
        // ====== Inscription
        String civilite;
        String nom;
        String prenom;
        String dateNaissance;
        String telephone;
        String mail;
        String motDePasse;
        
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       civilite = Saisie.lireChaine("Saisissez votre civilite : ");
       nom = Saisie.lireChaine("Saisissez votre nom : ");
       prenom = Saisie.lireChaine("Saisissez votre prenom : ");
       dateNaissance = Saisie.lireChaine("Saisissez votre date de Naissance \"dd/MM/yyyy\" : ");
       telephone = Saisie.lireChaine("Saisissez votre telephone : ");
       mail = Saisie.lireChaine("Saisissez votre mail : ");
       motDePasse = Saisie.lireChaine("Saisissez votre mot De Passe : "); 
       Client c1 = new Client(civilite, prenom, nom, format.parse(dateNaissance), telephone, mail, motDePasse);
       c1.setAdresse(new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", ""));
       c1 = (Client) Services.creerUtilisateur(c1);
        
       // ====== Authentification
       Services.authentifier(c1.getEmail(), c1.getMotDePasse());
       System.out.println("====== Authentification réussie");
        
       // ====== Demande d'intervention
       System.out.println("Saisir une demande d'intervention");
       System.out.println("\t1. Animal");
       System.out.println("\t2. Livraison");
       System.out.println("\t3. Incident");
       int typeIntervention = Saisie.lireInteger("Entrer le numéro de votre choix : ");
       
       String intitule = Saisie.lireChaine("Saisir l'intitulé : ");
       String descriptionClient = Saisie.lireChaine("Saisir la description : ");
       
       Intervention inter = null;
       switch (typeIntervention)
       {
           case 1: // Animal
               String nomAnimal = Saisie.lireChaine("Saisir le nom de l'animal : ");
               String typeAnimal = Saisie.lireChaine("Saisir son type (chien, chat...) : ");
               inter = new Animal(intitule, descriptionClient, nomAnimal, typeAnimal);
               break;
           case 2: // Livraison
               String heurePassage = Saisie.lireChaine("Saisir la date et l'heure de livraison (dd/MM/yyyy - hh:mm) : ");
               String codeSuivi = Saisie.lireChaine("Saisir le code de suivi : ");
               String type = Saisie.lireChaine("Saisir le type du colis : ");
               String entreprise = Saisie.lireChaine("Saisir le nom de l'entreprise de livraison : ");
               inter = new Livraison(intitule, descriptionClient, heurePassage, codeSuivi, type, entreprise);
               break;
           case 3: // Incident
               inter = new Incident(intitule, descriptionClient);
               break;
       }
      
       RetourCreationIntervention retour = Services.creerDemandeIntervention(c1, inter);
       
       if (retour == RetourCreationIntervention.Succes)
           System.out.println("Intervention créée avec succès.");
       else if (retour == RetourCreationIntervention.AucunEmployeDisponible)
           System.out.println("Aucun employé ne peut traiter votre demande, merci de réessayer ultérieurement.");
       else
           DebugLogger.log("Erreur avec la base de données");  
    }
    
    /**
     * Cette méthode permet de simuler un scénario de connexion et validation d'intervention
     * Étape 1 : L'employé doit s'authentifier
     * Étape 2 : Il consulte la liste de ses interventions
     * Étape 3 : L'employé saisie les informations de validation relatives à une intervention
     */
    private static void scenarioEmploye() {
        // Authentifiation
        String email = Saisie.lireChaine("Saisir votre adresse email : ");
        String motDePasse = Saisie.lireChaine("Saisir votre mot de passe : ");
        Employe e = (Employe) Services.authentifier(email, motDePasse);
        
        if (e != null) {
            Services.recupererToutesLesIntervention(e);
            
            
            List<Intervention> lesInterventions = e.getListeDesInterventions();
            for (int i = 0; i < lesInterventions.size(); i++) {
                System.out.println("Numéro " + i);
                System.out.println(lesInterventions.get(i).toString());
            }
            
            int numeroInter = Saisie.lireInteger("Saisir le numéro de l'intervention à valider : ");
            
            String commentaire = Saisie.lireChaine("Saisir un commentaire : ");
            int codeEtat = Saisie.lireInteger("Saisir le code état : ");
            
            if (Services.RetourTerminerIntervention.Succes == Services.terminerIntervention(lesInterventions.get(numeroInter).getId(), commentaire, codeEtat)) {
                System.out.println("La cloture de l'intervention s'est faîte avec succès.");
            }
            else {
                System.out.println("Erreur lors de la cloture de l'intervention.");
            }
        }
        else {
            System.out.println("Echec lors de l'authenfication.");
        }    
    }
    
    /**
     * Initialisation des employés
     * @throws ParseException 
     */
    private static void initialiserJeuEssai() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        // Initialisation de quelques employés
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseVrai3 = new Adresse(77, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adrEmp3 = new Adresse(46, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Employe employe1 = new Employe("M","Jean", "Neymar", format.parse("04/10/1980"), "0690239405", "jhameau@insa-lyon.fr", "1234567", true);
        Employe employe2 = new Employe("M","Théo", "Phile", format.parse("17/02/1990"), "0923849605", "tt@gmail.com", "1234567", true); 
        Employe employe3 = new Employe("M","Léo", "Pomp",format.parse("01/12/1976"), "0678301074", "lp@gmail.com", "1234567", true); 
        Intervention i1 = new Incident("Fuite eau", "De l'eau coule derrière le robinet de la cuisine");
        Intervention i2 = new Incident("Toilettes bouchées", "Mes toilettes sont bouchées");
        Intervention i3 = new Incident("Problème avec ma gouttière", "Les feuilles ont bouchés ma gouttière");
        employe1.setAdresse(adresseVrai3);
        employe2.setAdresse(adresseFausse2);
        employe3.setAdresse(adrEmp3);
        
        Client client1 = new Client("M","Mathat", "Antoine", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456");
        client1.setAdresse(adresseVrai2);
        client1 = (Client) Services.creerUtilisateur(client1);
        client1 = (Client) Services.authentifier(client1.getEmail(), client1.getMotDePasse());
        Services.creerUtilisateur(employe1);
        Services.creerUtilisateur(employe2);
        Services.creerUtilisateur(employe3);
        
        if(Services.creerDemandeIntervention(client1, i1)== RetourCreationIntervention.Succes)
            System.out.println("OK");
        else
            System.out.println("PAS OK");
        if(Services.creerDemandeIntervention(client1, i2)==RetourCreationIntervention.Succes)
            System.out.println("OK");
        else
            System.out.println("PAS OK");
        if(Services.creerDemandeIntervention(client1, i3)==RetourCreationIntervention.AucunEmployeDisponible)
            System.out.println("OK");
        else
            System.out.println("PAS OK");
        // ====
        
        Services.terminerIntervention(i2.getId(), "lol", 1);
        System.out.println(i1.toString() + i2.toString() + i3.toString());
    }
}
