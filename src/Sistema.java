import java.util.ArrayList;

public class Sistema {
    String nome;
    private ArrayList<Lampadina> lampadine;

    public Sistema(String nome){
        this.nome = nome;
        lampadine = new ArrayList<>();
    }

    public String getNome(){return this.nome;}

    public Lampadina cercaLampadina (String nome){
        for (int i = 0; i < lampadine.size(); i++) {
            if (lampadine.get(i).getNome().equals(nome)){
                return lampadine.get(i);
            }
        }
        return null;
    }

    public int cercaIndiceLampadina (String nome){
        for (int i = 0; i < lampadine.size(); i++) {
            if (lampadine.get(i).getNome().equals(nome)){
                return i;
            }
        }
        return -1;
    }
    public void aggiungiLampadina (Lampadina lampadina) {
        this.lampadine.add(lampadina);
    }

    public void aumentaLuminosita (String nome) {
        cercaLampadina(nome).setQI(cercaLampadina(nome).getQI() < 90 ? cercaLampadina(nome).getQI() + 10 : 100);
    }

    public void diminuisciLuminosita (String nome) {
        cercaLampadina(nome).setQI(cercaLampadina(nome).getQI() > 10 ? cercaLampadina(nome).getQI() - 10 : 0);
    }

    public void modificaLuminosita (String nome, int QI){
        cercaLampadina(nome).setQI(QI);
    }

    public void modificaColore (String nome, String colore) {
        cercaLampadina(nome).setColore(colore);
    }

    public float getPotenzaSistema (){
        float totale = 0;
        for (int i = 0; i < lampadine.size(); i++) {
            if (lampadine.get(i).isOn()){
                totale += lampadine.get(i).getPotenza() * lampadine.get(i).getQI() / 100;
            }
        }
        return totale;
    }

    public void accendiLampadina (String nome) {
        cercaLampadina(nome).setOn();
    }
    public void spegniLampadina (String nome) {
        cercaLampadina(nome).setOff();
    }

    public void accendiSistema () {
        for (int i = 0; i < lampadine.size(); i++) {
            lampadine.get(i).setOn();
        }
    }

    public void spegniSistema () {
        for (int i = 0; i < lampadine.size(); i++) {
            lampadine.get(i).setOff();
        }
    }

    public void eliminaLampadina (String nome) {
        lampadine.remove(cercaIndiceLampadina(nome));
    }
}
