package controller;

import dao.Configurazione;
import entity.Lampadina;
import entity.Presa;
import entity.Sistema;
import graphics.Canvas;
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

    /**
     * Numero che indica la modalita' dell'utente
     * 0 per modalita' selezione
     * 1 per modalita' aggiungi presa
     */
    protected static int modalitaUtente;

    /**
     * Indicazione per utente in modalita' 1
     */
    private static final Text info = new Text(-100,-100,"Clicca dove vuoi aggiungere una presa!");
    protected static boolean infoPresaVisibile = false;
    private static boolean scrittaPotenzaVisibile = false;
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
        System.out.println("Inserisci il nome della lampadina: ");
        nome = USER_IN.nextLine();
        while(sistema.cercaLampadina(nome) != null){
            System.out.println("Questo nome esiste gia'");
            System.out.println("Inserisci il nome della lampadina: ");
            nome = USER_IN.nextLine();
        }
        while (flag) {
            try {
                System.out.println("Inserisci la potenza della lampadina: ");
                potenza = USER_IN.nextFloat();
                flag = false;
            } catch (InputMismatchException e) {
                String inserimento = USER_IN.nextLine();
                System.out.println(inserimento + " non e' un numero");
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
        while(sistema.cercaPresa(nome) != null){
            System.out.println("Questo nome esiste gia'");
            System.out.println("Inserisci il nome della presa: ");
            nome = USER_IN.nextLine();
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
        Presa pSelezionata = sistema.cercaPresa(presaSelezionata);
        switch (codice) {
            case 0: // eliminare una presa o una lampadina
                if (pSelezionata.haLampadina()){
                    sistema.eliminaLampadina(presaSelezionata);
                }else {
                    sistema.eliminaPresa(presaSelezionata);
                }
                presaSelezionata = null;
                break;
            case 1: // aumentare la luminosita' o aggiungere una lampadina
                if (pSelezionata.haLampadina()) {
                    sistema.aumentaLuminosita(presaSelezionata);
                    MenuInformazioni.mostraInformazioni(pSelezionata);
                } else {
                    Canvas.getInstance().frame.setVisible(false);
                    sistema.aggiungiLampadinaAPresa(presaSelezionata, OperazioniUtente.letturaInfoLampadina());
                    presaSelezionata = null;
                    Canvas.getInstance().frame.setVisible(true);
                    Canvas.getInstance().frame.toFront();
                    Canvas.getInstance().frame.requestFocus();
                }
                break;
            case 2: // diminuire la luminosita'
                if (pSelezionata.haLampadina()) {
                    sistema.diminuisciLuminosita(presaSelezionata);
                    MenuInformazioni.mostraInformazioni(pSelezionata);
                }
                break;
            case 3: // creare una nuova presa
                Canvas.getInstance().frame.setVisible(false);
                String stanza = ottieniStanza(point);
                if(null != stanza){
                    Presa p = letturaInfoPresa(point);
                    p.setStanza(stanza);
                    sistema.aggiungiPresa(p);
                } else {
                    System.out.println("Posizione invalida");
                }
                Canvas.getInstance().frame.setVisible(true);
                Canvas.getInstance().frame.toFront();
                Canvas.getInstance().frame.requestFocus();
                presaSelezionata = null;
                modalitaUtente = 0;
                break;
            case 4: // cambiare il colore ad una lampadina
                sistema.cercaPresa(presaSelezionata).changeColor();
                sistema.cercaPresa(presaSelezionata).drawColor();
                break;
            case 5: // accendere o spegnere il sistema
                sistema.accendiSistema();
                presaSelezionata = null;
                break;
            case 6: // entrata in modalita' 1
                modalitaUtente = 1;
                presaSelezionata = null;
                break;
            case 7 : // salvataggio del sistema
                try {
                    Configurazione.salvaSistema(sistema);
                    System.out.println("Sistema aggiunto!");
                } catch (IOException e) {
                    System.out.println("Salvataggio fallito");
                }
                presaSelezionata = null;
                break;
            case 8: // accendi/spegni lampadina
                pSelezionata.onOffLampadina();
                break;
            case 9: // mostrare le informazioni di una presa/lampadina
                infoPresaVisibile = !infoPresaVisibile;
                break;
            case 10: // mostrare la potenza totale del sistema
                scrittaPotenzaVisibile = !scrittaPotenzaVisibile;
                presaSelezionata = null;
        }
        togliScrittaPotenza();
        if(scrittaPotenzaVisibile){
            potenzaSis = new Text(450,680,"Potenza totale: " + sistema.getPotenzaSistema() + "W");
            potenzaSis.draw();
        }
        disegnaScritta();
        // togliere il menu di selezione e informazioni
        if(presaSelezionata == null){
            infoPresaVisibile = false;
            MenuSelezione.togli();
        }
        aggiornamentoInfoPresa();
        SistemaLayer.disegnaPrese();
        return System.currentTimeMillis();
    }

    /**
     * Si occupa di mostrare o meno l'informazione in modalita' utente 1
     */
    public static void disegnaScritta(){
        if(modalitaUtente == 1){
            info.setPosition(600,680);
        } else {
            info.setPosition(-100,-100);
        }
    }

    /**
     * Scrive le informazioni presa se devono essere scritte
     */
    public static void aggiornamentoInfoPresa(){
        if(infoPresaVisibile){
            MenuInformazioni.mostraInformazioni(sistema.cercaPresa(presaSelezionata));
        } else {
            MenuInformazioni.togli();
        }
    }

    /**
     * Nasconde la scritta potenza
     */
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
