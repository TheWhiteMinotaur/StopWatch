package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/***********************************************************************
 * 
 *   Implement the following methods and properties in StopWatch class.  
 *   For properties, you will need three instance variables: 
 *   minutes (integer), seconds (integer), milliseconds (integer). 
 *   For methods, you will need to implement the following 
 *   (include getters and setters as needed).  At this point, 
 *   assume all values of parameters are valid (i.e., within range) 
 *   numbers.  
 * 
 * @author Kevin Smith
 * @version 1.0
 *
 */
public class StopWatch {

	/**this represents the boolean flag that will later suspend the program */
	private static boolean flag;

	/** This represents minutes in the StopWatch object */
	private int minutes;

	/** This represents seconds in the StopWatch object */
	private int seconds;

	/** This represents milliseconds in the StopWatch object */
	private int milliseconds;


	public StopWatch() {
		super();
	}


	/***********************************************************************
	 * 
	 * A constructor that initializes the instance variables with the 
	 * provided values.
	 * 
	 * @param minutes uses to set the number of minutes in the object
	 * @param seconds uses to set the number of seconds in the object
	 * @param milliseconds uses to set the number of milliseconds in the 
	 * object
	 */

	public StopWatch(int minutes, int seconds, int milliseconds) {
		super();
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;

		if(minutes < 0 || seconds < 0 || milliseconds < 0)
			throw new IllegalArgumentException();
	}

