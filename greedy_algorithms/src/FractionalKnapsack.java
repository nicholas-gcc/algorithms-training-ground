import java.io.*;
import java.util.Scanner;
import java.util.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        //write your code here
        ArrayList<double[]> valuesList = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            valuesList.add(new double[]{values[i], weights[i], (double)values[i] / weights[i]});
        }
        Collections.sort(valuesList, new SortByUnitValue()); // sort by descending order

        int capacityLeft = capacity;
        double outputValue = 0;
        while (capacityLeft > 0 && !valuesList.isEmpty()) {
            double[] maxUnitValueObj = valuesList.get(0);
            double weightAdded = Math.min(capacityLeft, maxUnitValueObj[1]);
            capacityLeft -= weightAdded;
            outputValue += weightAdded * maxUnitValueObj[2];
            valuesList.remove(0);
        }

        return outputValue;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    static class SortByUnitValue implements Comparator<double[]> {
        @Override
        public int compare(double[] doubles, double[] other) {
            Double unitValue1 = doubles[2];
            Double unitValue2 = other[2];
            return unitValue1.compareTo(unitValue2) * -1;
        }
    }
} 
