package com.example.to_dorpg.model;

public class Character {

    public int img_src;

        public Character(int imgSrc) {

            this.img_src = imgSrc;
        }

        public int getImgSrc() {
            return img_src;
        }
        public void setImgSrc(int imgSrc) {
            this.img_src = img_src;
        }
        @Override
        public String toString() {
            return "character{" +

                    "img_src='" + img_src + '\'' +
                    '}';
        }
}
