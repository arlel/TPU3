package Entidades;

import javax.persistence.*;
import CapaAccesoADB.PalabraController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

@Entity @Table(name="PALABRA")
public class Palabra implements Serializable{
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int idPalabra;
    @Column(name= "Documentos") private int n;
    @Column(name= "NombrePalabra") private String nombre;
    @Column(name= "MaxTF")private int maxTF;    
    @Transient private PalabraController pc = new PalabraController();
    
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
    private int tf = 0;
    @Transient
    private int doc;
    @Transient
    private int conteoDoc;
    @Transient
    private Posteo posteo;

    public Posteo getLista() {
        return posteo;
    }

       


    public Palabra(){
        this.doc = -1;
        this.conteoDoc = 0;
        this.posteo = new Posteo();
    }

    /**
     *
     * @param count
     * @param posteo
     * @param documento
     * @param palabra
     */
    public void sumar(int count, Documento documento){


        if(count > doc){
            n++;
            
            tf = 1;
            if(doc == -1){
                maxTF = 1; 
            }
            doc = count;
            posteo.agregarDoc(documento, this, tf);
            conteoDoc++;

        }
        else {

            tf++;
            DocumentoXPalabra tfold = (DocumentoXPalabra) this.getLista().getLista().get(conteoDoc-1);
            tfold.setTf(tf);
            if(tf > maxTF){
                maxTF = tf;
            }
        }
    }
    
    public int getMaxTf(){
        return maxTF;
    }
    
    public boolean existeEnBase(){
        List<Palabra> pList = pc.getPalabraByNombre(this.nombre);
        if(pList.size() !=0){
            for(Palabra p: pList){
                if(Objects.equals(p.nombre,this.nombre)){
                    return true;
        }}}return false;
    }
    
    public void persistir(){    
        List<Palabra> pList = pc.getPalabraByNombre(this.nombre);
        if(pList.size() !=0){
        for(Palabra p: pList){
            if(Objects.equals(p.nombre,this.nombre)){
                this.setIdPalabra(p.getIdPalabra());
                pc.modificar(this);
                return;
        }}}
        pc.agregar(this);         
    }
    
    public int getIdPalabraDeBase(){
        List<Palabra> pList = pc.getPalabraByNombre(this.nombre);
        if(pList.size() !=0){
            for(Palabra p: pList){
            if(Objects.equals(p.nombre,this.nombre)){
                return p.getIdPalabra();
        }}
        }
        return -1;
    }
    
    public void getPalabraDeBase(String nombre){
       List<Palabra> pList = pc.getPalabraByNombre(nombre);
        if(!(pList.isEmpty())){
            for(Palabra p: pList){
            if(Objects.equals(p.nombre, nombre)){
                this.nombre = p.nombre;
                this.maxTF = p.maxTF;
                this.n = p.n;
                this.idPalabra = p.idPalabra;
                return;
        }}
        }
        this.idPalabra = -1;
    }
    
    public String toValue(){
        String w = "("+String.valueOf(this.n)+", \'" + this.nombre +"\', " + String.valueOf(this.maxTF) + ")";
        return w;
    }
}
