package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.Scanner;

public class ActionGetTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final MessageTranslationService messageTranslationService;

    public ActionGetTask(ToDoService toDoService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.messageTranslationService = messageTranslationService;
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionGetTask");
    }

    @Override
    public void execute() {
        Task possibleTask = getTask();

        if (possibleTask != null) {
            System.out.println(possibleTask.getStatus().toString() + " " + possibleTask);
        } else {
            System.out.println(messageTranslationService.getMessage("taskDoesntExist"));
        }
    }

    private Task getTask() {
        System.out.println(messageTranslationService.getMessage("getTaskWhichTask"));
        int id = scanner.nextInt();
        return toDoService.getTask(id);
    }

}
