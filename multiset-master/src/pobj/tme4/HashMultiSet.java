package pobj.tme4;



import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;


public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private HashMap<T,Integer> mapMult = new HashMap<>();
	private int size = 0;

	public  HashMultiSet() {

	}

	public HashMultiSet(Collection<T> coll){
		Iterator<T> iterator = coll.iterator();
		while(iterator.hasNext())
			add(iterator.next());
	}

	@Override
	public boolean add(T e, int count) {
		if(count<0) 
			throw new IllegalArgumentException();
		if(count == 0)
			return false;
		
		if(mapMult.containsKey(e))
			mapMult.put(e,mapMult.get(e).intValue()+count);
		else
			mapMult.put(e, count);
		size+=count;
		return true;
	}

	@Override
	public boolean add(T e) {
		if(mapMult.containsKey(e))
			mapMult.put(e,mapMult.get(e).intValue()+1);
		else
			mapMult.put(e, 1);
		size++;
		return true;
	
	}

	@Override
	public boolean remove(Object e) {
		
		if(!mapMult.containsKey(e)) return false;
		
		else {
			if(mapMult.get(e).intValue()==1)
				mapMult.remove(e);
			else
				mapMult.put((T)e,mapMult.get(e).intValue()-1);

			size--;
		}
		return true;
	}


	@Override
	public boolean remove(Object e, int count) {
		if(count<0)
			throw new IllegalArgumentException();
		
		if(!mapMult.containsKey(e)) return false;
		
		else {
			if(mapMult.get(e)-count <= 0) 
				size-=mapMult.remove(e);//si ca beug c ici	
			else {
				mapMult.put((T)e,mapMult.get(e)-count);
				size-=count;
			}
		}
		return true;
	}

	@Override
	public int count(T o) {
		if(mapMult.containsKey(o))
			return mapMult.get(o);
		return 0;
	}

	@Override
	public void clear() {
		mapMult.clear();
		size=0;
	}

	@Override
	public int size() {
		return size;
	}

	public Iterator<T> iterator() {
		return new HashMultiSetIterator<T>(this);
	}


	public HashMap<T, Integer> getMapMult() {
		return mapMult;
	}
	
	
	@Override
	public MultiSetComparator<T> comparator() {
		return new MultiSetComparator<T>(this);
	}
	

	@Override
	public List<T> elements (){
		List<T> list = new ArrayList<T>();
		Iterator<Entry<T, Integer>> iter = mapMult.entrySet().iterator();
		while(iter.hasNext()) 
			list.add(iter.next().getKey());
		return list;
	}

	
	public T getMax() {
		Iterator<Entry<T, Integer>> iter = mapMult.entrySet().iterator();
		Entry<T,Integer> respon = null,temp;
		int compt=0;
		while(iter.hasNext()) {
			temp=iter.next();
			if(temp.getValue()>compt) {
				compt=temp.getValue();
				respon=temp;
			}
		}
			return respon.getKey();
	
	}


	@Override
	public String toString() {
		StringBuilder S = new StringBuilder();
		Iterator<Entry<T, Integer>> iter=mapMult.entrySet().iterator();
		Entry<T,Integer> current;
		S.append("[");
		while(iter.hasNext()) {
			current=iter.next();
			S.append(" "+current.getKey()+":"+current.getValue()+";");
		}
		S.deleteCharAt(S.length()-1);
		S.deleteCharAt(1);
		S.append("]");
		return S.toString();
	}
	
	
	private class HashMultiSetIterator<T> implements Iterator<T>{
		private int index=0;
		private int maxOccurences,nbOccurences=0;
		private T data;
		private Iterator<Entry<T, Integer>> iter;
		private Entry<T,Integer> current;
		public HashMultiSetIterator(HashMultiSet<T> hashMultiSet) {
			HashMap<T,Integer> mapMult=hashMultiSet.mapMult;
			iter=mapMult.entrySet().iterator();
			if(iter.hasNext()) {
				current=iter.next();
				index=0;
				maxOccurences=current.getValue();
				nbOccurences=0;
				data=current.getKey();
			}else {
				
			}
			
		}

		@Override
		public boolean hasNext() {
			return index<size();
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();	
		
			if(nbOccurences<maxOccurences) {
				nbOccurences++;
				index++;
				return data;	
			}
			current=iter.next();	
			maxOccurences=current.getValue();
			index++;
			nbOccurences=1;
			data=current.getKey();
			return data;
		}

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
}










