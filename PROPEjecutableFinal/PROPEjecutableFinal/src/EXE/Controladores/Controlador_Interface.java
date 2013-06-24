/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Controladores;


import EXE.Interface.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;



/**
 *
 * @author  Administrador
 */
public class Controlador_Interface {

    Menu1_Select_Carga menu1 = new Menu1_Select_Carga();
    Menu2_Carga menu2 = new Menu2_Carga();
    Menu3_Select_Carga2 menu3 = new Menu3_Select_Carga2();
    Menu4_Ubicacion_Archivo menu4 = new  Menu4_Ubicacion_Archivo();
    Menu5_Aplicacion menu5 = new Menu5_Aplicacion();
    Menu6_Ubicacion_Archivo2 menu6 = new  Menu6_Ubicacion_Archivo2();
    Menu7_Opciones menu7 = new  Menu7_Opciones();
    colorchooser colorchooser = new  colorchooser();


    private int accion = 0;

    private Controlador_dominio CD = new Controlador_dominio();

    private boolean cargado = false;
    private boolean aprendido = false;
    private String vactual = " ";
    private boolean text_read = false;
    protected Color t = new Color(0,0,0);

    public Controlador_Interface(){}
    
    

    public void Inicio_CI() throws InterruptedException, FileNotFoundException,
            IOException, BadLocationException{

            menu1.setVisible(true);
            accion = menu1.get_accion();
            boolean continuar = true;
            do{
                if (accion == 1){
                    //Carga por defecto
                    CD.Cargar(null, null, null, null);
                    cargado = true;
                    JOptionPane.showMessageDialog(new JFrame(), "La carga por "
                            + "defecto se ha realizado con éxito");
                } else if (accion == 2) {
                    //Carga selectiva
                    continuar = false;
                    menu1.setVisible(false);
                    this.Carga_selectiva();  
                } else if (accion == 3 && cargado) {
                    //Aprender
                    continuar = false;
                    CD.aprender();
                    aprendido = true;
                    menu1.setVisible(false);
                    this.Aplicacion();
                }
                Thread.sleep(100);
                accion = menu1.get_accion();
            } while (continuar);
        }



 public void Inicio_CI2() throws InterruptedException, FileNotFoundException,
         IOException, BadLocationException{

        menu3.setVisible(true);
        accion = menu3.get_accion();
        boolean continuar = true;
        do{
            if (accion == 1){
                //Carga por defecto
                CD.Cargar(null, null, null, null);
                //System.out.println("error capturado" + CD.get_err());
                if(CD.get_err() != 0) JOptionPane.showMessageDialog(new 
                        JFrame(), "Fallo de carga", "Inane "
                        + "error", JOptionPane.ERROR_MESSAGE);
                else JOptionPane.showMessageDialog(new JFrame(), "La carga por "
                        + "defecto se ha realizado con éxito");
                cargado = true;
            } else if (accion == 2) {
                //Carga selectiva
                menu3.setVisible(false);
                this.Carga_selectiva();
                continuar = false;
            } else if (accion == 3 && cargado) {
                //Aprender
                menu3.setVisible(false);
                continuar = false;
                CD.aprender();
                aprendido = true;
                this.Aplicacion();
            } else if (accion == 4 && !cargado) {
                //Atras
                menu3.setVisible(false);
                continuar = false;
                this.Aplicacion();
            }
            Thread.sleep(100);
            accion = menu3.get_accion();
        } while (continuar);
     
}
 
 
 private void opciones() throws InterruptedException, FileNotFoundException,
         IOException, BadLocationException {
        menu7.setVisible(true);
        accion = menu7.get_accion();
        boolean continuar = true;
        do{
            if (accion == 1){
                int i = menu7.getn();
                CD.set_N(i);
                //System.out.println(i);
                
            } else if (accion == 2){
                  colorchooser.setVisible(true);
                  int accion2 = colorchooser.get_accion();
                  boolean continuar2 = true;
                  do{
                     if (accion2 == 1){
                        this.t = colorchooser.get_color();
                        menu5.set_t(this.t);
                        JOptionPane.showMessageDialog(new JFrame(), "El color "
                                + "se ha cambiado con éxito");
                        this.Aplicacion();
                        }
                     else if (accion2 == 2){
                         colorchooser.setVisible(false);
                         continuar2 = false;
                         this.opciones();
                     }
                      Thread.sleep(100);
                      accion2 = colorchooser.get_accion();
                   }while(continuar2);
            } else if (accion == 3){
                menu7.setVisible(false);
                String d = this.Directorio();
                menu7.setVisible(true);
                if(d != null) CD.read_carga_por_defecto(d);
            } else if (accion == 4){
           
                File manual = new File ("ManualAplicacio.pdf");
                Desktop.getDesktop().open(manual);
                  
            } else if (accion == 5){

            } else if (accion == 6){
                menu7.setVisible(false);
                continuar = false;
                this.Aplicacion();
            }
            Thread.sleep(100);
            accion = menu7.get_accion();
        } while (continuar);
    }


