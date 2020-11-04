package com.joakrzem.todo.console.action;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;
import com.joakrzem.todo.service.message.PointsMessageService;

import java.util.HashMap;
import java.util.Map;

public class ActionService {
    private final Map<String, Action> actions;
    private final MessageTranslationService messageTranslationService;

    public ActionService(ToDoService toDoService, PointsMessageService pointsMessageService,
                         SplitTasksByStatus splitTasksByStatus, MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
        actions = new HashMap<>();

        initializeActions(toDoService, pointsMessageService, splitTasksByStatus, messageTranslationService);
    }

    private void initializeActions(ToDoService toDoService, PointsMessageService pointsMessageService,
                                   SplitTasksByStatus splitTasksByStatus, MessageTranslationService messageTranslationService) {
        actions.put("1", new ActionAddTask(toDoService, messageTranslationService));
        actions.put("2", new ActionRemoveTask(toDoService, messageTranslationService));
        actions.put("3", new ActionGetForDate(toDoService, splitTasksByStatus, messageTranslationService));
        actions.put("4", new ActionFinishTask(toDoService, messageTranslationService));
        actions.put("5", new ActionGetPoints(toDoService, pointsMessageService, messageTranslationService));
        actions.put("6", new ActionModify(toDoService, messageTranslationService));
        actions.put("7", new ActionGetTask(toDoService, messageTranslationService));
        actions.put("8", new ActionShowAllTasks(toDoService, splitTasksByStatus, messageTranslationService));
        actions.put("9", new ActionSetLanguage(messageTranslationService));
    }

    public void printMenu() {
        System.out.println(messageTranslationService.getMessage("printMenuWhatToDo"));
        actions.forEach((key, action) -> System.out.println(key + ". " + action.description()));
    }

    public void executeAction(String key) {
        Action possibleAction = actions.get(key);
        if (possibleAction != null) {
            possibleAction.execute();
        } else {
            System.out.println(messageTranslationService.getMessage("executeActionActionDoesntExist"));
        }
    }
}
