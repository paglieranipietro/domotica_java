import entity.Lampadina;
import entity.Sistema;
import entity.Sistemi;

import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("""
                0 - Crea un sistema
                1 - Aggiungi lampadina al sistema
                2 - Aumenta luminosita lampadina
                3 - Diminuisci luminosita lampadina
                4 - Modifica luminosita lampadina
                5 - Modifica colore lampadina
                6 - Visualizza potenza totale del sistema
                7 - Accendi lampadina
                8 - Spegni lampadina
                9 - Accendi sistema
                10- Spegni sistema
                11- Elimina lampadina""");
    }
    public static String sceltaSistema(){
        Scanner in = new Scanner(System.in);
        System.out.println("Quale sistema vuoi gestire? (inserisci il nome)");
        return in.next();
    }
    public static String sceltaLampadina(){
        Scanner in = new Scanner(System.in);
        System.out.println("Quale lampadina vuoi gestire? (inserisci il nome)");
        return in.next();
    }
    public static void main(String[] args) {
        Sistemi s = new Sistemi();
        Scanner in = new Scanner(System.in);
        int scelta;
        String nomeSistema;
        String nomeLampadina;
        boolean isOk = true;
        while(isOk){
            menu();
            scelta = in.nextInt();
            switch (scelta) {
                case 0 -> {
                    System.out.println("Inserisci il nome del sistema:");
                    s.aggiungiSistema(new Sistema(in.next()));
                }
                case 1 -> {
                    nomeSistema = sceltaSistema();
                    System.out.println("Inserisci nome e potenza della nuova lampadina:");
                    s.cercaSistema(nomeSistema).aggiungiLampadina(new Lampadina(in.next(), in.nextFloat()));
                }
                case 2 -> s.cercaSistema(sceltaSistema()).aumentaLuminosita(sceltaLampadina());
                case 3 -> s.cercaSistema(sceltaSistema()).diminuisciLuminosita(sceltaLampadina());
                case 4 -> {
                    nomeSistema = sceltaSistema();
                    nomeLampadina = sceltaLampadina();
                    System.out.println("Inserisci la nuova luminosita:");
                    s.cercaSistema(nomeSistema).modificaLuminosita(nomeLampadina, in.nextInt());
                }
                case 5 -> {
                    nomeSistema = sceltaSistema();
                    nomeLampadina = sceltaLampadina();
                    System.out.println("Inserisci il nuovo colore (bianco, giallo o blu):");
                    s.cercaSistema(nomeSistema).modificaColore(nomeLampadina, in.next());
                }
                case 6 ->
                        System.out.println("La potenza totale del sistema risulta: " + s.cercaSistema(sceltaSistema()).getPotenzaSistema());
                case 7 -> {
                    s.cercaSistema(sceltaSistema()).accendiLampadina(sceltaLampadina());
                    System.out.println("entity.Lampadina accesa");
                }
                case 8 -> {
                    s.cercaSistema(sceltaSistema()).spegniLampadina(sceltaLampadina());
                    System.out.println("entity.Lampadina spenta");
                }
                case 9 -> {
                    s.cercaSistema(sceltaSistema()).accendiSistema();
                    System.out.println("entity.Sistema acceso");
                }
                case 10 -> {
                    s.cercaSistema(sceltaSistema()).spegniSistema();
                    System.out.println("entity.Sistema spento");
                }
                case 11 -> {
                    s.cercaSistema(sceltaSistema()).eliminaLampadina(sceltaLampadina());
                    System.out.println("entity.Lampadina eliminata");
                }
                case 12 ->
                    isOk = false;
            }
        }
    }
}