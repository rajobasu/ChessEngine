package ui;

import java.awt.Color;

import javax.swing.JPanel;

import frameWork.Location;

public class CustomJPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Location location;
	private Color prevColor;
	
	public CustomJPanel(Location location) {
		super();
		this.location=location;
	}
	public void setColor(){
		this.prevColor=getBackground();
	}
	
	public Location getRowAndCol(){
		return location.copy();
	} 
	
	public void highlight(){
	
		setBackground(Color.CYAN);
	}
	
	public void setDefaultColor(){
		setBackground(prevColor);
	}
	public void highlight(Color color){
		
		prevColor=getBackground();
		
		setBackground(color);
		
	}
	
}
