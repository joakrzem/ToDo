package com.joakrzem.todo.console.action;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.action.show.ShowTasksByStatus;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import com.joakrzem.todo.service.ToDoService;

import java.util.List;

public class ActionShowAllTasks implements Action {

    private final ToDoService toDoService;
    private final SplitTasksByStatus splitTasksByStatus;

    public ActionShowAllTasks(ToDoService toDoService, SplitTasksByStatus splitTasksByStatus) {
        this.toDoService = toDoService;
        this.splitTasksByStatus = splitTasksByStatus;
    }

    @Override
    public String description() {
        return "Show all task";
    }

    @Override
    public void execute() {
        List<Task> allTask = getAllTasks();
        if (allTask.size() == 0) {
            System.out.println("There are no tasks. Add some");
        } else {
            TasksByStatus tasksByStatus = splitTasksByStatus.split(allTask);

            ShowTasksByStatus.show(tasksByStatus);
        }
    }

    private List<Task> getAllTasks() {
        return toDoService.allTasks();
    }
}

