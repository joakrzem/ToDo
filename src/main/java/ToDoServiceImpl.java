import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToDoServiceImpl implements ToDoService {
    private int collectedPoints;
    private final Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(id);
    }

    @Override
    public int finishTask(int id) {
        collectedPoints = collectedPoints + tasks.get(id).getPoints();
        tasks.remove(id);
        return collectedPoints;
    }

    @Override
    public int collectedPoints() {
        return collectedPoints;
    }

    @Override
    public List<Task> tasksForDay(LocalDateTime day) {
        return tasks.values().stream()
                .filter(task -> task.getEndDate().isEqual(day))
                .collect(Collectors.toList());
    }

    @Override
    public Task getTask(int id) {
        return tasks.get(id);

    }

    @Override
    public Task modify(Task modified, int id) {
        modified.setId(id);
        tasks.replace(id, modified);

        return modified;
    }

    @Override
    public List<Task> allTasks() {
        return new ArrayList<>(tasks.values());

    }


}
