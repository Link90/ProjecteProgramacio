/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Interface;

import EXE.Controladores.Controlador_Interface;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Administrador
 */
public class Main {
    static Controlador_Interface CI = new Controlador_Interface();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, BadLocationException  {
        CI.Inicio_CI();
    }
}
