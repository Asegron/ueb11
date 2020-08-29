import java.io.*;

/**
 * Die Klasse LOCAuswertung zählt die Lines Of Code fuer ausgewaehlte Dateien
 * und gibt sie aus
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class LOCAuswertung {
    /**
     * Attribute
     */
    private String[] eingabe;
    private int anzahlDateien;
    private int gesamtanzahlZeilen;

    /**
     * Konstruktor
     */
    public LOCAuswertung(String[] eingabe) throws IOException {
        this.eingabe = eingabe;
    }

    /**
     * Prueft, ob eine Zeile ein Kommentar ist
     * 
     * @param zeile Die zu untersuchende Zeile
     * @return true, wenn die Zeile ein Kommentar ist und false, wenn sie kein Kommentar ist
     */
    private boolean istKommentar(String zeile) {
        return (zeile.trim().startsWith("//"));
    }

    /**
     * Lest die Datei(-en), sortiert die Kommentare aus, berechnet die Gesamtanzahl der Zeilen
     * und gibt jene Datei mit der Anzahl an eigenen Zeilen aus
     * 
     * @param dateiName Die zu untersuchende Datei
     * @throws IOException Exception, wenn die Datei nicht existiert oder nicht lesbar ist
     */
    private void einlesenDatei(String dateiName) throws IOException {
        File datei;
        BufferedReader dateiLeser;
        int anzahlZeilen = 0;
        String zeile;

        datei = new File(dateiName);

        ExistiertNichtException.existiert(datei);
        NichtLesbarException.lesbar(datei);

        ++anzahlDateien;

        try {
            dateiLeser = new BufferedReader(new FileReader(datei));

            while((zeile = dateiLeser.readLine()) != null) {
                if(zeile.trim().length() > 0 && !istKommentar(zeile)) {
                    anzahlZeilen++;
                }
            }

            dateiLeser.close();
        } finally {
            System.out.println(dateiName + ":\t" + anzahlZeilen + " LOC");
            gesamtanzahlZeilen += anzahlZeilen;
        }
    }

    /**
     * Start-Methode
     */
    public void start() {
        System.out.println("Auswertung Lines Of Code (LOC)");

        try {
            for(int i = 0; i < eingabe.length; i++) {
                try {
                    einlesenDatei(eingabe[i]);

                } catch(ExistiertNichtException ExistiertNichtEx) {
                    System.out.println(ExistiertNichtEx);

                } catch(NichtLesbarException NichtLesbarEx) {
                    System.out.println(NichtLesbarEx);

                } catch(IOException IOEx) {
                    System.out.println(IOEx);
                }
            }
        } finally {
            System.out.println("\nGesamt:\n" +
                anzahlDateien + " Dateien\t" +
                gesamtanzahlZeilen + " LOC");
        }
    }

    /**
     * Main-Methode
     * 
     * @param args
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            System.exit(1);
        }

        try {
            LOCAuswertung locAuswertung = new LOCAuswertung(args);
            locAuswertung.start();
            System.exit(0);
        } catch(Exception e) {
            System.out.println("Ausnahme: " + e.getClass().getName());
            e.printStackTrace();
            System.exit(2);
        }
    }
}