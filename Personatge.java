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

    Random rand = new Random();

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

        } else {

            System.out.println("Inventari ple");

        }
    }

     public void mostrarArmes() {

        if (totalArmes == 0) {

            System.out.println("No té armes.");
            return;

        }

        for (int i = 0; i < totalArmes; i++) {

            System.out.println(i + " - " + inventari[i].getNom());

        }
    }

    public void equiparArma(int posicio) {

        if (posicio < 0 || posicio >= totalArmes) {

            System.out.println("Posicio incorrecta");
            return;

        }

        Arma a = inventari[posicio];

        if (a.esMagica() && inteligencia < 10) {

            System.out.println("No tens intel·ligència suficient per arma màgica");

        } else {

            armaEquipada = a;
            System.out.println("Arma equipada: " + a.getNom());

        }
    }

    public int atacar() {

        int dany;

        if (armaEquipada == null || !armaEquipada.esMagica()) {

            int danyArma = 0;

            if (armaEquipada != null) {
                danyArma = armaEquipada.getDany();
            }

            dany = (int) (forca * (1 + danyArma / 100.0));

        } else {

            dany = (armaEquipada.getDany() * inteligencia) / 100;

        }

        return dany;
    }

    public void rebreDany(int dany) {

        if (defensant) {

            dany = dany / 2;
            defensant = false;

        }

        salut -= dany;

        if (salut < 0) {

            salut = 0;

        }
    }
    
    public boolean esquivar() {

        double prob = (destresa - 5) * 3.33;

        int num = rand.nextInt(100) + 1;

        return num <= prob;
    }

    public void defensar() {

        defensant = true;

    }

    public void regenerarVida() {

        salut += constitucio * 3;

        if (salut > calcularSalutMax()) {

            salut = calcularSalutMax();

        }
    }

    public void regenerarMana() {

        mana += inteligencia * 2;

        if (mana > calcularManaMax()) {

            mana = calcularManaMax();

        }
    }

    public void pujarNivell() {

        nivell++;

        forca++;
        constitucio++;

        salut = calcularSalutMax();
        mana = calcularManaMax();

    }

    public void mostrarPersonatge() {

        System.out.println("Nom: " + nom);
        System.out.println("Edat: " + edat);
        System.out.println("Salut: " + salut);
        System.out.println("Mana: " + mana);
        System.out.println("Nivell: " + nivell);
        System.out.println("Armes:");
        mostrarArmes();
    }

    public String getNom() {
    return nom;
}

    public int getSalut() {
            return salut;
        }

}
