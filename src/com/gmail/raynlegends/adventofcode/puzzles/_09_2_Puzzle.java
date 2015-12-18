package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _09_2_Puzzle implements Puzzle {

	private static class Pair {
	    private final String city;
	    private final int distance;

	    public Pair(String city, int distance) {
	        this.city = city;
	        this.distance = distance;
	    }
	}

	@Override
	public String calculate(String input) {
		HashMap<String, HashMap<String, Integer>> distances = new HashMap<>();

		int part = 0;
		String partFirst = null;
		String partSecond = null;
		String partDistance = null;
		for (String section : input.split(" ")) {
			if (part == 5) {
				part = 0;
			}

			if (part == 0) {
				partFirst = section;
			} else if (part == 2) {
				partSecond = section;
			} else if (part == 4) {
				partDistance = section;
				int distance = Integer.parseInt(partDistance);

				HashMap<String, Integer> firstDistances = distances.get(partFirst);

				if (firstDistances == null) {
					firstDistances = new HashMap<String, Integer>();
					distances.put(partFirst, firstDistances);
				}

				firstDistances.put(partSecond, distance);

				HashMap<String, Integer> secondDistances = distances.get(partSecond);

				if (secondDistances == null) {
					secondDistances = new HashMap<String, Integer>();
					distances.put(partSecond, secondDistances);
				}

				secondDistances.put(partFirst, distance);
			}

			part++;
		}

		int longestPathLength = 0;
		for (String city : distances.keySet()) {
			String processing = city;
			
			ArrayList<String> processedCities = new ArrayList<>();
			processedCities.add(processing);
			
			int currentPathLength = 0;
			while (processedCities.size() < distances.size()) {
				HashMap<String, Integer> cityDistances = distances.get(processing);
				
				HashMap<String, Integer> cityDistancesCopy = new HashMap<>();
				for (Entry<String, Integer> entry : cityDistances.entrySet()) {
					if (!processedCities.contains(entry.getKey())) {
						cityDistancesCopy.put(entry.getKey(), entry.getValue());
					}
				}
				
				processedCities.add(processing);
				
				Pair result = process(cityDistancesCopy);
				currentPathLength += result.distance;
				processing = result.city;
			}
			
			if (currentPathLength > longestPathLength) {
				longestPathLength = currentPathLength;
			}
		}

		return longestPathLength + "";
	}

	private Pair process(HashMap<String, Integer> cityDistancesCopy) {
		String furthestCity = null;
		int furthestDistance = 0;
		for (String other : cityDistancesCopy.keySet()) {
			Integer distance = cityDistancesCopy.get(other);

			if (distance > furthestDistance) {
				furthestCity = other;
				furthestDistance = distance;
			}
		}

		cityDistancesCopy.remove(furthestCity);
		return new Pair(furthestCity, furthestDistance);
	}

	@Override
	public String toString() {
		return "Day 9, Part 2";
	}

}
