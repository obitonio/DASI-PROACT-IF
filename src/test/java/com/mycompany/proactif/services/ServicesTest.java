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
import com.mycompany.proactif.dao.DAOClient;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Adresse;
import com.mycompany.proactif.util.DebugLogger;

/**
 *
 * @author antoinemathat
 */
public class ServicesTest {
    
    public ServicesTest() {
    }
    
    private static Client client1, client2;
    
    private static Employe employe1, employe2;
    
    @BeforeClass
    public static void setUpClass() {
        
        JpaUtil.init();
     
        Adresse adresseVrai = new Adresse(44, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse = new Adresse(90, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        
        client1 = new Client("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456", new Date(), 6);
        client2 = new Client("George", "Ration", new Date(), "0467382904", "ration@yahoo.fr", "123456", new Date(), 6);
        client1.setAdresse(adresseVrai);
        client2.setAdresse(adresseFausse);
        
        employe1 = new Employe("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", "696965", 9,8);
        employe2 = new Employe("Théo", "Benzenma", new Date(), "0923849605", "tt@gmail.com", "1234567", "696965", 9,8); 
        employe1.setAdresse(adresseVrai2);
        employe2.setAdresse(adresseFausse2);
        
        Intervention monIntervention1
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
        String email = "";
        String motDePasse = "";
        boolean expResult = false;
        boolean result = Services.authentifier(email, motDePasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trierListe method, of class Services.
     */
    @Test
    public void testTrierListe() {
        System.out.println("trierListe");
        List<Intervention> listeInterventions = null;
        Comparateur.FILTRES monFiltre = null;
        boolean croissant = false;
        boolean expResult = false;
        boolean result = Services.trierListe(listeInterventions, monFiltre, croissant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Intervention intervention = null;
        boolean expResult = false;
        boolean result = Services.creerDemandeIntervention(intervention);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
