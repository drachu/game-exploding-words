package com.company;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Klasa odpowiedzialna za pole tekstowe, gdzie nie można wpisywać wiećej znaków niż określono
 */
public class JTextFieldLimit extends PlainDocument {
    /** Określony limit słów w polu tekstowym */
    private final int limit;

    /**
     * Konstruktor klasy
     * @param limit limit słow
     */
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Sprawdzenie ilości znaków w stringu
     * @param offset offset
     * @param str str
     * @param attr attr
     * @throws BadLocationException zła lokacja
     */
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
