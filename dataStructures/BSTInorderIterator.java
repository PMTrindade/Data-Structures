package dataStructures;

public class BSTInorderIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 0L;
	
	// The root of the BST.
	private BSTNode<K, V> root;
	
	// The stack used to save the nodes.
	private Stack<BSTNode<K, V>> stack;
	
	public BSTInorderIterator(BSTNode<K, V> root) {
		this.root = root;
		this.rewind();
	}
	
	public boolean hasNext() {
		return (!stack.isEmpty());
	}
	
	public Entry<K, V> next() throws NoSuchElementException {
		if(!this.hasNext()) {
			throw new NoSuchElementException();
		}
		
		BSTNode<K, V> next = stack.pop();
		pushLeftNodes(next.getRight());
		
		return next.getEntry();
	}
	
	public void rewind() {
		stack = new StackInList<BSTNode<K, V>>();
		pushLeftNodes(root);
	}
	
	public void pushLeftNodes(BSTNode<K, V> node) {
		while(node != null) {
			stack.push(node);
			node = node.getLeft();
		}
	}

}
