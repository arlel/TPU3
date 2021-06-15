/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaAccesoADB;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lenta
 */
public class ConexionADB {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
    
    
}
