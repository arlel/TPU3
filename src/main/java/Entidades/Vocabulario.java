package Entidades;

import CapaLogica.Consulta;
import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import Entidades.*;

public class Vocabulario { //lista de todas las palabras que se identificaron
    public Hashtable<String, Palabra> tabla; //Nombre de la palabra con la info de esa palabra
    public Consulta q;
    public ArrayList<String> rutas;//Documentos
    public Posteo posteo; //


    //tf Es la frecuencia del término tr (term frequency). Es la cantidad de veces que tr aparece en
    //el documento di.
    //N Es la cantidad total de documentos de la base.
    //nr Cantidad de documentos en los que aparece el término tr. Por lo mismo, ns es la cantidad
    //de documentos en los que aparece el término ts.
    //idf Llamamos frecuencia inversa del término tr a este factor. Como se puede ver, se calcula
    //como el logaritmo del cociente entre N y nr.
    
    
    //El vocabulario enlaza el backend con la base, para poder realizar la busqueda de la palabra.

    public Vocabulario(Consulta q) {
        this.q = q;
        this.rutas = new ArrayList<>();
        this.tabla = new Hashtable<>();


    }


    public Vocabulario(String str) {
        this.q = new Consulta(str);
        this.rutas = new ArrayList<>();
        this.tabla = new Hashtable<>();

    }


    public void setRutas() throws IOException {
        //Esto se puede hacer Consultando a Google drive O en una carpeta LOCAL del proyecto.
        Files.walk(Paths.get("D:\\tpu\\src\\archivos")).forEach(ruta-> {
            if (Files.isRegularFile(ruta)) {
                this.rutas.add(ruta.toString());

            }
        });
    }


    public void setVocabulario(){
        int count = 0;
        this.posteo = new Posteo(1000000);
        int posPosteo = 0;

        for (String ruta:rutas){
            Documento doc = new Documento(ruta, 0);
            try{
                File fl = new File(ruta);
                Scanner sc = new Scanner(fl);


                while (sc.hasNext()) {
                    String str = sc.next();

                    if (!str.contains("@")){str = str.replace(".", "");}
                    str = str.replace(":", "");
                    str = str.replace(",", "");
                    str = str.replace(";", "");
                    str = str.replace("?", "");
                    str = str.replace("!", "");
                    str = str.replace(")", "");
                    str = str.replace("(", "");
                    str = str.replace("\"", "");
                    str = str.replace("*", "");
                    str = str.replace("'s", "");
                    str = str.replace("«","");
                    str = str.replace("/","");
                    str = str.toLowerCase();

                     if(!tabla.containsKey(str)){
                        Palabra p = new Palabra();
                        tabla.put(str, p);
                        p.setPosteo(posPosteo);
                        posPosteo++;
                    }

                    Palabra p = tabla.get(str);
                    p.sumar(count, posteo, doc);





                }
            count ++;

            }
            catch (FileNotFoundException e){
                System.out.println("no taba el archivo ahi pibe, fijate");
            }
        }
    }
}
