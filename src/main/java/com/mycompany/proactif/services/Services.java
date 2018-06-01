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


/**
 *
 * @author Jean
 */
public class Services {
    
    @Deprecated
    public static boolean ajouterUtilisateur(Utilisateur utilisateur) {
        
        commencerTransaction();
        
        DAOUtilisateur maDAO = new DAOUtilisateur();
        maDAO.creer(utilisateur);
        
        finirTransaction();
        return true;
    }
    
    /**
     * Permet de créer un utilisateur de tout type
     * @param <T> Le type d'utilisateur à créer (Client, Employé)
     * @param unUtilisateur L'utilisateur 
     */
    public static <T> void creerUtilisateur(T unUtilisateur) {
        commencerTransaction();
        
        DAOAbstraitUtilisateur<T> maDAO = new DAOAbstraitUtilisateur<T>();
        maDAO.creer(unUtilisateur);
        
        finirTransaction();
    }
    
    /**
     * Permet de vérifier les identifiants d'un utilisateur
     * @param email Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return true en cas de succès, false en cas d'echec
     */
    public static boolean authentifier(String email, String motDePasse) {
        
        commencerTransaction();
        
        DAOUtilisateur maDAO = new DAOUtilisateur();
        if(maDAO.authentifier(email, motDePasse)){
            finirTransaction();
            return true;
        }
        else{
            finirTransaction();
            return false;
        }
    }
    
    /**
     * Créer une demande d'intervention
     * @param intervention La demande à créer
     * @return 
     */
    public static boolean creerDemandeIntervention(Intervention intervention) {
        
        commencerTransaction();
        DAOIntervention maDAO = new DAOIntervention();
        maDAO.creer(intervention);
        finirTransaction();
        return true;

    }
    
    private static void commencerTransaction() {
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction(); 
    }
    
    private static void finirTransaction() {
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager(); 
    }
}
