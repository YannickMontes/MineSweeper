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
        if(!c.isVisible())
        {
            this.changeFlag();   
        }
        else
        {
            //Afficher vide si vide, et annuler l'appui possible sur le bouton
            //Afficher le chiffre si pas vide (et annuler l'appui).
        }
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
