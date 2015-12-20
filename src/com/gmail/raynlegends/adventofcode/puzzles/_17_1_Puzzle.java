package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.gmail.raynlegends.adventofcode.Puzzle;
import com.gmail.raynlegends.adventofcode.PuzzleUtils;
import com.gmail.raynlegends.adventofcode.PuzzleUtils.CombinationValidator;

public class _17_1_Puzzle implements Puzzle {

	private final static int liters = 150;

	@Override
	public String calculate(String input) {
		String[] sections = input.split(" ");
		final Byte[] containers = new Byte[sections.length];
		for (int i = 0; i < sections.length; i++) {
			containers[i] = Byte.parseByte(sections[i]);
		}
		
		LinkedList<Byte[]> slowContainers = new LinkedList<>();
		byte id = 0;
		for (Byte container : containers) {
			Byte[] slowContainer = new Byte[2];
			slowContainer[0] = container;
			slowContainer[1] = id;
			slowContainers.add(slowContainer);
			id++;
		}
		
		sort(slowContainers);
		
		byte highestContainersNumberToReachLiters = 1;
		short highestContainersCurrentSize = 0;
		for (int i = slowContainers.size() - 1; i >= 0; i--) {
			highestContainersCurrentSize += slowContainers.get(i)[0];

			if (highestContainersCurrentSize >= 150) {
				break;
			}
			highestContainersNumberToReachLiters++;
		}
		
		byte smallestContainersNumberToReachLiters = 1;
		short smallestContainersCurrentSize = 0;
		for (int i = 0; i < slowContainers.size(); i++) {
			smallestContainersCurrentSize += slowContainers.get(i)[0];

			if (smallestContainersCurrentSize >= 150) {
				break;
			}
			smallestContainersNumberToReachLiters++;
		}

		int totalCombinations = 0;
		for (int i = highestContainersNumberToReachLiters; i < smallestContainersNumberToReachLiters; i++) {
			totalCombinations += PuzzleUtils.validCombinationsAmount(containers, i, new CombinationValidator<Byte>() {

				@Override
				public boolean check(Byte[] elements, int[] combination) {
					short total = 0;
					for (int z = 0; z < combination.length; z++) {
						total += elements[combination[z]];
					}
					
					if (total == liters) {
						return true;
					}
					
					return false;
				}
				
			});
		}
		
		return totalCombinations + "";
	}

	private void sort(LinkedList<Byte[]> list) {
		Collections.sort(list, new Comparator<Byte[]>(){

			@Override
			public int compare(Byte[] value1, Byte[] value2){
				if (value1[0] < value2[0]) {
					return -1; 
				}
				if (value1[0] > value2[0]){
					return 1; 
				}
				return 0;
			}

		});
	}

	@Override
	public String toString() {
		return "Day 17, Part 1";
	}

}
