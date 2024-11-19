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

    private static void disegnaPrese(){
        ArrayList<Presa> prese = sistema.getPrese();
        for(Presa p : prese){
            if(p.haLampadina()){
                Picture lampadina = new Picture(lampadinaImg);
                lampadina.translate(-lampadina.getX(),-lampadina.getY());
                lampadina.translate(p.getX(),p.getY());
                lampadina.draw();
            } else {
                Picture presa = new Picture(presaImg);
                presa.translate(-presa.getX(),-presa.getY());
                presa.translate(p.getX(),p.getY());
                presa.draw();
            }
        }
    }
}
