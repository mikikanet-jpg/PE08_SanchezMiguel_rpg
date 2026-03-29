public class Elf extends Personatge {

    public Elf(String nom, int edat, int f, int d, int c, int i, int s, int ca) {
        super(nom, edat,
              f,
              d + 2,
              c,
              i + 2,
              s,
              ca);
    }

    @Override
    public void regenerarMana() {
        mana += inteligencia * 3;

        if (mana > calcularManaMax()) {
            mana = calcularManaMax();
        }
    }
}