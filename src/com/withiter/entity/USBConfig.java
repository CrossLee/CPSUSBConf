package com.withiter.entity;

import java.util.Set;

public class USBConfig {

	public static Set<String> EXTS;
	public static String VIDEO_NEW_FOLDER;
	public static String INIT_NEW_FOLDER;
	public static String VIDEO_PATH;
	public static int VIDEO_MAX_NUMBER;
	public static int NEWS_MAX_CHARS;
	
	public static String drivePath;

	public static void initParams(Set<String> exts, String videoNewFolder, String initNewFolder, String videoPath,
			int videoMaxNumber, int newsMaxChars) {
		EXTS = exts;
		VIDEO_NEW_FOLDER = videoNewFolder;
		INIT_NEW_FOLDER = initNewFolder;
		VIDEO_PATH = videoPath;
		VIDEO_MAX_NUMBER = videoMaxNumber;
		NEWS_MAX_CHARS = newsMaxChars;
	}

	public static void description() {
		String des = new StringBuilder("").append(EXTS.toString()).append(", ")
				.append(VIDEO_NEW_FOLDER).append(", ")
				.append(INIT_NEW_FOLDER).append(", ")
				.append(VIDEO_PATH).append(", ").append(VIDEO_MAX_NUMBER)
				.append(", ").append(NEWS_MAX_CHARS).toString();
		System.out.println(des);
	}
}
