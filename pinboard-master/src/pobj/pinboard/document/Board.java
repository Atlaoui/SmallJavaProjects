package pobj.pinboard.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736991031348129758L;
	
	private List<Clip> clips;
	
	
	public Board() {
		clips = new ArrayList<>();
	}

	public void removeClip(Clip c1) {
		clips.remove(c1);
	}

	public void removeClip(List<Clip> asList) {
		clips.removeAll(asList);
	}

	public void addClip(List<Clip> asList) {
		clips.addAll(asList);
	}

	public List<Clip> getContents() {	
		return clips;
	}

	public void addClip(Clip c2) {
		clips.add(c2);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for(Clip c : clips)
			c.draw(gc);
	}
	
	public void clear() {
		clips.clear();
	}
}
