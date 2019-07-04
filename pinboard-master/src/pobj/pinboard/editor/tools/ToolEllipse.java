package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool{

	private ClipEllipse clip;
	private double mousePressX;
	private double mousePressY;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		mousePressX = e.getX();
		mousePressY = e.getY();
		clip = new ClipEllipse(e.getX(), e.getY(), e.getX(), e.getY(), i.getCurrentColor());	
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		if( e.getX() < mousePressX ) {
			clip.setGeometry(e.getX(), e.getY(), mousePressX, mousePressY);
		}
		else {
			clip.setGeometry(mousePressX, mousePressY, e.getX(), e.getY());
		}
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if(mousePressX <e.getX() && mousePressY >e.getY())
			clip.setGeometry(mousePressX,e.getY() , e.getX(),mousePressY );
		if(e.getX()<mousePressX && mousePressY <e.getY())
				clip.setGeometry(e.getX(), mousePressY, mousePressX, e.getY());
		
		CommandAdd cAdd = new CommandAdd(i,clip);
			cAdd.execute();
		i.getUndoStack().addCommand(cAdd);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeOval(clip.getLeft(), clip.getTop(), clip.getLarg(), clip.getLong());
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse";
	}

}
