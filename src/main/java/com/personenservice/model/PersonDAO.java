package com.personenservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public Connection connection = null;
	String url = "jdbc:mysql://localhost:3306/personendb?autoReconnect=true&useSSL=false";
	String username = "root";
	String password = "123456";

	public PersonDAO() {
	}

	public void getConnectToDatabase() {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public void disconnectDatabase() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public boolean insertPerson(Person person) throws Exception {
		String sql = "INSERT INTO PERSONEN (IDENT, NAME) VALUES (?,?)";
		getConnectToDatabase();
		
		String getLenghtOfTheName = person.getName();
	
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getName());

			boolean personInsertet = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
			disconnectDatabase();
			return personInsertet;
		
		
	}


	public boolean deletePerson(Person person) throws SQLException {
		String sql = "DELETE FROM PERSONEN WHERE IDENT = ?";
		getConnectToDatabase();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, person.getId()); 

		boolean personDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnectDatabase();
		return personDeleted;
	}
	
	public boolean updatePerson(Person person) throws SQLException {
		String sql = "UPDATE PERSON SET NAME = ?";
		sql += " WHERE IDENT = ?";
		getConnectToDatabase();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, person.getName());
		preparedStatement.setInt(2, person.getId());
		
		boolean personUpdate = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnectDatabase();	
		return personUpdate;
	}
	
	public Person getSinglePerson(int id) throws SQLException {
		Person person = null;
		String sql = "SELECT * FROM PERSONEN WHERE IDENT = ?";
		
		getConnectToDatabase();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			
			person = new Person(id, name);
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return person;
	}
	
	public List<Person> listAllThePersons() throws SQLException {
		List<Person> listAllPersons = new ArrayList<>();
				
		String sql = "SELECT * FROM PERSONEN";
		getConnectToDatabase();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Person person = new Person(id, name);
			listAllPersons.add(person);
		}

		resultSet.close();
		statement.close();

		disconnectDatabase();

		return listAllPersons;
	}
}
