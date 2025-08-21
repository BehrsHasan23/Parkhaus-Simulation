//Behrs Hasan
import java.util.*;

public class ParkhausApp {

    public static void main(String[] args) {
        // Parkhaus mit 3 Stockwerken: je 5 Plätze
        int[] kapazitaetProStockwerk = {5, 5, 5};
        Parkhaus parkhaus = new Parkhaus(kapazitaetProStockwerk, 0.05); // 0.05 CHF pro Minute

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Parkhaus Menü ---");
            System.out.println("1) Einfahren (Ticket ziehen)");
            System.out.println("2) Bezahlen");
            System.out.println("3) Ausfahren (Schranke 1 oder 2)");
            System.out.println("4) Anzeige zeigen (freie Plätze)");
            System.out.println("0) Ende");
            System.out.print("Wahl: ");

            String wahl = sc.nextLine().trim();

            switch (wahl) {
                case "1": {
                    Integer ticketNr = parkhaus.einfahren();
                    if (ticketNr == null) {
                        System.out.println("Parkhaus voll. Einfahrt nicht möglich.");
                    } else {
                        Ticket t = parkhaus.holeTicket(ticketNr);
                        System.out.println("Ticket-Nr: " + ticketNr + " | Stockwerk: " + t.getStockwerkNr());
                    }
                    break;
                }
                case "2": {
                    System.out.print("Ticket-Nr: ");
                    int nr = Integer.parseInt(sc.nextLine());
                    System.out.print("Parkdauer in Minuten (einfach eingeben): ");
                    int minuten = Integer.parseInt(sc.nextLine());
                    double betrag = parkhaus.bezahlen(nr, minuten);
                    if (betrag < 0) {
                        System.out.println("Ticket nicht gefunden oder schon bezahlt.");
                    } else {
                        System.out.printf("Zu zahlen: %.2f CHF. Ticket ist jetzt bezahlt.%n", betrag);
                    }
                    break;
                }
                case "3": {
                    System.out.print("Ticket-Nr: ");
                    int nr = Integer.parseInt(sc.nextLine());
                    System.out.print("Welche Schranke (1 oder 2)? ");
                    int schranke = Integer.parseInt(sc.nextLine());
                    boolean raus = parkhaus.ausfahren(nr, schranke);
                    if (raus) {
                        System.out.println("Schranke öffnet. Gute Fahrt!");
                    } else {
                        System.out.println("Ausfahrt nicht möglich. Wurde bezahlt? Stimmt die Ticket-Nr.?");
                    }
                    break;
                }
                case "4": {
                    parkhaus.zeigeAnzeige();
                    break;
                }
                case "0":
                    System.out.println("Programm beendet.");
                    sc.close();
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
