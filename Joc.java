import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Joc {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static Personatge[] personatges = new Personatge[10];
    static int totalPersonatges = 0;

    public static void main(String[] args) {
        menu();
    }

    public static int llegirInt() {
        while (true) {
            try {
                int valor = sc.nextInt();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Valor incorrecte. Introdueix un número.");
                sc.next();
            }
        }
    }

    public static boolean llegirBoolean() {
        while (true) {
            try {
                boolean valor = sc.nextBoolean();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Valor incorrecte. Escriu true o false.");
                sc.next();
            }
        }
    }

    public static void menu() {

        int opcio;

        do {

            System.out.println("\n--- MENU RPG ---");
            System.out.println("1. Crear personatge");
            System.out.println("2. Afegir arma a personatge");
            System.out.println("3. Mostrar personatges");
            System.out.println("4. Combat 1 vs 1");
            System.out.println("5. Veure estadistiques personatge (per si el creas automatic)");
            System.out.println("0. Sortir");

            opcio = llegirInt();

            switch (opcio) {

                case 1:
                    crearPersonatge();
                    break;

                case 2:
                    afegirArma();
                    break;

                case 3:
                    mostrarPersonatges();
                    break;

                case 4:
                    combat();
                    break;

                case 5:
                    veureEstadistiques();
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
        int edat = llegirInt();

        System.out.println("1 Manual");
        System.out.println("2 Automatic");

        int tipus = llegirInt();

        int forca, destresa, constitucio, inteligencia, saviesa, carisma;

        if (tipus == 1) {

            int puntsRestants = 60;

            forca = demanarCaracteristica("Força", puntsRestants);
            puntsRestants -= forca;

            destresa = demanarCaracteristica("Destresa", puntsRestants);
            puntsRestants -= destresa;

            constitucio = demanarCaracteristica("Constitució", puntsRestants);
            puntsRestants -= constitucio;

            inteligencia = demanarCaracteristica("Intel·ligència", puntsRestants);
            puntsRestants -= inteligencia;

            saviesa = demanarCaracteristica("Saviesa", puntsRestants);
            puntsRestants -= saviesa;

            carisma = puntsRestants;

            System.out.println("Carisma assignat automaticament: " + carisma);

        } else {

            forca = rand.nextInt(16) + 5;
            destresa = rand.nextInt(16) + 5;
            constitucio = rand.nextInt(16) + 5;
            inteligencia = rand.nextInt(16) + 5;
            saviesa = rand.nextInt(16) + 5;
            carisma = rand.nextInt(16) + 5;

        }

        System.out.println("Raza:");
        System.out.println("1 Huma");
        System.out.println("2 Elf");
        System.out.println("3 Orc");
        System.out.println("4 Nan");

        int raza = llegirInt();

        switch (raza) {
            case 1:
                personatges[totalPersonatges] =
                    new Huma(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
                break;

            case 2:
                personatges[totalPersonatges] =
                    new Elf(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
                break;

            case 3:
                personatges[totalPersonatges] =
                    new Orc(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
                break;

            case 4:
                personatges[totalPersonatges] =
                    new Nan(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
                break;

            default:
                System.out.println("Raza no valida");
                return;
        }
        totalPersonatges++;

        System.out.println("Personatge creat!");
    }

    public static void veureEstadistiques() {

        Personatge p = escollirPersonatge();

        if (p == null) return;

        System.out.println("\n--- ESTADISTIQUES DEL PERSONATGE ---");

        p.mostrarPersonatge();

        System.out.println("Armes del personatge:");
        p.mostrarArmes();
    }

    public static int demanarCaracteristica(String nom, int puntsRestants) {

        int valor;

        do {

            System.out.println(nom + " (5-20) | punts restants: " + puntsRestants);
            valor = llegirInt();

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

        int pos = llegirInt();

        if (pos >= 0 && pos < totalPersonatges) {
            return personatges[pos];
        }

        return null;
    }

    public static void afegirArma() {

        Personatge p = escollirPersonatge();

        if (p == null) return;

        System.out.println("1 Crear arma manual");
        System.out.println("2 Crear arma aleatoria");

        int opcio = llegirInt();

        String nom;
        String tipus;
        int dany;
        boolean magica;

        if (opcio == 1) {

            System.out.println("Nom arma:");
            nom = sc.next();

            System.out.println("Tipus:");
            tipus = sc.next();

            System.out.println("Dany (1-100):");
            dany = llegirInt();

            System.out.println("Es magica? true/false:");
            magica = llegirBoolean();

        } else {

            String[] tipusArmes = {"Espasa", "Destral", "Arc", "Bastó"};

            tipus = tipusArmes[rand.nextInt(tipusArmes.length)];
            nom = tipus;
            dany = rand.nextInt(100) + 1;
            magica = rand.nextBoolean();

            System.out.println("Arma generada:");
            System.out.println("Nom: " + nom);
            System.out.println("Tipus: " + tipus);
            System.out.println("Dany: " + dany);
            System.out.println("Magica: " + magica);

        }

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

    public static void combat() {

        if (totalPersonatges < 2) {
            System.out.println("Calen almenys 2 personatges.");
            return;
        }

        System.out.println("Jugador 1 tria personatge");
        Personatge p1 = escollirPersonatge();

        System.out.println("Jugador 2 tria personatge");
        Personatge p2 = escollirPersonatge();

        if (p1 == p2) {
            System.out.println("No poden ser el mateix personatge.");
            return;
        }

        boolean tornJugador1 = true;

        while (p1.getSalut() > 0 && p2.getSalut() > 0) {

            Personatge atacant;
            Personatge defensor;

            if (tornJugador1) {
                atacant = p1;
                defensor = p2;
            } else {
                atacant = p2;
                defensor = p1;
            }

            System.out.println("\nTorn de " + atacant.getNom());
            System.out.println("Vida " + p1.getNom() + ": " + p1.getSalut());
            System.out.println("Vida " + p2.getNom() + ": " + p2.getSalut());

            System.out.println("1 Atacar");
            System.out.println("2 Defensar");
            System.out.println("3 Canviar arma");

            int opcio = llegirInt();

            if (opcio == 1) {

                int dany = atacant.atacar();

                if (defensor.esquivar()) {

                    System.out.println(defensor.getNom() + " ha esquivat l'atac!");

                } else {

                    defensor.rebreDany(dany);
                    System.out.println("Dany fet: " + dany);

                }

            } else if (opcio == 2) {

                atacant.defensar();
                System.out.println(atacant.getNom() + " es defensa.");

            } else if (opcio == 3) {

                atacant.mostrarArmes();
                System.out.println("Tria arma:");
                int pos = llegirInt();

                atacant.equiparArma(pos);
            }

            atacant.regenerarVida();
            atacant.regenerarMana();

            defensor.regenerarVida();
            defensor.regenerarMana();

            System.out.println("\n--- Vida actualitzada ---");
            System.out.println(p1.getNom() + ": " + p1.getSalut());
            System.out.println(p2.getNom() + ": " + p2.getSalut());

            tornJugador1 = !tornJugador1;
        }

        if (p1.getSalut() <= 0) {

            System.out.println("\nHa guanyat " + p2.getNom());
            p2.pujarNivell();

        } else {

            System.out.println("\nHa guanyat " + p1.getNom());
            p1.pujarNivell();

        }
    }
}