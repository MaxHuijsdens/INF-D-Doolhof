/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpelElementen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Max
 */
public class VijandTimer implements ActionListener {

    private final Timer t;
    private final ArrayList<Vijand> vijanden;

    /**
     * Geeft alle vijanden als arraylist mee
     * @param vijanden
     */
    public VijandTimer(ArrayList<Vijand> vijanden) {
        this.vijanden = vijanden;
        t = new Timer(1250, this);
    }

    /**
     * Actie als timerevent wordt gegenereerd
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        for (Vijand vi : vijanden) {
            vi.loop();
        }
    }

    /**
     * Start timer
     */
    public void startTimer() {
        t.start();
    }

    /**
     * Stopt timer
     */
    public void stopTimer() {
        t.stop();
    }

}
