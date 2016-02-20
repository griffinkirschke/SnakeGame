/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Griffin Kirschke
 */
public class Barrier {
    


    
    public void draw(Graphics graphics){
            graphics.setColor(getColor());
            graphics.fill3DRect(cellData.getSystemCoordX(getX(), getY()),
                                cellData.getSystemCoordY(getX(), getY()),
                                cellData.getCellWidth(),
                                cellData.getCellHeight(),
                                true);
    }
    
    public Barrier(int x, int y, Color color, CellDataProviderIntf cellData){
        this.x = x;
        this.y = y;
        this.color = color;
        this.breakable = true;
        this.cellData = cellData;
    }
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x, y;
    private Color color;
    private boolean breakable = false;
    private CellDataProviderIntf cellData;
    
    public Point getLocation(){
        return new Point(x, y);
    }
    
    public int getX(){
        return x;
        
    }
    
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * @return the breakable
     */
    public boolean isBreakable() {
        return breakable;
    }
    
    /**
     * @param breakable the breakable to set
     */
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }
    
//</editor-fold>
}