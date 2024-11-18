package ui;

import entity.Sistema;
import graphics.*;

public class SistemaLayer {
    public void disegnaSistema(Sistema s){
        Picture pic = new Picture(s.getNome() + ".png");
    }
}
