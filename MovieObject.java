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

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MovieObject<E> {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
 /*
 * @author: Hector Felix
 * @Date: 2/21/19
 */
    
    //Each movie has a title, year of release, movie duration,movie genre and movie rating

    private int year;
    private int duration;
    private String genre;
    private String title;
    private double rating;
    DecimalFormat df = new DecimalFormat("#.##");
    private int count;
    private int genreCount;
    
    public MovieObject() {
        this.title = "";
        this.year = 0;
        this.duration = 0;
        this.rating = 0.0;
        this.genre = "";
    }
    
    public MovieObject(String title, int year, int duration, double rating, String genre) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
    }
    
    public MovieObject(String title, int year, int duration, double rating, String genre, int count, int genreCount) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        this.count = count;
        this.genreCount = genreCount;
    }
    
    public void setName(String name) {
        this.title = name;  
    }

    public String getTitle() {
        return title;        
    }
    
  

    public int getYear() {        
        return year;
    }
    
    public void setYear(int year) {        
        this.year = year;
    }
    
 

    public int getDuration() {
        return duration;
    }

    public String getGenre() {        
        return genre;
    }
    

    public void setRating(double rating) {        
        this.rating = rating;
    }

    public double getRating() {        
        return rating;
    }
    
    public int getCount() {
        return count;
    }
    
    public int getGenreCount() {
        return genreCount;
    }
    
    public void setGenreCount(int genreCount) {
        this.genreCount=genreCount;
    }
    
    public void setCount(int count) {
        this.count=count;
    }
    

//    //We will override toString
//    @Override
    public String toString() {
        
        String s = "";
        
        s = ("Title: " + this.title + " | Year: " + this.year
                + " | Genre: " + this.genre + " | Rating: " + this.rating
                + " | Duration: " + this.duration+'\n');

        return s;
    }
}

