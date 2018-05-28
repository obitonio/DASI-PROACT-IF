/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author antoinemathat
 */
public class IHM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proactif");
        EntityManager em = emf.createEntityManager();
        
        Utilisateur u1 = new Utilisateur("Jean", "Hameau");
        
        em.getTransaction().begin();
        em.persist(u1);
        em.getTransaction().commit();
        em.close();
        
        System.out.println("PROACT'IF");
    }
}
