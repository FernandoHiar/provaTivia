package br.com.vaga.emprego.stream;


/**
 * Created by rnaufal on 17/11/16.
 */
public class CharSequenceStream implements Stream {

    private final String source;

    private final char[] chars;

    private int index;

    public CharSequenceStream(String source) {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }
        this.source = source;
        this.chars = source.toCharArray();
    }

    public char getNext() {
        if (!hasNext()) {
            throw new RuntimeException("Finalizado o processamento dos caracteres");
        }
        return chars[index++];
    }

    public boolean hasNext() {
        return index < chars.length;
    }

    @Override
    public String toString() {
        return source;
    }
}
