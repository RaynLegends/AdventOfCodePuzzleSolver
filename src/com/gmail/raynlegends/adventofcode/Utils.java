package com.gmail.raynlegends.adventofcode;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

	private static final char DOT = '.';
	private static final char SLASH = '/';
	private static final String CLASS_SUFFIX = ".class";

	public static List<Class<?>> find(String scannedPackage) {
		String scannedPath = scannedPackage.replace(DOT, SLASH);
		URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
		if (scannedUrl == null) {
			throw new IllegalArgumentException("Couldn't find package " + scannedPackage);
		}
		File scannedDir = new File(scannedUrl.getFile());
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File file : scannedDir.listFiles()) {
			classes.addAll(find(file, scannedPackage));
		}
		return classes;
	}

	private static List<Class<?>> find(File file, String scannedPackage) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String resource = scannedPackage + DOT + file.getName();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				classes.addAll(find(child, resource));
			}
		} else if (resource.endsWith(CLASS_SUFFIX)) {
			int endIndex = resource.length() - CLASS_SUFFIX.length();
			String className = resource.substring(0, endIndex);
			try {
				classes.add(Class.forName(className));
			} catch (ClassNotFoundException ignore) {
				//
			}
		}
		return classes;
	}
}
