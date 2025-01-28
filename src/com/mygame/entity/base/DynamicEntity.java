package com.mygame.entity.base;

import com.mygame.config.Config;
import com.mygame.entity.dynamic.Ghost;
import com.mygame.entity.dynamic.Pacman;
import com.mygame.entity.fixed.Wall;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class DynamicEntity extends Entity {
    private int velocityX;
    private int velocityY;
    private char direction;
    private int baseSpeed = Config.TILE_SIZE/20;
    protected char[] directions;
    protected Random random;

    public DynamicEntity(int x, int y, int width, int height, Image image, int velocityX, int velocityY, char direction) {
        super(x, y, width, height, image);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.direction = direction;
        this.directions = new char[]{'U', 'L', 'D', 'R'};
        random = new Random();
    }

    public void updateDirection(char direction, Set<Entity> walls) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += this.velocityX;
        this.y += this.velocityY;
        for(Entity wall : walls) {
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
