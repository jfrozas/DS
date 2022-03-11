package e2;

import java.util.Objects;

public class Task implements Comparable<Task> {

    final private Character name;

    public Task(char c) {
        this.name = c;
    }

    public Character getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return name.equals(task.getName());
    }

    @Override
    public int compareTo(Task t) {
        return this.name.compareTo(t.getName());
    }

}
