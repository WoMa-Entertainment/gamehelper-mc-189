package de.winston.steamidle.test;

import java.util.Map.Entry;

public class Test {
	public static void main(String... args) {
		System.out.println("--- Environment: ---");
		for (Entry<String, String> nTry : System.getenv().entrySet()) {
			System.out.println(nTry.getKey() + ": " + nTry.getValue());
		}
		System.out.println("---Properties: ---");
		for (Entry<Object, Object> nTry : System.getProperties().entrySet()) {
			System.out.println(nTry.getKey() + ": " + nTry.getValue());
		}
	}
}
