/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author Griffin Kirschke
 */
public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationStarter.run(args, "Snake", new Dimension(1500,900), new Arena());
    }
    
}


