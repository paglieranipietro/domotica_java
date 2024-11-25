import controller.MouseUtenteListener;
import controller.OperazioniUtente;
import dao.Configurazione;
import entity.Sistema;
import graphics.Canvas;
import ui.SistemaLayer;
import ui.PulsantiLayer;
import java.io.IOException;

public class Main {

    private static void inizializzazione(Sistema s) throws IOException {
        SistemaLayer.inizializza(s);
        s.leggiStanze();
        OperazioniUtente.inizializza(s);
        PulsantiLayer.inizializza();
        Canvas.getInstance().frame.addMouseListener(new MouseUtenteListener(s));
    }

    public static void main(String[] args) {
        Sistema s;
        try {
            s = Configurazione.leggiSistema();
        } catch (IOException | ClassNotFoundException e) {
            s = new Sistema();
        }
        try {
            inizializzazione(s);
        } catch (IOException e) {
            System.out.println("Why???");
        }

    }
}