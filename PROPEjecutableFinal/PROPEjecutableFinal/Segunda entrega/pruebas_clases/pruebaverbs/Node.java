/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaverbs;

import java.util.ArrayList;

public class Node<Template> {
    public char ca;
    public Template content;
    public ArrayList<Node<Template>> child;


    public Node (){
        child = new ArrayList<Node<Template>>(0);
        content = (Template) new Object();
        ca = 0;
    }

    public Node (char c){
        child = new ArrayList<Node<Template>>(0);
        content = (Template) new Object();
        ca = c;
    }

    public Node (char c, Template info) {
        child = new ArrayList<Node<Template>>(0);
        content = info;
        ca = c;
    }

    public Node<Template> subNode(char c) {
        if(child != null) {
            for(Node<Template> eachChild:child) {
                if(eachChild.ca == c) return eachChild;
            }
        }
        return null;
    }

    public Node<Template> copy(){
        Node<Template> nc = new Node();
        nc.ca = this.ca;
        nc.content = this.content;
        for (Node<Template> i: this.child){
            Node<Template> aux = i.copy();
            nc.child.add(aux);
        }
        return nc;
    }
}

