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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Jean
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Intervention implements Serializable {

    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Employe employe;
    
    @Column(nullable=false)
    private String intitule;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    
    @Column(nullable=false)
    private String descriptionClient;
    
    @Column
    private String commentaireEmploye;
    
    @Column(nullable=false)
    private int etat;
    
    // ======================= Constructeurs
    public Intervention(Client client, String intitule, String descriptionClient) {
        this.client = client;
        this.intitule = intitule;
        this.descriptionClient = descriptionClient;
        this.etat =0;  //TODO A DEFINIR AVEC ANTOINE
        if(client != null){
            client.getListeDesInterventions().add(this);
        }
    }
    
    public Intervention(){}
    
    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public String getIntitule() {
        return intitule;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getDescriptionClient() {
        return descriptionClient;
    }

    public String getCommentaireEmploye() {
        return commentaireEmploye;
    }

    public int getEtat() {
        return etat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDescriptionClient(String descriptionClient) {
        this.descriptionClient = descriptionClient;
    }

    public void setCommentaireEmploye(String commentaireEmploye) {
        this.commentaireEmploye = commentaireEmploye;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    // ======================= Surcharges
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervention)) {
            return false;
        }
        Intervention other = (Intervention) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String contenuDeLIntervention = "Client : " + this.client.getPrenom() + " " + client.getNom() + '\n';
        if(this.employe != null)
            contenuDeLIntervention += "Employe : " + this.employe.getPrenom() + " " + this.employe.getNom() + '\n';
            
        if (this.dateDebut != null)
            contenuDeLIntervention += this.dateDebut.toString() + '\n';
        
        if(this.dateFin != null)
            contenuDeLIntervention += this.dateFin.toString() + '\n';
        
        contenuDeLIntervention += this.descriptionClient + '\n';
        
        if(this.commentaireEmploye != null)
            contenuDeLIntervention +=  this.commentaireEmploye + '\n';
        contenuDeLIntervention += this.intitule + "\nEtat : " +
                                String.valueOf(this.etat);
        return contenuDeLIntervention;                
    }
    
}
