/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutAbonnement;
    
    @Column(nullable=false)
    private int duree;
    
    @OneToMany(mappedBy = "client")
    private List<Intervention> listeDesInterventions;
    
    // ======================= Contructeurs
    public Client(String nom, String prenom, Date dateNaissance, String telephone, String email, String motDePasse,Date dateDebutAbonnement, int duree) {
        super(nom, prenom, dateNaissance, telephone, email, motDePasse);
        this.dateDebutAbonnement = dateDebutAbonnement;
        this.duree = duree;
        this.listeDesInterventions = new ArrayList<>();
    }
    public Client(){}
    
    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    
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
    
    public Date getDateDebutAbonnement() {
        return dateDebutAbonnement;
    }
    
    public String getDateDebutAbonnementFormate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(this.dateDebutAbonnement);

        return DateToStr;
    }
    
    // ======================= Surcharges
    
    @Override
    public String toString() {
        String message = "========================= Client\n";
        message += super.toString();
        message += "Début abonnement : " + this.getDateDebutAbonnementFormate() + " pour une durée de " + this.getDuree() + " mois\n";
        return message;
    }
    
}
