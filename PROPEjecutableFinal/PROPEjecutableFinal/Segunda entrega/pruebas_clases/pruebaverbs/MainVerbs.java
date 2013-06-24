package pruebaverbs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import Controladores.*;
//import Compartidas.*;

import java.io.*;
import java.util.*;


/**
 *
 * @author albert
 */
public class MainVerbs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

     Verbs a = new Verbs();
     System.out.println("Esto es una prueba de la clase Verbs");
     System.out.println("-------------------------------------");


     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     String s = new String();

        try{

            while(true){

                 System.out.println("1-inicializar infinitivos por defecto");
                 System.out.println("2-inicializar infinitivos por carga");
                 System.out.println("3-inicializar irregulares por defecto");
                 System.out.println("4-inicializar irregulares por carga");
                 System.out.println("5-ver si una palabra es un verbo");
                 System.out.println("6-obtener el infinitivo de una forma conjugada");
                 System.out.println("7-insertar una pareja en verbs");
                 System.out.println("8-printar verbs");
                 System.out.println("9-salir");
                 System.out.println();
                 System.out.println("Escriure opció:");

                String f = in.readLine();

                if(f.equals("1")) {
                    a.ini_reg();
                    System.out.println(".....OK!");
                }else if(f.equals("2")) {
                    System.out.println("Introduir path del arxiu desde directori del projecte");
                    System.out.println("ex: /package/files/example.txt:");
                    s = in.readLine();
                    a.carga_infinitivos(s);
                    System.out.println(".....OK!");
                }else if (f.equals("3")) {
                        a.ini_irr();
                        System.out.println(".....OK!");
                }else if(f.equals("4")){
                    System.out.println("Introduir path del arxiu desde directori del projecte");
                    System.out.println("ex: /package/files/example.txt:");

                    s = in.readLine();
                    a.carga_irregulares(s);
                    System.out.println(".....OK!");
                }else if (f.equals("5")) {
                     System.out.println("Introduir verb:");
                     s = in.readLine();
                     System.out.println();
                     System.out.println(a.is_verb(s));
                }else if (f.equals("6")) {
                     System.out.println("Introduir verb:");
                     s = in.readLine();
                     System.out.println();
                     System.out.println(a.get_infinitivo(s));
                }else if (f.equals("7")) {
                     System.out.println("Introduir verb:");
                     s = in.readLine();
                     System.out.println("Introduir infinitiu:");
                     String q = new String();
                     q = in.readLine();
                     a.insert_in_verbo(s,q);
                }else if(f.equals("8")){
                   ArrayList<String>  l = a.get_v().get_all();
                        for(Integer i = 0; i<l.size(); ++i){
                             System.out.print(l.get(i)+" ");
                             String g = a.get_v().get_content(l.get(i));
                             System.out.println(g);
                        }

                }else if(f.equals("9")){
                    System.exit(0);

                }
                else {
                    System.out.println("Entrada no válida");

                }
                System.out.println();
            }
            }catch(IOException e){ System.out.println("error e/S");}

        }
}
