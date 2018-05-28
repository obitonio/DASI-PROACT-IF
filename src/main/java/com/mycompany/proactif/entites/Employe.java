/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Employe extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private String numContrat;

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
    
    @Column(nullable=false)
    private int salaire;
    
    @Column(nullable=false)
    private int disponibilite;

    @Override
    public String toString() {
        return "com.mycompany.proactif.entites.Employe[ id=" + super.getId() + " ]";
    }
    
}
