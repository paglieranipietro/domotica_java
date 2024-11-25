package entity;

import controller.OperazioniUtente;
import graphics.Canvas;

import java.awt.event.MouseEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Sistema implements Serializable {
    //private String nome;
    @Serial
    private static final long serialVersionUID = 857428027573214L;
    private final ArrayList<Presa> prese;
    private static final int DELTA_LUMINOSITA = 10;
    
    public Sistema() {
        prese = new ArrayList<>();
    }

    public void aggiungiPresa(Presa presa) {
        prese.add(presa);
    }
    
    public Presa cercaPresa(String nome) {
        int indice = cercaIndicePresa(nome);
        if (indice == -1) {
            throw new IllegalArgumentException("Presa " + nome + " not found");
        }
        return prese.get(indice);
    }

    public int cercaIndicePresa(String nome) {
        for (int i = 0; i < prese.size(); i++) {
            if (nome.equals(prese.get(i).getNome())) {
                return i;
            }
        }
        return -1;
    }

    public void aggiungiLampadinaAPresa(String nomePresa, Lampadina lampadina) {
        Presa p = cercaPresa(nomePresa);
        p.aggiungiLampadina(lampadina);
    }

    public void aumentaLuminosita(String nomePresa) {
        Lampadina l = cercaPresa(nomePresa).getLampadina();
        l.setQI(l.getQI() + DELTA_LUMINOSITA);
    }

    public void diminuisciLuminosita(String nomePresa) {
        Lampadina l = cercaPresa(nomePresa).getLampadina();
        l.setQI(l.getQI() - DELTA_LUMINOSITA);
    }

    public float getPotenzaSistema() {
        float totale = 0;
        for (Presa presa : prese) {
            totale += presa.getPotenza();
        }
        return totale;
    }

    public void eliminaLampadina(String nomePresa) throws IllegalArgumentException {
        int indice = cercaIndicePresa(nomePresa);
        if (indice == -1) {
            throw new IllegalArgumentException("Presa " + nomePresa + " not found");
        }
        prese.get(indice).eliminaLampadina();
    }

    public void eliminaPresa(String nomePresa) {
        int indice = cercaIndicePresa(nomePresa);
        Canvas.delete(prese.get(indice).immaginePresa);
        Canvas.delete(prese.get(indice).immagineLampada);
        prese.remove(indice);
    }
    
    public ArrayList<Presa> getPrese() {
        return prese;
    }
    
    public String presaCliccata(MouseEvent e){
        for (Presa presa : prese) {
            if(OperazioniUtente.haCliccato(presa.getImmagine(),e)){
                return presa.getNome();
            }
        }
        return null;
    }
}