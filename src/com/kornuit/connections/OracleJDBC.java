package com.kornuit.connections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import scrumbag.domain.Kornuit;

import com.kornuit.calendar.Afspraak;
import com.kornuit.util.SecurePropertyLoader;

public class OracleJDBC {

	/**
	 * Connect to the database.
	 * @param path Connection path to database
	 * @return Connection object
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 */
	public static Connection getConnectionOra(String path)
			throws FileNotFoundException, IOException {
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {
			Properties p = SecurePropertyLoader.getSecureProperties(path);
			System.out.println(path + "connections.properties");

			String address = p.getProperty("database_address");
			String sid = p.getProperty("database_sid");
			String username = p.getProperty("database_user");
			String password = p.getProperty("database_password");
			int port = Integer.parseInt(p.getProperty("database_port"));

			System.out.println("URL -> " + "jdbc:oracle:thin:@//" + address
					+ ":" + port + "/" + sid);
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//"
					+ address + ":" + port + "/" + sid, username, password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			return connection;
		} else {
			System.out.println("Failed to make connection!");
		}
		return null;
	}

	/**
	 * Validates username and password in database.
	 * @param path Connection path to database
	 * @param username String username
	 * @param password String password
	 * @return boolean if username and password are valid
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static boolean validateUser(String path, String username,
			String password) throws SQLException, FileNotFoundException,
			IOException {

		Connection c = getConnectionOra(path);
		PreparedStatement validateUser = c
				.prepareStatement("SELECT * FROM KORNUIT_USER WHERE USERNAME = ? AND PASSWORD = ?");
		validateUser.setString(1, username);
		validateUser.setString(2, password);

		ResultSet result = validateUser.executeQuery();
		if (result.next() == false) {
			System.out.println("Result is empty, deniying user.");
			return false;
		}

		System.out.println("Execute query");

		result = validateUser.executeQuery();

		while (result.next() == true) {
			System.out.println("Execute query inner");
			String username_result = result.getString(1);
			System.out.println(username_result);
			String password_result = result.getString(2);
			System.out.println(password_result);
			String isBanned = result.getString(3);
			System.out.println(isBanned);
			if (isBanned.equalsIgnoreCase("Y")) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks id the given user has linked his FaceBook account in the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @return boolean if user has linked his FaceBook account
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static boolean hasFacebookToken(String path, String username)
			throws SQLException, FileNotFoundException, IOException {

		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_ACCESS_TOKEN WHERE USERNAME = ? AND TYPE = ?");
		query.setString(1, username);
		query.setString(2, "FACEBOOK");

		ResultSet result = query.executeQuery();
		if (result.next() == false) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Checks id the given user has linked his Twitter account in the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @return boolean if user has linked his Twitter account
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static boolean hasTwitterToken(String path, String username)
			throws SQLException, FileNotFoundException, IOException {

		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_ACCESS_TOKEN WHERE USERNAME = ? AND TYPE = ?");
		query.setString(1, username);
		query.setString(2, "TWITTER");

		ResultSet result = query.executeQuery();
		if (result.next() == false) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Checks id the given user has linked his Google account in the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @return boolean if user has linked his Google account
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static boolean hasGoogleToken(String path, String username)
			throws FileNotFoundException, IOException, SQLException {
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_ACCESS_TOKEN WHERE USERNAME = ? AND TYPE = ?");
		query.setString(1, username);
		query.setString(2, "GOOGLE");

		ResultSet result = query.executeQuery();
		if (result.next() == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Retrieves an access token of the given user from the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @param type String type of access token, example FaceBook or Google
	 * @return String Access token as a String
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static String getToken(String path, String username, String type)
			throws FileNotFoundException, IOException, SQLException {
		System.out.println("PREPARING TO RETURN TOKEN");
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_ACCESS_TOKEN WHERE USERNAME = ? AND TYPE = ?");
		query.setString(1, username);
		query.setString(2, type);

		ResultSet r = query.executeQuery();
		while (r.next()) {
			System.out.println("RETURNING KEY " + r.getString(2));
			return r.getString(2);
		}
		return null;
	}

	/**
	 * Saves an access token from a user in the database.
	 * @param path Connection path to database
	 * @param token String the access token that has to be saved
	 * @param type String type of access token, example FaceBook or Google
	 * @param username String username
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static void saveToken(String path, String token, String type,
			String username) throws FileNotFoundException, IOException,
			SQLException {
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("INSERT INTO KORNUIT_ACCESS_TOKEN (USERNAME, ACCESS_TOKEN, TYPE)  VALUES(?,?,?)");
		query.setString(1, username);
		query.setString(2, token);
		query.setString(3, type);
		query.execute();
	}

	/**
	 * Deletes an access token from a user in the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @param type String type of access token, example FaceBook or Google
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static void deleteToken(String path, String username, String type)
			throws SQLException, FileNotFoundException, IOException {
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("DELETE FROM KORNUIT_ACCESS_TOKEN WHERE USERNAME = ? AND TYPE = ?");
		query.setString(1, username);
		query.setString(2, type);
		query.execute();
	}

	/**
	 * Will save a new List with all 'Kornuiten' to the database.
	 * @param path Connection path to database
	 * @param username String username
	 * @param all_kornuiten List with all 'Kornuiten' of a certain user
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static void saveKornuit(String path, String username,
			List<Kornuit> all_kornuiten) throws FileNotFoundException,
			IOException, SQLException {
		Connection c = getConnectionOra(path);

		for (Kornuit k : all_kornuiten) {

			try {
				System.out.println("ATTEMPTING WRITING KORNUIT -> " + k.getName()
						+ " TO DATABASE.");
				PreparedStatement query = c
						.prepareStatement("INSERT INTO KORNUIT_FRIEND (ID, NAME, LAST_APPOINTMENT, SCORE, IGNORE, USERNAME) "
								+ "VALUES (?,?,?,?,?,?)");

				query.setString(1, k.getId());
				query.setString(2, k.getName());
				java.sql.Date sqlDate = new java.sql.Date(k
						.getLastAppointment().getTime());
				query.setDate(3, sqlDate);
				query.setFloat(4, k.getScore());
				String ignored = k.isIgnored() ? "Y" : "N";
				query.setString(5, ignored);
				query.setString(6, username);

				query.execute();
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				System.err.println("User already exists, skipping");
			}

		}

	}

	/**
	 * Retrieves all 'Kornuiten' of the given user.
	 * @param path Connection path to database
	 * @param username String username
	 * @return List with all 'Kornuiten' of a certain user
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static List<Kornuit> getKornuiten(String path, String username) throws FileNotFoundException, IOException, SQLException {
		
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_FRIEND WHERE USERNAME = ?");
		query.setString(1, username);
		
		
		ResultSet set = query.executeQuery();
		List<Kornuit> alle_Kornuiten = new ArrayList<Kornuit>();
		while(set.next()){
			Kornuit k = new Kornuit();
			k.setId(set.getString(1));
			k.setName(set.getString(2));
			Date d = set.getDate(3);
			java.util.Date newDate = new java.util.Date(d.getTime());
			k.setLastAppointment(newDate);
			k.setScore(set.getFloat(4));
			String ignore = set.getString(5);
			if(ignore.equalsIgnoreCase("Y")){
				k.setIgnore(true);
			}else{
				k.setIgnore(false);
			}
			alle_Kornuiten.add(k);
		}
		return alle_Kornuiten;
		
	}
	
	/**
	 * Retrieves all appointments of the given user.
	 * @param path Connection path to database
	 * @param username String username
	 * @return List with all appointments of a certain user
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static List<Afspraak> getAfspraken(String path, String username) throws FileNotFoundException, IOException, SQLException {
		
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("SELECT * FROM KORNUIT_APPOINTMENT WHERE APP_USER = ?");
		query.setString(1, username);
		
		ResultSet set = query.executeQuery();
		List<Afspraak> alle_Afspraken = new ArrayList<Afspraak>();
		while(set.next()){
			Afspraak a = new Afspraak();
			a.setId(set.getInt(1));
			a.setUser(set.getString(2));
			a.setDatumTijd(set.getTimestamp(3));
			a.setLocatie(set.getString(4));
			a.setActiviteit(set.getString(5));
			a.setFacebookVriendId(set.getString(6));
			a.setFacebookVriendNaam(set.getString(7));
			
			alle_Afspraken.add(a);
		}
		return alle_Afspraken;
	}
	
	/**
	 * Creates a new appointment in the database.
	 * @param path Connection path to database
	 * @param afspraak New Afspraak object
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	public static void nieuweAfspraak(String path, Afspraak afspraak) throws FileNotFoundException, IOException,
			SQLException {
		Connection c = getConnectionOra(path);
		PreparedStatement query = c
				.prepareStatement("INSERT INTO KORNUIT_APPOINTMENT (APP_ID, APP_USER, APP_TIME, APP_LOCATION, APP_ACTIVITY, APP_FRIEND_FACEBOOK_ID, APP_FRIEND_NAME)"
						+ "VALUES(KORNUIT_APPOINTMENT_SEQ.nextval,?,?,?,?,?,?)");
		query.setString(1, afspraak.getUser());
		query.setTimestamp(2, afspraak.getDatumTijd());
		query.setString(3, afspraak.getLocatie());
		query.setString(4, afspraak.getActiviteit());
		query.setString(5, afspraak.getFacebookVriendId());
		query.setString(6, afspraak.getFacebookVriendNaam());
		
		query.execute();
	}
}
