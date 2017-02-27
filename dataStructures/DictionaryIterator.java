package dataStructures;

public class DictionaryIterator<K, V> implements Iterator<Entry<K, V>> {

	static final long serialVersionUID = 0L;
	
	private Dictionary<K, V>[] table;
	private Iterator<Entry<K, V>> it;
	private int current;
	
	public DictionaryIterator(Dictionary<K, V>[] table) {
		this.table = table;
		this.rewind();
	}
	
	public boolean hasNext() {
		return (current < table.length);
	}
	
	public Entry<K, V> next() throws NoSuchElementException {
		Entry<K, V> next = it.next();
		
		if(!this.hasNext())
			throw new NoSuchElementException();
		
		if(!it.hasNext()) {
			current++;
			it = this.nextHash();
		}
		
		return next;
	}
	
	public void rewind() {
		current = 0;
		it = this.nextHash();
	}
	
	protected Iterator<Entry<K, V>> nextHash() {
		it = null;
		
		while(this.hasNext() && table[current].isEmpty()) {
			current++;
		}
		
		if(current < table.length)
			it = table[current].iterator();
		
		return it;
	}

}
