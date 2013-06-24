/**
 * Nom: Palabra
 * @author
 * Descripció: Ejemplo es una estructura simple de datos que nos permite
 * almacenar una pareja de Strings y un Integer asociado a ella.
 */

package P_Palabra;

import Compartidas.*;

public class Palabra {

    protected String pal;
    
    /***********************************************************************
    * Creadoras de Palabra
    ***********************************************************************/

    //Son las creadoras, un la creadora vacia y otra inicializando el String
    //pal con el valor de la entrada
    public Palabra () {
    }
    public Palabra (String s) {
        pal = s;
    }


    /***********************************************************************
    * Get de Palabra
    ***********************************************************************/

    //Retorna el String pal
    public String get_palabra () {
        return this.pal;
    }


    /***********************************************************************
    * Set de Palabra
    ***********************************************************************/

    //Modifica pal
    public void set_palabra (String t) {
        this.pal = t;
    }


    /***********************************************************************
    * Otras funciones/metodos de Palabra
    ***********************************************************************/

    //Hace una copia de pal retornandola en p2
    public void copy_palabra(Palabra p2) {
        this.set_palabra(p2.get_palabra());
    }

    //Nos dice si la palabra p2 es igual a esta. Retornamos cierto si son
    //iguales; en caso contrario retornamos falso
    public boolean compare_palabra(Palabra p2) {
        if (this.pal.equalsIgnoreCase(p2.pal)) return true;
        return false;
    }
}
