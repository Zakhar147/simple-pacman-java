package com.mygame.entity.base;

import java.awt.*;

public class DynamicEntity extends Entity {
    private int velocityX;
    private int velocityY;
    private String direction;

    public DynamicEntity(int x, int y, int width, int height, Image image, int velocityX, int velocityY, String direction) {
        super(x, y, width, height, image);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.direction = direction;
    }
}
