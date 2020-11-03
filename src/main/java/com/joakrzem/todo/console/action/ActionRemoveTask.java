package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.Scanner;

public class ActionRemoveTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleAppUtils consoleAppUtils;
    private final MessageTranslationService messageTranslationService;

    public ActionRemoveTask(ToDoService toDoService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.messageTranslationService = messageTranslationService;

        consoleAppUtils = new ConsoleAppUtils(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descritpionRemoveTask");
    }

    @Override
    public void execute() {
        removeTask();
    }

    private void removeTask() {
        System.out.print(messageTranslationService.getMessage("removeTaskWhich"));
        int id = consoleAppUtils.getIntFromConsole(messageTranslationService.getMessage("removeTaskIdCorrectly"));

        if (toDoService.getTask(id) == null) {
            System.out.println(messageTranslationService.getMessage("modifyTaskDoesntExist"));
            return;
        }

        toDoService.removeTask(id);
        System.out.println(messageTranslationService.getMessage("removeTaskSuccessfullRemoved"));
    }
}
