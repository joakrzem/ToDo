package com.joakrzem.todo.console;

import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.message.PointsMessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleApp {
    private final ToDoService toDoService;
    private final PointsMessageService pointsMessageService;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();

    public ConsoleApp(ToDoService toDoService, PointsMessageService pointsMessageService) {
        this.toDoService = toDoService;
        this.pointsMessageService = pointsMessageService;
    }

    public void printMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add task");
        System.out.println("2. Remove task");
        System.out.println("3. Get for date");
        System.out.println("4. Finish task");
        System.out.println("5. Get points");
        System.out.println("6. Modify");
        System.out.println("7. Get task");
        System.out.println("8. Show all tasks");
        System.out.println("9. Exit");

    }

    public void processMenuChoice(int choice) {
        if (choice == 1) {
            Task task = getTaskFromConsole();
            System.out.println(task);
            toDoService.addTask(task);
        }
        if (choice == 2) {
            removeTask();
        }
        if (choice == 3) {
            List<Task> forDate = getForDate();
            forDate.forEach(System.out::println);

        }
        if (choice == 4) {
            finishTask();

        }
        if (choice == 5) {
            int points = getPoints();
            System.out.println("You colleted " + points + " points \n"
                    + pointsMessageService.getMessage(points));
        }
        if (choice == 6) {
            System.out.println("Change to " + modify());
        }
        if (choice == 7) {
            System.out.println(getTask());
        }
        if (choice == 8) {
            List<Task> allTask = showAllTasks();
            allTask.forEach(System.out::println);
        }

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


    private void removeTask() {
        System.out.print("Which tasks do you want to remove?");
        int id = consoleAppUtils.getIntFromConsole("please enter id correctly");

        toDoService.removeTask(id);
    }

    private List<Task> getForDate() {
        System.out.print("What day do want to show?");
        LocalDateTime data = consoleAppUtils.getLocalDateTimeFromConsole();
        return toDoService.tasksForDay(data);

    }

    private void finishTask() {
        System.out.println("Which task did you finish?");
        int finishedTask = scanner.nextInt();

        System.out.println("Congratulation you have already get " + toDoService.finishTask(finishedTask) + " points");
    }

    private int getPoints() {
        return toDoService.collectedPoints();

    }

    private Task modify() {
        System.out.println("Which task do you want to modify?");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task toModify = toDoService.getTask(id);

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

        return toModify;
    }

    private Task getTask() {
        System.out.println("Which task do want to get?");
        int id = scanner.nextInt();
        return toDoService.getTask(id);

    }

    private List<Task> showAllTasks() {
        return toDoService.allTasks();

    }
}
