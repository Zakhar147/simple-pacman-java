package com.mygame.entity.base;

import java.awt.*;

public class DynamicEntity extends Entity {
    private int velocityX;
    private int velocityY;
    private char direction;

    public DynamicEntity(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.direction = direction;
    }
}
