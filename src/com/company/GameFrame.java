package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
/**
 * Okno główne gry, dziedziczące po JFrame
 * @author Adam Dradrach
 */
public class GameFrame extends JFrame {

    /** Szerokość okna gry */
    public static final int screenWidth = 1200;
    /** Wysokość okno gry */
    public static final int screenHeight = 800;


    /**
     * Główny konstruktor klasy - zdefiniowanie parametrów okna
     * @throws UnsupportedAudioFileException Zły format pliku
     * @throws LineUnavailableException Nie można dotrzeć do linii
     * @throws IOException Inny błąd
     */
    GameFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        //zdefiniowanie okna gry
        this.getContentPane().add(new GamePanel(screenWidth, screenHeight));
        this.setTitle("Wybuchowe słowa");
        this.setUndecorated(true);
        this.setIconImage(Resources.bombIconImage);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
