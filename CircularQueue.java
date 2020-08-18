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
public class CircularQueue implements BareBonesQueue {
    
    // Data
    private Object[] Q;                             // Array reference for the actual Queue
    private int front;                              // Front of the queue
    private int rear;                               // Rear of the queue
    private int size;                               // Number of elements in the queue
    private int capacity;                           // Maximum elements in the queue
    private final int DEFAULT_CAPACITY=30;           // For default constructor - default capacity
    
    // Contructor
    public CircularQueue() {
        Q = new Object[this.DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = this.DEFAULT_CAPACITY;
    }
    
    // Overloaded Contructor
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        Q = new Object[this.capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public void offer(Object obj) {
        // This Method Adds Elements to the Queue
        if(this.isFull()) {                         // The Queue is Full
            System.out.println("The Queue is Full. Cannot Insert!");
            return;
        }
        
        // There is space to insert data
        // Insert at the Rear
        Q[rear] = obj;                              // The dats is inserted
        rear = (rear + 1) % capacity;               // Update the rear for the next data
        size++;                                     // Update the number of elements in the queue                  
    }

    @Override
    public Object poll() {
        // This Method Deletes an Element from the Queue
        // Check if there is an Element to be Deleted
        if(isEmpty()) {                             // If there is nothing in the Queue
            return null;
        }
        
        // There is some element to delete in the Queue
        Object tmp = Q[front];                      // Save the element that will be deleted to return
        Q[front] = null;                            // Doing something
        front = (front + 1) % capacity;             // Update the front, don't forget mod
        size--;                                     // Update size
        return tmp;                                 // Return the Deleted Element
    }

    @Override
    public boolean isEmpty() {
        return(size == 0);
    }

    @Override
    public boolean isFull() {
        return (size == capacity);
    }

    @Override
    public Object peek() {
    return Q[front];             
    }
    
    // Method to Display the Contents of the Queue
    public String toString() {
        Object[] newArray = checkDup(Q);
        String s = "\n";
        // We will loop over the actual elements in the Queue
//        for (int i = front; i < front + size; i++) {
//            s += Q[i % capacity] + " | ";                               // Point to the actual data, use mod
//        }

        for (int i = 0; i < newArray.length; i++) {
//         if(Q[0]==null)
//             break;
            s+= newArray[i];
            //s += Q[i] + " | ";                               // Point to the actual data, use mod
            //s += Q[i % capacity] + " | ";  
        }
        return s;
    }
    
    public Object[] checkDup(Object[] dataOriginal) {
        int nullCount = 0;

//        for (int j = 0; j < dataOriginal.length - 1; j++) {
//            for (int i = j + 1; i < dataOriginal.length - 1; i++) {
//                if (dataOriginal[j] == dataOriginal[i]) {
//                    dataOriginal[i] = null;
//                    //nullCount++;
//                }
//            }
//        }
//        dataOriginal[dataOriginal.length - 1] = null;

        for (int i = 0; i < rear; i++) {
            if (dataOriginal[i] == null) {
                nullCount++;
            }
        }

        //System.out.println("Null count: "+nullCount);
        int secondCount = 0;
        
        Object[] dataModified =  new Object[(rear - nullCount)];
        for (int i = 0; i < rear + 1; i++) {
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
