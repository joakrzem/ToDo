package com.joakrzem.todo.console.action;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.action.show.ShowTasksByStatus;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.List;

public class ActionShowAllTasks implements Action {

    private final ToDoService toDoService;
    private final SplitTasksByStatus splitTasksByStatus;
    private final MessageTranslationService messageTranslationService;
    private final ShowTasksByStatus showTasksByStatus;

    public ActionShowAllTasks(ToDoService toDoService, SplitTasksByStatus splitTasksByStatus, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.splitTasksByStatus = splitTasksByStatus;
        this.messageTranslationService = messageTranslationService;

        showTasksByStatus = new ShowTasksByStatus(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionShowAllTask");
    }

    @Override
    public void execute() {
        List<Task> allTask = getAllTasks();
        if (allTask.size() == 0) {
            System.out.println(messageTranslationService.getMessage("addSomeTask"));
        } else {
            TasksByStatus tasksByStatus = splitTasksByStatus.split(allTask);

            showTasksByStatus.show(tasksByStatus);
        }
    }

    private List<Task> getAllTasks() {
        return toDoService.allTasks();
    }
}

