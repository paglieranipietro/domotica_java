package controller;

import dao.Configurazione;
import entity.Sistema;
import ui.MenuSelezione;
import ui.SistemaLayer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
     * Gestisce gli eventi di click del menu in base all'evento del mouse.
     *
     * @param e l'evento del mouse
     * @return true se un elemento del menu è stato cliccato, false altrimenti
     */
    private boolean cliccoMenu(MouseEvent e) {
        if (OperazioniUtente.haCliccato(MenuSelezione.CESTINO, e)) {
            if (e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(0, e);
        } else if (OperazioniUtente.haCliccato(MenuSelezione.PIU, e)) {
            if (e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(1, e);
        } else if (OperazioniUtente.haCliccato(MenuSelezione.MENO, e)) {
            if (e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(2, e);
        } else if (OperazioniUtente.haCliccato(MenuSelezione.COLORI, e)) {
            if (e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(4, e);
        } else if (OperazioniUtente.haCliccato(MenuSelezione.ONOFF, e)) {
            if (e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(5, e);
        } else if (MouseEvent.BUTTON3 == e.getButton() && sistema.presaCliccata(e) == null) {
                prevTime = OperazioniUtente.operazioneCodice(3, e);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Gestisce gli eventi di click del mouse.
     *
     * @param e l'evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getWhen() <= prevTime) {
            return;
        }
        e = new MouseEvent((Component) e.getSource(), 0, 0, 0, e.getX() - 10, e.getY() - 32, 0, false, e.getButton());
        if (e.getButton() == MouseEvent.BUTTON1) {
            String nomePresa = sistema.presaCliccata(e);
            if (nomePresa != null) {
                OperazioniUtente.presaSelezionata = nomePresa;
                MenuSelezione.disegna(sistema.cercaPresa(OperazioniUtente.presaSelezionata));
                return;
            }
        }
        if (cliccoMenu(e)) {
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON1 && OperazioniUtente.haCliccato(SistemaLayer.salvataggio, e)) {
            try {
                Configurazione.salvaSistema(sistema);
                System.out.println("Salvataggio effettuato");
            } catch (IOException ex) {
                System.err.println("Salvamento fallito: " + ex.getMessage());
                //ex.printStackTrace();   //uncomment se voglio vedere lo stack degli errori
            }
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON1 && OperazioniUtente.haCliccato(SistemaLayer.onOff, e)) {
            OperazioniUtente.onOffSistema();
        }
        OperazioniUtente.presaSelezionata = null;
        MenuSelezione.togli();
    }
}
