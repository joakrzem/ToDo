package com.joakrzem.todo.console;

import com.joakrzem.todo.model.Priority;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleAppUtils {
    private final Scanner scanner = new Scanner(System.in);

    public Priority getPriorityFromConsole() {
        Priority priority;
        while (true) {
            String priorityLine = scanner.nextLine();
            try {
                priority = Priority.valueOf(priorityLine.toUpperCase());
                break;
            } catch (Exception ignored) {
                System.out.println(" please enter priority correctly");
            }
        }
        return priority;
    }

    public int getIntFromConsole(String message) {
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

    public LocalDateTime getLocalDateTimeFromConsole() {
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
}
