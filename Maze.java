package Project1_EGCO221;

import java.util.List;

public class Maze {
    public static void printMaze(List<List<Character>> maze) {
        for (List<Character> row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();  // Move to the next line after printing each row
        }
    }
}
