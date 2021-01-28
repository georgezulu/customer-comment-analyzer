package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CommentAnalyzer implements Callable <Map<String, Integer>>{

	private File file;

	public CommentAnalyzer(File file) {
		this.file = file;
	}

	public Map<String, Integer> call() {

		Map<String, Integer> res = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line = null;

			while ((line = reader.readLine()) != null) {

				res = MetricsProcessor.process(line);

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}

		return res;
	}

}
