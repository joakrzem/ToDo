package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.service.ToDoService;

import java.util.Scanner;

public class ActionRemoveTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionRemoveTask(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Remove task";
    }

    @Override
    public void execute() {
        removeTask();
    }

    private void removeTask() {
        System.out.print("Which tasks do you want to remove?");
        int id = consoleAppUtils.getIntFromConsole("please enter id correctly");

        if (toDoService.getTask(id) == null) {
            System.out.println("Task which has this number doesn't exist");
            return;
        }

        toDoService.removeTask(id);
        System.out.println("Successfully removed");
    }
}
