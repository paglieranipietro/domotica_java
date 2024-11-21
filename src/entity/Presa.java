package entity;

import graphics.Picture;

public class Presa {
    private String nome;
    private String stanza;
    private String zona;
    private float x, y;
    private Lampadina lampadina;
    public final Picture immagineLampada =  new Picture("src/images/lampadina.jpg");
    public final Picture immaginePresa = new Picture("src/images/presa.jpg");
    public Presa(String nome, String stanza, String zona, float x, float y, Lampadina lampadina) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = zona;
        this.x = x;
        this.y = y;
        this.lampadina = lampadina;
    }
    public Presa(String nome, String stanza, float x, float y, Lampadina lampadina) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = null;
        this.x = x;
        this.y = y;
        this.lampadina = lampadina;
    }
    public Presa(String nome, String stanza, String zona, float x, float y) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = zona;
        this.x = x;
        this.y = y;
        lampadina = null;
    }

    public Presa(String nome, String stanza, float x, float y) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = null;
        this.x = x;
        this.y = y;
        lampadina = null;
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

    public String getStanza() {
        return stanza;
    }

    public String getZona() {
        return zona;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Lampadina getLampadina() {
        return lampadina;
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
        int delta = 99999;
        // spostare le immagini in una zona dove non si vede;
        immaginePresa.translate(delta,delta);
        immagineLampada.translate(delta,delta);
        getImmagine().translate(-getImmagine().getX()+ x, -getImmagine().getY() + y);
        getImmagine().draw();
    }
}
