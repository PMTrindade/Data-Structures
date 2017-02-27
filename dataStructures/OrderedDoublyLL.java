package dataStructures;

public class OrderedDoublyLL<K extends Comparable<K>, V> 
	implements OrderedDictionary<K, V> {

	static final long serialVersionUID = 0L;
	
	//Node at the head of the list.
	protected DListNode<Entry<K, V>> head;
	
	//Node at the tail of the list.
	protected DListNode<Entry<K, V>> tail;
	
	//Number of elements in the list.
	protected int currentSize;
	
	public OrderedDoublyLL() {
		head = null;
		tail = null;
		currentSize = 0;
	}
	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public int size() {
		return currentSize;
	}
	
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if(this.isEmpty())
			throw new EmptyDictionaryException();
		
		return head.getElement();
	}
	
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if(this.isEmpty())
			throw new EmptyDictionaryException();
		
		return tail.getElement();
	}
	
	protected DListNode<Entry<K, V>> findNode(K key) {
		DListNode<Entry<K, V>> node = head;
		
		while(node != null && node.getElement().getKey().compareTo(key) < 0) {
			node = node.getNext();
		}
		
		return node;
	}
	
	public V find(K key) {
		DListNode<Entry<K, V>> node = this.findNode(key);
		
		if(node == null || node.getElement().getKey().compareTo(key) != 0)
			return null;
		
		return node.getElement().getValue();
	}
	
	public V insert(K key, V value) {
		DListNode<Entry<K, V>> node;
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		V val = this.find(key);
		
		if(this.isEmpty()) {
			node = new DListNode<Entry<K, V>>(entry, null, null);
			head = node;
			tail = node;
			currentSize++;
		}
		else if(val != null) {
			this.findNode(key).getElement().setValue(value);
		}
		else if(val == null) {
			if(this.minEntry().getKey().compareTo(key) > 0) {
				node = new DListNode<Entry<K, V>>(entry, null, head);
				head.setPrevious(node);
				head = node;
			}
			else if(this.maxEntry().getKey().compareTo(key) < 0) {
				node = new DListNode<Entry<K, V>>(entry, tail, null);
				tail.setNext(node);
				tail = node;
			}
			else {
				DListNode<Entry<K, V>> previous = this.findNode(key).getPrevious();
				DListNode<Entry<K, V>> next = this.findNode(key);
				node = new DListNode<Entry<K, V>>(entry, previous, next);
				previous.setNext(node);
				next.setPrevious(node);
			}
			currentSize++;
		}
		
		return val;
	}
	
	public V remove(K key) {
		DListNode<Entry<K, V>> node = this.findNode(key);
		
		if(this.isEmpty() || this.find(key) == null)
			return null;
		
		if(node == head) {
			head = node.getNext();
			if(head == null)
				tail = null;
			else
				head.setPrevious(null);
		}
		else if(node == tail) {
			tail = node.getPrevious();
			if(tail == null)
				head = null;
			else
				tail.setNext(null);
		}
		else {
			DListNode<Entry<K, V>> previous = node.getPrevious();
			DListNode<Entry<K, V>> next = node.getNext();
			previous.setNext(next);
			next.setPrevious(previous);
		}
		
		currentSize--;
		
		return node.getElement().getValue();
	}
	
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}

}
