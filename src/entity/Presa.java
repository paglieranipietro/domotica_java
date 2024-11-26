package entity;

import graphics.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * La classe `Presa` rappresenta una presa che può contenere una lampadina.
 * Implementa l'interfaccia `Serializable` per permettere la serializzazione della classe.
 */
public class Presa implements Serializable {
    @Serial
    private static final long serialVersionUID = 574957592385323L;

    public final String nome;

    private final float x, y;

    private Lampadina lampadina;

    public transient Picture immagineLampada;

    public transient Picture immaginePresa;

    private transient Ellipse colorCircle;

    private String colore = "bianco";

    private String stanza;

    /**
     * Inizializza le immagini per la presa e la lampadina.
     */
    public void inizializzaImmagini(){
        this.immaginePresa = new Picture("src/images/presa.png");
        this.immagineLampada = new Picture("src/images/lampadina.png");
        this.colorCircle = new Ellipse(-1000, -1000, 45, 42);
    }

    /**
     * Costruisce una nuova `Presa` con il nome e le coordinate specificate.
     *
     * @param nome il nome della presa
     * @param x la coordinata x della presa
     * @param y la coordinata y della presa
     */
    public Presa(String nome, float x, float y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        lampadina = null;
        inizializzaImmagini();
    }

    /**
     * Aggiunge una lampadina alla presa.
     *
     * @param lampadina la lampadina da aggiungere
     */
    public void aggiungiLampadina (Lampadina lampadina) {
        this.lampadina = lampadina;
    }

    /**
     * Rimuove la lampadina dalla presa.
     */
    public void eliminaLampadina () {
        this.lampadina = null;
    }

    /**
     * Restituisce il nome della presa.
     *
     * @return il nome della presa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il colore della presa.
     *
     * @return il colore della presa
     */
    public String getColore(){
        return colore;
    }

    /**
     * Restituisce la lampadina associata alla presa.
     *
     * @return la lampadina associata alla presa
     */
    public Lampadina getLampadina() {
        return this.lampadina;
    }

    public String getStanza() {
        return stanza;
    }

    /**
     * Verifica se la presa ha una lampadina.
     *
     * @return `true` se la presa ha una lampadina, `false` altrimenti
     */
    public boolean haLampadina(){
        return lampadina != null;
    }

    /**
     * Restituisce la potenza della lampadina se è accesa.
     *
     * @return la potenza della lampadina, o 0 se la lampadina è spenta o non presente
     */
    public float getPotenza(){
        if(!haLampadina() || !getLampadina().isOn()){
            return 0;
        }
        return lampadina.getPotenza() * lampadina.getQI() / 100;
    }

    /**
     * Restituisce l'immagine della presa o della lampadina, a seconda che la presa abbia una lampadina.
     *
     * @return l'immagine della presa o della lampadina
     */
    public Picture getImmagine(){
        if(haLampadina()){
            return immagineLampada;
        }
        return immaginePresa;
    }

    public void setStanza(String stanza) {
        this.stanza = stanza;
    }

    /**
     * Alterna lo stato della lampadina (acceso/spento).
     */
    public void onOffLampadina(){
        if(haLampadina()){
            if (lampadina.isOn()){
                lampadina.setOff();
            } else {
                lampadina.setOn();
            }
        }
    }

    /**
     * Cambia il colore della presa in modo ciclico.
     */
    public void changeColor (){
        switch (colore) {
            case "rosso" -> this.colore = "verde";
            case "verde" -> this.colore = "blu";
            case "blu" -> this.colore = "bianco";
            case "bianco" -> this.colore = "rosso";
        }
    }

    /**
     * Disegna il cerchio colorato in base al colore specificato.
     */
    public void drawColor (){
        switch (colore) {
            case "rosso" -> this.colorCircle.setColor(new Color(25 * this.lampadina.getQI() / 10, 0, 0));
            case "verde" -> this.colorCircle.setColor(new Color(0, 25 * this.lampadina.getQI() / 10, 0));
            case "blu" -> this.colorCircle.setColor(new Color(0, 0, 25 * this.lampadina.getQI() / 10));
            case "bianco" -> this.colorCircle.setColor(new Color(25 * this.lampadina.getQI() / 10, 25 * this.lampadina.getQI() / 10, 25 * this.lampadina.getQI() / 10));
        }
        if(this.lampadina.isOn()) {
            colorCircle.draw();
            colorCircle.fill();
        }else colorCircle.translate(-1000 - colorCircle.getX(), -1000 - colorCircle.getY());
    }

    /**
     * Disegna la presa, la sua lampadina associata e il cerchio colorato.
     */
    public void draw(){
        // spostare le immagini in una zona dove non si vede;
        immaginePresa.setPosition(-1000,-1000);
        immagineLampada.setPosition(-1000,-1000);
        colorCircle.translate(-1000 - colorCircle.getX(), -1000 - colorCircle.getY());
        getImmagine().setPosition(x - (double)(getImmagine().getWidth() / 2),y - (double)(getImmagine().getHeight() / 2));
        if(haLampadina()){
            colorCircle.translate(1000 + getImmagine().getX() + 17, 1000 + getImmagine().getY() + 17);
            drawColor();
        }
        getImmagine().draw();
    }

    @Override
    public String toString() {
        return "Presa{" +
                "colore='" + colore + '\'' +
                ", stanza='" + stanza + '\'' +
                ", y=" + y +
                ", x=" + x +
                ", nome='" + nome + '\'' +
                ", lampadina=" + lampadina +
                '}';
    }
}
