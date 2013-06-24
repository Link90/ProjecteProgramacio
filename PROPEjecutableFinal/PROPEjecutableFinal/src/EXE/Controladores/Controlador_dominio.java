/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Controladores;
import EXE.Dominio.*;
import EXE.Compartidas.*;
import java.io.*;
import java.util.*;




/**
 *
 * @author cristian.barrio, albert.gazquez, jose.manuel.ramos
 */
public class Controlador_dominio {
    private static FileInputStream fitxer;
    private static FileInputStream fitxerCorpus;
    private static FileInputStream fitxerVerbs;
    private static FileInputStream fitxerInfinitius;
    private static FileInputStream fitxerText;
    private static FileInputStream fitxerDiccionari;
    private static FileInputStream fitxerTexto;


    String Path_proyecto = System.getProperty("user.dir");
    String separator = System.getProperty("file.separator");
    String path_sistema = null;
    boolean concat_con_path = false;

    private String directorio_irregulares_defecto = Path_proyecto.concat(
            separator+"Archivos_Preinstalados"+separator+
            "VerbosIrregularesConjugados.txt");
    private String directorio_infinitivos_defecto = Path_proyecto.concat(
            separator+"Archivos_Preinstalados"+separator+"VerbsWordNet.txt");
    private String directorio_corpus_defecto = Path_proyecto.concat(
            separator+"Archivos_Preinstalados"+separator+"PairsNounVerb.txt");
    private String directorio_diccionario_defecto = Path_proyecto.concat(
            separator+"Archivos_Preinstalados"+separator+"NounsWordNet.txt");


    private Corpus Cor = new Corpus();
    private Verbs v = new Verbs();
    private Diccionario d = new Diccionario();
    private Texto t = new Texto(" ");
    private ArrayList<String> v_text;
    private Regla rg = new Regla();
    private Integer alfa = 3;
    private Integer cont = 0;
    private Double pr = 0.0;
    private int err = 0;


    private ArrayList<Integer> v_pos_conju;

    //Creadora de la clase, inicializa las estructuras de datos sin insertarles valores
    public void Controlador_dominio() {
        Cor = new Corpus();
        v = new Verbs();
        d = new Diccionario();
        v_text = new ArrayList<String>();
        rg = new Regla();
    }

    public int get_err() {
        return this.err;
    }
    /*
    public void  Ejecutar_inicio() throws FileNotFoundException, IOException, InterruptedException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            inicializacion();
            System.out.println("Ejecutar_inicio");
                do {
                System.out.println();
                System.out.println("1 - Introducir texto");
                System.out.println("2 - Nominalizar verbo");
                System.out.println("3 - Aprendizaje");
                System.out.println("4 - Opciones de carga");
                System.out.println("5 - Cerrar");
                String s = in.readLine();
                if(s.equals("1")) {
                    leer_texto();
                    System.out.println("Ejecutar_inicio:");
                }else if(s.equals("2")) {
                    System.out.println("Introduzca el verbo deseado para sus nominalizaciones:");
                    s = in.readLine();
                    ArrayList<String> nom = new ArrayList<String>();
                    this.aplicar_reglas(nom, rg, s);
                    this.busca_nom(d, nom);
                }else if (s.equals("3")) {
                    this.aprender();
                }else if(s.equals("4")){
                    this.ejecutar_carga();
                    System.out.println("Ejecutar_inicio:");
                }else if (s.equals("5")) {
                    System.exit(0);
                }else {
                    System.out.println("Entrada no válida");
                }
                
            }  while (true);
        }
        catch(IOException e) {
            System.out.println("------------- Error E/S ! ------Ejecutar_inicio-----");
        }
    }
    */
    /*
    //Metodo encargado de inicializar todas las estructuras de datos con los
    //archivos que vienen por defecto
    public void inicializacion() throws FileNotFoundException{
        try {
            Cor = ini_corpus();
            Verbs vv = new Verbs();
            ini_irr(vv);
            ini_reg(vv);
            v = vv;
            d = ini_diccionari();

        }
        catch (FileNotFoundException e) {
            System.out.println("------------- Error E/S ! ----inicializacion-------");
        }

    }
    */
    public void Cargar (String c1, String c2, String c3, String c4)
            throws IOException, FileNotFoundException{
        Verbs vv = new Verbs();
        //System.out.println("nulo");
        System.out.println(this.directorio_irregulares_defecto);
        if(c1 == null)
            this.carga_irregulares(this.directorio_irregulares_defecto, vv);
        else this.carga_irregulares(c1, vv);
        if(c2 == null)
            this.carga_infinitivos(this.directorio_infinitivos_defecto, vv);
        else this.carga_infinitivos(c2, vv);
        v = vv;
        if(c3 == null) Cor = this.carga_corpus(this.directorio_corpus_defecto);
        else Cor = this.carga_corpus(c3);
        if(c4 == null)
            d = this.carga_diccionari(this.directorio_diccionario_defecto);
        else  d = this.carga_diccionari(c4);
    }

