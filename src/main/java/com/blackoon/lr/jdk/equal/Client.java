package com.blackoon.lr.jdk.equal;

public class Client {
	
	public static void main(String[] args) {
		Person person =new Person("hyy","woman","1");
		Person person2 =new Person("zy", "man","1");
		System.out.println(person.equals(person2));
		System.out.println(person.hashCode());
		System.out.println(person2.hashCode());
	}
}
