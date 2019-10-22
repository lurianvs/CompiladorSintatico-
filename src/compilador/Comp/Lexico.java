
package compilador.Comp;

import compilador.Erros.ErroLexico;
import compilador.Estruturas.Token;
import compilador.Estruturas.MapaTokens;
import java.util.Stack;


public class Lexico {

    Stack<Token> tk;
    MapaTokens mapaTok = new MapaTokens();

    public Lexico() {
        tk = new Stack<>();
    }
    int linha = 1;

    public Stack<Token> analiseLexica(Stack<Character> pilha) throws ErroLexico {

        while (!pilha.isEmpty()) {
            Character caractere = pilha.pop();

            if (Character.isWhitespace(caractere)) {
                if (caractere == '\n') {
                    linha++;
                }
                continue;
            }

//          analisar letras/digitos
            if (Character.isLetter(caractere) || caractere == '_') {
                pilha = verificaPalavras(pilha, caractere);
//              analisar digitos
            } else if (Character.isDigit(caractere)) {
                pilha = verificaInteiros(pilha, caractere);
            } else if (operadoresMatematicos(caractere)) { // numeros negativos nao funciona
                if (caractere == '-') {
                    verificaInteiros(pilha, caractere);
                } else {
                    tk.push(mapaTok.getToken(Character.toString(caractere), linha));
                }
                //tk.push(mapaTok.getToken(Character.toString(caractere), linha));

            } //analisar atribuicao
            else if (caractere == ':') {
                verificaAtribuicao(pilha, caractere);
            } else if (operadoresRelacionais(caractere)) {
                verificaRelacionais(pilha, caractere);
            } else if (caractere == '\'') {
                verificaLiteral(pilha, caractere);
            } else if (Simbolos(caractere)) {
                verificaSimbolos(pilha, caractere);

            }

        }

        return tk;
    }

    public boolean operadoresMatematicos(Character caractere) {
        //if(caractere=='+'||caractere=='-'||caractere=='*'||caractere=='/')
        return caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/';

        //return caractere;
    }

    public boolean operadoresRelacionais(Character caractere) {
        return caractere == '>' || caractere == '<' || caractere == '=';
    }

    public boolean Simbolos(Character caractere) {
        return caractere == '.' || caractere == ',' || caractere == ';' || caractere == '$'
                || caractere == '[' || caractere == ']' || caractere == '('
                || caractere == ')';
    }

    
public Stack<Character> verificaPalavras(Stack<Character> pilha, Character caractere) throws ErroLexico {

        String palavra = caractere.toString();
        String primeiraMaiuscula = caractere.toString();
        while (!pilha.empty()) {

            caractere = pilha.pop();
            if (palavra.length() > 30) {
                
                throw new ErroLexico("ERRO LEXICO: " + palavra,linha);
            }
            
            if (Character.isLetterOrDigit(caractere) || caractere.equals('_')) {
                palavra += caractere;
            } else {
                pilha.push(caractere);
                break;
            }
        }
        for (int i=0; i<palavra.length(); i++) {
             
            if(i==0){
                char c = palavra.charAt(i);
                String aux = ""+c;
                
                primeiraMaiuscula = ""+ aux.toUpperCase().trim();
                System.out.println("primeriamaiscula:"+primeiraMaiuscula);
            }
             if(i>0){
                char c = palavra.charAt(i);
                String aux = ""+c;
                primeiraMaiuscula += aux.toLowerCase().trim();
                 System.out.println("segunda primeira maiuscula:"+primeiraMaiuscula);
            }
        }
       
        palavra = primeiraMaiuscula;  
        tk.add(mapaTok.getToken(palavra,linha));

        return pilha;
    }

    public Stack<Character> verificaInteiros(Stack<Character> pilha, Character caractere) throws ErroLexico {
        String palavra = caractere.toString();
        while (!pilha.empty()) {
            caractere = pilha.pop();
            //palavra += caractere.toString();

            if (Character.isLetter(caractere)) { //tem que adicionar exception
                throw new ErroLexico("Numero invalido!", linha);

            } else if (!Character.isDigit(caractere)) {
                pilha.push(caractere);
                break;

            } else {
                palavra += caractere;
                if (Integer.parseInt(palavra) > 32767) {
                    throw new ErroLexico("Numero nao suportado!", linha);

                } else if (Integer.parseInt(palavra) < -32767) {
                    throw new ErroLexico("Numero nao suportado!", linha);
                }

            }
        }
        tk.add(mapaTok.getToken(palavra, linha));

        return pilha;
    }

