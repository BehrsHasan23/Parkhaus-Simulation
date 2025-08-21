//Behrs Hasan
import java.util.*;

public class Parkhaus {

    private List<Stockwerk> stockwerke = new ArrayList<>();
    private Map<Integer, Ticket> aktiveTickets = new HashMap<>();
    private Schranke ausfahrt1 = new Schranke("Ausfahrt 1");
    private Schranke ausfahrt2 = new Schranke("Ausfahrt 2");
    private double preisProMinute;
    private int naechsteTicketNummer = 1;
    
    //Hilfe von Youtube
    public Parkhaus(int[] plaetzeProStockwerk, double preisProMinute) {
        for (int i = 0; i < plaetzeProStockwerk.length; i++) {
            stockwerke.add(new Stockwerk(i + 1, plaetzeProStockwerk[i]));
        }
        this.preisProMinute = preisProMinute;
    }

    // Einfahren: nimmt das erste Stockwerk mit freiem Platz
    public Integer einfahren() {
        Stockwerk ziel = findeErstesFreiesStockwerk();
        if (ziel == null) return null; // voll
        ziel.belegePlatz();

        int nr = naechsteTicketNummer++;
        Ticket t = new Ticket(nr, ziel.getNummer());
        aktiveTickets.put(nr, t);
        return nr;
    }

    // Bezahlen: Nutzer gibt Minuten an
    public double bezahlen(int ticketNr, int minuten) {
        Ticket t = aktiveTickets.get(ticketNr);
        if (t == null || t.istBezahlt()) return -1;
        t.setMinuten(minuten);
        double betrag = t.berechnePreis(preisProMinute);
        t.setBezahlt(true);
        return betrag;
    }

    // Ausfahren: nur wenn bezahlt => Schranke öffnet => Platz wird frei
    public boolean ausfahren(int ticketNr, int schrankenNummer) {
        Ticket t = aktiveTickets.get(ticketNr);
        if (t == null || !t.istBezahlt()) return false;

        Schranke s = (schrankenNummer == 2) ? ausfahrt2 : ausfahrt1;
        if (!s.oeffneWennBezahlt(t)) return false;

        // Ticket löschen und Platz auf dem Stockwerk freigeben
        Stockwerk sw = stockwerke.get(t.getStockwerkNr() - 1);
        sw.gibPlatzFrei();
        aktiveTickets.remove(ticketNr);
        return true;
    }

    public void zeigeAnzeige() {
        System.out.println("\n=== Anzeige vor dem Parkhaus ===");
        System.out.println("Gesamt frei: " + freiePlaetzeGesamt());
        for (Stockwerk s : stockwerke) {
            System.out.println("Stockwerk " + s.getNummer() + ": frei " + s.freiePlaetze() + " von " + s.getKapazitaet());
        }
    }

    public int freiePlaetzeGesamt() {
        int sum = 0;
        for (Stockwerk s : stockwerke) sum += s.freiePlaetze();
        return sum;
    }

    private Stockwerk findeErstesFreiesStockwerk() {
        for (Stockwerk s : stockwerke) {
            if (s.hatFrei()) return s;
        }
        return null;
    }

    // Hilfsmethode für die App-Ansage
    public Ticket holeTicket(int nr) { return aktiveTickets.get(nr); }
}
