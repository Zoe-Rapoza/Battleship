
import java.util.Random;
//In this section: 
//WHILE ALIVE = TRUE
//Randomizes turn between players 1 and 2
//Asks players where on the grid they would like to shoot
//Sees if it was already hit and tells them what they hit, if so sends them back to choose again
//if not yet hit, lets them shoot and tells them what it was
//stores the hit for future reference
import java.util.Scanner;

public class Battleship {
	
	static String[][] ocean = new String[10][10];
	static String[][] radar = new String [10][10];
	
	public static int turn; 
	static String letter = "";
	
	
	private static int getTurn(int a, int b) {
		Random rand = new Random();
		rand.ints(a, b);		
		return rand.nextInt();
		
	}
	
	//public static int car1;
	//public static int battle1;
	//public static int cru1;
	//public static int sub1;
	//public static int dest1;	
	
	//public static int car2;
	//public static int battle2;
	//public static int cru2;
	//public static int sub2;
	//public static int dest2;
	
	 
	 private static int getCol(String letter, int col) {
		 boolean valid = false;
		 
		//ask which col to hit, store as int #1-10 as 'letter'
		 Scanner shoot = new Scanner(System.in);
			shoot = new Scanner(System.in);
			while( valid = false) {
			try {
				System.out.println("Which column would you like to hit? (Letters A-J)");
				letter = shoot.toString();
			}catch(Exception e) {
				System.err.print(e);}
			
		 if (letter == "A" || letter == "a"){
				col = 1;}
			if (letter == "B" || letter == "b"){
				col = 2;}
			if (letter == "C" || letter == "c"){
				col = 3;}
			if (letter == "D" || letter == "d"){
				col = 4;}
			if (letter == "E" || letter == "e"){
				col = 5;}
			if (letter == "F" || letter == "f"){
				col = 6;}
			if (letter == "G" || letter == "g"){
				col = 7;}
			if (letter == "H" || letter == "h"){
				col = 8;}
			if (letter == "I" || letter == "i"){
				col = 9;}
			if (letter == "J" || letter == "j"){
				col = 10;}
			else {
			System.out.println("That is not in the range. Pick again.");
			} 
				
			valid = true;
			}
		 return col;}
	 

	private static void hit(String board[][], int r, int c, int turn) { //10 for hit and ones place tells which ship
		 if (board[c][r] == "A") { 
				System.out.println ("Hit the Carrier (5 spaces)!");		
				board[c][r] = "H";
				if (turn ==1) {
				Ships.car.hit++;}
				if (turn ==2) {
					Ships.carrier.hit++;}
			}else if(board[c][r] == "B") {
				System.out.println ("Hit the Battleship (4 spaces)!");
				board[c][r] = "H";
				if (turn ==1) {
					Ships.bat.hit++;}
				if (turn ==2) {
					Ships.battle.hit++;}
			}else if(board[c][r] == "C") {
				System.out.println ("Hit the Cruiser (3 spaces)!");
				board[c][r] = "H";				
				if (turn ==1) {
					Ships.cru.hit++;}
				if (turn ==2) {
					Ships.cruiser.hit++;}
			}else if(board[c][r] == "S") {
				System.out.println ("Hit the Submarine (3 spaces)!");
				board[c][r] = "H";
				if (turn ==1) {
					Ships.sub.hit++;}
				if (turn ==2) {
					Ships.submarine.hit++;}
			}else if(board[c][r] == "D") {
				System.out.println ("Hit the Destroyer (2 spaces)!");
				board[c][r] = "H";
				if (turn ==1) {
					Ships.dest.hit++;}
				if (turn ==2) {
					Ships.destroyer.hit++;}
			}else {
				System.out.println("Miss.");
				board[c][r] = "M" ;
			}
	 }
	 
	 private static void ifPrevHit(int column, int row, boolean empty, String[][] board) {
		 if (board[column][row] == "M" || radar[column][row] == "H") {
				System.out.println("You already attacked " + letter+row + ".");
				if (board[column][row] == "H") { 
					System.out.println (letter+row + " held an enemy ship.");	
					
				}else if (board[column][row] == "M"){
					System.out.println("You had missed.");
				}
				empty = false;
				
			}else {
				empty = true;
			}
	 }
	 
	 private static boolean stillAlive(int turn, boolean alive) {
		 if (Ships.car.hit == Ships.car.size && Ships.bat.hit == Ships.bat.size && Ships.cru.hit ==Ships.cru.size&& Ships.sub.hit==Ships.sub.size && Ships.dest.hit==Ships.dest.size) {
				alive = false;
				System.out.println("Player 1 is dead!");
				System.out.println("Congratulations to player 2!");
				System.exit(0);
		 } if (Ships.carrier.hit == Ships.carrier.size && Ships.battle.hit == Ships.battle.size && Ships.cruiser.hit ==Ships.cruiser.size&& Ships.submarine.hit==Ships.submarine.size && Ships.destroyer.hit==Ships.destroyer.size) {
				alive = false;
				System.out.println("Player 2 is dead!");
				System.out.println("Congratulations to player 1!");
				System.exit(0);
		 }
				else {
					alive = true;	
				}

		 return alive;
	 }


	public static void main(String[] args) {
		ocean = Ships.oceanSetup(); //kaitlin's branch should populate array
		radar = Ships.radarSetup(); //kaitlin's branch should populate array
		boolean alive = true;
		boolean empty = false;
		
		
		turn = getTurn(1, 2); //get turn (1 for p1 2 for p2)
		
		int column = 0;
		int row =0;
		
		while (alive = true) {
			
			
			Scanner shoot = new Scanner(System.in);
			empty = false;
				
			while (empty = false) {
			//ask which num they want to hit, store as 'row' int

					
					
				shoot = new Scanner(System.in);
			try {
				System.out.println("Which row would you like to hit? (#s 1-10)");
				row = shoot.nextInt();
			}catch(Exception e) {
				System.err.print(e);}
				
			if(row <1 && row>10) {
				System.out.println("That is not in the range.");
				System.exit(0);
			}
				
			column = getCol(letter,column);
				
				
				
			}//while empty = false   meaning: while stuck in loop of already hit 
				
				if (turn ==1) {
				ifPrevHit(column,row,empty,radar);
				}
				if (turn ==2) {
					ifPrevHit(column,row,empty,ocean);
			}
			
				
				System.out.println("You attack " + letter+row + ".");
				
				if (turn ==1) {
					hit(radar, row, column, turn);
					}
					if (turn ==2) {
						hit(ocean, row, column, turn);
				}
					
			alive = stillAlive(turn, alive);
			
				
				if (turn ==1) {
					turn =2;
				}else
					turn = 1;
				
		
				
			}//while alive = true
		
	}//end main func

	
		
		
		
		
		

} //end class

