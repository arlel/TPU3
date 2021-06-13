/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package CapaAccesoADB;

import Entidades.Palabra;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author lenta
 */
/*
public class PalabraController {
    @Inject
    private EntityManager em;
    public List<Palabra> consultarTodos(){         
        List<Palabra> lista = em.createQuery("select * from PALABRA", Palabra.class).getResultList();
        return lista;
    }
    
    public Palabra consultarPorID(int idPalabra){
        return em.find(Palabra.class, idPalabra);        
    }
    
    public Palabra getPalabraByNombre(String nombrePalabra){
        return (Palabra) em.createQuery("select * from PALABRA p where p.NombrePalabra LIKE '"+nombrePalabra+"'", Palabra.class);
    }
    
    public void agregar(Palabra p){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(p);
        em.flush();
        t.commit();               
    }
    
    public void modificar(Palabra p){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(p);
        em.flush();
        t.commit(); 
    }
    
    public void eliminar(Palabra p){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(p);
        em.flush();
        t.commit(); 
    }
}
   */