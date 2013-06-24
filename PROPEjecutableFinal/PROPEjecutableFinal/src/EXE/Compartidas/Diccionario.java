/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Compartidas;
//import Compartidas.Trie;
import java.util.ArrayList;

/**
 *
 * @author Jordi
 */
public class Diccionario {
    private Trie Dicc;
    private int long_max_system;
    private int long_max_user;
    private int num_elements;
    private char marca_user;
    private char marca_system;

    /**
     * Crea una nova instància de Diccionario
     */
    public Diccionario() {
        Dicc = new Trie();
        Dicc.add_marca("$€");
        marca_user = '$';
        marca_system = '€';
        long_max_system = 0;
        long_max_user = 0;
        num_elements = 0;
    }

    /**
     * Crea un copia del diccionari.
     * @return Retorna una copia del diccionari.
     */
    public Diccionario copy() {
        Diccionario copia = new Diccionario();
        copia.long_max_system = long_max_system;
        copia.long_max_user = long_max_user;
        copia.num_elements = num_elements;
        copia.marca_user = marca_user;
        copia.marca_system = marca_system;
        copia.Dicc = Dicc.copy();
        return copia;
    }

    /**
     * Retorna la longitud màxima de les paraules del diccionari.
     *@return Retorna la longitud màxima de les paraules del diccionari.
     */
    public int get_long_max() {
        return (long_max_user > long_max_system)?long_max_user:long_max_system;
    }

    /**
     * Retorna el nombre de paraules del diccionari.
     * @return Retorna el nombre de paraules del diccionari.
     */
    public int get_num_elements() {
        return num_elements;
    }

    /**
     * Insereix una paraula al diccionari.
     * @param p Paraula que s’inseria al diccionari.
     * @return Retorna cer si p s'ha inserit al diccionari. Fals altrament.
     */
    public boolean insert_element(String p) {
        if (Dicc.search(p)) return false;
        Dicc.insert(p, null, marca_system);
        ++num_elements;
        if (p.length() > long_max_system) long_max_system = p.length();
        return true;
    }

    /**
     * Insereix una paraula al diccionari amb una marca especial d'usuari.
     * @param p Paraula que s’inseria al diccionari.
     * @return Retorna cer si p s'ha inserit al diccionari. Fals altrament.
     */
    public boolean insert_element_user(String p) {
        if (Dicc.search(p)) return false;
        Dicc.insert(p, null, marca_user);
        ++num_elements;
        if (p.length() > long_max_user) long_max_user = p.length();
        return true;
    }

    /**
     * Elimina p del diccionari.
     * @param p Paraula que es vol eliminar del diccionari.
     * @return Retorna cert si s'ha aconseguit esborrar la parauala del
     * diccionari. Fals altrament.
     */
    public boolean delete_element(String p) {
        if (Dicc.search(p) && Dicc.get_marca(p) == marca_user) {
            if (!Dicc.delete(p)) return false;
            --num_elements;
            if (p.length() == long_max_user) { 
                int long_aux = 0;
                ArrayList<String> d = Dicc.get_all(marca_user);
                for (int i = 0; i < d.size(); ++i) {
                    int aux = d.get(i).length();
                    if (aux > long_aux) long_aux = aux;
                    if (aux == long_max_user) break;
                }
                long_max_user = long_aux;
            }
            return true;
        }
        return false;
    }

    /**
     * Substitueix una paraula al diccionari, per un altre.
     * @param p_old Paraula que volem canviar
     * @param p_new Paraula per la qual volem substituir
     * @return Cert si aconseguir fer la substitució. Fals altrament.
     */
    public boolean set_element(String p_old, String p_new) {
        if (Dicc.search(p_old) && Dicc.get_marca(p_old) == marca_user) {
            if (!Dicc.delete(p_old)) return false;
            Dicc.insert(p_new, null, marca_user);
            return true;
        }
        return false;
    }

    /**
     * Comprova si existeix p al diccionari.
     * @param p Paraula que volem comprovar si es al diccionari.
     * @return Cert si la paraula es al diccionari. Fals altrament.
     */
    public boolean exists(String p) {
        return Dicc.search(p);
    }

    /**
     * Retrona totes es paraules del diccionari en forma de ArrayList.
     * @return Retorna totes les paraules del diccionari.
     */
    public ArrayList<String> get_all() {
        return Dicc.get_all();
    }

    /**
     * Retrona totes es paraules del diccionari no afegides per l’usuari
     * en forma de ArrayList.
     * @return Retorna totes les paraules del diccionari que no han estat
     * afegides per l'usuari.
     */
    public ArrayList<String> get_all_no_user() {
        return Dicc.get_all(marca_system);
    }

    /**
     * Retrona totes es paraules del diccionari afegides per l’usuari
     * en forma de ArrayList.
     * @return Retorna totes les paraules del diccionari que  han estat
     * afegides per l'usuari.
     */
    public ArrayList<String> get_all_user() {
        return Dicc.get_all(marca_user);
    }
}