    public void verificaAtribuicao(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        if (pilha.empty()) {
            tk.add(mapaTok.getToken(palavra, linha));
            return;
        }
        caractere = pilha.pop();
        if (caractere == '=') {
            palavra += caractere;
            tk.add(mapaTok.getToken(palavra, linha));
            return;
        }
        pilha.push(caractere);
        tk.add(mapaTok.getToken(palavra, linha));
    }

    public void verificaRelacionais(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        if (pilha.empty()) {
            tk.add(mapaTok.getToken(palavra, linha));
            return;
        }
        if (caractere == '>') {

            caractere = pilha.pop();

            if (caractere == '=') {
                palavra += caractere.toString();
                tk.add(mapaTok.getToken(palavra, linha));
                // palavra = "";

            } else {
                pilha.push(caractere);
                tk.add(mapaTok.getToken(palavra, linha));

            }

        } else if (caractere == '<') {
            caractere = pilha.pop();
            if (null == caractere) {
                pilha.push(caractere);

            } else {
                switch (caractere) {
                    case '>':
                        palavra += caractere.toString();
                        tk.add(mapaTok.getToken(palavra, linha));
                        break;
                    case '=':
                        palavra += caractere.toString();
                        tk.add(mapaTok.getToken(palavra, linha));
                        break;
                    default:
                        pilha.push(caractere);
                        break;
                }
            }

        } else if (caractere == '=') {

            tk.add(mapaTok.getToken(palavra, linha));
        }
    }

    public void verificaComentario(Stack<Character> pilha, Character caractere) throws ErroLexico {
        // try {

        while (!pilha.empty()) {
            // caractere = pilha.pop();
            if (caractere == '\n') {
                linha++;
            }
            if (caractere == '*') {
                caractere = pilha.pop();
                if (caractere == ')') {
                    return;
                }
            } else {
                if (!pilha.empty()) {
                    caractere = pilha.pop();
                }

                if (pilha.empty()) {
                    throw new ErroLexico("Comentario infinito!", linha);
                }
            }

        }

        /* } catch (Exception e) {
            throw new ErroLexico("Comentario infinito!");
        }*/
    } //nao feito ainda

    public void verificaLiteral(Stack<Character> pilha, Character caractere) throws ErroLexico {
        String palavra = "";

        while (!pilha.empty()) {
            caractere = pilha.pop();
            if (caractere == '\'') {
                if (palavra.length() > 255) {
                    throw new ErroLexico("erro", linha);

                }

                tk.add(new Token(mapaTok.getCodigo("Literal"), palavra, linha));
                return;
            } else {
                palavra += caractere.toString();
            }

        }
        if (pilha.empty() && caractere != '\'') {
            throw new ErroLexico("Literal nao fechado", linha);
        }

    }

    public void verificaSimbolos(Stack<Character> pilha, Character caractere) throws ErroLexico {
        String palavra = caractere.toString();

        if (caractere == '.' || caractere == '(') {
            if (pilha.empty()) {
                tk.add(mapaTok.getToken(palavra, linha));
                return;
            }
            if (caractere == '.') {

                caractere = pilha.pop();

                if (caractere == '.') {
                    palavra += caractere.toString();
                    tk.add(mapaTok.getToken(palavra, linha));

                } else {
                    pilha.push(caractere);
                    tk.add(mapaTok.getToken(palavra, linha));

                }

            } else if (caractere == '(') {
                //palavra+= caractere.toString();
                if (!pilha.empty()) {
                    caractere = pilha.pop();

                    if (caractere == '*') {
                        verificaComentario(pilha, caractere);
                    } else {
                        pilha.push(caractere);
                        tk.add(mapaTok.getToken(palavra, linha));
                        return;

                    }
                } else {
                    tk.add(mapaTok.getToken(palavra, linha));
                    return;
                }
            }
        } else {
            tk.add(mapaTok.getToken(palavra, linha));
            return;
        }

    }

}
