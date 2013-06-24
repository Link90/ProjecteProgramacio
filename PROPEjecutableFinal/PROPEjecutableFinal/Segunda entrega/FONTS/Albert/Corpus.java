/**
 * Nom: Corpus
 * @author albert
 * Descripció: Consiste en una clase que nos almacena en nodos enlazados de
 * parejas de string y la frecuencia con la que aparecen. Cor tiene almacenados
 * los ejemplos , infinitivo y un de sus nominalizaciones, y la frecuencia con
 * la que aparecen en el archivo corpus que recibe la aplicación. Num_total es
 * una variable que se encarga de de decir en numero de ejemplos diferentes,
 * no repetidos, que hay en el corpus.
 */

package Compartidas;

import java.util.*;

public class Corpus {

    protected Trie<Trie<Integer> > cor;
    protected Integer num_total;

    /***********************************************************************
    * Creadora de Corpus
    ***********************************************************************/

    //Es la creadora de la clase. No recibe parametros porque los Ejemplos se
    //introduciran de uno en uno. Como es lógico se inicializara la variable
    //num_total a 0
    public Corpus () {
        cor = new Trie(new Trie());
        num_total = 0;
    }
    
     /***********************************************************************
    * Get de Corpus
    ***********************************************************************/
    //Obtiens la frecuencia, número de veces que se repite en el corpus el
    //Ejemplo formado por el infinitivo a y su nominalización b
    public Integer get_fre_c (String a, String b) {
        Trie<Integer> aux = new Trie();
        if(!this.search_c(a,b)) return 0;
        else {
            aux = this.cor.get_content(a);
            return aux.get_content(b);
        }
    }

    //Obtienes el numero de Ejemplos distintos que tiene el Corpus
    public Integer get_num_total_c(){ return this.num_total;}

    //Obtienes un ArrayList de Ejemplos con todas las nominalizaciones de un
    //infinitivo a que esta en el corpus. En cada Ejemplo también aparece las
    //frecuencia con la que ha aparecido en el corpus. Como preconcición se ha
    //de cumplir que a esté en el corpus
    public ArrayList<Ejemplo> get_all_B (String a) {
        Trie<Integer> aux = new Trie();
        ArrayList<String> ar2 = new ArrayList();
        ArrayList<Ejemplo> res = new ArrayList();
        if (this.cor.search(a)){
            aux = this.cor.get_content(a);
            ar2 = aux.get_all();
            for(Integer j = 0; j < ar2.size(); ++j) {
                Ejemplo paux = new Ejemplo(a, ar2.get(j),
                                                aux.get_content(ar2.get(j)));
                res.add(paux);
            }
            ar2.clear();
        }
        return res;
    }

    //Esta función te devuelve un ArrayListe de Ejemplo con todos los ejemplos
    //que aparecene en el texto. En cada Ejemplo aparece el infinitivo, una
    //nominalizacion de este y la frecuencia con la que ha aparecido el ejemplo
    //en en corpus
    public ArrayList<Ejemplo> get_all_c () {
        Trie<Integer> aux = new Trie();
        ArrayList<String> ar = new ArrayList();
        ArrayList<String> ar2 = new ArrayList();
        ArrayList<Ejemplo> res = new ArrayList();
        String saux;
        ar = this.cor.get_all();
        for(Integer i = 0; i < ar.size(); ++i) {
            saux = ar.get(i);
            aux = this.cor.get_content(saux);
            ar2 = aux.get_all();
            for(Integer j = 0; j < ar2.size(); ++j) {
                Ejemplo paux = new Ejemplo(saux,ar2.get(j),
                                                aux.get_content(ar2.get(j)));
                res.add(paux);
            }
        }
        ar.clear();
        ar2.clear();
        return res;
    }

