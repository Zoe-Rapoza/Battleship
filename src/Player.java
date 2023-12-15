import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	String[][] ocean = new String[10][10];
	String[][] radar = new String[10][10];
	// ClassName<dType> ourName = new ClassName<dType>();
	ArrayList<Ship> deployedShips = new ArrayList<Ship>();
	ArrayList<Shot> myShots = new ArrayList<Shot>();
	ArrayList<Shot> opShots = new ArrayList<Shot>();
	Player opponent;
	
	public Player() {
		init();
		deployShips();
	}
	
	private void deployShips() {
		// creates ships and places them in dry dock.
		ArrayList<Ship> dryDock = new ArrayList<Ship>();
		Ship ac = new Ship(5, "AircraftCarrier", "A");
		Ship battle = new Ship(4, "BattleShip", "B");
		Ship cruiser = new Ship(3, "Cruiser", "C");
		Ship sub = new Ship(3, "Submarine", "S");
		Ship des = new Ship(2, "Destroyer", "D");
		dryDock.add(ac);
		dryDock.add(battle);
		dryDock.add(cruiser);
		dryDock.add(sub);
		dryDock.add(des);
		// loop through dry dock and place each ship
		
		for(Ship s : dryDock) {
			renderOcean();
			displayBoard(ocean);
			boolean validate = true;
			while(validate) {
				Shot loc = getLocation("Enter the placement for " + s.name);
				//get orientation
				boolean ori = getOrientation();
				//check for ship in bounds
				validate = checkMapBounds(s, ori, loc);
				// check for collisions
				if(validate) {
					validate = checkCollisions(s, ori, loc);
					if(validate) {
						System.out.println(s.name+" has been placed.");
						s.placeShip(loc, ori);
						deployedShips.add(s);
						validate = false;
					}else {
						System.out.println("This ship will colide with another already placed.");
						validate = true;
					}
				}else {
					System.out.println("The ship will not fit in this location, try again.");
					validate = true;
				}
			}
		}
		//We are done with setup. Change state to play
		for(Ship s : deployedShips) {
			s.setUp = false;
		}
	}
	
	

	private boolean getOrientation() {
		Scanner scan = new Scanner(System.in);
		System.out.println("[0] Vertical \n[1] Horizontal");
		int response = scan.nextInt();
		return response % 2 == 0;
	}
	
	private boolean checkMapBounds(Ship s, boolean ori, Shot loc) {
		if(ori) {
			//vertical
			int endPoint = loc.getY()+s.size - 1;
			return endPoint < 10;
		}else {
			//horizontal
			int endPoint = loc.getX()+s.size - 1;
			return endPoint < 10;
		}
	}

	private boolean checkCollisions(Ship s, boolean ori, Shot loc) {
		// if deployed ships is empty, return true
		if(deployedShips.isEmpty()) {
			return true;
		} else {
			for(Ship a : deployedShips) {
				if(ori) { //vertical
					for(int b = loc.getY();b < loc.getY()+s.size; b++) { //check rows
						if(a.checkHit(new Shot(loc.getX(),b))) { //asking ships
							return false;
						}
					}
				} else {
					for(int b = loc.getX();b < loc.getX()+s.size; b++) { //check columns
						if(a.checkHit(new Shot(loc.getY(),b))) { //asking ships
							return false;
						}
					}
				}
			} 
		}
		//if we have ships deployed, then loop through the possible locations to see if we hit
		return true;
	}

	private Shot getLocation(String text) {
		String choice = null;
		Scanner input = new Scanner(System.in);
		System.out.println(text);
		try {
			choice = input.nextLine();
			// String.charAt(#) and String.subString(#) both take numbers
			// charAt finds location, subString chops and takes the right
		}catch(Exception e) {
			e.printStackTrace();
		}
		char letter = choice.charAt(0);
		int row = (int)(letter - 'A');
		int col = -1;
		try {
			col = Integer.parseInt(choice.substring(1))-1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		Shot loc = new Shot(col, row);
		return loc;
	}
	
	public boolean checkGuess(Shot opfor) {
		for(Ship s : deployedShips) {
			if(s.checkHit(opfor)) {
				opfor.resolve(true);
				opShots.add(opfor);
				if(!s.isAlive()) {
					System.out.println(s.name+" is sunk!");
				}else {
					System.out.println(s.name+ " is hit!");
				}
				return true;
			}
		}
		System.out.println("Shot missed.");
		opfor.resolve(false);
		opShots.add(opfor);
		return false;
	}
	
	public void playTurn () {
		// display UI
		renderRadar();
		renderOcean();
		displayBoard(radar);
		displayBoard(ocean);
		// ask for shot
		Shot s = getLocation("Enter your guess: ");
		
		// check the shot
		s.resolve(opponent.checkGuess(s));
		myShots.add(s);
		// display results
		renderRadar();
		renderOcean();
		displayBoard(radar);
		displayBoard(ocean);
	}
	
	public boolean stillAlive() {
		for(Ship s : deployedShips) {
			if(s.isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public void setOpfor(Player P) {
		opponent = P;
	}
	
	private void init() {
		initBoard(radar);
		initBoard(ocean);
	}
	
	private void initBoard(String[][] board) {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				board[y][x] = " ";
			}
		}
	}
	
	private void renderRadar() {
		// for each loop
		// for(dType yourName : collectionName){}
		for(Shot s : myShots) {
			s.display(radar);
		}
		
	}
	
	private void renderOcean() {
		for(Ship s : deployedShips) {
			s.renderShip(ocean);
		}
		for(Shot s : opShots) {
			s.display(ocean);
		}
	}
	
	private void displayBoard(String[][] board) {
		// display this board
		char letter = 'A';
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for (int y = 0; y < 10; y++) {
			System.out.print((char)((short)letter+y));
			// short is an integer
			
			for(int x = 0; x < 10; x++) {
				System.out.print(" " + board[y][x]);
			}
			System.out.print("\n");
		}
	}
}

