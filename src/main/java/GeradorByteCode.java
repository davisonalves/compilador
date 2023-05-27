public class GeradorByteCode {

    public GeradorByteCode() {
        // TODO Auto-generated constructor stub
    }

    public String gerar(String linha) {
        String[] token = linha.trim().split("\\s+");

        //declaração de variavel
        if(token[0].equals("lade") && token[2].equals("=")){
            //array do tipo string
            if(token[3].contains("'")){
                return "String[]" + " " + token[1] + " " + "=" + " " + "{" + "\"" + token[3].substring(token[3].indexOf("'") + 1, token[3].lastIndexOf("'"))  + "\""+ "," + " "+ "\"" + token[4].substring(token[4].indexOf("'") + 1, token[4].lastIndexOf("'"))  + "\""+ "," + " "+ "\"" + token[5].substring(token[5].indexOf("'") + 1, token[5].lastIndexOf("'")) + "\"" + "};";
            }
            //array do tipo int
            if(!token[3].contains("'")){
                return "int[]" + " " + token[1] + " " + "=" + " " + "{" + token[3].substring(token[3].indexOf("[") + 1, token[3].lastIndexOf(",")) + "," + " " + token[4].substring(token[4].indexOf("[") + 1, token[4].lastIndexOf(",")) + "," + " " + token[5].substring(token[5].indexOf("[") + 1, token[5].lastIndexOf("]")) + "};";
            }
        }

        //imprimir
        if(token[0].equals("udskriv")){
            //imprimir string
            if(token[1].contains("'")){
                return "System.out.println(\"" + token[1].substring(token[1].indexOf("'") + 1, token[1].lastIndexOf("'")) + "\");";
            }
            //imprimir variavel
            if(!token[1].contains("'")){
                return "  " + "System.out.println(" + token[1] + " " + token[2] + " " + "\"" + " " + token[4] + " " + token[5] +  " " + "\"" + " " + token[7] + " " + token[8] + " " + token[9] + " " + "\"" +  " " + token[11] + " " + token[12] + "\");";
            }
        }


        //loop
        if(token[0].equals("til")){
            return "for (int" + " " + token[2] + " " + token[3] + " " + token[4] + " " + token[5] + " " + token[6] + " " + token[7] + " " + token[8] + " " + token[9];

        }
        //fim do loop
        if(token[0].equals("}")){
            return "}";
        }

        return "";
    }
}
