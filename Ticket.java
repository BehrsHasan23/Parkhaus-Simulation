//Behrs Hasan
public class Ticket {

    private int nummer;
    private boolean bezahlt = false;
    private int minuten = 0;      // einfache Simulation
    private int stockwerkNr;

    public Ticket(int nummer, int stockwerkNr) {
        this.nummer = nummer;
        this.stockwerkNr = stockwerkNr;
    }

    public double berechnePreis(double preisProMinute) {
        return minuten * preisProMinute;
    }

    // Getter/Setter
    public int getNummer() { return nummer; }
    public boolean istBezahlt() { return bezahlt; }
    public void setBezahlt(boolean bezahlt) { this.bezahlt = bezahlt; }
    public void setMinuten(int minuten) { this.minuten = minuten; }
    public int getStockwerkNr() { return stockwerkNr; }
}
