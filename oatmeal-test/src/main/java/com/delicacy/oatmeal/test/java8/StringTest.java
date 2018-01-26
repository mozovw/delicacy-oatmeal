package com.delicacy.oatmeal.test.java8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.delicacy.oatmeal.common.utils.base.ObjectUtil;
import com.delicacy.oatmeal.common.utils.base.ValidateUtil;

public class StringTest {

	public static void main(String[] args) throws IOException {
		System.out.println(distinct("foobar:foo:bar"));
		System.out.println(filter(":", "foobar:foo:bar", "bar"));
		System.out.println();

		long maxUnsignedInt = (1l << 32) - 1;
		String string = String.valueOf(maxUnsignedInt);
		int unsignedInt = Integer.parseUnsignedInt(string, 10);
		String string2 = Integer.toUnsignedString(unsignedInt, 10);
		System.out.println(string2);

		try (Stream<Path> stream = Files.list(Paths.get(""))) {
			String joined = stream.map(String::valueOf).filter(path -> !path.startsWith(".")).sorted()
					.collect(Collectors.joining("; "));
			System.out.println("List: " + joined);
		}

		Path start = Paths.get("");
		System.out.println(new File("").getAbsolutePath());
		int maxDepth = 5;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) -> String.valueOf(path).endsWith(".txt"))) {
			String joined = stream.sorted().map(String::valueOf).collect(Collectors.joining("; "));
			System.out.println("Found: " + joined);
		}

		try (Stream<Path> stream = Files.walk(start, maxDepth)) {
			String joined = stream.map(String::valueOf).filter(path -> path.endsWith(".txt")).sorted()
					.collect(Collectors.joining("; "));
			System.out.println("walk(): " + joined);
		}



		write("channel.txt", "ssss");
		System.out.println(findLine("channel.txt", "ssss"));

	}

	public static List<String> findLine(String path, String item) {
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			return stream.filter(line -> line.contains(item)).map(String::trim).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		/*
		 * try (BufferedReader reader = Files.newBufferedReader(path)) {
		 * reader.lines().filter(line ->
		 * line.contains("item")).map(String::trim).collect(Collectors.toList()); }
		 */
	}

	/**
	 * 会追加
	 * 
	 * @param from
	 * @param text
	 */
	public static void write(String from, String text) {
		write(from, from, text, true);
	}

	/**
	 * 追加 看flag
	 * 
	 * @param from
	 * @param text
	 * @param flag
	 */
	public static void write(String from, String text, boolean flag) {
		write(from, from, text, flag);
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param text
	 * @param flag
	 */
	public static void write(String from, String to, String text, boolean flag) {
		checkNullArg(from, from, text);
		List<String> lines = new ArrayList<>();
		if (flag) {
			try {
				lines = Files.readAllLines(Paths.get(from));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lines.add(text);
		try {
			Files.write(Paths.get(to), lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * String.join(":", "a","b","c") --> a:b:c
	 * 
	 * @param pattern
	 * @param string
	 * @return
	 */
	public static String join(String pattern, String... string) {
		checkNullArg(pattern, string);
		return String.join(pattern, string);// a:b:c
	}

	/**
	 * "foobar:foo:bar" --> [bar, foobar]
	 * 
	 * @param pattern
	 * @param string
	 * @param item
	 * @return
	 */
	public static List<String> filter(String pattern, String string, String item) {
		checkNullArg(pattern, string, item);
		return Pattern.compile(pattern).splitAsStream(string).filter(s -> s.contains(item)).sorted()
				.collect(Collectors.toList());
	}

	/**
	 * "foobar:foo:bar"-->":abfor"
	 * 
	 * @param string
	 * @return
	 */
	public static String distinct(String string) {
		checkNullArg(string);
		return string.chars().distinct().mapToObj(c -> String.valueOf((char) c)).sorted().collect(Collectors.joining());
	}

	private static void checkNullArg(Object... object) {
		boolean nullOrEmpty = ValidateUtil.isNullOrEmpty(object);
		if (!nullOrEmpty) {
			new IllegalArgumentException("not have args");
		}
	}

	private static void checkNullArg(Object object) {
		Object[] objects = new Object[1];
		objects[0] = object;
		checkNullArg(objects);
	}

	public static String showStr(Object object) {
		String prettyString = ObjectUtil.toPrettyString(object);
		return prettyString;
	}

}
