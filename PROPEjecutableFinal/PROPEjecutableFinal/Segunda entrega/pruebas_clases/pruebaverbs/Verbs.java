/**
 * Nom: Verbs
 * @author
 * Descripció:Esta clase en la encargada de almacenar todas verbales, tanto
 * infinitivas como conjugadas que la aplicaciön sera capaz de detectar. Su
 * ünico atibuto es verbo, que no es otra cosa que un Trie<String>. En esta
 * estructura tendremos las fromas conjugadas, ya sean los verbos regulares o
 * irregulares, y cada una de ellas tendra el infinitivo del que procede
 */
package pruebaverbs;

import java.io.*;
import java.util.*;

public class Verbs {

    private static FileInputStream fitxerInfinitius;
    private static FileInputStream fitxerVerbs;
    protected Trie<String> verbo;

    /***********************************************************************
     * Creadora de Verbs
     ***********************************************************************/
    //Creadora de la clase que crea un Trie de String vacio
    public Verbs() {
        verbo = new Trie<String>();
    }


     /***********************************************************************
     * Get de Verbo
     ***********************************************************************/

    //Get que retorna el objeto implícito de Verbs

    public Trie<String> get_v() {
        
        return verbo;
    }

    /***********************************************************************
     * Get de les Adreces
     ***********************************************************************/
    //Te devuelve el infinitivo dada una forma verbal existente en el trie de
    //verbo; Sino devuelve un mensaje de error corroborando que no existe
    public String get_infinitivo(String word) {
        if (verbo.search(word)) {
            return verbo.get_content(word);
        }
        return "No existeix";
    }

    /***********************************************************************
     * Otras funciones/metodos de Palabra
     ***********************************************************************/
    //Te dice si una forma verbal esta, retornara cierto,  o no esta, retornara
    //falso, en el trie verbo
    public boolean is_verb(String word) {
        if (verbo.search(word)) {
            return true;
        }
        return false;
    }

    //Función auxiliar para determinar si un caracter es una vocal o no
    private boolean is_vocal(Character c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }

    //Este metodo es el encargado de, dado un verbo, regular calcular sus
    //posibles formas conjugadas y guardar esta información en el trie
    //verbo de forma correcta
    public void set_inf_reg(String s) {
        String a;
        String b;
        String c;
        Integer m = s.length();
        if (s.endsWith("o")) {
            a = s.concat("es");
        } else {
            a = s.concat("s");
        }
        if (s.endsWith("e")) {
            b = s.concat("d");
        } else if (s.endsWith("y") && !is_vocal(s.charAt(m - 2))) {
            b = s.subSequence(0, m - 1).toString().concat("i");
            b = b.concat("ed");
        } else {
            b = s.concat("ed");
        }
        c = s.concat("ing");
        insert_in_verbo(a, s);
        insert_in_verbo(b, s);
        insert_in_verbo(c, s);
        insert_in_verbo(s, s);
    }

    //Este metodo se encarga de insertar en el trie verbo una forma conjugada
    //y el infinitivo del que procede. Se inserta de forma que el trie
    //identifique la forma conjugada, y en el nodo que marca el final de
    //palabra tiene asociado el infinitivo
    public void insert_in_verbo(String conj, String inf) {
        if (!verbo.search(conj)) {
            verbo.insert(conj, inf);
        }
    }


     /***********************************************************************
     * Funciones de Carga
     ***********************************************************************/

    /*
     Estas son funciones pertenecientes a la clase del controlador de Dominio,
     * pero para hacer la clase de prueba han sido incluidas aqui. Se encargan
     * de cargar por defecto los archivos correspondientes de verbos, tambien
     * ofreciendo la opción de introducir el path donde se encuentra el archivo
     * desde el directorio actual.
     */

    String Path_proyecto = System.getProperty("java.class.path");
    String separator = System.getProperty("file.separator");
    String path_sistema = null;
    boolean concat_con_path = false;

    public void ini_reg() throws FileNotFoundException {
        String words[] = new String[2];
        if (separator.equals("\\"))  words = Path_proyecto.split(";");
        else  words = Path_proyecto.split(":");
        path_sistema = words[1];
        String r = path_sistema.concat(separator+"pruebaverbs"+separator+"arxius"+separator+"VINF.txt");
        concat_con_path = true;
        carga_infinitivos(r);

    }

    public void ini_irr() throws FileNotFoundException {
        String words[] = null;
        if (separator.equals("\\"))  words = Path_proyecto.split(";");
        else  words = Path_proyecto.split(":");
        path_sistema = words[0];
        String r = path_sistema.concat(separator+"pruebaverbs"+separator+"arxius"+separator+"VIRR.txt");
        concat_con_path = true;
        carga_irregulares(r);

    }

    public void carga_irregulares(String s) throws FileNotFoundException {
        
        try {
            if(!concat_con_path) s = path_sistema.concat(s);
            fitxerVerbs = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerVerbs);
            BufferedReader br = new BufferedReader(isr);
            String aux = new String();

            boolean b = true;
            while (b) {
                String it = br.readLine();
                if (it != null) {
                    aux = it;
                    String[] words;
                    words = separaString(aux, " ");
                    String Infinitivo = words[0];
                    String Pasado = words[1];
                    String Participio = words[2];
                    String rrr = words[3];
                    String ggg = words[4];
                    insert_in_verbo(Infinitivo, Infinitivo);
                    insert_in_verbo(Pasado, Infinitivo);
                    insert_in_verbo(Participio, Infinitivo);
                    insert_in_verbo(rrr, Infinitivo);
                    insert_in_verbo(ggg, Infinitivo);
                } else {
                    fitxerVerbs.close();
                    concat_con_path = false;
                    b = false;
                }
            }
        } catch (IOException e) {
            System.out.println("------------- Error E/S ! -----------");
        }
    }

    public void carga_infinitivos(String s) throws FileNotFoundException {

        String aux = null;

        try {

            if(!concat_con_path) s = path_sistema.concat(s);
            fitxerInfinitius = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerInfinitius);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
            while (b) {
                String it = br.readLine();
                if (it != null) {
                    aux = it;
                    set_inf_reg(aux);
                } else {
                    fitxerInfinitius.close();
                    concat_con_path = false;
                    b = false;
                }
            }
        } catch (IOException e) {
            System.out.println("------------- Error E/S ! -----------");
        }
    }

    public static String[] separaString(String pString, String pDelimitador){
        int i=-1;
        StringTokenizer stringAProcesar = new StringTokenizer(pString, pDelimitador);
        int numTokens= stringAProcesar.countTokens();
        //
        if (numTokens == 0) {numTokens++;}
        String[] campos = new String[numTokens];
        //
        while (stringAProcesar.hasMoreTokens())
        {
          i++;
          campos[i]= stringAProcesar.nextToken();
          campos[i]=campos[i].trim();
        }

        if (i==0){campos[0]=pString;}
        return campos;
    }
}
