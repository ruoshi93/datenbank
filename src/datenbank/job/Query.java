package datenbank.job;

import java.util.ArrayList;
import java.util.List;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public abstract class Query {

	static List<String> allPermutations = new ArrayList<String>();

	private static char[] swap(char[] arr, int i, int j) {
		char tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
		return arr;
	}

	protected static void allPermutation(char[] arr, int start) {
		if (start == arr.length - 1) {
			allPermutations.add(String.valueOf(arr));
		} else if (start > arr.length - 1) {
			System.out
					.println("Error: The starting index cannot be greater than or equal to the length of the array. ");
		} else {
			for (int i = start; i < arr.length; i++) {
				arr = swap(arr, start, i);
				allPermutation(arr, start + 1);
				arr = swap(arr, start, i);
			}
		}
	}
	
	public Result execute() {
		long startT = System.currentTimeMillis();
		long runtimeSwap = 100000L;
		String order = "";
		for (String s : this.allPermutations) {
			long startTime = System.currentTimeMillis();
			this.numbersToCodeExample(s);
			long endTime = System.currentTimeMillis();
			long runtime = endTime - startTime;
			if(runtime<runtimeSwap) {
				runtimeSwap=runtime;
				order = s;
			}
		}
		Result result = this.numbersToCode(order);
		long endT1 = System.currentTimeMillis();
		System.out.println("Query runtime: "+DatabaseEngine.printRuntime(endT1-startT));
		System.out.println(result);
		long endT2 = System.currentTimeMillis();
		System.out.println("Total runtime: "+DatabaseEngine.printRuntime(endT2-startT));
		return result;
	}

	protected abstract void numbersToCodeExample(String s);

	protected abstract Result numbersToCode(String s);

}
