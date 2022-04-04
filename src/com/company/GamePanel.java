package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Główna klasa gry, dziedziczy po klasie JPanel
 * implementuje interfejsy ActionListenera, FocusListenera, KeyListenera, MouseListenera oraz MouseMotionListenera
 */
public class GamePanel extends JPanel implements ActionListener, FocusListener, KeyListener, MouseListener, MouseMotionListener{

    /** Obiekt słów */
    public Words w = new Words();
    /** Obiekt informacji o grze */
    public GameStatus gStatus;
    /** Obiekt bomb */
    public Bombs b;
    /** Odtwarzacza */
    public SoundPlayer sPlayer = new SoundPlayer();
    /** Obiekt menu głównego */
    public MainMenuPanel mMPanel;
    public RankingPanel rPanel;

    /** Czy rozgrywka jest uruchomiona */
    private boolean gamePlaying = false;
    /** Czy główne menu jest włączone */
    private boolean mainMenu = true;
    /** Czy menu porażki jest włączone */
    private boolean loseMenu = false;
    /** Czy została wpisana zła litera w pierwszym słowe */
    private boolean wrongLetter1 = false;
    /** Czy zostałą wpisana zła litera w drugim słowie */
    private boolean wrongLetter2 = false;
    /** Wejście do menu porażki */
    private int loseMenuEntered;
    /** Najechanie na przycisk wyjścia w rozgrywce */
    private boolean exitButtonSmallEntered = false;
    /** Szerokośc ekranu gry */
    private final int screenWidth;
    /** Wysokośc ekranu gry */
    private final int screenHeight;
    /** Częstotliwość odtwarzania ekranu - 100 ms */
    Timer timer1 = new Timer(100, this);

    /**
     * Zdefiniowanie panelu gry - główny konstruktor
     * @param screenWidth szerokość ekranu
     * @param screenHeight wysokość ekranu
     * @throws UnsupportedAudioFileException niewłaściwy format pliku audio
     * @throws IOException inny błąd
     * @throws LineUnavailableException nie odnaleziono
     */
    GamePanel(int screenWidth, int screenHeight) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        b = new Bombs();
        gStatus = new GameStatus();

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setLayout(null);


        Resources.loadInitial();
        this.setCursor(Resources.cursorDefault);
        sPlayer.createMenuMusic();

        mMPanel = new MainMenuPanel(screenWidth, screenHeight);
        mMPanel.playerNickTextField.addFocusListener(this);
        mMPanel.playButton.addActionListener(this);
        mMPanel.playButton.addMouseMotionListener(this);
        mMPanel.rankingButton.setBorderPainted(false);
        mMPanel.rankingButton.addActionListener(this);
        mMPanel.rankingButton.addMouseMotionListener(this);
        mMPanel.exitButton.addActionListener(this);
        mMPanel.exitButton.addMouseMotionListener(this);
        mMPanel.musicButton.addActionListener(this);
        mMPanel.musicButton.addMouseMotionListener(this);
        this.add(mMPanel);

