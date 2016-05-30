/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper_model.Case;
import minesweeper_view.CaseView;

/**
 *
 * @author yannick
 */
public class Controller_Case extends Controller implements EventHandler<MouseEvent>
{
    private Case c;
    private CaseView cv;
    
    public Controller_Case(Case c, CaseView cv)
    {
        super();
        this.c = c;
        this.cv = cv;
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("Click");
        if(event.getButton() == MouseButton.PRIMARY)
        {
            System.out.println("Click gauche");
        }
        else if(event.getButton() == MouseButton.SECONDARY)
        {
            System.out.println("Click droit");
            c.invertFlag();
        }
    }
}
