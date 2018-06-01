/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author antoinemathat
 * @param <T> Entity concerned by this DAO
 */
public abstract class DAOInstance <T> {
    
   // ======================= Attributs
    T objetLocal = null;

    
    // ======================= Contructeurs
    public DAOInstance() {}
    
    public DAOInstance(T object) {
        objetLocal = object;
    }
    
    // ======================= Méthodes publiques
    public void creer (T object) {
        JpaUtil.obtenirEntityManager().persist(object);
        this.objetLocal = object;
    }
    
    public void mettreAJour() {
        JpaUtil.obtenirEntityManager().merge(objetLocal);
    }
    
    public void supprimer () {
        JpaUtil.obtenirEntityManager().remove(objetLocal);
    }
   
    public void trouverParId(long id) {
        objetLocal = JpaUtil.obtenirEntityManager().find((Class<T>) objetLocal.getClass(), id);
    }
    

    /**
     * /!\ L'attribut objetLocal doit être instancié
     * @return Une liste avec tous les objets
     */
    public List<T> getTousLesObjets() {
        List<T> lesObjets = null;
        
        String name = objetLocal.getClass().getSimpleName();
        String jpql = "SELECT e FROM " + name + " e";
        System.out.println("Requête : " + jpql);
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        lesObjets = (List<T>)query.getResultList();
        
        return lesObjets;
    }
    
    // ======================= Getters / Setters
    public void setObjetLocal(T objetLocal) {
        this.objetLocal = objetLocal;
    }

    public T getObjetLocal() {
        return objetLocal;
    }
}
