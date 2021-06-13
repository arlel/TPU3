/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaAccesoADB;

import Entidades.Documento;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author lenta
 */
@ApplicationScoped
public class DocumentoController {

    public List<Documento> consultarTodos(){ 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrosPU");
        EntityManager em = emf.createEntityManager();
        List<Documento> lista = em.createQuery("select * from DOCUMENTO", Documento.class).getResultList();
        em.close();
        emf.close();     
        return lista;
    }
 /*   
    public List<Documento> consultarPorNombre(String NombreDoc){
        List<Documento> lista = em.createQuery("select * from DOCUMENTO where NombreDoc Like '"+NombreDoc+"'", Documento.class).getResultList();
        return lista;        
    }
    
    public Documento consultarPorID(int id){
        return em.find(Documento.class, id);        
    }
    
    public void agregar(Documento doc){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(doc);
        em.flush();
        t.commit();               
    }
    
    public void modificar(Documento doc){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(doc);
        em.flush();
        t.commit(); 
    }
    
    public void eliminar(Documento doc){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(doc);
        em.flush();
        t.commit(); 
    }
    
    */
}
