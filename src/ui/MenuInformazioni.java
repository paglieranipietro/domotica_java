package ui;

import entity.Presa;
import graphics.Text;

public class MenuInformazioni {
    public static Text testo = new Text(-100,-100,"");
    public static void mostraInformazioni(Presa p){
        togli();
        if(p.haLampadina()){
            infoLampadina(p);
        } else {
            infoPresa(p);
        }
    }

    private static void infoLampadina(Presa p){
        String info = "Nome:" + p.getLampadina().nome + "   "
                + "Potenza:" + p.getPotenza() + "   "
                + "Luminosita\':" + p.getLampadina().getQI() + "%   "
                + "Stanza:" + p.getStanza();
        testo = new Text(371,590,info);
        testo.draw();
    }

    private static void infoPresa(Presa p){
        String info = "Nome:" + p.getNome() + "   "
                + "Stanza:" + p.getStanza();
        testo = new Text(371,590,info);
        testo.draw();
    }

    public static void togli(){
        testo.setPosition(-200,-200);
    }
}
