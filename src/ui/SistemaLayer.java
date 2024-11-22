package ui;

import entity.Presa;
import entity.Sistema;
import graphics.*;
import graphics.Canvas;

import java.awt.*;
import java.util.ArrayList;

public class SistemaLayer {
    private static String lampadinaImg,piantinaImg,presaImg;
    private static final Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
    private static Sistema sistema;
    private static void caricaIndirizzoImmagini(){
        //System.out.println(new File("../images/lampadina.jpg").getAbsolutePath());
        lampadinaImg = "src/images/lampadina.jpg";
        piantinaImg = "src/images/piantina_appartamento.jpg";
        presaImg = "src/images/presa.jpg";
    }

    public static void inizializza(Sistema s){
        sistema = s;
        caricaIndirizzoImmagini();
        // piantina dell'appartamento
        Picture piantina = new Picture(piantinaImg);
        piantina.translate(300,-100);
        piantina.draw();

        disegnaPrese();
    }

    public static void disegnaPrese(){
        ArrayList<Presa> prese = sistema.getPrese();
        for(Presa p : prese){
            p.draw();
        }
    }

}
