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
import javax.persistence.Temporal;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Client extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutAbonnement;
    
    @Column(nullable=false)
    private int duree;
    
    @OneToMany(mappedBy = "client")
    private List<Intervention> listeDesInterventions;

    public Date getDateDebutAbonnement() {
        return dateDebutAbonnement;
    }

    public List<Intervention> getListeDesInterventions() {
        return listeDesInterventions;
    }
    
    public void setDateDebutAbonnement(Date dateDebutAbonnement) {
        this.dateDebutAbonnement = dateDebutAbonnement;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Client(String nom, String prenom, Date dateNaissance, String telephone, String email, String motDePasse,Date dateDebutAbonnement, int duree) {
        super(nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.dateDebutAbonnement = dateDebutAbonnement;
        this.duree = duree;
    }
    public Client(){}
    
    @Override
    public String toString() {
        return "com.mycompany.proactif.entites.Client[ id=" + super.getId() + " ]";
    }
    
}
