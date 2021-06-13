package Entidades;

import javax.persistence.*;

@Entity
public class Palabra {
    
    @Id private int idPalabra;
    @Column private int n;
    @Column private String nombre;

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public int getN() {
        return n;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    @Transient 
    private int[] lista = new int[3];
    @Transient
    private int tf = 0;
    @Transient
    private int doc;
    @Transient
    private int conteoDoc;

    public int[] getLista() {
        return lista;
    }

       


    public Palabra(){
        this.doc = -1;
        this.conteoDoc = 0;
    }

    /**
     *
     * @param count
     * @param posteo
     * @param documento
     * @param palabra
     */
    public void sumar(int count, Posteo posteo, Documento documento){


        if(count > doc){
            if(tf > lista[1]){
                lista[1] = tf;
            }
            lista[0]++;
            tf = 1;
            doc = count;
            posteo.agregarDoc(documento, this.getPosteo(), tf);
            conteoDoc++;

        }
        else {

            tf++;
            String[] tfold = (String[]) posteo.getLista()[this.getPosteo()].get(conteoDoc-1);
            tfold[1] = String.valueOf(tf);
            posteo.getLista()[this.getPosteo()].set(conteoDoc-1,tfold);
        }
    }

    public int getPosteo(){
        return lista[2];
    }

    public void setPosteo(int pos){
        lista[2] = pos;
    }
}
