package CapaLogica;

import Entidades.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;


/**
 *
 * @author Gon
 */
public class Respuesta {
    public Vocabulario voc;
    public String[][] ld;
    public int cant;
    public String[][] qidf;

    public Respuesta(int r) {
        this.voc = new Vocabulario();
        this.cant = r;
        this.ld = new String[r][2];
        for (int i = 0; i < ld.length; i++) {
            ld[i][0] = "";
            ld[i][1] = "0";
        }
    }

    public String[][] buscar(String[] q){

                
        this.qidf = new String[q.length][2];
        for (int i = 0; i < qidf.length; i++) {
            qidf[i][0] = "";
            qidf[i][1] = "0";
        }
        int i = 0;
        int conteoQ = 0;
        for (String word:q) {
            double idf = calcularIDF(word);
            String[] q1 = {word, String.valueOf(idf)};
            qidf[i] = q1;
            i++;
        }
        qidf = ordenarQIDF(qidf);


        while(conteoQ < qidf.length){
            double idf = Double.parseDouble(qidf[conteoQ][1]);
            //busca las palabras en base
            Palabra p = new Palabra();
            p.getPalabraDeBase(qidf[conteoQ][0]);
            if(p.getIdPalabra() != -1){
                //busca documentosXpalabra
                DocumentoXPalabra d = new DocumentoXPalabra();
                List<DocumentoXPalabra> listaPosteo = d.getDXPDeBase(p.getIdPalabra());
                if(listaPosteo.isEmpty()){
                    continue;
                            }
                for(int j=0;j<listaPosteo.size();j++){
                    DocumentoXPalabra dxp =  listaPosteo.get(j);
                    Documento d2 = new Documento();
                    d2 = d2.getDocumentoDeBase(dxp.getIdDoc());
                    String[] doc = dxp.convertirAString(d2.getURL());
                    Double peso = calcularPeso(idf, Integer.parseInt(doc[1]));
                    ld = sumarEnLD(ld, doc, peso);

                }

            }
            conteoQ++;
        }
        return ld;
    }

    public double calcularPeso(double idf, int tf){
        return tf * idf;
    }

    public double calcularIDF(String word){

        if(this.voc.tabla.containsKey(word)) {
            Palabra p = voc.tabla.get(word);
            return Math.log(((float) (voc.rutas.size())/ (p.getLista()[0])  ));
        }

        return 0;
    }


    public String[][] ordenarQIDF(String[][] qidf){
        if(qidf.length <= 1){return qidf;}

        for(int i = 0; i < qidf.length - 1; i++){
            String[] mayor = qidf[i];
            for( int j = i+1; j < qidf.length ; j ++){


                if(Double.parseDouble(qidf[j][1]) >  Double.parseDouble(mayor[1]) ){
                    String[] aux = qidf[i];
                    qidf[i] = qidf[j];
                    qidf[j] = aux;
                }
            }
        }
        return  qidf;
    }

    //REVISAR
    public String[][] sumarEnLD(String[][] ld, String[] doc, Double peso){

        for(int i = 0; i<ld.length; i++){
            if(doc[0].equals(ld[i][0])){
                Double nuevoPeso = Double.parseDouble(ld[i][1]) + peso;
                ld[i][1] = String.valueOf(nuevoPeso);
                ld = ordenarQIDF(ld);
                return ld;
            }
        }

        for (int i = 0; i < ld.length; i++) {
            if(peso > Double.parseDouble(ld[i][1])){
                return insertarEnLD(ld, doc, peso, i);
            }
        }
        return  ld;
    }

    public String[][] insertarEnLD(String[][] ld, String[] doc, Double peso, int pos){
        String[][] ldCopia = new String[ld.length + 1][2];
        System.arraycopy(ld, 0, ldCopia, 0, pos);
        ldCopia[pos][0] = doc[0];
        ldCopia[pos][1] = String.valueOf(peso);
        System.arraycopy(ld, pos, ldCopia, pos +1, ld.length - pos);
        return Arrays.copyOfRange(ldCopia, 0, ld.length);
    }
    
    public void cargarDocumentos() throws IOException{
        try {
            voc.setRutas();
            voc.setVocabulario();
            voc.persistir();    
            
        }
        catch(IOException e){}
        
    }
}
