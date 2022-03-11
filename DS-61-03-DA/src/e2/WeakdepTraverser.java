package e2;

import java.util.*;

public class WeakdepTraverser implements GraphTraverser{

    @Override
    public List<Task> traversegraph(Graph graph) {

        TreeMap<Task,List<Task>> graphMap = new TreeMap<>(graph.getmap());
        Graph g = new Graph(graphMap);

        List<Task> result = new ArrayList<>();
        List<Task> queue = new ArrayList<>();
        List<Task> aux;
        Task auxTask;

        for (Task t : g.getmap().keySet()){
            if (!g.taskDepends(t))
                queue.add(t);
        }
        while (!queue.isEmpty() && !g.isEmptyGraph()){
            Collections.sort(queue);

            auxTask = queue.get(0);
            queue.remove(0);
            result.add(auxTask);
            aux = g.getmap().get(auxTask);

            for (Task t : aux) {
                if (!queue.contains(t) && !result.contains(t))
                    queue.add(t);
            }
            g.deleteVertex(auxTask);

        }
        return result;
    }
}
