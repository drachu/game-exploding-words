package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za elementy głownego menu
 * @author Adam Dradrach
 */
public class MainMenuPanel extends JPanel {
    /** Szerokość ekranu gry */
    private final int screenWidth;
    /** Wysokość ekranu gry */
    private final int screenHeight;

    /** Prawy panel menu */
    public final JPanel rightPanel = new JPanel();
    /** Lewy panel gry */
    public final JPanel leftPanel = new JPanel();
    /** Srodkowy panel gry */
    public final JPanel middlePanel = new JPanel();
    /** Logo gry */
    public final JLabel gameLogo = new JLabel();
    /** Lewe tło menu */
    public final JLabel leftBackground = new JLabel();
    /** Prawe tło menu */
    public final JLabel rightBackground = new JLabel();
    /** Pole tekstowe do wpisywania nazwy użytkownika */
    public final JTextField playerNickTextField = new JTextField();
    /** Opcja zaznaczenia trybu 1 */
    public final JRadioButton englishToPolishModeBox = new JRadioButton();
    /** Opcja zaznaczenia trybu 2 */
    public final JRadioButton polishToEnglishModeBox = new JRadioButton();
    /** Przycisk rozpoczęcia rozgrywki */
    public final JButton playButton = new JButton();
    /** Przycisk przejścia do rankingu */
    public final JButton rankingButton = new JButton();
    /** Przycisk wyjścia */
    public final JButton exitButton = new JButton();
    /** Przycisk wyciszenia muzyki w menu głównym */
    public final JButton musicButton = new JButton();

    /**
     * Konstruktor klasy, określający główne parametry
     * @param screenWidth
     * @param screenHeight
     */
    public MainMenuPanel(int screenWidth, int screenHeight) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setBackground(Color.pink);
        this.setLayout(null);

        setStartingMenu();
    }


    /**
     * Stworzenie elementów menu
     */
    private void setStartingMenu(){

        //settings for menu
        ButtonGroup translateBox = new ButtonGroup();
        translateBox.add(englishToPolishModeBox);
        translateBox.add(polishToEnglishModeBox);
        englishToPolishModeBox.setFont(Resources.smallFont);
        englishToPolishModeBox.setText("angielski na polski");
        englishToPolishModeBox.setBounds((screenWidth/3)/2-120, screenHeight-80, 300, 30);
        englishToPolishModeBox.setBackground(Color.black);
        englishToPolishModeBox.setForeground(Color.white);
        englishToPolishModeBox.setSelected(true);
        polishToEnglishModeBox.setFont(Resources.smallFont);
        polishToEnglishModeBox.setText("polski na angielski");
        polishToEnglishModeBox.setBounds((screenWidth/3)/2-120, screenHeight-50, 300, 30);
        polishToEnglishModeBox.setBackground(Color.black);
        polishToEnglishModeBox.setForeground(Color.white);

        playerNickTextField.setBounds((screenWidth/3)/2-100, screenHeight/2-175, 200, 50);
        playerNickTextField.setFont(Resources.smallFont);
        playerNickTextField.setHorizontalAlignment(JTextField.CENTER);
        playerNickTextField.isFocusable();
        playerNickTextField.setDocument(new JTextFieldLimit(11));
        playerNickTextField.setText("Gracz");

        ImageIcon icon = new ImageIcon(Resources.bombLogo);
        gameLogo.setIcon(icon);
        gameLogo.setHorizontalAlignment(JLabel.CENTER);
        gameLogo.setVerticalAlignment(JLabel.CENTER);
        gameLogo.setBackground(Color.black);
        gameLogo.setOpaque(true);
        gameLogo.setBounds(0, -75, screenWidth/3, 300);


        playButton.setIcon(Resources.playButtonImage);
        playButton.setBounds(((screenWidth/3)/2-100), (screenHeight/2-100), 198, 100);
        playButton.setBorderPainted(false);
        playButton.setName("playButton");

        rankingButton.setIcon(Resources.rankingButtonImage);

        rankingButton.setBounds(((screenWidth/3)/2-100), (screenHeight/2+50), 198, 100);
        rankingButton.setName("rankingButton");

        exitButton.setIcon(Resources.exitButtonImage);
        exitButton.setBounds(((screenWidth/3)/2-100), (screenHeight/2+200), 198, 100);
        exitButton.setBorderPainted(false);
        exitButton.setName("exitButton");

        musicButton.setIcon(Resources.musicEnabledImage);
        musicButton.setBounds(screenWidth/3-Resources.musicEnabledImage.getIconWidth()-5, screenHeight-Resources.musicEnabledImage.getIconHeight(),82,84);
        musicButton.setBorderPainted(false);
        musicButton.setOpaque(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setName("musicButton");

        leftPanel.setBounds(0, 0, screenWidth/3, screenHeight);
        leftPanel.setBackground(Color.lightGray);
        leftPanel.setLayout(null);
        leftPanel.add(leftBackground);

        ImageIcon leftMenu = new ImageIcon(Resources.leftBackground);
        leftBackground.setBounds(0, 0, screenWidth/3, screenHeight);
        leftBackground.setIcon(leftMenu);
        leftBackground.setBackground(Color.red);
        leftBackground.setOpaque(true);

        middlePanel.setBounds((screenWidth/3), 0, screenWidth/3, screenHeight);
        middlePanel.setBackground(Color.black);
        middlePanel.setLayout(null);
        middlePanel.add(gameLogo);
        middlePanel.add(englishToPolishModeBox);
        middlePanel.add(polishToEnglishModeBox);
        middlePanel.add(playButton);
        middlePanel.add(rankingButton);
        middlePanel.add(exitButton);
        middlePanel.add(playerNickTextField);

        rightPanel.setBounds((screenWidth/3)*2 , 0, screenWidth/3, screenHeight);
        rightPanel.setBackground(Color.lightGray);
        rightPanel.setLayout(null);
        rightPanel.add(rightBackground);

        ImageIcon rightMenu = new ImageIcon(Resources.rightBackground);
        rightBackground.setBounds(0, 0, screenWidth/3, screenHeight);
        rightBackground.setIcon(rightMenu);
        rightBackground.setBackground(Color.red);
        rightBackground.setOpaque(true);
        rightBackground.add(musicButton);

        this.add(rightPanel);
        this.add(leftPanel);
        this.add(middlePanel);
    }
}
