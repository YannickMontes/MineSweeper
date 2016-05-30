/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.view;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeper.model.Grid;

/**
 *
 * @author yannick
 */
public class View extends Application
{
    private Grid grid;

    @Override
    public void start(Stage stage) throws Exception
    {
        this.grid = new Grid(8,8);
        
        BorderPane border = new BorderPane();
        
        GridPane gridPane = new GridPane();
        
        this.grid.addObserver(new Observer(){
            @Override
            public void update(Observable o, Object arg)
            {
                
            }
            
        }); 
    }
    
    
}
