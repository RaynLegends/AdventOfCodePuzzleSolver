package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;
import com.gmail.raynlegends.adventofcode.PuzzleUtils;

public class _02_1_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int totalSurface = 0;

		for (String line : input.split(" ")) {
			String[] rawParts = line.split("x");

			int l = number(rawParts, 0);
			int w = number(rawParts, 1);
			int h = number(rawParts, 2);

			int aSurface = l * w;
			int bSurface = w * h;
			int cSurface = h * l;

			int surface = 2 * aSurface + 2 * bSurface + 2 * cSurface;
			int smallestSurface = PuzzleUtils.smallest(aSurface, bSurface, cSurface);

			totalSurface += surface + smallestSurface;
		}

		return totalSurface + "";
	}

	private Integer number(String[] rawParts, int position) {
		return Integer.parseInt(rawParts[position]);
	}

	@Override
	public String toString() {
		return "Day 2, Part 1";
	}

}
