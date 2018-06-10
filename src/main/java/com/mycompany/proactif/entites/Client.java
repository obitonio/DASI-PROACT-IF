/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Client extends Utilisateur implements Serializable {

    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    
    @OneToMany(mappedBy = "client")
    private List<Intervention> listeDesInterventions;
    
    // ======================= Contructeurs
    public Client(String civilite, String prenom, String nom, Date dateNaissance, String telephone, String email, String motDePasse) {
        super(civilite,nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.listeDesInterventions = new ArrayList<>();
    }
    public Client(){}
    
    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    
    public List<Intervention> getListeDesInterventions() {
        return listeDesInterventions;
    }

    public void setListeDesInterventions(List<Intervention> listeDesInterventions) {
        this.listeDesInterventions = listeDesInterventions;
    }

    
    // ======================= Surcharges
    
    @Override
    public String toString() {
        String message = "========================= Client\n";
        message += super.toString();
        return message;
    }
    
}
