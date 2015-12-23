package com.gmail.raynlegends.adventofcode.puzzles;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _18_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		input = input.replace(" ", "");
		
		boolean[][] lights = new boolean[100][100];
		int lightx = 0;
		int lighty = 0;
		for (char character : input.toCharArray()) {
			lights[lightx][lighty] = character == '#' ? true : false;
			
			lightx++;
			if (lightx == 100) {
				lightx = 0;
				lighty++;
			}
		}
		
		lights[0][0] = true;
		lights[0][99] = true;
		lights[99][0] = true;
		lights[99][99] = true;
		
		for (int i = 0; i < 100; i++) {
			boolean[][] previouslights = new boolean[100][100];
			for (int x = 0; x < 100; x++) {
				for (int y = 0; y < 100; y++) {
					previouslights[x][y] = lights[x][y];
				}
			}
			
			for (int x = 0; x < 100; x++) {
				for (int y = 0; y < 100; y++) {
					if (x == 0 && y == 0) {
						continue;
					} else if (x == 0 && y == 99) {
						continue;
					} else if (x == 99 && y == 0) {
						continue;
					} else if (x == 99 && y == 99) {
						continue;
					}
					
					boolean status = previouslights[x][y];
					byte onNeighbors = 0;
					if (x > 0) {
						if (previouslights[x - 1][y]) {
							onNeighbors++;
						}
					}
					if (y > 0) {
						if (previouslights[x][y - 1]) {
							onNeighbors++;
						}
					}
					if (x > 0 && y > 0) {
						if (previouslights[x - 1][y - 1]) {
							onNeighbors++;
						}
					}
					if (x < 99 && y > 0) {
						if (previouslights[x + 1][y - 1]) {
							onNeighbors++;
						}
					}
					if (x < 99) {
						if (previouslights[x + 1][y]) {
							onNeighbors++;
						}
					}
					if (y < 99) {
						if (previouslights[x][y + 1]) {
							onNeighbors++;
						}
					}
					if (x < 99 && y < 99) {
						if (previouslights[x + 1][y + 1]) {
							onNeighbors++;
						}
					}
					if (x > 0 && y < 99) {
						if (previouslights[x - 1][y + 1]) {
							onNeighbors++;
						}
					}
					
					if (status) {
						if (onNeighbors != 2 && onNeighbors != 3) {
							lights[x][y] = false;
						}
					} else {
						if (onNeighbors == 3) {
							lights[x][y] = true;
						}
					}
				}
			}
		}
		
		int on = 0;
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (lights[x][y]) {
					on++;
				}
			}
		}
		
		return on + "";
	}

	@Override
	public String toString() {
		return "Day 18, Part 2";
	}

}
