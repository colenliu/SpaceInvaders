package ca.ubc.cpsc210.spaceinvaders.test;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Tank class.
 */
public class TankTest {
    private static final int XLOC = SIGame.WIDTH / 2;
    private Tank tank;

    @BeforeEach
    public void runBefore() {
        tank = new Tank(XLOC);
    }

    @Test
    public void testConstructor() {
        assertEquals(XLOC, tank.getX());
        assertTrue(tank.isFacingRight());
    }

    @Test
    public void testFaceRight() {
        tank.faceLeft();
        tank.faceRight();
        assertTrue(tank.isFacingRight());
    }

    @Test
    public void testFaceLeft() {
        tank.faceRight();
        tank.faceLeft();
        assertFalse(tank.isFacingRight());
    }

    @Test
    public void testMoveRightOnce() {
        tank.move();
        assertEquals(XLOC + Tank.DX, tank.getX());
    }

    @Test
    public void testMoveLeftOnce() {
        tank.faceLeft();
        tank.move();
        assertEquals(XLOC - Tank.DX, tank.getX());
    }

    @Test
    public void testMoveRightMany() {
        tank.faceRight();
        final int NUM_UPDATES = (SIGame.WIDTH / 4) / Tank.DX;

        for (int count = 0; count < NUM_UPDATES; count++) {
            tank.move();
        }

        assertEquals(XLOC + NUM_UPDATES * Tank.DX, tank.getX());
    }

    @Test
    public void testMoveLeftMany() {
        final int NUM_UPDATES = (SIGame.WIDTH / 4) / Tank.DX;

        tank.faceLeft();

        for (int count = 0; count < NUM_UPDATES; count++) {
            tank.move();
        }

        assertEquals(XLOC - NUM_UPDATES * Tank.DX, tank.getX());
    }

    @Test
    public void testLeftBoundary() {
        tank.faceLeft();
        while (tank.getX() > 0)
            tank.move();

        assertEquals(0, tank.getX());

        tank.move();
        assertEquals(0, tank.getX());
    }

    @Test
    public void testRightBoundary() {
        while (tank.getX() < SIGame.WIDTH)
            tank.move();

        assertEquals(SIGame.WIDTH, tank.getX());

        tank.move();
        assertEquals(SIGame.WIDTH, tank.getX());
    }
}
