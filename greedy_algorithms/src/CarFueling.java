import java.util.*;
import java.io.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        if (dist <= tank) {
            return 0;
        }

        int[] stopsArray = new int[stops.length + 2];
        stopsArray[0] = 0;
        stopsArray[stops.length + 1] = dist;
        for (int i = 1; i < stopsArray.length - 1; i++) {
            stopsArray[i] = stops[i - 1];
        }

        int currDist = 0;
        int numStops = 0;
        int currTank = tank;
        int currStop = 0;
        while (currDist < dist) {
            
        }

        return numStops;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
