package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private double top,bottom,right,left;
	private List<Clip> contents =new ArrayList<>();;
	
	
	public void select(Board board, double x, double y) {	
		contents.clear();
		
		for(Clip clip : board.getContents())
			if(clip.isSelected(x, y)) {
				contents.add(clip);
				top=clip.getTop();
				bottom=clip.getBottom();
				right=clip.getRight();
				left=clip.getLeft();
				findPos();
				break;
			}	
	}
	


	public void toogleSelect(Board board, double x, double y) {
		for(Clip clip : board.getContents())
			if(clip.isSelected(x, y))
				if(contents.contains(clip))
					contents.remove(clip);
				else
				contents.add(clip);
		findPos();
	}
	
	
	
	public List<Clip> getContents(){
		return contents;
	}
	
	 public void clear() {
		 contents.clear();
	 }
	 
	 public void drawFeedback(GraphicsContext gc) {
		Color col;	
		 for (Clip clip: contents) {
				col= clip.getColor();
				clip.setColor(Color.RED);
				gc.strokeRect(clip.getLeft(), clip.getRight(), clip.getRight()-clip.getLeft(), clip.getTop()-clip.getBottom());
				clip.setColor(col);
		 }
		 findPos();
		 gc.setStroke(Color.RED);
		 gc.strokeRect(getLeft(),getRight(),getTop(),getBottom());
	 }
	 
	 public boolean isSelected(double x,double y) {
		 if(y<=bottom && y>=top && x>=left && x<=right)
				return true;
			return false;
	 }
	 private void findPos() {
			top=2147483647;
			left=2147483647;
			bottom=0;
			right=0;
			for(Clip cl: contents) {
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
	 
	 
	 public void moveAll(double x,double y) {
		 for(Clip clip : contents) 
			 	clip.move(x-left, y-top);
		 findPos();
	 }
	 
		public double getTop() {
			return top;
		}


		public double getBottom() {
			return bottom;
		}

		
		public double getRight() {
			return right;
		}



		public double getLeft() {
			return left;
		}

		
	 
}
