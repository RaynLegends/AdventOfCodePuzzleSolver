package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.HashMap;
import java.util.Map.Entry;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _16_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		HashMap<String, HashMap<String, Integer>> sues = new HashMap<>();

		int part = 0;
		String[] sections = input.split(" ");
		String partName = null;
		HashMap<String, Integer> partCurrent = new HashMap<>();
		for (int i = 0; i < sections.length; i++) {
			String section = sections[i];

			if (part == 8) {
				part = 0;
			}

			if (part == 1) {
				partName = section.substring(0, section.length() - 1);
			} else if (part == 3) {
				partCurrent = new HashMap<String, Integer>();
				partCurrent.put(sections[i - 1].substring(0, sections[i - 1].length() - 1), Integer.parseInt(section.substring(0, section.length() - 1)));
			} else if (part == 5) {
				partCurrent.put(sections[i - 1].substring(0, sections[i - 1].length() - 1), Integer.parseInt(section.substring(0, section.length() - 1)));
			} else if (part == 7) {
				partCurrent.put(sections[i - 1].substring(0, sections[i - 1].length() - 1), Integer.parseInt(section));
				sues.put(partName, partCurrent);
			}

			part++;
		}

		HashMap<String, Integer> sueMFCSAM = new HashMap<>();
		sueMFCSAM.put("children", 3);
		sueMFCSAM.put("cats", 7);
		sueMFCSAM.put("samoyeds", 2);
		sueMFCSAM.put("pomeranians", 3);
		sueMFCSAM.put("akitas", 0);
		sueMFCSAM.put("vizslas", 0);
		sueMFCSAM.put("goldfish", 5);
		sueMFCSAM.put("trees", 3);
		sueMFCSAM.put("cars", 2);
		sueMFCSAM.put("perfumes", 1);

		String nearestSue = sues.keySet().iterator().next();
		int lowestDifference = Integer.MAX_VALUE;
		for (String sue : sues.keySet()) {
			int difference = 0;

			for (Entry<String, Integer> entry : sues.get(sue).entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				Integer sueMFCSAMvalue = sueMFCSAM.get(key);
				if (key.equals("cats") || key.equals("trees")) {
					if (value <= sueMFCSAMvalue) {
						difference += 1;
					}
				} else if (key.equals("pomeranians") || key.equals("goldfish")) {
					if (value >= sueMFCSAMvalue) {
						difference += 1;
					}
				} else {
					if (value != sueMFCSAMvalue) {
						difference += 1;
					}
				}
			}

			if (difference < lowestDifference) {
				nearestSue = sue;
				lowestDifference = difference;
			}
		}

		return nearestSue + "";
	}

	@Override
	public String toString() {
		return "Day 16, Part 2";
	}

}
