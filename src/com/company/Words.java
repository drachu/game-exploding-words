package com.company;

import java.util.Random;

/**
 * Klasa odpowiedzialna za działąnia związane ze słowami
 * @author Adam Dradrach
 */
public class Words {

    /** Numer losowego słowa */
    int randomWordNumber;
    /** Numer pierwszego angielskiego słowa */
    public int wordNumber1ENGLISH;
    /** Pierwsze angielskie słowo */
    public String word1ENGLISH = Resources.englishWords[wordNumber1ENGLISH];
    /** Numer drugiego angielskiego słowa */
    public int wordNumber2ENGLISH;
    /** Drugie angielskie słowo */
    public String word2ENGLISH = Resources.englishWords[wordNumber2ENGLISH];
    /** Numer pierwszego polskiego słowa */
    public int wordNumber1POLISH;
    /** Pierwsze polsie słowo */
    public String word1POLISH = Resources.polishWords[wordNumber1POLISH];
    /** Numer drugiego polskiego słowa */
    public int wordNumber2POLISH;
    /** Drugie polskie słowo */
    public String word2POLISH = Resources.polishWords[wordNumber2POLISH];
    /** Wpisywane słowo do porównania z pierwszym wylosowanym słowen */
    public String writtenWord1 = "";
    /** Wpisywane słowo do porównania z drugim wylosowanym słowen */
    public String writtenWord2 = "";
    /** Ostatnie przetłumaczone słowo */
    public String translatedWord = "";
    /** Obiekt losujący */
    public Random random = new Random();

    /**
     * Zresetowanie obu słów, przypisanie nowych losowych
     */
    public void resetWords(){
        randomWordNumber = random.nextInt(Resources.englishWords.length);
        wordNumber1ENGLISH = randomWordNumber;
        wordNumber1POLISH = randomWordNumber;
        word1ENGLISH = Resources.englishWords[randomWordNumber];
        word1POLISH = Resources.polishWords[randomWordNumber];
        randomWordNumber = random.nextInt(Resources.englishWords.length);
        wordNumber2ENGLISH = randomWordNumber;
        wordNumber2POLISH = randomWordNumber;
        word2ENGLISH = Resources.englishWords[randomWordNumber];
        word2POLISH = Resources.polishWords[randomWordNumber];
        while (wordNumber1ENGLISH==wordNumber2ENGLISH){
            resetWords();
        }
    }

    /**
     * Zresetowanie słowa 1, przypisanie nowego losowego
     */
    public void resetWord1(){
        randomWordNumber = random.nextInt(Resources.englishWords.length);
        wordNumber1ENGLISH = randomWordNumber;
        wordNumber1POLISH = randomWordNumber;
        word1ENGLISH = Resources.englishWords[randomWordNumber];
        word1POLISH = Resources.polishWords[randomWordNumber];
        while (wordNumber1ENGLISH==wordNumber2ENGLISH){
            resetWord1();
        }
    }

    /**
     * Zresetowanie słowa 2, przypisanie nowego losowego
     */
    public void resetWord2(){
        randomWordNumber = random.nextInt(Resources.englishWords.length);
        wordNumber2ENGLISH = randomWordNumber;
        wordNumber2POLISH = randomWordNumber;
        word2ENGLISH = Resources.englishWords[randomWordNumber];
        word2POLISH = Resources.polishWords[randomWordNumber];
        while (wordNumber1ENGLISH==wordNumber2ENGLISH){
            resetWord2();
        }
    }

}
