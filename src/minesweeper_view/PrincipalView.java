package minesweeper_view;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import minesweeper_controller.Controller_Case;
import minesweeper_model.Grid;

/**
 *
 * @author yannick
 */
public class PrincipalView extends Application
{
    private Grid grid;

    @Override
    public void start(Stage stage) throws Exception
    {
        this.grid = new Grid(8,8);
        
        BorderPane border = new BorderPane();
        
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        
        for(int i=0; i<this.grid.getHeightGrid(); i++)
        {
            for(int j=0; j<this.grid.getWidthGrid(); j++)
            {
                CaseView cv = new CaseView(this.grid.getCase(i, j));
                gridPane.add(cv, i, j);
            }
        }
        
        border.setCenter(gridPane);
        
        Scene scene = new Scene(border, Color.LIGHTGRAY);
        
        stage.setTitle("MineSweeper");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
