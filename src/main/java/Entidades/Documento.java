package Entidades;

import javax.persistence.*;

@Entity 
public class Documento {
    
    @Id    private int idDoc;
    @Column private String urlDoc;
    private int tf;
    @Column private String nombre;

    public Documento(){}
    
    public Documento(String nombre, int tf) {
        this.urlDoc = nombre;
        this.tf = tf;
    }

    public int getTf() {
        return tf;
    }

    public String getURL() {
        return urlDoc;
    }

    public void sumar(){
        this.tf ++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
