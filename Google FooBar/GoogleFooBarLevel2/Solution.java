import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Ideas:
 * HashMap to keep track of visited squares, use HashMap for lookups and do not add visited square to frontier
 * Breadth-first-search using Java queue
 * Track all moves from current position, filter to valid moves
 * Keep track of all positions as a pair of coordinates that can be converted to int of range [0, 63]
 */

public class Solution {

    public static int solution(int src, int dest) {
        if (src < 0 || dest < 0 || src > 63 || dest > 63) {
            return 0;
        }

        Queue<CoordinatePairs> nodesQueue = new LinkedList<>(); // keep track of node frontiers as a pair of coordinates
        HashMap<Integer, Boolean> isVisitedMap = new HashMap<>(); //keep track of visited nodes

        for (int i = 0; i < 64; i++) {
            isVisitedMap.put(i, false);
        }

        nodesQueue.add(convertToCoordinates(src));
        int numOfHops = 0;

        while (!nodesQueue.contains(convertToCoordinates(dest))) {
            numOfHops += 1;
            int currFrontierSize = nodesQueue.size();
            ArrayList<CoordinatePairs> newFrontier = new ArrayList<>();

            //breadth-first expansion of nodes in queue, adding child nodes to new frontier
            for (int i = 0; i < currFrontierSize; i++) {
                CoordinatePairs currNode = nodesQueue.poll();
                isVisitedMap.put(coordinatesToInt(currNode), true);
                newFrontier.addAll(getValidNodes(currNode));
            }

            newFrontier.removeIf(c -> isVisited(c, isVisitedMap));
            nodesQueue.addAll(newFrontier);
        }
        return numOfHops;
    }

    // get list of next nodes to add to frontier (arbitrary tie-breaking)
    public static ArrayList<CoordinatePairs> getValidNodes(CoordinatePairs coord) {
        ArrayList<CoordinatePairs> listNodes = new ArrayList<>();
        int currX = coord.getX();
        int currY = coord.getY();
        listNodes.add(new CoordinatePairs(currX + 2, currY + 1));
        listNodes.add(new CoordinatePairs(currX + 2, currY - 1));
        listNodes.add(new CoordinatePairs(currX + 1, currY + 2));
        listNodes.add(new CoordinatePairs(currX + 1, currY - 2));
        listNodes.add(new CoordinatePairs(currX - 1, currY + 2));
        listNodes.add(new CoordinatePairs(currX - 1, currY - 2));
        listNodes.add(new CoordinatePairs(currX - 2, currY + 1));
        listNodes.add(new CoordinatePairs(currX - 2, currY - 1));

        ArrayList<CoordinatePairs> listValidNodes = listNodes.stream()
                .filter(c -> c.getX() >= 0 && c.getX() < 8 && c.getY() >= 0 && c.getY() < 8)
                .collect(Collectors.toCollection(ArrayList::new));

        return listValidNodes;
    }

    public static Boolean isVisited(CoordinatePairs coord, HashMap<Integer, Boolean> visitedMap) {
        return visitedMap.get(coordinatesToInt(coord));
    }

    public static CoordinatePairs convertToCoordinates(int num) {
        int x = (int) Math.floor(num/8);
        int y = num % 8;
        return new CoordinatePairs(x, y);
    }

    public static int coordinatesToInt(CoordinatePairs coord) {
        return coord.getX() + coord.getY() * 8;
    }

    private static class CoordinatePairs {
        int x;
        int y;

        public CoordinatePairs(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        // nodesQueue can only work if CoordinatePair equality is defined as equality of x and y coordinate
        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CoordinatePairs)) {
                return false;
            }
            else return ((CoordinatePairs) o).getX() == this.getX() && ((CoordinatePairs) o).getY() == this.getY();
        }

    }

}
