package com.joakrzem.todo.console;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.action.ActionService;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;
import com.joakrzem.todo.service.message.PointsMessageService;

public class ConsoleApp {
    private final ActionService actionService;
    private final MessageTranslationService messageTranslationService;

    public ConsoleApp(ToDoService toDoService, PointsMessageService pointsMessageService, SplitTasksByStatus splitTasksByStatus, MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
        this.actionService = new ActionService(toDoService, pointsMessageService, splitTasksByStatus, messageTranslationService);
    }

    public void printMenu() {
        actionService.printMenu();
        System.out.println("0. " + messageTranslationService.getMessage("consoleAppExit"));
    }

    public void processMenuChoice(String choice) {
        actionService.executeAction(choice);
    }
}
