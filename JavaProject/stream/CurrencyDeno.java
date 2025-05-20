package stream;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CurrencyDeno {

	public static void main(String[] args) {
		int amount = 2900;
		int arr[] = { 500, 200, 100 };
		Map<Integer, Integer> hMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (amount > 0) {

				int deno = amount / arr[i];
				hMap.put(arr[i], deno);
				amount %= arr[i];
			} else {
				hMap.put(arr[i], 0);
			}

		}
		int totalNotes = 0;
		for (Entry<Integer, Integer> entry : hMap.entrySet()) {
			System.out.println(entry.getKey() + " * " + entry.getValue());
			totalNotes += entry.getValue();

		}
		System.out.println("Total Notes " + totalNotes);
	}

}
