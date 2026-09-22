package com.YaanMark.world;

import com.YaanMark.entities.*;
import com.YaanMark.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    private Tile[] tiles;
    public static int width, height;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            width = map.getWidth();
            height = map.getHeight();
            tiles = new Tile[map.getWidth() * map.getHeight()];
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            for (int xx = 0; xx < map.getWidth(); xx++ ) {
                for (int yy = 0; yy < map.getHeight(); yy++) {
                    int pixelAtual = xx + (yy * map.getWidth());
                    if (pixels[pixelAtual] == 0xFF000000) {
                        //Chão
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                    } else if (pixels[pixelAtual] == 0xFFFFFFFF) {
                        //Parede
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_WALL);
                    }  else if (pixels[pixelAtual] == 0xFFFF5900) {
                        //Arma
                        Game.entities.add(new Staff(xx*16, yy*16,16,16, Entity.STAFF_EN));
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                    }  else if (pixels[pixelAtual] == 0xFF00DEFF) {
                        //Mana
                        Game.entities.add(new ManaPotion(xx*16, yy*16,16,16, Entity.MANAPOTION_EN));
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                    }  else if (pixels[pixelAtual] == 0xFFFF0000) {
                        //Poção
                        Game.entities.add(new HealthPotion(xx*16, yy*16,16,16, Entity.HEALTHPOTION_EN));
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                    }  else if (pixels[pixelAtual] == 0xFFC800FF) {
                        //Inimigo
                        Game.entities.add(new Enemy(xx*16, yy*16,16,16, Entity.ORC_EN));
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                    }  else if (pixels[pixelAtual] == 0xFF0000FF) {
                        //Player
                        tiles[xx + (yy * width)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
                        Game.player.setX(xx*16);
                        Game.player.setY(yy*16);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(Graphics g) {
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                Tile tile = tiles[xx + (yy*width)];
                tile.render(g);
            }
        }
    }

}
