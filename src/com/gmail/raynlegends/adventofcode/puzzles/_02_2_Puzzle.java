package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;
import com.gmail.raynlegends.adventofcode.PuzzleUtils;

public class _02_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int totalLength = 0;

		for (String line : input.split(" ")) {
			String[] rawParts = line.split("x");

			int l = number(rawParts, 0);
			int w = number(rawParts, 1);
			int h = number(rawParts, 2);

			int aPerimeter = l * 2 + w * 2;
			int bPerimeter = w * 2 + h * 2;
			int cPerimeter = h * 2 + l * 2;

			int volume = l * w * h;
			int smallestPerimeter = PuzzleUtils.smallest(aPerimeter, bPerimeter, cPerimeter);

			totalLength += volume + smallestPerimeter;
		}

		return totalLength + "";
	}

	private Integer number(String[] rawParts, int position) {
		return Integer.parseInt(rawParts[position]);
	}

	@Override
	public String toString() {
		return "Day 2, Part 2";
	}

}
