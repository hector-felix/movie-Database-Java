/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project02;

import java.util.EmptyStackException;

/**
 *
 * @author Hector Felix
 * @param <E>
 */
public class ArrayStack<E> implements BareBonesStack {

    private E[] theData;
    private E[] theDataTemp;
    int topOfStack = -1;
    private static final int INITIAL_CAPACITY = 30;

    public ArrayStack() {
        theData = (E[]) new Object[INITIAL_CAPACITY];
    }

    public void push(E item) {
        topOfStack++;
        theData[topOfStack] = item;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack--];
    }

    @Override
    public E peek() {
        //System.out.println("Peek "+theData[topOfStack]);
        return theData[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        return (topOfStack == -1);
    }

    public String toString() {
        String s = "\n";
        E[] newArray = checkDup(theData);
//        for (int i = topOfStack; i >= 0; i--) {
//            s += theData[i];
//        }
        for (int i = newArray.length - 1; i >= 0; i--) {
            s += newArray[i];
        }

        return s;
    }

    public E[] checkDup(E[] dataOriginal) {
        int nullCount = 0;

        for (int j = 0; j < dataOriginal.length - 1; j++) {
            for (int i = j + 1; i < dataOriginal.length - 1; i++) {
                if (dataOriginal[j] == dataOriginal[i]) {
                    dataOriginal[i] = null;
                    //nullCount++;
                }
            }
        }
        dataOriginal[dataOriginal.length - 1] = null;

        for (int i = 0; i < topOfStack + 1; i++) {
            if (dataOriginal[i] == null) {
                nullCount++;
            }
        }

        //System.out.println("Null count: "+nullCount);
        int secondCount = 0;
        
        E[] dataModified = (E[]) new Object[(topOfStack + 1 - nullCount)];
        for (int i = 0; i < topOfStack + 1; i++) {
            if (dataOriginal[i] == null) {

            } else {
                dataModified[secondCount] = dataOriginal[i];
                secondCount++;
            }
        }
//        for(int i = 0; i<dataModified.length;i++) {
//            System.out.println(dataModified[i]);
//        }

//return dataOriginal;
        return dataModified;
    }
}
