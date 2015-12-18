package com.gmail.raynlegends.adventofcode;

import javax.swing.UIManager;

public class Main {

	private static GUI gui;

	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		gui = new GUI();
	}

	public static GUI getGui() {
		return gui;
	}

	public static String calculate(Object selectedItem, String input) {
		if (selectedItem == null) {
			throw new IllegalArgumentException("You must select a puzzle first!");
		}
		
		if (!(selectedItem instanceof Puzzle)) {
			throw new IllegalArgumentException("You must select a puzzle first!");
		}
		
		return ((Puzzle) selectedItem).calculate(input);
	}
}