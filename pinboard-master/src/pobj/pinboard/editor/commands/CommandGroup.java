package pobj.pinboard.editor.commands;


import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {

	private EditorInterface intF;
	private ClipGroup group;

	public CommandGroup(EditorInterface editor, List<Clip> rects) {
		intF=editor;
		group = new ClipGroup(rects);
	}

	@Override
	public void execute() {
		for(Clip c : group.getClips())
			intF.getBoard().removeClip(c);
		intF.getBoard().addClip(group);
	}

	@Override
	public void undo() {
		intF.getBoard().removeClip(group);
		for(Clip c : group.getClips())
			intF.getBoard().addClip(c);
	}
}
