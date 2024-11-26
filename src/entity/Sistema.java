package entity;

import controller.OperazioniUtente;
import dao.Configurazione;
import graphics.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe `Sistema` rappresenta un sistema che gestisce un insieme di prese elettriche.
 * Implementa l'interfaccia `Serializable` per permettere la serializzazione delle sue istanze.
 */
public class Sistema implements Serializable {
    //private String nome; //verrà aggiunto quando gestiremo più sistemi
    @Serial
    private static final long serialVersionUID = 857428027573214L;

    private final ArrayList<Presa> prese;

    private static final int DELTA_LUMINOSITA = 10;
    private transient ArrayList<Stanza> stanze;
    /**
     * Costruisce un nuovo `Sistema`.
     */
    public Sistema() {
        prese = new ArrayList<>();
        leggiStanze();
    }

    public void leggiStanze(){
        ArrayList<Stanza> stanzeTemp;
        try {
            stanzeTemp = Configurazione.leggiStanze();
        } catch (IOException e) {
            stanzeTemp = new ArrayList<>();
            System.out.println("Errore lettura stanze");
        }
        stanze = stanzeTemp;
    }

    /**
     * Aggiunge una presa al sistema.
     *
     * @param presa la presa da aggiungere
     */
    public void aggiungiPresa(Presa presa) {
        prese.add(presa);
    }

    /**
     * Cerca una presa per nome.
     *
     * @param nome il nome della presa
     * @return la presa con il nome specificato
     * @throws IllegalArgumentException se la presa non viene trovata
     */
    public Presa cercaPresa(String nome) {
        int indice = cercaIndicePresa(nome);
        if (indice == -1) {
            return null;
        }
        return prese.get(indice);
    }

    /**
     * Cerca l'indice di una presa per nome.
     *
     * @param nome il nome della presa
     * @return l'indice della presa, o -1 se non trovata
     */
    public int cercaIndicePresa(String nome) {
        for (int i = 0; i < prese.size(); i++) {
            if (prese.get(i).getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Aggiunge una lampadina a una presa.
     *
     * @param nomePresa il nome della presa
     * @param lampadina la lampadina da aggiungere
     */
    public void aggiungiLampadinaAPresa(String nomePresa, Lampadina lampadina) {
        Presa p = cercaPresa(nomePresa);
        p.aggiungiLampadina(lampadina);
    }

    /**
     * Aumenta di 'DELTA_LUMINOSITA' la luminosità della lampadina in una presa.
     *
     * @param nomePresa il nome della presa
     */
    public void aumentaLuminosita(String nomePresa) {
        Lampadina l = cercaPresa(nomePresa).getLampadina();
        l.setQI(l.getQI() + DELTA_LUMINOSITA);
    }

    /**
     * Diminuisce di 'DELTA_LUMINOSITA' la luminosità della lampadina in una presa.
     *
     * @param nomePresa il nome della presa
     */
    public void diminuisciLuminosita(String nomePresa) {
        Lampadina l = cercaPresa(nomePresa).getLampadina();
        l.setQI(l.getQI() - DELTA_LUMINOSITA);
    }

    /**
     * Restituisce la potenza totale del sistema.
     *
     * @return la potenza totale del sistema
     */
    public float getPotenzaSistema() {
        float totale = 0;
        for (Presa presa : prese) {
            totale += presa.getPotenza();
        }
        return totale;
    }

    /**
     * Rimuove la lampadina da una presa.
     *
     * @param nomePresa il nome della presa
     * @throws IllegalArgumentException se la presa non viene trovata
     */
    public void eliminaLampadina(String nomePresa) throws IllegalArgumentException {
        int indice = cercaIndicePresa(nomePresa);
        if (indice == -1) {
            throw new IllegalArgumentException("Presa " + nomePresa + " non trovata");
        }
        prese.get(indice).eliminaLampadina();
    }

    /**
     * Rimuove una presa dal sistema.
     *
     * @param nomePresa il nome della presa
     */
    public void eliminaPresa(String nomePresa) {
        int indice = cercaIndicePresa(nomePresa);
        Canvas.delete(prese.get(indice).immaginePresa);
        Canvas.delete(prese.get(indice).immagineLampada);
        prese.remove(indice);
    }

    /**
     * Restituisce il vettore delle prese nel sistema.
     *
     * @return il vettore delle prese
     */
    public ArrayList<Presa> getPrese() {
        return prese;
    }

    /**
     * Restituisce il nome della presa che è stata cliccata.
     *
     * @param e l'evento del mouse
     * @return il nome della presa cliccata, o null se nessuna presa è stata cliccata
     */
    public String presaCliccata(Point e){
        for (Presa presa : prese) {
            if(OperazioniUtente.haCliccato(presa.getImmagine(),e)){
                return presa.getNome();
            }
        }
        return null;
    }

    /**
     * Ottenere il nome della stanza che contiene il punto p
     * @param p punto
     * @return 'nome'
     */
    public String stanzaCliccata(Point p){
        for (Stanza stanza : stanze) {
            if (OperazioniUtente.haCliccato(stanza.getPosizione(),p)){
                return stanza.nome;
            }
        }
        return null;
    }

    /**
     * Accendere/spegnere il sistema
     */
    public void accendiSistema(){
        boolean tuttoAcceso = true;
        for(Presa p : getPrese()){
            if(p.haLampadina() && !p.getLampadina().isOn()){
                tuttoAcceso = false;
            }
        }
        for (Presa p : getPrese()){
            if(p.haLampadina()){
                if(!tuttoAcceso){
                    p.getLampadina().setOn();
                } else {
                    p.getLampadina().setOff();
                }
            }
        }
    }

    /**
     * Cerca lampadina dato il nome
     * @param nome nome della lampadina da cercare
     * @return 'lampadina'
     */
    public Lampadina cercaLampadina(String nome) {
        for(Presa presa : prese){
            if(presa.haLampadina()){
                if(presa.getLampadina().nome.equals(nome)){
                    return presa.getLampadina();
                }
            }
        }
        return null;
    }
}