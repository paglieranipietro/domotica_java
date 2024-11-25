package dao;

import entity.Presa;
import entity.Sistema;

import java.io.*;

/**
 * La classe `Configurazione` fornisce metodi per leggere e salvare l'oggetto `Sistema`
 * da e verso un file. Per farlo tilizza la serializzazione così da rendere persistente lo stato del `Sistema`.
 */
public class Configurazione {

    /**
     * Legge l'oggetto `Sistema` da un file.
     * Se il file è vuoto, restituisce un nuovo oggetto `Sistema` vuoto.
     *
     * @return l'oggetto `Sistema` letto dal file
     * @throws IOException se si verifica un errore di I/O
     * @throws ClassNotFoundException se la classe di un oggetto serializzato non può essere trovata
     */
    public static Sistema leggiSistema() throws IOException, ClassNotFoundException {
        File f = new File("src/config/sistema.dat");
        if (f.length() == 0) {
            return new Sistema();
        }
        FileInputStream fin = new FileInputStream("src/config/sistema.dat");
        ObjectInputStream in = new ObjectInputStream(fin);
        Sistema s = (Sistema) in.readObject();
        for (Presa p : s.getPrese()) {
            p.inizializzaImmagini();
        }
        in.close();
        fin.close();
        return s;
    }

    /**
     * Salva l'oggetto `Sistema` su un file.
     *
     * @param s l'oggetto `Sistema` da salvare
     * @throws IOException se si verifica un errore di I/O
     */
    public static void salvaSistema(Sistema s) throws IOException {
        FileOutputStream fout = new FileOutputStream("src/config/sistema.dat");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(s);
        out.close();
        fout.close();
    }
}
