package com.mygame.entity.dynamic;

import com.mygame.entity.base.DynamicEntity;
import com.mygame.entity.base.FixedEntity;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Pacman extends DynamicEntity {
    public Pacman(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image, velocityX, velocityY, direction);
    }

    public void updateState(HashSet<FixedEntity> walls) {
        this.setX(this.x + this.velocityX);
        this.setY(this.y + this.velocityY);

        for(FixedEntity wall : walls) {
            if(wall.collision(this, wall )) {
                this.setX(this.x - this.velocityX);
                this.setY(this.y - this.velocityY);
                break;
            }
        }
    }
}
