package entity;

import java.util.ArrayList;

public class Sistemi {
    private ArrayList<Sistema> v;

    public Sistemi(){
        this.v = new ArrayList<>();
    }

    public void aggiungiSistema(Sistema sistema){
        this.v.add(sistema);
    }

    public Sistema cercaSistema (String nome){
        for (int i = 0; i < this.v.size(); i++) {
            if (v.get(i).getNome().equals(nome)){
                return v.get(i);
            }
        }
        return null;
    }
}