    public void Carga_selectiva() throws InterruptedException,
            FileNotFoundException, IOException, BadLocationException{
        String c1 = null;
        String c2 = null;
        String c3 = null;
        String c4 = null;
        menu2.setVisible(true);
        accion = menu2.get_accion();
        boolean continuar = true;
        String ss;
        do{
            if (accion == 1){
                //Cargar Irregulares
                ss = Directorio();
                menu2.set_text(1, ss);
                //System.out.println(ss);
                c1 = ss;
            } else if (accion == 2) {
                //Cargar Infinitivos
                ss = Directorio();
                menu2.set_text(2, ss);
                //System.out.println(ss);
                c2 = ss;
            } else if (accion == 3) {
                //Cargar Corpus
                ss = Directorio();
                menu2.set_text(3, ss);
                //System.out.println(ss);
                c3 = ss;
            } else if (accion == 4) {
                //Cargar Diccionario
                ss = Directorio();
                menu2.set_text(4, ss);
                //System.out.println(ss);
                c4 = ss;
            } else if (accion == 5) {
                //Cargar
                menu2.setVisible(false);
                CD.Cargar(c1, c2, c3, c4);
                continuar = false;
                cargado = true;
                if(aprendido) this.Inicio_CI2();
                else this.Inicio_CI();
            } else if (accion == 6) {
                //Atras
                menu2.setVisible(false);
                continuar = false;
                if(aprendido) this.Inicio_CI2();
                else this.Inicio_CI();
            }
            Thread.sleep(100);
            accion = menu2.get_accion();
        } while (continuar);
    }

    private String Directorio() throws InterruptedException{
        menu4.setVisible(true);
        accion = menu4.get_accion();
        do{
            if (accion == 1){
                //Abrir
                menu4.setVisible(false);
                return menu4.get_dir();
            } else if (accion == 2){
                //Cancelar
                menu4.setVisible(false);
                return null;
            }
            Thread.sleep(100);
            accion = menu4.get_accion();
        } while (true);
    }

     private String Directorio2() throws InterruptedException{
        menu6.setVisible(true);
        accion = menu6.get_accion();
        do{
            if (accion == 1){
                //Guardar
                menu6.setVisible(false);
                return menu6.get_dir();
            } else if (accion == 2){
                //Cancelar
                menu6.setVisible(false);
                return null;
            }
            Thread.sleep(100);
            accion = menu6.get_accion();
        } while (true);
    }


