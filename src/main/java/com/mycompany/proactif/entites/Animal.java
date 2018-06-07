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
public class Animal extends Intervention implements Serializable {

    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private String nom;
    
    @Column(nullable=false)
    private String type;
    
    // ======================= Constructeurs
    public Animal() {}
    
    public Animal(Client client, String intitule, String descriptionClient, String unNom, String unType) {
        super(client, intitule, descriptionClient);
        this.nom = unNom;
        this.type = unType;
                
    }
    
    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // ======================= Surcharges
    @Override
    public String toString() {
        String message = "========================= Intervention - Animal\n";
        message += super.toString() + "\n";
        message += "Nom : " + this.getNom() + "\n";
        message += "Type : " + this.getType() + "\n";
        return message;
    }
    
}
