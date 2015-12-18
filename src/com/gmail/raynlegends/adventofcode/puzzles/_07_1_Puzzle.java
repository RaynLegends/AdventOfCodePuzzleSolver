package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.HashMap;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _07_1_Puzzle implements Puzzle {

	private static interface Source {
		int getValue();
		String toString();
	}
	
	private static class Wire implements Source {
		
		private static HashMap<String, Integer> calculateds = new HashMap<>();
		
		private final String id;
		private Source firstSource;
		private Source secondSource;
		private String operation;
		
		private Wire(String id, Source firstSource, Source secondSource, String operation) {
			this.id = id;
			this.firstSource = firstSource;
			this.secondSource = secondSource;
			this.operation = operation;
		}
		
		@Override
		public int getValue() {
			Integer calculated = calculateds.get(id);
			if (calculated != null) {
				return calculated; // Otherwise it would fall into an infinite loop
			}
			
			int result = 0;
			if (secondSource != null) {
				result = calculate(firstSource.getValue(), secondSource.getValue(), operation);
			} else if (operation != null) {
				result = ~firstSource.getValue(); // Operation equals "BWC" at this point
			} else {
				result = firstSource.getValue();
			}
			
			calculateds.put(id, result);
			
			return result;
		}
		
		@Override
		public String toString() {
			return id;
		}
	}
	
	private static class Value implements Source {
		private int value;
		
		private Value(int value) {
			this.value = value;
		}
		
		@Override
		public int getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return value + "";
		}
	}
	
	@Override
	public String calculate(String input) {
		HashMap<String, Wire> wires = new HashMap<>();
		
		int part = 0;
		int last = 100;
		String[] parts = new String[5];
		for (String section : input.split(" ")) {
			if (part == last + 1) {
				part = 0;
				last = 100;
				parts = new String[5];
			}
			
			parts[part] = section;
			
			if (section.equals("->")) {
				last = part + 1;
			}
			
			if (part == last) {
				String wireId = parts[last];
				Wire wire = wires.get(wireId);
				
				if (wire == null) {
					wire = new Wire(wireId, null, null, null);
					wires.put(wireId, wire);
				}
				
				if (last == 2) {
					wire.firstSource = getProvider(wires, parts[0]);
				} else if (last == 3) {
					wire.firstSource = getProvider(wires, parts[1]);
					wire.operation = "BWC";
				} else if (last == 4) {
					wire.firstSource = getProvider(wires, parts[0]);
					wire.operation = parts[1];
					wire.secondSource = getProvider(wires, parts[2]);
				}
			}
			
			part++;
		}
		
		return wires.get("a").getValue() + "";
	}

	private static int calculate(int first, int second, String operation) {
		switch (operation) {
		case "AND": return first & second;
		case "OR": return first | second;
		case "LSHIFT": return first << second;
		case "RSHIFT": return first >> second;
		}
		return 0;
	}
	
	private static Source getProvider(HashMap<String, Wire> wires, String id) {
		Source provider = null;
		try {
			provider = new Value(Integer.parseInt(id));
		} catch (Exception e) {
			Wire otherWire = wires.get(id);
			
			if (otherWire == null) {
				otherWire = new Wire(id, null, null, null);
				wires.put(id, otherWire);
			}
			
			provider = otherWire;
		}
		
		return provider;
	}
	
	@Override
	public String toString() {
		return "Day 7, Part 1";
	}

}
