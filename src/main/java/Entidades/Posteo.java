package Entidades;

import java.util.ArrayList;
import java.util.Arrays;

public class Posteo {//Lista de posteo que enlaza para una palabra, todos sus documentos
    public ArrayList[] lista;
    
    public Posteo(int n) {
        this.lista = new ArrayList[n];
        for(int i =0 ; i < n ; i++){
            lista[i] = new ArrayList<String[]>();
        }
    }


    public ArrayList[] getLista() {
        return lista;
    }


    public void agregarDoc(Documento doc, int n, int tf){
        String[] documento = new String[2];
        documento[0] = doc.getURL();
        documento[1] = String.valueOf(tf);
        lista[n].add(documento);

    }



}
