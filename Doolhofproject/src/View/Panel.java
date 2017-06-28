/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import SpelElementen.Vijand;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import Spel.InterfacePanel;
import Spel.Map;
import Enums.TypeVakje;

/**
 *
 * @author Mathilde
 */
public class Panel extends JPanel implements InterfacePanel {

    private Map m;
    private final MenuPanel bp;
    private int aantalStappen;
    private int aantalKogels;

    private static final Image GRASS = new ImageIcon("images/groen2.png").getImage();
    private static final Image BLUEGRASS = new ImageIcon("images/blauw.png").getImage();
    private static final Image WALL = new ImageIcon("images/wall.jpg").getImage();
    private static final int MAX_MOEILIJKHEID = 3;

    private static final int HOOGTE = 32;
    private static final int BREEDTE = 32;

    /**
     * Nieuw panel, maak level aan
     *
     * @see Map
     * @param bp
     * @param moeilijkheid
     */
    public Panel(MenuPanel bp, int moeilijkheid) {
        this.aantalStappen = 0;

        this.bp = bp;
        m = new Map(moeilijkheid, this, bp);
        setFocusable(true);

        start();
        this.addKeyListener(m.getPlayer());
    }

    /**
     * Update stappen
     *
     * @param aantal
     */
    @Override
    public void updateStappen(int aantal) {

        aantalStappen = aantalStappen + aantal;

        bp.updateStappen(aantalStappen);

    }

    /**
     * Update kogels
     *
     * @param aantal
     */
    @Override
    public void updateKogels(int aantal) {

        bp.updateKogels(aantal);

    }

    public Map getMap() {
        return this.m;
    }

    /**
     * Start level en vijandtimer
     */
    public void start() {
        this.requestFocusInWindow();
        m.getVijandTimer().startTimer();
    }

    /**
     * Pauzeert spel en vijandtimer
     */
    public void pauze() {
        this.removeFocusListener(null);
        m.getVijandTimer().stopTimer();
    }

    /**
     * Repaint
     */
    @Override
    public void teken() {
        repaint();
    }

    /**
     * Naar volgend level, reset spel als laatste level gehaald is
     */
    @Override
    public void nextLevel() {
        ImageIcon img = new ImageIcon("images/player_small.png");
        if (m.getMoeilijkheid() == MAX_MOEILIJKHEID) {
            JOptionPane.showMessageDialog(this, "Gefeliciteerd, je hebt gewonnen.", "Gewonnen", 0, img);
            bp.resetPanel(1);
        } else {
            Object[] options = {"Doorgaan", "Opnieuw beginnen"};

            int keuze = JOptionPane.showOptionDialog(this, "Gefeliciteerd, je hebt dit level gehaald",
                    "Level gehaald", 0, JOptionPane.PLAIN_MESSAGE, img, options, null);
            if (keuze == 0) {

                this.removeKeyListener(m.getPlayer());
                m.verwijderVijanden();
                m = new Map(m.getMoeilijkheid() + 1, this, bp);
                this.addKeyListener(m.getPlayer());
                aantalStappen = 0;
                bp.updateStappen(aantalStappen);
                aantalKogels = 0;
                bp.updateKogels(aantalKogels);
                this.start();
            }
            if (keuze == 1) {
                bp.resetPanel(m.getMoeilijkheid());
            }

        }
    }

    /**
     * Tekent alle vakjes en spelelementen
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int y = 0; y < Map.getHoogte(); y++) {
            for (int x = 0; x < Map.getBreedte(); x++) {
                if (m.getMap(x, y).equals(TypeVakje.VELD)) {
                    g.drawImage(GRASS, x * BREEDTE, y * HOOGTE, BREEDTE, HOOGTE, null);
                }
                if (m.getMap(x, y).equals(TypeVakje.MUUR)) {
                    g.drawImage(WALL, x * BREEDTE, y * HOOGTE, BREEDTE, HOOGTE, null);
                }
                if (m.getVakje(x, y).isKortstePad() == true) {
                    g.drawImage(BLUEGRASS, x * BREEDTE, y * HOOGTE, BREEDTE, HOOGTE, null);
                }
            }
        }

        m.getPlayer().draw(g);

        if (m.getValsSpeler().getPositie() != null) {
            g.drawImage(m.getValsSpeler().getValsspeler(), m.getValsSpeler().getPositie().getX() * BREEDTE, m.getValsSpeler().getPositie().getY() * HOOGTE, BREEDTE, HOOGTE, null);
        }
        if (m.getVriend().getPositie() != null) {
            g.drawImage(m.getVriend().getVriend(), m.getVriend().getPositie().getX() * BREEDTE, m.getVriend().getPositie().getY() * HOOGTE, BREEDTE, HOOGTE, null);
        }
        if (m.getBazooka().getPositie() != null) {
            g.drawImage(m.getBazooka().getBazooka(), m.getBazooka().getPositie().getX() * BREEDTE, m.getBazooka().getPositie().getY() * HOOGTE, BREEDTE, HOOGTE, null);
        }
        if (m.getHelper().getPositie() != null) {
            g.drawImage(m.getHelper().getHelper(), m.getHelper().getPositie().getX() * BREEDTE, m.getHelper().getPositie().getY() * HOOGTE, BREEDTE, HOOGTE, null);
        }
        for (Vijand vi : m.getVijanden()) {
            g.drawImage(vi.getVijand(), vi.getPositie().getX() * BREEDTE, vi.getPositie().getY() * HOOGTE, BREEDTE, HOOGTE, null);
        }

    }

}
