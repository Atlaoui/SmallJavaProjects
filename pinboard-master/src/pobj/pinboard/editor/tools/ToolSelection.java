package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;


public class ToolSelection implements Tool{
	
	private double originX;
	private double originY;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		}else {
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		}
		originX = e.getX();
		originY = e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for(Clip c : i.getSelection().getContents()) {
			c.move(e.getX()-originX, e.getY()-originY);
		}
		originX = e.getX();
		originY = e.getY();
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		//rien faire
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Selection";
	}

}
