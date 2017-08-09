package br.com.vaga.emprego;

import br.com.vaga.emprego.stream.CharSequenceStream;
import br.com.vaga.emprego.util.StreamUtils;

/**
 * Created by rnaufal on 17/11/16.
 */
public class Main {

    public static void main(String[] args) {
        char caractere = StreamUtils.procurar(new CharSequenceStream(args[0]));
        if (caractere != StreamUtils.NAO_ENCONTRADO) {
            System.out.println("\nSaída: " + caractere);
        } else {
            System.out.println("\nCaracter vogal nao localizado.");
        }
    }
}
