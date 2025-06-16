/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags;

/**
 *
 * @author TEA
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class Triple<T,K,V> {
    
    private T t;
    private V v;
    private K k;
    
    public Triple(T left,K center,V right){
        this.t=left;
        this.k = center;
        this.v = right;
    }

    public T getLeft() {
        return t;
    }

    public K getCenter() {
        return k;
    }
    
    public V getRight() {
        return v;
    }

    public void setLeft(T t) {
        this.t = t;
    }

    public void setRight(V v) {
        this.v = v;
    }

    public void setCenter(K k) {
        this.k = k;
    }
    
    
    
    
    
}
