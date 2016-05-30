/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_model;

/**
 * This class symbolize the board of the game.
 * @author yannick
 */
public class Grid
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
    
    /**
     * Base constructor. Create the grid with width columns and height rows.
     * @param width Nb columns
     * @param height Nb rows
     */
    public Grid(int width, int height)
    {
        WIDTH_GRID = width; 
        HEIGHT_GRID = height;
        this.initGrid();
    }
    
    /**
     * Internal function, initialize the variable grid.
     */
    private void initGrid()
    {
        this.grid = new Case[HEIGHT_GRID][WIDTH_GRID];
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.grid[i][j] = new Case();
            }
        }
    }
    
    public Case getCase(int i, int j)
    {
        return this.grid[i][j];
    }
    
    public int getWidthGrid()
    {
        return WIDTH_GRID;
    }
    
    public int getHeightGrid()
    {
        return HEIGHT_GRID;
    }
}
