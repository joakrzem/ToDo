package com.joakrzem.todo;

import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.ToDoServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ToDoService toDoService = new ToDoServiceImpl();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        Task task1 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "home", "do washing", "do washing", Priority.LOW, 5, 1);
        toDoService.addTask(task1);

        while (true) {
            printMenu();

            int choice = getIntFromConsole("please enter number correctly");

            if (choice == 9) {
                break;
            }

            processMenuChoice(choice);
        }
    }

    private static void printMenu() {
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

    private static void processMenuChoice(int choice) {
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
            System.out.println("You colleted " + points + " points Good Job");
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

    private static Task getTaskFromConsole() {

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("category: ");
        String category = scanner.nextLine();

        System.out.print("description: ");
        String description = scanner.nextLine();

        System.out.print("endDate: ");

        LocalDateTime endDate;
        endDate = getLocalDateTimeFromConsole();

        System.out.print("priority: ");
        Priority priority;

        priority = getPriorityFromConsole();


        System.out.print("points: ");
        int points = getIntFromConsole("please enter points correctly");

        System.out.print("id: ");

        int id = Math.abs(random.nextInt());

        return new Task(endDate, category, description, name, priority, points, id);

    }

    private static Priority getPriorityFromConsole() {
        Priority priority;
        while (true){
            String priorityLine = scanner.nextLine();
            try {
                priority = Priority.valueOf(priorityLine.toUpperCase());
                break;
            }
            catch (Exception ignored){
                System.out.println(" please enter priority correctly");
            }
        }
        return priority;
    }

    private static int getIntFromConsole(String message) {
        int integer;
        while (true) {
            String pointsLine = scanner.nextLine();

            try {
                integer = Integer.parseInt(pointsLine);
                break;
            } catch (Exception ignored) {
                System.out.println(message);
            }
        }
        return integer;
    }

    private static LocalDateTime getLocalDateTimeFromConsole() {
        LocalDateTime endDate;
        while (true) {
            String date = scanner.nextLine();

            try {
                endDate = LocalDateTime.parse(date);
                break;
            } catch (Exception ignored) {
                System.out.println("please enter date correctly");
            }
        }
        return endDate;
    }

    private static void removeTask() {
        System.out.print("Which tasks do you want to remove?");
        int id = getIntFromConsole("please enter id correctly");

        toDoService.removeTask(id);
    }

    private static List<Task> getForDate() {
        System.out.print("What day do want to show?");
        LocalDateTime data = getLocalDateTimeFromConsole();
        return toDoService.tasksForDay(data);

    }

    private static void finishTask() {
        System.out.println("Which task did you finish?");
        int finishedTask = scanner.nextInt();

        System.out.println("Congratulation you have already get " + toDoService.finishTask(finishedTask) + "points");
    }

    private static int getPoints() {
        return toDoService.collectedPoints();

    }

    private static Task modify() {
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
            }catch (Exception e) {
                System.out.println("please enter end date correctly");
                dateToSet = getLocalDateTimeFromConsole();
            }

            toModify.setEndDate(dateToSet);
        }

        System.out.print("priority: ");
        String priority = scanner.nextLine();
        if (!priority.equals("")) {
            Priority priorityToSet;
            try {
                priorityToSet = Priority.valueOf(priority.toUpperCase());
            }catch (Exception e){
                System.out.println("please enter priority correctly");
                priorityToSet = getPriorityFromConsole();
            }
            toModify.setPriority(priorityToSet);
        }

        System.out.print("points: ");
        String points = scanner.nextLine();
        if (!points.equals("")) {
            int pointsToSet;
            try {
                pointsToSet = Integer.parseInt(points);
            }
            catch (Exception e){
                System.out.println("please enter point correctly");
                pointsToSet = getIntFromConsole("please enter point correctly");

            }
            toModify.setPoints(pointsToSet);
        }

        toDoService.modify(toModify, id);

        return toModify;
    }

    private static Task getTask() {
        System.out.println("Which task do want to get?");
        int id = scanner.nextInt();
        return toDoService.getTask(id);

    }

    private static List<Task> showAllTasks() {
        return toDoService.allTasks();

    }

}
