package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1858467929506431700L;
	private Image image;
	private File filename;
	
	public  ClipImage(double left, double top, File filename){
		super(left,top,0,0,null);
		image = new Image("file://" + filename.getAbsolutePath());
		double bottom = image.getHeight();
		double right = image.getWidth();
		setGeometry(left, top, right, bottom);
		
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, getLeft(), getTop());
		
	}

	@Override
	public ClipImage copy() {
		// TODO Auto-generated method stub
		return new ClipImage(getLeft(), getTop(), filename);
	}
	
	public Image getImage() {
		return image;
	}

}
