import controller.MouseUtenteListener;
import controller.OperazioniUtente;
import dao.Configurazione;
import entity.Sistema;
import graphics.Canvas;
import ui.MenuSelezione;
import ui.SistemaLayer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Sistema s;
        try {
            s = Configurazione.leggiSistema();
        } catch (IOException | ClassNotFoundException e) {
            s = new Sistema();
        }
        SistemaLayer.inizializza(s);
        MenuSelezione.inizializza();
        OperazioniUtente.inizializza(s);
        Canvas.getInstance().frame.addMouseListener(new MouseUtenteListener(s));
    }
}