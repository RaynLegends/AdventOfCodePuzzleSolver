package com.gmail.raynlegends.adventofcode.puzzles;

import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _12_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {

		JSONParser parser = new JSONParser();
		Object parsed = null;
		try {
			parsed = parser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		total = 0;
		iterate(parsed);

		return total + "";
	}

	private long total = 0;

	@SuppressWarnings("unchecked")
	private void iterate(Object parsed) {
		if (parsed instanceof JSONObject) {
			boolean skip = false;
			JSONObject jsonObject = (JSONObject) parsed;
			for (Object rawEntry : jsonObject.entrySet()) {
				Entry<Object, Object> entry = (Entry<Object, Object>) rawEntry;

				if (entry.getKey().equals("red") || entry.getValue().equals("red")) {
					skip = true;
					break;
				}
			}

			if (!skip) {
				for (Object rawEntry : jsonObject.entrySet()) {
					Entry<Object, Object> entry = (Entry<Object, Object>) rawEntry;

					iterate(entry.getValue());
				}
			}
		} else if (parsed instanceof JSONArray) {
			for (Object raw : ((JSONArray) parsed)) {
				iterate(raw);
			}
		} else if (parsed instanceof Long) {
			total += (Long) parsed;
		}
	}

	@Override
	public String toString() {
		return "Day 12, Part 2";
	}

}
