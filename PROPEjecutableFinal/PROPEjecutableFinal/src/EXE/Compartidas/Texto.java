/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EXE.Compartidas;
import java.util.ArrayList;

/**
 *
 * @author Dani
 */
public class Texto {
    private String s_text;
    private ArrayList<Token> t_text;

    /**
     *
     * @param s
     * @return
     */
    private void tokenizer(String s) {
      t_text = new ArrayList();
      int n = s.length();
      for (int i = 0; i < n; i++) {
          String name = "";
          while (i < n && ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') ||
                           (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') ||
                            s.charAt(i) == '\'' || s.charAt(i) == '-')) {
              name += s.charAt(i);
              i++;
          }
          if (name.length() > 0 && ((name.charAt(0) >= 'a' && name.charAt(0) <= 'z') || (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z'))) {
             Token t = new Token(name, i - name.length());
             t_text.add(t);
          }
      }
    }

        private boolean is_token(String s) {
        if (s.length() > 0 && ((s.charAt(0) >= 'a' && s.charAt(0) <= 'z') ||
                               (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z'))) {
            for (int i = 1; i < s.length(); ++i)
                if (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') &&
                    !(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') &&
                    !(s.charAt(i) == '\'') && !(s.charAt(i) == '-')) return false;
            return true;
        }
        return false;
    }

    /**
     *
     */
    private Texto() {
      s_text = new String();
      t_text = new ArrayList();
     }

    /**
     *
     * @param s
     */
    public Texto(String s) {
      s_text = s;
      tokenizer(s);
     }

    /**
     *
     * @return
     */
    public Texto copy() {
        Texto copia = new Texto();
        copia.s_text = s_text;
        copia.t_text = t_text;
        return copia;
    }

    /**
     *
     * @return
     */
    public int size() {
      return t_text.size();
    }

    /**
     *
     * @param s
     * @param pos
     */
    public void change(String s, int pos) {
        if (pos >= 0 && pos < s_text.length() && is_token(s)) {
            boolean trobat = false;
            int n = t_text.size();
            for (int i = 0; i < n && !trobat; i++) {
                String aux = t_text.get(i).get_name();
                int pos_ini = t_text.get(i).get_pos();
                if (pos >= pos_ini && pos < pos_ini + aux.length()) {

                    // Substituim el nom del token, la posicio es queda igual
                    t_text.get(i).set_name(s);

                    // Retrocedim les posicions dels seguents tokens
                    if (s.length() < aux.length())
                      for (int j = i + 1; j < n; ++j)
                        t_text.get(j).set_pos(t_text.get(j).get_pos() - (aux.length() - s.length()));

                    // Avancem les posicions dels seguents tokens
                    if (s.length() > aux.length())
                      for (int j = i + 1; j < n; ++j)
                        t_text.get(j).set_pos(t_text.get(j).get_pos() + (s.length() - aux.length()));

                    // Modifiquem el string que conte el text
                    String s_inici = "", s_fi = "";
                    for (int j = 0; j < pos_ini; j++) s_inici += s_text.charAt(j);
                    for (int j = pos_ini + aux.length(); j < s_text.length(); ++j) s_fi += s_text.charAt(j);
                    s_text = s_inici + s + s_fi;

                    trobat = true;
                }
            }
        }
     }

    /**
     *
     * @param s1
     * @param s2
     */
    public void change_all(String s1, String s2) {
        int n = t_text.size();
        for (int i = 0; i < n; i++)
            if (t_text.get(i).get_name().equals(s1)) change(s2, t_text.get(i).get_pos());
    }

    /**
     *
     * @param s
     */
    public ArrayList exists_word(String s) {
        ArrayList<Integer> v = new ArrayList<Integer>();
        int n = t_text.size();
        for (int i = 0; i < n; i++) {
            if(t_text.get(i).get_name().equals(s)) v.add(t_text.get(i).get_pos());
        }
        return v;
     }

    /**
     *
     * @param s
     * @return
     */
    public int count_word(String s) {
        int count = 0;
        int n = t_text.size();
        for (int i = 0; i < n; i++) {
            if(t_text.get(i).get_name().equals(s)) ++count;
        }
        return count;
     }

      /**
       *
       * @param pos
       * @return
       */
      public Token get_token(int pos) {
        int i = 0;
        if (pos >= 0 && pos < s_text.length()) {
          int n = t_text.size();
          boolean trobat = false;
            while (i < n && !trobat) {
              String aux = t_text.get(i).get_name();
              int pos_ini = t_text.get(i).get_pos();
              if (pos >= pos_ini && pos < pos_ini + aux.length()) trobat = true;
              i++;
          }
        }
        return t_text.get(i - 1);
      }

      /**
       *
       * @return
       */
      public String get_all() {
          return s_text;
      }

      public ArrayList<Token> get_all_tokens() {
          return t_text;
      }
}