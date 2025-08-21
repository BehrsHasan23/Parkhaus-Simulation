//Behrs Hasan
public class Schranke {

    private String name;

    public Schranke(String name) {
        this.name = name;
    }

    // Schranke öffnet nur, wenn Ticket bezahlt ist
    public boolean oeffneWennBezahlt(Ticket t) {
        if (t != null && t.istBezahlt()) {
            System.out.println(name + ": Ticket ok -> Schranke öffnet.");
            return true;
        }
        System.out.println(name + ": Ticket NICHT bezahlt -> bleibt zu.");
        return false;
    }
}
