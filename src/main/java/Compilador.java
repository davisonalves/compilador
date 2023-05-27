import java.io.IOException;
import java.util.List;

public class Compilador {

    public List<String> alfabeto;
    public List<String> exemplo;
    public List<String> expressoes;
    public List<String> dicionario;

    public Compilador() {
        // TODO Auto-generated constructor stub
    }

    public void Iniciar() throws IOException {
        LerArquivo leitor = new LerArquivo();

        alfabeto = leitor.ler("C:\\Users\\dalvesdasi\\Documents\\estudos\\facul\\compiladores\\dbr\\alfabeto.txt");
        exemplo = leitor.ler("C:\\Users\\dalvesdasi\\Documents\\estudos\\facul\\compiladores\\dbr\\exemplo.txt");
        expressoes = leitor.ler("C:\\Users\\dalvesdasi\\Documents\\estudos\\facul\\compiladores\\dbr\\regexp.txt");
        dicionario =  leitor.ler("C:\\Users\\dalvesdasi\\Documents\\estudos\\facul\\compiladores\\dbr\\dicionario.txt");

        System.out.println("Alfabeto da linguagem de programacao:");
        for (int i = 0; i < alfabeto.size(); i++) {
            System.out.println(alfabeto.get(i));
        }
        System.out.println("----------------------------------------");

        System.out.println("Exemplo de codigo da linguagem de programacao:");
        for (int i = 0; i < exemplo.size(); i++) {
            System.out.println(exemplo.get(i));
        }
        System.out.println("----------------------------------------");

        String linha;
        AnalisadorLexico lexico = new AnalisadorLexico();
        System.out.println("Analise lexica das linhas do arquivo de exemplo:");
        for (int i = 0; i < exemplo.size(); i++) {
            linha = exemplo.get(i);
            lexico.analisar(linha, alfabeto);
        }

        if (!lexico.houveErro()) {
            System.out.println("Analise lexica bem sucedida!\n");
        }


        AnalisadorSintatico sintatico = new AnalisadorSintatico();
        System.out.println("Analise sintatica das linhas do arquivo de exemplo:");
        for (int i=0; i < exemplo.size(); i++) {
            linha = exemplo.get(i);
            sintatico.analisar(linha, expressoes);
        }

        if(!sintatico.houveErro()) {
            System.out.println("Analise sintatica bem sucedida!\n");
        }

        AnalisadorSemantico semantico = new AnalisadorSemantico();
        System.out.println("\nIniciando analise semantica...");
        for (int i=0; i < exemplo.size(); i++) {
            linha = exemplo.get(i);
            semantico.analisar(linha, expressoes);
        }
        System.out.println("Analise semantica bem sucedida\n");

        GeradorByteCode gerador = new GeradorByteCode();
        System.out.println("Gerando bytecode das linhas do arquivo de exemplo:");
        String exemploJava = "public class Main {\n  public static void main(String[] args) {\n";
        for (int i = 0; i < exemplo.size(); i++) {
            linha = exemplo.get(i);
            String bytecode = gerador.gerar(linha);
            System.out.println("Bytecode gerado: " + bytecode);
            exemploJava = exemploJava + "    " + bytecode + "\n";
        }
        exemploJava = exemploJava + "  }\n}";
        System.out.println("\nImprimindo exemplo em uma classe Main:");
        System.out.println(exemploJava);

    }
}
