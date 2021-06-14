package Entidades;

import CapaAccesoADB.DocumentoController;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.*;

@Entity @Table(name="DOCUMENTO")
public class Documento {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private int idDoc;
    @Column private String urlDoc;
    @Column private String nombre;
    @Inject DocumentoController dc;
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
    
    public void persistir(){
        List<Documento> lista = dc.consultarPorNombre(this.nombre);
        Iterator<Documento> it = lista.iterator();
        while( it.hasNext()) {
            Documento i = it.next();
            if(this.equals(i)){
                return;
            }            
        }        
        dc.agregar(this);
    }
    
    public boolean equals( Documento doc){
        return(doc.nombre.equals(this.nombre) && doc.urlDoc.equals(this.urlDoc));
    }
}
