package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Prosta gra polegająca na tłumaczeniu słów polski - angielski
 * @author Adam Dradrach
 */
public class WybuchoweSlowaMain {
    /**
     * metoda uruchomiająca grę
     * @param args domyślnie
     * @throws UnsupportedAudioFileException zły format pliku audio
     * @throws LineUnavailableException niedostępny plik
     * @throws IOException inny problem
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {


        //catch odpowidzialny za możliwe nie znalezienie pliku z czcionką
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\ARCADECLASSIC.TTF")));
        } catch (IOException |FontFormatException e) {

        }
        GameFrame startingMenu = new GameFrame();
    }

}
