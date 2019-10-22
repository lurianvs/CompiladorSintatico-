
package compilador.Erros;

import javax.swing.JOptionPane;


public class ErroSintatico extends Exception{

    public ErroSintatico(String message) {
        super("Erro sintatico: " + message);
        JOptionPane.showMessageDialog(null, "Erro sintatico: " + message);
    }

}
