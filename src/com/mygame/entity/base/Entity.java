package com.mygame.entity.base;

import java.awt.*;

public abstract class Entity {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    Entity(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

}
