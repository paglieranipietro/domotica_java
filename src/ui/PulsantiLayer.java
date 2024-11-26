package ui;

import dao.Configurazione;
import entity.Pulsante;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Entita' statica che gestisce il disegno dei pulsanti
 */
public class PulsantiLayer {
    private static ArrayList<Pulsante> listaPulsanti;

    /**
     * Leggere la configurazione dei pulsanti
     * @throws IOException eccezione generale di IO
     */
    public static void inizializza() throws IOException {
        listaPulsanti = Configurazione.leggiPulsanti();
    }

    /**
     * Mostrare un pulsante
     * @param nome nome del pulsante
     */
    public static void mostraPulsante(String nome){
        for (Pulsante pulsante : listaPulsanti){
            if (pulsante.getNome().equals(nome)){
                pulsante.mostra();
            }
        }
    }

    /**
     * Nascondere un pulsante
     * @param nome nome pulsante
     */
    public static void nascondiPulsante(String nome){
        for (Pulsante pulsante : listaPulsanti) {
            if (pulsante.getNome().equals(nome)) {
                pulsante.nascondi();
            }
        }
    }

    /**
     * Ottenere la lista dei pulsanti
     * @return 'listaPulsnti'
     */
    public static ArrayList<Pulsante> getListaPulsanti() {
        return listaPulsanti;
    }
}