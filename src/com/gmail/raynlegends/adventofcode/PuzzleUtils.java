package com.gmail.raynlegends.adventofcode;

import java.util.LinkedList;

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

	public static interface CombinationChecker<T> {

		public boolean check(LinkedList<LinkedList<T>> combinations, LinkedList<T> combination);

	}

	/**
	 * Calculates combinations of entries.
	 * A combination's length is variable,
	 * depends on the CombinationChecker
	 * 
	 * @param endChecker - Must return true if the combination exceeds a "limit"
	 * @param repeating - Could values repeat in the same combination?
	 * @param entries - The values to combine
	 * @param finalChecker - The finalChecker - Null = No checker
	 * @return The list of combinations found
	 */
	public static <T> LinkedList<LinkedList<T>> calculateCombinations(CombinationChecker<T> endChecker, boolean repeating, LinkedList<T> entries, CombinationChecker<T> finalChecker) {
		if (endChecker == null) {
			throw new IllegalArgumentException("The endChecker must be defined!");
		}

		LinkedList<LinkedList<T>> combinations = new LinkedList<>();
		calculateCombinations(endChecker, repeating, entries, combinations, new LinkedList<T>(), finalChecker);
		return combinations;
	}

	/**
	 * Calculates combinations of entries.
	 * A combination's length is complete
	 * when it reaches the limit
	 * 
	 * @param limit - The size of each combination
	 * @param repeating - Could values repeat in the same combination?
	 * @param entries - The values to combine - Can't be null
	 * @param finalChecker - The finalChecker - Null = No checker
	 * @return The list of combinations found
	 */
	public static <T> LinkedList<LinkedList<T>> calculateCombinations(final int limit, boolean repeating, LinkedList<T> entries, CombinationChecker<T> finalChecker) {
		if (limit < 0) {
			throw new IllegalArgumentException("The limit must be a positive number!");
		}

		return calculateCombinations(new CombinationChecker<T>() {

			@Override
			public boolean check(LinkedList<LinkedList<T>> combinations, LinkedList<T> combination) {
				return combination.size() >= limit;
			}

		}, repeating, entries, finalChecker);
	}

	private static <T> void calculateCombinations(CombinationChecker<T> endChecker, boolean repeating, LinkedList<T> entries, LinkedList<LinkedList<T>> combinations, LinkedList<T> currentCombination, CombinationChecker<T> finalChecker) {
		if (currentCombination != null && endChecker.check(combinations, currentCombination)) {
			if (finalChecker != null) {
				if (!finalChecker.check(combinations, currentCombination)) {
					return;
				}
			}
			combinations.add(currentCombination);
			return;
		}

		for (T entry : entries) {
			if (!repeating && currentCombination.contains(entry)) {
				continue;
			}

			LinkedList<T> newCurrentCombination = new LinkedList<>();
			for (T otherEntry : currentCombination) {
				newCurrentCombination.add(otherEntry);
			}
			newCurrentCombination.add(entry);
			calculateCombinations(endChecker, repeating, entries, combinations, newCurrentCombination, finalChecker);
		}
	}

	public static interface CombinationValidator<T> {

		/**
		 * Checks the current combination.
		 * To iterate through the real values:
		 * <br />
		 * <pre>
		 * 	for (int z = 0; z < combination.length; z++) {
		 * 		T current = elements[combination[z]];
		 * 	}
		 * </pre>
		 * 
		 * @param elements - The initial elements
		 * @param combination - The raw combination
		 * @return true if it should be added to the final count
		 */
		public boolean check(T[] elements, int[] combination);

	}

	/**
	 * Calculates the amount of valid combinations.
	 * 
	 * @param elements - The elements to combine
	 * @param limit - The size of the combination
	 * @param validator - See {@link CombinationValidator#check(Object[], int[])}
	 * @return The amount of valid combinations
	 */
	public static <T> int validCombinationsAmount(T[] elements, int limit, CombinationValidator<T> validator){
		int elementsCount = elements.length;

		if (limit > elementsCount){
			return 0;
		}

		int r = 0;		
		int index = 0;
		int combination[] = new int[limit];
		
		int validCombinations = 0;
		while (r >= 0) {
			if (index <= (elementsCount + (r - limit))) {
				combination[r] = index;

				if (r == limit - 1) {
					if (validator.check(elements, combination)) {
						validCombinations++;
					}

					index++;				
				} else {
					index = combination[r] + 1;
					r++;										
				}
			} else {
				r--;
				
				if (r > 0) {
					index = combination[r] + 1;
				} else {
					index = combination[0] + 1;
				}
			}			
		}

		return validCombinations;
	}

	/**
	 * Calculates the amount of total combinations.
	 * 
	 * @param elements - The number of elements to combine
	 * @param limit - The size of the combination
	 * @return The amount of total combinations
	 */
	public static int totalCombinationsAmount(int elementsCount, int limit) {
		int elementsCountFactorial = factorial(elementsCount);
		int limitFactorial = factorial(limit);
		int nrf = factorial(elementsCount - limit);
		int npr = elementsCountFactorial / nrf;
		int combinations = npr / limitFactorial; 

		return combinations;
	}

	private static int factorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

}
