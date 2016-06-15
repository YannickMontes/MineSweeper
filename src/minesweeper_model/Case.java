package minesweeper_model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Representation of one case
 * @author yannick
 */
public class Case extends Observable
{
    /**
     * Tell if the case is visible or not
     */
    private boolean visible;
    /**
     * Tell if there is a flag on the case or not
     */
    private boolean flag;
    /**
     * Tell if there is a mine
     */
    private boolean mine;
    /**
     * The value of the cell (1 means that there is one mine on the neighborhood).
     */
    private int value;
    /**
     * On which row is this case
     */
    private int row;
    /**
     * On which column is this case
     */
    private int column;
    /**
     * The grid parent, to know some variables in a fastest way.
     */
    public final Grid parent;
    
    private ArrayList<Case> neighboors;

    public Case(int i, int j, Grid p)
    {
        this.neighboors = new ArrayList<>();
        this.row = i;
        this.column = j;
        visible = false;
        flag = false;
        mine = false;
        value = 0;
        this.parent = p;
    }
    
    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
        this.modified();
    }
    
    public boolean isMine()
    {
        return this.mine;
    }
    
    public ArrayList<Case> getNeighboors()
    {
        return this.neighboors;
    }
    
    public void setMine(boolean mine)
    {
        this.mine = mine;
    }

    public int getValue()
    {
        return this.value;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public boolean isFlagged()
    {
        return flag;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }  

    public void invertFlag()
    {
        this.flag = !this.flag;
        this.modified();
    }
    
    public boolean isEmpty()
    {
        return this.value==0;
    }
    
    public void addNeighboor(Case ngh)
    {
        this.neighboors.add(ngh);
    }
    
    public void showCases(Case c, ArrayList<Case> visited)
    {
        c.setVisible(true);
        visited.add(c);
        if(c.isEmpty() && !c.isMine())
        {
            for(Case neigh : c.neighboors)
            {
                if(!visited.contains(neigh))// && (neigh.column==c.column || neigh.row==c.row))
                {
                    showCases(neigh, visited);
                }
            }
        }
        c.modified();
    }
    
    public void modified()
    {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }

    public void increaseValue()
    {
        this.value++;
    }
}
