/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Intervention;
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

/**
 *
 * @author antoinemathat
 */
public class ServicesTest {
    
    public ServicesTest() {
    }
    
    private static Client client1, client2, client3;
    
    private static Employe employe1, employe2, employe3;
    
    @BeforeClass
    public static void setUpClass() {
        
        JpaUtil.init();
     
        Adresse adresseVrai = new Adresse(44, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse = new Adresse(90, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adrCli3 = new Adresse(45, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adrEmp3 = new Adresse(46, "Rue de bruxelles", "69100", "Villeurbanne", "");
        
        client1 = new Client("Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456", new Date(), 6);
        client2 = new Client("George", "Ration", new Date(), "0467382904", "ration@yahoo.fr", "123456", new Date(), 6);
        client3 = new Client("Pascal", "Lebon", new Date(), "0789340718", "pl@yahoo.fr", "123456", new Date(), 6);
        
        client1.setAdresse(adresseVrai);
        client2.setAdresse(adresseFausse);
        client3.setAdresse(adrCli3);
        
        employe1 = new Employe("Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", "696965", 9,8);
        employe2 = new Employe("Théo", "Benzenma", new Date(), "0923849605", "tt@gmail.com", "1234567", "696965", 9,8); 
        employe3 = new Employe("Léo", "Pomp", new Date(), "0678301074", "lp@gmail.com", "1234567", "696965", 9,8); 
    
        employe1.setAdresse(adresseVrai2);
        employe2.setAdresse(adresseFausse2);
        employe3.setAdresse(adrEmp3);
        
        Services.creerUtilisateur(client3);
        Services.creerUtilisateur(employe3);
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
        
        Client c = (Client) Services.authentifier(client3.getEmail(), client3.getMotDePasse());
        Employe e = (Employe) Services.authentifier(employe3.getEmail(), employe3.getMotDePasse());
        
        assertNull(Services.authentifier(client2.getEmail(), client2.getMotDePasse()));
        assertNull(Services.authentifier(employe2.getEmail(), employe2.getMotDePasse()));
        assertNotNull(c);
        assertNotNull(e);
        
        assertTrue(c.equals(client3));
        assertTrue(e.equals(employe3));
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
