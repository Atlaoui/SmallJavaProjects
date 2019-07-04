package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ClipRect(double left, double top, double right, double bottom, Color green) {
		super(left,top,right,bottom, green);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(),getTop(),Math.abs(getRight()-getLeft()),Math.abs(getBottom()-getTop()));
	}


	@Override
	public ClipRect  copy() {
		
		return new ClipRect(this.getLeft(),this.getTop(),this.getRight(),this.getBottom(),this.getColor());
	}

}
