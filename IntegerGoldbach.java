import java.lang.Long;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

/**
 * *****************NOTE***************
 * THIS ALGORITHM WILL NOT WORK ON THIS LAPTOP
 * MAYBE TRY LATER
 * MEMORY SPACE IS (GUESSING) 2 * ((2^31 -1) * 8) + ODDS AND ENDS...
 * Around 32 Gigabytes of Memory Space needed!
 **/
 
public class IntegerGoldbach
{
	public static void main(String args[])
	{
		integerProof();
	}
	
	private static HashSet<Integer> createPrimeSet(int max)
	{
		HashSet<Integer> primeSet = new HashSet<Integer>(1000);
		primeSet.add(2);
		primeSet.add(3);
		primeSet.add(5);
		primeSet.add(7);
		for(int i = 2; i <= max; i++)
		{
			if(i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0)
			{
				primeSet.add(i);
			}
		}
		return primeSet;
	}
	
	private static void integerProof()
	{
		long time = System.currentTimeMillis();
		HashSet<Integer> primeSet = new HashSet<Integer>();
		HashSet<Integer> primeSumSet = new HashSet<Integer>(1000);
		Integer testNumber = new Integer(2);
		int max = Integer.MAX_VALUE;
		primeSet = createPrimeSet(max);
		//primeSet is an HashSet full of all primes between 2 and Integer.MAX_VALUE inclusive
		System.out.println("All Primes Found from 1 to " + max);
		
		for(Integer myInt : primeSet)
		{
			primeSumSet.add(myInt + myInt);
		}
		HashSet<Integer> set2 = new HashSet<Integer>(primeSet);
		for(Integer myInt : primeSet)
		{
			set2.remove(myInt);
			for(Integer anotherInt : set2)
			{
				if((Integer.MAX_VALUE - myInt) >= anotherInt)
				{
					primeSumSet.add(myInt + anotherInt);
				}
			}
		}
		System.out.println("Prime Sum Set Created");
		//We now have a HashSet of prime sums (primeSumSet)
		System.out.println("Testing Goldbach's Theory...");
		for(testNumber = 4; testNumber <= max; testNumber = testNumber + 2)
		{
			if(!(primeSumSet.contains(testNumber)))
			{
				System.out.println("Goldbach was wrong!");
				System.out.println(testNumber + " cannot be made from the sum of 2 primes");
				System.exit(1);
			}
		}
		System.out.println("Goldbach's Conjecture Proven up to (and including) " + max);
		time = System.currentTimeMillis() - time;
		System.out.println("Operation Time: " + Double.valueOf(time/1000) + " seconds");
	}
}
