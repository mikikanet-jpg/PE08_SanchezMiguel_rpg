public class Orc extends Personatge {

    public Orc(String nom, int edat, int f, int d, int c, int i, int s, int ca) {
        super(nom, edat,
              f + 3,
              d,
              c + 1,
              i,
              s,
              ca);
    }

    @Override
    public int atacar() {
        int dany = super.atacar();
        return (int)(dany * 1.10);
    }
}