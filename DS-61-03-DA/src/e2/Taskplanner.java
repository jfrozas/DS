package e2;

import java.util.List;

public class Taskplanner {

    private final Graph graph;

    public Taskplanner(List<String> depList) {

        graph = new Graph();

        Task t1,t2;

        for (String s: depList) {

            if (s == null || s.length()<5) throw new IllegalArgumentException();

            t1 = new Task(s.charAt(0));
            t2 = new Task(s.charAt(5));

            graph.addVertexifAbsent(t1);
            graph.addVertexifAbsent(t2);

            graph.addDependency(t1,t2);
        }
    }

    public List<Task> traverseGraph(GraphTraverser traverser){
        return graph.traverseGraph(traverser);
    }

    public Graph getGraph() {
        return graph;
    }

}
