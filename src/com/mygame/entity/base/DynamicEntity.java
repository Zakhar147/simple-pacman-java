package com.mygame.entity.base;

import com.mygame.config.Config;
import com.mygame.entity.fixed.Wall;

import java.awt.*;
import java.util.Set;

public class DynamicEntity extends Entity {
    private int velocityX;
    private int velocityY;
    private char direction;
    private int baseSpeed = Config.TILE_SIZE/7;

    public DynamicEntity(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.direction = direction;
    }

    public void updateDirection(char direction, Set<FixedEntity> walls) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += this.velocityX;
        this.y += this.velocityY;
        for(FixedEntity wall : walls) {
            if(collision(this, wall)) {
                this.x -=  this.velocityX;
                this.y -=  this.velocityY;
                this.direction = prevDirection;
                updateVelocity();
            }
        }
    }

    public void updateVelocity() {
        if(this.direction == 'U') {
            this.velocityX = 0;
            this.velocityY = -baseSpeed;
        }
        else if(this.direction == 'D') {
            this.velocityX = 0;
            this.velocityY = baseSpeed;
        }
        else if(this.direction == 'L') {
            this.velocityX = -baseSpeed;
            this.velocityY = 0;
        }
        else if(this.direction == 'R') {
            this.velocityX = baseSpeed;
            this.velocityY = 0;
        }
    }

    public boolean collision(Entity a, Entity b) {
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
        updateVelocity();
    }

    public int getVelocityX() {
        return velocityX;
    }
    public int getVelocityY() {
        return velocityY;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
