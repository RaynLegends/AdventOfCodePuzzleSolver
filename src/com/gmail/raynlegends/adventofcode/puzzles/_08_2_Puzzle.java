package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _08_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		int stringCode = 0;
		int stringEncoded = 0;

		for (String line : input.split(" ")) {
			stringCode += line.length();
			
			String encoded = line;
			encoded = encoded.replace("\\", "\\\\");
			encoded = encoded.replace("\"", "\\\"");
			encoded = "\"" + encoded + "\"";
			stringEncoded += encoded.length();
		}

		return stringEncoded - stringCode + "";
	}

	@Override
	public String toString() {
		return "Day 8, Part 2";
	}

}
