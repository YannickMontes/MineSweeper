/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_view;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    private Case c;
    private Image image;
    
    public CaseView(Case c)
    {
        this.c = c;
        this.c.addObserver(this);
        this.repaint();
        
        Controller_Case controller = new Controller_Case(c, this);
        this.setOnMouseClicked(controller);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("J'ai été update");
        this.repaint();
    }

    private void repaint()
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
