/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author sebas
 */
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import CapaLogica.Consulta;
import CapaLogica.Respuesta;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;





@Path("/Buscador")
public class BuscadorEndPoint {
    
String stopwords[] = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", 
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



    
    //@Inject private Consulta consultar;
    private Respuesta respuesta = new Respuesta(10);
    
    
    @GET
    @Path("/")
    public Response index() { 
        return Response.ok("works").build();
    }
    
    
    @GET
    @Path("/busqueda/{q}")
    @Produces("application/json")
    public Response urls(@PathParam("q") String q){
        Consulta c = new Consulta(q);
        String[] busqueda = c.GetPalabras();
          for(int i = 0; i < busqueda.length; i++)
          {
              for(int j = 0; j < stopwords.length; j++)
              {
                 if(stopwords[j].equals(busqueda[i]))
                    {
                       busqueda[i].replace(busqueda[i], "");
                       break;
                    }
              }
          }
          //Retornamos 204 en caso de que se busquen solo stopwords 
          //o palabras en donde mostraria todo el texto 
        if(busqueda.length == 0){
            return Response.noContent().build();
        }  
        String[][] urls = respuesta.buscar(busqueda);
          //url deberia devolver un vector de objetos de tipo documento
          return Response.ok(urls).build();
                  }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
    
   /* 
    @GET
    @Path("/documento")
    @Produces("application/json")
    public Response doc(String url){
        String doc = respuesta.buscarDoc(url);
        //doc deberia mostrar el contenidod el doc
    return Response.ok(doc).build();
    }
    */
    //cargar todos los documentos que estan en un archivo especificado
    @GET
    @Path("/cargar_documentos")
    @Produces("application/json")
    public Response cargar() {
        try {
            respuesta.cargarDocumentos();
            return Response.accepted().build();}
            
        catch (IOException e){ return Response.noContent().build();}        
    }
    
    /*@GET
    @Path("/agregar_documento")
    @Produces("application/json")
    public Response cargar(String url){
        String doc = respuesta.buscarDoc(url);
        //doc deberia mostrar el contenidod el doc
    return Response.ok(doc).build();
    }
   */

    

            
  
    
}
