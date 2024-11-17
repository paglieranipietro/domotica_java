public class Presa {
    String nome;
    String stanza;
    String zona;
    float x, y;
    Lampadina lampadina;
    public Presa(String nome, String stanza, String zona, float x, float y, Lampadina lampadina) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = zona;
        this.x = x;
        this.y = y;
        this.lampadina = lampadina;
    }
    public Presa(String nome, String stanza, String zona, float x, float y) {
        this.nome = nome;
        this.stanza = stanza;
        this.zona = zona;
        this.x = x;
        this.y = y;
        lampadina = null;
    }
    public void aggiungiLampadina (Lampadina lampadina) {
        this.lampadina = lampadina;
    }
    public void eliminaLampadina (Lampadina lampadina) {
        this.lampadina = null;
    }

    public String getNome() {
        return nome;
    }

    public String getStanza() {
        return stanza;
    }

    public String getZona() {
        return zona;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Lampadina getLampadina() {
        return lampadina;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
