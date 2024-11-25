package ui;

import entity.Presa;
import entity.Sistema;
import graphics.*;
import java.util.ArrayList;

/**
 * La classe `SistemaLayer` gestisce la rappresentazione grafica del sistema.
 */
public class SistemaLayer {
    private static String piantinaImg = "src/images/piantina_appartamento.jpg";

    public static Picture salvataggio = new Picture("src/images/salvataggio.png");

    public static Picture onOff = new Picture("src/images/onOff.png");

    private static Sistema sistema;

    /**
     * Inizializza il livello di sistema con il sistema dato.
     *
     * @param s il sistema da inizializzare
     */
    public static void inizializza(Sistema s){
        sistema = s;
        Picture piantina = new Picture(piantinaImg);

        Canvas.getInstance().frame.setSize(850,800);
        Canvas.getInstance().frame.setResizable(false);

        piantina.setPosition(-100,-100);

        piantina.draw();
        disegnaPrese();
    }

    /**
     * Disegna tutte le prese nel sistema.
     */
    public static void disegnaPrese(){
        ArrayList<Presa> prese = sistema.getPrese();
        for(Presa p : prese){
            p.draw();
        }
    }
}
