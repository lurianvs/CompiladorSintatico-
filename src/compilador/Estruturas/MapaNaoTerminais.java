
package compilador.Estruturas;

import java.util.HashMap;


public class MapaNaoTerminais {

    private final HashMap<String, Integer> mapaNaoTerminais;

    public MapaNaoTerminais() {
        mapaNaoTerminais = new HashMap<>();
        mapaNaoTerminais.put("PROGRAMA", 52);
        mapaNaoTerminais.put("BLOCO", 53);
        mapaNaoTerminais.put("DCLROT", 54);
        mapaNaoTerminais.put("LID", 55);
        mapaNaoTerminais.put("REPIDENT", 56);
        mapaNaoTerminais.put("DCLCONST", 57);
        mapaNaoTerminais.put("LDCONST", 58);
        mapaNaoTerminais.put("DCLVAR", 59);
        mapaNaoTerminais.put("LDVAR", 60);
        mapaNaoTerminais.put("TIPO", 61);
        mapaNaoTerminais.put("DCLPROC", 62);
        mapaNaoTerminais.put("DEFPAR", 63);
        mapaNaoTerminais.put("CORPO", 64);
        mapaNaoTerminais.put("REPCOMANDO", 65);
        mapaNaoTerminais.put("COMANDO", 66);
        mapaNaoTerminais.put("RCOMID", 67);
        mapaNaoTerminais.put("RVAR", 68);
        mapaNaoTerminais.put("PARAMETROS", 69);
        mapaNaoTerminais.put("REPPAR", 70);
        mapaNaoTerminais.put("ELSEPARTE", 71);
        mapaNaoTerminais.put("VARIAVEL", 72);
        mapaNaoTerminais.put("VARIAVEL1", 73);
        mapaNaoTerminais.put("REPVARIAVEL", 74);
        mapaNaoTerminais.put("ITEMSAIDA", 75);
        mapaNaoTerminais.put("REPITEM", 76);
        mapaNaoTerminais.put("EXPRESSAO", 77);
        mapaNaoTerminais.put("REPEXPSIMP", 78);
        mapaNaoTerminais.put("EXPSIMP", 79);
        mapaNaoTerminais.put("REPEXP", 80);
        mapaNaoTerminais.put("TERMO", 81);
        mapaNaoTerminais.put("REPTERMO", 82);
        mapaNaoTerminais.put("FATOR", 83);
        mapaNaoTerminais.put("CONDCASE", 84);
        mapaNaoTerminais.put("CONTCASE", 85);
        mapaNaoTerminais.put("RPINTEIRO", 86);
        mapaNaoTerminais.put("SEM EFE", 87);
    }

    public boolean existeNaoTerminal(Integer naoTerminal) {
        return mapaNaoTerminais.containsValue(naoTerminal);
    }

    public boolean existeSimbolo(String naoTerminal) {
        return mapaNaoTerminais.containsKey(naoTerminal);
    }

    public int getCodigo(String terminal) {
        return mapaNaoTerminais.get(terminal);
    }

  

}
