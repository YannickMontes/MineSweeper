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
 * @author Yannick Montes
 */
public class PrincipalView extends Application
{
    /**
     * The model grid
     */
    private Grid gridGame;
    /**
     * Public constants for the screen size
     */
    public final static int WIDTH_SCREEN = 1000;
    public final static int HEIGHT_SCREEN = 1000;
    /**
     * Variables
     */
    private BorderPane border;
    private Stage stage;
    public MenuItem restart;

    /**
     * Start function
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage s) throws Exception
    {
        this.stage = s;
        this.gridGame = new Grid(10,10);
        //stage.setResizable(false);
        Scene scene = new Scene(new VBox(), Color.LIGHTGRAY);
        MenuBar menuBar = new MenuBar();
        border = new BorderPane();
        
        
        Menu options = new Menu("Options");
        MenuItem start = new MenuItem("Start...");
        
        start.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                createNewGame();
            }
        });
        
        restart = new MenuItem("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                gridGame = new Grid(gridGame.WIDTH_GRID, gridGame.HEIGHT_GRID, gridGame.MINE_NUMBER);
                paintGrid(border);
            }
        });
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
    
    /**
     * Function used to display a window with new game parameters
     */
    public void createNewGame()
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
                    || Integer.parseInt(widthEntry.getText()) < 2 || Integer.parseInt(heightEntry.getText()) < 2
                    || Integer.parseInt(widthEntry.getText()) >= 99 || Integer.parseInt(heightEntry.getText()) >= 99
                    || Integer.parseInt(widthEntry.getText())*Integer.parseInt(heightEntry.getText()) < Integer.parseInt(mineEntry.getText())
                    || Integer.parseInt(mineEntry.getText()) < 1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setResizable(true);
                alert.setTitle("Fail");
                alert.setHeaderText("Aborted. Parameters weren't right");
                alert.setContentText("-Height and width must not go over 99,\n"
                        + "-Mine number must be lower than width*height, and higher or equals than 1.");
                alert.showAndWait();
                createNewGame();
            }
            else
            {
                gridGame = new Grid(Integer.parseInt(widthEntry.getText()), Integer.parseInt(heightEntry.getText()), Integer.parseInt(mineEntry.getText()));
                paintGrid(border);
            }
        }
    }
    
    /**
     * Function used to paint the grid to the window
     * @param border The borderPane which contain the grid.
     */
    public void paintGrid(BorderPane border)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        //gridPane.setGridLinesVisible(true);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        for(int i=0; i<this.gridGame.HEIGHT_GRID; i++)
        {
            for(int j=0; j<this.gridGame.WIDTH_GRID; j++)
            {
                CaseView cv = new CaseView(this.gridGame.getCase(i, j), this);
                gridPane.add(cv, j, i);
            }
        }
        border.setCenter(gridPane);
        //gridPane.setMaxSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        //border.setMaxSize(WIDTH_SCREEN, HEIGHT_SCREEN);
    }
    
    /**
     * Main function
     * @param args 
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Function used to show a dialog in case of victory or defeat.
     * @param victory Victory or not
     */
    public void endGame(boolean victory)
    {
        if(victory)
        {
            Alert end = new Alert(Alert.AlertType.INFORMATION, "YOU WIN!", ButtonType.YES, ButtonType.NO);
            end.setTitle("VICTORY");
            end.setHeaderText("Field is successfully cleared!");
            end.setContentText("Again?");
            end.showAndWait();
            if(end.getResult() == ButtonType.YES)
            {
                createNewGame();
            }
            else
            {
                stage.close();
            }
        }
        else
        {
            Alert end = new Alert(Alert.AlertType.ERROR, "GAME OVER! YOU FIND A MINE!", new ButtonType("Same grid"), ButtonType.YES, ButtonType.NO);
            end.setTitle("GAME OVER");
            end.setHeaderText("GAME OVER! YOU FIND A MINE!");
            end.setContentText("Retry?");
            end.showAndWait();
            if(end.getResult() == ButtonType.YES)
            {
                createNewGame();
            }
            else if(end.getResult().getButtonData() == ButtonBar.ButtonData.OTHER)
            {
                this.gridGame = new Grid(this.gridGame.WIDTH_GRID, this.gridGame.HEIGHT_GRID, this.gridGame.MINE_NUMBER);
                paintGrid(border);
            }
            else
            {
                stage.close();
            }
        }          
    }
}