package com.personenservice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	public Person() {

	}

	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@NotNull(message = "ID cannot be null")
	String id;

	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 20, message = "bitte 3-20 Zeichen eintragen")
	String name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

