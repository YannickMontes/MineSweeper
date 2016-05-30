package minesweeper_model;

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

    public Case()
    {
        visible = false;
        flag = false;
        mine = false;
        value = 0;
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

    public void invertFlag()
    {
        this.flag = !this.flag;
        this.modified();
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
