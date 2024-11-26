package entity;

import controller.OperazioniUtente;
import graphics.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Entita' che rappresenza una stanza della casa
 */
public class Stanza implements Serializable {
    private final Rectangle posizione;
    public final String nome;

    /**
     * Costruttore della stanza
     * @param posizione zona della stanza
     * @param nome nome della stanza
     */
    public Stanza(Rectangle posizione, String nome) {
        this.posizione = posizione;
        this.nome = nome;
    }

    /**
     * Ritorna la zona della stanza
     * @return 'posizione'
     */
    public Rectangle getPosizione() {
        return posizione;
    }
}
