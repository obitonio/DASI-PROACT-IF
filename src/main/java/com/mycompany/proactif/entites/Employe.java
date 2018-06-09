/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import com.google.maps.model.LatLng;
import com.mycompany.proactif.util.GeoTest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Employe extends Utilisateur implements Serializable {
    
    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private boolean disponibilite; // 0 = indisponible, 1 = disponuble;
    
    @OneToMany(mappedBy = "employe")
    private List<Intervention> listeDesInterventions;
    
    @Column
    private LatLng position;
    

    // ======================= Constructeurs
    public Employe(String prenom, String nom, Date dateNaissance, String telephone, String email, String motDePasse, boolean disponibilite) {
        super(nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.disponibilite = disponibilite;
        this.listeDesInterventions = new ArrayList<>();
    }
    
    public Employe(){}

    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    
    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }   

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    public boolean getDisponibilite() {
        return disponibilite;
    }

    public List<Intervention> getListeDesInterventions() {
        return listeDesInterventions;
    }

    public void setListeDesInterventions(List<Intervention> listeDesInterventions) {
        this.listeDesInterventions = listeDesInterventions;
    }

    // ======================= Surcharges
    @Override
    public void setAdresse(Adresse adresse) {
        super.setAdresse(adresse);
        this.setPosition(GeoTest.getLatLng(adresse.toGeoString()));
    }
    
    @Override
    public String toString() {
        String message = "========================= Employe\n";
        message += super.toString();
        return message;
    }
    
}
