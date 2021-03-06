/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.util;

import com.mycompany.proactif.entites.Intervention;
import java.util.Comparator;

/**
 *
 * @author Jean
 */
public class Comparateur implements Comparator<Intervention>{
    
    private boolean croissant;
    public enum FILTRES{
        DATE,
        INTITULE,
        EMPLOYE
    }
    private FILTRES typeFiltre;

    public Comparateur(FILTRES typeFiltre, boolean croissant) {
        this.typeFiltre = typeFiltre;
        this.croissant = croissant;
    }
    public Comparateur(FILTRES typeFiltre) {
        this.typeFiltre = typeFiltre;
        this.croissant = true;
    }
    

    public void setAscendant(boolean ascendant) {
        this.croissant = ascendant;
    }
    
    
    @Override
    public int compare(Intervention i1, Intervention i2) {
        int resultat;
        
        switch (this.typeFiltre){
          case DATE:
              if(croissant)
                resultat = i1.getDateDebut().compareTo(i2.getDateDebut());
              else
                  resultat = i2.getDateDebut().compareTo(i1.getDateDebut());
            break;        
        
          case INTITULE:
              if(croissant)
                resultat = i1.getIntitule().compareTo(i2.getIntitule());
              else
                  resultat = i2.getIntitule().compareTo(i1.getIntitule());
            break;        
            
          case EMPLOYE:
            if(croissant)
                  resultat = i1.getEmploye().getNom().compareTo(i2.getEmploye().getNom());
                else
                    resultat = i2.getEmploye().getNom().compareTo(i1.getEmploye().getNom());
              break; 
              
              
          default:
              resultat=0;
        }
    return resultat;
    }
    
}
