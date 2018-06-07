/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import com.google.maps.model.LatLng;
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
    private String numContrat;
    @Column(nullable=false)
    private int salaire;
    
    @Column(nullable=false)
    private int disponibilite; // 0 = indisponible, 1 = disponuble;
    
    @OneToMany(mappedBy = "employe")
    private List<Intervention> listeDesInterventions;
    
    @Column
    private LatLng position;
    

    // ======================= Constructeurs
    public Employe(String nom, String prenom, Date dateNaissance, String telephone, String email, String motDePasse, String numContrat, int salaire, int disponibilite) {
        super(nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.numContrat = numContrat;
        this.salaire = salaire;
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
    
    
    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public int getSalaire() {
        return salaire;
    }

    public int getDisponibilite() {
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
    public String toString() {
        String message = "========================= Employe\n";
        message += super.toString();
        message += "Contrat n°" + this.getNumContrat() + ", salaire : " + this.getSalaire() + "€, disponible : " + ((this.getDisponibilite() == 1)? "oui" : "non") + "\n";
        return message;
    }
    
}
