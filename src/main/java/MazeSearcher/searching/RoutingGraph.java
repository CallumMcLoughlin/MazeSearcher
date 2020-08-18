package MazeSearcher.searching;

import MazeSearcher.model.Grid;
import MazeSearcher.model.GridNode;
import javafx.util.Pair;

import java.util.ArrayList;

public class RoutingGraph extends Graph
{
    ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>() {
        {
            add(new Pair(-1, 0));
            add(new Pair(1, 0));
            add(new Pair(0, -1));
            add(new Pair(0, 1));
        }
    };

    @Override
    public boolean IsGoal(Arc arc)
    {
        int x = arc.GetX();
        int y = arc.GetY();
        return Grid.GetInstance().GetGridArray()[x][y] == GridNode.End;
    }

    @Override
    public Arc GetStartingArcs()
    {
        Pair<Integer, Integer> pair = Grid.GetInstance().GetStartingLocation();
        return new Arc(pair.getKey(), pair.getValue(), null, 0);
    }

    @Override
    public ArrayList<Arc> GetOutgoingArcs(Arc tailArc)
    {
        ArrayList<Arc> newArcs = new ArrayList<>();

        for (Pair<Integer, Integer> pair : pairList)
        {
            int x = pair.getKey();
            int y = pair.getValue();

            if (IsInBounds(x, y))
            {
                ArrayList<Arc> arcList;
                if (tailArc != null && tailArc.GetTail() != null)
                    arcList = new ArrayList<Arc>(tailArc.GetTail());
                else
                    arcList = new ArrayList<>();

                arcList.add(tailArc);
                Arc newArc = new Arc(x, y, arcList, 1);
                newArc.SetEstimate(GetEstimatedCostToGoal(newArc));
            }
        }

        return newArcs;
    }

    @Override
    public double GetEstimatedCostToGoal(Arc arc)
    {
        return 0;
    }

    private boolean IsInBounds(int x, int y)
    {
        return !(x < 0 || x >= Grid.GetInstance().GetGridArray().length || y < 0 || y >= Grid.GetInstance().GetGridArray()[0].length);
    }
}
