package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _13_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		HashMap<String, HashMap<String, Integer>> units = new HashMap<>();

		int part = 0;
		String partFirst = null;
		String partType = null;
		String partValue = null;
		String partSecond = null;
		for (String section : input.split(" ")) {
			if (part == 11) {
				part = 0;
			}

			if (part == 0) {
				partFirst = section;
			} else if (part == 2) {
				partType = section;
			} else if (part == 3) {
				partValue = section;
			} else if (part == 10) {
				partSecond = section.substring(0, section.length() - 1);

				int unit = Integer.parseInt(partValue) * (partType.equals("gain") ? 1 : -1);

				HashMap<String, Integer> firstUnits = units.get(partFirst);

				if (firstUnits == null) {
					firstUnits = new HashMap<String, Integer>();
					units.put(partFirst, firstUnits);
				}

				firstUnits.put(partSecond, unit);

				System.out.println("Stored " + partFirst + " -> " + partSecond + " : " + unit);
			}

			part++;
		}
		
		final String meId = "[ME]";
		HashMap<String, Integer> me = new HashMap<>();
		for (Entry<String, HashMap<String, Integer>> entry : units.entrySet()) {
			me.put(entry.getKey(), 0);
			entry.getValue().put(meId, 0);
		}
		units.put(meId, me);

		LinkedList<String> persons = new LinkedList<>();

		for (String person : units.keySet()) {
			persons.add(person);
		}

		LinkedList<LinkedList<String>> combinations = new LinkedList<>();
		calculateCombinations(persons, combinations, new LinkedList<String>());

		int highestTotal = Integer.MIN_VALUE;
		for (LinkedList<String> combination : combinations) {
			StringBuilder builder = new StringBuilder();
			
			int total = 0;

			int i = 0;
			for (String person : combination) {
				builder.append(person).append(", ");
				
				String previous = combination.get(i - 1 < 0 ? i - 1 + combination.size() : i - 1);
				String next = combination.get(i + 1 >= combination.size() ? i + 1 - combination.size() : i + 1);

				total += units.get(person).get(previous);
				total += units.get(person).get(next);

				i++;
			}

			if (total > highestTotal) {
				highestTotal = total;
			}
		}

		return highestTotal + "";
	}

	private void calculateCombinations(LinkedList<String> persons, LinkedList<LinkedList<String>> combinations, LinkedList<String> currentCombination) {
		if (currentCombination != null && currentCombination.size() >= persons.size()) {
			combinations.add(currentCombination);
			return;
		}

		for (String person : persons) {
			if (currentCombination.contains(person)) {
				continue;
			}
			LinkedList<String> newCurrentCombination = new LinkedList<>();
			for (String string : currentCombination) {
				newCurrentCombination.add(string);
			}
			newCurrentCombination.add(person);
			calculateCombinations(persons, combinations, newCurrentCombination);
		}
	}

	@Override
	public String toString() {
		return "Day 13, Part 1";
	}

}
