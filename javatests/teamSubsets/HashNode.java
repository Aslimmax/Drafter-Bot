package teamSubsets;

public class HashNode<K, V> {

    K key;
    V value;
    HashNode<K, V> next;

    HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public V getValue() {
        return value;
    }


    public void setValue(V value) {
        this.value = value;
    }


    public K getKey() {
        return key;
    }
}