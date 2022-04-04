package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za okno rankingu
 * @author Adam Dradrach
 */
public class RankingPanel extends JPanel{

    /** Szerokość ekranu gry */
    private final int screenWidth;
    /** Wysokość ekranu gry */
    private final int screenHeight;
    /** Pole pierwszej pozycji w rankingu */
    private final JLabel place1Background = new JLabel();
    /** Pole drugiej pozycji w rankingu */
    private final JLabel place2Background = new JLabel();
    /** Pole trzeciej pozycji w rankingu */
    private final JLabel place3Background = new JLabel();
    /** Pole czwartej pozycji w rankingu */
    private final JLabel place4Background = new JLabel();
    /** Pole piątej pozycji w rankingu */
    private final JLabel place5Background = new JLabel();
    /** Tekst pierwszej pozycji w rankingu */
    private final JLabel place1 = new JLabel();
    /** Tekst drugiej pozycji w rankingu */
    private final JLabel place2 = new JLabel();
    /** Tekst trzeciej pozycji w rankingu */
    private final JLabel place3 = new JLabel();
    /** Tekst czwartej pozycji w rankingu */
    private final JLabel place4 = new JLabel();
    /** Tekst piątej pozycji w rankingu */
    private final JLabel place5 = new JLabel();
    /** Przycisk powrotu do menu */
    public JButton goBackButton = new JButton();

    /**
     * Konstruktor klasy, zdefiniowanie parametrów
     * @param screenWidth szerokość ekranu gry
     * @param screenHeight wysokość ekranu gry
     */
    RankingPanel(int screenWidth, int screenHeight){

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.setBounds((screenWidth/3), 0, screenWidth/3, screenHeight);
        this.setBackground(Color.black);
        this.setLayout(null);

        setRanking();
        this.setVisible(false);
    }


    /**
     * Wypełnienie elementów rankingu
     */
    public void setRanking(){

        Icon playerRanking1 = new ImageIcon("images\\playerRanking1.png");
        Icon playerRanking2 = new ImageIcon("images\\playerRanking2.png");
        Icon playerRanking3 = new ImageIcon("images\\playerRanking3.png");
        Icon playerRanking = new ImageIcon("images\\playerRanking.png");
        setRankingBackground(place1Background, 30);
        place1Background.setIcon(playerRanking1);
        setRankingBackground(place2Background, 140);
        place2Background.setIcon(playerRanking2);
        setRankingBackground(place3Background, 250);
        place3Background.setIcon(playerRanking3);
        setRankingBackground(place4Background, 360);
        place4Background.setIcon(playerRanking);
        setRankingBackground(place5Background, 470);
        place5Background.setIcon(playerRanking);

        goBackButton.setIcon(Resources.backButtonImage);
        goBackButton.setBounds(((screenWidth/3)/2-100), (screenHeight/2+200), 198, 100);
        goBackButton.setBorderPainted(false);
        goBackButton.addMouseMotionListener(null);
        goBackButton.setName("goBackButton");

        this.add(place1Background);
        this.add(place2Background);
        this.add(place3Background);
        this.add(place4Background);
        this.add(place5Background);

        place1Background.add(place1);
        place2Background.add(place2);
        place3Background.add(place3);
        place4Background.add(place4);
        place5Background.add(place5);
        this.add(goBackButton);

    }

    /**
     * Wypełnienie labeli rankingu
     * @param background tło labela danej pozycji rankingu
     * @param positionY pozycja tła
     */
    private void setRankingBackground(JLabel background, int positionY){

        background.setBounds(((screenWidth/3)/2-184), positionY, 369, 99);
        background.setBackground(Color.black);
        background.setLayout(null);
    }

    /**
     * Zaktualizowanie rankingu wynikami z pliku
     * @param record1 rekord gracza 1
     * @param player1 nazwa gracza 1
     * @param record2 rekord gracza 2
     * @param player2 nazwa gracza 2
     * @param record3 rekord gracza 3
     * @param player3 nazwa gracza 3
     * @param record4 rekord gracza 4
     * @param player4 nazwa gracza 4
     * @param record5 rekord gracza 5
     * @param player5 nazwa gracza 5
     */
    public void updateRanking(int record1, String player1, int record2, String player2, int record3, String player3, int record4, String player4, int record5, String player5){ //loading records from list to visual labels

        setPositionPointsRanking(place1, record1 +" "+player1);
        setPositionPointsRanking(place2, record2 +" "+player2);
        setPositionPointsRanking(place3, record3 +" "+player3);
        setPositionPointsRanking(place4, record4 +" "+player4);
        setPositionPointsRanking(place5, record5 +" "+player5);
    }

    /**
     * Wypełnienie labeli rankingu
     * @param place miejsce w rankingu
     * @param tekst tekst miejsca w rankingu
     */
    private void setPositionPointsRanking(JLabel place, String tekst){

        place.setFont(Resources.bigFont);
        place.setText(tekst);
        place.setBounds(0,0,369,99);
        place.setHorizontalTextPosition(JLabel.CENTER);
        place.setVerticalTextPosition(JLabel.CENTER);
        place.setHorizontalAlignment(JLabel.CENTER);
    }

}
