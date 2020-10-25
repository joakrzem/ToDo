package com.joakrzem.todo.console.action.show;

import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;

public class ShowTasksByStatus {

    public static void show(TasksByStatus tasksByStatus) {
        System.out.println("Active tasks");
        for (Task activeTask : tasksByStatus.getActiveTasks()) {
            System.out.println(activeTask);
        }

        System.out.println("Finished tasks");
        for (Task finishedTask : tasksByStatus.getFinishedTasks()) {
            System.out.println(finishedTask);
        }
        System.out.println("Cancelled tasks");
        for (Task finishedTask : tasksByStatus.getCancelledTasks()) {
            System.out.println(finishedTask);
        }
        System.out.println("In progress tasks");
        for (Task finishedTask : tasksByStatus.getInProgressTasks()) {
            System.out.println(finishedTask);
        }
    }
}