    /**
    //Metodo que se encarga de insertar los verbos irregulares dentro de la
    //clase verbo del archivo que viene de serie
    private void ini_irr (Verbs vv) throws FileNotFoundException {
        //String words[] = null;
        //if (separator.equals("\\"))  words = Path_proyecto.split(";");
        //else  words = Path_proyecto.split(":");
        //path_sistema = words[1];
        String r = Path_proyecto.concat("/src"+separator+
        "Archivos_Preinstalados"+separator+"VerbosIrregularesConjugados.txt");
        concat_con_path = true;
        carga_irregulares(r, vv);
    }

    //Metodo que se encarga de insertar los verbos regulares dentro de la
    //clase verbo del archivo que viene de serie
    private void ini_reg (Verbs vv) throws FileNotFoundException {
       // String words[] = new String[2];
       //if (separator.equals("\\"))  words = Path_proyecto.split(";");
       //else  words = Path_proyecto.split(":");
       //path_sistema = words[1];
        String r = Path_proyecto.concat("/src"+separator+
        "Archivos_Preinstalados"+separator+"VerbsWordNet.txt");
        concat_con_path = true;
        carga_infinitivos(r, vv);
    }

    //Metodo que se encarga de inicializar el corpus, cogiendo el archivo desde
    //el archivo que viene de serie
    private Corpus ini_corpus() throws FileNotFoundException {
        //String words[] = new String[2];
        //System.out.println(Path_proyecto);
        //if (separator.equals("\\"))  words = Path_proyecto.split(";");

        //else  words = Path_proyecto.split(":");
        //path_sistema = words[1];
        String r = Path_proyecto.concat("src"+separator+
        "Archivos_Preinstalados"+separator+"PairsNounVerb.txt");
        concat_con_path = true;
        Cor = new Corpus();
        return carga_corpus(r);
    }

    //Metodo que se encarga de inicializar el diccionario, cogiendo el archivo
    //que viene de serie
    private Diccionario ini_diccionari() throws FileNotFoundException {
        Diccionario dd = new Diccionario();
        //String words[] = new String[2];
        //if (separator.equals("\\"))  words = Path_proyecto.split(";");
        //else  words = Path_proyecto.split(":");
        //path_sistema = words[1];
        String r = Path_proyecto.concat("/src"+separator+
        "Archivos_Preinstalados"+separator+"NounsWordNet.txt");
        concat_con_path = true;
        return dd = carga_diccionari(r);
    }*/

 private boolean es_valid(ArrayList<Token> ab, Token a, Token b)
    {
        boolean baux = false;
        int t1 = a.get_pos() + a.get_name().length()-1;
        if(t1 - b.get_pos() != 1) baux = true;
        return baux;
    }


