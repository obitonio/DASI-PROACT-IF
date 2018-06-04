/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

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

/**
 *
 * @author antoinemathat
 */
public class ServicesTest {
    
    public ServicesTest() {
    }
    
    private static Client client1;
    
    private static Employe employe1;
    
    @BeforeClass
    public static void setUpClass() {
        
        client1 = new Client("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456", new Date(), 6);
        employe1 = new Employe("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", "696965", 9,8);
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
     * Test of ajouterUtilisateur method, of class Services.
     */
    @Test
    public void testAjouterUtilisateur() {
        System.out.println("ajouterUtilisateur");
        Utilisateur utilisateur = null;
        boolean expResult = false;
        boolean result = Services.ajouterUtilisateur(utilisateur);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creerUtilisateur method, of class Services.
     */
    @Test
    public void testCreerUtilisateur() {
        System.out.println("creerUtilisateur");
        Utilisateur unUtilisateur = null;
        Services.creerUtilisateur(unUtilisateur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
