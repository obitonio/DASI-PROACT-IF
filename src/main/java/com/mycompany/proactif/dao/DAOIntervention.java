/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.dao;

import com.mycompany.proactif.entites.Intervention;

/**
 *
 * @author Jean
 */
public class DAOIntervention extends DAOInstance<Intervention> {
     
    public DAOIntervention(Intervention inter) {
        super(inter);
    }
    
    public DAOIntervention() {
        super();
    }
}
