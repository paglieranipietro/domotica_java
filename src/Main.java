import controller.UserListener;
import entity.Presa;
import entity.Sistema;
import graphics.Canvas;
import graphics.Ellipse;
import graphics.Rectangle;
import ui.SistemaLayer;

import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema("s1");
        s.aggiungiPresa(new Presa("p1","st","zo",0,0));
        s.aggiungiPresa(new Presa("p2","st","zo",200,300));
        SistemaLayer.inizializza(s);
        UserListener userListener = new UserListener(s);
        Canvas.getInstance().frame.addMouseListener(userListener);
    }
}