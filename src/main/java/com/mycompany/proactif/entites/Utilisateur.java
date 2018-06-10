/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
public abstract class Utilisateur implements Serializable {
    
    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String civilite;
    
    @Column(nullable=false)
    private String nom;
    
    @Column(nullable=false)
    private String prenom;
   
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    
    @Column(nullable=false)
    private String telephone;
    
    @Column(nullable=false, unique=true)
    private String email;
    
    @Column(nullable=false)
    private String motDePasse;
    
    @ManyToOne
    private Adresse adresse;
    
    // ======================= Constructeurs
    public Utilisateur(String civilite,String prenom, String nom, Date dateNaissance, String telephone, String email, String motDePasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.email = email;
        this.motDePasse = motDePasse;
    }
        
    public Utilisateur() {
    }

    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    public Long getId() {
        return id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    public String getDateNaissanceFormate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(this.dateNaissance);

        return DateToStr;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        if (!this.prenom.equals(other.getPrenom()) ||
            !this.nom.equals(other.getNom()) || 
            !this.email.equals(other.getEmail())) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        String message = this.getCivilite() + " " + this.getPrenom() + " " + this.getNom() + ", né le " + this.getDateNaissanceFormate() + "\n";
        message += "Adresse :\n";
        message += this.getAdresse().toString() + "\n";
        return message;
    }
    
}
