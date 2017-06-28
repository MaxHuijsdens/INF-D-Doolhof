/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Spel.InterfaceMenuPanel;

/**
 *
 * @author Mathilde
 */
public class MenuPanel extends JPanel implements ActionListener, InterfaceMenuPanel {

    private Panel panel;
    private UitlegPanel upanel;

    private boolean toonUitleg = false;
    private final JButton buttonStart;
    private final JButton buttonPauze;
    private final JButton buttonStop;
    private final JButton buttonHerstart;
    private final JButton buttonUitleg;
    private final JLabel stappenlabel;
    private final JLabel bazookalabel;

    /**
     * Maakt menupanel en alle knoppen en labels aan
     */
    public MenuPanel() {

        buttonStart = new JButton("Start");
        buttonStart.addActionListener(this);
        this.add(buttonStart);

        buttonPauze = new JButton("Pauze");
        buttonStop = new JButton("Stop");
        buttonHerstart = new JButton("Herstart");
        buttonUitleg = new JButton("Uitleg");

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                resetPanel(1);
                panel.pauze();

            }
        });
        buttonUitleg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toonUitleg();

            }
        });

        buttonHerstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resetPanel(panel.getMap().getMoeilijkheid());
                panel.start();
            }
        });

        buttonPauze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.pauze();

            }
        });

        this.add(buttonPauze);
        this.add(buttonStop);
        this.add(buttonHerstart);
        this.add(buttonUitleg);

        stappenlabel = new JLabel("Aantal stappen: 0");
        this.add(stappenlabel);

        bazookalabel = new JLabel("Aantal kogels: 0");
        this.add(bazookalabel);
        createPanel(1);

    }

    /**
     * Maakt panel
     * @param moeilijkheid
     */
    public void createPanel(int moeilijkheid) {
        panel = new Panel(this, moeilijkheid);
        panel.setPreferredSize(new Dimension(640, 672));
        this.add(panel);
    }

    /**
     * Reset panel en aantal stappen en kogels
     * @param moeilijkheid
     */
    @Override
    public void resetPanel(int moeilijkheid) {
        panel.pauze();
        panel.setVisible(false);
        createPanel(moeilijkheid);
        //panel.start();
        updateStappen(0);
        updateKogels(0);
    }

    /**
     * Disabled of enabled alle knoppen
     * @param disable
     */
    public void disableButtons(boolean disable) {
        buttonHerstart.setEnabled(disable);
        buttonPauze.setEnabled(disable);
        buttonStart.setEnabled(disable);
        buttonStop.setEnabled(disable);

    }

    /**
     * Toont of verbergt uitleg scherm
     */
    public void toonUitleg() {
        if (toonUitleg == false) {
            upanel = new UitlegPanel();
            this.add(upanel);
            panel.setVisible(false);
            toonUitleg = true;
            buttonUitleg.setText("Uitleg sluiten");
            disableButtons(false);
        } else {
            upanel.setVisible(false);
            toonUitleg = false;
            panel.setVisible(true);
            buttonUitleg.setText("Uitleg");
            disableButtons(true);
            panel.start();
        }
    }

    /**
     * Verandert aantal stappen
     * @param aantal
     */
    public void updateStappen(int aantal) {
        stappenlabel.setText("Aantal stappen: " + aantal);
    }

    /**
     * Verandert aantal kogels
     * @param aantal
     */
    public void updateKogels(int aantal) {
        bazookalabel.setText("Aantal kogels: " + aantal);
    }

    /**
     * Start level als op startknop wordt gedrukt
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.start();
        //buttonStart.setEnabled(false);

    }
}
