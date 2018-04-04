package frameWork;

import pieces.Piece;

public class Spot  {

	private Location location;
	private Piece piece;
	private boolean occupied;

	public Spot(Location location) {
		this.location = location;
	}

	

	public boolean isOccupied() {
		return occupied;
	}

	public void vacate() {
		
		piece.setLocation(null);
		piece = null;
		occupied = false;
	
	
	}

	public void occupy(Piece piece) {
		if (piece != null) {
			this.piece = piece;
			occupied = true;
			piece.setLocation(location);
		}
	}

	public Piece getOccupant() {
		return piece;
	}

	public String toString() {
		if (piece != null) {
			return piece.toString().substring(0,1);
		} else {
			return "N";
		}
	}

	public Location getLocation() {
		return location;
	}

	
}
