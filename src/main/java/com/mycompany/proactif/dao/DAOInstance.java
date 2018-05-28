/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

/**
 *
 * @author antoinemathat
 * @param <T> Entity concerned by this DAO
 */
public class DAOInstance <T> {
   
    T localObject = null;

    public void setLocalObject(T localObject) {
        this.localObject = localObject;
    }

    public T getLocalObject() {
        return localObject;
    }
    
    public DAOInstance() {
        
    }
    
    public DAOInstance(T object) {
        localObject = object;
    }
    
    public void create (T object) {
        JpaUtil.obtenirEntityManager().persist(object);
        this.localObject = object;
    }
    
    public void update() {
        JpaUtil.obtenirEntityManager().merge(localObject);
    }
    
    public void delete () {
        JpaUtil.obtenirEntityManager().remove(localObject);
    }
   
    public void findById(long id) {
        localObject = JpaUtil.obtenirEntityManager().find((Class<T>) localObject.getClass(), id);
    }
}
