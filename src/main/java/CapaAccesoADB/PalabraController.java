/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaAccesoADB;

import Entidades.Palabra;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author lenta
 */
@ApplicationScoped
public class PalabraController {
   
    public PalabraController(){ 
    }
    
    public List<Palabra> consultarTodos(){         
       
        EntityManager em = ConexionADB.emf.createEntityManager();        
        List<Palabra> lista = em.createQuery("select * from PALABRA", Palabra.class).getResultList();
        em.close();
        
        return lista;
    }
    
    public Palabra consultarPorID(int idPalabra){
        
        EntityManager em = ConexionADB.emf.createEntityManager();       
        Palabra respuesta = em.find(Palabra.class, idPalabra); 
        em.close();
        
        return respuesta;
    }
    
    public List<Palabra> getPalabraByNombre(String nombrePalabra){
        
        EntityManager em = ConexionADB.emf.createEntityManager();       
        List<Palabra> respuesta =  em.createNativeQuery("select * from PALABRA p where p.NombrePalabra LIKE '"+nombrePalabra+"'", Palabra.class).getResultList();
        em.close();
        
        return respuesta;
    }
    
    public void agregar(Palabra p){
        
        EntityManager em = ConexionADB.emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(p);
        em.flush();
        t.commit(); 
        em.close();
        
                     
    }
    
    public void modificar(Palabra p){
        
        EntityManager em = ConexionADB.emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(p);
        em.flush();
        t.commit(); 
        em.close();
        
    }
    
    public void eliminar(Palabra p){
        
        EntityManager em = ConexionADB.emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(p);
        em.flush();
        t.commit();  
        em.close();
        
    }
}
   