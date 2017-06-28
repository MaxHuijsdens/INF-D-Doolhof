/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import Spel.InterfacePanel;
import Spel.Vakje;

/**
 *
 * @author Mathilde
 */
public class Vriend extends SpelElement {

    private final InterfacePanel tekenpanel;

    /**
     * Set positie voor vriend
     * @param positie Vakje
     * @param panel InterfacePanel
     */
    public Vriend(Vakje positie, InterfacePanel panel) {

        this.tekenpanel = panel;
        this.positie = positie;
        positie.setElement(this);
    }

    /**
     * Roept volgend level aan als vriend wordt bereikt
     * @see Panel.nextLevel()
     */
    public void volgendLevel() {

        tekenpanel.nextLevel();
        tekenpanel.teken();
    }

}
