/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

import com.mycompany.proactif.entites.Utilisateur;
import javax.persistence.Query;

/**
 *
 * @author antoinemathat
 * @param <T> Type de l'utilisteur
 */
public class DAOAbstraitUtilisateur<T extends Utilisateur> extends DAOInstance<T> {
    
    public boolean authentifier(String email, String motDePasse){
        
        String jpql = "SELECT e FROM Utilisateur e WHERE e.email = :email AND e.motDePasse = :motDePasse";
        Query requete = JpaUtil.obtenirEntityManager().createQuery(jpql);
        requete.setParameter("email", email);
        requete.setParameter("motDePasse", motDePasse);
        try{
            objetLocal = (T) requete.getSingleResult();
        }
        catch(Exception e){
            objetLocal = null;
            //TODO : améliorer la réponse de contenue si l'on voiq=t qu'il y a un match avec les adresses mails
            return false;
        }
        return true; 
    }
}
