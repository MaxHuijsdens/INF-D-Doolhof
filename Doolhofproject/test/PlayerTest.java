
import SpelElementen.Player;
import SpelElementen.SpelElement;
import Spel.Map;
import View.MenuPanel;
import View.Panel;
import Enums.Richting;
import Spel.Vakje;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mathilde
 */
public class PlayerTest {
    
    MenuPanel mp;
    Panel pan;
    Map m;
    Player p;

    public PlayerTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        mp = new MenuPanel();
        pan = new Panel(mp, 1);
        m = new Map(1, pan, mp);
        p = m.getPlayer();
    }
    
    @Test
    public void testMoveZuidLeegVeld() {
        p.loop(Richting.ZUID);
        Vakje expectedResult = m.getVakje(1, 2);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveNoordLeegVeld() {
        p.setPositie(m.getVakje(1, 2));
        p.loop(Richting.NOORD);
        Vakje expectedResult = m.getVakje(1, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveWestLeegVeld() {
        p.setPositie(m.getVakje(2, 1));
        p.loop(Richting.WEST);
        Vakje expectedResult = m.getVakje(1, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveOostLeegVeld() {
        p.setPositie(m.getVakje(0, 1));
        p.loop(Richting.OOST);
        Vakje expectedResult = m.getVakje(1, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    
    @Test
    public void testMoveNoordMuur(){
        p.loop(Richting.NOORD);
        Vakje expectedResult = m.getVakje(1, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveZuidMuur(){
        p.setPositie(m.getVakje(1, 4));
        p.loop(Richting.ZUID);
        Vakje expectedResult = m.getVakje(1, 4);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveWestMuur(){
        p.setPositie(m.getVakje(2, 5));
        p.loop(Richting.WEST);
        Vakje expectedResult = m.getVakje(2, 5);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveOostMuur(){
        p.setPositie(m.getVakje(0, 5));
        p.loop(Richting.OOST);
        Vakje expectedResult = m.getVakje(0, 5);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveWestRand(){
        p.setPositie(m.getVakje(0, 1));
        p.loop(Richting.WEST);
        Vakje expectedResult = m.getVakje(0, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveNoordRand(){
        p.setPositie(m.getVakje(1, 0));
        p.loop(Richting.NOORD);
        Vakje expectedResult = m.getVakje(1, 0);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveZuidRand(){
        p.setPositie(m.getVakje(1, 19));
        p.loop(Richting.ZUID);
        Vakje expectedResult = m.getVakje(1, 19);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveOostRand(){
        p.setPositie(m.getVakje(19, 1));
        p.loop(Richting.OOST);
        Vakje expectedResult = m.getVakje(19, 1);
        Vakje Result = p.getPositie();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveOostElement(){
        p.setPositie(m.getVakje(5, 6));
        p.loop(Richting.OOST);
        SpelElement expectedResult = p;
        SpelElement Result = m.getVakje(6, 6).getElement();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveWestElement(){
        p.setPositie(m.getVakje(7, 6));
        p.loop(Richting.WEST);
        SpelElement expectedResult = p;
        SpelElement Result = m.getVakje(6, 6).getElement();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveZuidElement(){
        p.setPositie(m.getVakje(6, 5));
        p.loop(Richting.ZUID);
        SpelElement expectedResult = p;
        SpelElement Result = m.getVakje(6, 6).getElement();
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void testMoveNoordElement(){
        p.setPositie(m.getVakje(6, 7));
        p.loop(Richting.NOORD);
        SpelElement expectedResult = p;
        SpelElement Result = m.getVakje(6, 6).getElement();
        assertEquals(expectedResult, Result);
    }
    
}
