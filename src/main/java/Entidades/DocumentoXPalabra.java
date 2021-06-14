/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import CapaAccesoADB.DocumentosXPalabraController;
import java.io.Serializable;
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
    
    private DocumentosXPalabraController dpc = new DocumentosXPalabraController();
    
    public DocumentoXPalabra(){}
    public DocumentoXPalabra(String doc, String tief, int idPalabrita) {
        this.idDoc = Integer.parseInt(doc);
        this.tf = Integer.parseInt(tief);
        this.idPalabra = idPalabrita;
    }

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
        dpc.modificar(this);
    }
    
}
