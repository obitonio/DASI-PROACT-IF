/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Adresse implements Serializable {
    
    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private int numero;
    
    @Column(nullable=false)
    private String rue;
            
    @Column(nullable=false)
    private String codePostal;
    
    @Column(nullable=false)
    private String ville;

    @Column(nullable=false)
    private String informations;
    
    @Column(nullable=false)
    private String coordonneesGPS;
    
    // ======================= Constructeurs
    public Adresse() {}
    
    public Adresse(int unNumero, String uneRue, String unCodePostal, String uneVille, String desInformations, String desCoordonneesGPS) {
        this.numero = unNumero;
        this.rue = uneRue;
        this.codePostal = unCodePostal;
        this.ville = uneVille;
        this.informations = desInformations;
        this.coordonneesGPS = desCoordonneesGPS;
    }

    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public String getCoordonneesGPS() {
        return coordonneesGPS;
    }

    public void setCoordonneesGPS(String coordonneesGPS) {
        this.coordonneesGPS = coordonneesGPS;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String message = "=== Adresse ===\n";
        message += this.getNumero() + " " + this.getRue() + "\n";
        message += this.getCodePostal() + " " + this.getVille() + "\n";
        message += this.getInformations() + "\n";
        message += "Coordonnées GPS: " + this.getCoordonneesGPS() + "\n";
        return message;
    }
    
}
