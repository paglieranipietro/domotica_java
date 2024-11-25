package ui;

import entity.Presa;
import entity.Sistema;
import graphics.*;
import java.util.ArrayList;

/**
 * La classe `SistemaLayer` gestisce la rappresentazione grafica del sistema.
 */
public class SistemaLayer {
    private static String piantinaImg;

    public static Picture salvataggio = new Picture("src/images/salvataggio.png");

    public static Picture onOff = new Picture("src/images/onOff.png");

    private static Sistema sistema;

    /**
     * Carica il percorso dell'immagine della piantina.
     */
    private static void caricaIndirizzoImmagini(){
        piantinaImg = "src/images/piantina_appartamento.jpg";
    }

    /**
     * Inizializza il livello di sistema con il sistema dato.
     *
     * @param s il sistema da inizializzare
     */
    public static void inizializza(Sistema s){
        sistema = s;
        caricaIndirizzoImmagini();
        // piantina dell'appartamento
        Picture piantina = new Picture(piantinaImg);
        piantina.translate(300,-100);
        piantina.draw();
        salvataggio.translate(1400,700);
        salvataggio.draw();
        onOff.translate(1437,660);
        onOff.draw();

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
