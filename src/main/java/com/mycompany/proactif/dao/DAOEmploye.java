/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

import com.google.maps.model.LatLng;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.util.GeoTest;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author antoinemathat
 */
public class DAOEmploye extends DAOAbstraitUtilisateur<Employe> {
    
    public static List<Employe> getEmployesDisponibles(){
        try{
            String jpql = "SELECT e FROM Employe E WHERE e.disponibilite = 1";
            Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
            List<Employe> listeEmployesDisponibles = (List<Employe>) query.getResultList();
            return listeEmployesDisponibles;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public static Employe getEmployeLePlusProche(List<Employe> listeEmploye, Intervention intervention){
            
        if(listeEmploye == null || listeEmploye.isEmpty())
            return null;
        else if(listeEmploye.size()==1)
            return listeEmploye.get(0);
        else{
            Employe employeLePlusProche = listeEmploye.get(0);
            LatLng positionIntervention = intervention.getClient().getAdresse().getCoordonneesGPS();
            int tempsTrajetMin= Integer.MAX_VALUE;
                        
            for(Employe employe : listeEmploye){
                if(GeoTest.getTripDurationByBicycleInMinute(employe.getPosition(), positionIntervention) < tempsTrajetMin){
                    employeLePlusProche = employe;
                }
            }
            return employeLePlusProche;
        }
    }
    
    
}
