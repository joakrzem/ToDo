package com.joakrzem.todo;

import com.joakrzem.todo.businesslogic.SplitTasksByStatus;
import com.joakrzem.todo.console.ConsoleApp;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.ToDoServiceImpl;
import com.joakrzem.todo.service.message.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static MessageTranslationService messageTranslationService = new MessageTranslationServiceFile();
    static PointsMessageService pointsMessageService = new PointsMessageServiceImpl(messageTranslationService);
    static ToDoService toDoService = new ToDoServiceImpl();
    static ConsoleApp consoleApp = new ConsoleApp(toDoService, pointsMessageService, new SplitTasksByStatus(), messageTranslationService);
    static ExitMessageServiceImpl exitMessageService = new ExitMessageServiceImpl("exit-messages.txt");

    public static void main(String[] args) {
        Task task1 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "home", "do washing", "do washing", Priority.LOW, 5, 1);
        toDoService.addTask(task1);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            consoleApp.printMenu();

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                System.out.println(exitMessageService.getMessage());
                break;
            }

            consoleApp.processMenuChoice(choice);
        }
    }


}


