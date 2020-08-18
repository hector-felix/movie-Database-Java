/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project02;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

/**9
 * 
 *
 * @author Hector Felix
 */
public class makeMovies {

    public ArrayStack<MovieObject> stackWatchedMovieList = new ArrayStack<>();
    public CircularQueue queueWatchedMovieList = new CircularQueue();
    public ArrayList<MovieObject> movieList = new ArrayList<>();
    public ArrayList<MovieObject> finalWatchedList = new ArrayList<>();
    public ArrayList<MovieObject> titleList = new ArrayList<>();
    public ArrayList<MovieObject> genreList = new ArrayList<>();

    private final int MAX_MOVIES = 20;

    public void menu() {
        makeObjects();
        //System.out.println(movieList);

        int watchCount = 0;
        int genreWatchCount = 0;
        int option;
        String line, user = null, pass = null, username = null, password = null, firstName = null, lastName = null;
        BufferedReader bufferedReader;
        
        boolean login = false;
        int tryCount = 0;
        String filePath = "/Users/mac/Desktop/usernames.txt";

        Scanner s = new Scanner(System.in);
        try {
            do {

                System.out.println("\n1) Register User\n"
                        + "2) Login User\n"
                        + "3) Watch a Movie - Search by Title or Genre\n"
                        + "4) All Movies Listed in the Order they Were Watched\n"
                        + "5) All Movies in Reverse Chronological Order\n"
                        + "6) Number of Times Each Movie is Watched\n"
                        + "7) Most Watched Movie\n"
                        + "8) Most Watched Genre\n"
                        + "9) Logout");
                System.out.print("Please Select an Option: ");

                option = s.nextInt();

                switch (option) { //selection sort to sort years, pages, and ratings
                    case 1:
                        try {
                            FileWriter fstream = new FileWriter(filePath, true);
                            BufferedWriter out = new BufferedWriter(fstream);
                            bufferedReader = new BufferedReader(new FileReader(filePath));

                            login = false;
                            tryCount=0;
                            System.out.println("New User Registration\n----------------------\nEnter your First Name: ");
                            firstName = s.next();
                            System.out.println("Enter your Last Name: ");
                            lastName = s.next();
                            System.out.println("Enter your Username: ");
                            username = s.next();
                            System.out.println("Confirming Username...");

                            while ((line = bufferedReader.readLine()) != null) {
                                if (line.contains(".")) {
                                    String[] parts = line.split("\\.");
                                    user = parts[0].toLowerCase();
                                    
                                        while (user.equals(username) && tryCount < 2) {
                                            System.out.println(username + " Already In Use. Please Select Another Username: ");
                                            username = s.next();
                                            tryCount++;
                                        }
                                        if (user.equals(username)) {
                                            username = (lastName + ((int) (Math.random() * ((99999999 - 10000000) + 0)) + 10000000));
                                            System.out.println("Username " + username);
                                        }
                                        

                                        
                                    
                                    pass = parts[1].toLowerCase();
                                }
                            }

//                            if (user != null) {
//                                while (user.equals(username) && tryCount < 2) {
//                                    System.out.println(username + " Already In Use. Please Select Another Username: ");
//                                    username = s.next();
//                                    tryCount++;
//                                }
//                                if (user.equals(username)) {
//                                    username = (lastName + ((int) (Math.random() * ((99999999 - 10000000) + 0)) + 10000000));
//                                    System.out.println("Username " + username);
//                                }
//                                out.write('\n');
//                            }

                            System.out.println("Enter your Password(8 Character Min.): ");
                            password = s.next();
                            while (password.length() < 8) {
                                System.out.println("Password Must be at Least 8 Characters Long. Please select a New Password:");
                                password = s.next();
                            }
                            
                            if(user!=null)
                                out.write('\n');

                            out.write(username);
                            out.write(".");
                            out.write(password);

                            System.out.println("\n"+ username + " Registered and Logged In!");
                            login = true;
                           

                            while (!queueWatchedMovieList.isEmpty()) {
                                queueWatchedMovieList.poll();
                            }

                            while (!stackWatchedMovieList.isEmpty()) {
                                stackWatchedMovieList.pop();
                            }

                            out.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }

                        break;

                    case 2:
                        System.out.println("Enter your username: ");
                        username = s.next();
                        System.out.println("Enter your Password: ");
                        password = s.next();
                        System.out.println("Confirming Username...");

                        try {
                            bufferedReader = new BufferedReader(new FileReader(filePath));

                            login = false;
                            while ((line = bufferedReader.readLine()) != null) {
                                if (line.contains(".")) {
                                    String[] parts = line.split("\\.");
                                    user = parts[0].toLowerCase();
                                    pass = parts[1].toLowerCase();
                                }

                                if (user.equals(username) && pass.equals(password)) {
                                    System.out.println("\n"+user + " is logged in!");
                                    login = true;
                                   
                                }
                            }
                            if (!login) {
                                System.out.println("Username and Password Combination NOT Found! Try Registering or Login Again...");
                            }

                            while (!queueWatchedMovieList.isEmpty()) {
                                queueWatchedMovieList.poll();
                            }

                            while (!stackWatchedMovieList.isEmpty()) {
                                stackWatchedMovieList.pop();
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }

                        System.out.println("Would you like to Search by Title(Press 1) or Genre(Press 2)?");
                        int choice = s.nextInt();
                        while (choice != 1 && choice != 2) {
                            System.out.println("Invalid Selection. Try Again: ");
                            choice = s.nextInt();
                        }

                        if (choice == 1) {
                            do {
                                System.out.println("Please Enter Title to Search For: ");
                                String tSearch = s.next();

                                for (int i = 0; i < movieList.getSize(); i++) {
                                    if (movieList.get(i).getTitle().toUpperCase().contains(tSearch.toUpperCase())) {
                                        titleList.add(movieList.get(i));
                                    }
                                }
                                if (titleList.getSize() <= 0) {
                                    System.out.println("No Match Found - Return to Main Menu(Press 1) or Search Again(Press 2)?: ");
                                    int secondChoice = s.nextInt();
                                    if (secondChoice == 1) {
                                        break;
                                    } else if (secondChoice == 2) {
                                        continue;
                                    }
                                } else {
                                    System.out.println(titleList);

                                    System.out.println("Please Select Movie by Number to Watch: ");
                                    int movieChoice = s.nextInt();
                                    while (movieChoice <= 0 || movieChoice > titleList.getSize()) {
                                        System.out.println("Please Make a Valid Selection: ");
                                        movieChoice = s.nextInt();
                                    }

                                    queueWatchedMovieList.offer(titleList.get(movieChoice - 1));
                                    stackWatchedMovieList.push(titleList.get(movieChoice - 1));
                                    finalWatchedList.add(titleList.get(movieChoice - 1));

                                }
                            } while (titleList.getSize() <= 0);
                        } else if (choice == 2) {
                            do {
                                System.out.println("Please Enter Genre to Search For: ");
                                String gSearch = s.next();

                                for (int i = 0; i < movieList.getSize(); i++) {
                                    if (movieList.get(i).getGenre().toUpperCase().contains(gSearch.toUpperCase())) {
                                        genreList.add(movieList.get(i));
                                    }
                                }
                                if (genreList.getSize() <= 0) {
                                    System.out.println("No Match Found - Return to Main Menu(Press 1) or Search Again(Press 2)?: ");
                                    int secondChoice = s.nextInt();
                                    if (secondChoice == 1) {
                                        break;
                                    } else if (secondChoice == 2) {
                                        continue;
                                    }
                                } else {
                                    System.out.println(genreList);

                                    System.out.println("Please Select Movie by Number to Watch: ");
                                    int movieChoice = s.nextInt();
                                    while (movieChoice <= 0 || movieChoice > genreList.getSize()) {
                                        System.out.println("Please Make a Valid Selection: ");
                                        movieChoice = s.nextInt();
                                    }

                                    queueWatchedMovieList.offer(genreList.get(movieChoice - 1));
                                    stackWatchedMovieList.push(genreList.get(movieChoice - 1));
                                    finalWatchedList.add(genreList.get(movieChoice - 1));

                                }
                            } while (genreList.getSize() <= 0);
                        }
                        titleList.clear();
                        genreList.clear();
                        break;

                    case 4:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }
                        System.out.println(queueWatchedMovieList);

                        break;
                    case 5:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }
                        System.out.println(stackWatchedMovieList);

                        break;
                    case 6:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }
                        
                        System.out.println("\nWatch Counts per Movie\n----------------------");

                        for (int i = 0; i < movieList.getSize(); i++) {
                            for (int j = 0; j < finalWatchedList.getSize(); j++) {
                                if (movieList.get(i).equals(finalWatchedList.get(j))) {
                                    watchCount++;
                                    movieList.get(i).setCount(watchCount);
                                }
                            }
                            System.out.println("Watched " + watchCount + " times --- " + movieList.get(i).getTitle());
                            watchCount = 0;
                        }
                        break;
                    case 7:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }
                        for (int i = 0; i < movieList.getSize(); i++) {
                            for (int j = 0; j < finalWatchedList.getSize(); j++) {
                                if (movieList.get(i).equals(finalWatchedList.get(j))) {
                                    watchCount++;
                                    movieList.get(i).setCount(watchCount);
                                }
                            }
                            //System.out.println("Watched " + watchCount + " times --- " + movieList.get(i).getTitle());
                            watchCount = 0;
                        }

                        int max = 0;
                        int maxElement = 0;
                        for (int i = 0; i < movieList.getSize(); i++) {
                            if (movieList.get(i).getCount() > max) {
                                max = movieList.get(i).getCount();
                                maxElement = i;
                            }
                        }

                        //System.out.println("Max: "+max);
                        System.out.println("\nMovie with Most Views:\n----------------------");
                        System.out.print(max + " Views - " + movieList.get(maxElement));

                    default:
                        break;
                    case 8:
                        if (!login) {
                            System.out.println("\nPlease Login or Register to Begin Watching Movies");
                            break;
                        }
                        int maxGenre = 0;
                        int maxGenreElement = 0;

                        for (int i = 0; i < movieList.getSize(); i++) {
                            for (int j = 0; j < finalWatchedList.getSize(); j++) {
                                if (movieList.get(i).getGenre().equals(finalWatchedList.get(j).getGenre())) {
                                    genreWatchCount++;
                                    movieList.get(i).setGenreCount(genreWatchCount);
                                }
                            }
//                        System.out.println("Watched " + watchCount + " times --- " + movieList.get(i).getTitle());
                            genreWatchCount = 0;
                        }

                        for (int i = 0; i < movieList.getSize(); i++) {
                            if (movieList.get(i).getGenreCount() > maxGenre) {
                                maxGenre = movieList.get(i).getGenreCount();
                                maxGenreElement = i;
                            }
                        }

                        System.out.println("\nGenre with Most Views:\n-----------------------");
                        System.out.print(maxGenre + " Views - " + movieList.get(maxGenreElement).getGenre() + "\n");

                        break;
                    case 9:
                        System.out.println("Thanks for Watching!!!");
                        break;
                }
            } while (option != 9);
        } catch (InputMismatchException | NumberFormatException ex) {

        }
    }

    public void makeObjects() {

        MovieObject m0 = new MovieObject("Mission Impossible", 1996, 121, 9.0, "Action");
        MovieObject m1 = new MovieObject("Mission Impossible 2", 2000, 122, 8.5, "Action");
        MovieObject m2 = new MovieObject("Mission Impossible III", 2006, 123, 8.0, "Action");
        MovieObject m3 = new MovieObject("Mission Impossible - Ghost Protocol", 2011, 124, 7.5, "Action");
        MovieObject m4 = new MovieObject("Mission Impossible - Rogue Nation", 2015, 125, 7.0, "Action");
        MovieObject m5 = new MovieObject("Mission Impossible - Fallout", 2018, 126, 6.5, "Action");

        MovieObject m6 = new MovieObject("Goodfellas", 1990, 148, 8.7, "Suspense");
        MovieObject m7 = new MovieObject("Shawshank Redemption", 1994, 122, 9.3, "Suspense");
        MovieObject m8 = new MovieObject("The Usual Suspects", 1995, 108, 8.6, "Suspense");
        MovieObject m9 = new MovieObject("Shutter Island", 2010, 119, 8.1, "Suspense");
        MovieObject m10 = new MovieObject("Taken", 2008, 93, 7.8, "Suspense");

        MovieObject m11 = new MovieObject("Despicable Me", 2010, 95, 7.7, "Comedy");
        MovieObject m12 = new MovieObject("Despicable Me 2", 2013, 98, 7.4, "Comedy");
        MovieObject m13 = new MovieObject("Despicable Me 3", 2017, 96, 6.3, "Comedy");
        MovieObject m14 = new MovieObject("Anchorman", 2004, 104, 7.2, "Comedy");
        MovieObject m15 = new MovieObject("The Hangover", 2009, 108, 7.7, "Comedy");
        MovieObject m16 = new MovieObject("Knocked Up", 2007, 128, 7.0, "Comedy");

        MovieObject m17 = new MovieObject("The Conjuring", 2018, 148, 8.8, "Horror");
        MovieObject m18 = new MovieObject("Get Out", 2018, 129, 7.9, "Horror");
        MovieObject m19 = new MovieObject("The Exorcist", 2018, 111, 6.8, "Horror");
        MovieObject m20 = new MovieObject("Halloween", 2018, 89, 6.0, "Horror");

        movieList.add(m0);
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);
        movieList.add(m7);
        movieList.add(m8);
        movieList.add(m9);
        movieList.add(m10);
        movieList.add(m11);
        movieList.add(m12);
        movieList.add(m13);
        movieList.add(m14);
        movieList.add(m15);
        movieList.add(m16);
        movieList.add(m17);
        movieList.add(m18);
        movieList.add(m19);
        movieList.add(m20);
    }
}
