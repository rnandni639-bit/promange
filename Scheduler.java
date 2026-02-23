package scheduler;

import model.Project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyScheduler {

    public List<Project> scheduleProjects(List<Project> projects) {

        // Step 1: Sort by predicted revenue (highest first)
        projects.sort((p1, p2) ->
                Double.compare(p2.getPredictedRevenue(), p1.getPredictedRevenue())
        );

        List<Project> scheduled = new ArrayList<>();
        boolean[] slots = new boolean[5]; // 5 days (Mon-Fri)

        for (Project project : projects) {

            // Try to schedule before deadline
            for (int d = Math.min(5, project.getDeadline()) - 1; d >= 0; d--) {

                if (!slots[d]) {
                    slots[d] = true;
                    scheduled.add(project);
                    break;
                }
            }
        }

        return scheduled;
    }
}
