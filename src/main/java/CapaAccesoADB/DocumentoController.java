/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaAccesoADB;

import Entidades.Documento;
import java.util.ArrayList;
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
    
    static final int TAMANHOCARGA = 300;
    
    public DocumentoController(){
      }

    public List<Documento> consultarTodos(){ 
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        List<Documento> lista = em.createQuery("select * from DOCUMENTO", Documento.class).getResultList();
        em.close();
             
        return lista;
    }
 
    public List<Documento> consultarPorNombre(String NombreDoc){
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        List<Documento> lista = em.createNativeQuery("select * from DOCUMENTO d where d.NombreDoc Like '%"+NombreDoc+"%'", Documento.class).getResultList();
        em.close();
        
        return lista;        
    }

    
    public Documento consultarPorID(int id){
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        Documento respuesta = em.find(Documento.class, id);
        em.close();
        
        return respuesta;        
    }
    
    public void agregar(Documento doc){
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(doc);
        em.flush();
        t.commit();         
        em.close();
        
    }
    
    /**
      * carga de a TAMANHOCARGA por vez
      * @param d lista de documentos
      * @return nothing 
      */
    /*
    public void cargarMuchosDocumentos(ArrayList<Documento> d){
        long n = d.size();
        long divisiones = n/TAMANHOCARGA;
        if(d.size() >TAMANHOCARGA){
            for(int i = 0; i < n-TAMANHOCARGA; i+=TAMANHOCARGA){
                long limitesup = i+TAMANHOCARGA;
                if(limitesup > n) limitesup=n;
                this.agregarLista(d.subList(i, (int) limitesup));                
            }
        }else    this.agregarLista(d);
        
        
    }
    
    
    private void agregarLista(List<Documento> ds){
        EntityManager em = ConexionADB.emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.createNativeQuery(this.toValues(ds));        
        em.flush();
        t.commit(); 
        em.close();
    }
    
     
    private String toValues(List<Documento> ds){
        String salida = "";
        for(Documento d: ds){
            salida += d.toValue();
            salida += ", ";
        }
        return salida.substring(0, salida.length() - 1);
    }*/
    
    public void modificar(Documento doc){
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(doc);
        em.flush();
        t.commit(); 
        em.close();
        
    }
    
    public void eliminar(Documento doc){
        
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(doc);
        em.flush();
        t.commit(); 
        em.close();
        
    }
    
}
