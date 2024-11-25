package entity;

import graphics.Canvas;
import graphics.Color;
import graphics.Ellipse;
import graphics.Picture;

import java.io.Serial;
import java.io.Serializable;

import static graphics.Color.BLUE;
import static graphics.Color.WHITE;

public class Presa implements Serializable {
    @Serial
    private static final long serialVersionUID = 574957592385323L;
    private String nome;
    private String stanza;
    private String zona;
    private float x, y;
    private Lampadina lampadina;
    public transient Picture immagineLampada;
    public transient Picture immaginePresa;
    Ellipse colorCircle;
    String colore = "bianco";

    public void inizializzaImmagini(){
        this.immaginePresa = new Picture("src/images/presa.png");
        this.immagineLampada = new Picture("src/images/lampadina.png");
        this.colorCircle = new Ellipse(-1000, -1000, 47, 47);
    }

    public Presa(String nome, float x, float y) {
        this.nome = nome;
        this.zona = null;
        this.x = x;
        this.y = y;
        lampadina = null;
        inizializzaImmagini();
    }
    public void aggiungiLampadina (Lampadina lampadina) {
        this.lampadina = lampadina;
    }
    public void eliminaLampadina () {
        this.lampadina = null;
    }

    public String getNome() {
        return nome;
    }

    public String getColore(){
        return colore;
    }

    public Lampadina getLampadina() {
        return this.lampadina;
    }

    public boolean haLampadina(){
        return lampadina != null;
    }

    public float getPotenza(){
        if(!haLampadina() || !getLampadina().isOn()){
            return 0;
        }
        return lampadina.getPotenza() * lampadina.getQI() / 100;
    }

    public Picture getImmagine(){
        if(haLampadina()){
            return immagineLampada;
        }
        return immaginePresa;
    }


    public void changeColor (String colore){
        if (colore.equals("rosso")){
            this.colore = "verde";
        }else if (colore.equals("verde")) {
            this.colore = "blu";
        }else if (colore.equals("blu")) {
            this.colore = "bianco";
        }else if(colore.equals("bianco")){
            this.colore = "rosso";
        }
    }

    public void drawColor (String colore){
        if(colore.equals("rosso")){
            this.colorCircle.setColor(new Color(25 * this.lampadina.getQI() / 10, 0, 0));
        } else if(colore.equals("verde")){
            this.colorCircle.setColor(new Color(0, 25 * this.lampadina.getQI() / 10, 0));
        } else if(colore.equals("blu")){
            this.colorCircle.setColor(new Color(0, 0, 25 * this.lampadina.getQI() / 10));
        } else if(colore.equals("bianco")){
            this.colorCircle.setColor(new Color(25 * this.lampadina.getQI() / 10, 25 * this.lampadina.getQI() / 10, 25 * this.lampadina.getQI() / 10));
        }
        colorCircle.draw();
        colorCircle.fill();
    }

    public void draw(){
        // spostare le immagini in una zona dove non si vede;
        immaginePresa.setPosition(-1000,-1000);
        immagineLampada.setPosition(-1000,-1000);
        colorCircle.translate(-1000 - colorCircle.getX(), -1000 - colorCircle.getY());
        getImmagine().setPosition(x - (getImmagine().getWidth() / 2),y - (getImmagine().getHeight() / 2));
        if(getImmagine() == immagineLampada){
            colorCircle.translate(1000 + getImmagine().getX() + 15, 1000 + getImmagine().getY() + 15);
            drawColor(colore);
        }
        getImmagine().draw();
    }
}
