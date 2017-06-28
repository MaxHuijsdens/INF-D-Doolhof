/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import Spel.Vakje;

/**
 *
 * @author Max
 */
public class Valsspeler extends SpelElement {

    private int waarde;

    /**
     * Zet positie voor valsspeler
     * @param positie Vakje
     * @param waarde int
     */
    public Valsspeler(Vakje positie, int waarde) {

        this.waarde = waarde;
        this.positie = positie;
        positie.setElement(this);
    }

    /**
     *
     * @return int waarde
     */
    public int getWaarde() {
        return waarde;
    }

}
