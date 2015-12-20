package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.LinkedList;

import com.gmail.raynlegends.adventofcode.Puzzle;
import com.gmail.raynlegends.adventofcode.PuzzleUtils;
import com.gmail.raynlegends.adventofcode.PuzzleUtils.CombinationChecker;

public class _15_2_Puzzle implements Puzzle {

	private static class Ingredient {
		private final String name;
		private final int capacity;
		private final int durability;
		private final int flavor;
		private final int texture;
		private final int calories;

		public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
			this.name = name;
			this.capacity = capacity;
			this.durability = durability;
			this.flavor = flavor;
			this.texture = texture;
			this.calories = calories;
		}

		@Override
		public boolean equals(Object another) {
			if (this == another) {
				return true;
			}

			if ((another == null) || (another.getClass() != this.getClass())) {
				return false;
			}

			Ingredient ingredient = (Ingredient) another;

			return ingredient.capacity == this.capacity && 
					ingredient.durability == this.durability && 
					ingredient.flavor == this.flavor && 
					ingredient.texture == this.texture && 
					ingredient.calories == this.calories;
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 31 * hash + name.hashCode();
			hash = 31 * hash + capacity;
			hash = 31 * hash + durability;
			hash = 31 * hash + flavor;
			hash = 31 * hash + texture;
			hash = 31 * hash + calories;
			return hash;
		}
	}

	@Override
	public String calculate(String input) {
		LinkedList<Ingredient> ingredients = new LinkedList<>();
		
		int part = 0;
		String partName = null;
		String partCapacity = null;
		String partDurability = null;
		String partFlavor = null;
		String partTexture = null;
		String partCalories = null;
		for (String section : input.split(" ")) {
			if (part == 11) {
				part = 0;
			}

			if (part == 0) {
				partName = section.substring(0, section.length() - 1);
			} else if (part == 2) {
				partCapacity = section.substring(0, section.length() - 1);
			} else if (part == 4) {
				partDurability = section.substring(0, section.length() - 1);
			} else if (part == 6) {
				partFlavor = section.substring(0, section.length() - 1);
			} else if (part == 8) {
				partTexture = section.substring(0, section.length() - 1);
			} else if (part == 10) {
				partCalories = section;

				int capacity = Integer.parseInt(partCapacity);
				int durability = Integer.parseInt(partDurability);
				int flavor = Integer.parseInt(partFlavor);
				int texture = Integer.parseInt(partTexture);
				int calories = Integer.parseInt(partCalories);

				ingredients.add(new Ingredient(partName, capacity, durability, flavor, texture, calories));
			}

			part++;
		}

		LinkedList<Integer> values = new LinkedList<>();
		for (int i = 1; i <= 100; i++) {
			values.add(i);
		}
		
		LinkedList<LinkedList<Integer>> combinations = PuzzleUtils.calculateCombinations(ingredients.size(), true, values, new CombinationChecker<Integer>() {

			@Override
			public boolean check(LinkedList<LinkedList<Integer>> combinations, LinkedList<Integer> combination) {
				int sum = 0;
				for (int value : combination) {
					sum += value;
				}
				if (sum != 100) {
					return false;
				}
				return true;
			}
			
		});
		
		int highestTotal = Integer.MIN_VALUE;
		for (LinkedList<Integer> combination : combinations) {
			int totalCapacity = 0;
			int totalDurability = 0;
			int totalFlavor = 0;
			int totalTexture = 0;
			int totalCalories = 0;

			int i = 0;
			for (Integer value : combination) {
				Ingredient ingredient = ingredients.get(i);
				totalCapacity += ingredient.capacity * value;
				totalDurability += ingredient.durability * value;
				totalFlavor += ingredient.flavor * value;
				totalTexture += ingredient.texture * value;
				totalCalories += ingredient.calories * value;
				i++;
			}
			
			totalCapacity = totalCapacity < 0 ? 0 : totalCapacity;
			totalDurability = totalDurability < 0 ? 0 : totalDurability;
			totalFlavor = totalFlavor < 0 ? 0 : totalFlavor;
			totalTexture = totalTexture < 0 ? 0 : totalTexture;

			int total = totalCapacity * totalDurability * totalFlavor * totalTexture;
			
			if (total > highestTotal && totalCalories == 500) {
				highestTotal = total;
			}
		}
		
		return highestTotal + "";
	}

	@Override
	public String toString() {
		return "Day 15, Part 2";
	}

}
