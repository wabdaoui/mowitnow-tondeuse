package com.mowitnow.tondeuse.model;


public class Tondeuse {
    private int x;
    private int y;
    private char orientation;
    private String instructions;
    private Pelouse pelouse;

    public Tondeuse() {
    }

    public Tondeuse(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;

    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Pelouse getPelouse() {
        return pelouse;
    }

    public void setPelouse(Pelouse pelouse) {
        this.pelouse = pelouse;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}
