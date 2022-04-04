package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za odtwarzanie dzwięków
 * @author Adam Dradrach
 */
public class SoundPlayer {
    /** Dzwięk odtwarzany w głównym menu */
    public Clip menuMusic;

    /**
     * Domyślny konstruktor klasy
     */
    SoundPlayer(){}

    /**
     * Funkcja definiująca odtwarzanie dzwiękó
     * @param f dane odtwarzanego pliku
     */
    public static synchronized void playSound(final File f) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * Stworzenie clipu muzyki odtwarzanej w menu głównym
     * @throws UnsupportedAudioFileException Niewłaściwy format pliku
     * @throws IOException Inny błąd
     * @throws LineUnavailableException nieznaleziono
     */
    public void createMenuMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream inputStreamCon = AudioSystem.getAudioInputStream(Resources.mainMusicSound);
        menuMusic = AudioSystem.getClip();
        menuMusic.open(inputStreamCon);
        menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        menuMusic.start();

    }

    /**
     * Odtworzenie dzwięku eksplozji po wybuchu bomby po dobrym wpisaniu słowa
     */
    public static void playExplosionSound(){
        playSound (Resources.explosionSound);
    }

    /**
     * Odtworzenie dzwięku wybuchu bomby po upadku na ziemię
     */
    public static void playExplosionSound2(){
        playSound (Resources.explosionSound2);
    }

    /**
     * Odtworzenie dzwięku kliknięcia przycisku
     */
    public static void playClickSound(){
        playSound (Resources.clickSound);
    }

    /**
     * Odtworzenie dzwięku porażki
     */
    public static void playLoseSound(){
        playSound (Resources.loseSound);
    }

    /**
     * Odtworzenie dzwięku intro
     */
    public static void playIntroPlayingSound(){
        playSound (Resources.introPlayingSound);
    }

    /**
     * Odtworzenie dzwięku utraty życia
     */
    public static void playLiveDownSound(){
        playSound (Resources.liveDownSound);
    }

    /**
     * Odtworzenie dzwięku wpisania złej litery
     */
    public static void playBadLetterSound(){
        playSound (Resources.badLetterSound);
    }
}
