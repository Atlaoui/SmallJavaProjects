package pobj.pinboard.document;

import java.io.Serializable;

import javafx.scene.paint.Color;

public  abstract class AbstractClip implements Clip,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6170202954625160390L;
	private double top,bottom,right,left;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color green) {
		this.left=left;
		this.bottom=bottom;
		this.right=right;
		this.top=top;
		color=green;
	}
	@Override
	public double getTop() {
		return top;
	}

	@Override
	public double getLeft() {
		return left;
	}

	@Override
	public double getBottom() {
		return bottom;
	}

	@Override
	public double getRight() {
		return right;
	}
	
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}

	@Override
	public void move(double x, double y) {
		left+=x;
		right+=x;
		bottom+=y;
		top+=y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		if(y<=bottom && y>=top && x>=left && x<=right)
			return true;
		return false;
	}

	@Override
	public void setColor(Color c) {
		color=c;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public double getLarg() {
		return right-left;
	}
	
	public double getLong() {
		return bottom-top;
	}
	
}
