import java.io.*;

/**
 * Die Klasse ExistiertNichtException ueberprueft, ob eine Datei existiert
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class ExistiertNichtException extends IOException {
    /**
     * Standard-Konstruktor
     */
    public ExistiertNichtException() {
        super();
    }

    /**
     * Konstruktor mit dem String-uebergabeparameter
     * 
     * @param meldung
     */
    public ExistiertNichtException(String meldung) {
        super(meldung);
    }

    /**
     * Diese Methode ueberprueft, ob die Datei existiert
     * 
     * @param datei Die zu untersuchende Datei
     * @throws ExistiertNichtException Exception, wenn die Datei nicht existiert
     */
    public static void existiert(File datei) throws ExistiertNichtException {
        if(!datei.exists()) {
            throw new ExistiertNichtException(
                "Die Datei mit dem Namen " + datei.getName() + " existiert nicht!");
        }
    }
}