package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(1);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.001);
        c.move();
        assertEquals(1.94, c.energy(), 0.001);
        c.stay();
        assertEquals(1.93, c.energy(), 0.001);
        c.stay();
        assertEquals(1.92, c.energy(), 0.001);
        c.attack(p);
        assertEquals(2.92, c.energy(), 0.001);
    }

    @Test
    public void testReplicate() {
        // TODO
        Clorus p = new Clorus(2);
        Clorus r = p.replicate();
        Clorus t = new Clorus(1);
        assertEquals(t.energy(), r.energy(), 0.01);
        assertEquals(p.energy(), 1.0, 0.01);
        assertNotSame(r,t);
        assertNotSame(r,p);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // if any Plips are seen, the Clorus will ATTACK one of them randomly
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Empty());
        topPlip.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK,Direction.TOP);

        assertEquals(expected, actual);

        // if any Plips are seen, the Clorus will ATTACK one of them randomly
        p = new Clorus(2.2);
        HashMap<Direction, Occupant> botPlip = new HashMap<Direction, Occupant>();
        botPlip.put(Direction.TOP, new Empty());
        botPlip.put(Direction.BOTTOM, new Plip());
        botPlip.put(Direction.LEFT, new Empty());
        botPlip.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(botPlip);
        expected = new Action(Action.ActionType.ATTACK,Direction.BOTTOM);

        assertEquals(expected, actual);

        // Move around if energy < 1 and there is empty space.
        p = new Clorus(0.2);
        HashMap<Direction, Occupant> leftEmpty = new HashMap<Direction, Occupant>();
        leftEmpty.put(Direction.TOP, new Impassible());
        leftEmpty.put(Direction.BOTTOM, new Impassible());
        leftEmpty.put(Direction.LEFT, new Empty());
        leftEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(leftEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.LEFT);

        assertEquals(expected, actual);
    }
}