	/***********************************************************************
	 * 	
	 * @param seconds uses to set the number of seconds in the object
	 * @param milliseconds uses to set the number of milliseconds 
	 * in the object
	 */
	public StopWatch(int seconds, int milliseconds) {
		super();
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/***********************************************************************
	 * 
	 * @param milliseconds uses to set the number of milliseconds
	 *  in the object
	 */
	public StopWatch(int milliseconds) {
		super();
		this.milliseconds = milliseconds;
	}

	/***********************************************************************
	 * StopWatch gets a starting time from the user.
	 * @param startTime 
	 */
	public StopWatch (String startTime) {

		String[] s = startTime.split(":");

		if (s.length == 3) {
			minutes=Integer.parseInt(s[0]);
			seconds = Integer.parseInt(s[1]);
			milliseconds = Integer.parseInt(s[2]);
		}

		else if (s.length == 2){
			seconds = Integer.parseInt(s[0]);
			milliseconds = Integer.parseInt(s[1]);
		}

		else if (s.length == 1){
			milliseconds = Integer.parseInt(s[0]);					
		}
		if(this.minutes < 0 || this.seconds < 0 || this.milliseconds < 0)
			throw new IllegalArgumentException();

	}

	/******************************************************************
	 * Checks to see if the StopWatch is equal to what is entered.
	 * @param other
	 * @return true if this and other times are equal to eachother
	 */
	public boolean equals (StopWatch other) {

		if(other.minutes < 0 || other.seconds < 0 || other.milliseconds < 0)
			throw new IllegalArgumentException();


		if ((this.minutes == other.minutes) &&
				(this.seconds == other.seconds) &&
				(this.milliseconds == other.milliseconds))
			return true;
		else 
			return false;


	}
	/******************************************************************
	 * Checks to see if the StopWatch is equal to what is entered.
	 */
	public boolean equals (Object other) {

		if(minutes < 0 || seconds < 0 || milliseconds < 0)
			throw new IllegalArgumentException();


		StopWatch temp;
		if (other instanceof StopWatch) {
			temp = (StopWatch) other;

			if ((this.minutes == temp.minutes) &&
					(this.seconds == temp.seconds) &&
					(this.milliseconds == temp.milliseconds))
				return true;
			else 
				return false;
		}

		return false;
	}

	/******************************************************************
	 * Checks to see if the StopWatch is equal to what is entered.
	 * @param s1
	 * @param s2
	 * @return true, or false
	 */
	public static boolean equals (StopWatch s1, StopWatch s2) {

		if(s1.equals(s2)){
			return true;
		}
		else 
			return false;

	}

	/******************************************************************
	 * StopWatch compares if "this" or "other" StopWatch is bigger.
	 * @param other
	 * @return 1 if true, -1 if false, or 0 if neither.
	 */
	public int compareTo (StopWatch other){

		if(other.minutes < 0 || other.seconds < 0 || other.milliseconds < 0)
			throw new IllegalArgumentException();


		if(this.minutes > other.getMinutes())
			return 1;
		else if( this.minutes < other.getMinutes())
			return -1;

		if(this.seconds > other.getSeconds())
			return 1;
		else if(this.seconds < other.getMinutes())
			return -1;

		if(this.milliseconds > other.getMilliseconds())
			return 1;
		else if(this.milliseconds < other.getMilliseconds())
			return -1;

		else{
			return 0;
		}

	}

	/******************************************************************
	 * StopWatch adds time to the timer.
	 * @param milliseconds
	 */
	public void add (int milliseconds){
		if(flag == true)
			return;

		int result;
		result = (minutes * 60000) + (seconds * 1000) + (this.milliseconds + milliseconds);
		minutes = result / 60000;
		seconds = result % 60000 /1000;
		this.milliseconds = result % 60000 % 1000;

		if(minutes < 0 || seconds < 0 || milliseconds < 0)
			throw new IllegalArgumentException();

	}

	/******************************************************************
	 * StopWatch adds time to the other timer.
	 * @param other
	 */
	public void add (StopWatch other){
		if(flag == true)
			return;

		int result;
		result = (other.minutes * 60000) + (other.seconds * 1000) + (other.milliseconds);
		add(result);

		if (!(other instanceof StopWatch))
			throw new IllegalArgumentException();

		if(other.minutes < 0 || other.seconds < 0 || other.milliseconds < 0)
			throw new IllegalArgumentException();
	}

	/******************************************************************
	 * StopWatch increments by 1.
	 */
	public void inc (){
		add(1);
	}

	/******************************************************************
	 * StopWatch increments milliseconds by 1.
	 * @param milliseconds
	 */
	public void sub(int milliseconds) {

		if(flag == true)
			return;



		int result;
		result = (((minutes * 60000) + (seconds * 1000) + (this.milliseconds)) - milliseconds);
		minutes = result / 60000;
		seconds = result % 60000 / 1000;
		this.milliseconds = result % 60000 % 1000;

		if(result < 0 || this.milliseconds < 0){
			throw new IllegalArgumentException();
		}

		if(milliseconds < 0){
			throw new IllegalArgumentException();
		}


		if(minutes < 0 || seconds < 0 || milliseconds < 0){
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	 * Subtracts one StopWatch from another StopWatch 
	 * @param other
	 */
	public void sub(StopWatch other) {
		if(flag == true)
			return;

		if(other.minutes < 0 || other.seconds < 0 || other.milliseconds < 0)
			throw new IllegalArgumentException();


		int result;
		result = (other.minutes * 60000) + (other.seconds) * 1000 + (other.milliseconds);
		sub(result);

	}

	/******************************************************************
	 * Decrements the StopWatch by 1 millisecond. 
	 */
	public void dec (){
		if(flag == true)
			return;

		this.milliseconds--;
		if(milliseconds == 1000){
			seconds --;
			milliseconds = 999	;
		}

	}

	/******************************************************************
	 * Organizes the integers into a readable form for the user. 
	 */
	public String toString() {
		String min = "";
		String sec = "";
		String milli = "";

		if(this.minutes < 10 ) {
			min = (this.minutes + ":");
		}
		else{
			min = (this.minutes + ":");
		}

		if(this.seconds < 10) {
			sec = ("0" + this.seconds+ ":");
		}
		else {
			sec = (this.seconds + ":");
		}
		if(this.milliseconds < 10) {
			milli = ("0" + "0" + this.milliseconds);
		}
		else if(this.milliseconds < 100) {
			milli = ("0" + this.milliseconds);
		}
		else {
			milli =("" +  this.milliseconds);
		}

		if(minutes < 0 || seconds < 0 || milliseconds < 0){
			throw new IllegalArgumentException();
		}

		return (min + sec + milli);

	}


	/******************************************************************
	 * Allows for the user to save a file
	 */

	public void save(String fileName) {

		PrintWriter save = null;
		// Saves the file under the "fileName"
		try {
			save = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		}
		// Throws an error
		catch (IOException e) {
			e.printStackTrace();
		}
		// Saves the minutes, seconds, and milliseconds of the timer to the file
		save.print(minutes + " " + seconds + " " + milliseconds);
		save.close();
	}


	/******************************************************************
	 * Load constructor - allows the user to load a saved file
	 * 
	 */

	public void load(String fileName) {

		try {
			// Reads in the minutes, seconds and milliseconds from the file
			Scanner input = new Scanner(new File(fileName));
			minutes = input.nextInt();
			seconds = input.nextInt();
			milliseconds = input.nextInt();

			System.out.println(toString());

		}
		// Gives an error if the fileName is not found
		catch (Exception error) {
			System.out.println("File Not Found: " + fileName);
			System.exit(1);
		}

	}


	/***********************************************************************
	 * If this method is used it halts all processes in the StopWatch.
	 * @param Flag
	 */
	public static void suspend(boolean Flag){
		flag = Flag;


	}



	/***********************************************************************
	 * Returns the minutes in StopWatch
	 * @return minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/***********************************************************************
	 * Sets the minutes in StopWatch
	 * @param minutes
	 */
	public void setMinutes(int minutes) {
		if (minutes > 0)
			this.minutes = minutes;

		if (minutes < 0)
			throw new IllegalArgumentException();
	}


	/***********************************************************************
	 * Returns the seconds in StopWatch
	 * @return seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/***********************************************************************
	 * Sets the seconds in StopWatch
	 * @param seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/***********************************************************************
	 * Returns the milliseconds in StopWatch
	 * @return milliseconds
	 */
	public int getMilliseconds() {
		return milliseconds;
	}

	/***********************************************************************
	 * Sets the milliseconds in StopWatch
	 * @param milliseconds
	 */
	public void setMilliseconds(int milliseconds) {
		if (milliseconds > 999)
			this.seconds += milliseconds / 1000;
		this.milliseconds = milliseconds % 999;

		if (milliseconds <= 999 && milliseconds >= 0)
			this.milliseconds = milliseconds;

		if (milliseconds < 0)
			throw new IllegalArgumentException();
	}


	//this.milliseconds = milliseconds;
}




