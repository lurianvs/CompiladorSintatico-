
package compilador.Controle;

import compilador.Erros.ErroSintatico;

import compilador.Estruturas.Token;
import compilador.Comp.Compilador;
import compilador.View.Editor;
import compilador.Comp.Sintatico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControleEditor implements ActionListener {

    ModeloTabelaToken modelo = new ModeloTabelaToken();

    private final Editor editor;
    private String caminho_arquivo = null;
    private String local_arquivo;

    public String getArquivo() {
        return local_arquivo;
    }

    public void setArquivo(String local_arquivo) {
        this.local_arquivo = local_arquivo;
    }

    public ControleEditor(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            verificarComando(e);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        } catch (ErroSintatico ex) {

        }
    }

    public void verificarComando(ActionEvent ae) throws IOException, ErroSintatico {
        switch (ae.getActionCommand()) {
            case "Abrir":
                abrir();

                break;
            case "Salvar":
                salvarArquivo();
                break;
            case "Salvar Como":
                System.out.println("testeeee");
                salvarComo();
                break;
            case "Fechar":
                sair();
                break;
            case "Compila":
                Stack<Token> pilha = Compilador.rodarLexico(editor.getTexto());
                editor.addTabela(pilha);
                pilha = invertePilha(pilha);
                new Sintatico(pilha).AnaliseSintatica();

                break;

        }

    }

    public Stack<Token> invertePilha(Stack<Token> pilha) {
        Stack<Token> pilhaInvertida = new Stack<>();
        while (!pilha.empty()) {
            pilhaInvertida.push(pilha.pop());
        }
        return pilhaInvertida;
    }

    private void sair() {
        editor.dispose();
    }

    private void abrir() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        caminho_arquivo = fileChooser.getSelectedFile().getAbsolutePath();
        editor.setTexto(verificaArquivo(caminho_arquivo));
    }

    private void salvarArquivo() throws IOException {
        gravar(editor.getTexto(), getArquivo());
    }

    private void salvarComo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }

        caminho_arquivo = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
        setArquivo(caminho_arquivo);
        gravar(editor.getTexto(), caminho_arquivo);
    }

    public void gravar(String texto, String caminho) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write(texto);
        } catch (IOException ex) {
            throw new IOException("Não foi possível salvar o arquivo.");
        }
    }

    public static String verificaArquivo(String caminho) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho));) {
            String linha;
            StringBuilder texto = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                texto.append(linha).append('\n');
            }
            return texto.toString();
        } catch (IOException e) {
            throw new IOException("Arquivo inexistente!");
        }
    }

}
