package com.joakrzem.todo.console.action;

import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.PointsMessageService;

import java.util.HashMap;
import java.util.Map;

public class ActionService {
    private final Map<String, Action> actions;

    public ActionService(ToDoService toDoService, PointsMessageService pointsMessageService) {
        actions = new HashMap<>();

        initializeActions(toDoService, pointsMessageService);
    }

    private void initializeActions(ToDoService toDoService, PointsMessageService pointsMessageService) {
        actions.put("1", new ActionAddTask(toDoService));
        actions.put("2", new ActionRemoveTask(toDoService));
        actions.put("3", new ActionGetForDate(toDoService));
        actions.put("4", new ActionFinishTask(toDoService));
        actions.put("5", new ActionGetPoints(toDoService, pointsMessageService));
        actions.put("6", new ActionModify(toDoService));
        actions.put("7", new ActionGetTask(toDoService));
        actions.put("8", new ActionShowAllTasks(toDoService));
    }

    public void printMenu() {
        System.out.println("What do you want to do?");
        actions.forEach((key, action) -> System.out.println(key + ". " + action.description()));
    }

    public void executeAction(String key) {
        Action possibleAction = actions.get(key);
        if (possibleAction != null) {
            possibleAction.execute();
        } else {
            System.out.println("Action which has this number doesn't exist");
        }
    }
}
