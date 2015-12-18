package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _01_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int current = 0;
		int position = 1;
		
		for (char character : input.toCharArray()) {
			if (character == '(') {
				current++;
			} else if (character == ')') {
				current--;
			}
			
			if (current == -1) {
				break;
			}
			
			position++;
		}
		
		return position + "";
	}

	@Override
	public String toString() {
		return "Day 1, Part 2";
	}
	
}
