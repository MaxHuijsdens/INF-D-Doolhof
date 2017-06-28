/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

import java.util.Random;

/**
 *
 * @author Max
 */
public enum Richting {

    NOORD, OOST, WEST, ZUID;

    /**
     * Returnt random richting
     * @return 
     */
    public static Richting getRandomRichting() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
