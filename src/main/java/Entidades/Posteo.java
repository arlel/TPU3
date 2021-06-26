package Entidades;

import java.util.ArrayList;
import java.util.Arrays;

public class Posteo {//Lista de posteo que enlaza para una palabra, todos sus documentos
    public ArrayList listaPosteo;
    
    public Posteo() {
        this.listaPosteo = new ArrayList<DocumentoXPalabra>();
    }


    public ArrayList getLista() {
        return listaPosteo;
    }


    public void agregarDoc(Documento doc, Palabra p, int tf){
        DocumentoXPalabra documento = new DocumentoXPalabra(doc.getIdDoc(), p.getIdPalabra(), tf);
        listaPosteo.add(documento);
    }



}
