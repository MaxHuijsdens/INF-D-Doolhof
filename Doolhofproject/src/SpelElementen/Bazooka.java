/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import Spel.Vakje;

/**
 *
 * @author Mathilde
 */
public class Bazooka extends SpelElement {

    private final int aantalKogels;

    /**
     * Stelt aantal kogels en positie van bazooka in.
     * @param positie Vakje
     * @param kogels int
     */
    public Bazooka(Vakje positie, int kogels) {

        this.positie = positie;
        aantalKogels = kogels;
        positie.setElement(this);

    }

    /**
     *
     * @return int aantalKogels
     */
    public int getAantalKogels() {
        return this.aantalKogels;
    }

}
