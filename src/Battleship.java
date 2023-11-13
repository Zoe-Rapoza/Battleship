import java.util.Random;
import java.util.Scanner;

public class Battleship {
	
	private static int[][] ocean; //p1's ocean AND p2's radar
	private static int[][] radar; //opposite of ocean ^
	
	
	private static int getTurn(int a, int b) {
		Random rand = new Random();
		rand.ints(a, b);		
		return rand.nextInt();
		
	}
		
	 private static String ship(int shipNum){
		String Ship = "";
		
		 return Ship;
		
	 }
	 
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
		 return col;
	 }


	public static void main(String[] args) {
		ocean = Ships.shipSetup(); //kaitlin's branch should populate array
		int player1 =1;
		int player2 =2;
		int turn;
		boolean alive = true;
		turn = getTurn(player1, player2); //get turn (1 for p1 2 for p2)
		
		int column = 0;
		int row =0;
		
		while (alive = true) {
			
			
			Scanner shoot = new Scanner(System.in);
			if (turn ==1) { //IF IT'S PLAYER 1'S TURN
				
				
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
				column = stringtoint(letter);
				
				
				System.out.println("You attack " + letter+row + ".");
				
				
				//if LetNum doesn't contain a miss (0) then:
				if (radar[column][row] != 0) { 
					System.out.println ("Hit the ___!");				
				}else {
						System.out.println("Miss.");
					}
				
				//check if ship populates that slot
				//return miss or hit, if hit, tell which ship
				//*****STORE WHERE HAS ALREADY SHOT TO PREVENT REPEATS
				//switch turn & repeat
					
				}
				
				
				
			}
		}

	
		
		
		
		
		
		
		
		
		
		

	}

