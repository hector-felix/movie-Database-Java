/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project02;

import java.text.DecimalFormat;

/**
 *
 * @author Hector Felix
 */
public class ArrayList<E> implements BareBonesArrayList<E> {

    private int size;
    private int capacity;
    private E[] myArray;

    DecimalFormat df = new DecimalFormat("#.##");

    public ArrayList() {
        this.capacity = 30;
        this.size = 0;
        myArray = (E[]) new Object[this.capacity];

    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
    }

    public void add(E a) {
        if (size < capacity) {
            myArray[size] = a;
            size++;
        } else {
            System.out.println("NOT enough space! Call Reallocate.");
            this.reallocate(); //call reallocate method
            this.add(a);
        }
    }

    public boolean checkDup(E a) {
        for (int i = 0; i < size; i++) {
            if (a.equals(myArray[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(int index, E a) {
        //this method inserts the data at given index
        //first, check if index is valid or not
        if (index < 0 || index > size) {
            System.out.println("Invalid index!");
            return;
        } else if (index == size) {
            //this indicates that the element needs to be added to at the end
            //now we have a method for that
            this.add(a); //call the add method to handle
        } else {
            //we will insert the data by shifting elements
            //is there enough space to shift?
            if (this.capacity == this.size) {
                //AL is already full
                System.out.println("not enough space, call reallocate!");
                this.reallocate();
            }
            //we have space to shift hte elements
            for (int i = size; i > index; i--) {
                this.myArray[i] = this.myArray[i - 1];
            }
            //Don't forget to insert
            this.myArray[index] = a;
            //update total number of elements
            this.size++;
        }
    }

    public void reallocate() {
        //this method doubles the capacity of the arraylist
        this.capacity *= 2;
        E[] temp = (E[]) new Object[this.capacity]; //casting with (E[]) 
        //now we have empty array double the size

        for (int i = 0; i < myArray.length; i++) {
            temp[i] = myArray[i];
        }
        this.myArray = temp;
    }

    @Override
    public E remove(int index) {
        //this method deleted an element from the given index
        if (index < 0 || index >= size) {
            System.out.println("Invalid Index!");
            return null;
        }
        //We will save the element to be deleted in a temp

        E temp = myArray[index];
        //then we need to shift elements to the left
        for (int i = index; i < size - 1; i++) {
            this.myArray[i] = this.myArray[i + 1];
        }
        //don't forget to update size
        size--;

        return temp;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid Index!");
            return null;
        }
        return myArray[index];
    }

    @Override
    public void set(int index, E a) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid Index!");
            return;

        }
        myArray[index] = a;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int indexOf(E a) {
        //return the index of the value being searched
        //search method, find if value exists, linear. Use .equals
        for (int i = 0; i < size; i++) {
            //go over the array and check the elements
            if (myArray[i].equals(a)) {
                return i;
            }
        }
        System.out.println("No Match Found!");
        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            myArray[i] = null;
        }
        size = 0;
    }

    //Mehtod to print the contents of the ArrayList
    //We will override toString
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s +(i+1)+")"+ myArray[i];
        }
        return s;
    }

}