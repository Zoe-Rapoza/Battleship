
public class Ship {
	int row, col;
	boolean orientation; // true = vertical
	int size;
	int hit;
	String letter, name;
	boolean sunk = false;
	public boolean setUp = true;
	
	public Ship (int s, String n, String l) {
		size = s;
		name = n;
		letter = l;
	}
	
	public void placeShip(Shot loc, boolean or) {
		orientation = or;
		row = loc.getY();
		col = loc.getX();
	}
	
	public boolean checkHit(Shot loc) {
		//checking each spot to see if we hit.
		
		for(int c = 0; c < size; c++) {
			//deal with orientation
			if(orientation) {
				if(loc.equals(new Shot(col, (row+c)))) {
					// computer version of disposable fork
					if(!setUp) {
						hit++;
					}
					return true;
				}
			}else {
				if(loc.equals(new Shot((col+c), row))) {
					// computer version of disposable fork
					if(!setUp) {
						hit++;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isAlive() {
		if(hit == size) {
			return false;
		} else {
			return true;
		}
	}
	
	public String[][] renderShip(String[][] field) {
		for(int c = 0; c < size; c++) {
			//deal with orientation
			if(orientation) {
				field[row+c][col] = letter;
			}else {
				field[row][col+c] = letter; //space before letter?
			}
		}
		return field;
	}
}
