package stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class CurrencyDenominations {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of notes");
		int numOfNotes = sc.nextInt();
		List<Integer> aList = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		System.out.println("Enter Notes");
		for (int i = 0; i < numOfNotes; i++) {
			int a = sc.nextInt();
			aList.add(a);
		}
		Collections.sort(aList);
		Collections.reverse(aList);
		System.out.println("sorted " + aList);
		System.out.println("Enter amount u want to pay");
		int amt = sc.nextInt();
		while (amt > 0 && !aList.isEmpty()) {
			int note = aList.removeFirst();
			int deno = amt / note;
			map.put(note, deno);
			amt %= note;
		}
		if (aList.isEmpty() && amt != 0) {
			System.out.println("Cannot calculate exact denomination amount pending to pay = " + amt);
		}
		int totalNotes = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " * " + entry.getValue());
			totalNotes += entry.getValue();
		}
		System.out.println("Total Notes " + totalNotes);
		sc.close();
	}
}
