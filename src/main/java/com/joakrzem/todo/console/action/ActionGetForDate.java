package com.joakrzem.todo.console.action;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.console.action.show.ShowTasksByStatus;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.time.LocalDateTime;
import java.util.List;

public class ActionGetForDate implements Action {

    private final ToDoService toDoService;
    private final SplitTasksByStatus splitTasksByStatus;
    private final ConsoleAppUtils consoleAppUtils;
    private final MessageTranslationService messageTranslationService;
    private final ShowTasksByStatus showTasksByStatus;

    public ActionGetForDate(ToDoService toDoService, SplitTasksByStatus splitTasksByStatus, MessageTranslationService messageTranslationService) {
        this.toDoService = toDoService;
        this.splitTasksByStatus = splitTasksByStatus;
        this.messageTranslationService = messageTranslationService;

        consoleAppUtils = new ConsoleAppUtils(messageTranslationService);
        showTasksByStatus = new ShowTasksByStatus(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("descriptionGetForDate");
    }

    @Override
    public void execute() {
        printForDate();
    }

    private void printForDate() {
        System.out.print(messageTranslationService.getMessage("getForDateWhatDay"));
        LocalDateTime data = consoleAppUtils.getLocalDateTimeFromConsole();
        List<Task> allTasks = toDoService.tasksForDay(data);
        if (allTasks.size() == 0) {
            System.out.println(messageTranslationService.getMessage("getForDateThereAreNoTask"));
            return;
        }

        TasksByStatus tasksByStatus = splitTasksByStatus.split(allTasks);

        showTasksByStatus.show(tasksByStatus);
    }
}
