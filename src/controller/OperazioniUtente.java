package controller;

import entity.Lampadina;
import entity.Presa;
import entity.Sistema;
import graphics.Picture;
import ui.MenuSelezione;
import ui.SistemaLayer;

import java.awt.event.MouseEvent;
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

    /**
     * Inizializza il sistema con l'oggetto `Sistema` specificato.
     *
     * @param s l'oggetto `Sistema` da inizializzare
     */
    public static void inizializza(Sistema s) {
        sistema = s;
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
     * Legge le informazioni di una presa dall'input dell'utente e dall'evento del mouse.
     *
     * @param e l'evento del mouse contenente le coordinate della presa
     * @return un oggetto `Presa` con il nome e le coordinate inseriti
     */
    protected static Presa letturaInfoPresa(MouseEvent e) {
        String nome;
        Scanner USER_IN = new Scanner(System.in);
        System.out.println("Inserisci il nome della presa: ");
        nome = USER_IN.nextLine();
        return new Presa(nome, e.getX(), e.getY());
    }

    /**
     * Esegue un'operazione basata sul codice fornito e sull'evento del mouse.
     *
     * @param codice il codice dell'operazione
     * @param e      l'evento del mouse
     * @return il tempo corrente del sistema in millisecondi
     */
    protected static long operazioneCodice(int codice, MouseEvent e) {
        switch (codice) {
            case 0:
                if (sistema.cercaPresa(presaSelezionata).haLampadina())
                    sistema.eliminaLampadina(presaSelezionata);
                else {
                    sistema.eliminaPresa(presaSelezionata);
                    presaSelezionata = null;
                }
                MenuSelezione.togli();
                break;
            case 1:
                if (sistema.cercaPresa(presaSelezionata).haLampadina()) {
                    sistema.aumentaLuminosita(presaSelezionata);
                } else {
                    sistema.aggiungiLampadinaAPresa(presaSelezionata, OperazioniUtente.letturaInfoLampadina());
                    MenuSelezione.togli();
                }
                break;
            case 2:
                if (sistema.cercaPresa(presaSelezionata).haLampadina()) {
                    sistema.diminuisciLuminosita(presaSelezionata);
                }
                break;
            case 3:
                Presa p = letturaInfoPresa(e);
                sistema.aggiungiPresa(p);
                p.draw();
                break;
            case 4:
                sistema.cercaPresa(presaSelezionata).changeColor(sistema.cercaPresa(presaSelezionata).getColore());
                sistema.cercaPresa(presaSelezionata).drawColor(sistema.cercaPresa(presaSelezionata).getColore());
                break;
            case 5:
                sistema.cercaPresa(presaSelezionata).onOffLampadina();
                break;
        }
        SistemaLayer.disegnaPrese();
        return System.currentTimeMillis();
    }

    /**
     * Attiva o disattiva tutte le lampadine nel sistema.
     */
    protected static void onOffSistema() {
        for (Presa p : sistema.getPrese()) {
            if (p.haLampadina()) {
                p.onOffLampadina();
            }
        }
        SistemaLayer.disegnaPrese();
        System.currentTimeMillis();
    }

    /**
     * Verifica se l'immagine specificata è stata cliccata in base all'evento del mouse.
     *
     * @param p l'immagine da verificare
     * @param e l'evento del mouse
     * @return true se l'immagine è stata cliccata, false altrimenti
     */
    public static boolean haCliccato(Picture p, MouseEvent e) {
        float lx = p.getX();
        float ly = p.getY();
        float rx = p.getX() + p.getWidth();
        float ry = p.getY() + p.getHeight();
        return e.getX() >= lx && e.getX() <= rx && e.getY() >= ly && e.getY() <= ry;
    }
}
