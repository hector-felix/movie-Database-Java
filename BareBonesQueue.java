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
public interface BareBonesQueue {
    // We will have only 4 method here
    public void offer (Object obj);         // Adding Data
    public Object poll();                   // Removing Data
    public boolean isEmpty();               // Check if Empty
    public boolean isFull();                // Check if Full
    public Object peek();                   // Check the first Element
}
