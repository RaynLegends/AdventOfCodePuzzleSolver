package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.ArrayList;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _14_2_Puzzle implements Puzzle {

	private static class Reindeer {
		private final String name;
		private final int speed;
		private final int fly;
		private final int rest;
		
		private int kilometers = 0;
		private int flying = 0;
		private int resting = 0;
		
		private int score = 0;
		
		public Reindeer(String name, int speed, int fly, int rest) {
			this.name = name;
			this.speed = speed;
			this.fly = fly;
			this.rest = rest;
		}

		@Override
		public boolean equals(Object another) {
			if (this == another) {
				return true;
			}
			
			if ((another == null) || (another.getClass() != this.getClass())) {
				return false;
			}
			
			Reindeer reindeer = (Reindeer) another;
			
			return reindeer.name.equals(this.name);
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 31 * hash + name.hashCode();
			return hash;
		}
	}

	@Override
	public String calculate(String input) {
		ArrayList<Reindeer> reindeers = new ArrayList<>();

		int part = 0;
		String partName = null;
		String partSpeed = null;
		String partFly = null;
		String partRest = null;
		for (String section : input.split(" ")) {
			if (part == 15) {
				part = 0;
			}

			if (part == 0) {
				partName = section;
			} else if (part == 3) {
				partSpeed = section;
			} else if (part == 6) {
				partFly = section;
			} else if (part == 13) {
				partRest = section;
				
				int speed = Integer.parseInt(partSpeed);
				int fly = Integer.parseInt(partFly);
				int rest = Integer.parseInt(partRest);
				
				reindeers.add(new Reindeer(partName, speed, fly, rest));
			}

			part++;
		}
		
		for (int i = 0; i < 2503; i++) {
			int longest = 0;
			for (Reindeer reindeer : reindeers) {
				if (reindeer.flying < reindeer.fly) {
					reindeer.flying++;
					reindeer.kilometers += reindeer.speed;
				} else if (reindeer.resting < reindeer.rest) {
					reindeer.resting++;
				} else {
					reindeer.flying = 1;
					reindeer.resting = 0;
					reindeer.kilometers += reindeer.speed;
				}
				
				if (reindeer.kilometers > longest) {
					longest = reindeer.kilometers;
				}
			}
			
			for (Reindeer reindeer : reindeers) {
				if (reindeer.kilometers == longest) {
					reindeer.score++;
				}
			}
		}

		int highest = 0;
		for (Reindeer reindeer : reindeers) {
			if (reindeer.score > highest) {
				highest = reindeer.score;
			}
		}
		
		return highest + "";
	}

	@Override
	public String toString() {
		return "Day 14, Part 2";
	}

}
