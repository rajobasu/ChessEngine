package frameWork;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import ui.UIBoard;

public class Testing {
	
	static LinkedList<String> boardsEval;
	static Set<String> setBoards;
	
	static {
		boardsEval=new LinkedList<>();
		setBoards=new TreeSet<>();
	}
	
	public static void addPos(String s){
		boardsEval.add(s);
		setBoards.add(s);
	}
	
	public static void display(){
		UIBoard.println("Number of positions evaluated : "+boardsEval.size()+" \n");
		UIBoard.println("Number of duplicate positions : "+(boardsEval.size()-setBoards.size())+" \n");
		UIBoard.println("Duplicates as % : " +(((boardsEval.size()-setBoards.size())*100.0)/boardsEval.size())+"\n");
		 
	}
	
}
