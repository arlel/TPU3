package CapaLogica;

import java.util.List;

public class Consulta {
    String consulta;

    public Consulta(String consulta) {
        this.consulta = consulta;
    }


    public String[] GetPalabras(){
        return consulta.split(" ");
    }
}
