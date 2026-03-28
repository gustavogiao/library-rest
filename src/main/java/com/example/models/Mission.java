package com.example.models;

import java.util.Date;

import com.example.enums.MissionStatus;

public class Mission {
    public int id;
    public String name;
    public MissionStatus status;
    public Date launchDate;
    public int crewSize;
}
