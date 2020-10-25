package com.joakrzem.todo.service;

import com.joakrzem.todo.model.Priority;
import com.joakrzem.todo.model.Status;
import com.joakrzem.todo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoServiceImplTest {

    private final static LocalDateTime END_DATE = LocalDateTime.of(2020, 10, 15, 9, 0);
    private final static String CATEGORY = "home";
    private final static String DESCRIPTION = "clean up mess";
    private final static String NAME = "cleaning up";
    private final static Priority PRIORITY = Priority.LOW;
    private final static int POINTS = 5;
    private final static int ID = 1;

    Task task;
    ToDoServiceImpl toDo;

    @BeforeEach
    void setUp() {
        task = new Task(END_DATE, CATEGORY, DESCRIPTION, NAME, PRIORITY, POINTS, ID);
        toDo = new ToDoServiceImpl();
        toDo.addTask(task);
    }

    @Test
    void addTask_ShouldGetCorrectTasksForDay() {

        //When
        List<Task> tasksForDay = toDo.tasksForDay(END_DATE);

        //Then
        assertEquals(List.of(task), tasksForDay);
    }

    @Test
    void tasksForDay_ShouldGetEmptyListWhenThereAreNoTasksForGivenDay() {

        //Given
        LocalDateTime differentDate = LocalDateTime.of(2020, 10, 20, 9, 0);

        //When
        List<Task> tasksForDay = toDo.tasksForDay(differentDate);

        //Then
        assertEquals(List.of(), tasksForDay);
    }

    @Test
    void finishTasks_ShouldMarkTaskAsFinishedAndCalculatePoints() {

        //When
        toDo.finishTask(ID);

        //Then
        assertEquals(List.of(task), toDo.tasksForDay(END_DATE));
        assertEquals(POINTS, toDo.collectedPoints());
        assertEquals(Status.FINISHED, toDo.getTask(ID).getStatus());
    }

    @Test
    void collectedPoints_ShouldReturnCollectedPoints() {

        //When
        int points = toDo.collectedPoints();

        //Then
        assertEquals(0, points);
    }

    @Test
    void modify_ShouldModifyTaskCorrectly() {

        //Given
        Task newTask = new Task(END_DATE, "work", "write report", "write", PRIORITY, 20, 2);

        //When
        toDo.modify(newTask, ID);

        //Then
        assertEquals(toDo.getTask(ID), newTask);
    }

    @Test
    void allTasks_ShouldReturnAllTasks() {

        //When
        List<Task> result = toDo.allTasks();

        //Then
        assertEquals(List.of(task), result);

    }
}