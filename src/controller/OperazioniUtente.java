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
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicReference;

public class OperazioniUtente {

    protected static String presaSelezionata;
    private static Sistema sistema;
    public static void inizializza(Sistema s){
        sistema = s;

    }
    protected static Lampadina letturaInfoLampadina() {
        AtomicReference<String> nome = new AtomicReference<>();
        AtomicReference<Float> potenza = new AtomicReference<>((float) 0);
        synchronized (MouseUtenteListener.lock){
            new Thread(() -> {
                boolean flag = true;
                while (flag){
                    try {
                        Scanner USER_IN = new Scanner(System.in);
                        System.out.println("Inserisci il nome della lampadina: ");
                        nome.set(USER_IN.nextLine());
                        System.out.println("Inserisci la potenza della lampadina: ");
                        potenza.set(USER_IN.nextFloat());
                        flag = false;
                    } catch (InputMismatchException e){
                        System.out.println("Formato input errato");
                    }
                }

                synchronized (MouseUtenteListener.lock) {
                    MouseUtenteListener.lock.notify();
                }

            }).start();
            try {
                MouseUtenteListener.lock.wait();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
        return new Lampadina(nome.get(), potenza.get());
    }

    protected static Presa letturaInfoPresa(MouseEvent e) {
        AtomicReference<String> nome = new AtomicReference<>();

        synchronized (MouseUtenteListener.lock){
            new Thread(() -> {
                Scanner USER_IN = new Scanner(System.in);
                System.out.println("Inserisci il nome della presa: ");
                nome.set(USER_IN.nextLine());
                synchronized (MouseUtenteListener.lock) {
                    MouseUtenteListener.lock.notify();
                }
            }).start();
            try {
                MouseUtenteListener.lock.wait();
            } catch (InterruptedException err) {
                //err.printStackTrace();
            }
        }
        return new Presa(nome.get(),e.getX(),e.getY());
    }

    protected static long operazioneCodice(int codice,MouseEvent e){
        switch (codice){
            case 0:
                if(sistema.cercaPresa(presaSelezionata).haLampadina())
                    sistema.eliminaLampadina(presaSelezionata);
                else {
                    sistema.eliminaPresa(presaSelezionata);
                    presaSelezionata = null;
                }
                MenuSelezione.togli();
                break;
            case 1:
                if(sistema.cercaPresa(presaSelezionata).haLampadina()){
                    sistema.aumentaLuminosita(presaSelezionata);
                } else {
                    sistema.aggiungiLampadinaAPresa(presaSelezionata,OperazioniUtente.letturaInfoLampadina());
                    MenuSelezione.togli();
                }
                break;
            case 2:
                if(sistema.cercaPresa(presaSelezionata).haLampadina()){
                    sistema.diminuisciLuminosita(presaSelezionata);
                }
            break;
            case 3:
                Presa p = letturaInfoPresa(e);
                sistema.aggiungiPresa(p);
                p.draw();
        }
        SistemaLayer.disegnaPrese();
        return System.currentTimeMillis();
    }

    public static boolean haCliccato(Picture p, MouseEvent e){
        float lx = p.getX();
        float ly = p.getY();
        float rx = p.getX() + p.getWidth();
        float ry = p.getY() + p.getHeight();
        return e.getX() >= lx && e.getX() <= rx && e.getY() >= ly && e.getY() <= ry;
    }
}
