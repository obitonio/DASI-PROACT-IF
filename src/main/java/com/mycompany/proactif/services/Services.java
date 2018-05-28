/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.mycompany.proactif.Utilisateur;
import com.mycompany.proactif.dao.JpaUtil;


/**
 *
 * @author Jean
 */
public class Services {
    

    public static boolean ajouterUtilisateur(Utilisateur utilisateur) {
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        DAOUtilisateur myDAO = new DAOInstance();
        
        if (myDAO.create(utilisateur)) {
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            return true;
        } 
        else {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            return false;
        }       
    }
}
