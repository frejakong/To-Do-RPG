package com.example.to_dorpg.model;

public class Attack {
    public int value;


    public Attack(int value) {
        this.value=value;

    }

    public int getValue() {
        return value;

    }



    @Override
    public String toString() {
        return "attack{" +
                "value=" + value + '\'' +

                '}';
    }
}
