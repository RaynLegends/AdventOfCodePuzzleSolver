package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _08_1_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int stringCode = 0;
		int stringMemory = 0;

		for (String line : input.split(" ")) {
			char[] array = line.toCharArray();
			for (int i = 0; i < array.length; i++) {
				char current = array[i];
				if (i == 0) {
					continue;
				} else if (i == array.length - 1) {
					continue;
				}

				int remaining = (array.length - 1) - i;

				if (current == '\\') {
					if (remaining >= 1 && array[i + 1] == '"') {
						stringMemory++;
						i = i + 1;
					} else if (remaining >= 1 && array[i + 1] == '\\') {
						stringMemory++;
						i = i + 1;
					} else if (remaining >= 3 && array[i + 1] == 'x') {
						stringMemory++;
						i = i + 3;
					} else {
						// There aren't strings such as "a\a", the backslash is always followed by either ", \ or x
					}
				} else {
					stringMemory++;
				}
			}
			stringCode += line.length();
		}

		return stringCode - stringMemory + "";
	}

	@Override
	public String toString() {
		return "Day 8, Part 1";
	}

}
