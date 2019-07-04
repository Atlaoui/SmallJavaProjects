package pobj.pinboard.editor;

import java.util.ArrayDeque;
import java.util.Deque;

import pobj.pinboard.editor.commands.Command;


public class CommandStack {
	
	private Deque<Command> undo;
	private Deque<Command> redo;
	
	public CommandStack() {
		undo = new ArrayDeque<Command>();
		redo = new ArrayDeque<Command>();
	}

	public boolean isUndoEmpty() {
		// TODO Auto-generated method stub
		return undo.isEmpty();
	}

	public void addCommand(Command cmd1) {
		// TODO Auto-generated method stub	
		undo.push(cmd1);
		redo.clear();
		
	}

	public boolean isRedoEmpty() {
		// TODO Auto-generated method stub
		return redo.isEmpty();
	}

	public void undo() {
		Command c = undo.pop();
		c.undo();
		redo.push(c);
	}

	public void redo() {
		Command c = redo.pop();
		c.execute();
		undo.push(c);
	}

}
