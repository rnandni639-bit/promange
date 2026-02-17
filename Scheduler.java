package com.promanage.service;

import com.promanage.model.Project;
import java.util.*;

public class Scheduler {

    public static Map<Integer, Project> scheduleProjects(List<Project> projects) {
        projects.sort((a, b) -> Double.compare(b.getRevenue(), a.getRevenue()));
        Project[] week = new Project[5];

        for (Project project : projects) {
            int deadline = project.getDeadline();
            for (int day = Math.min(deadline, 5) - 1; day >= 0; day--) {
                if (week[day] == null) {
                    week[day] = project;
                    break;
                }
            }
        }

        Map<Integer, Project> schedule = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            if (week[i] != null) {
                schedule.put(i + 1, week[i]);
            }
        }
        return schedule;
    }
}
