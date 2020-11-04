package com.joakrzem.todo.console.action;

import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;
import com.joakrzem.todo.service.message.PointsMessageService;

public class ActionGetPoints implements Action {
    private final ToDoService toDoService;
    private final PointsMessageService pointsMessageService;
    private final MessageTranslationService messageTranslationService;

    public ActionGetPoints(ToDoService toDoService, PointsMessageService pointsMessageService, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.pointsMessageService = pointsMessageService;
        this.messageTranslationService = messageTranslationService;
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionGetPoints");
    }

    @Override
    public void execute() {
        int points = getPoints();
        System.out.println(String.format(messageTranslationService.getMessage("getPointsCollectedPoints"), points) + "\n"
                + pointsMessageService.getMessage(points));
    }

    private int getPoints() {
        return toDoService.collectedPoints();

    }
}
