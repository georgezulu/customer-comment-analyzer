package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
        
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(commentFiles.length);
		for (File commentFile : commentFiles) {
			
			CommentAnalyzer commentAnalyzer = new CommentAnalyzer(commentFile);
			Future<Map<String, Integer>> fileResults = executor.submit(commentAnalyzer);
			addReportResults(fileResults, totalResults);
			
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param fileResults1 the source map
	 * @param target the target map
	 */
	private static void addReportResults(Future<Map<String, Integer>> fileResults, Map<String, Integer> target) {

		try {
			for (Map.Entry<String, Integer> entry : fileResults.get().entrySet()) {
				target.put(entry.getKey(), entry.getValue());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
}
