package maze;

import java.util.LinkedList;

public class PathFinder {

    Maze maze;
    String direction = "blank";

    public PathFinder(Maze iMaze) {
        maze = iMaze;
    }

    public LinkedList<Coordinate> findPath(int startRow, int startColumn) {
        LinkedList<Coordinate> myPath = new LinkedList<Coordinate>();

        tracePath(myPath, startRow, startColumn, direction);

        return (myPath);
    }

    public boolean tracePath(LinkedList myPath, int row, int column, String direction) {
        boolean exitFound = false;
        Coordinate co = new Coordinate(row, column);

        if (maze.isExit(row, column)) {
            myPath.add(co);
            exitFound = true;
            return exitFound;
        } else {
            myPath.add(co);
            if ((!maze.hasNorthWall(row, column)) && !(direction.equals("North"))) {
                exitFound = tracePath(myPath, row - 1, column, "South");
                if (exitFound) {
                    return exitFound;
                }
            }
            if ((!maze.hasSouthWall(row, column)) && !(direction.equals("South"))) {
                exitFound = tracePath(myPath, row + 1, column, "North");
                if (exitFound) {
                    return exitFound;
                }
            }
            if ((!maze.hasEastWall(row, column)) && !(direction.equals("East"))) {
                exitFound = tracePath(myPath, row, column + 1, "West");
                if (exitFound) {
                    return exitFound;
                }
            }
            if ((!maze.hasWestWall(row, column)) && !(direction.equals("West"))) {
                exitFound = tracePath(myPath, row, column - 1, "East");
                if (exitFound) {
                    return exitFound;
                }
            }

        }
        myPath.remove(co);
        return exitFound;
    }
}
