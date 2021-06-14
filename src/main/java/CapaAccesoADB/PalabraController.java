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
   
    public PalabraController(){}
    
    public List<Palabra> consultarTodos(){         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();        
        List<Palabra> lista = em.createQuery("select * from PALABRA", Palabra.class).getResultList();
        em.close();
        emf.close();
        return lista;
    }
    
    public Palabra consultarPorID(int idPalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();       
        Palabra respuesta = em.find(Palabra.class, idPalabra); 
        em.close();
        emf.close();
        return respuesta;
    }
    
    public List<Palabra> getPalabraByNombre(String nombrePalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();       
        List<Palabra> respuesta =  em.createNativeQuery("select * from PALABRA p where p.NombrePalabra LIKE '"+nombrePalabra+"'", Palabra.class).getResultList();
        em.close();
        emf.close();
        return respuesta;
    }
    
    public void agregar(Palabra p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(p);
        em.flush();
        t.commit(); 
        em.close();
        emf.close();
                     
    }
    
    public void modificar(Palabra p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(p);
        em.flush();
        t.commit(); 
        em.close();
        emf.close();
    }
    
    public void eliminar(Palabra p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(p);
        em.flush();
        t.commit();  
        em.close();
        emf.close();
    }
}
   