package com.gmail.raynlegends.adventofcode;

public class Utils {
	private static OS os;

	static {
		String oscode = System.getProperty("os.name").substring(0, 3).toLowerCase();

		if (oscode.equals("win")) {
			os = OS.WINDOWS;
		} else if (oscode.equals("mac")) {
			os = OS.MAC;
		} else if (oscode.equals("nix")) {
			os = OS.UNIX;
		} else if (oscode.equals("nux")) {
			os = OS.UNIX;
		} else if (oscode.equals("aix")) {
			os = OS.UNIX;
		} else if (oscode.equals("sun")) {
			os = OS.UNSUPPORTED;
		}
	}

	public enum OS {
		WINDOWS,
		MAC,
		UNIX,
		UNSUPPORTED;
	}

	public static OS getOs() {
		return os;
	}

}
