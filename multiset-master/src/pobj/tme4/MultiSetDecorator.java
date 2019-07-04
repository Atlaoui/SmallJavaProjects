package pobj.tme4;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {

	private MultiSet<T> decorated;
	
	
	public MultiSetDecorator(MultiSet<T> todecorate) {
		decorated=todecorate;
	}
	
	private boolean isConsistant() {
		int cpt=0;
		List<T> l=elements();
		for(T e : l) {
			if(count(e)<=0)
				return false;
			cpt+=count(e);
		}
		if(cpt!=size())
			return false;
		return true;
	}
	
	
	@Override
	public boolean add(T e, int count) {
		boolean retour = decorated.add(e, count);
		assert isConsistant();
		return retour;
	}
	
	@Override
	public boolean add(T e) {
		boolean retour = decorated.add(e);
		assert isConsistant();
		return retour;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean retour = decorated.remove(e,count);
		assert isConsistant();
		return retour;
	}
	
	@Override
	public boolean remove(Object e) {
		boolean retour = decorated.remove(e);
		assert isConsistant();
		return retour;
	}
	
	
	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Comparator<T> comparator() {
		return decorated.comparator();
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public int size() {
		return decorated.size();
	}
	
	@Override
	public void clear() {
		decorated.clear();
		assert isConsistant();
		
	}
	@Override
	public String toString() {
		return decorated.toString();
	}

}
