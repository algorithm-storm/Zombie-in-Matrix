import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
     * @param grid: a 2D integer grid
     * @return: an integer
     */

    static public void main(String []args){
        Solution test = new Solution();
        int grid [][] = new int [][]{
                {0, 1, 2, 0, 0},
                {1, 0, 0, 2, 1},
                {0, 1, 0, 0, 0},
        };

        System.out.println(test.zombie(grid));

    }

    final int PEOPLE = 0;
    final int ZOMBIE = 1;
    final int WALL = 2;
    int people;

    class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    public int zombie(int[][] grid) {

        if(grid == null || grid.length == 0 || grid[0].length == 0 ){
            return -1;
        }

        Queue<Coordinate> queue = findZombie(grid);
        return infect(grid,queue);
    }


    public Queue<Coordinate> findZombie(int[][] grid){

        Queue<Coordinate> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j <grid[0].length; j++){
                if(grid[i][j] == ZOMBIE){
                    queue.offer(new Coordinate(i,j));
                }
                if(grid[i][j] == PEOPLE){
                    people++;
                }
            }
        }
        return queue;
    }

    public int infect(int[][] grid , Queue<Coordinate> queue){

        int days = 0;
        int [] directX = {-1,0,1,0};
        int [] directY = {0,1,0,-1};
        while (!queue.isEmpty()){
            days++;
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                Coordinate start = queue.poll();
                for(int j = 0 ; j < 4 ; j++){
                    int x = start.x + directX[j];
                    int y = start.y + directY[j];
                    if(isBound(grid,x,y)){
                        continue;
                    }
                    if(grid[x][y] == PEOPLE){
                        grid[x][y] = ZOMBIE;
                        queue.offer(new Coordinate(x,y));
                        people--;
                    }
                    if(people == 0){
                        return days;
                    }
                }
            }
        }
        return -1;
    }

    public boolean isBound(int[][] grid, int x , int y){
        if(x < 0 || x >= grid.length){
            return true;
        }
        if(y < 0 || y >= grid[0].length){
            return true;
        }
        return grid[x][y] == WALL ;
    }



}