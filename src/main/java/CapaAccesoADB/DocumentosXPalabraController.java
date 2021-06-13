/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
/*
package CapaAccesoADB;

import Entidades.DocumentoXPalabra;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
*/
/**
 *
 * @author lenta
 */
/*
public class DocumentosXPalabraController {//Cambiar de nombre a DocumentosXPalabra
    @Inject
    private EntityManager em;
    public List<DocumentoXPalabra> consultarTodos(){ 
        
        List<DocumentoXPalabra> lista = em.createQuery("select * from DOCUMENTOSXPALABRA", DocumentoXPalabra.class).getResultList();
        return lista;
    }
    
    public List<DocumentoXPalabra> getAllByWord(String word){
        List<DocumentoXPalabra> lista = em.createQuery("SELECT * from PALABRA p JOIN DOCUMENTOSXPALABRA p1 ON p.IDPalabra=p1.IDPalabra where p.NombrePalabra LIKE '"+word+"'", DocumentoXPalabra.class).getResultList();
        return lista;
    }
    
    public List<DocumentoXPalabra> getDocumentosXPalabraByIDPalabra(int idPalabra){
        List<DocumentoXPalabra> lista = em.createQuery("SELECT * from DOCUMENTOSXPALABRA d where d.IDDoc == "+idPalabra, DocumentoXPalabra.class).getResultList();
        return lista; 
    }
    
    public void agregar(DocumentoXPalabra docXPalabra){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(docXPalabra);
        em.flush();
        t.commit();               
    }
    
    public void modificar(DocumentoXPalabra docXPalabra){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(docXPalabra);
        em.flush();
        t.commit(); 
    }
    
    public void eliminar(DocumentoXPalabra docXPalabra){
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(docXPalabra);
        em.flush();
        t.commit(); 
    }
}
*/