    public void localizar_verbos(ArrayList<String> v_conj, 
    ArrayList<Integer> pos_conj) throws IOException, InterruptedException {
        
        ArrayList<Token> tokens = t.get_all_tokens();
        v_text = new ArrayList<String>();
        //int next = 0;
        ArrayList<String> form_v = new ArrayList<String> ();
        for(Integer i = 0; i < tokens.size(); ++i){
            Integer i2, i3, i4;
            i2 = i + 1;
            i3 = i2 + 1;
            //System.out.println("i3 ------ " + i3);
            i4 = i3 + 1;
            //System.out.println("i4 ------ " + i4);
            //System.out.println("i " + i + "i2 " + i2 + "i3 " + i3 + "i4 " + i4);
            if(v.get_infinitivo(tokens.get(i).get_name()).equals("have") ||
                    v.get_infinitivo(tokens.get(i).get_name()).equals("will")) {
              //  System.out.println("---- token " +  v.get_infinitivo(tokens.get(i).get_name()));
                form_v.add(v.get_infinitivo(tokens.get(i).get_name()));
                //System.out.println("---- tam paso 0");
                if(i2 < tokens.size()) {
                    String taux2 = tokens.get(i2).get_name();
                  //   System.out.println("---- tam paso 1");
                    if(es_valid(tokens,tokens.get(i),tokens.get(i2))) {
                        form_v.add(taux2);
                        if(i3 < tokens.size()) {
                            String taux3 = tokens.get(i3).get_name();
                    //        System.out.println("---- tam paso 2 " + taux3 + " " + tokens.size() + " - " + es_valid(tokens,tokens.get(i2),tokens.get(i3)));
                            if(es_valid(tokens,tokens.get(i2),tokens.get(i3))) {
                                form_v.add(taux3);
                                if(i4 < tokens.size()) {
                                    String taux4 = tokens.get(i4).get_name();
                      //              System.out.println( "i3 i4 " + i3 + i4 +"---- tam paso 3 " + taux4 + " " + tokens.size() + " - " + es_valid(tokens,tokens.get(i3),tokens.get(i4)));
                                    if (es_valid(tokens,tokens.get(i3),
                                            tokens.get(i4))) form_v.add(taux4);
                                }
                            }
                        }
                    }
                }
                int j = 0;
                //System.out.println("---- RR " + form_v.get(j) + " is verb " + v.is_verb(form_v.get(j)));
                while(j < form_v.size() && v.is_verb(form_v.get(j))){
                    //System.out.println("---- RR " + form_v.get(j) + " is verb "+ v.is_verb(form_v.get(j)));
                    //System.out.println("vuelta ------- " + j + " tope " + form_v.size());
                    ++j;
                }
                //System.out.println("----- fi bucle " + i + " j " + j);
                i = i + j -1;
                v_text.add(v.get_infinitivo(form_v.get(j-1)));
                v_conj.add(tokens.get(j-1).get_name());
                pos_conj.add(tokens.get(j-1).get_pos());
             //       System.out.println(tokens.get(i).get_name() + " " + v_text.get(v_text.size() - 1));
            }
            else if(v.is_verb(tokens.get(i).get_name()))
            {
                v_text.add(v.get_infinitivo(tokens.get(i).get_name()));
                v_conj.add(tokens.get(i).get_name());
                pos_conj.add(tokens.get(i).get_pos());
           System.out.println(tokens.get(i).get_name() + " "
                   + v_text.get(v_text.size() - 1));
            }
        }
    }


   /* public ArrayList<Integer> pos_verbos() {
        return v_pos_conju;
    }*/
/*
    public void leer_texto() throws IOException, InterruptedException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            boolean b = true;
            String s;
            System.out.println();
            System.out.println("leer_texto:");
            while(b) {
                System.out.println("1 - Escribir texto");
                System.out.println("2 - Cargar texto");
                System.out.println("3 - Localizar los verbos del texto");
                System.out.println("4 - Obtener las nominalizaciones");
                System.out.println("5-  Atras");
                s = in.readLine();
                if(s.equals("1")) {
                    System.out.println("Introduce en una frase el texto");
                    s = in.readLine();
                    t = new Texto(s);
                }else if(s.equals("2")) {
                    System.out.println("Introduce el directorio");
                    s = in.readLine();
                    //t = carga_texto(s);
                }else if(s.equals("3")) {
                    localizar_verbos();
                }else if (s.equals("4")) {
                    for(Integer i = 0; i < v_text.size(); ++i) {
                        System.out.println(i + " " + v_text.get(i));
                    }
                    boolean f = true;
                    while(f){
                        System.out.println("Introduce en numero del verbo del que quieres nominalizar");
                        System.out.println("para salir introduce -1");
                        Integer c = Integer.valueOf(in.readLine());
                        if (c == -1) {
                            f = false;
                        } else {
                            ArrayList<String> nom = new ArrayList();
                            this.aplicar_reglas(nom, v_text.get(c));
                            this.busca_nom(nom);
                        }
                    }
                }else if (s.equals("5")) {
                    b = false;
                }
                System.out.println();
            }
        }catch (IOException e){
            System.out.println("------------- Error E/S ! ------leer_texto-----");
        }
  }
 */ 
    public void set_texto(String s) {
        this.t = new Texto(s);
    }

