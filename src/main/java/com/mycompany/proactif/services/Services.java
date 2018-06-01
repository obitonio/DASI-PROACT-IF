/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.dao.DAOAbstraitUtilisateur;;
import com.mycompany.proactif.dao.DAOIntervention;
import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.DAOUtilisateur;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.util.Comparateur;
import com.mycompany.proactif.util.Comparateur.FILTRES;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Jean
 */
public class Services {
    
    @Deprecated
    public static boolean ajouterUtilisateur(Utilisateur utilisateur) {
        
        commencerTransactionEcriture();
        
        DAOUtilisateur maDAO = new DAOUtilisateur();
        maDAO.creer(utilisateur);
        
        finirTransactionEcriture();
        return true;
    }
    
    /**
     * Permet de créer un utilisateur de tout type
     * @param <T> Le type d'utilisateur à créer (Client, Employé)
     * @param unUtilisateur L'utilisateur 
     */
    public static <T extends Utilisateur> void creerUtilisateur(T unUtilisateur) {
        commencerTransactionEcriture();
        
        DAOAbstraitUtilisateur<T> maDAO = new DAOAbstraitUtilisateur<T>();
        maDAO.creer(unUtilisateur);
        
        finirTransactionEcriture();
    }
    
    /**
     * Permet de vérifier les identifiants d'un utilisateur
     * @param email Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return true en cas de succès, false en cas d'echec
     */
    public static boolean authentifier(String email, String motDePasse) {
        
        commencerTransactionLecture();
        DAOUtilisateur maDAO = new DAOUtilisateur();
        if(maDAO.authentifier(email, motDePasse)){
            finirTransactionLecture();
            return true;
        }
        else{
            finirTransactionLecture();
            return false;
        }  
    }
    public static boolean trierListe(List<Intervention> listeInterventions,FILTRES monFiltre, boolean croissant){
        
        Comparateur monComparateur = new Comparateur (monFiltre, croissant);
        Collections.sort(listeInterventions, monComparateur);
        return true;
    }
    
    /**
     * Créer une demande d'intervention
     * @param intervention La demande à créer
     * @return 
     */
    public static boolean creerDemandeIntervention(Intervention intervention) {
        
        commencerTransactionEcriture();
        DAOIntervention maDAO = new DAOIntervention();
        maDAO.creer(intervention);
        finirTransactionEcriture();
        return true;

    }
    
    private static void commencerTransactionEcriture() {
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction(); 
    }
    
    private static void finirTransactionEcriture() {
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager(); 
    }
    
    private static void finirTransactionLecture() {
       JpaUtil.fermerEntityManager(); 
    }
    
    private static void commencerTransactionLecture() {
       JpaUtil.creerEntityManager(); 
    }
}
