/**
 * Nom: Verbs
 * @author jose.manuel.ramos
 * Descripció:Esta clase en la encargada de almacenar todas verbales, tanto
 * infinitivas como conjugadas que la aplicaciön sera capaz de detectar. Su
 * ünico atibuto es verbo, que no es otra cosa que un Trie<String>. En esta
 * estructura tendremos las fromas conjugadas, ya sean los verbos regulares o
 * irregulares, y cada una de ellas tendra el infinitivo del que procede
 */

package EXE.Dominio;


import EXE.Compartidas.*;

public class Verbs {


    public Trie<String> verbo;

    /***********************************************************************
    * Creadora de Verbs
    ***********************************************************************/

    //Creadora de la clase que crea un Trie de String vacio
    public Verbs() {
        verbo = new Trie<String>();
    }


    /***********************************************************************
    * Get de les Adreces
    ***********************************************************************/

    //Te devuelve el infinitivo dada una forma verbal existente en el trie de
    //verbo; Sino devuelve una marca: "#@€"
    public String get_infinitivo(String word){
        if(verbo.search(word))return verbo.get_content(word);
        return "#@€";
    }


    /***********************************************************************
    * Otras funciones/metodos de Palabra
    ***********************************************************************/

    //Te dice si una forma verbal esta, retornara cierto,  o no esta, retornara
    //falso, en el trie verbo
    public boolean is_verb(String word){
        if(verbo.search(word))return true;
        return false;
    }

    //Función auxiliar para determinar si un caracter es una vocal o no
    private boolean is_vocal(Character c){
       if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                                                                return true;
       return false;
    }

    //Este metodo es el encargado de, dado un verbo, regular calcular sus
    //posibles formas conjugadas y guardar esta información en el trie
    //verbo de forma correcta
    public void set_inf_reg (String s){
       String a;
       String b;
       String c;
       Integer m = s.length();
       if(s.endsWith("o")) a = s.concat("es");
       else a = s.concat("s");
       if(s.endsWith("e")) b = s.concat("d");
       else if(s.endsWith("y") && !is_vocal(s.charAt(m-2))){
           b = s.subSequence(0, m-1).toString().concat("i");
           b = b.concat("ed");
       } else  b = s.concat("ed");
       c = s.concat("ing");
       insert_in_verbo (a,s);
       insert_in_verbo (b,s);
       insert_in_verbo (c,s);
       insert_in_verbo (s,s);
   }

    //Este metodo se encarga de insertar en el trie verbo una forma conjugada
    //y el infinitivo del que procede. Se inserta de forma que el trie
    //identifique la forma conjugada, y en el nodo que marca el final de
    //palabra tiene asociado el infinitivo
    public void insert_in_verbo (String conj, String inf){
        if(!verbo.search(conj)) verbo.insert(conj, inf);
        //return this;
    }
}
