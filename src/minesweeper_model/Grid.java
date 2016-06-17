package minesweeper_model;

import java.util.ArrayList;

/**
 * This class symbolize the board of the game.
 * @author Yannick Montes
 */
public class Grid
{
    /**
     * Variables which contain the height and width of the grid, and the number of mines
     */
    public final int WIDTH_GRID;
    public final int HEIGHT_GRID;
    public final int MINE_NUMBER;
    /**
     * Variable for the grid
     */
    private Case[][] grid;
    /**
     * Variable used to count the number of flahs on theœ&±(' grid
     */
    private int NB_FLAGS;
    
    /**
     * Base constructor. Create the grid with width columns and height rows.
     * @param width Nb columns
     * @param height Nb rows
     */
    public Grid(int width, int height)
    {
        this.WIDTH_GRID = width; 
        this.HEIGHT_GRID = height;
        this.MINE_NUMBER = width*height/5;
        this.NB_FLAGS = 0;
        this.initGrid();
    }
    
    /**
     * Base constructor. Create the grid with width columns and height rows, and mine_number mines.s
     * @param width Nb columns
     * @param height Nb rows
     * @param mine_number Number of mines
     */
    public Grid(int width, int height, int mine_number)
    {
        this.WIDTH_GRID = width; 
        this.HEIGHT_GRID = height;
        this.MINE_NUMBER = mine_number;
        this.NB_FLAGS = 0;
        this.initGrid();
    }
    
    /**
     * Internal function, initialize the variable grid.
     */
    private void initGrid()
    {
        //Grid creation
        this.grid = new Case[HEIGHT_GRID][WIDTH_GRID];
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.grid[i][j] = new Case(i,j, this);
            }
        }
        
        //Mine random generation
        
        ArrayList<Integer> mines = new ArrayList<>();
        for(int i=0; i<MINE_NUMBER; i++)
        {
            int nbAlea = 0;
            do
            {
                nbAlea = (int) (Math.random()*(HEIGHT_GRID*WIDTH_GRID));
            }while(mines.contains(nbAlea));
            mines.add(nbAlea);
        }
        
        int case_number = 0;
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.majNeighboors(i,j);
                if(mines.contains(case_number))
                {
                    this.grid[i][j].setMine(true);
                    for(Case c : this.grid[i][j].getNeighboors())
                    {
                        c.increaseValue();
                    }
                }
                case_number++;
            }
        }        
        
        System.out.println(this);
    }
    
    //GET & SET
    
    public Case getCase(int i, int j)
    {
        return this.grid[i][j];
    }
    
    /**
     * Used to know if the game is finished or not. Check if cases not visibles 
     * but not mines still remain.
     * @return True if it's finish, False else.
     */
    public boolean isGameFinished()
    {
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                if(!this.grid[i][j].isMine() && !this.grid[i][j].isVisible())
                {
                    return false;
                }
            }
        }
        return true;
    }       

    /**
     * Function used to put number on each neighbor case of a mine
     * @param i The row of the actual mine case
     * @param j The column of the actual mine case
     */
    private void majNeighboors(int i, int j)
    {
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
    }
    
    /**
     * Put all the case of the grid visible
     */
    public void showAll()
    {
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.grid[i][j].setVisible(true);
            }
        }
    }
    
    /**
     * Override ToString function
     * @return The grid in a string.
     */
    @Override
    public String toString()
    {
        String text = "";
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                if(this.grid[i][j].isMine())
                {
                    text += " M ";
                }
                else
                {
                    text += " "+this.grid[i][j].getValue()+" ";
                }
            }
            text += "\n";
        }    
        
        return text;
    }

    public boolean canPutFlag()
    {
        return this.NB_FLAGS < this.MINE_NUMBER; 
    }

    public void incrementFlagNumber()
    {
        if(this.NB_FLAGS < this.MINE_NUMBER)
        {
            this.NB_FLAGS++;
        }
    }

    public void decrementFlagNumber()
    {
        if(this.NB_FLAGS > 0)
        {
            this.NB_FLAGS--;
        }
    }
}
