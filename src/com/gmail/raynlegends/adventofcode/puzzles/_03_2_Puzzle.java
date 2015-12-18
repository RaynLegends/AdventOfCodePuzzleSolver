package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.ArrayList;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _03_2_Puzzle implements Puzzle {

	private static class House {
		private final int x;
		private final int y;
		
		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object another) {
			if (this == another) {
				return true;
			}
			
			if ((another == null) || (another.getClass() != this.getClass())) {
				return false;
			}
			
			House house = (House) another;
			
			return house.x == this.x && house.y == this.y;
		}

		@Override
		public int hashCode() {
			int hash = 9;
			hash = 31 * hash + x;
			hash = 31 * hash + y;
			return hash;
		}
	}

	@Override
	public String calculate(String input) {
		int santaX = 0;
		int santaY = 0;
		
		int robotX = 0;
		int robotY = 0;

		int number = 1;
		ArrayList<House> houses = new ArrayList<>();
		houses.add(new House(0, 0)); // The first house has to be counted anyway
		
		boolean santaMoves = true;
		for (char character : input.toCharArray()) {
			House current;
			if (santaMoves) {
				if (character == '>') {
					santaX++;
				} else if (character == '<') {
					santaX--;
				} else if (character == '^') {
					santaY++;
				} else if (character == 'v') {
					santaY--;
				}
				
				current = new House(santaX, santaY);
			} else {
				if (character == '>') {
					robotX++;
				} else if (character == '<') {
					robotX--;
				} else if (character == '^') {
					robotY++;
				} else if (character == 'v') {
					robotY--;
				}
				
				current = new House(robotX, robotY);
			}
			santaMoves = !santaMoves;
			
			if (!houses.contains(current)) {
				number++;
				houses.add(current);
			}
		}

		return number + "";
	}

	@Override
	public String toString() {
		return "Day 3, Part 2";
	}

}
