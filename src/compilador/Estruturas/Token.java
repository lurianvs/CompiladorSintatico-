
package compilador.Estruturas;
public class Token {
    private final int codigo;
    private final String palavra;
    private final Integer linha;

    public Token(int codigo, String palavra,int linha) {
        this.codigo = codigo;
        this.palavra = palavra;
        this.linha = linha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getPalavra() {
        return palavra;
    }
    
    public int getLinha(){
        return linha;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Palavra: " + palavra;
    }

    
}
