/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spel;

import Enums.TypeVakje;
import Enums.Richting;
import SpelElementen.SpelElement;
import java.util.HashMap;

/**
 *
 * @author Max
 */
public class Vakje {

    public TypeVakje type;
    private SpelElement element;

    //variabelen voor het vinden van het kortste pad.
    private Vakje vorigVeld;
    private boolean isKortstePad;

    private final int Xpositie;
    private final int Ypositie;

    private final HashMap<Richting, Vakje> buren;

    /**
     *
     * @param type char
     * @param xpos int
     * @param ypos int
     */
    public Vakje(char type, int xpos, int ypos) {
        this.buren = new HashMap<>();
        this.isKortstePad = false;
        if (type == 'w') {
            this.type = TypeVakje.MUUR;
        }
        if (type == 'g') {
            this.type = TypeVakje.VELD;
        }
        Xpositie = xpos;
        Ypositie = ypos;
    }

    /**
     *
     * @return TypeVakje
     */
    public TypeVakje getType() {
        return this.type;
    }

    /**
     *
     * @return int Xpositie
     */
    public int getX() {
        return Xpositie;
    }

    /**
     *
     * @return int Ypositie
     */
    public int getY() {
        return Ypositie;
    }

    /**
     *
     * @return boolean isKortstePad
     */
    public boolean isKortstePad() {
        return this.isKortstePad;
    }

    /**
     * Zet kortste pad
     * @param bool boolean
     * @see BFSalgoritme
     */
    public void setKortstePad(boolean bool) {
        this.isKortstePad = bool;
    }

    /**
     * Zet vorig veld voor kortste pad algoritme
     * @param vv Vakje
     * @see BFSalgoritme
     */
    public void setVorigVeld(Vakje vv) {
        this.vorigVeld = vv;
    }

    /**
     *
     * @return Vakje vorigVeld
     */
    public Vakje getVorigVeld() {
        return vorigVeld;
    }

    /**
     *
     * @param element SpelElement
     */
    public void setElement(SpelElement element) {
        this.element = element;
    }

    /**
     *
     * @return SpelElement
     */
    public SpelElement getElement() {
        return this.element;
    }

    /**
     * Voegt buurvakje toe aan hashmap
     * @param richting Richting
     * @param vakje Vakje
     */
    public void setBuur(Richting richting, Vakje vakje) {
        buren.put(richting, vakje);
    }

    /**
     *
     * @param richting Richting
     * @return Vakje
     */
    public Vakje getBuur(Richting richting) {
        return buren.get(richting);
    }

    /**
     * Verwijdert element
     */
    public void verwijderElement() {
        this.element = null;
    }

}
