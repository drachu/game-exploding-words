package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Klasa odpowiedzialna za zasoby grym takie jak słowa, obrazy i dzwięki
 * @author Adam Dradrach
 */
public class Resources {

    /** Tablica zawierająca angielskie słowa */
    public static String[] englishWords = {"table", "chair", "book", "window", "bed", "clock", "earth", "computer",
    "rat", "cap", "sun", "key", "road", "house", "beer", "school", "green", "red", "black", "yellow", "purple",
    "white", "blue", "pink", "brown", "grey", "time", "year", "man", "woman", "life", "child", "children",
    "world", "family", "student", "hand", "week", "work", "night", "water", "room", "mother", "father", "money",
    "month", "eye", "word", "head", "friend", "hour", "parent", "office", "art"};
    /** Tablica zawierająca polskie słowa */
    public static String[] polishWords = {"stół", "krzesło", "książka", "okno", "łóżko", "zegar", "ziemia", "komputer",
    "szczur", "czapka", "słońce", "klucz", "droga", "dom", "piwo", "szkoła", "zielony", "czerwony", "czarny", "żółty", "fioletowy",
    "biały", "niebieski", "różowy", "brązowy", "szary", "czas", "rok", "mężczyzna", "kobieta", "życie", "dziecko", "dzieci",
    "świat", "rodzina", "uczeń", "dłoń", "tydzień", "praca", "noc", "woda", "pokój", "matka", "ojciec", "pieniądze",
    "miesiąc", "oko", "słowo", "głowa", "przyjaciel", "godzina", "rodzic", "biuro", "sztuka"};
    /** Grafika bomby H - grafika */
    public static Image bombImageH;
    /** Grafika bomby V - grafika */
    public static Image bombImageV;
    /** Logo gry, ikona - grafika */
    public static Image bombLogo;
    /** Tło rozgrywki 1 - grafika */
    public static Image skyBackground;
    /** Tło rozgrywki 2 - grafika */
    public static Image skyBackground2;
    /** Tło rozgrywki 3 - grafika */
    public static Image skyBackground3;
    /** Tło rozgrywki 4 - grafika */
    public static Image skyBackground4;
    /** Tło rozgrywki 5 - grafika */
    public static Image skyBackground5;
    /** Tło rozgrywki 6 - grafika */
    public static Image skyBackground6;
    /** Ikona gry w menu - grafika */
    public static Image bombIconImage;
    /** Dolny panel w rozgrywce - grafika*/
    public static Image bottomPanel;
    /** Grafika spadającej bomby */
    public static Image droppingBomb;
    /** Przycisk wyjścia z gry w rozgrywce - grafika */
    public static Image exitButtonSmall;
    /** Przycisk wyjścia z gry w rozgrywce po najechaniu myszką - grafika */
    public static Image exitButtonSmallEntered;
    /** Panel w rozgrywce, poziom zniszczenia 0 - grafika */
    public static Image playerPanel;
    /** Panel w rozgrywce, poziom zniszczenia 1 - grafika */
    public static Image playerPanel1;
    /** Panel w rozgrywce, poziom zniszczenia 2 - grafika */
    public static Image playerPanel2;
    /** Panel w rozgrywce, poziom zniszczenia 3 - grafika */
    public static Image playerPanel3;
    /** Menu  po najechaniu na przycisk PLAY - grafika */
    public static Image menuPanelPlay;
    /** Menu po najechaniu na przycisk RANKING - grafika*/
    public static Image menuPanelRanking;
    public static Image menuPanelExit;
    public static Image menuPanelBack;
    public static Image menuPanelBackEntered;
    /** Duży panel w rozgrywce, poziom zniszczenia 0 - grafika */
    public static Image playerBigPanel;
    /** Duży panel w rozgrywce, poziom zniszczenia 1 - grafika */
    public static Image playerBigPanel1;
    /** Duży panel w rozgrywce, poziom zniszczenia 2 - grafika */
    public static Image playerBigPanel2;
    /** Duży panel w rozgrywce, poziom zniszczenia 3 - grafika */
    public static Image playerBigPanel3;
    /** Sredni panel w rozgrywce, poziom zniszczenia 0 - grafika */
    public static Image playerMediumPanel;
    /** Sredni panel w rozgrywce, poziom zniszczenia 1 - grafika */
    public static Image playerMediumPanel1;
    /** Sredni panel w rozgrywce, poziom zniszczenia 2 - grafika */
    public static Image playerMediumPanel2;
    /** Sredni panel w rozgrywce, poziom zniszczenia 3 - grafika */
    public static Image playerMediumPanel3;
    /** Gif eksplozji po wpisaniu dobrego słowa 1 */
    public static Image explosion1;
    /** Gif eksplozji po wpisaniu dobrego słowa 2*/
    public static Image explosion1copy;
    /** Gif eksplozji upadku bomby*/
    public static Image explosion2;
    /** Prawe tło w menu - grafika */
    public static Image rightBackground;
    /** Lewe tło w menu - grafika */
    public static Image leftBackground;
    /** Menu porażki - grafika */
    public static Image loseMenu;
    /** Menu porażki po najechaniu myszką na przycisk MENU - grafika*/
    public static Image loseMenuMenuEntered;
    /** Menu porażki po najechaniu myszką na przycisk PLAY - grafika */
    public static Image loseMenuPlayEntered;
    /** Gif ognia */
    public static Image fire;
    /** Grafika kursora */
    public static Image cursorDefaultImage;
    /** Grafika przycisku PLAY */
    public static ImageIcon playButtonImage = new ImageIcon("images\\menuPanelPlay.png");
    /** Grafika przycisku PLAY po najechaniu */
    public static ImageIcon playButtonEnteredImage = new ImageIcon("images\\menuPanelPlayEntered.png");
    /** Grafika przycisku RANKING */
    public static ImageIcon rankingButtonImage = new ImageIcon("images\\menuPanelRanking.png");
    /** Grafika przycisku RANKING po najechaniu */
    public static ImageIcon rankingButtonEnteredImage = new ImageIcon("images\\menuPanelRankingEntered.png");
    /** Grafika przycisku WYJSCIE */
    public static ImageIcon exitButtonImage = new ImageIcon("images\\menuPanelExit.png");
    /** Grafika przycisku WYJSCIE po najechaniu */
    public static ImageIcon exitButtonEnteredImage = new ImageIcon("images\\menuPanelExitEntered.png");
    /** Grafika przycisku POWRÓT */
    public static ImageIcon backButtonImage = new ImageIcon("images\\menuPanelBack.png");
    /** Grafika przycisku POWRÓT po najechaniu */
    public static ImageIcon backButtonEnteredImage = new ImageIcon("images\\menuPanelBackEntered.png");
    /** Grafika przyciku włączonej muzyki menu */
    public static ImageIcon musicEnabledImage = new ImageIcon("images\\musicEnabledButton.png");
    /** Grafika przycisku wyłączonej muzyki menu */
    public static ImageIcon musicDisabledImage = new ImageIcon("images\\musicDisabledButton.png");
    /** Grafika przycisku włączonej muzyki po najechaniu */
    public static ImageIcon musicButtonEnteredImage = new ImageIcon("images\\musicButtonEntered.png");
    /** Grafika przycisku wyłączonej muzyki po najechaniu */
    public static ImageIcon musicDisabledEnteredImage = new ImageIcon("images\\musicDisabledButtonEntered.png");
    /** Dzwięk klikania myszką */
    public static File clickSound;
    /** Dzwięk porażki */
    public static File loseSound;
    /** Dzwięk eksplozji po wpisaniu dobrego słowa */
    public static File explosionSound;
    /** Dzwięk eksplozji po upadku na ziemię */
    public static File explosionSound2;
    /** Dzwięk rozpoczęcia rozgrywki */
    public static File startGameSound;
    /** Dzwięk intro */
    public static File introPlayingSound;
    /** Muzyka w głównym menu */
    public static File mainMusicSound;
    /** Dzwięk straty życia */
    public static File liveDownSound;
    /** Dzwięk wpisania złej litery */
    public static File badLetterSound;
    /** Plik niestandardowej dużej czcionki */
    public static Font bigFont = new Font("ARCADECLASSICPOL",Font.BOLD,40);
    /** Plik niestandardowej małej czcionki */
    public static Font smallFont = new Font("ARCADECLASSICPOL",Font.BOLD,20);
    /** Plik niestandardowej bardzo małej dzcionki */
    public static Font verySmallFont = new Font("ARCADECLASSICPOL",Font.BOLD,2);
    /** Domyślny kursor */
    public static Cursor cursorDefault;
    /** Niewidzialny kursor */
    public static Cursor cursorBlank;

