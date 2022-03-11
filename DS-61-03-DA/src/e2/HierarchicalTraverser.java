package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class HierarchicalTraverser implements GraphTraverser{

    @Override
    public List<Task> traversegraph(Graph graph) {

        TreeMap<Task,List<Task>> graphMap = new TreeMap<>(graph.getmap());
        Graph g = new Graph(graphMap);

        List<Task> result = new ArrayList<>();
        List<Task> queue = new ArrayList<>();
        List<Task> queue2 = new ArrayList<>();

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
                if (!queue.contains(t) && !queue2.contains(t) && !result.contains(t))
                    queue2.add(t);
            }

            if (queue.isEmpty() && !queue2.isEmpty()){
                queue.addAll(queue2);
                queue2.clear();
            }

        }

        return result;
    }
}
