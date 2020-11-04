package com.joakrzem.todo.console.action.show;

import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.List;

public class ShowTasksByStatus {
    public ShowTasksByStatus(MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
    }

    private final MessageTranslationService messageTranslationService;

    public void show(TasksByStatus tasksByStatus) {
        showTasksList(tasksByStatus.getActiveTasks(), messageTranslationService.getMessage("showAllTaskShowTaskByStatusActive"));
        showTasksList(tasksByStatus.getCancelledTasks(), messageTranslationService.getMessage("showAllTaskShowTaskByStatusCancelled"));
        showTasksList(tasksByStatus.getFinishedTasks(), messageTranslationService.getMessage("showAllTaskShowTaskByStatusFinished"));
        showTasksList(tasksByStatus.getInProgressTasks(), messageTranslationService.getMessage("showAllTaskShowTaskByStatusInProgress"));
    }

    private void showTasksList(List<Task> tasks, String status) {
        if (tasks != null && tasks.size() != 0) {
            System.out.println(status);

            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}