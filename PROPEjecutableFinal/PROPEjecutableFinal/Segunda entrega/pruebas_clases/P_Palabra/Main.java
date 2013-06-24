

package P_Palabra;

import P_Ejemplo.*;
import java.io.*;

/**
 *
 * @author 
 */
public class Main {

        public static void main(String[] args) throws IOException {
            boolean bool = true;
            boolean p1_eliminada = true;
            boolean p2_eliminada = true;
            boolean probando = false;
            BufferedReader in = new BufferedReader
                                            (new InputStreamReader(System.in));
            System.out.println("Menu Inicial:");
            String s;
            while(bool) {
                System.out.println
                ("1 - Comenzar la prueba creando una Palabra llamada P1 vacia");
                System.out.println("9 - Salir");
                s = in.readLine();
                if(s.equals("1")) {
                    Palabra P1 = new Palabra();
                    Palabra P2 = new Palabra();
                    p1_eliminada = false;
                    probando = true;
                    System.out.println("Palabra P1 creada");
                    while (probando) {
                        System.out.println("1 - Consultar pal de P1");
                        System.out.println("2 - Modificar pal de P1");
                        System.out.println("3 - Crear una nueva palabra vacia");
                        System.out.println
                                ("4 - Crear una nueva palabra con un String");
                        System.out.println("5 - Consultar pal de P2");
                        System.out.println("6 - Modificar pal de P2");
                        System.out.println("7 - Son iguales P1 y P2?");
                        System.out.println("8 - Copia");
                        System.out.println("9 - Eliminar");
                        System.out.println
                ("0 - Ir al menu principal principal, se eliminaran P1 y P2");
                        s = in.readLine();
                        if(s.equals("1")) {
                            if (!p1_eliminada) System.out.println(P1.get_palabra());
                            else System.out.println("Primero crea P1");
                        } else if (s.equals("2")) {
                            if (!p1_eliminada){
                                System.out.println
                                    ("Introduce el nuevo valor de pal(String)");
                                s = in.readLine();
                                P1.set_palabra(s);
                            } else System.out.println("Primero crea P1");
                        } else if (s.equals("3")) {
                            if (!p1_eliminada && !p2_eliminada) {
                                   System.out.println
                                    ("Has creado el maximo numero de palabras");
                            } else if (!p1_eliminada && p2_eliminada) {
                                P2 = new Palabra();
                                p2_eliminada = false;
                                System.out.println("Palabra P2 creada");
                            } else if (p1_eliminada) {
                                P1 = new Palabra();
                                p1_eliminada = false;
                                System.out.println("Palabra P1 creada");
                            }  
                        } else if (s.equals("4")) {
                            if (!p1_eliminada && !p2_eliminada) {
                                   System.out.println
                                    ("Has creado el maximo numero de palabras");
                            } else if (!p1_eliminada && p2_eliminada) {
                                System.out.println
                        ("Introduce el valor de pal(String) para crear P2");
                                s = in.readLine();
                                P2 = new Palabra(s);
                                p2_eliminada = false;
                                System.out.println("Palabra P2 creada");
                            } else if (p1_eliminada) {
                                System.out.println
                        ("Introduce el valor de pal(String) para crear P1");
                                s = in.readLine();
                                P1 = new Palabra(s);
                                p1_eliminada = false;
                                System.out.println("Palabra P1 creada");
                            }
                        } else if(s.equals("5")) {
                            if (!p2_eliminada) System.out.println(P2.get_palabra());
                            else System.out.println("Primero crea P2");
                        }  else if (s.equals("6")) {
                            if (!p2_eliminada){
                                System.out.println
                                    ("Introduce el nuevo valor de pal(String)");
                                s = in.readLine();
                                P2.set_palabra(s);
                            } else System.out.println("Primero crea P2");
                        } else if (s.equals("7")){
                             if (!p1_eliminada && !p2_eliminada) {
                                 if (P1.get_palabra() == null
                                            || P2.get_palabra() == null){
                                                        System.out.println
                                    ("Como minimo una de las palabras es nula");
                                } else if (P1.compare_palabra(P2))
                                    System.out.println ("P1 y P2 son iguales");
                                   else System.out.println
                                    ("P1 y P2 son diferentes");
                            } else System.out.println
                                    ("Como minimo te falta una palabra");
                        } else if (s.equals("8")) {
                            if (!p1_eliminada && !p2_eliminada) {
                                System.out.println("1 - Copiar P1 en P2");
                                System.out.println("2 - Copiar P2 en P1");
                                s = in.readLine();
                                if(s.equals("1")) {
                                    P2.copy_palabra(P1);
                                    System.out.println
                                            ("P1 ha sido copiada en P2");
                                } else if(s.equals("2")) {
                                    P1.copy_palabra(P2);
                                    System.out.println
                                            ("P2 ha sido copiada en P1");
                                }
                            } else {System.out.println
                                    ("Como minimo te falta una palabra");
                            }
                        } else if (s.equals("9")){
                            System.out.println("1 - Eliminar P1");
                            System.out.println("2 - Eliminar P2");
                            s = in.readLine();
                            if(s.equals("1")) {
                                if (!p1_eliminada) {
                                    p1_eliminada = true;
                                    System.out.println("P1 se ha eliminado");
                                } else System.out.println("P1 no existe");
                            } else if(s.equals("2")) {
                                if (!p2_eliminada) {
                                    p2_eliminada = true;
                                    System.out.println("P2 se ha eliminado");
                                } else System.out.println("P2 no existe");
                            }
                        } else if (s.equals("0")){
                            System.out.println("Menu Inicial:");
                            p1_eliminada = true;
                            p2_eliminada = true;
                            probando = false;
                        }
                    }
                } else if (s.equals("9")) bool = false;
            }
            System.out.println("Fin de la prueba");
        }

}
