package com.rnaufal.vowel.stream.search.stream;

import com.rnaufal.vowel.stream.search.exception.InvalidStreamStateException;

/**
 * Created by rnaufal on 17/11/16.
 */
public class CharSequenceStream implements Stream {

    private final String source;

    private final char[] chars;

    private int currentIndex;

    public CharSequenceStream(String source) {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }
        this.source = source;
        this.chars = source.toCharArray();
    }

    public char getNext() {
        if (!hasNext()) {
            throw new InvalidStreamStateException();
        }
        return chars[currentIndex++];
    }

    public boolean hasNext() {
        return currentIndex < chars.length;
    }

    @Override
    public String toString() {
        return source;
    }
}
