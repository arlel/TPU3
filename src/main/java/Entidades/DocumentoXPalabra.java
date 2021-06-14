/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import CapaAccesoADB.DocumentosXPalabraController;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.*;

/**
 *
 * @author Gonza
 */
@Entity @Table(name="DOCUMENTOSXPALABRA")
public class DocumentoXPalabra implements Serializable {
    @Id
    private int idDoc;
    @Id
    private int idPalabra;
    
    @Column private int tf;
    
    @Transient private DocumentosXPalabraController dpc = new DocumentosXPalabraController();
    
    public DocumentoXPalabra(){}

    public int getIdDoc() {
        return idDoc;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public int getTf() {
        return tf;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }
    
    
    public DocumentoXPalabra(int idDoc, int idPalabra, int tf) {
        this.idDoc = idDoc;
        this.idPalabra = idPalabra;
        this.tf = tf;
    }
    
    public void persistir(){
        
        List<DocumentoXPalabra> dxp = dpc.getDocumentosXPalabraByIDPalabra(this.idPalabra);
        if(!(dxp.isEmpty())){
            dpc.modificar(this);
        }
        else dpc.agregar(this);
    }
    
}
