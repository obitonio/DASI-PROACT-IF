/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.entites;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author antoinemathat
 */
@Entity
public class Livraison extends Intervention implements Serializable {

    // ======================= Attributs
    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private String livreur;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heurePassage;
    
    @Column(nullable=false)
    private String codeSuivi;

    // ======================= Constructeurs
    public Livraison() {}
    
    public Livraison(Client client, String intitule, String descriptionClient, String unLivreur, String uneHeurePassage, String unCodeSuivi) {
        super(client, intitule, descriptionClient);
        this.livreur = unLivreur;
        this.setHeurePassage(uneHeurePassage);
        this.codeSuivi = unCodeSuivi;
    }

    public Livraison(String récupérer_colis, Timestamp timestamp, String u374) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // ======================= Méthodes publiques
    
    // ======================= Getters / Setters
    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public Date getHeurePassage() {
        return heurePassage;
    }

    public final void setHeurePassage(String heurePassage) {
        if (heurePassage == null)
            return;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
            this.heurePassage = sdf.parse(heurePassage);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public String getCodeSuivi() {
        return codeSuivi;
    }

    public void setCodeSuivi(String codeSuivi) {
        this.codeSuivi = codeSuivi;
    }
    
    // ======================= Surcharges
    @Override
    public String toString() {
        String message = "========================= Intervention - Livraison\n";
        message += super.toString() + "\n";
        message += "Livreur : " + this.getLivreur() + "\n";
        message += "Heure passage : " + this.getHeurePassage() + "\n";
        message += "Code suivi : " + this.getCodeSuivi() + "\n";
        return message;
    }
    
}
