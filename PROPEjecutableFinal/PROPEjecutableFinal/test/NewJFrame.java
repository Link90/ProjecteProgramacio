
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Point;
import java.awt.TextArea;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.AttributeSet;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 25-may-2011, 16:23:20
 */

/**
 *
 * @author Administrador
 */
public class NewJFrame extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public NewJFrame() throws BadLocationException {
        initComponents();
        this.rr();
    }


    private JTextPane texto;

    public Integer i = 0;
    private JEditorPane jep = new JEditorPane("patio", "polo");

    public void rr() throws BadLocationException{
        
    tt.setText("casaa sksks spps");
        /*jep.setText("0123456789012345678");
        System.out.print(jep.getText(2, 7));
        String dd = "casa";
        jep.setText(jep.getText(1, 4).concat(dd.concat(jep.getText(4 + dd.length()-1, jep.getText().length()-1))));

        
        System.out.println("en rr");
        String c= "casa";
        t.setText(c);
        Caret cc = t.getCaret();
        cc.setSelectionVisible(true);
        cc.setVisible(true);
        t.setCaretColor(Color.red);



        Component ccc = new Component() {};
        String dd = "lsls";
        tt.new (dd, ccc);
        //t.a
        Frame fr = new Frame(" caooo");
        ttt.setText("ceñedst ssls");
        TextArea aa = new TextArea();
        aa.setText("casa");
        aa.setForeground(Color.BLUE);
        fr.add(aa);
        fr.pack();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setBold(attrs, true);
        int g = tt.getStyledDocument().getLength();
        tt.insertComponent(ccc);
        tt.setStyledDocument().insertString(g , "Negrita", attrs);,*/
       /* MutableAttributeSet cc = new SimpleAttributeSet();

        tt.setText("cassssssds sss");

        
        //AttributeSet cc = new AttributeSet();
        StyleConstants.setForeground( cc, Color.RED);
        tt.getStyledDocument().setCharacterAttributes(0, 5, cc, false);*/
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tt = new javax.swing.JTextPane();
        ttt = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(t);

        tt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttt, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ttt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ttMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttMouseClicked
        // TODO add your handling code here:
        //tt.setText("casaa");
        if (i == 0){
            i = 1;
            tt.setForeground(Color.red);
        } else {
            i = 0;
            tt.setForeground(Color.blue);
        }
        //Integer kk = tt.getCaretPosition();
        //double k = p.getX();
        //Integer o = (int)k;
        //System.out.println(kk);

    }//GEN-LAST:event_ttMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame().setVisible(true);
                } catch (BadLocationException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane t;
    private javax.swing.JTextPane tt;
    private java.awt.TextArea ttt;
    // End of variables declaration//GEN-END:variables


}
