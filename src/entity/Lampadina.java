package entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * La classe `Lampadina` rappresenta una lampadina con un nome, una potenza, un indice di luminosità (QI) e uno stato acceso/spento.
 * Implementa l'interfaccia `Serializable` per permettere la serializzazione della classe.
 */
public class Lampadina implements Serializable {
    @Serial
    private static final long serialVersionUID = 21471294827194L;

    public final String nome;
    private final float potenza;
    private int QI = 60;
    private boolean isOn = false;

    /**
     * Costruisce una `Lampadina` con il nome e la potenza specificati.
     *
     * @param nome il nome della lampadina
     * @param potenza la potenza della lampadina
     */
    public Lampadina(String nome, float potenza) {
        this.nome = nome;
        this.potenza = potenza;
    }

    /**
     * Restituisce la potenza della lampadina.
     *
     * @return la potenza della lampadina
     */
    public float getPotenza() {
        return potenza;
    }

    /**
     * Restituisce l'indice di luminosità (QI) della lampadina.
     *
     * @return l'indice di luminosità (QI) della lampadina
     */
    public int getQI() {
        return QI;
    }

    /**
     * Restituisce lo stato acceso/spento della lampadina.
     *
     * @return true se la lampadina è accesa, false altrimenti
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Imposta l'indice di luminosità (QI) della lampadina.
     * Il QI è limitato tra 0 e 100.
     *
     * @param QI il nuovo indice di luminosità (QI) della lampadina
     */
    public void setQI(int QI) {
        if(QI < 0){
            this.QI = 0;
        } else {
            this.QI = Math.min(QI, 100);
        }
    }

    /**
     * Accende la lampadina.
     */
    public void setOn() {
        this.isOn = true;
    }

    /**
     * Spegne la lampadina.
     */
    public void setOff(){
        this.isOn = false;
    }

    /**
     * Restituisce una rappresentazione in stringa della lampadina.
     *
     * @return una rappresentazione in stringa della lampadina
     */
    @Override
    //non usato ma comodo da avere
    public String toString() {
        return "Lampadina{" +
                "\nnome=" + nome  +
                "\npotenza=" + potenza +
                "\nQI=" + QI +
                "\nisOn=" + isOn +
                "\n}";
    }
}
