/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Compartidas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author David
 */

class Contenido{
    int n;
    Trie<Integer> b;

    Contenido (){
        n = 0;
        b = new Trie(0);
    }


}

public class Regla {
    private int maxFrag;    //tamaño máximo de las particiones de un string.
    private double propCorrectos; //porcentaje de correctos
    private double fiabilidad;
    private Trie<Contenido> ttrie;


    public Regla(){
        maxFrag = 0;
        propCorrectos = 0;
        ttrie = new Trie(new Contenido());
    }

    public Regla(int N, double C){
        maxFrag = N;
        propCorrectos = C;
        ttrie = new Trie(new Contenido());
    }

    public void print(){
        ArrayList<String> a = ttrie.get_all();
        Collections.sort(a);
        for (String i: a){
            System.out.println(i + " -- " + ttrie.get_content(i).n);
            ArrayList<String> b = ttrie.get_content(i).b.get_all();
            Collections.sort(b);
            for(String j: b) System.out.println("   " + j + " -- " + ttrie.get_content(i).b.get_content(j));
        }
    }

    public int get_numTotal(){
        int res = 0;
        res = ttrie.size();
        ArrayList<String> vs = ttrie.get_all();
        //res += ttrie.get_content(vs.get(0)).b.size();
        for (String i: vs) res += ttrie.get_content(i).b.size();
        return res;
    }

    public int get_maxFrag(){return maxFrag;}
    public double get_propCorrectos() {return propCorrectos;}
    public double get_fiabilidad() {return fiabilidad;}

    public void set_maxFrag(int a) {maxFrag = a;}
    public void set_propCorrectos(double a){propCorrectos = a;}
    public void set_fiabilidad(double a) {fiabilidad = a;}

    public void add_marca(char m) {ttrie.add_marca(m);}

    public char get_marcaA(String a){return ttrie.get_marca(a);}
    public char get_marcaAB(String a, String b){
        Contenido aux = ttrie.get_content(a);
        if (aux != null) return aux.b.get_marca(b);
        return 0;
    }

    //{Pre: r es una regla inicializada}
    //{Post: r2 es una copia de r}
    public Regla copy(){
        Regla res = new Regla();
        res.maxFrag = this.maxFrag;
        res.propCorrectos = this.propCorrectos;
        ArrayList<String> vs = this.ttrie.get_all();
        for (String i: vs){
            Contenido aux = new Contenido();
            aux.b = this.ttrie.get_content(i).b.copy();
            aux.n = this.ttrie.get_content(i).n;
            res.ttrie.insert(i, aux);
        }
        return res;
    }

    public Node<Contenido> get_root(){
        return ttrie.get_root();
    }

    //{Pre: r es una regla inicializada}
    //{Post: el string s ha sido insertado en la Regla}
    public void insertA(String s){
        Contenido aux = new Contenido();
        ttrie.insert(s, aux);
    }
    public void insertA(String s, char m) {
        Contenido aux = new Contenido();
        ttrie.insert(s, aux, m);
    }

    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: inserta el string b a partir de a, estando este ultimo ya en
    // la Regla. Si ha sido posible, devuelve true, de no ser así,
    // retorna un boolean con el valor de false}
    public boolean insertAB(String a, String b){
        Contenido aux = ttrie.get_content(a);
        if (aux != null){
            aux.b.insert(b, 0);
            return true;
        }
        return false;
    }
    
    public boolean insertAB(String a, String b, char m){
        Contenido aux = ttrie.get_content(a);
        String ma = "";
        ma += m;
        if (aux != null) {
            if (!aux.b.get_marcas().contains(ma)) aux.b.add_marca(m);
            aux.b.insert(b, 0, m);
            return true;
        }
        return false;
    }

    //Retorna la probabilidad de P(a -> b)
    // -1 si error
    public double prob(String a, String b){
        if (searchAB(a, b)){
            double na = (double)ttrie.get_content(a).n;
            double nb = (double)ttrie.get_content(a).b.get_content(b);
            return nb/(na/(1 - propCorrectos));
        }
        return -1;
    }

