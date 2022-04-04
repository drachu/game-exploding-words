package com.company;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Klasa odpowiedzialna za wszelkie działania związane z bombami
 * @author Adam Dradrach
 */
public class Bombs {


    /** stan wybuchu jakiejkolwiek bomby */
    public boolean bombExploded = false;
    /** prędkość bomby */
    public int yVelocity = 10;
    /** opóźnienie bomby 2 w ms */
    public static int bomb2delay = 30;
    /**  */
    public int bomb2delayTime = 1;

    /** pozycja startowa bomb Y */
    public int[] startingPosition = {0, 0};
    /** możliwe współrzędne startowe bomb w osi X */
    public int[] droppingBombPositionX = {100, 200, 300, 400, 500, 600, 700, 800};
    /** pozycja bomb w osi Y */
    public int[] droppingBombPositionY = {-120, -125};
    /** stan wybuchu każdej z bomb po wpisaniu dobrego słowa*/
    public int[] bombExplosion = {0, 0};
    /** współrzędna wybuchających bomb X po wpisaniu dobrego słowa*/
    public int[] explodingBombX = {0, 0};
    /** współrzędna wybuchających bomb Y po wpisaniu dobrego słowa*/
    public int[] explodingBombY = {0, 0};
    /** czas trwania eksplozji po wpisaniu dobrego słowa*/
    public int[] explosionCurrentTime = {0, 0};

    /** wspórzędna wybuchu bomby po opadnięciu na ziemię*/
    public int bombExplodedX = 0;
    /** maks czas wybuchu bomby po wpisaniu dobrego słowa */
    public static int explosionTime = 8;
    /** maks czas wybuchu bomby po opadnięciu za ziemię*/
    public static int explosionTime2 = 16;
    /** aktualny czas wybuchu bomby po opadnięciu na ziemię*/
    public int explodingTime = 0;


    /**
     * restartowanie pozycji bomb
     */
    public void restartBombs(){
        Random random = new Random();
        droppingBombPositionY[0] = -120;
        startingPosition[0] = random.nextInt(7);
        droppingBombPositionY[1] = -120;
        startingPosition[1] = random.nextInt(7);
    }

    /**
     * restart pozycji pierwszej bomby
     */
    public void restartBomb1(){
        Random random = new Random();
        droppingBombPositionY[0] = -120;
        startingPosition[0] = random.nextInt(7);
    }

    /**
     * restart pozycji drugiej bomby
     */
    public void restartBomb2(){
        Random random = new Random();
        droppingBombPositionY[1] = -120;
        startingPosition[1] = random.nextInt(7);
    }

    /**
     * sprawdzenie pozycji bomb, czy nie nachodzą na siebie
     */
    public void checkBombPosition(){
        Random random = new Random();
        if (droppingBombPositionY[0]>=droppingBombPositionY[1]){
            if (abs(droppingBombPositionY[1]-droppingBombPositionY[0])<Resources.droppingBomb.getHeight(null)){
                droppingBombPositionY[1]=-120;
            }
        }
        else if (droppingBombPositionY[0]<droppingBombPositionY[1]){
            if (abs(droppingBombPositionY[1]-droppingBombPositionY[0])<Resources.droppingBomb.getHeight(null)){
                droppingBombPositionY[0]=-120;
            }
        }
        //resetowanie pozycji jesli bomby sa na tej samej szerokosci
        if (droppingBombPositionY[0]>=droppingBombPositionY[1]){
            if (abs(droppingBombPositionX[startingPosition[1]]-droppingBombPositionX[startingPosition[0]])<=100){
                startingPosition[1] = random.nextInt(7);
            }
        }
        else if (droppingBombPositionY[0]<droppingBombPositionY[1]){
            if (abs(droppingBombPositionX[startingPosition[0]]-droppingBombPositionX[startingPosition[1]])<=100){
                startingPosition[0] = random.nextInt(7);
            }
        }
    }

    /**
     * ustawienie pozycji wybuchu bomby 1
     */
    public void setBombExplosionPosition1(){
        explodingBombX[0] = droppingBombPositionX[startingPosition[0]];
        explodingBombY[0] = droppingBombPositionY[0];
    }

    /**
     * ustwaienie pozycji wybuchu bomby 2
     */
    public void setBombExplosionPosition2(){
        explodingBombX[1] = droppingBombPositionX[startingPosition[1]];
        explodingBombY[1] = droppingBombPositionY[1];
    }

    /**
     * opóźnienie drugiej bomby, aby nie zlatywały na nowo cały czas w momencie wybuchu lub wpisania właściwego słowa
     */
    public void delayBomb2(){
        if (bomb2delayTime>0){
            restartBomb2();
            bomb2delayTime++;
            if(bomb2delayTime == Bombs.bomb2delay){bomb2delayTime=0;}
        }
    }
}
