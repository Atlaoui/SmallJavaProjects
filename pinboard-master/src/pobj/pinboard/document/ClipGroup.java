package pobj.pinboard.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup implements Composite,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Clip> clips;
	private Color color = Color.BISQUE;
	private double top,bottom,right,left;
	
	public ClipGroup() {
		clips = new ArrayList<>();
	}
	
	
	public ClipGroup(List<Clip> clips) {
		this.clips=new ArrayList<>();
		this.clips.addAll(clips);
		findPos();
	}


	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c: clips)
			c.draw(ctx);
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		move(right-left,bottom-top);
	}

	@Override
	public void move(double x, double y) {

		for(Clip c: clips)
			c.move(x, y);
		findPos();
	}

	@Override
	public boolean isSelected(double x, double y) {
		for(Clip cl: clips)
			if(cl.isSelected(x, y))
				return true;
		return false;
	}



	@Override
	public ClipGroup copy() {
		ClipGroup copy = new ClipGroup();
		for(Clip cl: this.clips)
			copy.clips.add(cl.copy());

		return copy;
	}

	private void findPos() {
		top=2147483647;
		left=2147483647;
		bottom=0;
		right=0;
		for(Clip cl: clips) {
			if( top >cl.getTop())
				top=cl.getTop();
			if(left > cl.getLeft())
				left=cl.getLeft();
			if(bottom < cl.getBottom())
				bottom=cl.getBottom();
			if(right < cl.getRight())
				right=cl.getRight();
		}	
	}



	@Override
	public List<Clip> getClips() {
		// TODO Auto-generated method stub
		return clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		clips.add(toAdd);
		findPos();
	}

	@Override
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);
		findPos();
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public double getLeft() {

		return left;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setColor(Color c) {
		for(Clip cl: clips)
			cl.setColor(c);

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	public void clear() {
		clips.clear();
	}

	public boolean contains(Clip o) {
		return clips.contains(o);
	}
	
}
