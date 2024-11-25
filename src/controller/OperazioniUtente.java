package controller;

import dao.Configurazione;
import entity.Lampadina;
import entity.Presa;
import entity.Sistema;
import graphics.Shape;
import graphics.Text;
import ui.MenuInformazioni;
import ui.MenuSelezione;
import ui.SistemaLayer;

import java.awt.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La classe `OperazioniUtente` fornisce metodi per le operazioni dell'utente sul sistema,
 * inclusa l'inizializzazione del sistema, la lettura delle informazioni delle lampadine e delle prese,
 * e l'esecuzione di varie operazioni.
 */
public class OperazioniUtente {

    protected static String presaSelezionata;
    private static Sistema sistema;
    protected static int modalitaUtente;
    private static final Text info = new Text(-100,-100,"Clicca dove vuoi aggiungere una presa!");
    private static Text potenzaSis = new Text(-100,-100,"");
    /**
     * Inizializza il sistema con l'oggetto `Sistema` specificato.
     *
     * @param s l'oggetto `Sistema` da inizializzare
     */
    public static void inizializza(Sistema s) {
        sistema = s;
        info.draw();
    }

    /**
     * Legge le informazioni di una lampadina dall'input dell'utente.
     *
     * @return un oggetto `Lampadina` con il nome e la potenza inseriti
     */
    protected static Lampadina letturaInfoLampadina() {
        String nome = null;
        float potenza = 0.0f;
        boolean flag = true;
        Scanner USER_IN = new Scanner(System.in);
        while (flag) {
            try {
                System.out.println("Inserisci il nome della lampadina: ");
                nome = USER_IN.nextLine();
                if(sistema.cercaLampadina(nome) != null){
                    System.out.println("Questo nome esiste gia'");
                    return null;
                }
                System.out.println("Inserisci la potenza della lampadina: ");
                potenza = USER_IN.nextFloat();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Formato input errato");
            }
        }
        return new Lampadina(nome, potenza);
    }

    /**
     * Legge le informazioni di una presa dall'input dell'utente e dalla posizione del mouse.
     *
     * @param e posizione della presa
     * @return un oggetto `Presa` con il nome e le coordinate inseriti
     */
    protected static Presa letturaInfoPresa(Point e) {
        String nome;
        Scanner USER_IN = new Scanner(System.in);
        System.out.println("Inserisci il nome della presa: ");
        nome = USER_IN.nextLine();
        if(sistema.cercaPresa(nome) != null){
            System.out.println("Questo nome esiste gia'");
            return null;
        }
        return new Presa(nome, (float)e.getX(), (float)e.getY());
    }

    private static String ottieniStanza(Point p){
        if(sistema.presaCliccata(p) != null) {
            return null;
        }
        return sistema.stanzaCliccata(p);
    }

    /**
     * Esegue un'operazione basata sul codice fornito e sull'evento del mouse.
     *
     * @param codice il codice dell'operazione
     * @param point      l'evento del mouse
     * @return il tempo corrente del sistema in millisecondi
     */
    protected static long operazioneCodice(int codice, Point point) {
        boolean fPotenza = false;
        switch (codice) {
            case 0:
                if (sistema.cercaPresa(presaSelezionata).haLampadina())
                    sistema.eliminaLampadina(presaSelezionata);
                else {
                    sistema.eliminaPresa(presaSelezionata);
                }
                presaSelezionata = null;
                MenuInformazioni.togli();
                MenuSelezione.togli();
                break;
            case 1:
                if (sistema.cercaPresa(presaSelezionata).haLampadina()) {
                    sistema.aumentaLuminosita(presaSelezionata);
                    MenuInformazioni.togli();
                } else {
                    sistema.aggiungiLampadinaAPresa(presaSelezionata, OperazioniUtente.letturaInfoLampadina());
                    presaSelezionata = null;
                    MenuSelezione.togli();
                    MenuInformazioni.togli();
                }
                break;
            case 2:
                if (sistema.cercaPresa(presaSelezionata).haLampadina()) {
                    sistema.diminuisciLuminosita(presaSelezionata);
                    MenuInformazioni.togli();
                }
                break;
            case 3:
                String stanza = ottieniStanza(point);
                if(null != stanza){
                    Presa p = letturaInfoPresa(point);
                    if(p != null){
                        p.setStanza(stanza);
                        sistema.aggiungiPresa(p);
                    }
                } else {
                    System.out.println("Posizione invalida");
                }
                presaSelezionata = null;
                modalitaUtente = 0;
                break;
            case 4:
                sistema.cercaPresa(presaSelezionata).changeColor();
                sistema.cercaPresa(presaSelezionata).drawColor();
                break;
            case 5:
                sistema.accendiSistema();
                break;
            case 6:
                modalitaUtente = 1;
                presaSelezionata = null;
                break;
            case 7 :
                try {
                    Configurazione.salvaSistema(sistema);
                    System.out.println("Sistema aggiunto!");
                } catch (IOException e) {
                    System.out.println("Salvataggio fallito");
                }
                break;
            case 8:
                sistema.cercaPresa(presaSelezionata).onOffLampadina();
                break;
            case 9:
                MenuInformazioni.mostraInformazioni(sistema.cercaPresa(presaSelezionata));
                break;
            case 10:
                togliScrittaPotenza();
                potenzaSis = new Text(450,680,"Potenza totale: " + sistema.getPotenzaSistema() + "W");
                potenzaSis.draw();
                fPotenza = true;
                MenuInformazioni.togli();
                MenuSelezione.togli();
        }
        if(!fPotenza){
            togliScrittaPotenza();
        }
        disegnaScritta();
        SistemaLayer.disegnaPrese();
        return System.currentTimeMillis();
    }

    public static void disegnaScritta(){
        if(modalitaUtente == 1){
            info.setPosition(600,680);
        } else {
            info.setPosition(-100,-100);
        }
    }

    public static void togliScrittaPotenza(){
        potenzaSis.setPosition(-100,-100);
    }
    /**
     * Verifica se la figura specificata è stata cliccata in base alla posizione del mouse.
     *
     * @param p la figura da verificare
     * @param e la posizione del mouse
     * @return true se la figura è stata cliccata, false altrimenti
     */
    public static boolean haCliccato(Shape p, Point e) {
        float lx = p.getX();
        float ly = p.getY();
        float rx = p.getX() + p.getWidth();
        float ry = p.getY() + p.getHeight();
        return e.getX() >= lx && e.getX() <= rx && e.getY() >= ly && e.getY() <= ry;
    }
}
