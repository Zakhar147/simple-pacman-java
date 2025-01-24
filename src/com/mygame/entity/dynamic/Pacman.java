package com.mygame.entity.dynamic;

import com.mygame.entity.base.DynamicEntity;

import java.awt.*;

public class Pacman extends DynamicEntity {
    public Pacman(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image, velocityX, velocityY, direction);
    }
}
