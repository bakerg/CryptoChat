package io.github.bakerg;

import java.util.Arrays;
import java.util.List;

public class StringHandler {
	public static String[] parseInput(String input){
		List<String> outputList = Arrays.asList(input.split(","));
		String[] output = new String[outputList.size()];
		for(int i = 0; i < outputList.size(); i++){
			output[i] = outputList.get(i);
		}
		return output;
	}
}
