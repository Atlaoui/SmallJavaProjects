package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {
	private ClipImage clip;
	private File filename;
	
	public ToolImage(File filename) {
		this.filename = filename;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		clip = new ClipImage(e.getX(), e.getY(), filename);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		clip.setGeometry(e.getX(), e.getY(), clip.getRight(), clip.getBottom());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		clip.setGeometry(e.getX(), e.getY(), clip.getRight(), clip.getBottom());
		i.getBoard().addClip(clip);
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.drawImage(clip.getImage(), 0,0);
		
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Image";
	}

}
