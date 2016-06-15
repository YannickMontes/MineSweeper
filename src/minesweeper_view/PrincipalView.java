package minesweeper_view;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private Grid gridGame;

    @Override
    public void start(Stage stage) throws Exception
    {
        this.gridGame = new Grid(10,10);
        stage.setResizable(false);
        Scene scene = new Scene(new VBox(), 1200,1000, Color.LIGHTGRAY);
        MenuBar menuBar = new MenuBar();
        BorderPane border = new BorderPane();
        
        
        Menu options = new Menu("Options");
        MenuItem start = new MenuItem("Start...");
        
        start.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                Dialog param = new Dialog();
                param.setResizable(false);
                param.setTitle("New game");
                
                GridPane grid = new GridPane();
                
                Label width = new Label("Grid width (2 to 99): ");
                NumberTextField widthEntry = new NumberTextField();
                Label height = new Label("Grid height (2 to 99): ");
                NumberTextField heightEntry = new NumberTextField();
                Label mine_number = new Label("Mine number: ");
                NumberTextField mineEntry = new NumberTextField();
                
                grid.add(width, 0, 0);
                grid.add(height, 0, 1);
                grid.add(mine_number, 0, 2);
                grid.add(widthEntry, 1, 0);
                grid.add(heightEntry, 1, 1);
                grid.add(mineEntry, 1, 2);
                
                ButtonType create = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                param.getDialogPane().getButtonTypes().addAll(cancel,create);
                param.getDialogPane().setContent(grid);
                param.setContentText("Configure your new game:");
                
                Optional<ButtonType> result = param.showAndWait();
                                
                if(result.isPresent() && result.get().getButtonData()==ButtonBar.ButtonData.OK_DONE)
                {
                    if(widthEntry.getText().isEmpty() || heightEntry.getText().isEmpty() || mineEntry.getText().isEmpty()
                            && Integer.parseInt(widthEntry.getText()) >= 99 && Integer.parseInt(heightEntry.getText()) >= 99
                            && Integer.parseInt(widthEntry.getText())*Integer.parseInt(heightEntry.getText()) < Integer.parseInt(mineEntry.getText()))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Fail");
                        alert.setHeaderText("Aborted. Parameters weren't right");
                        alert.setContentText("-Height and width must not go over 99,\n"
                                + "-Mine number must be lower than width*height.");
                        alert.showAndWait();
                    }
                    else
                    {
                        gridGame = new Grid(Integer.parseInt(widthEntry.getText()), Integer.parseInt(heightEntry.getText()), Integer.parseInt(mineEntry.getText()));
                        paintGrid(border);
                    }
                }
                
                
            }
        });
        
        MenuItem restart = new MenuItem("Restart");
        restart.setDisable(true);
        options.getItems().addAll(start, restart);
        Menu help = new Menu("?");
        
        Menu about = new Menu("About");
        
        menuBar.getMenus().addAll(options, about, help);

        paintGrid(border);
        
        
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, border);
        stage.setTitle("MineSweeper");
        stage.setScene(scene);
        stage.show();
    }
    
    public void paintGrid(BorderPane border)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        
        for(int i=0; i<this.gridGame.getHeightGrid(); i++)
        {
            for(int j=0; j<this.gridGame.getWidthGrid(); j++)
            {
                CaseView cv = new CaseView(this.gridGame.getCase(i, j));
                gridPane.add(cv, i, j);
            }
        }
        
        border.setCenter(gridPane);
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}