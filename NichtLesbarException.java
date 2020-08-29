import java.io.*;

/**
 * Die Klasse NichtLesbarException ueberprueft, ob eine Datei lesbar ist
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class NichtLesbarException extends IOException {
    /**
     * Standard-Konstruktor
     */
    public NichtLesbarException() {
        super();
    }

    /**
     * Konstruktor mit dem String-Uebergabeparameter
     * 
     * @param meldung
     */
    public NichtLesbarException(String meldung) {
        super(meldung);
    }

    /**
     * Diese Methode ueberprueft, ob die Datei lesbar ist
     * 
     * @param datei Die zu untersuchende Datei
     * @throws NichtLesbarException Exception, wenn die Datei nicht lesbar ist
     */
    public static void lesbar(File datei) throws NichtLesbarException {
        if(!datei.canRead()) {
            throw new NichtLesbarException(
                "Die Datei mit dem Namen " + datei.getName() + " ist nicht lesbar!");
        }
    }
}