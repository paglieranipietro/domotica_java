package entity;

import graphics.Canvas;
import graphics.Ellipse;
import graphics.Picture;

import java.io.Serial;
import java.io.Serializable;

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
    public transient Picture cerchioRosso;
    public transient Picture cerchioGiallo;
    public transient Picture cerchioBlu;

    public void inizializzaImmagini(){
        this.immaginePresa = new Picture("src/images/presa.png");
        this.immagineLampada = new Picture("src/images/lampadina.png");
        this.cerchioRosso = new Picture("src/images/rosso.png");
        this.cerchioGiallo = new Picture("src/images/giallo.png");
        this.cerchioBlu = new Picture("src/images/blu.png");
    }

    public Presa(String nome, String stanza, float x, float y) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = null;
        this.x = x;
        this.y = y;
        lampadina = null;
        inizializzaImmagini();
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

    public void togliLampadina(){
        this.lampadina = null;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void draw(){
        // spostare le immagini in una zona dove non si vede;
        immaginePresa.setPosition(-1000,-1000);
        immagineLampada.setPosition(-1000,-1000);
        getImmagine().setPosition(x,y);
        if(getImmagine() == immagineLampada){
            cerchioGiallo.setPosition(-1000, -1000);
            cerchioGiallo.setPosition(x,y);
            cerchioGiallo.draw();
        }
        getImmagine().draw();
    }
}
