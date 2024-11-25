package ui;

import entity.Presa;
import graphics.Color;
import graphics.Picture;
import graphics.Rectangle;

public class MenuSelezione {
    public static final Picture PIU = new Picture("src/images/PIU.png");
    public static final Picture MENO = new Picture("src/images/MENO.png");
    public static final Picture CESTINO = new Picture("src/images/CESTINO.png");
    public static final Picture COLORI = new Picture("src/images/colori.png");
    private static final Rectangle CONTENITORE = new Rectangle(-100,-100,300,55);
    private static final int X_FUORI = -100;
    private static final int Y_FUORI = -100;
    private static final int X = 10;
    private static final int Y = 50;
    private static final int DISTANZA = 60;
    private static final int Distanza_Y = 5;

    private static void menuPresa(){
        CONTENITORE.setPosition(X,Y);
        PIU.setPosition(X,Y + Distanza_Y);
        CESTINO.setPosition(X + DISTANZA,Y + Distanza_Y);
    }

    private static void menuLampadina(){
        CONTENITORE.setPosition(X,Y);
        PIU.setPosition(X,Y + Distanza_Y);
        MENO.setPosition(X+DISTANZA,Y + Distanza_Y);
        COLORI.setPosition(X + 2 * DISTANZA,Y + Distanza_Y);
        CESTINO.setPosition(X + 3 * DISTANZA,Y + Distanza_Y);
    }
    public static void inizializza(){
        togli();
        CONTENITORE.draw();
        PIU.draw();
        MENO.draw();
        COLORI.draw();
        CESTINO.draw();
    }
    public static void disegna(Presa p){

        if(p.haLampadina()){
            menuLampadina();
        } else {
            menuPresa();
        }
    }

    public static void togli(){
        CONTENITORE.setPosition(X_FUORI,Y_FUORI);
        PIU.setPosition(X_FUORI,Y_FUORI);
        MENO.setPosition(X_FUORI,Y_FUORI);
        COLORI.setPosition(X_FUORI,Y_FUORI);
        CESTINO.setPosition(X_FUORI,Y_FUORI);
    }
}
