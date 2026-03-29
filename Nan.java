public class Nan extends Personatge {

    public Nan(String nom, int edat, int f, int d, int c, int i, int s, int ca) {
        super(nom, edat,
              f,
              d - 1,
              c + 4,
              i,
              s,
              ca);
    }

    @Override
    public void rebreDany(int dany) {

        dany = (int)(dany * 0.75);

        super.rebreDany(dany);
    }

    @Override
    public void regenerarVida() {
        salut += constitucio * 4;

        if (salut > calcularSalutMax()) {
            salut = calcularSalutMax();
        }
    }
}