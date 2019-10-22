
package compilador.Comp;

import compilador.Erros.ErroSintatico;

import compilador.Estruturas.MapaNaoTerminais;
import compilador.Estruturas.Token;
import compilador.Estruturas.MapaTokens;

import compilador.Estruturas.TabelaParsing;

import java.util.Stack;
import javax.swing.JOptionPane;



public class Sintatico {

    Stack<Token> a;
    Stack<Integer> x;
    TabelaParsing tabelaparsing;

    MapaNaoTerminais mapaNaoTerminais = new MapaNaoTerminais();
    MapaTokens mapatokens = new MapaTokens();

    public Sintatico(Stack<Token> pilhatokens) {
        this.a = pilhatokens;
        this.x = new Stack<>();
        this.tabelaparsing = new TabelaParsing();
        x.push(mapaNaoTerminais.getCodigo("PROGRAMA"));

    }

    public void AnaliseSintatica() throws ErroSintatico {
        Integer simbolo;
        Token token;

        while (!a.empty()) {

            simbolo = x.peek();
            token = a.peek();

            if (simbolo < 52) {

                if (simbolo == token.getCodigo()) {
                    x.pop();
                    a.pop();
                } else {
                    throw new ErroSintatico("Erro sintatico: "+token.getPalavra()+token.getLinha());
                    
                    
                }

            } else {
                String derivado = tabelaparsing.getDerivacao(simbolo, token);
                if ("NULL".equals(derivado)) { 
                    x.pop();
                }
                else if(derivado == null){
                    throw new ErroSintatico("Derivação nula!"+token.getPalavra()+token.getLinha());
                }
                else {
                    Derivacao(x.pop(),derivado);
                }

            }
            

        }
        JOptionPane.showMessageDialog(null,"Sucesso!");
        System.out.println("Sucesso!");

    }

    public void Derivacao(Integer simbolo,String derivado) throws ErroSintatico {

        String[] producao = derivado.split("\\|");

        for (int i = producao.length - 1; i >= 0; i--) {
            if (mapaNaoTerminais.existeSimbolo(producao[i])) { 
                x.push(mapaNaoTerminais.getCodigo(producao[i]));

            } else if (mapatokens.existeToken(producao[i])) { 
                x.push(mapatokens.getCodigo(producao[i]));
            } else {
                throw new ErroSintatico(""+a.peek().getPalavra()+a.peek().getLinha());
            }

        }

    }

}

