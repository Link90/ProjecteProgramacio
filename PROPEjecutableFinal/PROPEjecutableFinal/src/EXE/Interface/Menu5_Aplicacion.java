package EXE.Interface;



import java.util.ArrayList;
import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class Menu5_Aplicacion extends javax.swing.JFrame {

    private boolean tactualizado = false;

    MutableAttributeSet rojo = new SimpleAttributeSet();
    MutableAttributeSet azul = new SimpleAttributeSet();
    MutableAttributeSet mut = new SimpleAttributeSet();
    MutableAttributeSet negro = new SimpleAttributeSet();
    Color t = new Color(255,0,0);


    //ArrayList<Integer> enters = new ArrayList<Integer>();
    
    public Menu5_Aplicacion() {
        StyleConstants.setForeground( mut, Color.RED);
        StyleConstants.setForeground( rojo, Color.RED);
        StyleConstants.setForeground( azul, Color.blue);
        StyleConstants.setForeground( negro, Color.black);
        initComponents();
    }
    private int accion = 0;
    private int posit = 0;


    public String get_Texto(){
        return TATexto.getText();
    }

    public String get_Nominalizaciones(){
        return TANominalizaciones.getText();
    }

    public String get_Buscar(){
        //System.out.println(CTPalabra_a_buscar.getText() + " VEefr43g45RBOOOOOOO");
        return CTPalabra_a_buscar.getText();   
    }

    public String get_InsertDelet(){
        return CTEjemplo_a_insertar_o_borrar.getText();
    }


    public int get_accion() {
        int aux = accion;
        accion = 0;
        return aux;
     }
    
    public void set_t(Color mutent){
        this.t = mutent;
        StyleConstants.setForeground( mut, t);
    }

    public void set_texto(String s) {
        TATexto.setText(s);
    }
    
    public void set_nominalizaciones(String s){
        TANominalizaciones.setText(s);      
    }

    public boolean get_text_acutualizado(){
        return tactualizado;
    }

    public void set_text_actualizado(boolean b) {
        tactualizado = b;
    }

    public String get_v_nomi(){
        return this.get_Buscar();
    }


    public void marcar_busqueda(ArrayList<Integer> v_pos, String p_buscada){
        while(!v_pos.isEmpty()) {
            TATexto.getStyledDocument().setCharacterAttributes(v_pos.get(0),
                    p_buscada.length(), mut, false);
            v_pos.remove(0);
        }
    }

    /*public void localizar_enters(){
        enters = new ArrayList<Integer>();
        String t = TATexto.getText();
        for(int i = 0; i < t.length(); ++i){
            if(t.charAt(i) == '\n') {
                System.out.println(i);
                enters.add(i);
            }
        }
        enters.add(t.length());
    }*/

    public void marcar_verbos(ArrayList<Integer> v_pos, 
            ArrayList<String> v_verbos) throws BadLocationException,
            InterruptedException{
        //System.out.println("DDDDDDDDD " + v_pos.size() + " " + v_verbos.size());
        //this.localizar_enters();
        //System.out.println("¬¬ enters localizados");
        //Integer i = 0;
        //Integer ent;
        this.desmarcar();
         //if ( enters.size() > 0) ent = enters.get(i);
         //else ent = enters.size();
        while(!v_pos.isEmpty()) {
            //if(v_pos.get(0) < ent){
                TATexto.getStyledDocument().setCharacterAttributes
                        (v_pos.get(0) , v_verbos.get(0).length(), mut, false);
                v_pos.remove(0);
                v_verbos.remove(0);
            //} else {

               // i = i + 1;
               // ent = enters.get(i);
            //}
         //this.desmarcar();
           // TATexto.grabFocus();e
        }
    }


    public void desmarcar() throws InterruptedException{
        //TATexto.setForeground(Color.black);
       
        TATexto.getStyledDocument().setCharacterAttributes
                (0, TATexto.getText().length(), negro, false);

    }


   // public void dd(){
     //   Point p = TATexto.getMousePosition();
       // System.out.println(p.toString());

        /*TATexto.setCaretPosition(10);
        String textoTotal = TATexto.getText();
        int posicion = textoTotal.indexOf("ss");
        */
        //System.out.println(TATexto.getCaret().getMark());
        //System.out.println(TATexto.getCaret().getDot());
   //     Color colorrojo=new Color(255, 0, 0);
        
     //   MutableAttributeSet attr = new SimpleAttributeSet();
       // StyleConstants.setForeground(attr, colorrojo);
        //Component cc. = new Component();
       // TATexto.add(this, WIDTH);

        //System.out.println(" aa   " + TATexto.getCaret().getMark());
        //System.out.println(" aa   " + TATexto.getCaret().getDot());
/*
        Caret c = TATexto.getCaret();
        c.setVisible(true);
        c.setSelectionVisible(true);

        TATexto.setCaret(c);
        TATexto.setCaretColor(colorrojo);
        TATexto.setCaretPosition(5);
        TATexto.moveCaretPosition(5 + 6);
        System.out.println("kkkkk");*/
        //System.out.println(" bb  " + TATexto.getCaret().getMark());
        //System.out.println(" bb   " + TATexto.getCaret().getDot());
   // }
   /* public void remarca(Integer ini, Integer fin) {
        Color colorrojo=new Color(255, 0, 0);
        String s = "casa";
        Graphics g = null;
        g.setColor(colorrojo);
        java.awt.TextArea cc = new java.awt.TextArea();
        cc.setText(s);
        TATexto.paint(g);



MutableAttributeSet attr = new SimpleAttributeSet();
StyleConstants.setForeground(attr, colorrojo);
//TATexto..setCharacterAttributes(attr, false);
        /*
        s.
       // Grafic g = new grafic();
     //   TATexto.print();.getFont();
   //     c = c + 5;
        TATexto.insert(null, WIDTH)
        String s = TATexto;
        TATexto.setForeground(colorrojo);
          //      TATexto.setFont(c + 5);
    * }
         *
         */
   


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BLimpiar_texto = new javax.swing.JToggleButton();
        BNominalizar = new javax.swing.JToggleButton();
        BBuscar = new javax.swing.JToggleButton();
        BInsertar_ejemplo = new javax.swing.JButton();
        BBorrar_ejemplo = new javax.swing.JButton();
        CTPalabra_a_buscar = new javax.swing.JTextField();
        CTEjemplo_a_insertar_o_borrar = new javax.swing.JTextField();
        TANominalizaciones = new java.awt.TextArea();
        BRecargar_archivo = new javax.swing.JButton();
        BCargar_texto = new javax.swing.JButton();
        BGuardar_texto = new javax.swing.JButton();
        BGuardar_Archivo = new javax.swing.JButton();
        BSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TATexto = new javax.swing.JTextPane();
        opciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BLimpiar_texto.setText("Limpiar texto");
        BLimpiar_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiar_textoActionPerformed(evt);
            }
        });

        BNominalizar.setLabel("Seleccionar Verbos");
        BNominalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNominalizarActionPerformed(evt);
            }
        });

        BBuscar.setText("Buscar");
        BBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarActionPerformed(evt);
            }
        });

        BInsertar_ejemplo.setText("Insertar_ejemplo");
        BInsertar_ejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInsertar_ejemploActionPerformed(evt);
            }
        });

        BBorrar_ejemplo.setText("Borrar_ejemplo");
        BBorrar_ejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBorrar_ejemploActionPerformed(evt);
            }
        });

        CTPalabra_a_buscar.setText("Palabra a buscar");

        CTEjemplo_a_insertar_o_borrar.setText("Ejemplo a insertar o borrar");

        BRecargar_archivo.setText("Recargar archivos");
        BRecargar_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRecargar_archivoActionPerformed(evt);
            }
        });

        BCargar_texto.setText("Cargar texto");
        BCargar_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargar_textoActionPerformed(evt);
            }
        });

        BGuardar_texto.setText("Guardar texto");
        BGuardar_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardar_textoActionPerformed(evt);
            }
        });

        BGuardar_Archivo.setText("Guardar Corpus");
        BGuardar_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardar_ArchivoActionPerformed(evt);
            }
        });

        BSalir.setText("Salir");
        BSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSalirActionPerformed(evt);
            }
        });

        TATexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TATextoMouseClicked(evt);
            }
        });
        TATexto.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                TATextoInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TATexto);

        opciones.setText("Opciones");
        opciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BCargar_texto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BLimpiar_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BGuardar_texto))
                                    .addComponent(CTPalabra_a_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BNominalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BInsertar_ejemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BBorrar_ejemplo, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                                    .addComponent(CTEjemplo_a_insertar_o_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)))
                            .addComponent(TANominalizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BRecargar_archivo, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(BGuardar_Archivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BNominalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BLimpiar_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BCargar_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BGuardar_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CTPalabra_a_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BBorrar_ejemplo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(BRecargar_archivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(BInsertar_ejemplo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CTEjemplo_a_insertar_o_borrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BGuardar_Archivo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TANominalizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarActionPerformed
        accion = 4;
        //System.out.println(CTPalabra_a_buscar.getText());
    }//GEN-LAST:event_BBuscarActionPerformed

    private void BLimpiar_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiar_textoActionPerformed
        accion = 2;
    }//GEN-LAST:event_BLimpiar_textoActionPerformed

    private void BInsertar_ejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInsertar_ejemploActionPerformed
        accion = 6;
    }//GEN-LAST:event_BInsertar_ejemploActionPerformed

    private void BCargar_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargar_textoActionPerformed
        accion = 1;
    }//GEN-LAST:event_BCargar_textoActionPerformed

    private void BGuardar_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardar_textoActionPerformed
        accion = 3;
    }//GEN-LAST:event_BGuardar_textoActionPerformed

    private void BNominalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNominalizarActionPerformed
        accion = 5;
    }//GEN-LAST:event_BNominalizarActionPerformed

    private void BBorrar_ejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBorrar_ejemploActionPerformed
        accion = 7;
    }//GEN-LAST:event_BBorrar_ejemploActionPerformed

    private void BRecargar_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRecargar_archivoActionPerformed
        accion = 8;
    }//GEN-LAST:event_BRecargar_archivoActionPerformed

    private void BGuardar_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardar_ArchivoActionPerformed
        accion = 9;
    }//GEN-LAST:event_BGuardar_ArchivoActionPerformed

    private void BSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSalirActionPerformed
        accion = 15;
    }//GEN-LAST:event_BSalirActionPerformed

    private void TATextoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_TATextoInputMethodTextChanged
        // TODO add your handling code here:
        tactualizado = false;
    }//GEN-LAST:event_TATextoInputMethodTextChanged

    private void TATextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TATextoMouseClicked
        if(TATexto.getCaretPosition() != 0) {
             this.posit = TATexto.getCaretPosition();
             this.accion = 11;
         }

       /*
        this.posit = TATexto.getCaretPosition();
        System.out.println("#######" + this.posit);

         if(this.posit != 0) {
             this.localizar_enters();
            this.posit = TATexto.getCaretPosition();
            Integer i = 0;
            Integer ent;
            if ( enters.size() > 0) ent = enters.get(i);
            else ent = enters.size();
            //System.out.println("#######" + this.posit + " " + i + " " + ent);
            while((posit+i) > ent) {
                i = i + 1;
                ent = enters.get(i);
                //System.out.println("))))))))))))))))))" + this.posit + " " + i + " " + ent);
            }
            this.posit = this.posit + i;
            //System.out.println("##===============" + this.posit + " " + i + " " + ent);
            this.accion = 11;
        }*/
    }//GEN-LAST:event_TATextoMouseClicked

    private void opcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesActionPerformed
        // TODO add your handling code here:
        accion = 17;
    }//GEN-LAST:event_opcionesActionPerformed

    public Integer get_posit() {
    return this.posit;
}

    public void set_posit(Integer x) {
        this.posit = x;
    }

    /*public ArrayList<Integer> get_enters(){
        return enters;
    }*/

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBorrar_ejemplo;
    private javax.swing.JToggleButton BBuscar;
    private javax.swing.JButton BCargar_texto;
    private javax.swing.JButton BGuardar_Archivo;
    private javax.swing.JButton BGuardar_texto;
    private javax.swing.JButton BInsertar_ejemplo;
    private javax.swing.JToggleButton BLimpiar_texto;
    private javax.swing.JToggleButton BNominalizar;
    private javax.swing.JButton BRecargar_archivo;
    private javax.swing.JButton BSalir;
    private javax.swing.JTextField CTEjemplo_a_insertar_o_borrar;
    private javax.swing.JTextField CTPalabra_a_buscar;
    private java.awt.TextArea TANominalizaciones;
    private javax.swing.JTextPane TATexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton opciones;
    // End of variables declaration//GEN-END:variables

}
