/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import SpelElementen.Vriend;
import java.util.Stack;
import Enums.Richting;
import Enums.TypeVakje;
import Spel.Vakje;

/**
 *
 * @author Max
 */
public class BTalgoritme {

    private final VakjeRichting startPositie;
    private final Stack<Vakje> splitsingen = new Stack();
    private final Stack<VakjeRichting> st = new Stack();

    /**
     *
     * @param start
     */
        public BTalgoritme(Vakje start) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        this.startPositie = new VakjeRichting(start, null, null, 0);

        KortstePad();
        stopwatch.stop();
        System.out.println(stopwatch.getElapsedTime());
    }

    /**
     * Find Path
     */
    public void KortstePad() {

        for (Richting r : Richting.values()) {
            if (startPositie.getVakje().getBuur(r).getType() == TypeVakje.VELD) {
                st.push(new VakjeRichting(startPositie.getVakje(), r, startPositie, 1));
            }
        }

        while (!st.isEmpty()) {

            VakjeRichting huidig = st.pop();

            if (huidig.getVakje().getElement() instanceof Vriend) {

                TekenPad(huidig);
            }

            for (Richting r : Richting.values()) {
                if (huidig.getVakje().getBuur(r).getType() == TypeVakje.VELD && huidig.getVakje().getBuur(r) != huidig.getVorige().getVakje()) {

                    if (checkSplitsing(huidig.getVakje().getBuur(r))) {
                        if (!splitsingen.contains(huidig.getVakje().getBuur(r))) {
                            st.push(new VakjeRichting(huidig.getVakje().getBuur(r), r, huidig, huidig.getStap() + 1));
                            splitsingen.push(huidig.getVakje().getBuur(r));
                        }
                    } else {

                        st.push(new VakjeRichting(huidig.getVakje().getBuur(r), r, huidig, huidig.getStap() + 1));
                    }
                }
            }

        }

    }

    /**
     *
     * @param v
     * @return boolean
     */
    public boolean checkSplitsing(Vakje v) {
        int paden = 0;
        for (Richting r : Richting.values()) {
            if (v.getBuur(r).type == TypeVakje.VELD) {
                paden++;
            }
        }

        if (paden > 2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @param vr
     */
    public void TekenPad(VakjeRichting vr) {
        Stack<VakjeRichting> vakjes = new Stack<>();
        vakjes.push(vr);
        while (!vakjes.isEmpty()) {
            try {
                VakjeRichting huidig = vakjes.pop();
                vakjes.push(huidig.getVorige());
                huidig.getVakje().setKortstePad(true);
            } catch (NullPointerException ex) {

            }
        }

    }

}
