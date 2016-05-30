package minesweeper_controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper_model.Case;
import minesweeper_view.CaseView;

/**
 * This controller is used to control a cell of the grid.
 * @author yannick
 */
public class Controller_Case extends Controller implements EventHandler<MouseEvent>
{
    /**
     * Concerned cell
     */
    private Case c;
    /**
     * The view of this cell
     */
    private CaseView cv;
    
    /**
     * Base constructor
     * @param c The cell
     * @param cv The view
     */
    public Controller_Case(Case c, CaseView cv)
    {
        super();
        this.c = c;
        this.cv = cv;
    }

    /**
     * Click gestion
     * @param event 
     */
    @Override
    public void handle(MouseEvent event)
    {
        if(event.getButton() == MouseButton.PRIMARY)//Left click
        {

        }
        else if(event.getButton() == MouseButton.SECONDARY)//Right click
        {
            c.invertFlag();
        }
    }
}
