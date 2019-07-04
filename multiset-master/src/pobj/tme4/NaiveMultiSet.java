package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	private List<T> list;


	public NaiveMultiSet(){
		list=  new ArrayList<>();;
	}


	//Comment cr�e un nouvel objet d'un objet de type T
	@Override
	public boolean add(T e, int count) {
		if(count<0)
			throw new IllegalArgumentException();
		
		if(count==0)
			return false;
		for(int i = 0;i<count;i++) {
			list.add(e);
		}
		return true;
	}
	
	@Override
	public boolean add(T e) {
		list.add(e);
		return true;
		
	}

	@Override
	public boolean remove(Object e, int count) {
		if(count<1)
			throw new IllegalArgumentException("ERREUR ");
		
		if(list.contains(e)) {
			Iterator<T> iter = list.iterator();
			int  cpt = count;
			while(iter.hasNext() && cpt >0) {
				T elem= iter.next();
				if(elem.equals(e))
					iter.remove();
			}
			return true;
		}	
		return false;
	}

	@Override
	public int count(T o) {
		int cpt=0;
		for(int i=0,len=list.size();i<len;i++)
			if(list.get(i).equals(o))
				cpt++;	
		return cpt;
	}

	@Override//pas besoin de le redefinir normalment
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public void clear() {
		list.clear();
	}


	@Override
	public List<T> elements() {
		Set<T> s = new HashSet<>(list);		
		return new ArrayList<T>(s);
	}


	@Override
	public MultiSetComparator<T> comparator() {
		return new MultiSetComparator<T>(this);
	}
	
	private class MultiSetComparator<T> implements Comparator<T> {
		
		MultiSet<T> ms;
		public MultiSetComparator(MultiSet<T> ms) {
			this.ms = ms;
		}
		public int compare(T o1, T o2) {
			return Integer.compare(ms.count(o2), ms.count(o1));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder S = new StringBuilder();
		Iterator<T> iter = elements().iterator();
		T current;
		S.append("[");
		while(iter.hasNext()) {
			current=iter.next();
			S.append(" "+current+":"+count(current)+";");
		}
		S.deleteCharAt(S.length()-1);
		S.deleteCharAt(1);
		S.append("]");
		return S.toString();
	}
	

}
