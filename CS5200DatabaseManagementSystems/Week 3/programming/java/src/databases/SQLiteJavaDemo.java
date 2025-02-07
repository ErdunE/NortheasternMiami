package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLiteJavaDemo {

	public static void main(String[] args) throws ClassNotFoundException {

		// Using SQLite or MariaDB?
		final boolean USING_SQLITE = true;
		final String PARAM;
		if (USING_SQLITE) {
			PARAM = "path to chinook sqlite file";
		} else {
			PARAM = "host/Chinook?user=root";
		}

		if (args.length != 1) {
			System.out.printf("Usage: java %s <%s>", SQLiteJavaDemo.class.getCanonicalName(), PARAM);
			System.exit(0);
		}

		// load the sqlite-JDBC driver using the current class loader
		// make the connection URI, based on DBMS
		final String CONN_URI;
		if (USING_SQLITE) {
			Class.forName("org.sqlite.JDBC");
			CONN_URI = ("jdbc:sqlite:" + args[0]);
		} else {
			Class.forName("org.mariadb.jdbc.Driver");
			CONN_URI = ("jdbc:mariadb://" + args[0]);
		}

		// using try-with-resource automatically closes
		// connection to the database (and input scanner)
		try (final Connection connection = DriverManager.getConnection(CONN_URI);
			 final Scanner input = new Scanner(System.in); ) {

			// get input(s)
			System.out.printf("Enter an artist (type n/a for none): ");
			final String param = input.nextLine();
			final boolean usingParam = (param.compareTo("n/a") != 0);

			// generate parameterized sql
			final String sql;
			if (usingParam) {

				sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
					  " FROM artist art INNER JOIN album alb ON art.ArtistId=alb.ArtistId" +
					  " WHERE art.Name LIKE ?" +
					  " ORDER BY art_name ASC, alb_title ASC";

			} else {

				sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
					  " FROM album alb INNER JOIN artist art ON alb.ArtistId=art.ArtistId" +
					  " ORDER BY art_name ASC, alb_title ASC";

			}
			System.out.printf("%nSQL: %s%n%n", sql);

			// prepare statement
			try (final PreparedStatement stmt = connection.prepareStatement(sql)) {

				// bind parameter(s)
				if (usingParam) {
					stmt.setString( 1, param );
				}

				// get results
				try (final ResultSet res = stmt.executeQuery()) {
					while (res.next()) {
						System.out.printf("<%s> %s%n", res.getString("art_name"), res.getString("alb_title"));
					}
				}
			}

		} catch (SQLException e) {
			System.out.printf("Error connecting to db: %s%n", e.getMessage());
			System.exit(0);
		}

	}

}
