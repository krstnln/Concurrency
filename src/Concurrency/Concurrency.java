package Concurrency;

import java.util.Random;

public class Concurrency {
	
	public static void main(String[] args) {
	
		
		int len = 200000000; 		
		int numThreads = 2; 
		int threadSplit = len / numThreads;				
		int[] randArr = new int[len]; 
		Random randNum = new Random();

		for (int i = 0; i < randArr.length; i++)
			randArr[i] = randNum.nextInt(10) + 1; 
		
		System.out.println("The length of the array is " + len + ".");
							
	    long startTime = System.nanoTime(); 
	    System.out.println("The sum of the array is " + multiThread(randArr, threadSplit) + ".");
	    System.out.println("The solution using multiple threads was completed in " + (System.nanoTime() - startTime) + " nanoseconds.");
	    
	    startTime = System.nanoTime(); 
	    System.out.println("The sum of the array is " + singleThread(randArr) + ".");
	    System.out.println("The solution using a single thread was completed in " + (System.nanoTime() - startTime) + " nanoseconds.");	
	}
	
	
	public static int multiThread(int randArr[], int threadSplit) {
		int[] sum1 = {0};
		int[] sum2 = {0};

		Thread t1 = new Thread(new Runnable() {			
			public void run() {
				for (int i = 0; i < randArr[threadSplit]; i++) 
					sum1[0] = sum1[0] + randArr[i];	
			}					
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {				
				for (int j = randArr[threadSplit]; j < randArr.length; j++) 
					sum2[0] = sum2[0] + randArr[j];	
			}			
		});

		t1.start();		
		t2.start();

		try {			
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
			
		return (sum1[0] + sum2[0]);
	}
	
	
	public static int singleThread(int randArr[]) {
		int sum = 0;
		
		for (int i = 0; i < randArr.length; i++) 
			sum = sum + randArr[i];
		
		return sum;
	}
}


