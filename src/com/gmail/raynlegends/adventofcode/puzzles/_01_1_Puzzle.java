package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _01_1_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int up = 0;
		int down = 0;
		
		for (char character : input.toCharArray()) {
			if (character == '(') {
				up++;
			} else if (character == ')') {
				down++;
			}
		}
		
		return up - down + "";
	}

	@Override
	public String toString() {
		return "Day 1, Part 1";
	}
	
}
