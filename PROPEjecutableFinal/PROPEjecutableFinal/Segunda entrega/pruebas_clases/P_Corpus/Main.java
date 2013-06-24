

package P_Corpus;
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
        Controlador_dominio cd = new Controlador_dominio();
        
        String s;
        while(bool) {
            System.out.println("1 - Crear el corpus que viene por defecto");
            System.out.println
    ("2 - Crear el corpus con otro archivo que no sea el que va por defecto");
            System.out.println("3 - Obtener el numero de ejemplos diferentes que hay ");
            System.out.println("4 - obterner la frecuencia con la que se ha repetido un ejemplo");
            System.out.println("5 - Obtener todas las nominalizaciones de un verbo");
            System.out.println("6 - Obtenr un archivo con todos los ejemplos del corpus");
            System.out.println("7 - Insertar un ejemplo en el corpus");
            System.out.println("8 - Saber si un ejemplo esta en el corpus");
            System.out.println("9 - Eliminar un ejemplo el corpus");
            System.out.println("10 - Eliminar un ejemplo el corpus");
            System.out.println("0 - Salir");
            s = in.readLine();
            if(s.equals("1")) {
                cd.Cor = cd.ini_corpus();
                System.out.println("Se ha cargado correctamente el corpus");
            } else if(s.equals("2")) {
                System.out.println("Carga cualquier archivo");
                System.out.println("Inserta el path del archivo con el nombre y la extension correspondiente");
                s = in.readLine();
                cd.Cor = cd.carga_corpus(s);
                System.out.println("Se ha cargado correctamente el corpus");
            } else if(s.equals("3")) {
                System.out.println("Hay " + cd.Cor.get_num_total_c() + " ejemplos diferentes");
            } else if(s.equals("4")) {
                System.out.println("Introduce el infinitivo");
                String a = in.readLine();
                System.out.println("Introduce el nombre");
                String b = in.readLine();
                System.out.println("La frecuencia del ejemplo es " + cd.Cor.get_fre_c(a, b));
            } else if(s.equals("5")) {
                System.out.println("Inserta el infinitivo sobre el que quieres obtener sus nominalizaciones");
                s = in.readLine();
                System.out.println("Para facilitar la lectura del resultado los valores se imprimiran en el archivo");
                System.out.println("Inserta la ruta de escritura");
                String dir = in.readLine();
                cd.escribe_fichero(cd.Cor.get_all_B(s), dir);
                System.out.println("Se han escrito en el archivo");
            } else if(s.equals("6")) {
                System.out.println("Para facilitar la lectura del resultado los valores se imprimiran en el archivo");
                System.out.println("Inserta la ruta de escritura");
                String dir = in.readLine();
                cd.escribe_fichero(cd.Cor.get_all_c(), dir);
                System.out.println("Se han escrito en el archivo");
            } else if(s.equals("7")) {    
                System.out.println("Introduce el infinitivo");
                String a = in.readLine();
                System.out.println("Introduce el nombre");
                String b = in.readLine();
                System.out.println("Introduce la frecuencia del ejemplo (>=1)");
                Integer c = Integer.valueOf(in.readLine());
                cd.Cor.insert_c(a, b, c);
                System.out.println("Se ha introducido el ejemplo correctamente");
            } else if(s.equals("8")) {
                System.out.println("Introduce el infinitivo");
                String a = in.readLine();
                System.out.println("Introduce el nombre");
                String b = in.readLine();
                if (cd.Cor.search_c(a, b)) System.out.println("Si que esta");
                else System.out.println("Si que esta");
            } else if(s.equals("9")) {    
                System.out.println("Introduce el infinitivo");
                String a = in.readLine();
                System.out.println("Introduce el nombre");
                String b = in.readLine();
                cd.Cor.delete_AB(a, b);
                System.out.println("Se ha eliminado el ejemplo");
            } else if(s.equals("10")) {
                System.out.println("Introduce el porcentaje de ejemplos en el primer array");
                Integer c = Integer.valueOf(in.readLine());
                System.out.println("Para facilitar la lectura del resultado los valores se imprimiran en el archivo");
                System.out.println("Inserta la ruta de escritura del txt para el primer array");
                String dir1 = in.readLine();
                System.out.println("Inserta la ruta de escritura del txt para el segundo array");
                String dir2 = in.readLine();
                ArrayList<Ejemplo> ar1 = new ArrayList<Ejemplo>();
                ArrayList<Ejemplo> ar2 = new ArrayList<Ejemplo>();
                cd.Cor.get_all_c(ar1, ar2, c);
                cd.escribe_fichero(ar1, dir1);
                cd.escribe_fichero(ar2, dir2);
             } else if(s.equals("0")) {    
                bool = false;
                System.out.println("Se elimina el corpus actual");
                cd = new Controlador_dominio();
                System.out.println("Menu Inicial:");
            }
        }
        System.out.println("Fin de la prueba");
     }

}
