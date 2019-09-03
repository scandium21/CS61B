package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    // start and end time for running the constructor
    long startTime;
    long endTime;

    double timeout;
    double solutionWeight = 0;
    Vertex start;
    Vertex end;
    int numStatesExplored = 0;
    AStarGraph<Vertex> input;

    List<Vertex> result = new ArrayList<>();
    HashMap<Vertex, Double> distTo = new HashMap<>();
    // track the linkage, key = destination, value = source
    HashMap<Vertex, Vertex> edgeTo = new HashMap<>();

    ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        startTime = System.currentTimeMillis();
        distTo.put(start, 0.0);
        this.timeout = timeout;
        this.start = start;
        this.end = end;
        this.input = input;
        result.add(start);
        pq.add(start, 0.0);

        while (pq.size() != 0 && explorationTime()<timeout && !pq.getSmallest().equals(end)) {

            Vertex v = pq.removeSmallest();
            numStatesExplored += 1;
            relax(v);

            endTime = System.currentTimeMillis();
        }

    }


    private void relax(Vertex v) {
        for (WeightedEdge<Vertex> e : input.neighbors(v)) {
            double h = input.estimatedDistanceToGoal(e.to(),end);
            Vertex from = e.from();
            Vertex to = e.to();
            double w = e.weight();
            if (distTo.get(to)==null ||
                    distTo.get(from) + w  < distTo.get(e.to())) {
                distTo.put(to,distTo.get(from)+w);
                edgeTo.put(to, from);
                if (pq.contains(to)) { pq.changePriority(to,h+distTo.get(to)); }
                else { pq.add(to,h+distTo.get(to)); }
            }
        }

    }


    public SolverOutcome outcome() {
        if (explorationTime() > timeout) {
            result.clear();
            return SolverOutcome.TIMEOUT; }
        else if (edgeTo.get(end)==null) {
            result.clear();
            return SolverOutcome.UNSOLVABLE; }
        return SolverOutcome.SOLVED;
    }
    public List<Vertex> solution() {
        if (edgeTo.get(end)==null) {
            result.clear();
            return result;
        }
        Vertex v = end;
        while (!v.equals(start)){
            if (!result.contains(v)) {
                result.add(1,v);
            }
            v = edgeTo.get(v);
        }
        return result;
    }
    public double solutionWeight() {
        solutionWeight = distTo.get(end);
        if (outcome()==SolverOutcome.UNSOLVABLE || outcome() == SolverOutcome.TIMEOUT) {
            solutionWeight = 0;
        }
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return (endTime-startTime)/1000;
    }
}
