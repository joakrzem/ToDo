package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActionGetForDate implements Action {

    private final ToDoService toDoService;
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionGetForDate(ToDoService toDoService) {
        this.toDoService = toDoService;
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

        List<Task> activeTasks = new ArrayList<>();
        List<Task> finishedTasks = new ArrayList<>();
        List<Task> inProgressTasks = new ArrayList<>();
        List<Task> cancelledTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getStatus() == Status.ACTIVE) {
                activeTasks.add(task);
            } else {
                finishedTasks.add(task);
            }
        }

        System.out.println("Active tasks");
        for (Task activeTask : activeTasks) {
            System.out.println(activeTask);
        }

        System.out.println("Finished tasks");
        for (Task finishedTask : finishedTasks) {
            System.out.println(finishedTask);
        }
    }
}
