package entity;

import graphics.Picture;

/**
 * Entita' che gestisce la funzione e le caratteristiche di un pulsante
 */
public class Pulsante{
    private final String nome;
    private final int x,y;
    private final Picture immagine;
    public final int codice;

    /**
     * Costruttore oggetto 'Pulsante'
     * @param nome nome del pulsante
     * @param x coordinata x
     * @param y coordinata y
     * @param nomeImmagine path dell'immagine
     * @param codice la funzionalita' corrispondente
     */
    public Pulsante(String nome, int x, int y, String nomeImmagine, int codice) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.immagine = new Picture(nomeImmagine);
        this.codice = codice;
        this.immagine.draw();
    }

    /**
     * Nascondi pulsante
     */
    public void nascondi(){
        this.immagine.setPosition(-100,-100);
    }

    /**
     * Mostra pulsante
     */
    public void mostra(){
        this.immagine.setPosition(x,y);
    }

    /**
     * Ritorna il nome del pulsante
     * @return 'nome'
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ritorna la Picture del pulsante
     * @return 'immagine'
     */
    public Picture getImmagine() {
        return immagine;
    }
}