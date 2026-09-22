package com.YaanMark.entities;

import com.YaanMark.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public static BufferedImage HEALTHPOTION_EN = Game.spritesheet.getSprite(80, 0, 16, 16);
    public static BufferedImage STAFF_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
    public static BufferedImage MANAPOTION_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
    public static BufferedImage ORC_EN = Game.spritesheet.getSprite(32, 64, 16, 16);

    protected double x, y;
    protected int width, height;

    private BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public void setX(double newX){
        this.x = newX;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public int getX(){
        return (int) this.x;
    }

    public int getY(){
        return (int) this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX(), this.getY(), null);
    }

}