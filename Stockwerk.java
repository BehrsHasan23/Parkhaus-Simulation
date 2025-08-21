//Behrs Hasan
public class Stockwerk {

    private int nummer;
    private int kapazitaet;
    private int belegt = 0;

    public Stockwerk(int nummer, int kapazitaet) {
        this.nummer = nummer;
        this.kapazitaet = kapazitaet;
    }

    public boolean hatFrei() {
        return belegt < kapazitaet;
    }

    public void belegePlatz() {
        if (hatFrei()) belegt++;
    }

    public void gibPlatzFrei() {
        if (belegt > 0) belegt--;
    }

    public int freiePlaetze() {
        return kapazitaet - belegt;
    }

    public int getNummer() { return nummer; }
    public int getKapazitaet() { return kapazitaet; }
}
