package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{
    private List<Clip> clips;
	private EditorInterface intF;
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		clips = new ArrayList<>();
		intF=editor;
		clips.add(toAdd);
	}
	public CommandAdd(EditorInterface editor, List<Clip> clips) {
		this.clips = clips;
		intF=editor;
	}


	@Override
	public void execute() {
		intF.getBoard().addClip(clips);
	}

	@Override
	public void undo() {
		intF.getBoard().removeClip(clips);
	}

}
