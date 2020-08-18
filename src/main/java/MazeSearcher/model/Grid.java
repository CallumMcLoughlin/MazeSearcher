package MazeSearcher.model;

import MazeSearcher.searching.AStarFrontier;
import MazeSearcher.searching.Arc;
import MazeSearcher.searching.GenericSearch;
import MazeSearcher.searching.RoutingGraph;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.util.HashMap;

public class Grid
{
    private static Grid Instance;
    public static Grid GetInstance()
    {
        if (Instance == null)
            Instance = new Grid();
        return Instance;
    }

    private Color gridColor = Color.GREY;

    private HashMap<GridNode, Color> colorMap = new HashMap<>();
    {
        colorMap.put(GridNode.End, Color.RED);
        colorMap.put(GridNode.Start, Color.GREEN);
        colorMap.put(GridNode.Wall, Color.BLACK);
    }

    private Pair<Integer, Integer> startLocation;
    private Pair<Integer, Integer> endLocation;

    public Pair<Integer, Integer> GetStartingLocation()
    {
        return startLocation;
    }

    public Pair<Integer, Integer> GetEndLocation()
    {
        return endLocation;
    }


    private int gridSpacing = 20;

    private GridNode currentType = GridNode.Wall;

    private GridNode[][] gridArray;

    private Grid() {}

    public void Reset(int row, int col)
    {
        gridArray = new GridNode[col][row];
        startLocation = null;
        endLocation = null;
    }


    public void SetCurrentNodeType(GridNode type)
    {
        this.currentType = type;
    }

    public void SetNode(int x, int y)
    {
        if (currentType == GridNode.Start)
        {
            if (startLocation != null)
                return;
            startLocation = new Pair<>(x, y);
        }
        else if (currentType == GridNode.End)
        {
            if (endLocation != null)
                return;
            endLocation = new Pair<>(x, y);
        }

        if (this.gridArray[x][y] != null)
            return;

        this.gridArray[x][y] = currentType;
    }

    public GridNode[][] GetGridArray()
    {
        return this.gridArray;
    }

    public int GetSpacing()
    {
        return this.gridSpacing;
    }

    public void SetSpacing(int newValue)
    {
        this.gridSpacing = newValue;
    }

    public Paint GetGridColor(GridNode type)
    {
        return this.colorMap.get(type);
    }

    public Paint GetLineColor()
    {
        return this.gridColor;
    }
}
