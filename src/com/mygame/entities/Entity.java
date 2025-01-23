package com.mygame.entities;

import java.awt.*;

public class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    protected int startX;
    protected int startY;
    protected char direction = 'U';
    protected int velicityX = 0;
    protected int velicityY = 0;

    Entity(int x, int y, int width, int height, Image image) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    void updateDirection(char direction) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += velicityX;
        this.y += velicityY;
//        for(Pacman.Block wall : walls) {
//            if(collision(this, wall)) {
//                this.x -=  this.velicityX;
//                this.y -=  this.velicityY;
//                this.direction = prevDirection;
//                updateVelocity();
//            }
//        }
    }

    void updateVelocity() {
//        if(this.direction == 'U') {
//            this.velicityX = 0;
//            this.velicityY = -tileSize /20;
//
//        }
//        else if(this.direction == 'D') {
//            this.velicityX = 0;
//            this.velicityY = tileSize /20;
//        }
//        else if(this.direction == 'L') {
//            this.velicityX = -tileSize /20;
//            this.velicityY = 0;
//        }
//        else if(this.direction == 'R') {
//            this.velicityX = tileSize /20;
//            this.velicityY = 0;
//        }
    }

    void reset() {
        this.x = this.startX;
        this.y = this.startY;
    }
}
