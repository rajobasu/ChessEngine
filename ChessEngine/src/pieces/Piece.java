package pieces;

import frameWork.Location;
import id.PieceColor;
import id.PieceID;

public abstract class Piece implements Movable{
	private PieceID ID;
    protected Location location;
	
    private PieceColor color;
    protected int moves;
	protected boolean hasBeenCaptured=false;
    
	
	public Piece(PieceID iD,PieceColor color) {
		super();
		ID = iD;
		this.color=color;
		moves=0;
	}

	public PieceID getID(){
    	return ID;
    }

    public PieceColor getColor() {
		return color;
	}

    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
	public String toString()
	{
		return ID.name()+" :: "+location;
	}
	public void moved(){
		moves++;
		
	}
	public void unmoved(){
		
		moves--;
	}
	public boolean hasMoved(){
		return moves!=0;
	}
	public boolean hasbeenCaptured(){
		return hasBeenCaptured;
	}
	public void captured(){
		hasBeenCaptured=true;
	}
	public void unCaptured(){
		hasBeenCaptured=false;
		
	}
	public boolean equals(Piece p){
		if(this.getLocation().equals(p.getLocation()))return true;
		return false;
	}
	public int getMoves(){
		return moves;
	}

}
