import entity.Presa;
import entity.Sistema;
import ui.SistemaLayer;

public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema("s1");
        s.aggiungiPresa(new Presa("p1","st","zo",100,100));
        s.aggiungiPresa(new Presa("p2","st","zo",200,300));
        SistemaLayer.inizializza(s);

    }
}