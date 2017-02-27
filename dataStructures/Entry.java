package dataStructures;

import java.io.Serializable;

public interface Entry<K, V> extends Serializable {

    //Returns the key in the entry.
    K getKey();
    
    //Returns the value in the entry.
    V getValue();
    
    //Sets the key in the entry.
    void setKey(K key);
    
    //Sets the value in the entry.
    void setValue(V value);

}
