/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Enums.Richting;
import Spel.Vakje;

/**
 *
 * @author Max
 */
public class VakjeRichting {

    private final Vakje vakje;
    private final Richting richting;
    private final VakjeRichting vorig;
    private final int stap;

    /**
     *
     * @param vakje
     * @param richting
     * @param vorige
     * @param stap
     */
    public VakjeRichting(Vakje vakje, Richting richting, VakjeRichting vorige, int stap) {
        this.vakje = vakje;
        this.richting = richting;
        this.vorig = vorige;
        this.stap = stap;
    }

    /**
     *
     * @return Vakje
     */
    public Vakje getVakje() {
        return vakje;
    }

    /**
     *
     * @return Richting
     */
    public Richting getRichting() {
        return richting;
    }

    /**
     *
     * @return VakjeRichting vorig
     */
    public VakjeRichting getVorige() {
        return vorig;
    }

    /**
     *
     * @return int stap
     */
    public int getStap() {
        return stap;
    }

}
