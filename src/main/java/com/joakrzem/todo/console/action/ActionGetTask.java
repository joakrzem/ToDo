package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.util.Scanner;

public class ActionGetTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);

    public ActionGetTask(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Get task";
    }

    @Override
    public void execute() {
        Task possibleTask = getTask();

        if (possibleTask != null) {
            System.out.println(possibleTask);
        } else {
            System.out.println("Task which has this number doesn't exist");
        }
    }

    private Task getTask() {
        System.out.println("Which task do want to get?");
        int id = scanner.nextInt();
        return toDoService.getTask(id);
    }

}
