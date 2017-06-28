/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Spel.InterfaceMenuPanel;
import Spel.InterfacePanel;
import Enums.Richting;
import Enums.TypeVakje;
import Spel.Vakje;

/**
 *
 * @author Max
 */
public class Vijand extends SpelElement {

    private final InterfaceMenuPanel menutekenpanel;
    private final InterfacePanel tekenpanel;

    /**
     * Zet positie voor vijand
     * @param positie Vakje
     * @param mpanel InterfaceMenuPanel
     * @param panel InterfacePanel
     */
    public Vijand(Vakje positie, InterfaceMenuPanel mpanel, InterfacePanel panel) {
        this.positie = positie;
        this.tekenpanel = panel;
        this.menutekenpanel = mpanel;
        positie.setElement(this);
    }

    /**
     * Spel begint opnieuw als de vijand de speler raakt
     */
    public void eetPlayer() {
        ImageIcon img = new ImageIcon("images/vijand_small.png");
        JOptionPane.showMessageDialog(null, "Je bent opgegeten.", "Game over", 0, img);
        menutekenpanel.resetPanel(1);
    }

    /**
     * Functie om vijand in een willekeurige richting te verplaatsen
     * @see VijandTimer
     */
    public void loop() {
        boolean verplaatst = false;
        while (verplaatst == false) {
            Richting r = Richting.getRandomRichting();
            if (positie.getBuur(r) != null) {
                if (positie.getBuur(r).getType() == TypeVakje.VELD
                        && (positie.getBuur(r).getElement() == null || positie.getBuur(r).getElement() instanceof Player)) {
                    positie.verwijderElement();
                    this.positie = positie.getBuur(r);
                    if (positie.getElement() instanceof Player) {
                        positie.verwijderElement();
                        eetPlayer();
                        return;

                    }
                    positie.setElement(this);
                    tekenpanel.teken();
                    verplaatst = true;
                }
            }
        }
    }

}
