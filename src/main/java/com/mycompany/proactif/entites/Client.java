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
public class Client extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private Date dateDebutAbonnement;

    public Date getDateDebutAbonnement() {
        return dateDebutAbonnement;
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
    
    @Column(nullable=false)
    private int duree;
 

    @Override
    public String toString() {
        return "com.mycompany.proactif.entites.Client[ id=" + super.getId() + " ]";
    }
    
}
