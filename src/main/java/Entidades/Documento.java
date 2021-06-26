package Entidades;

import CapaAccesoADB.DocumentoController;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.persistence.*;

@Entity @Table(name="DOCUMENTO")
public class Documento implements Serializable{
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int idDoc;
    @Column private String urlDoc;
    @Column(name= "NombreDoc") private String nombre;
    @Transient private DocumentoController dc = new DocumentoController();
    public Documento(){}
    
    public Documento(String ruta, String nombre) {
        this.nombre = nombre;
        this.urlDoc = ruta;
    }

    public int getIdDoc() {
        return idDoc;
    }
    

    public String getURL() {
        return urlDoc;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setID(int id){
        this.idDoc = id;
    }
    
    public void persistir(){
        
        String nom = this.nombre;
        List<Documento> lista = dc.consultarPorNombre(this.nombre);
        if(lista.size()>0 ){
        Iterator<Documento> it = lista.iterator();
        while( it.hasNext()) {
            Documento i = it.next();
            if(this.equals(i)){
                return;
            }            
        }    }    
        dc.agregar(this);
    }
    
    public boolean equals( Documento doc){
        try{
        return(doc.nombre.equals(this.nombre));
        }
        catch(NullPointerException e){
            return false;
        }
    }
    
    public int getIdDocDeBase(){
        List<Documento> docs = dc.consultarPorNombre(this.nombre);
        for(Documento i: docs) if(i.equals(this))return i.getIdDoc();
        return -1;
    }
    
    public void getDocumentoDeBase(String nom){
        List<Documento> lista = dc.consultarPorNombre(nom);
        if(lista.size()>0 ){
            Iterator<Documento> it = lista.iterator();
            while( it.hasNext()) {
                Documento i = it.next();
                if(Objects.equals(nom, i.nombre)){
                    this.idDoc= i.getIdDoc();
                    this.nombre = nom;
                    this.urlDoc = i.getURL();
                    return;
                }            
            }
        }
        this.idDoc = -1;
    }
    
    public Documento getDocumentoDeBase(int id){
        Documento doc = dc.consultarPorID(id);
        if(doc == null){return null;}
        return doc;
    }
    
}
