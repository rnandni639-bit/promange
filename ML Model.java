package ml;

import java.util.List;

public class MLModel {

    // Weighted Moving Average Prediction
    public double predictRevenue(List<Double> pastRevenues) {

        if (pastRevenues == null || pastRevenues.size() == 0) {
            return 0;
        }

        int n = pastRevenues.size();
        double weightedSum = 0;
        double totalWeight = 0;

        // Weight increases with recency
        for (int i = 0; i < n; i++) {
            int weight = i + 1;   // 1,2,3,4...
            weightedSum += pastRevenues.get(i) * weight;
            totalWeight += weight;
        }

        return weightedSum / totalWeight;
    }
}
