package pobj.pinboard.editor.commands;



import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {

	private EditorInterface editor;
	private ClipGroup group;
	public CommandUngroup(EditorInterface editor, ClipGroup group) {
		this.editor=editor;
		this.group = group;
	}

	@Override
	public void execute() {
		 editor.getBoard().removeClip(group);
		for(Clip c : group.getClips())
			editor.getBoard().addClip(c);
	}

	@Override
	public void undo() {
		for(Clip c : group.getClips())
			editor.getBoard().removeClip(c);
		editor.getBoard().addClip(group);
	}
}
