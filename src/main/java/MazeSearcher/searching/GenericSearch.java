package MazeSearcher.searching;

public class GenericSearch
{
    private Frontier frontier;
    private Graph graph;

    public GenericSearch(Graph graph, Frontier frontier)
    {
        this.frontier = frontier;
        this.graph = graph;

        this.frontier.AddArc(graph.GetStartingArcs());
    }

    public Arc GetNextArc()
    {
        Arc frontierArc = frontier.GetNext();

        for (Arc arc : graph.GetOutgoingArcs(frontierArc))
        {
            this.frontier.AddArc(arc);
        }
        return frontierArc;
    }
}
