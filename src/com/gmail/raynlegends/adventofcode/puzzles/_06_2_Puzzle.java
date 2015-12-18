package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.HashMap;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _06_2_Puzzle implements Puzzle {

	private static class Light {
		private final int x;
		private final int y;
		
		public Light(int x, int y) {
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
			
			Light house = (Light) another;
			
			return house.x == this.x && house.y == this.y;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 31 * hash + x;
			hash = 31 * hash + y;
			return hash;
		}
	}

	@Override
	public String calculate(String input) {
		HashMap<Light, Integer> lights = new HashMap<>();
		
		input = input.replace("turn on", "turn_on");
		input = input.replace("turn off", "turn_off");
		
		int part = 0;
		String partCommand = null;
		String partStart = null;
		String partEnd = null;
		for (String section : input.split(" ")) {
			if (part == 4) {
				part = 0;
			}
			
			if (part == 0) {
				partCommand = section;
			} else if (part == 1) {
				partStart = section;
			} else if (part == 2) {
				// partThrough
			} else if (part == 3) {
				partEnd = section;
				
				String[] rawStart = partStart.split(",");
				int startX = Integer.parseInt(rawStart[0]);
				int startY = Integer.parseInt(rawStart[1]);
				
				String[] rawEnd = partEnd.split(",");
				int endX = Integer.parseInt(rawEnd[0]);
				int endY = Integer.parseInt(rawEnd[1]);
				
				for (int x = startX; x <= endX; x++) {
					for (int y = startY; y <= endY; y++) {
						Light light = new Light(x, y);
						
						Integer status = lights.get(light);
						if (status == null) {
							status = 0;
						}
						
						switch (partCommand) {
						case "turn_on": lights.put(light, status + 1); break;
						case "turn_off": lights.put(light, (status - 1 < 0 ? 0 : status - 1)); break;
						case "toggle": lights.put(light, status + 2); break;
						}
					}
				}
			}
			
			part++;
		}

		int brightness = 0;
		for (Integer status : lights.values()) {
			brightness += status;
		}
		
		return brightness + "";
	}

	@Override
	public String toString() {
		return "Day 6, Part 2";
	}

}
