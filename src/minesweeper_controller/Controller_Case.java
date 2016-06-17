package minesweeper_controller;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper_model.Case;
import minesweeper_view.CaseView;
import minesweeper_view.PrincipalView;

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
     * Principal view
     */
    private PrincipalView parent;
    /**
     * Base constructor
     * @param c The cell
     * @param cv The view
     */
    public Controller_Case(Case c, CaseView cv, PrincipalView p)
    {
        super();
        this.c = c;
        this.parent = p;
        this.cv = cv;
    }

    /**
     * Click gestion
     * @param event 
     */
    @Override
    public void handle(MouseEvent event)
    {
        //To begin, check if the case is hidden. If it's not, uselss to do the job.
        if(!c.isVisible())
        {
            if(event.getButton() == MouseButton.PRIMARY)//Then on Left click
            {
                if(c.isMine())//If it's a mine
                {
                    c.parent.showAll();//We put all the case visible
                    parent.endGame(false);//End of the game, defeat
                    return;
                }
                //We call the function to show all the neighborhood
                this.c.showCases(c, new ArrayList<Case>());
                //If the game is finished
                if(this.c.parent.isGameFinished())
                {
                    //Victory
                    c.parent.showAll();
                    parent.endGame(true);
                }
            }
            else if(event.getButton() == MouseButton.SECONDARY)//Then on Right click
            {
                //We invert the flag, if there is less flags than the number of mines
                c.invertFlag();
            }
            if(this.parent.restart.isDisable())
            {
                this.parent.restart.setDisable(false);
            }
        }
    }
}
