/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.dao.DAOEmploye;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.util.Comparateur;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.entites.Animal;
import com.mycompany.proactif.entites.Incident;
import com.mycompany.proactif.entites.Livraison;
import java.util.ArrayList;

/**
 *
 * @author antoinemathat
 */
public class ServicesTest {
    
    public ServicesTest() {
    }
    
    private static Client client1, client2, client3;
    
    private static Employe employe1, employe2;
    
    Intervention i1 = new Incident(client1, "Fuite eau", "De l'eau coule derrière le robinet de la cuisine", "URGENT");
    Intervention i2 = new Incident(client2, "Toilettes bouchées", "Mes toilettes sont bouchées", "À TRAITER");
    Intervention i3 = new Incident(client1, "Problème avec ma gouttière", "Les feuilles ont bouchés ma gouttière", "URGENT");

    Intervention a1 = new Animal(client1, "Nourir mon chat", "Les croquettes sont dans le placard d ela cuisine", "Tigrou", "CHAT");
    Intervention a2 = new Animal(client2, "Sortir mon chien", "La laisse est sur le porte manteau", "Théo", "CHIEN");
    Intervention a3 = new Animal(client1, "Nourir mon serpent", "Attention à pas vous faire mordre", "Rex", "Serpent");

    Intervention l1 = new Livraison(client2, "Livraison G", "Le livreur passe à 18h30", "Jean", "01/06/2018 - 18:30", "093KS38U375");
    Intervention l2 = new Livraison(client2, "Livraison H", "Le livreur passe à 11h30","Faissal", "04/06/2018 - 11:30", "093KS38U374");
    Intervention l3 = new Livraison(client1, "Livraison I", "Le livreur passe à 17h00", "Arthur", "12/06/2018 - 17:00", "093KS38U373");
    
    @BeforeClass
    public static void setUpClass() {
        
        JpaUtil.init();
     
        Adresse adresseVrai = new Adresse(44, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse = new Adresse(90, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        
        client1 = new Client("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456", new Date(), 6);
        client2 = new Client("George", "Ration", new Date(), "0467382904", "ration@yahoo.fr", "123456", new Date(), 6);
        client3 = new Client("Pierre", "Kiroule", new Date(), "0467382904", "pierre@yahoo.fr", "123456", new Date(), 6);
        client1.setAdresse(adresseVrai);
        client2.setAdresse(adresseFausse);
        
        employe1 = new Employe("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", "696965", 9,8);
        employe2 = new Employe("Théo", "Benzenma", new Date(), "0923849605", "tt@gmail.com", "1234567", "696965", 9,8); 
        employe1.setAdresse(adresseVrai2);
        employe2.setAdresse(adresseFausse2);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of creerUtilisateur method, of class Services.
     */
    @Test
    public void testCreerUtilisateur() {
        System.out.println("creerUtilisateur");
   
        // Création des utilisateurs dans la base
        assertEquals(Services.RetourCreationUtilisateur.Succes, Services.creerUtilisateur(client1));
        assertEquals(Services.RetourCreationUtilisateur.LatLngIntrouvable, Services.creerUtilisateur(client2));
        assertEquals(Services.RetourCreationUtilisateur.Succes, Services.creerUtilisateur(employe1));
        assertEquals(Services.RetourCreationUtilisateur.LatLngIntrouvable, Services.creerUtilisateur(client2));  
    }

    /**
     * Test of authentifier method, of class Services.
     */
    @Test
    public void testAuthentifier() {
        System.out.println("authentifier");
        
        Services.creerUtilisateur(client1);
        Services.creerUtilisateur(employe1);
        
        assertTrue(Services.authentifier(client1.getEmail(), client1.getMotDePasse()));
        assertTrue(Services.authentifier(employe1.getEmail(), employe1.getMotDePasse()));
        
        assertFalse(Services.authentifier(client2.getEmail(), client2.getMotDePasse()));
        assertFalse(Services.authentifier(employe2.getEmail(), employe2.getMotDePasse()));
    }

    /**
     * Test of trierListe method, of class Services.
     */
    @Test
    public void testTrierListe() {
        System.out.println("trierListe");
        boolean croissant;
        boolean expResult;
        
        
        
        //DATE
        List<Intervention> expectedListeFiltreDate = new ArrayList<>();
        croissant = true;
        Comparateur.FILTRES monFiltre = Comparateur.FILTRES.DATE;
        boolean result = Services.trierListe(listeInterventions, monFiltre, croissant);
        assertEquals(true, result);
    }

    /**
     * Test of rechercher method, of class Services.
     */
    @Test
    public void testRechercher() {
        System.out.println("rechercher");
        List<Intervention> listeIntervention = null;
        String motClef = "";
        List<Intervention> expResult = null;
        List<Intervention> result = Services.rechercher(listeIntervention, motClef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creerDemandeIntervention method, of class Services.
     */
    @Test
    public void testCreerDemandeIntervention() {
        System.out.println("creerDemandeIntervention");
        boolean expResult = false;
        boolean result = Services.creerDemandeIntervention(a1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
