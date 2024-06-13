package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

	public static void main(String[] args) {
		Connections ques1 = new Connections();
		Connection connection = null;
		try {
			connection = ques1.getConnection();

			Date currentDate = new Date(System.currentTimeMillis());

//	            retriveDataByemail(connection, "gaylord.ressie@example.net");
//		       retriveDataByAutId(connection, 1);
//			UpdateEmailById(connection, 1, "sandy@example.org");
//			deleteAuthorById(connection, 2);
//			getAuthorById(connection, 1);
//            insertDataIntoPosts(connection, 101, "Sample Post 3", "Description for Sample Post 3", "Content for Sample Post 3",currentDate ,2);
//		       UpdateById(connection, 1, "Updated Post 1", "Updated Description for Post 1", "Updated Content for Post 1");
//		       deletePostById(connection,1);
//		       getPostsBydate(connection, Date.valueOf("2012-02-05"));
//	            insertData(connection, "John", "Doe", "john.doe@example.com", "1980-01-01");

//		       getAuthIdPostCount(connection);
//		       getAuthAlphabet(connection);
//		       getPostsTitleKeyWord(connection,"commodi ");
//		       getLatestPostsByAuthId(connection, 2);
//		       getLatestPostsByDateRange(connection,Date.valueOf("1986-06-27"),Date.valueOf("2018-02-05"));
//		       getAuthorsWithNoPosts(connection);
//		       getPostTitleAuthorsName(connection);
//		       getLatestPostsBeforeDate(connection,Date.valueOf("2024-01-01"));
//		       getAuthorsByPostCount(connection);
//		       getAuthorsPostCount(connection);
//		       getOldestAuther(connection);
			getLatestPosts(connection);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertData(Connection connection, String firstName, String lastName, String email,
			String birthDate) throws SQLException {
		String query = "INSERT INTO authors (first_name, last_name, email, birthdate) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, birthDate);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected.");
		}
	}

	public static void retriveDataByemail(Connection connection, String email) throws SQLException {
		String query = "SELECT * FROM authors WHERE email = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String birthDate = resultSet.getString("birthdate");

				String added = resultSet.getString("added");

				System.out.println(firstName);

			}
		}
	}

	public static void UpdateEmailById(Connection connection, int id, String email) throws SQLException {
		String query = "UPDATE authors SET email = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) updated.");
		}
	}

	public static void getAuthorById(Connection connection, int id) throws SQLException {
		String query = "SELECT * FROM authors WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String firstName = resultSet.getString("first_name");
					String lastName = resultSet.getString("last_name");
					String email = resultSet.getString("email");
					String birthDate = resultSet.getString("birthdate");
					String added = resultSet.getString("added"); // Assuming 'added' column exists
					System.out.println("firstName=" + firstName);

				}
			}
		}
	}

	public static void deleteAuthorById(Connection connection, int id) throws SQLException {
		String query = "DELETE FROM authors WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) deleted.");
		}
	}

	public static void insertDataIntoPosts(Connection connection, int id, String title, String description,
			String content, Date date, int authorId) throws SQLException {
		String query = "INSERT INTO posts (id, title, description, content, date, author_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, content);
			preparedStatement.setDate(5, date);
			preparedStatement.setInt(6, authorId);

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected.");
		}
	}

	public static void retriveDataByAutId(Connection connection, int author_id) throws SQLException {
		String query = "SELECT * FROM posts WHERE author_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, author_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String description = resultSet.getString("description");
				System.out.println("description=" + description);

			}
		}
	}

	public static void UpdateById(Connection connection, int id, String title, String description, String content)
			throws SQLException {
		String query = "UPDATE posts SET title = ?, description = ?, content = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, content);
			preparedStatement.setInt(4, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) updated.");
		}
	}

	public static void deletePostById(Connection connection, int id) throws SQLException {
		String query = "DELETE FROM posts WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) deleted.");
		}
	}

	public static void getPostsBydate(Connection connection, Date date) throws SQLException {
		String query = "SELECT * FROM posts WHERE date = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDate(1, date);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String description = resultSet.getString("description");

					System.out.println("description=" + description);

				}
			}
		}
	}

	public static void getAuthIdPostCount(Connection connection) throws SQLException {
		String query = "SELECT author_id, COUNT(author_id) AS post_count FROM posts GROUP BY author_id ORDER BY post_count DESC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int authorId = resultSet.getInt("author_id");
					int postCount = resultSet.getInt("post_count");
					System.out.println("Author ID: " + authorId + ", Post Count: " + postCount);
				}
			}
		}
	}

	public static void getPostsTitleKeyWord(Connection connection, String keyword) throws SQLException {
		String query = "SELECT * FROM posts WHERE title LIKE ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, "%" + keyword + "%");
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String content = resultSet.getString("content");
					int authorId = resultSet.getInt("author_id");
					Date createdAt = resultSet.getDate("date");

					System.out.println("\n ID: " + id + "\n Title: " + title + "\n Description: " + description
							+ "\n Content: " + content + "\n Author ID: " + authorId + "\n Created At: " + createdAt);

				}
			}
		}
	}

	public static void getAuthAlphabet(Connection connection) throws SQLException {
		String query = "SELECT * FROM authors ORDER BY first_name ASC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				Date birthdate = resultSet.getDate("birthdate");

				System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
						+ email + ", Birthdate: " + birthdate);
			}
		}
	}

	public static void getLatestPostsByAuthId(Connection connection, int author_id) throws SQLException {
		String query = "SELECT * FROM posts where author_id=? ORDER BY date DESC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, author_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String content = resultSet.getString("content");
					int authorId = resultSet.getInt("author_id");
					Date createdAt = resultSet.getDate("date");

					System.out.println("\n ID: " + id + "\n Title: " + title + "\n Description: " + description
							+ "\n Content: " + content + "\n Author ID: " + authorId + "\n Created At: " + createdAt);

				}
			}
		}
	}

	public static void getAuthorsWithNoPosts(Connection connection) throws SQLException {
		String query = "SELECT a.* FROM authors a LEFT JOIN posts p ON a.id = p.author_id WHERE p.id IS NULL";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				Date birthdate = resultSet.getDate("birthdate");

				System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
						+ email + ", Birthdate: " + birthdate);
			}
		}
	}

	public static void getLatestPostsByDateRange(Connection connection, Date start, Date end) throws SQLException {
		String query = "SELECT * FROM posts WHERE date BETWEEN ? AND ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDate(1, start);
			preparedStatement.setDate(2, end);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String content = resultSet.getString("content");
					int authorId = resultSet.getInt("author_id");
					Date createdAt = resultSet.getDate("date");

					System.out.println("\n ID: " + id + "\n Title: " + title + "\n Description: " + description
							+ "\n Content: " + content + "\n Author ID: " + authorId + "\n Created At: " + createdAt);

				}
			}
		}
	}

	public static void getPostTitleAuthorsName(Connection connection) throws SQLException {
		String query = "SELECT p.title, a.first_name FROM authors a INNER JOIN posts p ON a.id = p.author_id";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String title = resultSet.getString("title");

				System.out.println("firstName: " + firstName + ", title: " + title);
			}
		}
	}

	public static void getLatestPostsBeforeDate(Connection connection, Date beforedate) throws SQLException {
		String query = "SELECT * FROM posts WHERE date < ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDate(1, beforedate);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String content = resultSet.getString("content");
					int authorId = resultSet.getInt("author_id");
					Date createdAt = resultSet.getDate("date");

					System.out.println("\n ID: " + id + "\n Title: " + title + "\n Description: " + description
							+ "\n Content: " + content + "\n Author ID: " + authorId + "\n Created At: " + createdAt);

				}
			}
		}
	}

	public static void insertMultiAuthor(Connection connection, String firstName, String lastName, String email,
			String birthDate) throws SQLException {

	}

	public static void getAuthorsByPostCount(Connection connection) throws SQLException {
		String query = "SELECT a.id, a.first_name, a.last_name, a.email, a.birthdate, COUNT(p.author_id) AS authcount "
				+ "FROM authors a " + "LEFT JOIN posts p ON a.id = p.author_id " + "GROUP BY a.id "
				+ "ORDER BY authcount";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				Date birthdate = resultSet.getDate("birthdate");
				int postCount = resultSet.getInt("authcount");

				System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
						+ email + ", Birthdate: " + birthdate + ", PostsCount: " + postCount);
			}
		}
	}

	public static void getAuthorsPostCount(Connection connection) throws SQLException {
		String query = "SELECT a.id, a.first_name, a.last_name, a.email, a.birthdate, COUNT(p.author_id) AS authcount "
				+ "FROM authors a " + "LEFT JOIN posts p ON a.id = p.author_id "
				+ "GROUP BY a.id, a.first_name, a.last_name, a.email, a.birthdate " + "ORDER BY a.id ASC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				Date birthdate = resultSet.getDate("birthdate");
				int postCount = resultSet.getInt("authcount");

				System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
						+ email + ", Birthdate: " + birthdate + ", PostsCount: " + postCount);
			}
		}
	}

	public static void getOldestAuther(Connection connection) throws SQLException {
		String query = "SELECT * FROM authors WHERE birthdate = (SELECT MIN(birthdate) FROM authors)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				Date birthdate = resultSet.getDate("birthdate");

				System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
						+ email + ", Birthdate: " + birthdate);
			}
		}
	}

	public static void getLatestPosts(Connection connection) throws SQLException {
		String query = "SELECT * FROM posts ORDER BY date DESC LIMIT 10";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String content = resultSet.getString("content");
					int authorId = resultSet.getInt("author_id");
					Date createdAt = resultSet.getDate("date");

					System.out.println("\n ID: " + id + "\n Title: " + title + "\n Description: " + description
							+ "\n Content: " + content + "\n Author ID: " + authorId + "\n Created At: " + createdAt);

				}
			}
		}
	}

}
