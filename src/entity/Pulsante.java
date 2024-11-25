package entity;

import graphics.Picture;

public class Pulsante{
    private final String nome;
    private final int x,y;
    private final Picture immagine;
    public final int codice;
    public Pulsante(String nome, int x, int y, String nomeImmagine, int codice) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.immagine = new Picture(nomeImmagine);
        this.codice = codice;
        this.immagine.draw();
    }

    public void nascondi(){
        this.immagine.setPosition(-100,-100);
    }

    public void mostra(){
        this.immagine.setPosition(x,y);
    }

    public String getNome() {
        return nome;
    }

    public Picture getImmagine() {
        return immagine;
    }
}