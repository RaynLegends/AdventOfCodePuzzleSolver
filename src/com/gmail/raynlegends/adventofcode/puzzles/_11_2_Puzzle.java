package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _11_2_Puzzle implements Puzzle {

	char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	char[] forbidden = {'i', 'o', 'l'};

	@Override
	public String calculate(String input) {
		String current = input;

		int[] ids = new int[8];
		char[] array = input.toCharArray();
		for (int i = 0; i < array.length; i++) {
			ids[i] = getId(array[i]);
		}

		int found = 0;
		while(found < 2) { // Since we have to calculate the next entry without having the one before
			StringBuilder builder = new StringBuilder();
			
			ids[7]++;
			for (int i = 7; i >= 0; i--) {
				int id = ids[i];

				if (id + 1 > letters.length) {
					ids[i] = id - letters.length;

					if (i - 1 >= 0) {
						ids[i - 1]++;
					}
				}

				builder.insert(0, letters[ids[i]]);
			}

			current = builder.toString();

			if (forbidden(current)) {
				continue;
			}
			
			if (check(current)) {
				found++;
			}
		}

		return current + "";
	}

	private int getId(char character) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == character) {
				return i;
			}
		}
		return -1;
	}

	private boolean check(String current) {
		boolean sequence = sequence(current);
		boolean pairs = pairs(current);
		
		return sequence && pairs;
	}

	private boolean forbidden(String current) {
		for (int i = 0; i < forbidden.length; i++) {
			if (current.indexOf(forbidden[i]) > -1) {
				return true;
			}
		}
		return false;
	}

	private boolean sequence(String current) {
		for (int i = 0; i < letters.length - 2; i++) {
			StringBuilder builder = new StringBuilder().append(letters[i]).append(letters[i + 1]).append(letters[i + 2]);
			if (current.contains(builder.toString())) {
				return true;
			}
		}
		return false;
	}

	private boolean pairs(String current) {
		int pairs = 0;
		for (int i = 0; i < letters.length; i++) {
			StringBuilder builder = new StringBuilder().append(letters[i]).append(letters[i]);
			if (current.contains(builder.toString())) {
				builder.append(letters[i]);
				if (!current.contains(builder.toString())) {
					pairs++;
				}
			}
		}
		
		return pairs >= 2;
	}

	@Override
	public String toString() {
		return "Day 11, Part 2";
	}

}
