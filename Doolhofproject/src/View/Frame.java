/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Mathilde
 */
public class Frame extends JFrame {

    /**
     * Maakt frame aan
     */
    public Frame() {
        this.add(new MenuPanel(), BorderLayout.NORTH);
        this.setSize(647, 711);
        this.setResizable(false);
        this.setTitle("Doolhofspel 2014");
        this.setVisible(true);

    }

}
