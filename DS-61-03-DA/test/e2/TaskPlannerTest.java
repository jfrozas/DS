package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskPlannerTest {

    Taskplanner taskplanner;
    Task A,B,C,D,E,F,G,H,J;

    @BeforeEach
    void setUp() {
        String[] array = {"C -> A","C -> F","A -> B","A -> D","B -> E","D -> E","F -> E","G -> F","G -> H","F -> J","H -> J"};
        taskplanner = new Taskplanner(Arrays.asList(array));

        A = new Task('A');
        B = new Task('B');
        C = new Task('C');
        D = new Task('D');
        E = new Task('E');
        F = new Task('F');
        G = new Task('G');
        H = new Task('H');
        J = new Task('J');
    }

    @Test
    void testInvalidinit(){
        assertThrows(IllegalArgumentException.class, () -> new Taskplanner(Arrays.asList("C","C ->","A -> B","A -> D")));
        assertThrows(IllegalArgumentException.class, () -> new Taskplanner(Arrays.asList(null,"C ->A")));
    }

    @Test
    void testStrongDependency(){
        Task[] strongDep = {C,A,B,D,G,F,E,H,J};
        assertEquals(new ArrayList<>(Arrays.asList(strongDep)),taskplanner.traverseGraph(new StrongdepTraverser()));
    }

    @Test
    void testWeakDependency(){
        Task[] strongDep = {C,A,B,D,E,F,G,H,J};
        assertEquals(new ArrayList<>(Arrays.asList(strongDep)),taskplanner.traverseGraph(new WeakdepTraverser()));
    }

    @Test
    void testHierarchicalOrder(){
        Task[] strongDep = {C,G,A,F,H,B,D,E,J};
        assertEquals(new ArrayList<>(Arrays.asList(strongDep)),taskplanner.traverseGraph(new HierarchicalTraverser()));
    }

}
