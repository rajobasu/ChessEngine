package frameWork;

import id.SquareNames;
import pieces.Copyable;

public class Location implements Copyable<Location>{

	private int row;
	private int col;
	

	public Location(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public Location(int location){
		this(location/8,location%8);
	}
	
	private Location(Location location){
		this.row=location.getRow();
		this.col=location.getCol();
	}
	
	
	
	public int getRow() {
		return row;
	}
	public int getCol() {
	    return col;
	}
	public String toString(){
		//return row+" "+col;
		return SquareNames.getInstance().getSquareName(this);
	}
	public Location copy(){
		return new Location(this);
	}
	public boolean equals(Location to){
		if(to==null)return false;
		if((to.getCol()==this.getCol())&&(to.getRow()==this.getRow())){return true;}
		return false;
	}
	public int NumberForm(){
		return row*8+col;
	}
}
