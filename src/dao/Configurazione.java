package dao;

import entity.Presa;
import entity.Sistema;

import java.io.*;

public class Configurazione {
    public static Sistema leggiSistema() throws IOException, ClassNotFoundException {
        File f = new File("src/config/sistema.dat");
        if(f.length() == 0){
            return new Sistema();
        }
        FileInputStream fin = new FileInputStream("src/config/sistema.dat");
        ObjectInputStream in = new ObjectInputStream(fin);
        Sistema s = (Sistema) in.readObject();
        for(Presa p : s.getPrese()){
            p.inizializzaImmagini();
        }
        in.close();
        fin.close();
        return s;
    }

    public static void salvaSistema(Sistema s) throws IOException {
        FileOutputStream fout = new FileOutputStream("src/config/sistema.dat");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(s);
        out.close();
        fout.close();
    }
}
