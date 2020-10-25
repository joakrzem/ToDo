package com.joakrzem.todo.console.action;

import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.service.ToDoService;

import java.util.Scanner;

public class ActionFinishTask implements Action {

    private final ToDoService toDoService;
    private final Scanner scanner = new Scanner(System.in);

    public ActionFinishTask(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String description() {
        return "Finish task";
    }

    @Override
    public void execute() {
        finishTask();
    }

    private void finishTask() {
        System.out.println("Which task did you finish?");
        int finishedTask = scanner.nextInt();
        Task taskToFinish = toDoService.getTask(finishedTask);

        if (taskToFinish != null) {
            if (taskToFinish.getStatus() != Status.FINISHED) {
                System.out.println("Congratulation you have already get " + toDoService.finishTask(finishedTask) + " points");
            } else {
                System.out.println("This task is already finished");
            }
        } else {
            System.out.println("Task which has this number doesn't exist");
        }
    }
}
