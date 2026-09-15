package com.YaanMark.main;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 160;
    private final int HEIGHT = 120;
    private final int SCALE = 4;

    public Game() {
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
    }

    public void initFrame() {
        frame = new JFrame("Zelda Clone");
        frame.add(this);
        frame.setResizable(false);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public synchronized void start() {

        thread = new Thread(this);
        isRunning = true;
        thread.start();

    }

    public synchronized void stop() {

    }

    static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

        }
    }

    public void tick() {

    }

    public void render() {

    }

}
