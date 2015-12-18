package com.gmail.raynlegends.adventofcode;

import javax.swing.UIManager;

import org.json.simple.parser.JSONParser;

public class Main {

	private static GUI gui;
	private static JSONParser parser;

	public static final Integer version = 1;

	public static void main(String... args) {
		new Utils();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		parser = new JSONParser();

		gui = new GUI();
	}

	public static GUI getGui() {
		return gui;
	}

	public static JSONParser getParser() {
		return parser;
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