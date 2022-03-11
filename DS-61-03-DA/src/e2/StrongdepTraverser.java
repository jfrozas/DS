package e2;

import java.util.*;

public class StrongdepTraverser implements GraphTraverser {

    @Override
    public List<Task> traversegraph(Graph graph) {

        TreeMap<Task,List<Task>> graphMap = new TreeMap<>(graph.getmap());
        Graph g = new Graph(graphMap);

        List<Task> result = new ArrayList<>();

        while (!g.isEmptyGraph()){
            for (Task t : g.getmap().keySet()){
                if (!g.taskDepends(t)){
                    result.add(t);
                    g.deleteVertex(t);
                    break;
                }
            }
        }
        return result;
    }
}
