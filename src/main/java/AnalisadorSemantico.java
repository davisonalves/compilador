import java.util.ArrayList;
import java.util.List;

public class AnalisadorSemantico {

    public List<String> tabelaSimbolos;

    public AnalisadorSemantico() {
        tabelaSimbolos = new ArrayList<>();
    }

    public void analisar(String linha, List<String> dicionario) {
        linha = linha.replace(";", "");
        String[] tokens = linha.split(" ");

        if (tokens[0].equals("lade")) {
            if (tokens[1].matches("[a-zA-Z][a-zA-Z0-9]*")) {
                // Adicionar à tabela de símbolos
                if (estaNaTabelaSimbolos(tokens[1])) {
                    System.out.println("Variável já declarada: " + tokens[1]);
                    return;
                }
                tabelaSimbolos.add(tokens[1]);
            } else {
                System.out.println("Nome inválido para a variável: " + tokens[1]);
            }
        } else if (tokens[0].equals("udskriv")) {
            // Verificar se é uma string de texto entre aspas simples ('')
            if (tokens[1].startsWith("'") && tokens[1].endsWith("'")) {
                // É uma string de texto, não precisa verificar a existência na tabela de símbolos
                return;
            }
            // Verificar se a variável existe na tabela de símbolos
            if (!estaNaTabelaSimbolos(tokens[1])) {
                System.out.println("Variável não declarada: " + tokens[1]);
            }
        } else {
            // Percorrer todas as palavras da linha
            for (String token : tokens) {
                // Verificar se é uma variável
                if (ehVariavel(token, dicionario)) {
                    if (!estaNaTabelaSimbolos(token)) {
                        System.out.println("Variável não declarada: " + token);
                    }
                }
            }
        }
    }

    public boolean ehVariavel(String palavra, List<String> dicionario) {
        if (palavra.matches("[a-z][a-z0-9]{0,9}")) {
            for (String s : dicionario) {
                if (s.equals(palavra)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean estaNaTabelaSimbolos(String palavra) {
        return tabelaSimbolos.contains(palavra);
    }
}
