package MazeSearcher.searching;

public abstract class Frontier
{
    public abstract void AddArc(Arc arc);
    public abstract Arc GetNext();
}
