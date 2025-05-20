package stream;

import java.util.ArrayList;
import java.util.List;

public class CharacterOccurence {

	public static void main(String[] args) {
		String str="bcdeafacb";
		System.out.println("first occurence "+findOccurence(str));
	}

	private static int findOccurence(String str) {
		List<Character> chList=new ArrayList<>();
		for(char ch:str.toCharArray())
		{
			if(chList.contains(ch))
			{
				return str.indexOf(ch)+1;
			}
			else
			{
				chList.add(ch);
			}
		}
		return -1;
	}
}
