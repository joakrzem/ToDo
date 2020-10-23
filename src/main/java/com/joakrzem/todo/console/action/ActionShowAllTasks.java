package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.util.ArrayList;
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
            List<Task> activeTasks = new ArrayList<>();
            List<Task> finishedTasks = new ArrayList<>();
            List<Task> inProgressTasks = new ArrayList<>();
            List<Task> cancelledTasks = new ArrayList<>();
            for (Task task : allTask) {
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

    private List<Task> showAllTasks() {
        return toDoService.allTasks();
    }
}

