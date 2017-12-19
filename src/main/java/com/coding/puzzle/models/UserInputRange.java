package com.coding.puzzle.models;

public class UserInputRange {

    private final int min;

    private final int max;

    public UserInputRange(int min, int max) {
        this.min = min;
        this.max = max;
    }
   
    public boolean contains(int value) {
        return value >= min && value <= max;
    }
}
