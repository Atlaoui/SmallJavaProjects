package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard  {
	private static Clipboard clipB =new Clipboard();
	private List<Clip> clips;
	private List<ClipboardListener> listeners;
	
	private Clipboard() {
		clips=new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> lc = new ArrayList<>();
		for(Clip c : clips)
			lc.add(c.copy());
		notifyListners();
		return lc;
	}
	
	public void copyToClipboard(List<Clip> clips) {
		for(Clip c : clips)
			this.clips.add(c.copy());
		notifyListners();
	}
	
	public void clear() {
		clips.clear();
		notifyListners();
	}
	
	public boolean isEmpty() {
		return clips.isEmpty();
	}
	
	public static Clipboard getInstance() {
		return clipB;
	}

	public void addListener(ClipboardListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		listeners.remove(listener);
	}
	
	public void notifyListners() {
		for(ClipboardListener l : listeners)
			 l.clipboardChanged();
	}
	
}
