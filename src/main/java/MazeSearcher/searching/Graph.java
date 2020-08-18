package MazeSearcher.searching;

import MazeSearcher.model.GridNode;

import java.util.ArrayList;

public abstract class Graph
{
    public abstract boolean IsGoal(Arc arc);
    public abstract Arc GetStartingArcs();
    public abstract ArrayList<Arc> GetOutgoingArcs(Arc tailArc);

    /**
     * Useful if search algorithm implements a heuristic
     * @param arc Arc to get distance to goal from
     * @return Distance
     */
    public double GetEstimatedCostToGoal(Arc arc)
    {
        return 0;
    }
}
