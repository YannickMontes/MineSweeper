/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.model;

import java.util.Observable;

/**
 * This class symbolize the board of the game.
 * @author yannick
 */
public class Grid extends Observable
{
    /**
     * Variables which contain the height and width of the grid
     */
    private static int WIDTH_GRID;
    private static int HEIGHT_GRID;
    /**
     * Variable contenant le tableau
     */
    private Case[][] grid;
    
    public Grid(int width, int height)
    {
        WIDTH_GRID = width; 
        HEIGHT_GRID = height;
    }
}