    public void carga_texto(String s) throws IOException,
            EOFException, InterruptedException {
        String aux = null;
        Texto taux = new Texto(" ");
        try {
            //String words[] = new String[2];
            //if (separator.equals("\\"))  words = Path_proyecto.split(";");
            //else  words = Path_proyecto.split(":");
            
            //path_sistema = words[0];
            //System.out.println(path_sistema);
            //if(!concat_con_path) s = path_sistema.concat(s);
            fitxerTexto = new FileInputStream(s);
           // System.out.println(s);
            InputStreamReader isr = new InputStreamReader(fitxerTexto);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
            //boolean premero = true;
            while (b) {
                //System.out.println(" en el while");
                String it = br.readLine();
                //System.out.println("-------------------------------==========-----" + it);
                if (it != null) {
                    if(aux == null) aux = it.concat(" \n");
                    else aux = aux.concat(it).concat(" \n");//
                } else {
                    fitxerTexto.close();
                    //concat_con_path = false;
                    b = false;
                    //System.out.println(" b a false");
                    if (aux != null) throw new EOFException();
                }
                //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + it);
            }
            t = new Texto(" ");
        }
        catch (EOFException r) {
            //System.out.println(" antes d salir");
            //System.out.println(aux);
            taux = new Texto(aux);
            //System.out.println(taux.get_all());
            t = new Texto(aux);
        }
        catch (IOException e) {
            System.out.println("--------- Error E/S ! -----carga_texto------");
        }
    }


public void carga_texto_inicial() throws IOException, EOFException,
        InterruptedException {
        String aux = null;
        String direc_inicio = Path_proyecto.concat(separator+
                "Archivos_Preinstalados"+separator+"Default_text.txt");
        Texto taux = new Texto(" ");
        try {
            fitxerTexto = new FileInputStream(direc_inicio);
            //System.out.println(direc_inicio);
            InputStreamReader isr = new InputStreamReader(fitxerTexto);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
            boolean premero = true;
            String it = br.readLine();
            aux = it;
            t = new Texto(aux);
            fitxerTexto.close();
            //System.out.println(" en el while");
                //System.out.println("-------------------------------==========-----" + it);                
                //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + it);        
        }
        catch (IOException e) {
            System.out.println("--------- Error E/S ! -----carga_texto------");
        }
    }


/*
    private void ejecutar_carga() throws IOException, InterruptedException {
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       String s;
       boolean b = true;
       System.out.println();
       System.out.println("ejecutar_carga:");
        do {
            System.out.println("1 - Cargar Diccionario");
            System.out.println("2 - Cargar Corpus");
            System.out.println("3 - Cargar Infinitivos");
            System.out.println("4 - Cargar Irregulares");
            System.out.println("5 - Atras");
            s = in.readLine();
            if(s.equals("1")) {
                System.out.println("Introducir en nombre del archivo");
                s = in.readLine();
                d = carga_diccionari(s);
            }else if(s.equals("2")) {
                System.out.println("Introducir en nombre del archivo");
                s = in.readLine();
                Cor = carga_corpus(s);
            }else if (s.equals("3")) {
                System.out.println("Introducir en nombre del archivo");
                s = in.readLine();
                carga_infinitivos(s, v);
            }else if (s.equals("4")) {
                System.out.println("Introducir en nombre del archivo:");
                s = in.readLine();
                carga_irregulares(s, v);
            }else if (s.equals("5")) {
                 b = false;
            }else{
                System.out.println("Entrada no válida");
            }
            System.out.println();
        } while (b);

    }
*/


