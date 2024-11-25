package entity;

import controller.OperazioniUtente;
import graphics.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Stanza implements Serializable {
    private final Rectangle posizione;
    public final String nome;

    public Stanza(Rectangle posizione, String nome) {
        this.posizione = posizione;
        this.nome = nome;
    }


    public Rectangle getPosizione() {
        return posizione;
    }
}
