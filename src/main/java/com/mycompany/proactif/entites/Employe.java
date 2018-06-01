/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
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
    private int disponibilite;
    
    @OneToMany(mappedBy = "employe")
    private List<Intervention> listeDesInterventions;

    // ======================= Constructeurs
    public Employe(String nom, String prenom, Date dateNaissance, String telephone, String email, String motDePasse, String numContrat, int salaire, int disponibilite) {
        super(nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.numContrat = numContrat;
        this.salaire = salaire;
        this.disponibilite = disponibilite;
    }
    
    public Employe(){}

    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
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
        return "com.mycompany.proactif.entites.Employe[ id=" + super.getId() + " ]";
    }
    
}
