package com.mygame.entity.dynamic;

import com.mygame.entity.base.DynamicEntity;

import java.awt.*;

public class Ghost extends DynamicEntity {
    public Ghost(int x, int y, int width, int height, Image image, int velocityX, int velocityY, String direction) {
        super(x, y, width, height, image, velocityX, velocityY, direction);
    }
}
