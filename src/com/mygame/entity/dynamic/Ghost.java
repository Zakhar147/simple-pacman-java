package com.mygame.entity.dynamic;

import com.mygame.entity.base.DynamicEntity;
import com.mygame.entity.base.Entity;
import com.mygame.entity.base.FixedEntity;
import com.mygame.pacman.Game;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class Ghost extends DynamicEntity {

    public Ghost(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image, velocityX, velocityY, direction);
    }

    public void reset(Set<Entity> walls) {
        super.reset();
        setNewDirection(walls);
    }

    public void setNewDirection(Set <Entity> walls) {
        char newDirection = this.directions[this.random.nextInt(directions.length)];
        this.updateDirection(newDirection, walls);
    }
}
