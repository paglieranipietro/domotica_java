package dao;

import entity.*;
import graphics.Rectangle;

import java.io.*;
import java.util.ArrayList;

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

    /**
     * Leggere le stanze dal file "src/config/stanze.config"
     * @return 'stanze' lista
     * @throws IOException se ci sono errori generici di IO
     */
    public static ArrayList<Stanza> leggiStanze() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/config/stanze.config"));
        String nomeStanza;
        ArrayList<Stanza> stanze = new ArrayList<>();
        while (null != (nomeStanza = br.readLine())){
            String posizione = br.readLine();
            String[] p = posizione.split(" ");
            Rectangle rc = new Rectangle(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]));
            stanze.add(new Stanza(rc,nomeStanza));
        }
        br.close();
        return stanze;
    }

    /**
     * Legge i pulsanti dal file "src/config/pulsanti.config"
     * @return 'pulsanti' lista
     * @throws IOException
     */
    public static ArrayList<Pulsante> leggiPulsanti() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/config/pulsanti.config"));
        String riga;
        ArrayList<Pulsante> pulsanti = new ArrayList<>();
        while (null != (riga = br.readLine())){
            String[] p = riga.split(" ");
            String pathImmagine = br.readLine();
            boolean mostrare = Boolean.parseBoolean(p[4]);
            Pulsante pul = new Pulsante(p[0],Integer.parseInt(p[1]),Integer.parseInt(p[2]),pathImmagine,Integer.parseInt(p[3]));
            if(mostrare){
                pul.mostra();
            } else {
                pul.nascondi();
            }
            pulsanti.add(pul);
        }
        br.close();
        return pulsanti;
    }
}
