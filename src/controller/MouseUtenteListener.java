package controller;

import entity.Pulsante;
import entity.Sistema;
import ui.MenuInformazioni;
import ui.MenuSelezione;
import ui.PulsantiLayer;

import java.awt.*;
import java.awt.event.*;

/**
 * La classe `MouseUtenteListener` estende `MouseAdapter` per gestire gli eventi del mouse
 * per le interazioni dell'utente con il sistema.
 */
public class MouseUtenteListener extends MouseAdapter {
    private final Sistema sistema;

    /**
     * Costruisce un `MouseUtenteListener` con il `Sistema` specificato.
     *
     * @param sistema il sistema da gestire con questo listener
     */
    public MouseUtenteListener(Sistema sistema) {
        this.sistema = sistema;
    }

    private long prevTime = 0;

    /**
     * Gestisce gli eventi di click del mouse.
     *
     * @param e l'evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // filtrare gli eventi di clicco quando si aspetta l'input dell'utente
        if(e.getWhen() <= prevTime){
            return;
        }
        // mappare la posizione del mouse alla posizione all'interno della finestra
        Point posizione = e.getPoint();
        posizione.x -= 10;
        posizione.y -= 32;
        //System.out.println(STR."\{posizione.getX()}   \{posizione.getY()}");
        // controllare cosa ha cliccato l'utente
        if(e.getButton() == MouseEvent.BUTTON1){
            if(OperazioniUtente.modalitaUtente == 1){
                OperazioniUtente.operazioneCodice(3,posizione);
                return;
            }
            String nomePresa = sistema.presaCliccata(posizione);
            if(nomePresa != null){
                MenuSelezione.togli();
                OperazioniUtente.infoPresaVisibile = false;
                OperazioniUtente.aggiornamentoInfoPresa();
                OperazioniUtente.presaSelezionata = nomePresa;
                MenuSelezione.disegna(sistema.cercaPresa(OperazioniUtente.presaSelezionata));
                return;
            }
            for(Pulsante p : PulsantiLayer.getListaPulsanti()){
                if(OperazioniUtente.haCliccato(p.getImmagine(),posizione)){
                    OperazioniUtente.operazioneCodice(p.codice,posizione);
                    return;
                }
            }
            if(!OperazioniUtente.haCliccato(MenuSelezione.CONTENITORE,posizione)){
                MenuSelezione.togli();
            }
            if(!OperazioniUtente.haCliccato(MenuInformazioni.testo,posizione)){
                MenuInformazioni.togli();
            }
        } else if(OperazioniUtente.modalitaUtente == 1){
            OperazioniUtente.modalitaUtente = 0;
            OperazioniUtente.presaSelezionata = null;
            OperazioniUtente.disegnaScritta();
        }
    }
}
