package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class ActionAddTask implements Action {
    private final ToDoService toDoService;
    private final MessageTranslationService messageTranslationService;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final ConsoleAppUtils consoleAppUtils;

    public ActionAddTask(ToDoService toDoService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.messageTranslationService = messageTranslationService;

        consoleAppUtils = new ConsoleAppUtils(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionAddTask");
    }

    @Override
    public void execute() {
        Task task = getTaskFromConsole();
        System.out.println(task);
        toDoService.addTask(task);
    }

    private Task getTaskFromConsole() {

        System.out.print(messageTranslationService.getMessage("name"));
        String name = scanner.nextLine();

        System.out.print(messageTranslationService.getMessage("category"));
        String category = scanner.nextLine();

        System.out.print(messageTranslationService.getMessage("description"));
        String description = scanner.nextLine();

        System.out.print(messageTranslationService.getMessage("endDate"));
        LocalDateTime endDate;
        endDate = consoleAppUtils.getLocalDateTimeFromConsole();

        System.out.print(messageTranslationService.getMessage("priority"));
        Priority priority;

        priority = consoleAppUtils.getPriorityFromConsole();


        System.out.print(messageTranslationService.getMessage("points"));
        int points = consoleAppUtils.getIntFromConsole(messageTranslationService.getMessage("modifyPointCorrectly"));

        System.out.print(messageTranslationService.getMessage("id"));

        int id = Math.abs(random.nextInt());

        return new Task(endDate, category, description, name, priority, points, id);
    }
}
