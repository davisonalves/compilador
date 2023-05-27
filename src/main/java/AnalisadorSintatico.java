import java.util.List;

public class AnalisadorSintatico {

    private boolean houveErro;

    public AnalisadorSintatico() {
        houveErro = false;
    }

    public void analisar(String linha, List<String> expressoes) {
        boolean resultado = false;
        for (int i = 0; i < expressoes.size(); i++) {
            if (linha.trim().matches(expressoes.get(i))) {
                System.out.println(linha + ": ok");
                return;
            }
        }

        houveErro = true;
        System.out.println("Erro na linha: " + linha);
    }

    public boolean houveErro() {
        return houveErro;
    }
}