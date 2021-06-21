/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package CapaAccesoADB;

import Entidades.DocumentoXPalabra;
import java.util.ArrayList;
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
   
    static final int TAMANHOCARGA = 6000;
    
    public DocumentosXPalabraController(){
    }
    
    public List<DocumentoXPalabra> consultarTodos(){ 
       
        EntityManager em = ConexionADB.emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createQuery("select * from DOCUMENTOSXPALABRA", DocumentoXPalabra.class).getResultList();
        em.close();
        return lista;
    }
    
    public List<DocumentoXPalabra> getAllByWord(String word){
       EntityManager em = ConexionADB.emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createQuery("SELECT * from PALABRA p JOIN DOCUMENTOSXPALABRA p1 ON p.IDPalabra=p1.IDPalabra where p.NombrePalabra LIKE '"+word+"'", DocumentoXPalabra.class).getResultList();
        em.close();
        return lista;
    }
    
    public List<DocumentoXPalabra> getDocumentosXPalabraByIDPalabra(int idPalabra){
        EntityManager em = ConexionADB.emf.createEntityManager();
        List<DocumentoXPalabra> lista = em.createNativeQuery("SELECT * from DOCUMENTOSXPALABRA d where d.IDPalabra= "+idPalabra, DocumentoXPalabra.class).getResultList();
        em.close();
        return lista; 
    }
    
    public DocumentoXPalabra getById(int idPalabra, int idDocumento){
        EntityManager em = ConexionADB.emf.createEntityManager();
        String IdP = Integer.toString(idPalabra);
        String IdD = Integer.toString(idDocumento);
        DocumentoXPalabra poust = (DocumentoXPalabra) em.createNativeQuery("Select * from DOCUMENTOSXPALABRA d where d.IDDoc ="+IdD+" AND d.IDPalabra ="+IdP,DocumentoXPalabra.class).getSingleResult();
        em.close();
        return poust; 
    }
    
    public void agregar(DocumentoXPalabra docXPalabra){
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.createNativeQuery("insert into DOCUMENTOSXPALABRA(tf, idPalabra, idDoc) values(?, ?, ?)").setParameter(1, docXPalabra.getTf()).setParameter(2, docXPalabra.getIdPalabra()).setParameter(3, docXPalabra.getIdDoc()).executeUpdate();
        //em.persist(docXPalabra); //cambiarlo por un insert a ver si asi anda (?
        em.flush();
        t.commit();  
        em.close();
    }
    
    public void cargarMuchosDocumentoXPalabra(ArrayList<DocumentoXPalabra> d){
        StringBuilder query = new StringBuilder();
        String consulta = "insert into documentosxPalabra (IdDoc, IdPalabra, TF ) values ";
        int n = d.size();
        int divisiones = n/TAMANHOCARGA;
        query.append(consulta);
        for(int i = 0; i < n; i++){
            query.append(String.format("(%d, %d, %d ),", d.get(i).getIdDoc(), d.get(i).getIdPalabra(), d.get(i).getTf()));                
            if((i!= 0) && ((i%TAMANHOCARGA == 0 )||(i == n-1))){
                String q = query.toString().substring(0, query.length()-1);
                this.agregarMuchos(q);  
                query.delete(consulta.length(), query.length());
            }
        }   
    }
    
    
    private void agregarMuchos(String query){
        EntityManager em = ConexionADB.emf.createEntityManager();       
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.createNativeQuery(query).executeUpdate();
        em.flush();
        t.commit(); 
        em.close();
    } 
    
     
    
    
    public void modificar(DocumentoXPalabra docXPalabra){
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(docXPalabra);
        em.flush();
        t.commit(); 
        em.close();        
    }
    
    public void eliminar(DocumentoXPalabra docXPalabra){
        EntityManager em = ConexionADB.emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(docXPalabra);
        em.flush();
        t.commit(); 
        em.close();
    }
}