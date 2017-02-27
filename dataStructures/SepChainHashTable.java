package dataStructures;

public class SepChainHashTable<K extends Comparable<K>, V> 
	extends HashTable<K, V> {

    static final long serialVersionUID = 0L;
    
    //The array of dictionaries.
    protected Dictionary<K, V>[] table;
    
    @SuppressWarnings("unchecked")
	public SepChainHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        //Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for(int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoublyLL<K, V>();
        
        maxSize = capacity;
        currentSize = 0;
    }
    
    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }
    
    //Returns the hash value of the specified key.
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }
    
    //If there is an entry in the dictionary whose key is the specified key,
    //returns its value; otherwise, returns null.
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }
    
    //If there is an entry in the dictionary whose key is the specified key,
    //replaces its value by the specified value and returns the old value;
    //otherwise, inserts the entry (key, value) and returns null.
    public V insert(K key, V value) {
    	if(this.isFull())
        	this.rehash();
        
        V val = table[this.hash(key)].insert(key, value);
        
        if(val == null)
        	currentSize++;
        
        return val;
    }
    
    //If there is an entry in the dictionary whose key is the specified key,
    //removes it from the dictionary and returns its value;
    //otherwise, returns null.
    public V remove(K key) {
    	V val = table[this.hash(key)].remove(key);
        
        if(val != null)
        	currentSize--;
        
        return val;
    }
    
    //Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator() {
    	return new DictionaryIterator<K, V> (table);
    }
    
    @SuppressWarnings("unchecked")
	protected void rehash() { //Test!
    	//Duplica o tamanho máximo da tabela.
    	maxSize = maxSize * 2;
    	int newSize = HashTable.nextPrime((int) (1.1 * maxSize));
    	
    	//Compiler gives a warning.
    	//Cria uma nova tabela com o novo tamanho máximo.
    	Dictionary<K, V>[] newTable = (Dictionary <K, V>[]) new Dictionary[newSize];
    	
    	//Inicializa o valor de cada posição da tabela, ou seja, as OrderedDoublyLL.
    	for(int i = 0; i < newSize; i++) {
    		newTable[i] = new OrderedDoublyLL<K, V>();
    	}
    	
    	//Cria e corre um iterador para inicializar os valores de cada posição em todas as OrderedDoublyLL da tabela.
    	Iterator<Entry<K, V>> it = this.iterator();
    	Entry<K, V> entry = null;
    	while(it.hasNext()) {
    		entry = it.next();
    		newTable[this.hash(entry.getKey())].insert(entry.getKey(), entry.getValue());
    	}
    	
    	//Iguala a tabela anterior à tabela criada com o dobro do tamanho.
    	table = newTable;
    }

}
