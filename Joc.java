package PE08_SanchezMiguel_rpg;

import java.util.Scanner;

public class Joc {

    static Scanner sc = new Scanner(System.in);

    static Personatge[] personatges = new Personatge[10];
    static int totalPersonatges = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        int opcio;

        do {

            System.out.println("\n--- MENU RPG ---");
            System.out.println("1. Crear personatge");
            System.out.println("2. Afegir arma a personatge");
            System.out.println("3. Mostrar personatges");
            System.out.println("0. Sortir");

            opcio = sc.nextInt();

            switch (opcio) {

                case 1:
                    crearPersonatge();
                    break;

                case 2:
                    afegirArma();
                    break;

                case 3:
        
                    break;

                case 0:
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (opcio != 0);
    }

    public static void crearPersonatge() {

    if (totalPersonatges >= personatges.length) {
        System.out.println("Maxim de personatges.");
        return;
    }

    System.out.println("Nom:");
    String nom = sc.next();

    System.out.println("Edat:");
    int edat = sc.nextInt();

    int puntsRestants = 60;

    int forca = demanarCaracteristica("Força", puntsRestants);
    puntsRestants -= forca;

    int destresa = demanarCaracteristica("Destresa", puntsRestants);
    puntsRestants -= destresa;

    int constitucio = demanarCaracteristica("Constitució", puntsRestants);
    puntsRestants -= constitucio;

    int inteligencia = demanarCaracteristica("Intel·ligència", puntsRestants);
    puntsRestants -= inteligencia;

    int saviesa = demanarCaracteristica("Saviesa", puntsRestants);
    puntsRestants -= saviesa;

    int carisma = puntsRestants;

    System.out.println("Carisma assignat automaticament: " + carisma);

    personatges[totalPersonatges] =
            new Personatge(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);

    totalPersonatges++;

    System.out.println("Personatge creat!");
}

    public static int demanarCaracteristica(String nom, int puntsRestants) {

        int valor;

        do {

            System.out.println(nom + " (5-20) | punts restants: " + puntsRestants);
            valor = sc.nextInt();

            if (valor < 5 || valor > 20 || valor > puntsRestants) {
                System.out.println("Valor incorrecte.");
            }

        } while (valor < 5 || valor > 20 || valor > puntsRestants);

        return valor;
    }

    public static Personatge escollirPersonatge() {

        if (totalPersonatges == 0) {
            System.out.println("No hi ha personatges.");
            return null;
        }

        System.out.println("Escull personatge:");

        for (int i = 0; i < totalPersonatges; i++) {
        System.out.println(i + " - " + personatges[i].getNom());
        }

        int pos = sc.nextInt();

        if (pos >= 0 && pos < totalPersonatges) {
            return personatges[pos];
        }

        return null;
    }


    public static void afegirArma() {

        Personatge p = escollirPersonatge();

        if (p == null) return;

        System.out.println("Nom arma:");
        String nom = sc.next();

        System.out.println("Tipus:");
        String tipus = sc.next();

        System.out.println("Dany (1-100):");
        int dany = sc.nextInt();

        System.out.println("Es magica? true/false:");
        boolean magica = sc.nextBoolean();

        Arma a = new Arma(nom, tipus, dany, magica);

        p.afegirArma(a);

        System.out.println("Arma afegida!");
    }

    public static void mostrarPersonatges() {

        if (totalPersonatges == 0) {
            System.out.println("No hi ha personatges.");
            return;
        }

        for (int i = 0; i < totalPersonatges; i++) {

            System.out.println("\nPersonatge " + i);
            personatges[i].mostrarPersonatge();

        }
    }
}