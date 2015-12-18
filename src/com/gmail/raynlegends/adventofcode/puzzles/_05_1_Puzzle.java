package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _05_1_Puzzle implements Puzzle {
	
	@Override
	public String calculate(String input) {
		int nice = 0;

		for (String line : input.split(" ")) {
			if (line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy")) {
				continue;
			}
			
			int vouels = 0;
			boolean repeating = false;
			char previous = ' ';
			for (char character : line.toCharArray()) {
				if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u') {
					vouels++;
				}
				
				if (character == previous) {
					repeating = true;
				}
				
				previous = character;
			}
			
			if (vouels >= 3 && repeating) {
				nice++;
			}
		}

		return nice + "";
	}

	@Override
	public String toString() {
		return "Day 5, Part 1";
	}

}
