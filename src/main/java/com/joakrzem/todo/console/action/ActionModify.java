package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ActionModify implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ActionModify(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Modify";
    }

    @Override
    public void execute() {
        Task possiblyModified = modify();
        if (possiblyModified != null) {
            System.out.println("Change to " + possiblyModified);
        }
    }

    private Task modify() {
        System.out.println("Which task do you want to modify?");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task toModify = toDoService.getTask(id);
        if (toModify == null) {
            System.out.println("Task which has this number doesn't exist");
            return null;
        }
        if (toModify.getStatus() == Status.FINISHED) {
            System.out.println("You can't modify finished task");
            return null;
        }
        System.out.print("name: ");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            toModify.setName(name);
        }

        System.out.print("category: ");
        String category = scanner.nextLine();
        if (!category.equals("")) {
            toModify.setCategory(category);
        }

        System.out.print("description: ");
        String description = scanner.nextLine();
        if (!description.equals("")) {
            toModify.setDescription(description);
        }

        System.out.print("endDate: ");
        String date = scanner.nextLine();
        if (!date.equals("")) {
            LocalDateTime dateToSet;
            try {
                dateToSet = LocalDateTime.parse(date);
            } catch (Exception e) {
                System.out.println("please enter end date correctly");
                dateToSet = consoleAppUtils.getLocalDateTimeFromConsole();
            }

            toModify.setEndDate(dateToSet);
        }

        System.out.print("priority: ");
        String priority = scanner.nextLine();
        if (!priority.equals("")) {
            Priority priorityToSet;
            try {
                priorityToSet = Priority.valueOf(priority.toUpperCase());
            } catch (Exception e) {
                System.out.println("please enter priority correctly");
                priorityToSet = consoleAppUtils.getPriorityFromConsole();
            }
            toModify.setPriority(priorityToSet);
        }

        System.out.print("points: ");
        String points = scanner.nextLine();
        if (!points.equals("")) {
            int pointsToSet;
            try {
                pointsToSet = Integer.parseInt(points);
            } catch (Exception e) {
                System.out.println("please enter point correctly");
                pointsToSet = consoleAppUtils.getIntFromConsole("please enter point correctly");

            }
            toModify.setPoints(pointsToSet);
        }

        toDoService.modify(toModify, id);

        System.out.println("Successfully modified");
        return toModify;
    }
}




