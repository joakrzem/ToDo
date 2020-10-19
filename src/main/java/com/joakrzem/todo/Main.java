package com.joakrzem.todo;

import com.joakrzem.todo.console.ConsoleApp;
import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;
import com.joakrzem.todo.service.ToDoServiceImpl;
import com.joakrzem.todo.service.message.ExitMessageServiceImpl;
import com.joakrzem.todo.service.message.PointsMessageService;
import com.joakrzem.todo.service.message.PointsMessageServiceImpl;

import java.time.LocalDateTime;

public class Main {
    static PointsMessageService pointsMessageService = new PointsMessageServiceImpl();
    static ToDoService toDoService = new ToDoServiceImpl();
    static ConsoleAppUtils consoleAppUtils = new ConsoleAppUtils();
    static ConsoleApp consoleApp = new ConsoleApp(toDoService, pointsMessageService);
    static ExitMessageServiceImpl exitMessageService = new ExitMessageServiceImpl("exit-messages.txt");

    public static void main(String[] args) {
        Task task1 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "home", "do washing", "do washing", Priority.LOW, 5, 1);
        toDoService.addTask(task1);

        while (true) {
            consoleApp.printMenu();

            int choice = consoleAppUtils.getIntFromConsole("please enter number correctly");

            if (choice == 9) {
                System.out.println(exitMessageService.getMessage());
                break;
            }

            consoleApp.processMenuChoice(choice);
        }
    }


}


