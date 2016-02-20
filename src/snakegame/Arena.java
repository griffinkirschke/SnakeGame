/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Griffin Kirschke
 */
public class Arena extends Environment implements CellDataProviderIntf {

    private Grid grid;

    private Barriers barriers;
    private Snake snake;
    private ArrayList<Item> items;
    private Object body;

    public Arena() {
        this.setBackground(ResourceTools.loadImageFromResource("snakegame/Grass.svg.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(35, 10));
        body.add(new Point(35, 11));
        body.add(new Point(35, 12));
        body.add(new Point(35, 13));
        body.add(new Point(35, 14));

        snake = new Snake(body, Direction.LEFT, this);

        barriers = new Barriers();
        barriers.addBarrierRange(0, 0, 0, 35, Color.GRAY, this);
        barriers.addBarrierRange(0, 0, 69, 0, Color.GRAY, this);
        barriers.addBarrierRange(69, 0, 69, 35, Color.GRAY, this);
        barriers.addBarrierRange(0, 35, 69, 35, Color.GRAY, this);

        items = new ArrayList<>();
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));
        items.add(new Item(random(68) + 1, random(34) + 1, "POWER UP", ResourceTools.loadImageFromResource("snakegame/2000px-Red_pog.svg.png"), this));

        // items break and re-appear in another random position
    }

    public int random(int value) {
        return (int) (Math.random() * value);
    }

    public int random(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }

    public void checkIntersections() {

        if (snake.getHead().equals(snake.getBody())) {
            snake.addHealth(-1000);
        if (snake.getHead().equals(snake.getTail())) 
            snake.addHealth(-1000);
        }

        for (Barrier barrier : barriers.getBarriers()) {
            if (barrier.getLocation().equals(snake.getHead())) {
                snake.addHealth(-1000);

            }
        }

        // when the snake head hits the item, snake body adds + 3 in length
        for (Item item : items) {
            if (item.getLocation().equals(snake.getHead())) {
                snake.getBody().add(new Point(snake.getTail()));
                snake.getBody().add(new Point(snake.getTail()));
                snake.getBody().add(new Point(snake.getTail()));

// move the item
                item.setX(random(1, grid.getColumns() - 2));
                item.setY(random(1, grid.getRows() - 2));
            }
        }

    }

    public void initializeEnvironment() {

    }

    private int moveDelay = 2;
    private int moveDelayLimit = 3;

    public void timerTaskHandler() {

        if (snake != null) {
            if (moveDelay >= moveDelayLimit) {
                snake.move();
                moveDelay = 0;
            } else {
                moveDelay++;
            }
            checkIntersections();

        }
    }

    public void keyPressedHandler(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(Direction.DOWN);

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/snakegame/rattle.wav");
        }

    }

    public void keyReleasedHandler(KeyEvent e) {
    }

    public void environmentMouseClicked(MouseEvent e) {
    }

    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }

        if (snake != null) {
            snake.draw(graphics);
        }

        if (barriers != null) {
            barriers.draw(graphics);
        }

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);

            }
        }

    }

    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }

    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;

    }

}
