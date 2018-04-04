package ui;

import id.PieceColor;

public class GameStateInfo {
	private int depth;
	private boolean quiesce;
	private PieceColor c;
	private int time;
	
	public GameStateInfo(int depth, boolean quiesce, PieceColor c,int time) {
		super();
		this.depth = depth;
		this.quiesce = quiesce;
		this.c = c;
		this.time=time;
	}
	public int getDepth() {
		return depth;
	}
	public boolean isQuiesce() {
		return quiesce;
	}
	public PieceColor getC() {
		return c;
	}
	public int getTime(){
		return time;
	}
	
}
