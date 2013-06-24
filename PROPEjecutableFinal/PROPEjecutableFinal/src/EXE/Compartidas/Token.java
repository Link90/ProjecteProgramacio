/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Compartidas;

/**
 *
 * @author Pablo
 */
public class Token {
    private String name;
    private int pos;
    private boolean marcat;

    /**
     *  Crea una instancia de Token, buit.
     */
    public Token() {
        name = "";
        pos = 0;
        marcat = false;
    }

    /**
     * Crean una nova instancia de Token amb els parametres name i pos.
     * @param name Nom que la paraula a la que representa el token
     * @param pos Posicio en el que esta situat el token, al text, per caracters
     */
    public Token(String name, int pos) {
        this.name = name;
        this.pos = pos;
        marcat = false;
    }

    /**
     * Crea una copia del token.
     * @return La copia del token.
     */
    public Token copy() {
        Token t2 = new Token(name, pos);
        return t2;
    }

    /**
     * Compara si dos tokens son iguals.
     * @param t2 Token amb el que volem comparar el token pasat
     * per parametre implicit.
     * @return Retorna cert si el token pasar per parametre implicit es
     * igual a t2. Retorna fals altrament.
     */
    public boolean compare(Token t2) {
        if (name.equals(t2.name) && pos == t2.pos) return true;
        return false;
    }

    /**
     * Consultadora del name de token.
     * @return Retorna el name del token.
     */
    public String get_name() {
        return name;
    }

    /**
     * Consultadora de la pos del token passat per parametre implicit.
     * @return Retorna la posicio del token.
     */
    public int get_pos() {
        return pos;
    }

    /**
     * Modifica del name de token  per name.
     * @param name Nou nom que volen que tingui el token.
     */
    public void set_name(String name) {
        this.name = name;
    }

    /**
     * Modifica la pos del token.
     * @param pos Nova posicio que volem que tingui el token pasat.
     */
    public void set_pos(int pos) {
        this.pos = pos;
    }

    /**
     * Modifica marcat a m;
     * @param m Valor que prendrà marcat;
     */
    public void set_marcat(boolean m) {
        marcat = m;
    }

    /**
     * Retorna el valor de marcat.
     * @return Retorna cert si el Token està marcat. Fals altrament.
     */
    public boolean get_marcat() {
        return marcat;
    }


}
