import java.time.LocalDateTime;
import java.util.List;

public interface ToDoService {
    void addTask(Task task);

    void removeTask(int id);

    int finishTask(int id);

    int collectedPoints();

    List<Task> tasksForDay(LocalDateTime day);

    Task getTask(int id);

    Task modify(Task modified, int id);

    List<Task> allTasks();


}
