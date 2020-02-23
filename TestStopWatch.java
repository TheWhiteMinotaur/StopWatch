package project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestStopWatch {

	/**
	 *
	 * The following are simple random JUnit test cases... After talking with your 
	 * instructor, create many, many more that shows that your code 
	 * is functioning correctly.
	 *
	 */	

	@Test
	public void testConstructor() {
		StopWatch s = new StopWatch (5,10,300);
		assertEquals(s.toString(),"5:10:300");

		s = new StopWatch("20:10:8");
		assertEquals(s.toString(),"20:10:008");

		s = new StopWatch("20:8");
		assertEquals(s.toString(),"0:20:008");

		s = new StopWatch("8");
		assertEquals(s.toString(),"0:00:008");

	}

	// There can only be one test here
	// no more lines of code after "new StopWatch("-2");"
	@Test (expected = IllegalArgumentException.class)
	public void testNegSingleInput() {
		new StopWatch("-2");

	}

	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble1Input() {
		new StopWatch("-59:-23");

	}

	// To the students of CIS163: is this a good test?
	@Test 
	public void testAlotInput() {
		for (int m = 0; m < 50; m++)
			for (int s = 0; s < 60; s++)
				for (int ms = 0; ms < 1000; ms++) {
					String st = m + ":" + s + ":" + ms;
					StopWatch d = new StopWatch(st);
					assertEquals(m, d.getMinutes());
					assertEquals(s, d.getSeconds());
					assertEquals(ms, d.getMilliseconds());					
				}
	}


	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2aInput() {
		new StopWatch("2:-2");

	}

	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2bInput() {
		new StopWatch("-2:7");

	}

	@Test (expected = IllegalArgumentException.class)
	public void testAlphaInput() {
		new StopWatch("a");

	}

	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2cInput() {
		new StopWatch("-2:-7");

	}

	@Test
	public void testAddMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.add(2000);
		assertEquals (s1.toString(),"6:01:300");

		s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.add(s2);
		System.out.println (s1);
		assertEquals (s1.toString(),"8:01:600");

		for (int i = 0; i < 15000; i++)
			s1.inc();
		System.out.println (s1);
		assertEquals (s1.toString(),"8:16:600");
	}


	@Test (expected = IllegalArgumentException.class)
	public void testContuctor() {
		new StopWatch("2,-3,-3");

	}

	@Test 
	public void testEqual () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));

		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);

	}
	@Test 
	public void testCompareTo () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));

		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);

	}

	@Test 
	public void testLoadSave () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,59,300);
		StopWatch s3 = new StopWatch (7,59,300);

		s1.save("file1");
		//s1 = new StopWatch ();  // resets to zero
		s2.save("file2");
		s3.save("file3");

		s1.load("file1");
		s2.load("file2");
		s3.load("file3");
		//assertTrue (s1.equals(s2));
		assertTrue(s1.equals(s1));
		assertTrue(s2.equals(s2));
		assertTrue(s3.equals(s3));
	}

	@Test 
	public void testMutate () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		StopWatch.suspend(true);
		s1.add(1000);
		assertTrue (s1.equals(s2));	
		StopWatch.suspend(false);
	}

	@Test 
	public void equalsTest() {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		assertEquals(s1, s2);
	}


	@Test (expected = IllegalArgumentException.class)
	public void testAddMethodneg () {
		StopWatch s3 = new StopWatch (5,59,300);
		s3.add(-2000);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddNegOther () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,-2,300);
		s1.add(s2);
		assertEquals (s1.toString(),"8:01:600");

	}

	// Checks if the timer will accept letters.
	@Test (expected = IllegalArgumentException.class)
	public void testLettersKS(){
		StopWatch s1 = new StopWatch ("d:h5:lll");

	}

	// Checks if the timer will take a backwards input.
	@Test
	public void testBackWardsKS(){
		StopWatch s1 = new StopWatch ("555:26:2");

	}

	// Checks if the timer can adjust to many minutes.
	@Test
	public void testLotsOfMinutesKS(){
		StopWatch s4 = new StopWatch (5,9,300);
		s4.add(600000000);
		assertEquals (s4.toString(),"10005:09:300");
	}

	// Checks if the timer will set back to a specified time.
	@Test
	public void testSetMinutesKS() {
		StopWatch s1 = new StopWatch(5,59,300);
		s1.setMinutes(2);
		assertEquals(s1.toString(), "2:59:300");

	}

	// Checks if the timer will set three parameters.
	@Test
	public void testSetMinutesAndSecondsAndMillisecondsKS() {
		StopWatch s1 = new StopWatch(5,59,300);
		s1.setMinutes(2);
		s1.setSeconds(0);
		s1.setMilliseconds(0);
		assertEquals(s1.toString(), "2:00:000");

	}

	// Checks if the timer will subtract two StopWatches
	@Test (expected = IllegalArgumentException.class)
	public void testSubStopWatchKS() {
		StopWatch s1 = new StopWatch(1,2,100);
		StopWatch s2 = new StopWatch(5,59,300);
		s1.sub(s2);
	}

	// Checks if the timer will add milliseconds.
	@Test
	public void testAddMillisecondsKS(){
		StopWatch s1 = new StopWatch(9);
		s1.add(10);
		assertEquals(s1.toString(), "0:00:019");
	}

	// Checks if the timer will add seconds.
	@Test
	public void testAddSecondsKS(){
		StopWatch s1 = new StopWatch(2,0,0);
		s1.add(1000);
		assertEquals(s1.toString(), "2:01:000");
	}

	// Checks if the timer will add minutes.
	@Test
	public void testAddMinutesKS(){
		StopWatch s1 = new StopWatch(2,0,0);
		s1.add(60000);
		assertEquals(s1.toString(), "3:00:000");
	}

	// Checks if the timer can save many different files and load them.
	@Test 
	public void testSaveAndLoadManyKS () {
		StopWatch s1 = new StopWatch (5,0,0);
		String file = "";
		int temp = 1;
		for(int i = 1; i <= 10; i++){
			s1.add(60000);
			s1.save("file");
			System.out.println("Save file" + " " + temp + ": " + s1);
			System.out.print("Load file " + temp + ": ");
			s1.load("file");
			temp++;
		}
	}

	// Checks if the timer will subtract only milliseconds.
	@Test
	public void testSubSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(5,59,100);
		s1.sub(200);
		assertTrue(s1.equals(s2));
	}

	// Checks if the timer will subtract a negative.
	@Test(expected = IllegalArgumentException.class)
	public void testSubInvalidSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(5,59,500);
		s1.sub(-200);
		assertTrue(s1.equals(s2));
	}

	// Checks if the timer will subtract more than what is stored.
	@Test(expected = IllegalArgumentException.class)
	public void testSubTooMuchSW() {
		StopWatch s1 = new StopWatch(300);
		s1.sub(500);
	}

	// Checks if the timer will increment by one millisecond.
	@Test 
	public void testIncSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(5,59,301);
		s1.inc();
		assertTrue(s1.equals(s2));
	}

	// Checks if the timer will decrement by one millisecond.
	@Test 
	public void testDecSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(5,59,299);
		s1.dec();
		assertTrue(s1.equals(s2));
	}

	// Checks if the timer will add two StopWatches together.
	@Test
	public void testAddStopWatchSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(1,2,100);
		s1.add(s2);
		assertEquals(s1.toString(), "7:01:400");
	}

	// Checks if the timer will subtract two StopWatches together.
	@Test
	public void testSubStopWatchSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		StopWatch s2 = new StopWatch(1,2,100);
		s1.sub(s2);
		assertEquals(s1.toString(), "4:57:200");
	}

	// Checks if the timer will set only milliseconds.
	@Test
	public void testSetMillisecondsSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		s1.setMilliseconds(0);
		assertEquals(s1.toString(), "5:59:000");
	}

	//Checks if the timer will set Seconds.
	@Test
	public void testSetSecondsSW() {
		StopWatch s1 = new StopWatch(5,59,300);
		s1.setSeconds(25);
		assertEquals(s1.toString(), "5:25:300");
	}

	// Checks to make sure the toString() works properly by setting
	//it to 0.
	@Test
	public void testToStringMsSW() {
		StopWatch s1 = new StopWatch(0);
		assertEquals(s1.toString(), "0:00:000");
	}

	// Checks if the timer will display abc.
	@Test(expected = IllegalArgumentException.class)
	public void testToStringInvalidSW() {
		StopWatch s1 = new StopWatch("abc");
	}

	// Checks if the timer will display a negative StopWatch. 
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeStringSW() {
		StopWatch s1 = new StopWatch(-5,-59,-300);
	}


}
