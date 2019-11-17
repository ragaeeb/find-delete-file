package com.rhaq.net.io.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

	private static final Pattern p=Pattern.compile("\\d{3,4}x\\d{3,4}.jpg");

	private static boolean tester(Path f)
	{
		Matcher m= p.matcher(f.toFile().getName());
		return m.find();
	}

	private static void deleteFile(Path f) {
		f.toFile().delete();
	}

	public static void main(String[] args) throws Exception {


		try {
			Files.walk(Paths.get("/Users/rhaq/Pictures/temp"))
			.filter(Files::isRegularFile)
			.filter(f -> f.toFile().getName().endsWith("jpg"))
			.filter(f -> tester(f))
			.forEach(f -> deleteFile(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
