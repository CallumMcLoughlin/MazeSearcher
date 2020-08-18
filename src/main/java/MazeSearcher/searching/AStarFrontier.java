package MazeSearcher.searching;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarFrontier extends Frontier
{
    private Graph graph;
    private ArrayList<Arc> expandedArcs = new ArrayList<Arc>();

    private PriorityQueue<Arc> priorityQueue = new PriorityQueue<>();

    public AStarFrontier(Graph graph)
    {
        this.graph = graph;
    }

    @Override
    public void AddArc(Arc arc)
    {
        if (expandedArcs.contains(arc))
            return;
        arc.SetEstimate(graph.GetEstimatedCostToGoal(arc));
        priorityQueue.add(arc);
    }

    @Override
    public Arc GetNext()
    {
        Arc nextArc = priorityQueue.poll();
        while (expandedArcs.contains(nextArc))
            nextArc = priorityQueue.poll();
        return nextArc;
    }
}
