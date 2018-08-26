package com.personenservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

public class PersonDAO {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
//	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public Connection connection = null;
//	String url = "jdbc:mysql://localhost/personendb?user=root&password=123456";
	String url = "jdbc:mysql://localhost:3306/personendb";
	String username = "root";
	String password = "123456";

	public PersonDAO() {
	}

	public void getConnectToDatabase() throws InstantiationException, IllegalAccessException {

		try {
			// load the Driver
			Class.forName(DRIVER).newInstance();
			System.out.println("Driver loaded!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}

		try {
			// open the connection to the DB
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connection to DB is open");
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public void disconnectDatabase() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("disconnected");
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public boolean insertPerson(String id, String name) throws Exception {
		String sql = "INSERT INTO PERSONEN (IDENT, NAME) VALUES (?,?)";

		getConnectToDatabase();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, name);

		boolean personInsertet = preparedStatement.executeUpdate() > 0;
		System.out.println("a single Person was created");
		preparedStatement.close();
		disconnectDatabase();
		return personInsertet;
	}

	public void validatePerson(String id, String name) throws Exception {

		String sql = "SELECT * FROM PERSONEN WHERE IDENT = ?";

		getConnectToDatabase();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		//create new Person if not existing
		if (resultSet.next() == false) {
			insertPerson(id, name);
		} else {
			throw new Exception("Person is existing");
		}
		
		disconnectDatabase();
	}

	public boolean deletePersonById(String id) throws SQLException, InstantiationException, IllegalAccessException {
		String sql = "DELETE FROM PERSONEN WHERE IDENT = ?";
		getConnectToDatabase();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);

		boolean personDeleted = preparedStatement.executeUpdate() > 0;
		System.out.println("person has been deleted by id");
		preparedStatement.close();
		disconnectDatabase();
		return personDeleted;
	}

	public boolean updatePersonById(String id, String name) throws SQLException, InstantiationException, IllegalAccessException {
//		String sql = "UPDATE PERSON SET NAME = ?";
//		sql += " WHERE IDENT = ?";
		String sql = "UPDATE PERSONEN SET NAME = ? WHERE IDENT = ?";
		getConnectToDatabase();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, id);

		boolean personUpdate = preparedStatement.executeUpdate() > 0;
		System.out.println("3");
		preparedStatement.close();
		disconnectDatabase();
		return personUpdate;
	}

	public Person getSinglePersonById(String id) throws SQLException, InstantiationException, IllegalAccessException {
		Person person = null;
		String sql = "SELECT * FROM PERSONEN WHERE IDENT = ?";

		getConnectToDatabase();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");

//			person = new Person(id, name);
		}

		resultSet.close();
		preparedStatement.close();
		disconnectDatabase();
		return person;
	}

	public void listAllThePersons() throws SQLException, InstantiationException, IllegalAccessException {
		
		String sql = "SELECT * FROM PERSONEN";
		JsonRepository jsonRepository = new JsonRepository();
		
		getConnectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			//write from mysql into json file 
			jsonRepository.putValuesIntoJsonObject(id, name);

		}

		resultSet.close();
		statement.close();
		disconnectDatabase();

	}
}