    private Corpus carga_corpus(String s) throws FileNotFoundException {
        Corpus res = new Corpus();
        String aux = null;
        try {
            //if(!concat_con_path) s = path_sistema.concat(s);
            //System.out.println(s);
            fitxerCorpus = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerCorpus);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true; 
            while (b) {
                String it = br.readLine();
                if (it != null) {
                    aux = it;
                    String[] words = aux.split(" ");
                    String firstWord = words[0];
                    String secondWord = words[1];
                    res.insert_c(firstWord, secondWord);
                } else {
                    fitxerCorpus.close();
                    concat_con_path = false;
                    b = false;
                    //System.out.println("Corpus cargado OK");
                    throw new EOFException();
                }
            }
        }
        catch (EOFException r) {
            return res;
        }
        catch (IOException e) {
            this.err = 1;
            System.out.println(get_err());
            System.out.println("-------- Error E/S ! ------carga_corpus-----");
        }
        return null;
    }


    private void carga_irregulares (String s, Verbs vv)
            throws FileNotFoundException {
        try {
            //if(!concat_con_path) s = path_sistema.concat(s);
            //System.out.println(s);
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
                    vv.insert_in_verbo(Infinitivo, Infinitivo);
                    vv.insert_in_verbo(Pasado, Infinitivo);
                    vv.insert_in_verbo(Participio, Infinitivo);
                    vv.insert_in_verbo(rrr, Infinitivo);
                    vv.insert_in_verbo(ggg, Infinitivo);
                } else {
                    fitxerVerbs.close();
                    concat_con_path = false;
                    b = false;
                    //System.out.println("Irregulares cargado OK");
                }
            }
        } catch (IOException e) {
            System.out.println("------- Error E/S ! ----carga_irregulares---");
        }
    }


    private void carga_infinitivos (String s, Verbs vv)
            throws FileNotFoundException {
        String aux = null;
        try {
           // if(!concat_con_path) s = path_sistema.concat(s);
            //System.out.println(s);
            fitxerInfinitius = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerInfinitius);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
            while (b) {
                String it = br.readLine();
                if (it != null) {
                    aux = it;
                    vv.set_inf_reg(aux);
                } else {
                    fitxerInfinitius.close();
                    concat_con_path = false;
                    b = false;
                    //System.out.println("Infinitivos cargado OK");
                }
            }
        } catch (IOException e) {
            System.out.println("------- Error E/S ! ----carga_infinitivos----");
        }
    }


    private Diccionario carga_diccionari(String s)throws FileNotFoundException {
        Diccionario dd = new Diccionario();
        try {
            //if(!concat_con_path) s = path_sistema.concat(s);
            //System.out.println(s);
            fitxerDiccionari = new FileInputStream(s);
            InputStreamReader isr = new InputStreamReader(fitxerDiccionari);
            BufferedReader br = new BufferedReader(isr);
            boolean b = true;
                while (b) {
                    String regel = br.readLine();
                    if(regel != null) {
                        boolean r = dd.insert_element(regel);
                    }
                    else{
                        concat_con_path = false;
                        d = dd;
                        b = false;
                        //System.out.println("Diccionario cargado OK");
                        fitxerDiccionari.close();
                        return dd;
                    }
                }
            
        }
        catch (IOException e){
            System.out.println("----- Error E/S ! -----carga_diccionari-----");
        }
        return dd;
    }
  
    //Este metodo es el encargado de guardar todo el Corpus de la aplicación
    //en la ruta que pase el usuario por el string s
    public void save_corpus(String s){
        ArrayList<Ejemplo> ar = new ArrayList<Ejemplo> ();
        ar = Cor.get_all_c();
        escribe_fichero(ar, s);
    }
    
    /*//Este metodo es el encargado de guardar todo el conjunto de reglas de la
    //aplicación en la ruta que pase el usuario por el string s
    public void save_reglas(String s){
        ArrayList<String> rga = rg.get_allA();
        for(int i = 0; i < rga.size(); ++i) {
            ArrayList<String> rgb = rg.get_allB(rga.get(i));
            for(int j = 0; j < rgb.size(); ++j){
                System.out.print(rgb.get(i) + ",");
            }
            System.out.println();
        }
    }*/

    //Este es un metodo para guardar un Array de Ejemplos, ya sean las reglas
    //o el corpus, en un archivo. La ubicación y nombre del archivo le vienen
    //por el String direc
    private static void escribe_fichero(ArrayList<Ejemplo> ar, String direc){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter(direc);
            pw = new PrintWriter(fichero);
            for (int i = 0; i < ar.size(); i++){
                for (int j = 0; j < ar.get(i).get_c(); ++j){
                    String s = ar.get(i).get_a();
                    s = s.concat(" ");
                    s = s.concat(ar.get(i).get_b());
                    pw.println(s);
                }
            }
            fichero.close();
        } catch (IOException e){
            System.out.println("------ Error E/S ! -----escribe_fichero------");
        }
    }


    public static void save_texto(String texto, String direc){
        FileWriter fichero = null;
        PrintWriter pw = null;
        //System.out.println("en save_texto");
        try{
            ArrayList<Integer> enters = new ArrayList<Integer>();
            for(int i = 0; i < texto.length(); ++i){
                if(texto.charAt(i) == '\n') enters.add(i);
            }
            enters.add(texto.length());           
            fichero = new FileWriter(direc);
            pw = new PrintWriter(fichero);
            Integer offset = 0;
            for(int i = 0; i < enters.size(); ++i){
                pw.println(texto.substring(offset, enters.get(i)));
                offset =  enters.get(i) + 1;             
            }
            fichero.close();
        } catch (IOException e){
            System.out.println("----------- Error E/S ! ----save_texto------");
        }
    }


    public void aprender(){
         ArrayList<Ejemplo> ej = Cor.get_all_c();
         for(int x = 0; x < ej.size(); ++x) {
            Ejemplo o = ej.get(x);
            Integer[][] mat = this.Levensteindistance(o.get_b(),o.get_a());
            boolean[][] bmat = new boolean[mat.length][mat[0].length];
            for(int i = 0; i < bmat.length; ++i) {
                for(int j = 0; j < bmat[0].length; ++j) {
                    bmat[i][j] = false;
                }
            }
            this.cont = 0;
            this.generar_reglas(mat,bmat,o.get_b(),o.get_a(),
                    o.get_b().length(),o.get_a().length());
         }
    }

    private static String[] separaString(String pString, String pDelimitador){
        int i=-1;
        StringTokenizer stringAProcesar = new StringTokenizer(pString,
                pDelimitador);
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

    private Integer[][] Levensteindistance (String a, String b){
        Integer matrizlev[][] = new Integer[a.length() + 1][b.length() + 1];
        matrizlev[0][0] = 0;
        for(int i = 0; i < a.length() + 1; ++i) matrizlev[i][0] = i;
        
        // the distance of any first string to an empty second string
        for(int j = 0; j < b.length() + 1; ++j) {
            matrizlev[0][j] = j;
        }

   for(int i = 1; i < a.length() + 1; ++i) {
     for(int j = 1; j < b.length() + 1; ++j) {
         //System.out.println("punto a " + i + " " + j + " " + a.charAt(i - 1) + " " + b.charAt(j - 1));
       if(a.charAt(i - 1) == b.charAt(j - 1)) {
           //System.out.println(" iguales ");
         matrizlev[i][j] = matrizlev[i-1][j-1];       // no operation required
       } else {
          //System.out.println("--------------------");
          //System.out.println("----" + i + " " + j + " " + matrizlev[i][j-1] + " " + matrizlev[i-1][j-1]);
         Integer aux = Math.min(matrizlev[i][j-1] + 1,matrizlev[i-1][j-1] + 1);
        //System.out.println("----" + i + " " + j + " " + matrizlev[i-1][j]);
         matrizlev[i][j] = Math.min(matrizlev[i-1][j] + 1,aux);
         //System.out.println("punto b");
       }
   }
 }
        return matrizlev;
    }

    private void generar_reglas(Integer[][] matrizlev, 
        boolean[][] bmat, String a, String b, Integer i, Integer j) {

   // System.out.println("----" + a + " " + b + " " + i + " " + j);
        if(i > 0 && j > 0) {
            Integer min = Math.min(matrizlev[i][j-1],matrizlev[i-1][j-1]);
    //System.out.println("-------------> MIN " + matrizlev[i][j-1] + " " + matrizlev[i-1][j-1] + " " + min);
    //System.out.println("-------------> MIN " + matrizlev[i-1][j] + " " + min);
            min = Math.min(matrizlev[i-1][j],min);
    //System.out.println("-------------> MIN " + min);
            String rga;
            String rgb;
            String rga2 = new String();
            String rgb2 = new String();
     //System.out.println("!!!!----!!!!" + " " + i + " " + j + " " + matrizlev[i][j]);
            if(matrizlev[i][j] == 0) {
        //System.out.println("caso base");
                rga = a.substring(i-1,a.length());
                rgb = b.substring(j-1,b.length());
                rga2 = this.girar(rga);
                rgb2 = rgb;
                bmat[i][j] = true;
        //System.out.println("!!!!!!" + i + " " + j + " " + a.length() + " " + b.length());
        //if(i != a.length() || j != b.length()) {
           // System.out.println(i + " " + j + " " + "insertaAB " + rga2 + " " + rgb2);
                insertreg(rga2,rgb2);
                ++this.cont;
        //}
        //System.out.println("insertado");
            }else {
        //System.out.println("caso recursivo");
                if(min == matrizlev[i][j-1] && !bmat[i][j-1]) {
            //System.out.println("caso r 1 "+ matrizlev[i][j-1] + " " + i + " " + j);
                    generar_reglas(matrizlev,bmat,a,b,i,j-1);
                }
                min = Math.min(matrizlev[i-1][j-1],matrizlev[i-1][j]);
                if(min == matrizlev[i-1][j-1] && !bmat[i-1][j-1] &&
                        this.cont != this.alfa) {
            //System.out.println("caso r 2 "+ matrizlev[i-1][j-1] + " " + i + " " + j);
                    generar_reglas(matrizlev,bmat,a,b,i-1,j-1);
                }
                if(min == matrizlev[i-1][j] && !bmat[i-1][j] &&
                        this.cont != this.alfa) {
            //System.out.println("caso r 3 "+ matrizlev[i-1][j] + " " + i + " " + j);
                    generar_reglas(matrizlev,bmat,a,b,i-1,j);
                }

        //System.out.println("vuelve recursividad");
            }
    //System.out.println("fin");
        }
    }

    private void insertreg(String a, String b) {
    //System.out.println("!!!!! " + a);
        if(!rg.searchA(a)) {
            rg.insertA(a);
            rg.insertAB(a,b);
            rg.sumaA(a);
            rg.sumaAB(a,b);
        }else {
            if(!rg.searchAB(a,b)) {
                rg.insertAB(a,b);
                rg.sumaA(a);
                rg.sumaAB(a,b);
            }else {
                rg.sumaAB(a,b);
            }
        }
    }

    private String girar(String a) {
        String a2 = new String();
        for (int x = a.length()-1; x >= 0 ; --x){
                    a2 = a2 + a.charAt(x);
        //System.out.println("String -------> " + a2);
        }
        //System.out.println("String -------> " + a2);
        return a2;
    }

    public void busca_nom(ArrayList<String> nom) {
    //System.out.println("-----busca_nom------------------- FINAL " + nom.size());
        for(int i = 0; i < nom.size(); ++i) {
        //System.out.println("-------------- " + nom.size());
        //System.out.println("--------------- O " + nom.get(i));
            if(!this.d.exists(nom.get(i))) {
            //System.out.println("--------------- BORRA " + nom.get(i));
                nom.remove(nom.get(i));
                --i;
            }
        }
        //for(int i = 0; i < nom.size(); ++i) System.out.println(nom.get(i));
}

/*
public void aplicar_reglas(ArrayList<String> nom, Regla rg, String verb) {

    for(int j = 1; j < matriza[0].length; ++j) {
        if(current.ca == verb.charAt(j)) {
            matriza[i][j] = 1;
            for (Node<Template> x: current.child) {
                aplicar_reglas(nom,matriza,rg,verb,++i,x);
            }
        }else {
            rg.searchAB(abc, abc);
        }
}

}
*/

    public void aplicar_reglas(ArrayList<String> nom, String verb) {
        if(!verb.equals("be")) {
            ArrayList<String> rgb = new ArrayList<String> ();
            //System.out.println("$$$$$$" + rg.get_numTotal());
            ArrayList<Double> ap_rg = new ArrayList<Double> ();
            Integer con = 1;
            for(int i= verb.length() - 1; i > verb.length() - this.alfa-1; --i){
                if(i >= 0) {
                    String subv = verb.substring(i);
          //  System.out.println("Z_Z" + subv);
                    ArrayList<String> rgbaux = rg.get_allB(this.girar(subv));
                    for(int j = 0; j < rgbaux.size(); ++j) {
                        String tr = rgbaux.get(j);
                        if(!rgb.contains(tr)) {
        //            System.out.println("ASD " + tr);
                            rgb.add(tr);
                         ap_rg.add(rg.prob(subv,subv.concat(tr))/this.alfa-con);
                        }else {
                            int pos = rgb.indexOf(tr);
                            ap_rg.set(pos,ap_rg.get(pos) +
                                (rg.prob(subv,subv.concat(tr))/this.alfa-con));
                        }
                    }
                    ++con;
                }
      //  System.out.println("------------------- punto a " + rgb.size() + " " + ap_rg.size());

                for(int x = 1; x < rgb.size(); ++x) {
                    String beta = rgb.get(x);

                    char auxbeta = beta.charAt(0);
    //System.out.println("O_O" + rgb.get(x));
                    int haux = 0;
                    for (int h = verb.length()-1; h > 0; --h) {
                        if(verb.charAt(h) == auxbeta) {
                            haux = h;
                            h = -1;
                        }
                //System.out.println("A_A" + haux);
                    }
                    String vnew = verb.substring(0, haux);
            //System.out.println("A_A" + vnew);
            //System.out.println("------------------- n " + rgb.get(x) + "----- " + vnew.concat(rgb.get(x)));
            /*if(ap_rg.get(x) > pr)*/
                    if(!nom.contains(vnew.concat(rgb.get(x)))) {
                        nom.add(vnew.concat(rgb.get(x)));
                    }
                    ArrayList<Ejemplo> cauxc = this.Cor.get_all_B(verb);
                    for (int k = 0; k < cauxc.size(); ++k) {
                        boolean bauxb = false;
                        for (int y = 0; y < nom.size(); ++y) {
                            if(nom.get(y).equals(cauxc.get(k).get_b()))
                                bauxb = true;
                        }
                        if(!bauxb) nom.add(cauxc.get(k).get_b());
                    }
                }
            }
        } else {
            nom.add("being");
        }

    }


    public void eliminar_ejemplo (String s1, String s2) {
        if(Cor.search_c(s1, s2)) Cor.delete_AB(s1, s2);
    }

    public void añadir_ejemplo (String s1, String s2) {
        Cor.insert_c(s1, s2);
    }

    public ArrayList<Integer> apariciones(String s) {
        return t.exists_word(s);
    }



    public String texto(){
        //System.out.println("++" + t.get_all() + "++");
        return t.get_all();
    }


    

    public ArrayList<String> get_v_text() {
       return this.v_text;
    }

    public boolean is_verb(String s){
        return v.is_verb(s);
    }
    public String get_infinitivo(Integer c){


        return v.get_infinitivo(t.get_token(c).get_name());
    }
    public Texto get_texto(){
        return t;
    }
    
    public void set_N(int x) {
        this.alfa = x;
    }

    public void read_carga_por_defecto(String dtxt)
            throws FileNotFoundException, IOException{
        String aux = null;
        Texto taux = new Texto(" ");
        fitxerTexto = new FileInputStream(dtxt);
        //System.out.println(dtxt);
        InputStreamReader isr = new InputStreamReader(fitxerTexto);
        BufferedReader br = new BufferedReader(isr);
        String it = br.readLine();
       // System.out.println("-------------------------------==========-----" + it);
        this.directorio_irregulares_defecto = it;
        it = br.readLine();
        //System.out.println("-------------------------------==========-----" + it);
        this.directorio_infinitivos_defecto = it;
        it = br.readLine();
        //System.out.println("-------------------------------==========-----" + it);
        this.directorio_corpus_defecto = it;
        it = br.readLine();
        //System.out.println("-------------------------------==========-----" + it);
        this.directorio_diccionario_defecto = it;
        fitxerTexto.close();
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + it);
        //System.out.println(this.directorio_irregulares_defecto);
        //System.out.println(this.directorio_infinitivos_defecto);
        //System.out.println(this.directorio_corpus_defecto);
        //System.out.println(this.directorio_diccionario_defecto);
    }

    /*public void write_cargas_defecto(String direc) throws FileNotFoundException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        System.out.println("en write_cargas_defecto");
        try{
            fichero = new FileWriter(direc);
            pw = new PrintWriter(fichero);
             pw.println(this.directorio_irregulares_defecto);
             pw.println(this.directorio_infinitivos_defecto);
             pw.println(this.directorio_corpus_defecto);
             pw.println(this.directorio_diccionario_defecto);
             fichero.close();
        } catch (IOException e){
            System.out.println("------------- Error E/S ! ----write_cargas_defecto------");
        }
    }*/

}
