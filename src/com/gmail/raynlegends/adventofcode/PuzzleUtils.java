package com.gmail.raynlegends.adventofcode;

public class PuzzleUtils {

	public static int smallest(int... ints) {
		int smallest = Integer.MAX_VALUE;
		
		for (int current : ints) {
			if (current < smallest) {
				smallest = current;
			}
		}
		
		return smallest;
	}

	public static int countTrue(boolean... booleans) {
		int count = 0;
		
		for (boolean current : booleans) {
			if (current) {
				count++;
			}
		}
		
		return count;
	}
	
}
