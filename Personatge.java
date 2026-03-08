package PE08_SanchezMiguel_rpg;

import java.util.Random;

public class Personatge {

    private String nom;
    private int edat;

    private int salut;
    private int mana;

    private int forca;
    private int destresa;
    private int constitucio;
    private int inteligencia;
    private int saviesa;
    private int carisma;

    private int nivell; // atribut extra

    private Arma[] inventari;
    private int totalArmes;

    private Arma armaEquipada;

    private boolean defensant;

    public Personatge(String nom, int edat,
            int forca, int destresa, int constitucio,
            int inteligencia, int saviesa, int carisma) {

        this.nom = nom;
        this.edat = edat;

        this.forca = forca;
        this.destresa = destresa;
        this.constitucio = constitucio;
        this.inteligencia = inteligencia;
        this.saviesa = saviesa;
        this.carisma = carisma;

        this.salut = calcularSalutMax();
        this.mana = calcularManaMax();

        this.nivell = 1;

        inventari = new Arma[5];
        totalArmes = 0;

        defensant = false;
    }

    public int calcularSalutMax() {
        return constitucio * 50;
    }

    public int calcularManaMax() {
        return inteligencia * 30;
    }

    public void afegirArma(Arma a) {
        if (totalArmes < inventari.length) {
            inventari[totalArmes] = a;
            totalArmes++;
        }
    }
    
    public void mostrarPersonatge() {

        System.out.println("Nom: " + nom);
        System.out.println("Edat: " + edat);
        System.out.println("Salut: " + salut);
        System.out.println("Mana: " + mana);
        System.out.println("Nivell: " + nivell);
    }

    public String getNom() {
    return nom;
}
}
