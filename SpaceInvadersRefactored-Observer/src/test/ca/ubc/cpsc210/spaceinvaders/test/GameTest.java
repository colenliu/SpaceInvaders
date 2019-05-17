package ca.ubc.cpsc210.spaceinvaders.test;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Sprite;
import ca.ubc.cpsc210.spaceinvaders.model.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the Game class.
 */
public class GameTest {
    private SIGame siGame;

    @BeforeEach
    public void runBefore() {
        siGame = new SIGame();
    }

    @Test
    public void testConstructor() {
        Tank t = siGame.getTank();
        assertEquals(SIGame.WIDTH / 2, t.getX());
        List<Sprite> sprites = siGame.getSprites();
        assertEquals(1, sprites.size());
    }

    @Test
    public void testUpdate() {
        Tank t = siGame.getTank();
        assertEquals(SIGame.WIDTH / 2, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 + Tank.DX, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 + 2 * Tank.DX, t.getX());
    }

    @Test
    public void testNonKeyPadKeyEvent() {
        Tank t = siGame.getTank();
        siGame.keyPressed(KeyEvent.VK_LEFT);
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - 2 * Tank.DX, t.getX());
        siGame.keyPressed(KeyEvent.VK_RIGHT);
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2, t.getX());
    }

    @Test
    public void testKeyPadKeyEvent() {
        Tank t = siGame.getTank();
        siGame.keyPressed(KeyEvent.VK_KP_LEFT);
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - 2 * Tank.DX, t.getX());
        siGame.keyPressed(KeyEvent.VK_KP_RIGHT);
        siGame.update();
        assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
        siGame.update();
        assertEquals(SIGame.WIDTH / 2, t.getX());
    }

    @Test
    public void testSpaceKeyEvent() {
        siGame.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(siGame.getSprites().size() >= 2);  // Tank + 1 missile + unspecified # of invaders
        siGame.keyPressed(KeyEvent.VK_SPACE);
        siGame.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(siGame.getSprites().size() >= 4);  // Tank + 3 missiles + unspecified # of invaders
        assertEquals(3, siGame.getNumMissiles());
    }
}
