/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Incident extends Intervention implements Serializable {
    
    // ======================= Attributs
    private static final long serialVersionUID = 1L;

    // ======================= Constructeurs
    public Incident() {}
    
    public Incident(Client client, String intitule, String descriptionClient) {
        super(client, intitule, descriptionClient);
    }
    
    // ======================= Méthodes publiques

    // ======================= Getters / Setters
    

    // ======================= Surcharges
    @Override
    public String toString() {
        String message = "========================= Intervention - Incident\n";
        message += super.toString() + "\n";
        message += "Type : Incident \n";
        return message;
    }
    
}
