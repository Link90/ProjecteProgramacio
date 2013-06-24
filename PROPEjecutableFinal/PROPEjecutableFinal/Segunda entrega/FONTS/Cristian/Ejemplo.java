/**
 * Nom: Ejemplo
 * @author cristian
 * Descripció: Inicialmente creada para contener parejas de infinitivo,
 * nominalización y frecvuencia de repeticion, pero tambien puede ser usada
 * como una simple contenedora de objetos de la clase String, String o String,
 * String e Integer.
 */


package Compartidas;

public class Ejemplo {

    private String a;
    private String b;
    private Integer c;

    /***********************************************************************
    * Creadoras de Ejemplo
    ***********************************************************************/

    //Son las creadoras, un la creadora vacia; otra inicializando los String
    //a y b con el valor de la entrada; y la última inicializando todos los
    //campos con los valores de la entrada
    public Ejemplo () {
    }
    public Ejemplo (String a, String b) {
        this.a = a;
        this.b = b;
        this.c = null;
    }
    public Ejemplo (String a, String b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /***********************************************************************
     * Get de Ejemplo
     ***********************************************************************/

    //Son las consultoras para obtener cada uno de los atributos de la clase
    public String get_a () {return this.a;}
    public String get_b () {return this.b;}
    public Integer get_c () {return this.c;}


    /***********************************************************************
     * Set de Ejemplo
     ***********************************************************************/

    //Son las modificadoras para cambiar cada uno de los atributos de la clase
    public void set_a (String a) {this.a = a;}
    public void set_b (String b) {this.b = b;}
    public void set_c (Integer c) {this.c = c;}
}
