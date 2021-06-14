package CapaLogica;

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
import Entidades.Documento;
import Entidades.Palabra;
import Entidades.Posteo;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Vocabulario { //lista de todas las palabras que se identificaron
    public LinkedHashMap<String, Palabra> tabla; //Nombre de la palabra con la info de esa palabra
    public Consulta q;
    public ArrayList<String> rutas;//Documentos
    public Posteo posteo; //
    public LinkedHashSet<Documento> docs;
    private String stopwords[] = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", 
"you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", 
"she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", 
"theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", 
"those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", 
"having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", 
"as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", 
"through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", 
"on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", 
"why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor",
"not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should",
"now", "project", "gutenberg's", "etext", "see", "index", "copyright", "laws", "changing", "world", "sure",
"check", "copyright", "laws", "country", "posting", "files!!", "please", "take", "look", "important",
"information", "header", "encourage", "keep", "file", "disk", "keeping", "electronic", "path", "open",
"next", "readers", "remove", "welcome", "world", "free", "plain", "vanilla", "electronic", "texts",
"etexts", "readable", "humans", "computers", "since", "1971", "etexts", "prepared", "hundreds",
"volunteers", "donations", "information", "contacting", "project", "gutenberg", "get", "etexts",
"information", "included", "need", "donations" };    

    //tf Es la frecuencia del término tr (term frequency). Es la cantidad de veces que tr aparece en
    //el documento di.
    //N Es la cantidad total de documentos de la base.
    //nr Cantidad de documentos en los que aparece el término tr. Por lo mismo, ns es la cantidad
    //de documentos en los que aparece el término ts.
    //idf Llamamos frecuencia inversa del término tr a este factor. Como se puede ver, se calcula
    //como el logaritmo del cociente entre N y nr.
    
    
    //El vocabulario enlaza el backend con la base, para poder realizar la busqueda de la palabra.
    public Vocabulario(){        
        this.rutas = new ArrayList<>();
        this.tabla = new LinkedHashMap<>();
        this.docs = new LinkedHashSet<>();
    }
    public Vocabulario(Consulta q) {
        this.q = q;
        this.rutas = new ArrayList<>();
        this.tabla = new LinkedHashMap<>();
        this.docs = new LinkedHashSet<>();


    }


    public Vocabulario(String str) {
        this.q = new Consulta(str);
        this.rutas = new ArrayList<>();
        this.tabla = new LinkedHashMap<>();

    }


    public void setRutas() throws IOException {
        //Esto se puede hacer Consultando a Google drive O en una carpeta LOCAL del proyecto.
        Files.walk(Paths.get("F:\\AccesosWindows\\Documentos\\NetBeansProjects\\TPU3\\src\\main\\webapp\\recursos")).forEach(ruta-> {
            if (Files.isRegularFile(ruta)) {
                if(ruta==null) return;
                this.rutas.add(ruta.toString());

            }
        });
    }


    public void setVocabulario(){
        boolean salir = false;
        int count = 0;
        this.posteo = new Posteo(1000000);
        int posPosteo = 0;

        for (String ruta:rutas){
            String[] temp;
            temp = ruta.split("\\\\");
            int var = temp.length-1;
            String nombre = temp[var];
            Documento doc = new Documento(ruta, nombre);
            try{
                File fl = new File(ruta);
                Scanner sc = new Scanner(fl);
                docs.add(doc);

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
                    //Si str es una stopword, verifico la proxima palabra
                    for(int j = 0; j < stopwords.length; j++)
                        {
                           if(stopwords[j].equals(str))
                              {
                                salir = true;
                                break;
                              }
                        }
                    if(salir){
                        salir = false;
                        continue;
                    }
                    if(!tabla.containsKey(str)){
                        Palabra p = new Palabra();
                        p.setNombre(str);
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
    public void persistir(){
        Iterator it2 = docs.iterator();
         while(it2.hasNext()){
             ((Documento)it2.next()).persistir();
         }
         Collection col = tabla.values();
         Iterator it = col.iterator();
         while(it.hasNext()){
            Palabra p =(Palabra)it.next();
            p.persistir();
            ArrayList[] posteosTotales = posteo.getLista();
            ArrayList posteosPalabra = posteosTotales[p.getPosteo()];
            Iterator it3 = posteosPalabra.iterator();
            while(it3.hasNext()){
                String[] cosa = (String[]) it3.next();
                Documento doc = new Documento();
                doc.setNombre(cosa[0]);                
                DocumentoXPalabra DXP = new DocumentoXPalabra(doc.getIdDocDeBase(), p.getIdPalabraDeBase(), Integer.parseInt(cosa[1]));
                DXP.persistir();
            }            
                     
         }
         
         
         
         
   }
}
