

package P_Ejemplo;
import java.util.*;
import java.io.*;

/**
 *
 * @author 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        boolean bool = true;
        boolean eliminado = true;
        BufferedReader in = new BufferedReader
                                            (new InputStreamReader(System.in));
        System.out.println("Menu Inicial:");
        String s;
        while(bool) {
            System.out.println("1 - Crear un nuevo ejemplo");
            System.out.println("9 - Salir");
            s = in.readLine();
            if(s.equals("1")) {
                System.out.println("1 - Crear un nuevo ejemplo con 2 Strings");
                System.out.println
                    ("2 - Crear un nuevo ejemplo con 2 Strings y 1 Integer");
                System.out.println("9 - Retroceder");
                s = in.readLine();
                if(s.equals("1")) {
                    System.out.println("Introduce el primer elemento");
                    String a = in.readLine();
                    System.out.println("Introduce el segundo elemento");
                    String b = in.readLine();
                    Integer c;
                    Ejemplo e = new Ejemplo(a, b);
                    eliminado = false;
                    System.out.println("Ejemplo creado");
                    while (!eliminado) {
                        System.out.println("1 - Consultar primer campo");
                        System.out.println("2 - Consultar segundo campo");
                        System.out.println("3 - Consultar tercer campo");
                        System.out.println("4 - Modificar primer campo");
                        System.out.println("5 - Modificar segundo campo");
                        System.out.println("6 - Modificar tercer campo");
                        System.out.println("7 - Eliminar Ejemplo");
                        s = in.readLine();
                        if(s.equals("1")) System.out.println(e.get_a());
                        else if (s.equals("2")) System.out.println(e.get_b());
                        else if (s.equals("3")) System.out.println(e.get_c());
                        else if (s.equals("4")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            a = in.readLine();
                            e.set_a(a);
                        } else if (s.equals("5")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            b = in.readLine();
                            e.set_b(b);
                        } else if (s.equals("6")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            c = Integer.valueOf(in.readLine());
                            e.set_c(c);
                        } else if (s.equals("7")) eliminado = true;
                    }
                    System.out.println("Se ha eliminado");
                } else if (s.equals("2")) {
                    System.out.println("Introduce el primer elemento");
                    String a = in.readLine();
                    System.out.println("Introduce el segundo elemento");
                    String b = in.readLine();
                    Integer c = Integer.valueOf(in.readLine());
                    Ejemplo e = new Ejemplo(a, b, c);
                    eliminado = false;
                    System.out.println("Ejemplo creado");
                    while (!eliminado) {
                        System.out.println("1 - Consultar primer campo");
                        System.out.println("2 - Consultar segundo campo");
                        System.out.println("3 - Consultar tercer campo");
                        System.out.println("4 - Modificar primer campo");
                        System.out.println("5 - Modificar segundo campo");
                        System.out.println("6 - Modificar tercer campo");
                        System.out.println("7 - Eliminar Ejemplo");
                        s = in.readLine();
                        if(s.equals("1")) System.out.println(e.get_a());
                        else if (s.equals("2")) System.out.println(e.get_b());
                        else if (s.equals("3")) System.out.println(e.get_c());
                        else if (s.equals("4")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            a = in.readLine();
                            e.set_a(a);
                        } else if (s.equals("5")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            b = in.readLine();
                            e.set_b(b);
                        } else if (s.equals("6")) {
                            System.out.println
                                    ("Introduce el nuevo elemento(String)");
                            c = Integer.valueOf(in.readLine());
                            e.set_c(c);
                        } else if (s.equals("7")) eliminado = true;
                    }
                    System.out.println("Se ha eliminado");
                } else if (s.equals("9")) {
                    System.out.println("Menu Inicial:");
                    eliminado = true;
                }
            } else if (s.equals("9")) bool = false;
        }
        System.out.println("Fin de la prueba");
     }

}