    /**
     * Załadowania plików zasobów do gry
     */
public static void loadInitial(){

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    cursorDefaultImage = toolkit.getImage("images\\cursor.png");
    Point cursorPoint = new Point(0,0);
    cursorDefault = toolkit.createCustomCursor(cursorDefaultImage, cursorPoint, "cursorDefault");

    bombImageH = loadImage("images\\bombH.png");
    bombImageV = loadImage("images\\bombV.png");
    bombIconImage = loadImage("images\\bombIcon.png");
    bombLogo = loadImage("images\\bombLogo.png");
    skyBackground = loadImage("images\\sky.jpg");
    skyBackground2 = loadImage("images\\sky2.png");
    skyBackground3 = loadImage("images\\sky3.png");
    skyBackground4 = loadImage("images\\sky4.png");
    skyBackground5 = loadImage("images\\sky5.png");
    skyBackground6 = loadImage("images\\sky6.png");
    bottomPanel = loadImage("images\\bottomPanel.png");
    droppingBomb = loadImage("images\\droppingBomb.png");
    exitButtonSmall = loadImage("images\\exitButtonSmall.png");
    exitButtonSmallEntered = loadImage("images\\exitButtonSmallEntered.png");
    playerPanel = loadImage("images\\playerSmallPanel.png");
    playerPanel1 = loadImage("images\\playerSmallPanel1.png");
    playerPanel2 = loadImage("images\\playerSmallPanel2.png");
    playerPanel3 = loadImage("images\\playerSmallPanel3.png");
    playerBigPanel = loadImage("images\\playerBigPanel.png");
    playerBigPanel1 = loadImage("images\\playerBigPanel1.png");
    playerBigPanel2 = loadImage("images\\playerBigPanel2.png");
    playerBigPanel3 = loadImage("images\\playerBigPanel3.png");
    playerMediumPanel = loadImage("images\\playerMediumPanel.png");
    playerMediumPanel1 = loadImage("images\\playerMediumPanel1.png");
    playerMediumPanel2 = loadImage("images\\playerMediumPanel2.png");
    playerMediumPanel3 = loadImage("images\\playerMediumPanel3.png");
    explosion1 = loadImage("images\\explosion1.gif");
    explosion1copy = loadImage("images\\explosion1copy.gif");
    explosion2 = loadImage("images\\explosion2.gif");
    menuPanelPlay = loadImage("images\\menuPanelPlay.png");
    menuPanelRanking = loadImage("images\\menuPanelRanking.png");
    menuPanelExit = loadImage("images\\menuPanelExit.png");
    menuPanelBack = loadImage("images\\menuPanelBack.png");
    menuPanelBackEntered = loadImage("images\\menuPanelBackEntered.png");
    leftBackground = loadImage("images\\menuLeft.png");
    rightBackground = loadImage("images\\menuRight.png");
    loseMenu = loadImage("images\\loseMenu.png");
    loseMenuMenuEntered = loadImage("images\\loseMenuMenuEntered.png");
    loseMenuPlayEntered = loadImage("images\\loseMenuPlayEntered.png");
    fire = loadImage("images\\fire.gif");

    clickSound = new File("sounds\\clickSound.wav");
    loseSound = new File("sounds\\loseSound.wav");
    explosionSound = new File("sounds\\explosionSound.wav");
    explosionSound2 = new File("sounds\\explosionSound2.wav");
    startGameSound = new File("sounds\\startGameSound.wav");
    introPlayingSound = new File("sounds\\introPlayingSound.wav");
    mainMusicSound = new File("sounds\\mainMusicSound.wav");
    liveDownSound = new File("sounds\\liveDownSound.wav");
    badLetterSound = new File("sounds\\badLetterSound.wav");
}

    /**
     * Funkcja za pomocą której ładowanie plików jest uproszczone
     * @param fileName nazwa pliku, ściezka
     * @return wczytany plik
     */
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }


}
