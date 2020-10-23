package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class ActionAddTask implements Action {
    private final ToDoService toDoService;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionAddTask(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Add task";
    }

    @Override
    public void execute() {
        Task task = getTaskFromConsole();
        System.out.println(task);
        toDoService.addTask(task);
    }

    private Task getTaskFromConsole() {

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("category: ");
        String category = scanner.nextLine();

        System.out.print("description: ");
        String description = scanner.nextLine();

        System.out.print("endDate: ");

        LocalDateTime endDate;
        endDate = consoleAppUtils.getLocalDateTimeFromConsole();

        System.out.print("priority: ");
        Priority priority;

        priority = consoleAppUtils.getPriorityFromConsole();


        System.out.print("points: ");
        int points = consoleAppUtils.getIntFromConsole("please enter points correctly");

        System.out.print("id: ");

        int id = Math.abs(random.nextInt());


        return new Task(endDate, category, description, name, priority, points, id);
    }
}
