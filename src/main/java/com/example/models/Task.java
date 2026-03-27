package com.example.models;

import java.util.Date;

import com.example.enums.TaskStatus;

public class Task {
    public int id;
    public String title;
    public String description;
    public TaskStatus status;
    public Date dueDate;
}
