package MazeSearcher.gui;

import javafx.scene.canvas.Canvas;

public class CanvasGrid extends Canvas
{
    /**
     * Constructor method
     */
    public CanvasGrid()
    {
    }

    @Override
    public double minHeight(double height)
    {
        return 0;
    }

    @Override
    public double minWidth(double width)
    {
        return 0;
    }

    @Override
    public double prefHeight(double height)
    {
        return 1000;
    }

    @Override
    public double prefWidth(double width)
    {
        return 1000;
    }

    @Override
    public double maxWidth(double width)
    {
        return 10000;
    }

    @Override
    public double maxHeight(double height)
    {
        return 10000;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
        super.setWidth(width);
        super.setHeight(height);
    }
}
