/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.model;

/**
 * Representation of one case
 * @author yannick
 */
public class Case
{
    /**
     * Tell if the case is visible or not
     */
    private boolean visible;
    /**
     * Tell if there is a flag on the case or not
     */
    private boolean flag;

    public Case()
    {
        visible = false;
        flag = false;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isDrapeau()
    {
        return flag;
    }

    public void setDrapeau(boolean drapeau)
    {
        this.flag = drapeau;
    }
}
