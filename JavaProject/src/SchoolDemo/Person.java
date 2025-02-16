package SchoolDemo;

import java.util.Scanner;

abstract class Person {

	protected String name;
	protected int age;
	Scanner sc=new Scanner(System.in);
	abstract void getDetails();
	abstract void displayInfo();
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
}
