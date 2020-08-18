package MazeSearcher.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainGUI extends Application
{
    /**
     * Name of the GUI
     */
    private final String GUI_TITLE = "Maze Searcher";
    private final int MIN_WIDTH = 950;
    private final int MIN_HEIGHT = 650;

    private final String menubarFXML = "menubar";

    /**
     * Resources to load
     */
    private final String[] resourceFXML = new String[]
    {
        "main",
    };

    /**
     * Main startup of GUI
     * @param primaryStage Autopassed variable from GUI Constructor
     * @throws IOException Thrown if .fxml files can't be found
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        //Root pane is borderpane, left is menubar, center is each pane
        BorderPane rootPane = new BorderPane();
        Node menuBar = FXMLLoader.load(getClass().getResource("/" + this.menubarFXML + ".fxml"));
        rootPane.setLeft(menuBar);

        //Instantiate WindowHandler singleton
        new WindowHandler(rootPane);

        //Load each FXML class once to have fast response time rather than on the fly.
        //MAYBE CHANGE if loading heavy classes
        for (String resource : this.resourceFXML)
        {
            Node source = FXMLLoader.load(getClass().getResource("/" + resource + ".fxml"));
            WindowHandler.GetInstance().AddWindow(resource, source);
        }

        //Set primaryScene to a new scene with rootPane as main content
        Scene primaryScene = new Scene(rootPane);
        primaryScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        //Set variables
        primaryStage.setTitle(this.GUI_TITLE);
        primaryStage.initStyle(StageStyle.UTILITY);

        primaryStage.setMinHeight(this.MIN_HEIGHT);
        primaryStage.setHeight(this.MIN_HEIGHT);

        primaryStage.setMinWidth(this.MIN_WIDTH);
        primaryStage.setWidth(this.MIN_WIDTH);

        primaryStage.setScene(primaryScene);

        //Set active window
        WindowHandler.GetInstance().SetActiveWindow("main");
        primaryStage.show();
    }
}

