package com.german.todoapp.models;

public class Task {

    private String title;
    private String description;
    private boolean finished;
    private String assignedTo;

    public Task() { }

    public Task(String title, String description, boolean finished, String assignedTo) {
        this.title = title;
        this.description = description;
        this.finished = finished;
        this.assignedTo = assignedTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
