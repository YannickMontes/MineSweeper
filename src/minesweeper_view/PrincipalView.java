package minesweeper_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import minesweeper_model.Grid;

/**
 * This class is the main view of the project. It create the grid, and display it.
 * @author yannick
 */
public class PrincipalView extends Application
{
    /**
     * The model grid
     */
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
