public class Arma {

    private String nom;
    private String tipus;
    private int dany; // 1-100
    private boolean magica;

    public Arma(String nom, String tipus, int dany, boolean magica) {
        this.nom = nom;
        this.tipus = tipus;
        this.dany = dany;
        this.magica = magica;
    }

    public String getNom() {
        return nom;
    }

    public String getTipus() {
        return tipus;
    }

    public int getDany() {
        return dany;
    }

    public boolean esMagica() {
        return magica;
    }

    public void mostrarArma() {
        System.out.println("Nom: " + nom);
        System.out.println("Tipus: " + tipus);
        System.out.println("Dany: " + dany);
        System.out.println("Magica: " + magica);
    }
}