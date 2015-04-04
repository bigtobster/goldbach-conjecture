import java.lang.Long;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

/**
 * *****************NOTE***************
 * THIS ALGORITHM WILL NOT WORK ON ANY MACHINE CURRENTLY INVENTED AS IT STANDS!!!
 * MAYBE TRY LATER
 * MEMORY SPACE IS (GUESSING) 2 * ((2^63 -1) * 8) + ODDS AND ENDS...
 * Around 128 Zettabytes of Memory Space needed!
 **/

public class LongGoldbach
{
	public static void main(String args[])
	{
		longProof();
	}
	
	private static HashSet<Long> createPrimeSet(long max)
	{
		HashSet<Long> primeSet = new HashSet<Long>(1000000);
		primeSet.add(Long.valueOf(2));
		primeSet.add(Long.valueOf(3));
		primeSet.add(Long.valueOf(5));
		primeSet.add(Long.valueOf(7));
		for(int i = 2; i <= max; i++)
		{
			if(i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0)
			{
				primeSet.add(Long.valueOf(i));
			}
		}
		return primeSet;
	}
	
	private static void longProof()
	{
		long time = System.currentTimeMillis();
		HashSet<Long> primeSet = new HashSet<Long>();
		HashSet<Long> primeSumSet = new HashSet<Long>(1000);
		Long testNumber = new Long(2);
		long max = Long.valueOf(Long.MAX_VALUE);
		primeSet = createPrimeSet(max);
		//primeSet is an HashSet full of all primes between 2 and Integer.MAX_VALUE inclusive
		System.out.println("All Primes Found from 1 to " + max);
		
		for(Long myLong : primeSet)
		{
			primeSumSet.add(myLong + myLong);
		}
		HashSet<Long> set2 = new HashSet<Long>(primeSet);
		for(Long myLong : primeSet)
		{
			set2.remove(myLong);
			for(Long anotherLong : set2)
			{
				if((Long.MAX_VALUE - myLong) >= anotherLong)
				{
					primeSumSet.add(myLong + anotherLong);
				}
			}
		}
		System.out.println("Prime Sum Set Created");
		//We now have a HashSet of prime sums (primeSumSet)
		
		System.out.println("Testing Goldbach's Theory...");
		for(testNumber = Long.valueOf(4); testNumber <= max; testNumber = testNumber + Long.valueOf(4))
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
