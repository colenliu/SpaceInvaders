package ca.ubc.cpsc210.spaceinvaders.test;

import ca.ubc.cpsc210.spaceinvaders.model.Missile;
import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MissileTest {
    private static final int XLOC = SIGame.WIDTH / 2;
    private static final int YLOC = 50;
    private Missile missile;

    @BeforeEach
    public void runBefore() {
        missile = new Missile(XLOC, YLOC);
    }

    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;

        missile.move();
        assertEquals(XLOC, missile.getX());
        assertEquals(YLOC + Missile.DY, missile.getY());

        for (int count = 1; count < NUM_UPDATES; count++) {
            missile.move();
        }

        assertEquals(XLOC, missile.getX());
        assertEquals(YLOC + NUM_UPDATES * Missile.DY, missile.getY());
    }
}
