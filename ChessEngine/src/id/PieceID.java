package id;

public enum PieceID {
	PAWN(10,100,"P"), ROOK(15,500,"R"), KNIGHT(16,320,"N"), BISHOP(17,330,"B"), KING(5,10000,"K"), QUEEN(20,900,"Q");
	private String stringform;
	
    public String stringForm(){
    	return stringform;
    }
	int val;
	int precedence;
    
	private PieceID(int p,int val,String str) {
		this.val = val;
		this.stringform=str;
	    this.precedence=p;
	}

	public int getVal() {
		return val;
	}

	public int getPrecedence() {
		// TODO Auto-generated method stub
		return precedence;
	}
    
}
