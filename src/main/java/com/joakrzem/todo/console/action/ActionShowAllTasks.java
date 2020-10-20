package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.util.List;

public class ActionShowAllTasks implements Action {

    private final ToDoService toDoService;

    public ActionShowAllTasks(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Show all task";
    }

    @Override
    public void execute() {
        List<Task> allTask = showAllTasks();
        if (allTask.size() == 0) {
            System.out.println("There are no tasks. Add some");
        } else {
            allTask.forEach(System.out::println);
        }
    }

    private List<Task> showAllTasks() {
        return toDoService.allTasks();
    }
}

