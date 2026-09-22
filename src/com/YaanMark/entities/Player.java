package com.YaanMark.entities;

import com.YaanMark.main.Game;
import com.YaanMark.world.Camera;
import com.YaanMark.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    public boolean right, up, left, down;
    public double speed = 1.4;

    private int frames = 0, maxFrames = 8, index = 0, maxIndex = 2;
    private boolean moved = false;

    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        rightPlayer = new BufferedImage[3];
        leftPlayer = new BufferedImage[3];
        upPlayer = new BufferedImage[3];
        downPlayer = new BufferedImage[3];

        for(int i = 0; i < 3; i++) {
            rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
        }
        for(int i = 0; i < 3; i++) {
            leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
        }
        for(int i = 0; i < 3; i++) {
            upPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 48, 16, 16);
        }
        for(int i = 0; i < 3; i++) {
            downPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 32, 16, 16);
        }

    }

    public void tick() {
        double dx = 0;
        double dy = 0;

        moved = false;

        if (right && World.isFree((int)(x+speed), this.getY())) {
            moved = true;
            dx += 1;
        }
        if (left && World.isFree((int)(x-speed), this.getY())) {
            moved = true;
            dx -= 1;
        }
        if (up && World.isFree(this.getX(), (int)(y-speed))) {
            moved = true;
            dy -= 1;
        }
        if (down && World.isFree(this.getX(), (int)(y+speed))) {
            moved = true;
            dy += 1;
        }

        if (dx != 0 && dy != 0) {
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
        }

        x += dx * speed;
        y += dy * speed;

        if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex) {
                    index = 0;
                }
            }
        }

        Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.width * 16 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.height * 16 - Game.HEIGHT);

    }

    public void render(Graphics g) {

        if (!down && !up && !right && !left) {
            g.drawImage(downPlayer[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
        }

        if (down) {
            g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (up) {
            g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (right) {
            g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (left) {
            g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        }
    }

}
