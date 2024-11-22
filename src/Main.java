import entity.Presa;
import entity.Sistema;
import graphics.Canvas;
import ui.SistemaLayer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema("s1");
        s.aggiungiPresa(new Presa("p1", "st", "zo", 0, 0));
        s.aggiungiPresa(new Presa("p2", "st", "zo", 200, 300));
        SistemaLayer.inizializza(s);
        Canvas.getInstance().frame.addMouseListener(s);

    }
}