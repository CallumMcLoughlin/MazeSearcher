package MazeSearcher.searching;

import java.util.ArrayList;

public class Arc implements Comparable<Arc>
{
    private int X;
    private int Y;

    private ArrayList<Arc> tailArcs;
    private double arcCost;

    private double arcEstimate = 0;

    /**
     * Constructor method
     * @param tail Arc tail
     * @param cost Cost to get to this arc from previous arc
     */
    public Arc(int x, int y, ArrayList<Arc> tail, double cost)
    {
        this.X = x;
        this.Y = y;

        this.tailArcs = tail;
        this.arcCost = cost;
    }

    /**
     * Get tail arcs
     * @return List of arcs to get to this arc
     */
    public ArrayList<Arc> GetTail()
    {
        return tailArcs;
    }

    /**
     * Get arc cost
     * @return Cost to get from previous arc to this arc
     */
    public double GetCost()
    {
        return this.arcCost;
    }

    public void SetEstimate(double estimate)
    {
        this.arcEstimate = estimate;
    }


    public double GetEstimate()
    {
        return this.arcEstimate;
    }

    public int GetX()
    {
        return this.X;
    }

    public int GetY()
    {
        return this.Y;
    }

    @Override
    public int compareTo(Arc o) {
        double thisCost = this.arcCost + this.arcEstimate;
        for (Arc someArc : tailArcs)
            thisCost += someArc.GetCost();

        double oCost = o.GetCost();
        for (Arc someArc : o.GetTail())
            oCost += someArc.GetCost() + someArc.GetEstimate();

        return (int)(thisCost - oCost);
    }
}
