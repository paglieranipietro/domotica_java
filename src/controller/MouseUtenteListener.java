package controller;

import dao.Configurazione;
import entity.Lampadina;
import entity.Presa;
import entity.Sistema;
import graphics.Picture;
import ui.MenuSelezione;
import ui.SistemaLayer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MouseUtenteListener extends MouseAdapter{
    protected static final Object lock = new Object();
    private final Sistema sistema;
    public MouseUtenteListener(Sistema sistema) {
        this.sistema = sistema;
    }

    private long prevTime = 0;
    private boolean cliccoMenu(MouseEvent e){
        if(OperazioniUtente.haCliccato(MenuSelezione.CESTINO,e)){
            if(e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(0,e);
        } else if(OperazioniUtente.haCliccato(MenuSelezione.PIU,e)){
            if(e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(1,e);
        } else if(OperazioniUtente.haCliccato(MenuSelezione.MENO,e)){
            if(e.getButton() == MouseEvent.BUTTON1)
                prevTime = OperazioniUtente.operazioneCodice(2,e);
        } else if(MouseEvent.BUTTON3 == e.getButton() && sistema.presaCliccata(e) == null){
            prevTime = OperazioniUtente.operazioneCodice(3,e);
        } else {
            return false;
        }
        return true;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getWhen() <= prevTime){
            return;
        }
        e = new MouseEvent((Component) e.getSource(),0,0,0,e.getX()-10,e.getY()-32,0,false,e.getButton());
        if(e.getButton() == MouseEvent.BUTTON1){
            String nomePresa = sistema.presaCliccata(e);
            if(nomePresa != null){
                OperazioniUtente.presaSelezionata = nomePresa;
                MenuSelezione.disegna(sistema.cercaPresa(OperazioniUtente.presaSelezionata));
                return;
            }
        }
        if(cliccoMenu(e)){
            return;
        }
        if(e.getButton() == MouseEvent.BUTTON2){
            synchronized (lock){
                new Thread(() -> {
                    try {
                        Configurazione.salvaSistema(sistema);
                    } catch (IOException ex) {
                        System.out.println("Salvamento fallito"); // TODO salvataggio del sistema
                    }
                    synchronized (lock){
                        lock.notify();
                    }
                }).start();
                try {
                    MouseUtenteListener.lock.wait();
                } catch (InterruptedException err) {
                    //err.printStackTrace();
                }
            }

            return;
        }
        OperazioniUtente.presaSelezionata = null;
        MenuSelezione.togli();
    }
}
