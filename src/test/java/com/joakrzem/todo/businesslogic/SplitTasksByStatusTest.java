package com.joakrzem.todo.businesslogic;

import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import com.joakrzem.todo.model.TasksByStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitTasksByStatusTest {

    Task task1;
    Task task2;
    Task task3;
    Task task4;

    List<Task> tasks;

    SplitTasksByStatus splitTasksByStatus;

    @BeforeEach
    void addTasks() {
        splitTasksByStatus = new SplitTasksByStatus();

        task1 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "work",
                "write raport", "write raport", Priority.HIGH, 20, 1);

        task2 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "work",
                "write raport", "write raport", Priority.LOW, 20, 2);

        task3 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "work",
                "write raport", "write raport", Priority.MEDIUM, 20, 3);

        task4 = new Task(LocalDateTime.of(2020, 10, 15, 9, 0), "work",
                "write raport", "write raport", Priority.LOW, 20, 4);

        tasks = List.of(task1, task2, task3, task4);
    }

    @Test
    void split_ShouldHaveFourTasksInListOfActiveTasks() {
        //When
        TasksByStatus result = splitTasksByStatus.split(tasks);

        //Then
        assertEquals(4, result.getActiveTasks().size());
    }

    @Test
    void split_ShouldHaveThreeTasksInListOfFinishedTasks() {
        //Given
        task1.setStatus(Status.FINISHED);
        task2.setStatus(Status.FINISHED);
        task3.setStatus(Status.FINISHED);

        //When
        TasksByStatus result = splitTasksByStatus.split(tasks);

        //Then
        assertEquals(3, result.getFinishedTasks().size());
    }

    @Test
    void split_ShouldHaveTwoTasksInListOfInProgressTasks() {
        //Given
        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.IN_PROGRESS);

        //When
        TasksByStatus inProgressTasks = splitTasksByStatus.split(tasks);

        //Then
        assertEquals(2, inProgressTasks.getInProgressTasks().size());
    }

    @Test
    void split_ShouldHaveOneTaskInListOfCancelledTasks() {
        //Given
        task1.setStatus(Status.CANCELLED);
        task2.setStatus(Status.CANCELLED);

        //When
        TasksByStatus result = splitTasksByStatus.split(tasks);

        //Then
        assertEquals(2, result.getCancelledTasks().size());
    }

    @Test
    void split_ShouldHaveFewTasksDifferentStatus() {
        //Given
        task1.setStatus(Status.FINISHED);
        task3.setStatus(Status.CANCELLED);
        task4.setStatus(Status.IN_PROGRESS);

        //When
        TasksByStatus result = splitTasksByStatus.split(tasks);

        //Then
        assertEquals(1, result.getFinishedTasks().size());
        assertEquals(1, result.getActiveTasks().size());
        assertEquals(1, result.getCancelledTasks().size());
        assertEquals(1, result.getInProgressTasks().size());
    }
}
