package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.Scanner;

public class ActionFinishTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final MessageTranslationService messageTranslationService;

    public ActionFinishTask(ToDoService toDoService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.messageTranslationService = messageTranslationService;
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionFinishTask");
    }

    @Override
    public void execute() {
        finishTask();
    }

    private void finishTask() {
        System.out.println(messageTranslationService.getMessage("finishTaskWhichTask"));
        int finishedTask = scanner.nextInt();
        Task taskToFinish = toDoService.getTask(finishedTask);

        if (taskToFinish != null) {
            if (taskToFinish.getStatus() != Status.FINISHED) {
                System.out.println(messageTranslationService.getMessage("finishTaskPoints") + " " + toDoService.finishTask(finishedTask) + " " + messageTranslationService.getMessage("finishTaskGetPoints"));
            } else {
                System.out.println(messageTranslationService.getMessage("finishTaskWhichTaskFinish"));
            }
        } else {
            System.out.println(messageTranslationService.getMessage("taskDoesntExist"));
        }
    }
}
