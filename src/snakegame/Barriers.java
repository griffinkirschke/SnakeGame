/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Griffin Kirschke
 */
public class Barriers {


    {
        barriers = new ArrayList<>();
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Barrier> barriers;
    
    /**
     * @return the barriers
     */
    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }
    
    /**
     * @param barriers the barriers to set
     */
    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }
    
    /**
     * @param barrier the barrier to set
     */
    public void add(Barrier barrier) {
        this.barriers.add(barrier);
    }
    
    /**
     * @param x
     * @param y
     * @param color
     * @param cellData
     */
    public void add(int x, int y, Color color, CellDataProviderIntf cellData) {
        this.barriers.add(new Barrier(x, y, color, cellData));
    }
    
    /**
     * @param xStart
     * @param yStart
     * @param xEnd
     * @param yEnd
     * @param color
     * @param cellData
     */
    public void addBarrierRange(int xStart, int yStart, int xEnd, int yEnd, Color color, CellDataProviderIntf cellData) {
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                this.barriers.add(new Barrier(x, y, color, cellData));
            }
        }
    }
//</editor-fold>

    void draw(Graphics graphics) {
        for (Barrier barrier : barriers) {
            barrier.draw(graphics);
        }
    }
}