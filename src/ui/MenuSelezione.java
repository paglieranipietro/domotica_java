package ui;

import entity.Presa;
import graphics.Picture;
import graphics.Rectangle;

/**
 * La classe `MenuSelezione` gestisce il menu di selezione per le prese e le lampadine.
 */
public class MenuSelezione {
    public static final Picture PIU = new Picture("src/images/PIU.png");

    public static final Picture MENO = new Picture("src/images/MENO.png");

    public static final Picture CESTINO = new Picture("src/images/CESTINO.png");

    public static final Picture COLORI = new Picture("src/images/colori.png");

    public static final Picture ONOFF = new Picture("src/images/onOff.png");

    private static final Rectangle CONTENITORE = new Rectangle(-100, -100, 300, 55);

    private static final int X_FUORI = -100;

    private static final int Y_FUORI = -100;

    private static final int X = 10;

    private static final int Y = 50;

    private static final int DISTANZA = 60;

    private static final int Distanza_Y = 5;

    /**
     * Configura il menu per una presa.
     */
    private static void menuPresa() {
        togli();
        CONTENITORE.setPosition(X, Y);
        PIU.setPosition(X, Y + Distanza_Y);
        CESTINO.setPosition(X + DISTANZA, Y + Distanza_Y);
    }

    /**
     * Configura il menu per una lampadina.
     */
    private static void menuLampadina() {
        togli();
        CONTENITORE.setPosition(X, Y);
        PIU.setPosition(X, Y + Distanza_Y);
        MENO.setPosition(X + DISTANZA, Y + Distanza_Y);
        COLORI.setPosition(X + 2 * DISTANZA, Y + Distanza_Y);
        CESTINO.setPosition(X + 3 * DISTANZA, Y + Distanza_Y);
        ONOFF.setPosition(X + 4 * DISTANZA, Y + Distanza_Y);
    }

    /**
     * Inizializza il menu nascondendo tutti gli elementi e disegnandoli successivamente.
     */
    public static void inizializza() {
        togli();
        CONTENITORE.draw();
        PIU.draw();
        MENO.draw();
        COLORI.draw();
        CESTINO.draw();
        ONOFF.draw();
    }

    /**
     * Disegna il menu appropriato in base alla presenza, o meno, di una lampadina nella presa.
     *
     * @param p la presa da controllare
     */
    public static void disegna(Presa p) {
        if (p.haLampadina()) {
            menuLampadina();
        } else {
            menuPresa();
        }
    }

    /**
     * Nasconde tutti gli elementi del menu spostandoli fuori dallo schermo.
     */
    public static void togli() {
        CONTENITORE.setPosition(X_FUORI, Y_FUORI);
        PIU.setPosition(X_FUORI, Y_FUORI);
        MENO.setPosition(X_FUORI, Y_FUORI);
        COLORI.setPosition(X_FUORI, Y_FUORI);
        CESTINO.setPosition(X_FUORI, Y_FUORI);
        ONOFF.setPosition(X_FUORI, Y_FUORI);
    }
}
