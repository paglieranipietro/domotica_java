package entity;

import ui.SistemaLayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema extends MouseAdapter {
    private String nome;
    private ArrayList<Presa> prese;
    private static final int DELTA_LUMINOSITA = 10;

    public Sistema(String nome) {
        this.nome = nome;
        prese = new ArrayList<>();
    }

    public void aggiungiPresa(Presa presa) {
        prese.add(presa);
    }

    public String getNome() {
        return this.nome;
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

    public int cercaIndiceLampadina(String nome) {
        for (int i = 0; i < prese.size(); i++) {
            if (prese.get(i).haLampadina() && nome.equals(prese.get(i).getLampadina().getNome())) {
                return i;
            }
        }
        return -1;
    }

    public Lampadina cercaLampadina(String nome) {
        int indice = cercaIndiceLampadina(nome);
        if (indice == -1) {
            throw new IllegalArgumentException("Lampadina " + nome + " not found");
        }
        return prese.get(indice).getLampadina();
    }

    public void aggiungiLampadinaAPresa(String nomePresa, Lampadina lampadina) {
        Presa p = cercaPresa(nomePresa);
        p.aggiungiLampadina(lampadina);
    }

    public void aumentaLuminosita(String nome) {
        Lampadina l = cercaLampadina(nome);
        l.setQI(l.getQI() + DELTA_LUMINOSITA);
    }

    public void diminuisciLuminosita(String nome) {
        Lampadina l = cercaLampadina(nome);
        l.setQI(l.getQI() - DELTA_LUMINOSITA);
    }

    public void modificaLuminosita(String nome, int QI) {
        Lampadina l = cercaLampadina(nome);
        l.setQI(QI);
    }

    public void modificaColore(String nome, String colore) {
        cercaLampadina(nome).setColore(colore);
    }

    public float getPotenzaSistema() {
        float totale = 0;
        for (Presa presa : prese) {
            totale += presa.getPotenza();
        }
        return totale;
    }

    public void accendiLampadina(String nome) {
        cercaLampadina(nome).setOn();
    }

    public void spegniLampadina(String nome) {
        cercaLampadina(nome).setOff();
    }

    public void accendiSistema() {
        for (Presa presa : prese) {
            if (presa.haLampadina())
                presa.getLampadina().setOn();
        }
    }

    public void spegniSistema() {
        for (Presa presa : prese) {
            if (presa.haLampadina())
                presa.getLampadina().setOff();
        }
    }

    public void eliminaLampadina(String nome) {
        int indice = cercaIndiceLampadina(nome);
        if (indice == -1) {
            throw new IllegalArgumentException("Lampadina " + nome + " not found");
        }
        prese.get(indice).eliminaLampadina();
    }

    public ArrayList<Presa> getPrese() {
        return prese;
    }

    Scanner scanner = new Scanner(System.in);
    public Lampadina letturaInfoLampadina() {
        System.out.println("Inserisci il nome della lampadina: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci la potenza della lampadina: ");
        float potenza = scanner.nextInt();
        return new Lampadina(nome, potenza);
    }

    private boolean haCliccatoSu(Presa p, MouseEvent e) {
        float lx = p.getX();
        float ly = p.getY();
        float rx = p.getX() + p.getImmagine().getWidth();
        float ry = p.getY() + p.getImmagine().getHeight();
        return e.getX() >= lx && e.getX() <= rx && e.getY() >= ly && e.getY() <= ry;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        for (Presa p : prese) {
            if (haCliccatoSu(p, e)) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (p.haLampadina()) {
                        p.togliLampadina();
                    } else {
                        Lampadina l = letturaInfoLampadina();
                        p.aggiungiLampadina(l);
                    }
                }
                break;
            }
        }
        SistemaLayer.disegnaPrese();
    }
}