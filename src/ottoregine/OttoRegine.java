package ottoregine;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OttoRegine {


    public static void main(String[] args) {
        try {
            new Scacchiera();
        } catch (InterruptedException ex) {
            Logger.getLogger(OttoRegine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
