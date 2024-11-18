package entity;

import java.util.ArrayList;

public class Sistema {
    private String nome;
    private ArrayList<Presa> prese;
    private static final int DELTA_LUMINOSITA = 10;
    public Sistema(String nome){
        this.nome = nome;
        prese = new ArrayList<>();
    }
    public void aggiungiPresa(Presa presa){
        prese.add(presa);
    }
    public String getNome(){return this.nome;}

    public Presa cercaPresa (String nome){
        int indice = cercaIndicePresa(nome);
        return prese.get(indice);
    }

    public int cercaIndicePresa(String nome) {
        for (int i = 0; i < prese.size(); i++) {
            if(!prese.get(i).hasLampadina())
                continue;
            if(nome.equals(prese.get(i).getNome())){
                return i;
            }
        }
        return -1;
    }

    public int cercaIndiceLampadina (String nome){
        for (int i = 0; i < prese.size(); i++) {
            if(!prese.get(i).hasLampadina())
                continue;
            if(nome.equals(prese.get(i).getLampadina().getNome())){
                return i;
            }
        }
        return -1;
    }

    public Lampadina cercaLampadina (String nome){
        int indice = cercaIndiceLampadina(nome);
        return prese.get(indice).getLampadina();
    }
    public void aggiungiLampadinaAPresa (String nomePresa,Lampadina lampadina) {
        Presa p = cercaPresa(nomePresa);
        p.aggiungiLampadina(lampadina);
    }

    public void aumentaLuminosita (String nome) {
        Lampadina l = cercaLampadina(nome);
        l.setQI(l.getQI()+DELTA_LUMINOSITA);
    }

    public void diminuisciLuminosita (String nome) {
        Lampadina l = cercaLampadina(nome);
        l.setQI(l.getQI()-DELTA_LUMINOSITA);
    }

    public void modificaLuminosita (String nome, int QI){
        Lampadina l = cercaLampadina(nome);
        l.setQI(QI);
    }

    public void modificaColore (String nome, String colore) {
        cercaLampadina(nome).setColore(colore);
    }

    public float getPotenzaSistema (){
        float totale = 0;
        for (Presa presa : prese) {
            totale += presa.getPotenza();
        }
        return totale;
    }

    public void accendiLampadina (String nome) {
        cercaLampadina(nome).setOn();
    }
    public void spegniLampadina (String nome) {
        cercaLampadina(nome).setOff();
    }

    public void accendiSistema () {
        for (Presa presa : prese) {
            if (presa.hasLampadina())
                presa.getLampadina().setOn();
        }
    }

    public void spegniSistema () {
        for (Presa presa : prese) {
            if (presa.hasLampadina())
                presa.getLampadina().setOff();
        }
    }

    public void eliminaLampadina (String nome) {
        prese.get(cercaIndiceLampadina(nome)).eliminaLampadina();
    }
}
