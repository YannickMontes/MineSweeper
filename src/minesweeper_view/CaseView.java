package minesweeper_view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import minesweeper_controller.Controller_Case;
import minesweeper_model.Case;

/**
 *
 * @author yannick
 */
public class CaseView extends Button implements Observer
{
    public static Image FLAG = new Image(CaseView.class.getResource("/minesweeper_images/flag.png").toExternalForm());   
    public static Image EMPTY = new Image(CaseView.class.getResource("/minesweeper_images/Empty.png").toExternalForm());
    public static Image MINE = new Image(CaseView.class.getResource("/minesweeper_images/Mine.png").toExternalForm());
    public static Image ONE = new Image(CaseView.class.getResource("/minesweeper_images/One.png").toExternalForm());
    public static Image TWO = new Image(CaseView.class.getResource("/minesweeper_images/Two.png").toExternalForm());
    public static Image THREE = new Image(CaseView.class.getResource("/minesweeper_images/Three.png").toExternalForm());
    public static Image FOUR = new Image(CaseView.class.getResource("/minesweeper_images/Four.png").toExternalForm());
    public static Image FIVE = new Image(CaseView.class.getResource("/minesweeper_images/Five.png").toExternalForm());
    public static Image SIX = new Image(CaseView.class.getResource("/minesweeper_images/Six.png").toExternalForm());
    public static Image SEVEN = new Image(CaseView.class.getResource("/minesweeper_images/Seven.png").toExternalForm());
    public static Image EIGHT = new Image(CaseView.class.getResource("/minesweeper_images/Eight.png").toExternalForm());

    private Case c;
    private Image image;
    
    public CaseView(Case c)
    {
        this.c = c;
        this.c.addObserver(this);
        this.changeFlag();
        
        Controller_Case controller = new Controller_Case(c, this);
        this.setOnMouseClicked(controller);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.checkImage();
    }

    private void checkImage()
    {
        if(this.c.isVisible())
        {
            switch(this.c.getValue())
            {
                case 0:
                    this.image = EMPTY;
                    break;
                case 1:
                    this.image = ONE;
                    break;
                case 2:
                    this.image = TWO;
                    break;
                case 3:
                    this.image = THREE;
                    break;
                case 4:
                    this.image = FOUR;
                    break;
                case 5:
                    this.image = FIVE;
                    break;
                case 6:
                    this.image = SIX;
                    break;
                case 7:
                    this.image = SEVEN;
                    break;
                case 8:
                    this.image = EIGHT;
                    break;
                default:
                    this.image = EMPTY;
                    break;
            }
            if(this.c.isMine())
            {
                this.image = MINE;
            }
            this.setDisable(true);
        }
        else
        {
            if(c.isFlagged())
            {
                this.image = FLAG;
            }
            else
            {
                this.image = EMPTY;
            }
        }
        this.setGraphic(new ImageView(this.image));
    }
    
    private void changeFlag()
    {
        if(c.isFlagged())
        {
            this.image = FLAG;        
        }
        else 
        {
            this.image = EMPTY;
        }
        this.setGraphic(new ImageView(this.image));
    }
}