    //Esta función sirve para poder partir todo el corpus en dos ArrysListes de
    //Ejemplos teniendo en N%, con la N entre 0 y 100, de los Ejemplos en r1 y
    //el resto en r2. Los Ejemplos que hay en r1 son escogidos al azar
    public void get_all_c(ArrayList<Ejemplo> r1,
                                      ArrayList<Ejemplo> r2, Integer n) {
        ArrayList<Ejemplo> res = new ArrayList();
        res = this.get_all_c();
        Integer top;
        Integer sizeres = res.size();
        Integer raux;
        if (n < 50) {
            top = (sizeres*n)/100;
            for(int i = 0; i < top; ++i) {
                raux = 0 + (int)(Math.random() * (sizeres - 0));
                r1.add(res.get(raux));
                boolean e;
                e = res.remove(res.get(raux));
                --sizeres;
            }
            --sizeres;
            for(int j = sizeres; j > 0; --j) {
                r2.add(res.get(j));
                boolean e;
                e = res.remove(res.get(j));
            }
        } else {
            top = (sizeres*(100 - n))/100;
            for(int i = 0; i < top; ++i) {
                raux = 0 + (int)(Math.random() * (sizeres - 0));
                r2.add(res.get(raux));
                boolean e;
                e = res.remove(res.get(raux));
                --sizeres;
            }
            --sizeres;
            for(int j = sizeres; j > 0; --j) {
                r1.add(res.get(j));
                boolean e;
                e = res.remove(res.get(j));
            }
        }
    }

    
    /***********************************************************************
    * Set de les Adreces
    ***********************************************************************/

    //Inserta el Ejemplo a y b en el corpus. Hace todas las comprobaciones
    //oportunas para que esto se cumpla. La frecuencia de este Ejemplo ha sido
    //aumentada en 1
    public void insert_c (String a, String b) {
        Trie<Integer> aux = new Trie();
        if(!this.cor.search(a)) {
            aux.insert(b,1);
            this.cor.insert(a,aux);
            ++this.num_total;
        } else {
            aux = this.cor.get_content(a);
            if(!aux.search(b)) {
                aux.insert(b,1);
                ++this.num_total;
            }
            else {
                int fr = aux.get_content(b);
                ++fr;
            }
        }
    }

    //Inserta el Ejemplo a y b en el corpus. Hace todas las comprobaciones
    //oportunas para que esto se cumpla. La frecuencia de este Ejemplo ha sido
    //aumentada en f
    public void insert_c (String a, String b, Integer f) {
        Trie<Integer> aux = new Trie();
        if(!this.cor.search(a)) {
            aux.insert(b,f);
            this.cor.insert(a,aux);
            ++this.num_total;
        } else {
            aux = this.cor.get_content(a);
            if(!aux.search(b)) {
                aux.insert(b,f);
                ++this.num_total;
            } else {
                Integer fr = aux.get_content(b);
                fr += f;
                aux.set_content(b,fr);
            }
        }
    }

    
    /***********************************************************************
    * Otras funciones/metodos de Palabra
    ***********************************************************************/

    //Retorna cierto si el Ejemplo formado por a y b forma parte del corpus;
    //en caso contrario retorna falso
    public boolean search_c (String a, String b) {
        Trie<Integer> aux = new Trie();
        if(!this.cor.search(a)) return false;
        else {
            aux = this.cor.get_content(a);
            if(!aux.search(b)) return false;
            else return true;
        }
    }
    
    //Srirve para borrar el Ejemplo formado por a y b del corpus. A y b han de
    //formar parte del corpus. Se elinimina la nominalizacion b del infinitivo
    //a dentro del corpus. Si el infinitivo a no tiene más nominalizaciones
    //también se elimina a del corpus
    public void delete_AB (String a, String b) {
        Trie<Integer> aux = new Trie();
        aux = this.cor.get_content(a);
        if(aux.size() == 0) {
            boolean baux = this.cor.delete(a);
        }else {
            boolean baux = aux.delete(b);
        }
        --this.num_total;
    }
}
