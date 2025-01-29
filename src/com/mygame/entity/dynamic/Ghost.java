package com.mygame.entity.dynamic;

import com.mygame.entity.base.DynamicEntity;
import com.mygame.entity.base.FixedEntity;

import java.awt.*;
import java.util.HashSet;

public class Ghost extends DynamicEntity {

    public Ghost(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image, velocityX, velocityY, direction);
    }

    public void updateState(HashSet<FixedEntity> walls) {
        this.setX(this.x + this.velocityX);
        this.setY(this.y + this.velocityY);
        for(FixedEntity wall : walls) {
            if(wall.collision(this, wall)) {
                this.setX(this.x - this.velocityX);
                this.setY(this.y - this.velocityY);
                this.setNewDirection(walls);
                break;
            }
        }
    }

    public void reset(HashSet<FixedEntity> walls) {
        super.reset();
        setNewDirection(walls);
    }

    public void setNewDirection(HashSet <FixedEntity> walls) {
        char newDirection = this.directions[this.random.nextInt(directions.length)];
        this.updateDirection(newDirection, walls);
    }
}
