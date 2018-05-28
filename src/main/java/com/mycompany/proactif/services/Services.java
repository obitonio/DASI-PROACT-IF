/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.DAOUtilisateur;
import com.mycompany.proactif.dao.JpaUtil;


/**
 *
 * @author Jean
 */
public class Services {
    
    public static boolean ajouterUtilisateur(Utilisateur utilisateur) {
        
        commencerTransaction();
        
        DAOUtilisateur maDAO = new DAOUtilisateur();
        maDAO.creer(utilisateur);
        
        finirTransaction();
        return true;
    }
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
    
    private static void commencerTransaction() {
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction(); 
    }
    
    private static void finirTransaction() {
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager(); 
    }
}
