import controller.UserListener;
import entity.Lampadina;
import entity.Presa;
import entity.Sistema;
import graphics.Canvas;
import graphics.Ellipse;
import graphics.Rectangle;
import ui.SistemaLayer;

import java.awt.event.KeyListener;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema("s1");
        s.aggiungiPresa(new Presa("p1","st","zo",0,0));
        s.aggiungiPresa(new Presa("p2","st","zo",200,300));
        SistemaLayer.inizializza(s);
        UserListener userListener = new UserListener(s);
        Canvas.getInstance().frame.addMouseListener(userListener);
        boolean continua = true;
        int scelta;
        while(continua == true){
            System.out.println("1. Aggiungi lampadina");
            System.out.println("2. Rimuovi lampadina");
            Scanner scanner = new Scanner(System.in);
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il nome della presa");
                    String nomePresa = scanner.next();
                    System.out.println("Inserisci il nome della lampadina");
                    String nomeLampadina = scanner.next();
                    System.out.println("Inserisci la potenza della lampadina");
                    int potenza = scanner.nextInt();
                    s.aggiungiLampadinaAPresa(nomePresa, new Lampadina(nomeLampadina, potenza));
                    break;
                case 2:
                    System.out.println("Inserisci il nome della lampadina");
                    nomePresa = scanner.next();
                    s.eliminaLampadina(nomePresa);
                    break;
                case 3:
                    continua = false;
                    break;
            }
            SistemaLayer.disegnaPrese();
        }
    }
}