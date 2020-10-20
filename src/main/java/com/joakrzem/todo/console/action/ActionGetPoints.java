package com.joakrzem.todo.console.action;

import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.PointsMessageService;

public class ActionGetPoints implements Action {
    private final ToDoService toDoService;
    private final PointsMessageService pointsMessageService;

    public ActionGetPoints(ToDoService toDoService, PointsMessageService pointsMessageService) {
        this.toDoService = toDoService;
        this.pointsMessageService = pointsMessageService;
    }

    @Override
    public String description() {
        return "Get points";
    }

    @Override
    public void execute() {
        int points = getPoints();
        System.out.println("You colleted " + points + " points \n"
                + pointsMessageService.getMessage(points));
    }

    private int getPoints() {
        return toDoService.collectedPoints();

    }
}
