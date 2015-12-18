package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _10_1_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		String current = input;
		for (int i = 0; i < 40; i++) {
			StringBuilder out = new StringBuilder();
			for (int j = 0; j < current.length(); j++) {
				int count = 1;
				char character = current.charAt(j);
				while (j + 1 < current.length() && current.charAt(j + 1) == character) {
					j++;
					count++;
				}
				out.append(count);
				out.append(character);
			}
			current = out.toString();
		}

		return current.length() + "";
	}

	@Override
	public String toString() {
		return "Day 10, Part 1";
	}

}
