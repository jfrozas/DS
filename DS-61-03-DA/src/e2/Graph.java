package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Graph {

    private final TreeMap<Task, List<Task>> graph;

    public Graph() {
        graph = new TreeMap<>();
    }

    public Graph(TreeMap<Task, List<Task>> treemap){
        graph = treemap;
    }

    public TreeMap<Task, List<Task>> getmap() {
        return graph;
    }

    boolean isEmptyGraph(){
        return graph.isEmpty();
    }

    void addVertexifAbsent(Task c){
        //graph.put(c,new ArrayList<Task>());
        graph.putIfAbsent(c,new ArrayList<>());
    }

    void addDependency(Task father, Task dependent){
        List<Task> depList = graph.get(father);
        depList.add(dependent);

        graph.replace(father,depList);
    }

    void deleteVertex(Task c){
        graph.remove(c);
    }

    List<Task> traverseGraph(GraphTraverser traverser){
        return traverser.traversegraph(this);
    }

    boolean taskDepends(Task t){

        List<Task> aux;

        for (Task key : graph.keySet()) {
            aux = graph.get(key);

            if (aux.contains(t))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

}
