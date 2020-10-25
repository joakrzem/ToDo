package com.joakrzem.todo.businesslogic;

import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;

import java.util.ArrayList;
import java.util.List;

public class SplitTasksByStatus {

    public TasksByStatus split(List<Task> tasks) {
        List<Task> activeTasks = new ArrayList<>();
        List<Task> finishedTasks = new ArrayList<>();
        List<Task> cancelledTasks = new ArrayList<>();
        List<Task> inProgressTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() == Status.ACTIVE) {
                activeTasks.add(task);
            }
            if (task.getStatus() == Status.FINISHED) {
                finishedTasks.add(task);
            }
            if (task.getStatus() == Status.CANCELLED) {
                cancelledTasks.add(task);
            }
            if (task.getStatus() == Status.IN_PROGRESS) {
                inProgressTasks.add(task);
            }
        }

        return new TasksByStatus(activeTasks, finishedTasks, inProgressTasks, cancelledTasks);
    }
}
