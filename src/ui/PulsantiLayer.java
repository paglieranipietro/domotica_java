package ui;

import dao.Configurazione;
import entity.Pulsante;

import java.io.IOException;
import java.util.ArrayList;

public class PulsantiLayer {
    private static ArrayList<Pulsante> listaPulsanti;

    public static void inizializza() throws IOException {
        listaPulsanti = Configurazione.leggiPulsanti();
    }

    public static void mostraPulsante(String nome){
        for (Pulsante pulsante : listaPulsanti){
            if (pulsante.getNome().equals(nome)){
                pulsante.mostra();
            }
        }
    }
    public static void nascondiPulsante(String nome){
        for (Pulsante pulsante : listaPulsanti) {
            if (pulsante.getNome().equals(nome)) {
                pulsante.nascondi();
            }
        }
    }

    public static ArrayList<Pulsante> getListaPulsanti() {
        return listaPulsanti;
    }
}