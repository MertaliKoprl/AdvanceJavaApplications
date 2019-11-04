/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author 216CS2017
 */
public class Hash<T> {
    
    LinkedList<T>[] table;// ELEMENT CAN BE CHANGE TO LINKED LIST OR TREE E.T.C
    boolean[] deleted;
    int N;
    HashFunction<T> hash;
    
    public Hash(int N, HashFunction<T> hash) {
        table = new LinkedList[N];
        deleted = new boolean[N];
        this.N = N;
        this.hash = hash;
    }
    
    Node<T> search(Node<T> newNode) {
        int address = hash.hashFunction(newNode.data);
        return table[address].searchNode(newNode);//.search for treee.

    }
    
    void insert(Node<T> newNode) {
        int address = hash.hashFunction(newNode.data);
        table[address].insertMiddle(newNode, table[address].getPrevious(newNode));
        
    }
    
    void delete(Node<T> newNode) {
        
        int address;
        address = hash.hashFunction(newNode.data);
        newNode = table[address].searchNode(newNode);
        if (newNode != null) {
            table[address].deleteMiddle(newNode);//IMPLEMENT THAT DELETE RESPECT TO LINKED LIST
        }
    }
    
    
}
