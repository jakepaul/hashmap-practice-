package ca.uwo.eng.se2205b.lab5.model;

import ca.uwo.eng.se2205b.lab5.IHashMap;

import java.util.*;

/**
 * Created by jacob on 2017-03-09.
 */
public class MyHashMap<K,V> extends AbstractMap implements IHashMap {
    
    
    public static class HashEntry<K, V> extends AbstractMap.SimpleEntry<K, V> {
    
        private K key;
        private V value;
        
        
        
        public HashEntry(K key, V value) {
            super(key, value);
            this.key = (K) key;
            this.value = (V) value;
        }
    
        public V getValue() {
            return value;
        }
        
        public K getKey() {
            return key;
        }
    
        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }
        
        public int hashcode(){
            if (key != null && value != null){
                int out = key.hashCode();
                out += 37*value.hashCode();
                return out;
            } else {
                throw new NullPointerException("cannot be null");
            }
            
        }
        
        public boolean defunct = false;
    }
    
    
    
    
    
    //MyHashMap Stuff
    
    private double loadFactor;
    private int tableEntries = 0;    //n
    private int tableSize = 7;      //N
    
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    private HashEntry<K, V>[] hashTable = (HashEntry<K, V>[]) (new HashEntry[tableSize]);
    
    
    public MyHashMap() {
        loadFactor = 0.3;
        
    }
    
    public MyHashMap(double loadFactor) {
        this.loadFactor = loadFactor;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    
    
    
    
    @Override
    public Set<Entry> entrySet() {
        
        EntrySet<Entry> entrySet = new EntrySet<>();
        return entrySet;
        
    }
    
    
    
    
    
    
    @Override
    public boolean containsKey(Object key) {
        
        if (key != null) {
            int index = Math.abs(Math.abs(key.hashCode())) % tableSize;
    
            while (hashTable[index] != null) {
                if (key.equals(hashTable[index].getKey()) && !hashTable[index].defunct) {
                    return true;
                } else {
                    index = (index + 1) % tableSize;
                }
            }
            return false;
        } else {
            throw new NullPointerException("key is null");
        }
    }
    
    @Override
    public Object get(Object key) {
        if (key != null) {
            int index = Math.abs(Math.abs(key.hashCode())) % tableSize;
    
            while (hashTable[index] != null) {
                if (key.equals(hashTable[index].getKey()) && !hashTable[index].defunct) {
                    return hashTable[index].getValue();
                } else {
                    index = (index + 1) % tableSize;
                }
            }
            return null;
            
        } else {
            throw new NullPointerException("key is null");
        }
        
        
    }
    
    @Override
    public Object put(Object key, Object value) {
        if (key != null) {
            int index = Math.abs(Math.abs(Math.abs(key.hashCode()))) % tableSize;
            System.out.println("PUT: key="+key.toString() + " hashcode=" + Math.abs(Math.abs(key.hashCode())) + " index=" + index);
    
            while (hashTable[index] != null) {
                if (key.equals(hashTable[index].getKey()) && !hashTable[index].defunct) {
                    V temp = hashTable[index].getValue();
                    hashTable[index] = new HashEntry<K, V>((K) key, (V) value);
                    tableEntries++;
                    size++;
                    if (loadFactor() > loadFactorThreshold()) {
                        reHash();
                    }
                    return temp;
                } else {
                    index = (index + 1) % tableSize;
                }
            }
            hashTable[index] = new HashEntry<K, V>((K) key, (V) value);
            tableEntries++;
            size++;
            if (loadFactor() > loadFactorThreshold()) {
                reHash();
            }
            return null;
        } else {
            throw new NullPointerException("key is null");
        }
    }
    
    @Override
    public Object remove(Object key) {
        if (key != null){
    
            int index = Math.abs(key.hashCode()) % tableSize;
            System.out.println("REMOVE: key=" + key.toString() + " hashcode=" + Math.abs(Math.abs(key.hashCode())) + " index=" + index);
            
            
            while (hashTable[index] != null) {
                if (key.equals(hashTable[index].getKey()) && !hashTable[index].defunct) {
                    hashTable[index].defunct = true;
                    size--;
                    return hashTable[index].getValue();
                }
                index = (index + 1) % tableSize;
            }
            
           return null;
            
        }else{
            throw new NullPointerException("key is null");
        }
    }
    
    @Override
    public void clear() {
        this.hashTable = (HashEntry<K, V>[]) (new HashEntry[tableSize]);
        tableEntries = 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null) {
    
            if (this.getClass() == o.getClass()) {
                if (this.size() == ((MyHashMap<K, V>) o).size()) {
                    Iterator<Entry> itr = this.entrySet().iterator();
            
                    while (itr.hasNext()) {
                        if (!((MyHashMap<K, V>) o).entrySet().contains(itr.next())) {
                            return false;
                        }
                    }
            
                    return true;
            
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else{
            throw new NullPointerException("object is null");
        }
       
    }
    
    @Override
    public int hashCode() {
        
        int out = 0;
    
        for(HashEntry<K,V> e : hashTable){
            if (e != null && !e.defunct) {
                //System.out.print(e.getKey().toString());
                out += e.hashCode();
            }
        }
        
        return out;
    }
    
    @Override
    public String toString() {
    
        String s = "{";
        
        int counter = size;
        
        for(HashEntry<K,V> e : hashTable){
            if (e != null && !e.defunct) {
                if (e.getKey() != null) {
                    //System.out.print(e.getKey().toString());
                    s += e.getKey().toString();
                    counter--;
                }
                if (e.getValue() != null) {
                    //System.out.print("=" + e.getValue().toString() + "\n");
                    s += "=" + e.getValue().toString();
                } else {
                    s += "null";
                }
                if (counter > 0) {
                    s += ", ";
                }
            }
            
        }
        
        s += "}";
        
        return s;
    }
    
    // IHashMap Methods
    
    
    @Override
    public double loadFactorThreshold() {
        return loadFactor;
    }
    
    @Override
    public double loadFactor() {
        return (double) tableEntries / (double) tableSize;
    }
    
    @Override
    public int capacity() {
        return tableSize;
    }
    
    private void reHash() {
        
        int oldSize = tableSize;
        tableSize = nextPrime(2*tableSize);
        
        tableEntries = 0;
        size = 0;
        
        HashEntry<K,V>[] oldHashTable = hashTable;
        
        this.hashTable = (HashEntry<K, V>[]) (new HashEntry[tableSize]);
        
        
        for (int i = 0; i < oldSize; i++){
            if (oldHashTable[i] != null && !oldHashTable[i].defunct){
                this.put(oldHashTable[i].getKey(), oldHashTable[i].getValue());
            }
        }
        
    }
    
    private int nextPrime(int n) {
        
        while (!isPrime(n)){
            n++;
        }
        return n;
    }
    
        /**
         * Checks is a positive integer is a prime number
         */
    
    public static boolean isPrime(int number) {
        for (int check = 2; check < number; ++check) {
            if (number % check == 0) {
                return false;
            }
        }
        return true;
    }
    
    
    
    
    public class EntrySet<Entry> extends AbstractSet<Entry> {
    
        @Override
        public void clear() {
            MyHashMap.this.clear();
        }
    
        @Override
        public int size(){
            return MyHashMap.this.size();
        }
    
        @Override
        public Iterator<Entry> iterator() {
            /*List<Entry> list = new ArrayList<Entry>();
            for (int i = 0; i < MyHashMap.this.hashTable.length; i++){
                if (MyHashMap.this.hashTable[i] != null && !MyHashMap.this.hashTable[i].defunct){
                    list.add((Entry)MyHashMap.this.hashTable[i]);
                }
            }
            return list.iterator();*/
            SetIterator itr = new SetIterator();
            return (Iterator)itr;
        }
        
        public class SetIterator<Entry> implements Iterator<Entry> {
            
            int current;
            
            public SetIterator() {
                current = 0;
            }
            
            @Override
            public void remove() {
                MyHashMap.this.remove(MyHashMap.this.hashTable[current]);
            }
            
            @Override
            public boolean hasNext() {
                int i = current + 1;
                //return current < MyHashMap.this.hashTable.length;
    
                while (MyHashMap.this.hashTable[i] == null || MyHashMap.this.hashTable[i].defunct){
                    i++;
                    if (i == MyHashMap.this.hashTable.length) break;
                }
    
                return (i < MyHashMap.this.hashTable.length);
            }
            
            @Override
            public Entry next() {
                current++;
                //return (Entry)MyHashMap.this.hashTable[current-1];
                while (MyHashMap.this.hashTable[current] == null || MyHashMap.this.hashTable[current].defunct){
                    current++;
                    if (current == MyHashMap.this.hashTable.length) break;
                }
                
                if (current >= MyHashMap.this.hashTable.length) {
                    throw new NoSuchElementException("No more stuff");
                }
                else {
                    return (Entry)MyHashMap.this.hashTable[current];
                }
                    
            }
        }
        
    }
    


    public void print(){
    
        System.out.println("TableSize:" + tableSize);
        System.out.println("NumEntries: " + tableEntries);
        System.out.println("Size: " + this.size());
        
        for(HashEntry<K,V> e : hashTable){
            if (e != null && !e.defunct) {
                if (e.getKey() != null) {
                    System.out.print(e.getKey().toString());
                }
                if (e.getValue() != null) {
                    System.out.print("=" + e.getValue().toString() + "\n");
                }
            }
        }
        
        System.out.println("\n");
    }
    
    
    public static void main(String[] args){
        
        
        //System.out.println("" + "Deirdre".hashCode());
        
        
        MyHashMap<String, Integer> testMap = new MyHashMap<>();
    
        System.out.println("Should be false: " + testMap.containsKey("Jake"));
        
        testMap.put("Jake", (Integer)19);
        testMap.print();
        
        testMap.put("Evan", (Integer)20);
        testMap.print();
        
        testMap.remove("Jake");
        testMap.print();
        
        testMap.remove("Evan");
        testMap.print();
        
        testMap.remove("Jake");
        testMap.print();
    
        System.out.println("Should be false: " + testMap.containsKey("Jake"));
        
        testMap.remove("NotInTable");
        testMap.print();
        
        
        testMap.put("Breanne", (Integer)19);
        testMap.print();
        
        testMap.put("Deirdre", (Integer)15);
        testMap.print();
        
        
        testMap.put("Jake", (Integer)20);
        testMap.print();
        
        
        System.out.println("Should be true: " + testMap.containsKey("Jake"));
        
        testMap.clear();
        testMap.print();
        
        
        
        //System.out.println("" + testMap.hashCode());
        
        System.out.println(testMap.toString());
        System.out.println(testMap.hashCode());
        
        
        System.out.println("Testing Entry Set");
        Iterator itr = testMap.entrySet().iterator();
        
        while (itr.hasNext()){
            System.out.println(((HashEntry)itr.next()).getKey().toString());
        }
        
    }
    



}
    


