package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        if (e < 0) {
            energy = 0;
        } else if (energy < 2) {
            energy = e;
        } else {
            energy = 2;
        }
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 0;
        r = 34;
        b = 231;
        return color(r, g, b);
    }

    /**
     * Clorus gains the energy of the creature being attacked.
     */
    public void attack(Creature c) {
        // gain that creature’s energy
        energy += c.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * Clorus loses 0.01 energy when staying.
     */
    public void stay() {
        // TODO
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        Clorus replica = new Clorus(0.5 * this.energy());
        this.energy = 0.5 * this.energy();
        return replica;
    }

    /**
     * Clorus take exactly the following actions based on NEIGHBORS:
     * 1. If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares
     * do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> surrPlips = new ArrayDeque<>();
        boolean anyPlips = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            }
            if (entry.getValue().name().equals("plip")) {
                anyPlips = true;
                surrPlips.add(entry.getKey());
            }
        }

        if (emptyNeighbors.isEmpty()) { // FIXME
            // TODO
            stay();
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (anyPlips) {
            return new Action(Action.ActionType.ATTACK,randomEntry(surrPlips));
        }

        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
    }
}
