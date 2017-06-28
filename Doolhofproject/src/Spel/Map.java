    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spel;

import SpelElementen.Player;
import SpelElementen.VijandTimer;
import Enums.TypeVakje;
import Enums.Richting;
import SpelElementen.Bazooka;
import SpelElementen.Helper;
import SpelElementen.Valsspeler;
import SpelElementen.Vijand;
import SpelElementen.Vriend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mathilde
 */
public class Map {

    private Scanner scan;
    private final Vakje[][] Maze;
    private final int moeilijkheid;

    private Player p;
    private Vriend v;
    private Bazooka b;
    private Valsspeler vals;
    private Helper h;
    private final ArrayList vi;
    private final VijandTimer Vtimer;

    private static final int BREEDTE = 20;
    private static final int HOOGTE = 20;

    /**
     * Maakt nieuw doolhof en bijbehorende spelelementen.
     * @param moeilijkheid
     * @param panel
     * @param mpanel
     */
    public Map(int moeilijkheid, InterfacePanel panel, InterfaceMenuPanel mpanel) {
        vi = new ArrayList<>();
        this.Maze = new Vakje[100][100];
        this.moeilijkheid = moeilijkheid;

        openFile();
        readFile();
        closeFile();
        setBuren();

        laadLevel(panel, mpanel);
        Vtimer = new VijandTimer(vi);
    }
    
    /**
     *
     * @return Player
     */
    public Player getPlayer() {
        return p;
    }

    /**
     *
     * @return Bazooka
     */
    public Bazooka getBazooka() {
        return b;
    }

    /**
     *
     * @return Valsspeler
     */
    public Valsspeler getValsSpeler() {
        return vals;
    }

    /**
     *
     * @return Vriend
     */
    public Vriend getVriend() {
        return v;
    }

    /**
     *
     * @return Helper
     */
    public Helper getHelper() {
        return h;
    }

    /**
     * Returnt arraylist van vijanden.
     * @return Vijand
     */
    public ArrayList<Vijand> getVijanden() {
        return vi;
    }

    /**
     * 
     * @return VijandTimer
     */
    public VijandTimer getVijandTimer() {
        return Vtimer;
    }

    /**
     *
     * @return moeilijkheid
     */
    public int getMoeilijkheid() {
        return this.moeilijkheid;
    }

    /**
     *
     * @param x
     * @param y
     * @return TypeVakje
     */
    public TypeVakje getMap(int x, int y) {
        return Maze[x][y].getType();
    }

    /**
     *
     * @param x
     * @param y
     * @return Vakje
     */
    public Vakje getVakje(int x, int y) {
        return Maze[x][y];
    }

    /**
     * Opent tekstbestand met doolhof
     */
    public void openFile() {
        try {
            if (moeilijkheid == 1) {
                scan = new Scanner(new File("images/Map.txt"));
            }
            if (moeilijkheid == 2) {
                scan = new Scanner(new File("images/Doolhof2.txt"));
            }
            if (moeilijkheid == 3) {
                scan = new Scanner(new File("images/Doolhof3.txt"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("error loading map");
        }
    }

    /**
     * Leest tekstbestand met doolhof en creëert vakjes
     */
    public void readFile() {
        String Map[] = new String[BREEDTE];

        while (scan.hasNext()) {
            for (int i = 0; i < HOOGTE; i++) {
                Map[i] = scan.next();
                char[] chars = Map[i].toCharArray();
                for (int j = 0; j < BREEDTE; j++) {
                    Maze[j][i] = new Vakje(chars[j], j, i);
                }

            }
        }

    }

    /**
     * Sluit tekstbestand met doolhof
     */
    public void closeFile() {
        scan.close();
    }

    /**
     * Berekent buren voor elk vakje
     */
    public void setBuren() {
        for (int i = 0; i < HOOGTE; i++) {
            for (int j = 0; j < BREEDTE; j++) {
                if (i > 0) {
                    Maze[j][i].setBuur(Richting.NOORD, Maze[j][i - 1]);

                }
                if (i < HOOGTE - 1) {
                    Maze[j][i].setBuur(Richting.ZUID, Maze[j][i + 1]);

                }
                if (j < BREEDTE) {
                    Maze[j][i].setBuur(Richting.OOST, Maze[j + 1][i]);
                }
                if (j > 0) {
                    Maze[j][i].setBuur(Richting.WEST, Maze[j - 1][i]);
                }
            }
        }

    }

    /**
     * Laad juiste level afhankelijk van moeilijkheid
     * @param panel
     * @param mpanel
     */
    public void laadLevel(InterfacePanel panel, InterfaceMenuPanel mpanel) {
        if (this.moeilijkheid == 1) {
            p = new Player(getVakje(1, 1), panel);
            v = new Vriend(getVakje(18, 16), panel);
            b = new Bazooka(getVakje(6, 6), 4);
            vals = new Valsspeler(getVakje(15, 9), 10);
            h = new Helper(getVakje(7, 2));
            vi.add(new Vijand(getVakje(4, 12), mpanel, panel));
            vi.add(new Vijand(getVakje(17, 13), mpanel, panel));
        }
        if (this.moeilijkheid == 2) {
            p = new Player(getVakje(1, 1), panel);
            b = new Bazooka(getVakje(11, 8), 3);
            vals = new Valsspeler(getVakje(16, 8), 20);
            v = new Vriend(getVakje(18, 18), panel);
            h = new Helper(getVakje(3, 16));
            vi.add(new Vijand(getVakje(7, 10), mpanel, panel));
            vi.add(new Vijand(getVakje(16, 4), mpanel, panel));
        }
        if (this.moeilijkheid == 3) {
            p = new Player(getVakje(2, 1), panel);
            b = new Bazooka(getVakje(3, 12), 1);
            vals = new Valsspeler(getVakje(16, 8), 10);
            v = new Vriend(getVakje(18, 18), panel);
            h = new Helper(getVakje(7, 2));
            vi.add(new Vijand(getVakje(6, 14), mpanel, panel));
            vi.add(new Vijand(getVakje(18, 10), mpanel, panel));
            vi.add(new Vijand(getVakje(10, 10), mpanel, panel));

        }
    }

    /**
     * Maak vijanden arraylist leeg
     */
    public void verwijderVijanden() {
        vi.clear();
    }

    /**
     *
     * @return HOOGTE
     */
    public static int getHoogte() {
        return HOOGTE;
    }

    /**
     *
     * @return BREEDTE
     */
    public static int getBreedte() {
        return BREEDTE;
    }

}
