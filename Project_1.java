package Project1;
import java.util.*;
import java.io.*;

//maize_3.txt

public class Project_1{
    public static void main(String[] args) {
        //Set var
        int count =0;
        Scanner sc = new Scanner(System.in);
        boolean opensuccess = false;
        String map;
        String path = "src/main/java/Project1/";
        System.out.println("New file name = ");
        map = sc.nextLine();
//        map = "maize_3.txt";
        String file_name = path+map;
        int [][] start ;
        int count_row=0,count_food=0;
        char temp ;
        int start_row=0,start_col=0;
        int end_row=0, end_col=0;


        List<Integer> end_row_2f = new ArrayList<>();
        List<Integer> end_col_2f = new ArrayList<>();


        List<List<Character>> maze = new ArrayList<>();

//        Maze m = new Maze();

        while(!opensuccess) {
            try{
                File file = new File(file_name);
                Scanner filescan = new Scanner(file);
                opensuccess = true;
                System.out.print( "\n");
                while(filescan.hasNext()){
                    List<Character> row = new ArrayList<>();


                    String line = filescan.nextLine();
                    String[] col = line.split(",");
                    count = col.length;
                    for(int i=0;i<count;i++){
                       temp = col[i].trim().charAt(0);
                        if(temp=='R'){
                            start_col = i;
                            start_row = count_row;
                        }
                        if(temp=='F'){
                            // end_row,end_col;
                            end_col = i;
                            end_row = count_row;
//                            end_row_2f[i]=count_row;
//                            end_col_2f[0]=i;
//                            end_col_2f[1]=i;
//                            end_col_2f.add(i);
//                            end_row_2f.add(count_row);
                            count_food++;
                        }
                        row.add(temp);
                    }

                    maze.add(row);
                    count_row++;

                }
                //print Maze
                Maze.printMaze(maze);

                // to find position of F
//                for(int i = 0 ; i< maze.size() ; i++) {
//                    for(int j=0 ; j<maze.get(i).size() ; j++){
//                        if(maze.get(i).get(j)=='F') {
//                            System.out.print(i);
//                            System.out.print(j);
//                            System.out.println();
//                        }
//                    }
//                }
//                System.out.print(count_food);
                play play_auto  = new play(start_row, start_col, maze,count_food, end_row, end_col );
                play_auto .play_game();



                filescan.close();
            }catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.print("New file name  = ");
                map = sc.nextLine();
                file_name = path + map;
            }

            //System.out.print
            //System.out.print
        }

    }
}
