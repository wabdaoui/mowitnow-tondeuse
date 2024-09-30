package com.mowitnow.tondeuse.business;

import com.mowitnow.tondeuse.model.Pelouse;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DeplacementManager.class)
public class DeplacementManagerTest {

    @Autowired
    IDeplacementManager deplacementManager;

    @Test
    void testMoveForwardNorth() {
        Tondeuse tondeuse = new Tondeuse(0, 0, 'N');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(1, tondeuse.getY());
    }

    @Test
    void testMoveForwardNorthBound() {
        Tondeuse tondeuse = new Tondeuse(0, 5, 'N');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(5, tondeuse.getY());
    }

    @Test
    void testMoveForwardEast() {
        Tondeuse tondeuse = new Tondeuse(0, 0, 'E');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(1, tondeuse.getX());
    }

    @Test
    void testMoveForwardEastBound() {
        Tondeuse tondeuse = new Tondeuse(5, 0, 'E');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(5, tondeuse.getX());
    }

    @Test
    void testMoveForwardSouth() {
        Tondeuse tondeuse = new Tondeuse(0, 1, 'S');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getY());
    }

    @Test
    void testMoveForwardSouthBound() {
        Tondeuse tondeuse = new Tondeuse(0, 0, 'S');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getY());
    }

    @Test
    void testMoveForwardWest() {
        Tondeuse tondeuse = new Tondeuse(1, 0, 'W');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getX());
    }

    @Test
    void testMoveForwardWestBound() {
        Tondeuse tondeuse = new Tondeuse(0, 0, 'W');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getX());
    }

    @Test
    void testMoveForwardFromCenterNorth() {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'N');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(2, tondeuse.getY());
    }

    @Test
    void testMoveForwardFromCenterEast() {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'E');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(2, tondeuse.getX());
    }

    @Test
    void testMoveForwardFromCenterSouth() {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'S');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getY());
    }

    @Test
    void testMoveForwardFromCenterWest() {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'W');
        tondeuse.setPelouse(new Pelouse(5, 5));
        deplacementManager.moveForward(tondeuse);
        assertEquals(0, tondeuse.getX());
    }
}
