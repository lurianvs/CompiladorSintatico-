
package compilador.Erros;

import javax.swing.JOptionPane;



public class ErroLexico extends Exception{
    
    public ErroLexico(String message,int linha) {
        
        super("Erro lexico: "+message+"Na linha: "+linha);
        JOptionPane.showMessageDialog(null,"Erro lexico: "+message+"Na linha: "+linha);
        
    }  
}
