package pobj.pinboard.editor.commands;

import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {

	private ClipRect rect;
	private EditorInterface editor;
	private double d;
	private double e;

	public CommandMove(EditorInterface editor, ClipRect rect, double d, double e) {
		this.rect=rect;
		this.editor=editor;
		this.d=d;
		this.e=e;
	}

	@Override
	public void execute() {
		
		editor.getBoard().removeClip(rect);
		rect.move(d,e);
		editor.getBoard().addClip(rect);
	}

	@Override
	public void undo() {
		rect.move(-1*d, -1*e);
		editor.getBoard().removeClip(rect);
		editor.getBoard().addClip(rect);
	}

}
