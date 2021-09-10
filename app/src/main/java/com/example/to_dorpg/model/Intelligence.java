package com.example.to_dorpg.model;

public class Intelligence {

        public int value;

        public Intelligence(int value) {
            this.value=value;

        }

        public int getValue() {
            return value;

        }



    @Override
        public String toString() {
            return "intelligence{" +
                    "value=" + value + '\'' +

                    '}';
        }

    }
