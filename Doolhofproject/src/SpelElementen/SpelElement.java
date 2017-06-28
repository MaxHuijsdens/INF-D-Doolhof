/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import java.awt.Image;
import javax.swing.ImageIcon;
import Spel.Vakje;

/**
 *
 * @author Mathilde
 */
public abstract class SpelElement {

    protected Vakje positie;

    protected Image player;
    private final Image bazooka;
    private final Image vriend;
    private final Image valsspeler;
    private final Image helper;
    private final Image vijand;

    /**
     * Constructor creates Images
     */
    public SpelElement() {
        player = new ImageIcon("images/images2.png").getImage();
        bazooka = new ImageIcon("images/bazooka.png").getImage();
        vriend = new ImageIcon("images/vriend.png").getImage();
        valsspeler = new ImageIcon("images/valsspeler2.png").getImage();
        helper = new ImageIcon("images/helper.jpg").getImage();
        vijand = new ImageIcon("images/vijand.png").getImage();
    }

    /**
     *  
     * @return Image player
     */
    public Image getPlayer() {
        return player;
    }

    /**
     *
     * @return Image bazooka
     */
    public Image getBazooka() {
        return bazooka;
    }

    /**
     *
     * @return Image vriend
     */
    public Image getVriend() {
        return vriend;
    }

    /**
     *
     * @return Image valsspeler
     */
    public Image getValsspeler() {
        return valsspeler;
    }

    /**
     *
     * @return Image helper
     */
    public Image getHelper() {
        return helper;
    }

    /**
     *
     * @return Image vijand
     */
    public Image getVijand() {
        return vijand;
    }

    /**
     *
     * @return Vakje positie
     */
    public Vakje getPositie() {
        return this.positie;
    }

    /**
     * Verwijdert element
     */
    public void verdwijn() {
        this.positie = null;
    }

}
