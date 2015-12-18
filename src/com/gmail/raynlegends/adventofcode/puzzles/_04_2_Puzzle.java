package com.gmail.raynlegends.adventofcode.puzzles;

import java.security.MessageDigest;

import com.gmail.raynlegends.adventofcode.Puzzle;

public class _04_2_Puzzle implements Puzzle {

	@Override
	public String calculate(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			int current = 1;
			byte[] hash;
			while (true) {
				hash = md.digest((input + current).getBytes("UTF-8"));

				StringBuffer result = new StringBuffer();
				for (int i = 0; i < hash.length; i++) {
				    if ((0xff & hash[i]) < 0x10) {
				    	result.append("0" + Integer.toHexString((0xFF & hash[i])));
				    } else {
				    	result.append(Integer.toHexString(0xFF & hash[i]));
				    }
				}
				
				if (result.toString().startsWith("000000")) {
					return current + "";
				}
				
				current++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String toString() {
		return "Day 4, Part 2";
	}

}
