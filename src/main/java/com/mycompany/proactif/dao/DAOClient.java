/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.util.DebugLogger;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author antoinemathat
 */
public class DAOClient extends DAOAbstraitUtilisateur<Client> {

    public DAOClient(Client unUtilisateur) {
        super(unUtilisateur);
    }
    
    public  boolean recupererToutesLesIntervention(){
        String jpql = "SELECT i FROM Intervention i WHERE i.client = :client";
        Query requete = JpaUtil.obtenirEntityManager().createQuery(jpql);
        requete.setParameter("client", objetLocal);
        
        try{
            objetLocal.setListeDesInterventions((List<Intervention>) requete.getResultList());
        }
        catch(Exception e) {
            objetLocal = null;
            DebugLogger.log("[DAOUtilisateurAbstrait] recupererToutesLesIntervention (Client)", e);
            //TODO : améliorer la réponse de contenue si l'on voit qu'il y a un match avec les adresses mails
            return false;
        }
        return true;
    }
}
