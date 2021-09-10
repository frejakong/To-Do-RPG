package com.example.to_dorpg.model;

public class Gold {
    public int coins;


    public Gold(int coins) {
        this.coins=coins;

    }

    public int getCoins() {
        return coins;
    }

    @Override
    public String toString() {
        return "gold{" +
                "coins=" + coins + '\'' +
                '}';
    }
}
