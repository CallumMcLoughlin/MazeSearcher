package MazeSearcher.gui.controller;

import MazeSearcher.model.Grid;
import MazeSearcher.model.GridNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable
{
    @FXML
    public Button startButton;

    @FXML
    public Button goalButton;

    @FXML
    public Button wallButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        startButton.setOnMouseClicked(x -> SetBrushNode(GridNode.Start));
        goalButton.setOnMouseClicked(x -> SetBrushNode(GridNode.End));
        wallButton.setOnMouseClicked(x -> SetBrushNode(GridNode.Wall));
    }

    public void SetBrushNode(GridNode node)
    {
        Grid.GetInstance().SetCurrentNodeType(node);
    }
}
