
package compilador.Estruturas;

import java.util.HashMap;


public class MapaTokens {

    HashMap<String, Integer> tokens;

    public MapaTokens() {
        tokens = new HashMap<>();
        tokens.put("P"
                + "rogram", 1);
        tokens.put("Label", 2);
        tokens.put("Const", 3);
        tokens.put("Var", 4);
        tokens.put("Procedure", 5);
        tokens.put("Begin", 6);
        tokens.put("End", 7);
        tokens.put("Integer", 8);
        tokens.put("Array", 9);
        tokens.put("Of", 10);
        tokens.put("Call", 11);
        tokens.put("Goto", 12);
        tokens.put("If", 13);
        tokens.put("Then", 14);
        tokens.put("Else", 15);
        tokens.put("While", 16);
        tokens.put("Do", 17);
        tokens.put("Repeat", 18);
        tokens.put("Until", 19);
        tokens.put("Readln", 20);
        tokens.put("Writeln", 21);
        tokens.put("Or", 22);
        tokens.put("And", 23);
        tokens.put("Not", 24);
        tokens.put("Identificador", 25);
        tokens.put("Inteiro", 26);
        tokens.put("For", 27);
        tokens.put("To", 28);
        tokens.put("Case", 29);
        tokens.put("+", 30);
        tokens.put("-", 31);
        tokens.put("*", 32);
        tokens.put("/", 33);
        tokens.put("[", 34);
        tokens.put("]", 35);
        tokens.put("(", 36);
        tokens.put(")", 37);
        tokens.put(":=", 38);
        tokens.put(":", 39);
        tokens.put("=", 40);
        tokens.put(">", 41);
        tokens.put(">=", 42);
        tokens.put("<", 43);
        tokens.put("<=", 44);
        tokens.put("<>", 45);
        tokens.put(",", 46);
        tokens.put(";", 47);
        tokens.put("Literal", 48);
        tokens.put(".", 49);
        tokens.put("..", 50);
        tokens.put("$", 51);
    }

    public  Token getToken(String palavra,int linha) {
        if (tokens.containsKey(palavra)) {
            return new Token(tokens.get(palavra), palavra,linha);
        } 
        else if(Character.isDigit(palavra.charAt(0))|| palavra.charAt(0) == '-'){
            return new Token(tokens.get("Inteiro"), palavra,linha);
        }
        else {
            return new Token(tokens.get("Identificador"), palavra,linha);
        }
    }
    
    public int getCodigo(String token){
        return tokens.get(token);
    }
    
    public boolean existeToken(String palavra){
        return tokens.containsKey(palavra);
    }
}
