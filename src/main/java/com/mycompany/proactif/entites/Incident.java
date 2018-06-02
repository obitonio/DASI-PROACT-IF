/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Incident extends Intervention implements Serializable {
    
    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private String priorite;

    // ======================= Constructeurs
    public Incident() {}
    
    public Incident(Client client, String intitule, Date dateDebut, String descriptionClient, String unePriorite) {
        super(client, intitule, dateDebut, descriptionClient);
        this.priorite = unePriorite;
    }
    
    // ======================= Méthodes publiques

    // ======================= Getters / Setters
    
    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }
    

    // ======================= Surcharges
    @Override
    public String toString() {
        String message = super.toString() + "\n";
        message += "Type : Incident \n";
        message += "Priorité : " + this.getPriorite() + "\n";
        return message;
    }
    
}
