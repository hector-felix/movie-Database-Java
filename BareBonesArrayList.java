/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project02;

/**
 *
 * @author Hector Felix
 */
public interface BareBonesArrayList<E> {
    
    public void add(E a);
    public void add(int index, E a);
    public void reallocate();
    public E remove(int index);
    public E get(int index);
    public boolean checkDup (E a);
    public void set(int index, E a);
    public int getSize();
    public int indexOf(E a);
    public void clear();
}