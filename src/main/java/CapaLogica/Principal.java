/*package CapaLogica;

import Entidades.Vocabulario;
import Entidades.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal {
    public static void main(String[] args) throws IOException {
        String q = "este es un ejemplo de verdad";
        Consulta cq = new Consulta(q);
        Vocabulario vob = new Vocabulario(cq);
        vob.setRutas();
        vob.setVocabulario();

        q = "quijote";
        q = q.toLowerCase();
        String[] consulta = q.split(" ");
        for (String palabra : consulta) {


            if (vob.tabla.containsKey(palabra)) {
                System.out.println(Arrays.toString(vob.tabla.get(palabra).getLista()));
                System.out.println(vob.tabla.size());
                System.out.println(vob.posteo.getLista()[vob.tabla.get(palabra).getLista()[2]].size());
            }
        }


        Respuesta rta = new Respuesta(vob, 10);

        rta.buscar(q);
        for(int i=0;i<rta.cant; i++){
            System.out.println(Arrays.toString(rta.ld[i]));
        }


    }
}


*/