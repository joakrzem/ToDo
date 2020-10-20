package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.time.LocalDateTime;
import java.util.List;

public class ActionGetForDate implements Action {

    private final ToDoService toDoService;
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionGetForDate(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Get for date";
    }

    @Override
    public void execute() {
        List<Task> forDate = getForDate();
        if (forDate.size() == 0) {
            System.out.println("There are no tasks for this date");
        } else {
            forDate.forEach(System.out::println);
        }
    }

    private List<Task> getForDate() {
        System.out.print("What day do want to show?");
        LocalDateTime data = consoleAppUtils.getLocalDateTimeFromConsole();
        return toDoService.tasksForDay(data);
    }

}
