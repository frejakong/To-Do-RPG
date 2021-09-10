package com.example.to_dorpg.model;

public class Strength {
    public int value;
    public int percentage;


    public Strength(int value) {
        this.value=value;

    }

    public int getValue() {
        return value;

    }


    @Override
    public String toString() {
        return "strength{" +
                "value=" + value + '\'' +
                '}';
    }

}
