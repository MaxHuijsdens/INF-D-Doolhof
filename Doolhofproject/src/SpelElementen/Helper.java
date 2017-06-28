/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import Algoritmes.BFSalgoritme;
import Spel.Vakje;

/**
 *
 * @author Max
 */
public class Helper extends SpelElement {

    /**
     * Zet positie voor helper
     * @param positie Vakje
     */
    public Helper(Vakje positie) {

        this.positie = positie;
        positie.setElement(this);
    }

    /**
     * Roept algoritme aan
     */
    public void ToonKortstePad() {
        new BFSalgoritme(positie);
    }

}
