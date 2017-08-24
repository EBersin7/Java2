/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinders;

import graph.Graph;

public class Dijkstra implements PathFinder {

    //returns path as a sequence of node-indices.
    public int[] findPath(Graph g) {
        //get total # of nodes and create array for path indices
        int total = g.getNumberOfNodes();
        int[] path = new int[total];
        path[0] = 0;

        //get neighbors of the starting point
        int[][] neighbors = g.getNeighborsOfNode(0);

        for (int j = 1; j < total; j++) {
            //array for shortest length node
            int[] min = new int[2];
            min[0] = 1;
            min[1] = Integer.MAX_VALUE;

            //array for longest length node
            int[] max = new int[2];
            max[0] = 1;
            max[1] = Integer.MIN_VALUE;

            //array for a middle length node
            int[] mid = new int[2];
            mid[0] = 1;
            mid[1] = 1;

            //cycle through to find the min, max, and mid node indices
            for (int i = 0; i < neighbors.length; i++) {
                if (neighbors[i][1] < min[1]) {
                    if (min[1] != Integer.MAX_VALUE) {
                        mid[0] = min[0];
                        mid[1] = min[1];
                    }
                    min[0] = neighbors[i][0];
                    min[1] = neighbors[i][1];
                }
                if (neighbors[i][1] > max[1]) {
                    max[0] = neighbors[i][0];
                    max[1] = neighbors[i][1];
                }
            }

            //check that the min is not already in the path
            boolean foundMin = false;
            for (int i = 0; i < path.length; i++) {
                if (path[i] == min[0]) {
                    foundMin = true;
                }
            }

            //check that the mid is not already in the path
            boolean foundMid = false;
            for (int i = 0; i < path.length; i++) {
                if (path[i] == mid[0]) {
                    foundMid = true;
                }
            }

            //check that the max is not already in the path
            boolean foundMax = false;
            for (int i = 0; i < path.length; i++) {
                if (path[i] == max[0]) {
                    foundMax = true;
                }
            }

            //try to use min, then max, then mid, if all found then break
            if (!foundMin) {
                path[j] = min[0];
                neighbors = g.getNeighborsOfNode(min[0]);
            } else if (!foundMax) {
                path[j] = max[0];
                neighbors = g.getNeighborsOfNode(max[0]);
            } else if (!foundMin) {
                path[j] = mid[0];
                neighbors = g.getNeighborsOfNode(mid[0]);
            } else {
                break;
            }
        }
        return (path);
    }
}
