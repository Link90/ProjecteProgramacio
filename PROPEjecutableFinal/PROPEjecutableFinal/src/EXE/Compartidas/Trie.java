/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Compartidas;

import java.util.ArrayList;

/**
 *
 * @author Grup 1
 */

public class Trie<T> {
    private Node<T> root;
    private String marcas;
    private int tam;

    public Trie (T info) {
        root = new Node(' ', info);
        tam = 0;
        marcas = "#";
    }

    public Trie(){
        T aux = (T) new Object();
        root = new Node(' ', aux);
        tam = 0;
        marcas = "#";
    }

    public Node<T> get_root(){
        return root;
    }

    //Retorna el número de Strings que contiene el Trie
    public int size(){return tam;}

    //Retorna una copia del Trie llamador.
    public Trie<T> copy(){
        Trie<T> res = new Trie();
        res.marcas = this.marcas;
        res.tam = this.tam;
        res.root = this.root.copy();
        return res;
    }

    //Retorna las marcas de final válidas
    public String get_marcas(){
        return marcas;
    }

    //Retorna si dada una marca de final, esta es válida o no.
    public boolean marca_valida(char c){
        return marcas.contains("" + c);
    }

    private boolean es_final(Node<T> n){
        return marca_valida(n.ca);
    }

    //Añade la marca de final c al conjunto de marcas
    public void add_marca(char c){
        if (!marca_valida(c)) marcas = marcas + c;
    }
    public void add_marca(String c){
        int n = c.length();
        for (int i = 0; i < n; ++i) add_marca(c.charAt(i));
    }

    //Inserta el String s con marca de final char c.
    //{Pre: s no existe en el Trie}
    public void insert(String s, T info) {
        insert(s, info, '#');
    }

    //Inserta el String s con marca de final char c.
    //{Pre: s no existe en el Trie}
    public void insert(String s, T info, char c) {
        if (marca_valida(c)) {
            Node<T> current = root;
            //if(s.length() == 0) current.child.add(new Node(c, info));
            char[] cad = s.toCharArray();
            int n = s.length();
            for(int i = 0; i < n; i++) {
                Node<T> child = current.subNode(cad[i]);
                if (child == null){
                    child = new Node(cad[i], null);
                    current.child.add(child);
                    current = child;
                }
                current = child;
                if(i == s.length()-1) {
                    if (current.subNode(c) == null) {
                        current.child.add(0, new Node(c, info));
                        ++tam;
                    }
                }
            }
        }
    }

    //Retorna si el String s se encuentra en el Trie o no
    public boolean search(String s) {
        Node<T> aux = get_node(s);
        return aux != null;
    }


    //Retorna el nodo con la marca de final del String s
    // null si error.
    private Node<T> get_node(String s){
        Node<T> current = root;
        while(current != null && s.length() > 0) {
            for(int i = 0; i < s.length(); i++) {
                Node<T> aux = current.subNode(s.charAt(i));
                if(aux == null) return null;
                else current = aux;
            }
            int n = marcas.length();
            for (int i = 0; i < n; ++i){
                Node<T> aux = current.subNode(marcas.charAt(i));
                if (aux != null && marca_valida(aux.ca)) return aux;
            }
        }
        return null;
    }

    //Retorna la marca de final del String s
    // 0 si error.
    public char get_marca(String s){
        Node<T> aux = get_node(s);
        if (aux != null) return aux.ca;
        return 0;
    }


    //Retorna el contenido del nodo final del String s
    // null si error
    public T get_content(String s){
        Node<T> aux = get_node(s);
        if (aux != null) return (T)aux.content;
        return null;
    }

    public void set_content(String s, T cont){
        Node<T> aux = get_node(s);
        if (aux != null) aux.content = cont;
    }

    //Elimina el String s del Trie
    //Retorna si se ha podido eliminar o no.
    public boolean delete(String s){
        Node<T> aux = get_node(s);
        if (aux != null){
            --tam;
            aux.ca = 0;
            aux.child = new ArrayList<Node<T>>(0);
            aux.content = (T) new Object();
            return true;
        }
        return false;
    }

    private void get_all(Node<T> a, String s, ArrayList<String> res){
        if (es_final(a)) res.add(s);
        String nextS = s + Character.toString(a.ca);
        if (a.child != null){
            for (Node<T> i: a.child){
                get_all(i, nextS, res);
            }
        }
    }

    public ArrayList<String> get_all(){
        Node<T> current = root;
        ArrayList<String> res = new ArrayList<String>();
        if (current.child != null){
            for (Node<T> i: current.child){
                String s = new String();
                get_all(i, s, res);
            }
        }
        return res;
    }

        private void get_all(Node<T> a, String s, ArrayList<String> res, char m){
        if (a.ca == m) res.add(s);
        String nextS = s + Character.toString(a.ca);
        if (a.child != null){
            for (Node<T> i: a.child){
                get_all(i, nextS, res, m);
            }
        }
    }

    public ArrayList<String> get_all(char m){
        Node<T> current = root;
        ArrayList<String> res = new ArrayList<String>(0);
        if (current.child != null && marca_valida(m)){
            for (Node<T> i: current.child){
                String s = new String();
                get_all(i, s, res, m);
            }
        }
        return res;
    }
}