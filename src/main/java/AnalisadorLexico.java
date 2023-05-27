import java.util.List;

public class AnalisadorLexico {

    private boolean houveErro = false;

    public AnalisadorLexico() {
        // TODO Auto-generated constructor stub
    }

    public void analisar(String linha, List<String> alfabeto) {
        boolean erro = false;

        for (int i = 0; i < linha.length(); i++) {
            char letra = linha.charAt(i);
            boolean encontrou = false;

            for (String caracteres : alfabeto) {
                if (caracteres.contains(Character.toString(letra))) {
                    encontrou = true;
                    break;
                }
            }

            if (!encontrou) {
                System.out.println("caracter: " + letra + " invalido!");
                erro = true;
                houveErro = true;
            }
        }

        if (!erro) {
            System.out.println("Linha analisada com sucesso: " + linha);
        }
    }

    public boolean houveErro() {
        return houveErro;
    }
}
