package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.HashMap;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _05_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int nice = 0;

		for (String line : input.split(" ")) {
			HashMap<String, Integer> pairs = new HashMap<>();

			boolean pair = false;
			boolean xyx = false;

			char[] array = line.toCharArray();
			for (int i = 0; i < array.length; i++) {
				char current = array[i];

				if (i == 0) {
					continue;
				}

				char previous = array[i - 1];

				String currentPair = current + "" + previous;
				Integer previousPairs = pairs.get(currentPair);

				if (previousPairs == null) {
					previousPairs = 0;
				}

				if (i > 1) {
					char previousPrevious = array[i - 2];

					if (previousPrevious == previous) {
						continue;
					}
					
					if (previous != current && previousPrevious == current) {
						xyx = true;
					}
				}

				previousPairs++;
				pairs.put(currentPair, previousPairs);
				
				if (previousPairs == 2) {
					pair = true;
				}
			}

			if (pair && xyx) {
				nice++;
			}
		}

		return nice + "";
	}

	@Override
	public String toString() {
		return "Day 5, Part 2";
	}

}
