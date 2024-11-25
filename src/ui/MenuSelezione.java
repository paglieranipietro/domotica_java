package ui;

import entity.Presa;
import graphics.Rectangle;

/**
 * La classe `MenuSelezione` gestisce il menu di selezione per le prese e le lampadine.
 */
public class MenuSelezione {
    public static final Rectangle CONTENITORE = new Rectangle(-100, -100, 300, 55);
    private static final String[] PULSANTI_PRESA = {"AggiungiLampadina","EliminaPresa","VisualizzaInfoPresa"};
    private static final String[] PULSANTI_LAMPADINA = {"AumentaLuminosita","DiminuisciLuminosita","EliminaLampadina","VisualizzaInfoLampadina","CambiaColore","OnOffLampadina"};
    private static final int X_FUORI = -100,Y_FUORI = -100;
    private static final int X = 370,Y = 530;
    /**
     * Configura il menu per una presa.
     */
    private static void menuPresa() {
        CONTENITORE.setPosition(X, Y);

        for (String s : PULSANTI_PRESA) {
            PulsantiLayer.mostraPulsante(s);
        }
    }

    /**
     * Configura il menu per una lampadina.
     */
    private static void menuLampadina() {
        CONTENITORE.setPosition(X, Y);
        for (String s : PULSANTI_LAMPADINA) {
            PulsantiLayer.mostraPulsante(s);
        }
    }

    /**
     * Disegna il menu appropriato in base alla presenza, o meno, di una lampadina nella presa.
     *
     * @param p la presa da controllare
     */
    public static void disegna(Presa p) {
        CONTENITORE.draw();
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
        for (String s : PULSANTI_PRESA) {
            PulsantiLayer.nascondiPulsante(s);
        }
        for (String s : PULSANTI_LAMPADINA) {
            PulsantiLayer.nascondiPulsante(s);
        }
    }
}
