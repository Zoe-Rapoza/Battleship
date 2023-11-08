import java.util.*;
public class Battleship {
	public static int numRows = 10;
    public static int numColumns = 10;
    public static int player1Ships;
    public static int player2Ships;
    public static String[][] grid = new String[numRows][numColumns];
    public static int[][] missedGuesses = new int[numRows][numColumns];

    public static void main(String[] args){
    	createOceanMap();
    }
    
    public static void createOceanMap() {
    	// First section of ocean map
    	System.out.print("  ");
    	for(int i = 0; i < grid.length; i++)
    		System.out.print(i);
    	System.out.println();
    	
    	// Middle section of ocean map
    	for(int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			grid[i][j] = " ";
    			if (j == 0)
    				System.out.print(i+ "|" + grid[i][j]);
    			else if (j == grid[i].length - 1)
    				System.out.print(grid[i][j] + "|" + i);
    			else
    				System.out.print(grid[i][j]);
    		}
    	System.out.println();
    	}
    	
    	// Last section of ocean map
    	System.out.print("  ");
    	for(int i = 0; i < numColumns; i++)
    		System.out.print(i);
    	System.out.println();
    }
    
    
}