    //{Pre: r es una regla inicializada}
    //{Post: busca el string s en la Regla r. En caso de encontrarse, devuelve
    // true. De no ser asi, retorna false}
    public boolean searchA(String a){return ttrie.search(a);}

    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: busca si intentando escribir b se ha escrito a, de ser asi,
    // devuelve true}
    public boolean searchAB(String a, String b){
        Contenido aux = ttrie.get_content(a);
        //System.out.println("Entro en searchAB (" + a + ", " + b + ") a found?: " + (aux != null));
        if (aux != null){
            //System.out.println("     b found?: " + (aux.b.search(b)));
            return aux.b.search(b);
        }
        return false;
    }

    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: Elimina el string A de la Regla y todos los strings B asociados.
    // Retorna true si se ha podido eliminar, false si no}
    public boolean deleteA(String a){
        ArrayList<String> aux = get_allB(a);
        for (String i: aux) deleteAB(a, i);
        return ttrie.delete(a);
    }

    //{Pre: r es una regla inicializada y el string a y el b existen en la Regla.}
    //{Post: Elimina el string b asociado al string a. Retorna true si se logra
    //  eliminar, false si no}
    public boolean deleteAB(String a, String b){
        if (ttrie.search(a)){
            Contenido aux = ttrie.get_content(a);
            return aux.b.delete(b);
        }
        return false;
    }

    //{Pre: r es una regla inicializada}
    //{Post: Devuelve el número de veces que el String a ha sido error. -1
    //  en caso de que a no pertenezca a la Regla r}
    public int get_sumaA(String a){
        Contenido aux = ttrie.get_content(a);
        if (aux != null) return aux.n;
        return -1;
    }

    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: sumaAB es el número de veces que el String b ha sido escrito
    // erróneamente por el String a, esto es:  #(a -> b),  siendo -1 en el caso
    // de no encontrarse alguno de los dos string en la Regla}
    public int get_sumaAB(String a, String b){
        Contenido aux = ttrie.get_content(a);
        if (aux != null){
            int c = aux.b.get_content(b);
            return c;
        }
        return -1;
    }

    //{Pre: r es una regla inicializada}
    //{Post: devuelve todos los strings }
    public ArrayList<String> get_allA(){return ttrie.get_all();}
    public ArrayList<String> get_allA(char m) {return ttrie.get_all(m);}
    
    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: devuelve todos los strings que estan asociados a el string a, en
    // caso de error, allB adquiere el valor null}
    public ArrayList<String> get_allB(String a){
        Contenido aux = ttrie.get_content(a);
        ArrayList<String> res = new ArrayList<String>();
        if (aux != null) res = aux.b.get_all();
        return res;
    }
    public ArrayList<String> get_allB(String a, char m){
        Contenido aux = ttrie.get_content(a);
        ArrayList<String> res = new ArrayList<String>();
        if (aux != null) res = aux.b.get_all(m);
        return res;
    }

    //{Pre: r es una regla inicializada y el string a existe en la Regla.}
    //{Post: Suma n al contador del String a. Retorna el estado del contador
    // tras la suma, siendo -1 en caso de error}
    public int sumaA (String a, int n){
        Contenido aux = ttrie.get_content(a);
        if (aux != null){
            aux.n = aux.n + n;
            return aux.n;
        }
        return -1;
    }
    public int sumaA (String a) {return sumaA(a, 1);}
    public int restaA (String a, int n) {return sumaA(a, -n);}
    public int restaA (String a) {return restaA(a, 1);}

    //{Pre: r es una regla inicializada y el string a y b existen en la Regla.}
    //{Post: Suma n al contador del String b pasando por el string a. Retorna el
    // estado del contador tras la suma, siendo -1 en caso de error}
    public int sumaAB(String a, String b, int n){
        Contenido aux = ttrie.get_content(a);
        if (aux != null){
            int m = aux.b.get_content(b);
            m = m + n;
            aux.b.set_content(b, m);
            return m;
        }
        return -1;
    }

    public int sumaAB(String a, String b) {return sumaAB(a, b, 1);}
    public int restaAB(String a, String b, int n) {return sumaAB(a, b, -n);}
    public int restaAB(String a, String b) {return restaAB(a, b, 1);}
}
