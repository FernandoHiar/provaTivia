package br.com.vaga.emprego.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.vaga.emprego.stream.Stream;


/**
 * Created by rnaufal on 17/11/16.
 */
public class StreamUtils {

    public static final char NAO_ENCONTRADO = ' ';

    private static final String VOGAIS_REGEX = "(?i)[aáàãâ�?ÀÃÂeéêÉÊií�?oóõôÓÕÔuúÚ]";

    private static final String CHARACTERE_ESPECIAL = "[^\\w]";

    private static final String DIGITO = "\\d";

    private StreamUtils() {

    }

    public static char procurar(Stream input) {
       
        Map<Character, Boolean> vogais = new LinkedHashMap<>();
        Map<Character, List<Character>> predecessors = new HashMap<>();

        char caractereAnterior = ' ';
        while (input.hasNext()) {
            char caractere = input.getNext();

            configuraVogais(vogais, caractere);
            procurarCaractereAnterior(predecessors, caractere, caractereAnterior);

            caractereAnterior = caractere;
        }

        return procurarVogal(vogais, predecessors);
    }

    private static Character procurarVogal(Map<Character, Boolean> vowelsByOccurrence,
                                         Map<Character, List<Character>> predecessors) {
        for (Map.Entry<Character, Boolean> vowelOccurrence : vowelsByOccurrence.entrySet()) {
            if (!vowelOccurrence.getValue()) {
                continue;
            }

            for (Character vowelPredecessor : predecessors.get(vowelOccurrence.getKey())) {
                if (!isConsonant(vowelPredecessor)) {
                    continue;
                }

                for (Character consonantPredecessor : predecessors.get(vowelPredecessor)) {
                    if (isVogal(consonantPredecessor)) {
                        return vowelOccurrence.getKey();
                    }
                }
            }
        }
        return NAO_ENCONTRADO;
    }

    private static void procurarCaractereAnterior(Map<Character, List<Character>> predecessors,
                                           char currentChar,
                                           char previousChar) {
        List<Character> characters = predecessors.get(currentChar);
        if (characters == null) {
            characters = new ArrayList<>();
            predecessors.put(currentChar, characters);
        }
        if (previousChar != ' ') {
            characters.add(previousChar);
        }
    }

    private static void configuraVogais(Map<Character, Boolean> vogais,
                                                  char caractere) {
        if (!isVogal(caractere)) {
            return;
        }
        if (vogais.get(caractere) == null) {
        	vogais.put(caractere, true);
        } else {
        	vogais.put(caractere, false);
        }
    }

    private static boolean isVogal(char caractere) {
        return isVowel(String.valueOf(caractere));
    }

    private static boolean isConsonant(char vogal) {
        String caractere = String.valueOf(vogal);
        return !isVowel(caractere) &&
                !isSpecialCharacter(caractere) &&
                !isDigit(caractere);
    }

    private static boolean isVowel(String character) {
        return character.matches(VOGAIS_REGEX);
    }

    private static boolean isDigit(String character) {
        return character.matches(DIGITO);
    }

    private static boolean isSpecialCharacter(String character) {
        return character.matches(CHARACTERE_ESPECIAL);
    }
}
