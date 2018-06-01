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
        EMPLOYE,
        TYPEINTERVENTION
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
              
          case TYPEINTERVENTION:
              String type1 = i1.getType().getClass().getSimpleName();
              String type2 = i2.getType().getClass().getSimpleName();
              System.out.println("t1: " + type1 + " t2: " + type2);
              
              if(croissant)
                resultat = type1.compareTo(type2);
              else
                resultat = type2.compareTo(type1);
              break; 
              
          default:
              resultat=0;
        }
    return resultat;
    }
    
}
