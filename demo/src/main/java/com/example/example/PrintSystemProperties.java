package com.example.example;

import java.util.Properties;

public class PrintSystemProperties {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.list(System.out);
	}
}
