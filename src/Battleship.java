
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
	
	private static int[][] ocean; //p1's ocean AND p2's radar
	private static int[][] radar; //opposite of ocean ^
	
	
	private static int getTurn(int a, int b) {
		Random rand = new Random();
		rand.ints(a, b);		
		return rand.nextInt();
		
	}
	
	public static int car1;
	public static int battle1;
	public static int cru1;
	public static int sub1;
	public static int dest1;	
	
	public static int car2;
	public static int battle2;
	public static int cru2;
	public static int sub2;
	public static int dest2;
	
	 
	 private static int stringtoint(String letter) {
		 int col=0;
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
		if(col <1 && col>10) {
			System.out.println("That is not in the range.");
			System.exit(0);}
		 return col;
	 }
	 
	 private static void hit(int board[][], int r, int c, int turn) { //10 for hit and ones place tells which ship
		 if (board[c][r] == 5) { 
				System.out.println ("Hit the Carrier (5 spaces)!");		
				board[c][r] = 15;
				if (turn ==1) {
				car1=car1+1;}
				if (turn ==2) {
					car2=car2+1;}
			}else if(board[c][r] == 4) {
				System.out.println ("Hit the Battleship (4 spaces)!");
				board[c][r] = 14;
				if (turn ==1) {
					battle1 = battle1+1;}
				if (turn ==2) {
					battle2 = battle2+1;}
			}else if(board[c][r] == 3) {
				System.out.println ("Hit the Cruiser (3 spaces)!");
				board[c][r] = 13;
				if (turn ==1) {
					cru1 = cru1+1;}
				if (turn ==2) {
					cru2 = cru2+1;}
			}else if(board[c][r] == 2) {
				System.out.println ("Hit the Submarine (3 spaces)!");
				board[c][r] = 12;
				if (turn ==1) {
					sub1 =sub1+1;}
				if (turn ==2) {
					sub2 =sub2+1;}
			}else if(board[c][r] == 1) {
				System.out.println ("Hit the Destroyer (2 spaces)!");
				board[c][r] = 11;
				if (turn ==1) {
					dest1=dest1+1;}
				if (turn ==2) {
					dest2=dest2+1;}
			}else {
				System.out.println("Miss.");
				board[c][r] = 10; // 10 for miss
			}
	 }


	public static void main(String[] args) {
		ocean = Ships.oceanSetup(); //kaitlin's branch should populate array
		radar = Ships.radarSetup(); //kaitlin's branch should populate array
		int turn;
		boolean alive = true;
		boolean empty = false;
		
		
		turn = getTurn(1, 2); //get turn (1 for p1 2 for p2)
		
		int column = 0;
		int row =0;
		
		while (alive = true) {
			
			
			Scanner shoot = new Scanner(System.in);
			while (turn ==1) { //IF IT'S PLAYER 1'S TURN
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
				
				//ask which col to hit, store as int #1-10 as 'letter'
				String letter = "";
				shoot = new Scanner(System.in);
				try {
					System.out.println("Which column would you like to hit? (Letters A-J)");
					letter = shoot.toString();
				}catch(Exception e) {
					System.err.print(e);}
				column = stringtoint(letter); //making column into int 1-10
				
				if (radar[column][row] >9 &&radar[column][row] <16) {
					System.out.println("You already attacked " + letter+row + ".");
					if (radar[column][row] == 15) { 
						System.out.println ("You had hit the Carrier (5 spaces).");	
						
					}else if (radar[column][row] == 14) {
						System.out.println ("You had hit the Battleship (4 spaces)!");
						
					}else if(radar[column][row] == 13) {
						System.out.println ("You had hit the Cruiser (3 spaces)!");
						
					}else if(radar[column][row] == 12) {
						System.out.println ("You had hit the Submarine (3 spaces)!");
						
					}else if(radar[column][row] == 11) {
						System.out.println ("You had hit the Destroyer (2 spaces)!");
						
					}else if(radar[column][row] == 10){
						System.out.println("You had missed.");
					}
					empty = false;
					
				}else {
					empty = true;
				}
				System.out.println("You attack " + letter+row + "."); }
				
				hit(radar, row, column, turn);
				
				turn = 2;
				
				}
				
			while (turn ==2) { //IF IT'S PLAYER 2'S TURN
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
				
				//ask which col to hit, store as int #1-10 as 'letter'
				String letter = "";
				shoot = new Scanner(System.in);
				try {
					System.out.println("Which column would you like to hit? (Letters A-J)");
					letter = shoot.toString();
				}catch(Exception e) {
					System.err.print(e);}
				column = stringtoint(letter); //making column into int 1-10
				
				if (ocean[column][row] >9 &&ocean[column][row] <16) {
					System.out.println("You already attacked " + letter+row + ".");
					if (ocean[column][row] == 15) { 
						System.out.println ("You had hit the Carrier (5 spaces).");	
						
					}else if (ocean[column][row] == 14) {
						System.out.println ("You had hit the Battleship (4 spaces)!");
						
					}else if(ocean[column][row] == 13) {
						System.out.println ("You had hit the Cruiser (3 spaces)!");
						
					}else if(ocean[column][row] == 12) {
						System.out.println ("You had hit the Submarine (3 spaces)!");
						
					}else if(ocean[column][row] == 11) {
						System.out.println ("You had hit the Destroyer (2 spaces)!");
						
					}else if(ocean[column][row] == 10){
						System.out.println("You had missed.");
					}
					empty = false;
					
				}else {
					empty = true;
				}
				System.out.println("You attack " + letter+row + "."); }
				
				hit(ocean, row, column,turn);
				
				turn = 1;
				
				}//while turn =2
				
			}//while alive = true
		
	}//end main func

	
		
		
		
		
		

} //end class

