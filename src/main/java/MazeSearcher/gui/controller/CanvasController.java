package MazeSearcher.gui.controller;

import MazeSearcher.gui.CanvasGrid;
import MazeSearcher.model.Grid;
import MazeSearcher.model.GridNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CanvasController implements Initializable
{
    @FXML
    BorderPane rootPane;

    @FXML
    CanvasGrid canvasGrid;

    @FXML
    Slider gridSizeSlider;

    private GraphicsContext graphicsContext;
    private Grid grid;

    private void InitializeGraphics()
    {
        this.grid = Grid.GetInstance();
        this.graphicsContext = canvasGrid.getGraphicsContext2D();
        CalculateAndDrawGrid();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        InitializeGraphics();
        gridSizeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
                grid.SetSpacing(newValue.intValue());
                CalculateAndDrawGrid();
            }
        );
    }

    @FXML
    private void CalculateAndDrawGrid()
    {
        int hLineCount = (int) Math.floor((this.canvasGrid.getHeight() + 1) / grid.GetSpacing());
        int vLineCount = (int) Math.floor((this.canvasGrid.getWidth() + 1) / grid.GetSpacing());
        grid.Reset(hLineCount, vLineCount);
        DrawGrid();
    }

    /**
     * Draw grid on canvas
     */
    private void DrawGrid()
    {
        graphicsContext.clearRect(0, 0, this.canvasGrid.getWidth(), this.canvasGrid.getHeight());
        graphicsContext.setLineWidth(1);

        graphicsContext.setStroke(grid.GetLineColor());

        GridNode[][] gridArray = grid.GetGridArray();
        for (int x = 0; x < gridArray[0].length; x++)
            graphicsContext.strokeLine(0, Snap((x + 1) * grid.GetSpacing()), gridArray.length * canvasGrid.getWidth(), Snap((x + 1) * grid.GetSpacing()));

        for (int y = 0; y < gridArray.length; y++)
            graphicsContext.strokeLine(Snap((y + 1) * grid.GetSpacing()), 0, Snap((y + 1) * grid.GetSpacing()), gridArray[0].length * canvasGrid.getHeight());

        DrawGridArray();
    }

    public void DrawGridArray()
    {
        GridNode[][] gridArray = grid.GetGridArray();
        for (int x = 0; x < gridArray.length; x++)
        {
            for (int y = 0; y < gridArray[x].length; y++)
            {
                int cornerX = x * grid.GetSpacing();
                int cornerY = y * grid.GetSpacing();

                if (gridArray[x][y] != null)
                {
                    graphicsContext.setFill(grid.GetGridColor(gridArray[x][y]));
                    graphicsContext.fillRect(cornerX, cornerY, grid.GetSpacing(), grid.GetSpacing());
                }
            }
        }
    }

    /**
     * Snap to y
     * @param y Current y value
     * @return Snapped y value
     */
    private double Snap(double y)
    {
        return ((int) y) + 0.5;
    }

    @FXML
    public void MouseClicked(MouseEvent mouseEvent)
    {
        int gridLengthX = grid.GetGridArray().length;
        int gridLengthY = grid.GetGridArray()[0].length;

        double x = RoundTo(mouseEvent.getX(), grid.GetSpacing());
        double y = RoundTo(mouseEvent.getY(), grid.GetSpacing());

        int roundedX = (int) x / grid.GetSpacing();
        int roundedY = (int) y / grid.GetSpacing();

        if (roundedX < 0 || roundedX >= gridLengthX || roundedY < 0 || roundedY >= gridLengthY)
            return;

        grid.SetNode(roundedX, roundedY);
        DrawGridArray();
    }

    private int RoundTo(double val, int to)
    {
        return (int)Math.floor(val / to) * to;
    }
}
