package com.personenservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

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
			//load the Driver
			Class.forName(DRIVER).newInstance();
			System.out.println("Driver loaded!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}

		try {
			//open the connection to the DB
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
	
	public boolean updatePersonById(Person person) throws SQLException, InstantiationException, IllegalAccessException {
		String sql = "UPDATE PERSON SET NAME = ?";
		sql += " WHERE IDENT = ?";
		getConnectToDatabase();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, person.getName());
//		preparedStatement.setInt(2, person.getId());
		
		boolean personUpdate = preparedStatement.executeUpdate() > 0;
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
		
		return person;
	}
	
	public List<Person> listAllThePersons() throws SQLException, InstantiationException, IllegalAccessException {
		List<Person> listAllPersons = new ArrayList<>();
				
		String sql = "SELECT * FROM PERSONEN";
		getConnectToDatabase();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
//			Person person = new Person(id, name);
//			listAllPersons.add(person);
		}

		resultSet.close();
		statement.close();

		disconnectDatabase();

		return listAllPersons;
	}
}
