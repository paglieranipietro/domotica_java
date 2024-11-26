package ui;

import entity.Presa;
import graphics.Text;

/**
 * Entita' statica che gestisce il menu delle informazioni di una presa/lampadina
 */
public class MenuInformazioni {
    public static Text testo = new Text(-100,-100,"");

    /**
     * Mostrare le informazioni di una presa considerando se possiede una lampadina o meno
     * @param p presa
     */
    public static void mostraInformazioni(Presa p){
        togli();
        if(p.haLampadina()){
            infoLampadina(p);
        } else {
            infoPresa(p);
        }
    }

    /**
     * Scrive le informazioni di una lampadina
     * @param p presa
     */
    private static void infoLampadina(Presa p){
        String info = "Nome:" + p.getLampadina().nome + "   "
                + "Potenza:" + p.getPotenza() + "   "
                + "Luminosita\':" + p.getLampadina().getQI() + "%   "
                + "Stanza:" + p.getStanza();
        testo = new Text(371,590,info);
        testo.draw();
    }

    /**
     * Scrive le informazioni di una presa
     * @param p presa
     */
    private static void infoPresa(Presa p){
        String info = "Nome:" + p.getNome() + "   "
                + "Stanza:" + p.getStanza();
        testo = new Text(371,590,info);
        testo.draw();
    }

    /**
     * Nascondere le informazioni
     */
    public static void togli(){
        testo.setPosition(-200,-200);
    }
}
