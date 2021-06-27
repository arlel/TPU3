package Entidades;

import java.util.ArrayList;
import java.util.Arrays;

public class Posteo {//Lista de posteo que enlaza para una palabra, todos sus documentos
    public ArrayList listaPosteo;
    public String palabra;
    
    public Posteo(String palabra) {
        this.listaPosteo = new ArrayList<DocumentoXPalabra>();
        this.palabra = palabra;
    }


    public ArrayList getLista() {
        return listaPosteo;
    }


    public void agregarDoc(Documento doc, Palabra p, int tf){
        DocumentoXPalabra documento = new DocumentoXPalabra(doc.getIdDoc(), p.getIdPalabra(), tf);
        documento.setNombreDoc(doc.getNombre());
        listaPosteo.add(documento);
    }



}
