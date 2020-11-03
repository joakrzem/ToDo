package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ActionModify implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleAppUtils consoleAppUtils;
    private final MessageTranslationService messageTranslationService;

    public ActionModify(ToDoService toDoService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.messageTranslationService = messageTranslationService;

        consoleAppUtils = new ConsoleAppUtils(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionModify");
    }

    @Override
    public void execute() {
        Task possiblyModified = modify();
        if (possiblyModified != null) {
            System.out.println(messageTranslationService.getMessage("modifyChangeTo") + " " + possiblyModified);
        }
    }

    private Task modify() {
        System.out.println(messageTranslationService.getMessage("modifyWhichTask"));
        int id = scanner.nextInt();
        scanner.nextLine();

        Task toModify = toDoService.getTask(id);
        if (toModify == null) {
            System.out.println(messageTranslationService.getMessage("taskDoesntExist"));
            return null;
        }
        if (toModify.getStatus() == Status.FINISHED) {
            System.out.println(messageTranslationService.getMessage("modifyCantModify"));
            return null;
        }
        System.out.print(messageTranslationService.getMessage("getTaskFromConsoleName"));
        String name = scanner.nextLine();
        if (!name.equals("")) {
            toModify.setName(name);
        }

        System.out.print(messageTranslationService.getMessage("getTaskFromConsoleCategory"));
        String category = scanner.nextLine();
        if (!category.equals("")) {
            toModify.setCategory(category);
        }

        System.out.print(messageTranslationService.getMessage("getTaskFromConsoleDescription"));
        String description = scanner.nextLine();
        if (!description.equals("")) {
            toModify.setDescription(description);
        }

        System.out.print(messageTranslationService.getMessage("getTaskFromConsoleEndDate"));
        String date = scanner.nextLine();
        if (!date.equals("")) {
            LocalDateTime dateToSet;
            try {
                dateToSet = LocalDateTime.parse(date);
            } catch (Exception e) {
                System.out.println(messageTranslationService.getMessage("modifyDateCorrectly"));
                dateToSet = consoleAppUtils.getLocalDateTimeFromConsole();
            }

            toModify.setEndDate(dateToSet);
        }

        System.out.print(messageTranslationService.getMessage("getTaskFromConsolePriority"));
        String priority = scanner.nextLine();
        if (!priority.equals("")) {
            Priority priorityToSet;
            try {
                priorityToSet = Priority.valueOf(priority.toUpperCase());
            } catch (Exception e) {
                System.out.println(messageTranslationService.getMessage("executePriorityCorrectly"));
                priorityToSet = consoleAppUtils.getPriorityFromConsole();
            }
            toModify.setPriority(priorityToSet);
        }

        System.out.print(messageTranslationService.getMessage("getTaskFromConsolePoints"));
        String points = scanner.nextLine();
        if (!points.equals("")) {
            int pointsToSet;
            try {
                pointsToSet = Integer.parseInt(points);
            } catch (Exception e) {
                System.out.println(messageTranslationService.getMessage("modifyPointCorrectly"));
                pointsToSet = consoleAppUtils.getIntFromConsole(messageTranslationService.getMessage("modifyPointCorrectly"));

            }
            toModify.setPoints(pointsToSet);
        }

        toDoService.modify(toModify, id);

        System.out.println(messageTranslationService.getMessage("removeTaskSuccessfullyRemoved"));
        return toModify;
    }
}