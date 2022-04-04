package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Klasa pomocnicza, zawierające dane informacyjne gry i zapisy
 * @author Adam Dradrach
 */
public class GameStatus {

    /** Nazwa gracza */
    public String PLAYERNAME;
    /** Ostatnie nieprzetłumaczone słowo */
    public String MISSEDWORD = "";
    /** Ilość zdobytych punktów */
    public int POINTS = 0;
    /** Pozostałe graczowi życia */
    public int GAMELIVES = 3;
    /** Tryb gry
     * 1 - english - polish
     * 2 - polish - english
     */
    public int GAMEMODE = 0;
    /** Tablica zawierające 5 najlepszych rekordów - punkty*/
    public int[] records = new int[5];
    /** tablica zawierająca 5 najlepszych rekordów - nazwy graczy */
    public String[] recordsPlayerName = new String[5];
    /** Lokalizacja i nazwa pliku zawierającego rekordy */
    String saveFileName = "save\\records.txt";


    /**
     * Konstruktor klasy
     * Wczytanie pliku zapisu
     */
    GameStatus(){
        readSave(saveFileName);
    }

    /**
     * Zresetowanie rozgrywki i podstawowych statystyk
     */
    public void resetGame(){
        GAMELIVES = 3;
        POINTS = 0;
        GAMEMODE = 0;
        MISSEDWORD = "";
    }

    /**
     * Sprawdzenie czy przy zakończeniu gry uzyskany wynik mieści się w rankingu jeśli tak wpisanie do rankingu
     */
    public void checkRecord(){
        for (int i =0; i<=4; i++){
            if (POINTS>=records[i]) {
                if (i == 0) {

                    records[4] = records[3];
                    records[3] = records[2];
                    records[2] = records[1];
                    records[1] = records[0];

                    recordsPlayerName[4] = recordsPlayerName[3];
                    recordsPlayerName[3] = recordsPlayerName[2];
                    recordsPlayerName[2] = recordsPlayerName[1];
                    recordsPlayerName[1] = recordsPlayerName[0];

                    records[i] = POINTS;
                    recordsPlayerName[i] = PLAYERNAME;
                    break;
                }
                if (i == 1) {

                    records[4] = records[3];
                    records[3] = records[2];
                    records[2] = records[1];

                    recordsPlayerName[4] = recordsPlayerName[3];
                    recordsPlayerName[3] = recordsPlayerName[2];
                    recordsPlayerName[2] = recordsPlayerName[1];

                    records[i] = POINTS;
                    recordsPlayerName[i] = PLAYERNAME;
                    break;
                }
                if (i == 2) {

                    records[4] = records[3];
                    records[3] = records[2];

                    recordsPlayerName[4] = recordsPlayerName[3];
                    recordsPlayerName[3] = recordsPlayerName[2];

                    records[i] = POINTS;
                    recordsPlayerName[i] = PLAYERNAME;
                    break;
                }
                if (i == 3) {

                    records[4] = records[3];

                    recordsPlayerName[4] = recordsPlayerName[3];

                    records[i] = POINTS;
                    recordsPlayerName[i] = PLAYERNAME;
                    break;
                } else if (i == 4) {

                    records[i] = POINTS;
                    recordsPlayerName[i] = PLAYERNAME;
                    break;
                }
            }
        }
        saveGame(saveFileName);
    }

    /**
     * Zapis rankingu do pliku
     * @param saveFileName nazwa i lokalizacja pliku zapisu
     */
    public void saveGame(String saveFileName) {
        try {
            PrintWriter out = new PrintWriter(saveFileName);
            out.println(records[0]);
            out.println(recordsPlayerName[0]);
            out.println(records[1]);
            out.println(recordsPlayerName[1]);
            out.println(records[2]);
            out.println(recordsPlayerName[2]);
            out.println(records[3]);
            out.println(recordsPlayerName[3]);
            out.println(records[4]);
            out.println(recordsPlayerName[4]);
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Niestety, nie mogę utworzyć pliku!");
        }
    }

    /**
     * Odczytanie rankingu z pliku
     * @param saveFileName nazwa i lokalizacja pliku odczytu
     */
    public void readSave(String saveFileName) {
        File fileData = new File(saveFileName);
        try {
            Scanner skaner = new Scanner(fileData);
            for (int i=0; i<=4; i++) {
                records[i] = skaner.nextInt();
                recordsPlayerName[i] = skaner.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak Pliku do odczytania!");
        }

    }

}
