/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package CapaAccesoADB;

import Entidades.DocumentoXPalabra;
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
public class DocumentosXPalabraController {//Cambiar de nombre a DocumentosXPalabra
    
    public List<DocumentoXPalabra> consultarTodos(){ 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createQuery("select * from DOCUMENTOSXPALABRA", DocumentoXPalabra.class).getResultList();
        em.close();
        emf.close();
        return lista;
    }
    
    public List<DocumentoXPalabra> getAllByWord(String word){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createQuery("SELECT * from PALABRA p JOIN DOCUMENTOSXPALABRA p1 ON p.IDPalabra=p1.IDPalabra where p.NombrePalabra LIKE '"+word+"'", DocumentoXPalabra.class).getResultList();
        em.close();
        emf.close();
        return lista;
    }
    
    public List<DocumentoXPalabra> getDocumentosXPalabraByIDPalabra(int idPalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createQuery("SELECT * from DOCUMENTOSXPALABRA d where d.IDDoc == "+idPalabra, DocumentoXPalabra.class).getResultList();
        em.close();
        emf.close();
        return lista; 
    }
    
    public void agregar(DocumentoXPalabra docXPalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(docXPalabra);
        em.flush();
        t.commit();  
        em.close();
        emf.close();
    }
    
    public void modificar(DocumentoXPalabra docXPalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(docXPalabra);
        em.flush();
        t.commit(); 
        em.close();
        emf.close();
    }
    
    public void eliminar(DocumentoXPalabra docXPalabra){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPUGASv2");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(docXPalabra);
        em.flush();
        t.commit(); 
        em.close();
        emf.close();
    }
}
