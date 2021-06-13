/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.persistence.*;

/**
 *
 * @author Gonza
 */
@Entity
public class DocumentoXPalabra {
    @Id
    private int idDoc;
    @Id
    private int idPalabra;
    
    @Column private int tf;

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

    public DocumentoXPalabra() {
        
    }

    public DocumentoXPalabra(int idDoc, int idPalabra, int tf) {
        this.idDoc = idDoc;
        this.idPalabra = idPalabra;
        this.tf = tf;
    }
    
    
}
