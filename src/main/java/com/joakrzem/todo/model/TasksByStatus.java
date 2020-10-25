package com.joakrzem.todo.model;

import java.util.List;
import java.util.Objects;

public class TasksByStatus {

    private List<Task> activeTasks;
    private List<Task> finishedTasks;
    private List<Task> inProgressTasks;
    private List<Task> cancelledTasks;

    public TasksByStatus(List<Task> activeTasks, List<Task> finishedTasks, List<Task> inProgressTasks, List<Task> cancelledTasks) {
        this.activeTasks = activeTasks;
        this.finishedTasks = finishedTasks;
        this.inProgressTasks = inProgressTasks;
        this.cancelledTasks = cancelledTasks;
    }

    public List<Task> getActiveTasks() {
        return activeTasks;
    }

    public List<Task> getFinishedTasks() {
        return finishedTasks;
    }

    public List<Task> getInProgressTasks() {
        return inProgressTasks;
    }

    public List<Task> getCancelledTasks() {
        return cancelledTasks;
    }

    public void setActiveTasks(List<Task> activeTasks) {
        this.activeTasks = activeTasks;
    }

    public void setFinishedTasks(List<Task> finishedTasks) {
        this.finishedTasks = finishedTasks;
    }

    public void setInProgressTasks(List<Task> inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public void setCancelledTasks(List<Task> cancelledTasks) {
        this.cancelledTasks = cancelledTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksByStatus that = (TasksByStatus) o;
        return Objects.equals(activeTasks, that.activeTasks) &&
                Objects.equals(finishedTasks, that.finishedTasks) &&
                Objects.equals(inProgressTasks, that.inProgressTasks) &&
                Objects.equals(cancelledTasks, that.cancelledTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeTasks, finishedTasks, inProgressTasks, cancelledTasks);
    }
}
