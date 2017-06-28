/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import Spel.InterfacePanel;
import Enums.Richting;
import Enums.TypeVakje;
import Spel.Vakje;

/**
 *
 * @author Mathilde
 */
public class Player extends SpelElement implements KeyListener {

    private boolean bazooka;
    private InterfacePanel tekenpanel;
    private int aantalKogels;

    private final Image playerLinks;
    private final Image playerRechts;
    private final Image playerUp;
    private final Image playerDown;

    /**
     *
     * @param positie Vakje
     * @param panel InterfacePanel
     */
    public Player(Vakje positie, InterfacePanel panel) {
        this.bazooka = false;

        playerDown = new ImageIcon("images/images2.png").getImage();
        playerLinks = new ImageIcon("images/player_links.png").getImage();
        playerRechts = new ImageIcon("images/player_rechts.png").getImage();
        playerUp = new ImageIcon("images/player_up.png").getImage();

        this.positie = positie;

        this.tekenpanel = panel;
        positie.setElement(this);
        aantalKogels = 0;

    }

    /**
     *
     * @param g Graphics
     */
    public void draw(Graphics g) {

        g.drawImage(getPlayer(), positie.getX() * 32, positie.getY() * 32, 32, 32, null);
    }

    /**
     * 
     * @return true als een speler over een bazooka heen is gelopen en nog kogels over heeft 
     */
    public boolean heeftBazooka() {
        return bazooka;
    }

    /**
     *
     * @param panelInterface InterfacePanel
     */
    public void setInterfacePanel(InterfacePanel panelInterface) {
        tekenpanel = panelInterface;
    }

    /**
     *
     * Deze methode zorgt ervoor dat player de repaint() methode van panel aan kan roepen als hij loopt
     */
    public void herteken() {
        tekenpanel.teken();
    }

    /**
     *
     * Met deze methode loopt de player. Er wordt gekeken of hij een vakje kan verplaatsen in de richting die hij wil. 
     * Zo ja, dan verplaatst hij. Zo nee, dan gebeurt er niets
     * @param direction Richting
     */
    public void loop(Richting direction) {
        if (positie.getBuur(direction) != null) {
            if (positie.getBuur(direction).getType() == TypeVakje.VELD) {
                positie.verwijderElement();
                switch (direction) {
                    case NOORD:
                        positie = positie.getBuur(Richting.NOORD);
                        this.player = playerUp;
                        break;
                    case ZUID:
                        positie = positie.getBuur(Richting.ZUID);
                        this.player = playerDown;
                        break;
                    case WEST:
                        positie = positie.getBuur(Richting.WEST);
                        this.player = playerLinks;
                        break;
                    case OOST:
                        positie = positie.getBuur(Richting.OOST);
                        this.player = playerRechts;
                        break;
                }
                tekenpanel.updateStappen(1);
                checkSpelElementen();
                positie.setElement(this);
            }
        }

    }

    /**
     *
     * Bij deze methode wordt gecontroleerd of de player tegen een spelelement aanloopt en zo ja, welke. 
     * Bij elk spelelement wordt er een andere methode aangeroepen.
     */
    public void checkSpelElementen() {
        SpelElement element = positie.getElement();
        if (element instanceof Bazooka) {
            Bazooka b = (Bazooka) element;
            pakBazooka(b);
        }
        if (element instanceof Helper) {
            Helper h = (Helper) element;
            pakHelper(h);
        }
        if (element instanceof Valsspeler) {
            Valsspeler v = (Valsspeler) element;
            pakValsspeler(v);
        }
        if (element instanceof Vriend) {
            Vriend v = (Vriend) element;
            pakVriend(v);
        }
        if (element instanceof Vijand) {
            Vijand v = (Vijand) element;
            raakVijand(v);
        }
    }

    /**
     *
     * Bij deze methode wordt er gekeken wat de waarde van de valsspeler is. 
     * Het aantal stappen vand de player wordt verlaagd met die waarde. Daarna wordt de valsspeler uit het level verwijderd.
     * @param v Valsspeler
     */
    public void pakValsspeler(Valsspeler v) {

        tekenpanel.updateStappen(-v.getWaarde());
        positie.verwijderElement();
        v.verdwijn();

    }

    /**
     *
     * Bij deze methode wordt de vriend uit het level verwijderd en wordt het volgende level gestart.
     * @param v Vriend
     */
    public void pakVriend(Vriend v) {
        positie.verwijderElement();
        v.volgendLevel();
        v.verdwijn();
    }

    /**
     *
     * Bij deze methode wordt het aantal kogels van Bazooka opgehaald en wordt de boolean van player true.
     * Daarna wordt de bazooka uit het level verwijderd.
     * @param b Bazooka
     */
    public void pakBazooka(Bazooka b) {
        this.aantalKogels = b.getAantalKogels();
        tekenpanel.updateKogels(aantalKogels);
        this.bazooka = true;
        positie.verwijderElement();
        b.verdwijn();
    }

    /**
     *
     * Bij deze methode wordt het kortste pad naar de vriend getoond en wordt de helper uit het level verwijderd.
     * @param h Helper
     */
    public void pakHelper(Helper h) {

        h.ToonKortstePad();
        positie.verwijderElement();
        h.verdwijn();
    }

    /**
     *
     * Bij deze methode wordt de methode eetPlayer() van vijand aangeroepen.
     * @param v Vijand
     */
    public void raakVijand(Vijand v) {

        v.eetPlayer();
    }

    /**
     *
     * Bij deze methode wordt er gekeken of player nog kogels heeft om te schieten. 
     * Zo ja, dan wordt er gecontroleerd of er een muur is in de richting waar de player heenschoot.
     * Als dit het geval is, verdwijnt deze muur. Als er geen muur is, maar de rand van het level, dan gebeurt er niets.
     * @param direction Richting
     */
    public void afschietenBazooka(Richting direction) {

        boolean schietCheck = true;
        
        if (aantalKogels > 0) {
            Vakje doel = positie.getBuur(direction);
            while (schietCheck == true && doel != null) {
                if(doel.type == TypeVakje.MUUR){
                    doel.type = TypeVakje.VELD;
                    tekenpanel.updateKogels(aantalKogels - 1);
                    aantalKogels--;
                    schietCheck = false;
                }else{
                    doel = doel.getBuur(direction);
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_UP) {

            loop(Richting.NOORD);

        }

        if (keycode == KeyEvent.VK_DOWN) {

            loop(Richting.ZUID);

        }
        if (keycode == KeyEvent.VK_LEFT) {

            loop(Richting.WEST);

        }
        if (keycode == KeyEvent.VK_RIGHT) {

            loop(Richting.OOST);

        }
        if (keycode == KeyEvent.VK_SPACE) {

            if (this.heeftBazooka() == true) {
                if (player == playerUp) {
                    afschietenBazooka(Richting.NOORD);
                } else if (player == playerDown) {
                    afschietenBazooka(Richting.ZUID);
                } else if (player == playerLinks) {
                    afschietenBazooka(Richting.WEST);
                } else if (player == playerRechts) {
                    afschietenBazooka(Richting.OOST);
                }

            }

        }
        positie.setElement(this);
        herteken();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //alleen voor testen

    /**
     *Deze methode is alleen voor het testen gebruikt om de positie van player aan te passen om de verschillende tests uit te kunnen voeren.
     * @param positie Vakje
     */
        public void setPositie(Vakje positie) {
        this.positie = positie;
    }
}