        rPanel = new RankingPanel(screenWidth, screenHeight);
        rPanel.goBackButton.addMouseMotionListener(this);
        rPanel.goBackButton.addActionListener(this);
        rPanel.updateRanking(gStatus.records[0], gStatus.recordsPlayerName[0], gStatus.records[1], gStatus.recordsPlayerName[1], gStatus.records[2], gStatus.recordsPlayerName[2],  gStatus.records[3], gStatus.recordsPlayerName[3],  gStatus.records[4], gStatus.recordsPlayerName[4]);
        mMPanel.add(rPanel);
    }

    /**
     * Działania związane z każdym tickiem zegara
     * @param e wydarzenie
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //sprawdzenie czy powinna grać muzyka, zastopowanie jej
        if (!mainMenu){
            sPlayer.menuMusic.setMicrosecondPosition(0);
            sPlayer.menuMusic.stop();
        }
        if (mMPanel.musicButton.getIcon().toString().equals("images\\musicDisabledButton.png")){
            sPlayer.menuMusic.stop();
        }

   if (gamePlaying){//działania w czasie rozgrywki
        //sprawdzenie czy życia spadną do zera, zakończenie rozgrywki i włączenie menu porażki
       if (gStatus.GAMELIVES==0){
           if (b.explodingTime == Bombs.explosionTime2 -1){
               SoundPlayer.playLoseSound();
               gamePlaying = false;
               loseMenu = true;
           }
       }
       if (gStatus.GAMELIVES<0) gStatus.GAMELIVES=0;//zabezpieczenie aby nie wyświetlać wartości ujemnych
       //opóźnienie bomby 2, wytłumaczone w klasie Bombs
       b.delayBomb2();

        //sprawdzenie czy na dół ekranu spadła bomba 1 i podjęcie działań
       if (b.droppingBombPositionY[0] >= screenHeight-20){
           if (gStatus.GAMEMODE == 0) gStatus.MISSEDWORD = w.word1POLISH;
           if (gStatus.GAMEMODE == 1) gStatus.MISSEDWORD = w.word1ENGLISH;
           SoundPlayer.playExplosionSound2();
           gStatus.GAMELIVES--;
           b.bombExplodedX = b.droppingBombPositionX[b.startingPosition[0]];
           b.bombExploded = true;
           b.restartBomb1();
           w.resetWord1();
       }
       //sprawdzenie czy na dół ekranu spadła bomba 2 i podjęcie działań
       if (b.droppingBombPositionY[1] >= screenHeight-20){
           if (gStatus.GAMEMODE == 0) gStatus.MISSEDWORD = w.word2POLISH;
           if (gStatus.GAMEMODE == 1) gStatus.MISSEDWORD = w.word2ENGLISH;
           gStatus.GAMELIVES--;
           SoundPlayer.playExplosionSound2();
           b.bombExplodedX = b.droppingBombPositionX[b.startingPosition[1]];
           b.bombExploded = true;
           b.restartBomb2();
           w.resetWord2();
           b.bomb2delayTime=1;
       }

       //zwiększanie prędkości spadania bomb wraz z większą ilością zdobytych punktów
       if (gStatus.POINTS<=100){
           b.droppingBombPositionY[0] += b.yVelocity;
           b.droppingBombPositionY[1] += b.yVelocity;
       }
       if (gStatus.POINTS>100 && gStatus.POINTS<=200){
           b.droppingBombPositionY[0] += b.yVelocity+2;
           b.droppingBombPositionY[1] += b.yVelocity+2;
       }
       if (gStatus.POINTS>200 && gStatus.POINTS<=300){
           b.droppingBombPositionY[0] += b.yVelocity+4;
           b.droppingBombPositionY[1] += b.yVelocity+4;
       }
       if (gStatus.POINTS>300 && gStatus.POINTS<=400){
           b.droppingBombPositionY[0] += b.yVelocity+6;
           b.droppingBombPositionY[1] += b.yVelocity+6;
       }
       if (gStatus.POINTS>400 && gStatus.POINTS<=500){
           b.droppingBombPositionY[0] += b.yVelocity+8;
           b.droppingBombPositionY[1] += b.yVelocity+8;
       }
       if (gStatus.POINTS>500){
           b.droppingBombPositionY[0] += b.yVelocity+10;
           b.droppingBombPositionY[1] += b.yVelocity+10;
       }

       b.checkBombPosition();

       //zarządzanie czasem wybuchającej bomby 1 po przetłumaczeniu słowa
       if (b.bombExplosion[0] == 1) b.explosionCurrentTime[0]++;
       if (b.explosionCurrentTime[0]== Bombs.explosionTime) {
           Resources.explosion1.flush();
           b.explosionCurrentTime[0] = 0;
           b.bombExplosion[0] = 0;
       }
       //zarządzanie czasem wybuchającej bomby 2 po przetłumaczeniu słowa
       if (b.bombExplosion[1] == 1) b.explosionCurrentTime[1]++;
       if (b.explosionCurrentTime[1]== Bombs.explosionTime) {
           Resources.explosion1copy.flush();
           b.explosionCurrentTime[1] = 0;
           b.bombExplosion[1] = 0;
       }
       //zresetowanie danych wybuchającej bomby, zwolnienie zasobów
       if (b.bombExploded) b.explodingTime++;
       if (b.explodingTime== Bombs.explosionTime2){
           SoundPlayer.playLiveDownSound();
           Resources.explosion2.flush();
           b.bombExploded = false;
           b.explodingTime = 0;
       }


       //POPRAWNOSC SLOWA
       if (gStatus.GAMEMODE == 0) {
           for(int i = 0; i<w.writtenWord1.length();i++){
               if (w.writtenWord1.length()<=w.word1POLISH.length()){
                   if (w.writtenWord1.charAt(i) != w.word1POLISH.charAt(i)){
                       w.translatedWord = "";
                       w.writtenWord1 = "";
                       wrongLetter1 = true;
                       break;
                   }
               }
               else {
                   w.writtenWord1 = "";}
           }
           for(int i = 0; i<w.writtenWord2.length();i++){
               if (w.writtenWord2.length()<=w.word2POLISH.length()){
                   if (w.writtenWord2.charAt(i) != w.word2POLISH.charAt(i)){
                       w.translatedWord = "";
                       w.writtenWord2 = "";
                       wrongLetter2 = true;
                       break;
                   }
               }
               else {
                   w.writtenWord2 = "";}
           }
           //does bomb 1 word equals written word - actions
           if (w.writtenWord1.equals(w.word1POLISH)){
               SoundPlayer.playExplosionSound();
               b.setBombExplosionPosition1();
               b.bombExplosion[0] = 1;
               w.translatedWord = w.writtenWord1;
               w.writtenWord1 = "";
               w.writtenWord2 = "";
               b.restartBomb1();
               w.resetWord1();
               gStatus.POINTS+=10;
           }
           //does bomb 2 word equals written word - actions
           if (w.writtenWord2.equals(w.word2POLISH)){
               SoundPlayer.playExplosionSound();
               b.setBombExplosionPosition2();
               b.bombExplosion[1] = 1;
               w.translatedWord = w.writtenWord2;
               w.writtenWord1 = "";
               w.writtenWord2 = "";
               b.restartBomb2();
               b.bomb2delayTime=1;
               w.resetWord2();
               gStatus.POINTS+=10;
           }
       }
       //WORD CORRECTNESS CHECK IN MODE POLISH - ENGLISH
       if (gStatus.GAMEMODE == 1) {
           for(int i = 0; i<w.writtenWord1.length();i++){
               if (w.writtenWord1.length()<=w.word1ENGLISH.length()){
                   if (w.writtenWord1.charAt(i) != w.word1ENGLISH.charAt(i)){
                       w.translatedWord = "";
                       w.writtenWord1 = "";
                       wrongLetter1 = true;
                       break;
                   }
               }
               else w.writtenWord1 = "";
           }
           for(int i = 0; i<w.writtenWord2.length();i++){
               if (w.writtenWord2.length()<=w.word2ENGLISH.length()){
                   if (w.writtenWord2.charAt(i) != w.word2ENGLISH.charAt(i)){
                       w.translatedWord = "";
                       w.writtenWord2 = "";
                       wrongLetter2 = true;
                       break;
                   }
               }
               else w.writtenWord2 = "";
           }
           //does bomb 1 word equals written word - actions
           if (w.writtenWord1.equals(w.word1ENGLISH)){
               SoundPlayer.playExplosionSound();
               b.setBombExplosionPosition1();
               b.bombExplosion[0] = 1;
               w.translatedWord = w.writtenWord1;
               w.writtenWord1 = "";
               w.writtenWord2 = "";
               b.restartBomb1();
               w.resetWord1();
               gStatus.POINTS+=10;
           }
           //does bomb 2 word equals written word - actions
           if (w.writtenWord2.equals(w.word2ENGLISH)){
               SoundPlayer.playExplosionSound();
               b.setBombExplosionPosition2();
               b.bombExplosion[1] = 1;
               w.translatedWord = w.writtenWord2;
               w.writtenWord1 = "";
               w.writtenWord2 = "";
               b.restartBomb2();
               b.bomb2delayTime=1;
               w.resetWord2();
               gStatus.POINTS+=10;
           }
       }
       if (wrongLetter1 && wrongLetter2){
           SoundPlayer.playBadLetterSound();
       }
       wrongLetter1 = false;
       wrongLetter2 = false;
       //screen refresh
       repaint();
   }
        //MENU - CLICK ON PLAY BUTTON
        if (e.getSource()==mMPanel.playButton){

            SoundPlayer.playIntroPlayingSound();
            b.restartBombs();
            w.resetWords();
            w.translatedWord = "";
            gStatus.resetGame();
            mMPanel.setVisible(false);
            mMPanel.playButton.setIcon(Resources.playButtonImage);

            timer1.start();
            if (mMPanel.englishToPolishModeBox.isSelected()) gStatus.GAMEMODE = 0;
            else gStatus.GAMEMODE = 1;
            gStatus.PLAYERNAME = mMPanel.playerNickTextField.getText();
            mainMenu = false;
            gamePlaying = true;
        }
        //MENU - CLICK ON RANKING BUTTON
        if (e.getSource()==mMPanel.rankingButton){
            SoundPlayer.playClickSound();
            gamePlaying = false;
            mMPanel.middlePanel.setVisible(false);
            rPanel.setVisible(true);
            rPanel.updateRanking(gStatus.records[0], gStatus.recordsPlayerName[0], gStatus.records[1], gStatus.recordsPlayerName[1], gStatus.records[2], gStatus.recordsPlayerName[2],  gStatus.records[3], gStatus.recordsPlayerName[3],  gStatus.records[4], gStatus.recordsPlayerName[4]);
        }
        //MENU - CLICK ON GO BACK TO MENU BUTTON
        if (e.getSource()==rPanel.goBackButton){
            SoundPlayer.playClickSound();
            gamePlaying = false;
            rPanel.setVisible(false);
            mMPanel.middlePanel.setVisible(true);
        }
        if (e.getSource()==mMPanel.musicButton){
            SoundPlayer.playClickSound();
            if (sPlayer.menuMusic.isRunning()) {
                mMPanel.musicButton.setIcon(Resources.musicDisabledEnteredImage);
                sPlayer.menuMusic.stop();}
            else {
                mMPanel.musicButton.setIcon(Resources.musicButtonEnteredImage);
                sPlayer.menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
                sPlayer.menuMusic.start();
            }
        }
        //MENU - CLICK ON EXIT
        if (e.getSource()==mMPanel.exitButton){
            gStatus.saveGame(gStatus.saveFileName);
            System.exit(0);
        }

    }

    /**
     * Obranie focusu przed coś, w tym przypadku pole tekstowe
     * @param e wydarzenie
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==mMPanel.playerNickTextField){
            mMPanel.playerNickTextField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    /**
     * Rysowanie elementów gry
     * @param gs element graficzny
     */
    @Override
    protected void paintComponent(Graphics gs) {
        Graphics2D g = (Graphics2D) gs;

        if (gamePlaying){
            //graphics antialiasing implementation
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            //BACKGROUND
            if (gStatus.POINTS <= 100) g.drawImage(Resources.skyBackground, 0, 0, null);
            if (gStatus.POINTS > 100 && gStatus.POINTS <=200) g.drawImage(Resources.skyBackground2, 0, 0, null);
            if (gStatus.POINTS > 200 && gStatus.POINTS <=300) g.drawImage(Resources.skyBackground3, 0, 0, null);
            if (gStatus.POINTS > 300 && gStatus.POINTS <=400) g.drawImage(Resources.skyBackground4, 0, 0, null);
            if (gStatus.POINTS > 400 && gStatus.POINTS <=500) g.drawImage(Resources.skyBackground5, 0, 0, null);
            if (gStatus.POINTS > 500 && gStatus.POINTS <=600) g.drawImage(Resources.skyBackground4, 0, 0, null);
            if (gStatus.POINTS > 600 && gStatus.POINTS <=700) g.drawImage(Resources.skyBackground3, 0, 0, null);
            if (gStatus.POINTS > 700 && gStatus.POINTS <=800) g.drawImage(Resources.skyBackground2, 0, 0, null);
            if (gStatus.POINTS > 800 && gStatus.POINTS <=900) g.drawImage(Resources.skyBackground, 0, 0, null);
            if (gStatus.POINTS > 900) g.drawImage(Resources.skyBackground2, 0, 0, null);

            //DESTROYING HUD
            if (gStatus.GAMELIVES==3){
                g.drawImage(Resources.playerMediumPanel, screenWidth/2-Resources.playerMediumPanel.getWidth(null)/2, screenHeight-Resources.playerBigPanel.getHeight(null)-80, null);
                g.drawImage(Resources.playerBigPanel, Resources.playerPanel.getWidth(null)+5, screenHeight-Resources.playerBigPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES==2){
                g.drawImage(Resources.playerMediumPanel1, screenWidth/2-Resources.playerMediumPanel.getWidth(null)/2, screenHeight-Resources.playerBigPanel.getHeight(null)-80, null);
                g.drawImage(Resources.playerBigPanel1, Resources.playerPanel.getWidth(null)+5, screenHeight-Resources.playerBigPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES==1){
                g.drawImage(Resources.playerMediumPanel2, screenWidth/2-Resources.playerMediumPanel.getWidth(null)/2, screenHeight-Resources.playerBigPanel.getHeight(null)-80, null);
                g.drawImage(Resources.playerBigPanel2, Resources.playerPanel.getWidth(null)+5, screenHeight-Resources.playerBigPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES<=0){
                g.drawImage(Resources.playerMediumPanel3, screenWidth/2-Resources.playerMediumPanel.getWidth(null)/2, screenHeight-Resources.playerBigPanel.getHeight(null)-80, null);
                g.drawImage(Resources.playerBigPanel3, Resources.playerPanel.getWidth(null)+5, screenHeight-Resources.playerBigPanel.getHeight(null), null);
            }
            g.setFont(Resources.smallFont);
            g.drawString("OSTATNIA  NIEZDEZAKTYWOWANA  BOMBA", Resources.playerPanel.getWidth(null)+130, screenHeight - 100);
            g.setFont(Resources.bigFont);
            if (w.writtenWord1.length()>=1 || w.writtenWord2.length()>=1){
                if (w.writtenWord1.length()>w.writtenWord2.length()) { g.drawString(w.writtenWord1+"...", screenWidth/2-w.writtenWord1.length()*12,screenHeight - 180);}
                else if (w.writtenWord1.length()<w.writtenWord2.length()) { g.drawString(w.writtenWord2+"...", screenWidth/2-w.writtenWord2.length()*12,screenHeight - 180);}
            }
            else { g.drawString(w.translatedWord, screenWidth/2-w.translatedWord.length()*12,screenHeight - 180);}


            g.drawString(gStatus.MISSEDWORD, screenWidth/2-gStatus.MISSEDWORD.length()*12,screenHeight - 60);

            //PLAYER NAME
            g.drawImage(Resources.playerPanel, 0, 0, null);
            g.setFont(Resources.smallFont);
            g.drawString("GRACZ", 120, 50);
            g.setFont(Resources.bigFont);
            g.drawString(gStatus.PLAYERNAME, Resources.playerPanel.getWidth(null)/2-gStatus.PLAYERNAME.length()*12, 90);

            //ANIMATION - DROPPING BOMB
            g.drawImage(Resources.droppingBomb, b.droppingBombPositionX[b.startingPosition[0]], b.droppingBombPositionY[0], null);
            g.setFont(Resources.bigFont);
            if (gStatus.GAMEMODE == 0) g.drawString(w.word1ENGLISH,b.droppingBombPositionX[b.startingPosition[0]]+130, b.droppingBombPositionY[0]+75);
            if (gStatus.GAMEMODE == 1) g.drawString(w.word1POLISH,b.droppingBombPositionX[b.startingPosition[0]]+130, b.droppingBombPositionY[0]+75);
            g.drawImage(Resources.droppingBomb, b.droppingBombPositionX[b.startingPosition[1]], b.droppingBombPositionY[1], null);
            g.setFont(Resources.bigFont);
            if (gStatus.GAMEMODE == 0) g.drawString(w.word2ENGLISH,b.droppingBombPositionX[b.startingPosition[1]]+130, b.droppingBombPositionY[1]+75);
            if (gStatus.GAMEMODE == 1) g.drawString(w.word2POLISH,b.droppingBombPositionX[b.startingPosition[1]]+130, b.droppingBombPositionY[1]+75);

            //DESTROYING HUD
            if (gStatus.GAMELIVES==3){
                g.drawImage(Resources.playerPanel, 0, screenHeight-Resources.playerPanel.getHeight(null), null);
                g.drawImage(Resources.playerPanel, screenWidth-Resources.playerPanel.getWidth(null),screenHeight-Resources.playerPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES==2){
                g.drawImage(Resources.playerPanel1, 0, screenHeight-Resources.playerPanel.getHeight(null), null);
                g.drawImage(Resources.playerPanel1, screenWidth-Resources.playerPanel.getWidth(null),screenHeight-Resources.playerPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES==1){
                g.drawImage(Resources.playerPanel2, 0, screenHeight-Resources.playerPanel.getHeight(null), null);
                g.drawImage(Resources.playerPanel2, screenWidth-Resources.playerPanel.getWidth(null),screenHeight-Resources.playerPanel.getHeight(null), null);
            }
            if (gStatus.GAMELIVES<=0){
                g.drawImage(Resources.playerPanel3, 0, screenHeight-Resources.playerPanel.getHeight(null), null);
                g.drawImage(Resources.playerPanel3, screenWidth-Resources.playerPanel.getWidth(null),screenHeight-Resources.playerPanel.getHeight(null), null);
            }

            //EXIT BUTTON
             if (!exitButtonSmallEntered) {g.drawImage(Resources.exitButtonSmall, screenWidth-Resources.exitButtonSmall.getWidth(null), 0, null);}
             if (exitButtonSmallEntered) {g.drawImage(Resources.exitButtonSmallEntered, screenWidth-Resources.exitButtonSmall.getWidth(null), 0, null);}

            //PLAYER POINTS
            g.setFont(Resources.smallFont);
            g.drawString("PUNKTY", 110, screenHeight-100);
            g.setFont(Resources.bigFont);
            g.drawString(String.valueOf(gStatus.POINTS), Resources.playerPanel.getWidth(null)/2-String.valueOf(gStatus.POINTS).length()*10, screenHeight-60);

            //HEALTH POINTS
            g.setFont(Resources.smallFont);
            g.drawString("PUNKTY ZDROWIA", screenWidth-220, screenHeight-100);
            g.setFont(Resources.bigFont);
            g.drawString(String.valueOf(gStatus.GAMELIVES), screenWidth-154, screenHeight-60);

            //ANIMATION - EXPLOSION AFTER WORD TRANSLATION
            if (b.bombExplosion[0] == 1) g.drawImage(Resources.explosion1, b.explodingBombX[0]-25, b.explodingBombY[0]-125, null);
            if (b.bombExplosion[1] == 1) g.drawImage(Resources.explosion1copy, b.explodingBombX[1]-25, b.explodingBombY[1]-125, null);

            //ANIMATION - EXPLOSION AFTER BOMB TOUCHING GROUND
            if (b.bombExploded)g.drawImage(Resources.explosion2, b.bombExplodedX, screenHeight-250, null);
        }
        //showing lose menu
        if (loseMenu){
            if (loseMenuEntered == 0){g.drawImage(Resources.loseMenu, screenWidth/2-Resources.loseMenu.getWidth(null)/2, screenHeight/2-Resources.loseMenu.getHeight(null)/2, null);}
            if (loseMenuEntered == 1){g.drawImage(Resources.loseMenuMenuEntered, screenWidth/2-Resources.loseMenu.getWidth(null)/2, screenHeight/2-Resources.loseMenu.getHeight(null)/2, null);}
            if (loseMenuEntered == 2){g.drawImage(Resources.loseMenuPlayEntered, screenWidth/2-Resources.loseMenu.getWidth(null)/2, screenHeight/2-Resources.loseMenu.getHeight(null)/2, null);}
            g.setFont(Resources.bigFont);
            if (gStatus.POINTS>= gStatus.records[4]) g.drawString("NOWY REKORD", 475, 375);

        }
    }

    /**
     * Wciśnięcie przycisku
     * @param e wydarzenie
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (gamePlaying){
            w.writtenWord1+=String.valueOf(e.getKeyChar());
            w.writtenWord2+=String.valueOf(e.getKeyChar());
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Kliknięcie myszką
     * @param e wydarzenie
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gamePlaying){
            //reaction on click on exit button while playing
            if(e.getX()>(screenWidth-Resources.exitButtonSmall.getWidth(null)) && e.getY()<(Resources.exitButtonSmall.getHeight(null))){
                SoundPlayer.playClickSound();
                mainMenu = true;
                gamePlaying = false;
                gStatus.checkRecord();
                gStatus.resetGame();
                mMPanel.setVisible(true);
                exitButtonSmallEntered = false;
                sPlayer.menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
                sPlayer.menuMusic.start();
            }
        }
        if (loseMenu){
            //click on menu button on lose menu
            if (e.getX()>(355) && e.getY()>(410) && e.getX()<(555) && e.getY()<(510)){
                //MENU
                gStatus.checkRecord();
                rPanel.updateRanking(gStatus.records[0], gStatus.recordsPlayerName[0], gStatus.records[1], gStatus.recordsPlayerName[1], gStatus.records[2], gStatus.recordsPlayerName[2],  gStatus.records[3], gStatus.recordsPlayerName[3],  gStatus.records[4], gStatus.recordsPlayerName[4]);
                gStatus.resetGame();
                SoundPlayer.playClickSound();
                mMPanel.setVisible(true);
                loseMenu = false;
                mainMenu = true;
                timer1.start();
                sPlayer.menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
                sPlayer.menuMusic.start();
            }
            //click on play again button on lose menu
            if (e.getX()>(645) && e.getY()>(410) && e.getX()<(845) && e.getY()<(510)){
                //PLAY
                timer1.start();
                SoundPlayer.playIntroPlayingSound();
                b.restartBombs();
                w.resetWords();
                w.translatedWord = "";
                gStatus.checkRecord();
                rPanel.updateRanking(gStatus.records[0], gStatus.recordsPlayerName[0], gStatus.records[1], gStatus.recordsPlayerName[1], gStatus.records[2], gStatus.recordsPlayerName[2],  gStatus.records[3], gStatus.recordsPlayerName[3],  gStatus.records[4], gStatus.recordsPlayerName[4]);
                gStatus.resetGame();
                if (mMPanel.englishToPolishModeBox.isSelected()) gStatus.GAMEMODE = 0;
                else gStatus.GAMEMODE = 1;
                gamePlaying = true;
                loseMenu = false;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Ruch myszką
     * @param e wydarzenie
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        if (gamePlaying){
            exitButtonSmallEntered = e.getX() > (screenWidth - Resources.exitButtonSmall.getWidth(null)) && e.getY() < (Resources.exitButtonSmall.getHeight(null));
        }
        if (mainMenu){
            if(Objects.equals(e.getComponent().getName(), "goBackButton")){
                rPanel.goBackButton.setIcon(Resources.backButtonEnteredImage);
            }
            else{rPanel.goBackButton.setIcon(Resources.backButtonImage);}
            if(Objects.equals(e.getComponent().getName(), "playButton")){
                mMPanel.playButton.setIcon(Resources.playButtonEnteredImage);
            }
            else{mMPanel.playButton.setIcon(Resources.playButtonImage);}
            if(Objects.equals(e.getComponent().getName(), "exitButton")){
                mMPanel.exitButton.setIcon(Resources.exitButtonEnteredImage);
            }
            else{mMPanel.exitButton.setIcon(Resources.exitButtonImage);}
            if(Objects.equals(e.getComponent().getName(), "rankingButton")){
                mMPanel.rankingButton.setIcon(Resources.rankingButtonEnteredImage);
            }
            else{mMPanel.rankingButton.setIcon(Resources.rankingButtonImage);}
            if((Objects.equals(e.getComponent().getName(), "musicButton")) && sPlayer.menuMusic.isRunning()){
                mMPanel.musicButton.setIcon(Resources.musicButtonEnteredImage);
            }
            else if (Objects.equals(e.getComponent().getName(), "musicButton") && !sPlayer.menuMusic.isRunning()){
                mMPanel.musicButton.setIcon(Resources.musicDisabledEnteredImage);
            }
            else if (sPlayer.menuMusic.isRunning()){
                mMPanel.musicButton.setIcon(Resources.musicEnabledImage);
            }
            else {mMPanel.musicButton.setIcon(Resources.musicDisabledImage);}
        }
        if (loseMenu){
            if (e.getX()>(355) && e.getY()>(410) && e.getX()<(555) && e.getY()<(510)){
                loseMenuEntered = 1;
                repaint();
            }
            else if (e.getX()>(645) && e.getY()>(410) && e.getX()<(845) && e.getY()<(510)){
                loseMenuEntered = 2;
                repaint();
            }
            else{
                loseMenuEntered = 0;
                repaint();
            }
        }
    }
}