    private void Aplicacion () throws InterruptedException,
           FileNotFoundException, IOException, BadLocationException{
        menu5.setVisible(true);
        accion = menu5.get_accion();
        boolean continuar = true;
        Integer tsize = 0;       
        //System.out.println("En Aplicacion");
        CD.carga_texto_inicial();
        String ss = CD.texto();
        menu5.set_texto(ss);
        menu5.set_text_actualizado(true);
        String verbo = " ";
        do{
            //System.out.println(tsize + " " + menu5.get_Texto().length());
            if(!tsize.equals(menu5.get_Texto().length())){
                tsize = menu5.get_Texto().length();
                this.text_read = false;
                menu5.desmarcar();
                //menu5.localizar_enters();
                menu5.set_text_actualizado(false);
            }
            if (accion == 1){
                //Cargar text
                menu5.setVisible(false);
                String d = Directorio();
                menu5.setVisible(true);
                if(d != null) {
                    CD.carga_texto(d);
                    JOptionPane.showMessageDialog(new JFrame(), "La carga del "
                            + "texto se ha realizado con éxito");
                    String s = CD.texto();
                    menu5.set_texto(s);
                    menu5.set_text_actualizado(true);
                }
                menu5.desmarcar();
            } else if (accion == 2){
                //Limpiar texto
                menu5.desmarcar();
                menu5.set_texto(null);
                menu5.set_text_actualizado(false);
            } else if (accion == 3){
                //Guardar Texto
                menu5.desmarcar();
                menu5.setVisible(false);
                String d = Directorio2();
                menu5.setVisible(true);
                if(d != null) {
                    String t = menu5.get_Texto();
                    //System.out.println("en aplicacion, maccion==3 " + t);
                    CD.save_texto(t, d);
                    JOptionPane.showMessageDialog(new JFrame(), "El guardado "
                            + "del texto se ha realizado con éxito");
                }
           } else if (accion == 4){
                //Buscar
               menu5.desmarcar();
                if(!menu5.get_text_acutualizado()){
                    CD.set_texto(menu5.get_Texto());
                    menu5.set_text_actualizado(true);
                    //menu5.localizar_enters();
                }
                verbo = menu5.get_Buscar();
                ArrayList<Integer> cc = CD.apariciones(verbo);
                menu5.marcar_busqueda(cc, verbo);                
                if(CD.is_verb(verbo)) {
                    String aux = "";
                    ArrayList<String> nom = new ArrayList();
                    CD.aplicar_reglas(nom, verbo);
                    //System.out.println(nom.size()+" numero size nom");
                    CD.busca_nom(nom);
                    Integer resultados = 0;
                    while(!nom.isEmpty()){
                        aux = aux.concat(" ").concat(nom.get(0));
                        nom.remove(0);
                        resultados = resultados + 1;
                    }
                    if(resultados.equals(0)) menu5.set_nominalizaciones("No hay"
                            + " nominalizaciones: ");
                    else if(resultados.equals(1)) menu5.set_nominalizaciones("n"
                            + "ominalizacion: ".concat(aux));
                    else menu5.set_nominalizaciones("nominalizacio"
                            + "nes: ".concat(aux));
                }
            } else if (accion == 5){
                //Nominalizar
                menu5.desmarcar();
                if(!menu5.get_text_acutualizado()){
                    CD.set_texto(menu5.get_Texto());
                    menu5.set_text_actualizado(true);
                    menu5.set_texto(CD.texto());
                }
                //System.out.println("localizar verbos");
                ArrayList<Integer> posiciones_verbos = new ArrayList();
                ArrayList<String> listado_verbos = new ArrayList();
                CD.localizar_verbos(listado_verbos, posiciones_verbos);
                String aux = "Listado de verbos del texto:";
                for(int i = 0; i < listado_verbos.size(); ++i){
                    aux=aux.concat("\n").concat(listado_verbos.get(i)).concat(""
                            + " ").concat(posiciones_verbos.get(i).toString());
                }
                //menu5.set_nominalizaciones(aux);                
                menu5.set_nominalizaciones(aux);
              //System.out.println("DDDDttttttttttttttttDDDDD " + posiciones_verbos.size() + " " + listado_verbos.size());
                //System.out.println("verbos localizados");
                menu5.marcar_verbos(posiciones_verbos, listado_verbos); 
                this.text_read = true;
            } else if (accion == 6){
                //insertar ejemplo
                String b = menu5.get_InsertDelet();
                if(verbo != null && b != null)CD.añadir_ejemplo(this.vactual, b);
                //System.out.println("insert ------ " + this.vactual + " " + b);
            } else if (accion == 7){
                //borrar ejemplo
                String b = menu5.get_InsertDelet();
                if(verbo != null && b != null)
                    CD.eliminar_ejemplo(this.vactual, b);
            } else if (accion == 8){
                //Recargar archivos
                menu5.setVisible(false);
                cargado = false;
                this.Inicio_CI2();
                continuar = false;
            } else if (accion == 9){
                //Guardar corpus
                menu5.setVisible(false);
                String d = Directorio2();
                menu5.setVisible(true);
                if(d != null) {
                    CD.save_corpus(d);
                    JOptionPane.showMessageDialog(new JFrame(), "El guardado "
                            + "del corpus se ha realizado con éxito");
                }
            } else if (accion == 15){
                //Salir
                System.exit(0);
            } else if(accion == 11 && this.text_read) {
                if(menu5.get_Texto().length() != menu5.get_posit()){
                    this.vactual = CD.get_infinitivo(menu5.get_posit());
                    if((menu5.get_posit() < menu5.get_Texto().length()) &&
                            CD.get_v_text().contains(this.vactual)) {
                        String aux = "Nominalizaciones de ".concat(this.vactual);
                        ArrayList<String> listado_nom = new ArrayList();
                        CD.aplicar_reglas(listado_nom, this.vactual);
                        CD.busca_nom(listado_nom);
                        for(int i = 0; i < listado_nom.size(); ++i){
                            aux = aux.concat(""
                                + "\n").concat(listado_nom.get(i)).concat(" ");
                        }
                        menu5.set_posit(0);
                        menu5.set_nominalizaciones(aux);
                    }else menu5.set_nominalizaciones("No ha seleccionado "
                            + "un verbo");
                } 
            } else if (accion == 17){
                 menu5.setVisible(false);
                 this.opciones();
            }
            Thread.sleep(100);
            accion = menu5.get_accion();
        } while (continuar);
    }

public Color get_color() {
        return this.t;
     }

}
