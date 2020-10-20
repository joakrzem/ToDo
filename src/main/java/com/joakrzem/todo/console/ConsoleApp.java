package com.joakrzem.todo.console;

import com.joakrzem.todo.console.action.ActionService;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.PointsMessageService;

public class ConsoleApp {
    private final ActionService actionService;

    public ConsoleApp(ToDoService toDoService, PointsMessageService pointsMessageService) {
        this.actionService = new ActionService(toDoService, pointsMessageService);
    }

    public void printMenu() {
        actionService.printMenu();
        System.out.println("0. Exit");
    }

    public void processMenuChoice(String choice) {
        actionService.executeAction(choice);
    }
}
