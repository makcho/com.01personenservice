package com.personenservice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	public Person() {

	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	int id;

	@NotNull
	@Size(min = 3, max = 20)
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

