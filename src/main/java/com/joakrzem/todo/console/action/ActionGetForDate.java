package com.joakrzem.todo.console.action;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.console.action.show.ShowTasksByStatus;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import com.joakrzem.todo.service.ToDoService;

import java.time.LocalDateTime;
import java.util.List;

public class ActionGetForDate implements Action {

    private final ToDoService toDoService;
    private final SplitTasksByStatus splitTasksByStatus;
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionGetForDate(ToDoService toDoService, SplitTasksByStatus splitTasksByStatus) {
        this.toDoService = toDoService;
        this.splitTasksByStatus = splitTasksByStatus;
    }

    @Override
    public String description() {
        return "Get for date";
    }

    @Override
    public void execute() {
        printForDate();
    }

    private void printForDate() {
        System.out.print("What day do want to show?");
        LocalDateTime data = consoleAppUtils.getLocalDateTimeFromConsole();
        List<Task> allTasks = toDoService.tasksForDay(data);
        if (allTasks.size() == 0) {
            System.out.println("There are no tasks for this date");
            return;
        }

        TasksByStatus tasksByStatus = splitTasksByStatus.split(allTasks);

        ShowTasksByStatus.show(tasksByStatus);
    }
}
