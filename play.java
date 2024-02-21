package Project1;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class play {
    private int start_row;
    private int start_col;
    private int end_row, end_col;
    private int count_food, count_round = 0;

    private String input;

    List<List<Character>> maze = new ArrayList<>();
    //For List end_row,col 2 food

    play(int s_row, int s_col, List<List<Character>> maze, int food, int e_row, int e_col) {
        this.start_row = s_row;
        this.start_col = s_col;
        this.maze = maze;
        this.count_food = food;
        this.end_row = e_row;
        this.end_col = e_col;
    }

    public void play_game() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("User input");
            input = sc.nextLine();

            int row_2;
            int col_2;

            if (Objects.equals(input, "r")) {
                row_2 = start_row;
                col_2 = start_col + 1;
                swap_position(row_2, col_2);
            }
            if (Objects.equals(input, "l")) {
                row_2 = start_row;
                col_2 = start_col - 1;
                swap_position(row_2, col_2);
            }
            if (Objects.equals(input, "u")) {
                row_2 = start_row - 1;
                col_2 = start_col;
                swap_position(row_2, col_2);
            }
            if (Objects.equals(input, "d")) {
                row_2 = start_row + 1;
                col_2 = start_col;
                swap_position(row_2, col_2);
            }

            // To solve auto when have 1 food
            if (Objects.equals(input, "a") && count_round == 0 && count_food==1) {
                Deque<String> path = new ArrayDeque<>();
                if (findPath(start_row, start_col, end_row, end_col, path)) {
                    System.out.println("Path to find Food :) ->");
                    while (!path.isEmpty()) {
                        String poppedElement = path.removeLast();
                        System.out.println("Popped element: " + poppedElement);
                    }
                    Maze.printMaze(maze);
                    Project_1.main(null);
                } else {
                    System.out.println("No path to destination found.");
                    Project_1.main(null);
                }

            }else if(Objects.equals(input, "a") && count_round != 0 && count_food==1) {
                Deque<String> path = new ArrayDeque<>();
                if (findPath(start_row, start_col, end_row, end_col, path)) {
                    System.out.println("Path to find Food :) ->");
                    while (!path.isEmpty()) {
                        String poppedElement = path.removeLast();
                        System.out.println("Popped element: " + poppedElement);
                    }
                    Maze.printMaze(maze);
                    Project_1.main(null);
                } else {
                    System.out.println("No path to destination found.");
                    Project_1.main(null);
                }
            } else if (Objects.equals(input, "a") && count_round == 0 && count_food>1){
                Deque<String> path = new ArrayDeque<>();
                while(count_food!=0) {
                    for(int i = 0 ; i< maze.size() ; i++) {
                        for(int j=0 ; j<maze.get(i).size() ; j++){
                            if(maze.get(i).get(j)=='F') {
                                if (findPath(start_row, start_col, i, j, path)) {
                                    System.out.println("Path to find Food :) ->");
                                    while (!path.isEmpty()) {
                                        String poppedElement = path.removeLast();
                                        System.out.println("Popped element: " + poppedElement);
                                    }
                                    start_row=i;
                                    start_col=j;
                                    Maze.printMaze(maze);
//                                    path = null;
                                    count_food--;
                                    if(count_food==0){
                                        Project_1.main(null);
                                    }
                                } else {
                                    System.out.println("No path to destination found.");
                                }
                            }
                        }
                    }

                }
            }



        }
    }

    public void swap_position(int row_2, int col_2) {
        if (check(row_2, col_2)) {
            if (maze.get(row_2).get(col_2) != '0' && maze.get(row_2).get(col_2) != 'F') {
                char temp = maze.get(start_row).get(start_col);
                maze.get(start_row).set(start_col, maze.get(row_2).get(col_2));
                maze.get(row_2).set(col_2, temp);
                Maze.printMaze(maze);
                start_row = row_2;
                start_col = col_2;
            } else {
                System.out.println("Cannot move\n");
            }
            if (maze.get(row_2).get(col_2) == 'F') {
                maze.get(start_row).set(start_col, '1');
                maze.get(row_2).set(col_2, 'R');
                Maze.printMaze(maze);
                start_row = row_2;
                start_col = col_2;
                System.out.println("+++++ Find Food +++++");
                Maze.printMaze(maze);
                count_food--;
                if (count_food == 0) {
                    System.out.println("---------------------------------------");
                    Project_1.main(null);
                }
            }
        } else {
            System.out.println("Cannot move");
        }
    }

    public boolean check(int row, int col) {
        return row >= 0 && row < maze.size() && col >= 0 && col < maze.get(row).size();
    }
    public boolean findPath(int row, int col, int endRow, int endCol, Deque<String> path) {
        //When Find F
        if (row == endRow && col == endCol && maze.get(row).get(col)=='F' ) {
            maze.get(row).set(col, 'R');
            path.add("(" + row + "," + col + ")");
            return true;
        }
        //Condition to find food
        if ( check(row, col) && maze.get(row).get(col) != '0') {
            maze.get(row).set(col, '0'); // Mark the current cell as visited
            if(    findPath(row + 1, col, endRow, endCol, path) ||
                    findPath(row, col + 1, endRow, endCol, path) ||
                    findPath(row - 1, col, endRow, endCol, path) ||
                    findPath(row, col - 1, endRow, endCol, path)
               ){
                    maze.get(row).set(col, '1');
                    path.add("(" + row + "," + col + ")");
                    return true;

            }
            maze.get(row).set(col, '1'); // Unmark the current cell if the path is not found
        }

        return false;
    }


}
