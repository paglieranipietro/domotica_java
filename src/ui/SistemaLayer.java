package ui;

import entity.Presa;
import entity.Sistema;
import graphics.*;
import graphics.Canvas;

import java.awt.*;
import java.util.ArrayList;

public class SistemaLayer {
    private static String piantinaImg;
    public static Picture salvataggio = new Picture("src/images/salvataggio.png");
    public static Picture onOff = new Picture("src/images/onOff.png");
    private static Sistema sistema;
    private static void caricaIndirizzoImmagini(){
        piantinaImg = "src/images/piantina_appartamento.jpg";
    }

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
        //onOff.grow(50,50);

        disegnaPrese();
    }

    public static void disegnaPrese(){
        ArrayList<Presa> prese = sistema.getPrese();
        for(Presa p : prese){
            p.draw();
        }
    }

}
