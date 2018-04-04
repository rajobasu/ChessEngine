package id;

public enum PieceColor {
	BLACK,WHITE;
	public PieceColor reverse(){
		if(this==BLACK)return PieceColor.WHITE;
		if(this==WHITE)return PieceColor.BLACK;
	    return this;
	}

	
}
