/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.dao.DAOAbstraitUtilisateur;
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
import com.mycompany.proactif.entites.Animal;
import com.mycompany.proactif.entites.Incident;
import com.mycompany.proactif.entites.Livraison;
import com.mycompany.proactif.util.DebugLogger;
import com.mycompany.proactif.util.GeoTest;
import java.util.ArrayList;

/**
 *
 * @author antoinemathat
 */
public class ServicesTest {
    
    public ServicesTest() {
    }
    
    private static Client client1, client2, client3, client4;
    
    private static Employe employe1, employe2, employe3, employe4, employe5;
    
    private static Intervention i1, i2, i3, a1, a2, a3, l1, l2, l3;
    
    @BeforeClass
    /**
     * client1, client2, employe1, employe2 : Utilisé pour les tests de créerUtilisateur
     * client3, client4 : Utilisable dans tous les tests
     */
    public static void setUpClass() {
        
        JpaUtil.init();
     
        Adresse adresseVrai = new Adresse(44, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse = new Adresse(90, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        Adresse adresseVrai2 = new Adresse(79, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adresseFausse2 = new Adresse(9, "rue zzzzzzzzz", "69100", "Villedsfsdfl", "");
        
        Adresse adrCli3 = new Adresse(45, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adrCli4 = new Adresse(48, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adrEmp3 = new Adresse(46, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adrEmp4 = new Adresse(49, "Rue de bruxelles", "69100", "Villeurbanne", "");
        Adresse adrEmp5 = new Adresse(50, "Rue de bruxelles", "69100", "Villeurbanne", "");
        
        client1 = new Client("M","Antoine", "Mathat", new Date(), "0677500460", "amathat@insa-lyon.fr", "123456");
        client2 = new Client("M","George", "Ration", new Date(), "0467382904", "ration@yahoo.fr", "123456");
        client3 = new Client("M","Pierre", "Kiroule", new Date(), "0467382904", "pierre@yahoo.fr", "123456");
        client4 = new Client("M","Pascal", "Lebon", new Date(), "0789340718", "pl@yahoo.fr", "123456");
        
        client1.setAdresse(adresseVrai);
        client2.setAdresse(adresseFausse);
        client3.setAdresse(adrCli3);
        client4.setAdresse(adrCli4);
       
        employe1 = new Employe("M","Jean", "Neymar", new Date(), "0690239405", "jhameau@insa-lyon.fr", "1234567", true);
        employe2 = new Employe("M","Théo", "Benzema", new Date(), "0923849605", "tt@gmail.com", "1234567", true); 
        employe3 = new Employe("M","Léo", "Pomp", new Date(), "0678301074", "lp@gmail.com", "1234567", true); 
        employe4 = new Employe("M","Nabil", "Pondu", new Date(), "0678903478", "nabildupond@gmail.com", "1234567", true); 
        employe5 = new Employe("M","Corentin", "Violet", new Date(), "0798234905", "cocovivi@outlock.com", "1234567", false); 
        
        
        employe1.setAdresse(adresseVrai2);
        employe2.setAdresse(adresseFausse2);
        employe3.setAdresse(adrEmp3);
        employe4.setAdresse(adrEmp4);
        employe5.setAdresse(adrEmp5);
        
        employe3.setPosition(GeoTest.getLatLng("2 Rue Léon Fabre, Villeurbanne"));
        employe4.setPosition(GeoTest.getLatLng("19 Avenue Gaston Berger, Villeurbanne"));
        employe5.setPosition(GeoTest.getLatLng("6 Avenue Gaston Berger, Villeurbanne"));
        
        Services.creerUtilisateur(client3);
        Services.creerUtilisateur(client4);
        Services.creerUtilisateur(employe3);
        Services.creerUtilisateur(employe4);
        Services.creerUtilisateur(employe5);
        
        i1 = new Incident(client3, "Fuite eau", "De l'eau coule derrière le robinet de la cuisine");
        i2 = new Incident(client3, "Toilettes bouchées", "Mes toilettes sont bouchées");
        i3 = new Incident(client4, "Problème avec ma gouttière", "Les feuilles ont bouchés ma gouttière");

        a1 = new Animal(client3, "Nourir mon chat", "Les croquettes sont dans le placard d ela cuisine", "Tigrou", "CHAT");
        a2 = new Animal(client3, "Sortir mon chien", "La laisse est sur le porte manteau", "Théo", "CHIEN");
        a3 = new Animal(client4, "Nourir mon serpent", "Attention à pas vous faire mordre", "Rex", "Serpent");

        l1 = new Livraison(client3, "Livraison G", "Le livreur passe à 18h30", "01/06/2018 - 18:30", "093KS38U375", "Colis", "DHL");
        l2 = new Livraison(client4, "Livraison H", "Le livreur passe à 11h30", "04/06/2018 - 11:30", "093KS38U374", "Recommandé", "La Poste");
        l3 = new Livraison(client4, "Livraison I", "Le livreur passe à 17h00", "12/06/2018 - 17:00", "093KS38U373", "Lettre", "Colissimo");
    
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
        assertEquals(client1, Services.creerUtilisateur(client1));
        assertEquals(null, Services.creerUtilisateur(client2));
        assertEquals(employe1, Services.creerUtilisateur(employe1));
        assertEquals(null, Services.creerUtilisateur(employe2));
    }
    
    @Test
    public void testMettreAJourUtilisateur() {
        System.out.println("mettreAJourUtilisateur");
        
        Client c = (Client) Services.authentifier(client4.getEmail(), client4.getMotDePasse());
        Client c2 = new Client();
        
        c.setNom("Test");
        c.setPrenom("Test");
        
        assertEquals(Services.mettreAJourUtilisateur(c),c);
        
        try {
            Services.commencerTransactionLecture();
            DAOAbstraitUtilisateur<Client> maDAO = new DAOAbstraitUtilisateur<>(c2);
            maDAO.trouverParId(c.getId());
            c2 = maDAO.getObjetLocal();
            Services.finirTransactionLecture();
        }
        catch (Exception e) {
            DebugLogger.log("[TEST-SERVICES] testMettreAJourUtilisateur", e);
            fail ("Echec lors de la vérification");
        }
        
        assertTrue(c.equals(c2));   
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
        boolean croissant;
        
        List<Intervention> maListe = new ArrayList<>();
        
        maListe.add(a1); maListe.add(a2); maListe.add(a3);
        maListe.add(i1); maListe.add(i2); maListe.add(i3);
        maListe.add(l1); maListe.add(l2); maListe.add(l3);
        
        for(Intervention intervention : maListe)
            intervention.setDateDebut(new Date());
        
       
        //DATE 1
        System.out.println("Date1");
        List<Intervention> expectedListe = new ArrayList<>();
        expectedListe.add(a1); expectedListe.add(a2); expectedListe.add(a3);
        expectedListe.add(i1); expectedListe.add(i2); expectedListe.add(i3);
        expectedListe.add(l1); expectedListe.add(l2); expectedListe.add(l3);
        croissant = true;
        Comparateur.FILTRES monFiltre = Comparateur.FILTRES.DATE;
        boolean result = Services.trierListe(maListe, monFiltre, croissant);
        assertTrue(result);
        assertEquals(maListe, expectedListe);
        
        //DATE 2
        System.out.println("Date2");
        expectedListe.clear();
  
        expectedListe.add(l3); expectedListe.add(l2); expectedListe.add(l1);
        expectedListe.add(i3); expectedListe.add(i2);expectedListe.add(i1);
        expectedListe.add(a3); expectedListe.add(a2); expectedListe.add(a1);
        croissant = false;
        
        result = Services.trierListe(maListe, monFiltre, croissant);
        assertTrue(result);
        assertEquals(maListe, expectedListe);
        
        //INTITULE 1
        System.out.println("Intitule1");
        expectedListe.clear();
        
        expectedListe.add(i1); expectedListe.add(l1); expectedListe.add(l2);
        expectedListe.add(l3); expectedListe.add(a1); expectedListe.add(a3);
        expectedListe.add(i3); expectedListe.add(a2); expectedListe.add(i2);
        
        croissant = true;
        monFiltre = Comparateur.FILTRES.INTITULE;
        
        result = Services.trierListe(maListe, monFiltre, croissant);
        assertTrue(result);
        assertEquals(maListe, expectedListe);
        
    }

    /**
     * Test of rechercher method, of class Services.
     */
    @Test
    public void testRechercher() {
        System.out.println("rechercher");
        
        List<Intervention> maListe = new ArrayList<>();
        
        maListe.add(a1); maListe.add(a2); maListe.add(a3);
        maListe.add(i1); maListe.add(i2); maListe.add(i3);
        maListe.add(l1); maListe.add(l2); maListe.add(l3);
        
        
        List<Intervention> listeReduite =  Services.rechercher(maListe, "Nourir");
        
        List<Intervention> listeAttendue = new ArrayList<>();
        listeAttendue.add(a1);
        listeAttendue.add(a3);
        assertEquals(listeAttendue,listeReduite);
    }

    /**
     * Test of creerDemandeIntervention method, of class Services.
     */
    @Test
    public void testCreerDemandeIntervention() {
        System.out.println("creerDemandeIntervention");

        Client c1 = (Client) Services.authentifier(client3.getEmail(), client3.getMotDePasse());           
        
        assertEquals(Services.RetourCreationIntervention.Succes, Services.creerDemandeIntervention(c1, i1));
        assertEquals(Services.RetourCreationIntervention.Succes, Services.creerDemandeIntervention(c1, a3));
        assertEquals(Services.RetourCreationIntervention.Succes, Services.creerDemandeIntervention(c1, a2));
        assertEquals(Services.RetourCreationIntervention.AucunEmployeDisponible, Services.creerDemandeIntervention(c1, l1));
    }   
    
    @Test
    public void testTerminerIntervention(){
        System.out.println("terminerIntervention");
        
        Client c1 = (Client) Services.authentifier(client3.getEmail(), client3.getMotDePasse());           
        
        // Création des interventions
        assertEquals(Services.RetourCreationIntervention.Succes, Services.creerDemandeIntervention(c1, i2));
        assertEquals(Services.RetourCreationIntervention.Succes, Services.creerDemandeIntervention(c1, i3));
      
        // Cloture des interventions
        assertEquals(Services.RetourTerminerIntervention.Succes, Services.terminerIntervention(i2, "Les toilettes sont oppérationnels", 0));
        assertEquals(Services.RetourTerminerIntervention.InterventionNonReussie, Services.terminerIntervention(i3, "La gouttière est cassée un professionnel doit intervenir", -1));

    }
}
