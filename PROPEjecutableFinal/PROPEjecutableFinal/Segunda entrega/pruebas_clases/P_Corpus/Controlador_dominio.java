/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package P_Corpus;
import java.io.*;
import java.util.*;




/**
 *
 * @author Administrador
 */
public class Controlador_dominio {
    private static FileInputStream fitxer;
    private static FileInputStream fitxerCorpus;
    private static FileInputStream fitxerVerbs;
    private static FileInputStream fitxerInfinitius;
    private static FileInputStream fitxerText;
    private static FileInputStream fitxerDiccionari;
     private static FileInputStream fitxerG;


    String Path_proyecto = System.getProperty("java.class.path");
    String separator = System.getProperty("file.separator");
    String path_sistema = null;
    boolean concat_con_path = false;


    


    public Corpus Cor;



    //Creadora de la clase, inicializa las estructuras de datos sin insertarles valores
    public void Controlador_dominio() {
        Cor = new Corpus();
        
    }

    //Metodo que se encarga de inicializar el corpus, cogiendo el archivo desde
    //el archivo que viene de serie
    public Corpus ini_corpus() throws FileNotFoundException {
        String words[] = new String[2];
        if (separator.equals("\\"))  words = Path_proyecto.split(";");
        else  words = Path_proyecto.split(":");
        path_sistema = words[1];
        String r = path_sistema.concat(separator+"Archivos_Preinstalados"+separator+"PairsNounVerb.txt");
        concat_con_path = true;
        Cor = new Corpus();
        return carga_corpus(r);
    }


    public Corpus carga_corpus(String s) throws FileNotFoundException {
        Corpus res = new Corpus();
        String aux = null;
        try {
            String words[] = new String[2];
            if (separator.equals("\\"))  words = Path_proyecto.split(";");
            else  words = Path_proyecto.split(":");
            path_sistema = words[1];
            
            if(!concat_con_path) s = path_sistema.concat(s);
            System.out.println(s);
            
            fitxerCorpus = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerCorpus);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
            while (b) {
                String it = br.readLine();
                if (it != null) {
                    aux = it;
                    String[] t = aux.split(" ");
                    String firstWord = t[0];
                    String secondWord = t[1];
                    res.insert_c(firstWord, secondWord);
                } else {
                    fitxerCorpus.close();
                    concat_con_path = false;
                    b = false;
                    throw new EOFException();
                }
            }
        }
        catch (EOFException r) {
            return res;
        }
        catch (IOException e) {
            System.out.println("------------- Error E/S ! -----------");
        }
        return null;
    }
  
    //Este metodo es el encargado de guardar todo el Corpus de la aplicación
    //en la ruta que pase el usuario por el string s
    public void save_corpus(String s){
        ArrayList<Ejemplo> ar = Cor.get_all_c();
        escribe_fichero(ar, s);
    }
    
   
    //Este es un metodo para guardar un Array de Ejemplos, ya sean las reglas
    //o el corpus, en un archivo. La ubicación y nombre del archivo le vienen
    //por el String direc
    public void escribe_fichero(ArrayList<Ejemplo> ar, String direc){
        String words[] = new String[2];
        if (separator.equals("\\"))  words = Path_proyecto.split(";");
        else  words = Path_proyecto.split(":");
        path_sistema = words[1];
        String r = path_sistema.concat(direc);
        FileWriter fichero = null;
        BufferedWriter pw = null;
         System.out.println( ar.size());
        try{
            fichero = new FileWriter(r);
            pw = new BufferedWriter(fichero);
            Integer a = ar.size();
            for (int i = 0; i < a; i++){
                for (int j = 0; j < ar.get(i).get_c(); ++j){
                    System.out.println( i);
                    if(j>0)
                    System.out.println( i  + " " +  j);
                    String s = ar.get(i).get_a();
                    s = s.concat(" ");
                    s = s.concat(ar.get(i).get_b());
                    pw.write(s);
                    pw.newLine();
                }
            }
        } catch (IOException e){
            System.out.println("------------- Error E/S ! -----------");
        }
    }


    private static String[] separaString(String pString, String pDelimitador){
        int i=-1;
        StringTokenizer stringAProcesar = new StringTokenizer(pString, pDelimitador);
        int numTokens= stringAProcesar.countTokens();
        if (numTokens == 0) {numTokens++;}
        String[] campos = new String[numTokens];
        while (stringAProcesar.hasMoreTokens()) {
          i++;
          campos[i]= stringAProcesar.nextToken();
          campos[i]=campos[i].trim();
        }
        if (i==0){campos[0]=pString;}
        return campos;
    }

}
