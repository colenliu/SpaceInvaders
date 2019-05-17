package ca.ubc.cpsc210.spaceinvaders.test;

import ca.ubc.cpsc210.spaceinvaders.model.Invader;
import ca.ubc.cpsc210.spaceinvaders.model.Missile;
import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class InvaderTest {
    private static final int XLOC = SIGame.WIDTH / 2;
    private static final int YLOC = 50;
    private Invader invdr;

    @BeforeEach
    public void runBefore() {
        invdr = new Invader(XLOC, YLOC);
    }


    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;

        invdr.move();
        // can't test XLOC due to random jiggle behaviour
        assertEquals(YLOC + Invader.DY, invdr.getY());

        for (int count = 1; count < NUM_UPDATES; count++) {
            invdr.move();
        }

        assertEquals(YLOC + NUM_UPDATES * Invader.DY, invdr.getY());
    }

    @Test
    public void testCollideWith() {
        Missile m = new Missile(0, 0);
        assertFalse(invdr.collidedWith(m));

        m = new Missile(invdr.getX(), invdr.getY());
        assertTrue(invdr.collidedWith(m));

        m = new Missile(invdr.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2, invdr.getY());
        assertTrue(invdr.collidedWith(m));

        m = new Missile(invdr.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2 + 1, invdr.getY());
        assertFalse(invdr.collidedWith(m));

        m = new Missile(invdr.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2, invdr.getY());
        assertTrue(invdr.collidedWith(m));

        m = new Missile(invdr.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2 - 1, invdr.getY());
        assertFalse(invdr.collidedWith(m));

        m = new Missile(invdr.getX(), invdr.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2);
        assertTrue(invdr.collidedWith(m));

        m = new Missile(invdr.getX(), invdr.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2 + 1);
        assertFalse(invdr.collidedWith(m));
    }
}
