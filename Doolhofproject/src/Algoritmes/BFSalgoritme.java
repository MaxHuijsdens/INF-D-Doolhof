/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import SpelElementen.Vriend;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import Enums.Richting;
import Enums.TypeVakje;
import Spel.Vakje;

/**
 *
 * @author Max
 */
public class BFSalgoritme {

    private final Queue<Vakje> q = new LinkedList<>();
    private final ArrayList visited = new ArrayList();
    private final Vakje startPositie;

    /**
     *
     * @param start
     */
    public BFSalgoritme(Vakje start) {

        startPositie = start;
        q.add(startPositie);
        KortstePad();

    }

    /**
     * Find Path
     */
    public void KortstePad() {
        while (!q.isEmpty()) {
            Vakje v = q.remove();
            visited.add(v);

            //actie als vriend is gevonden
            if (v.getElement() instanceof Vriend) {
                setPad(v);
                return;
            }

            //loop door alle richtingen
            for (Richting r : Richting.values()) {
                if (v.getBuur(r) != null && v.getBuur(r).getType() == TypeVakje.VELD && !visited.contains(v.getBuur(r))) {
                    q.add(v.getBuur(r));
                    v.getBuur(r).setVorigVeld(v);
                }
            }
        }

    }

    /**
     *
     * @param v
     */
    public void setPad(Vakje v) {

        while (v != startPositie) {
            v.setKortstePad(true);
            v = v.getVorigVeld();
        }

    }

}
