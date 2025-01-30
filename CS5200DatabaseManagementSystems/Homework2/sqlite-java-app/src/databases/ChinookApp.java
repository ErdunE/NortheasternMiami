package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

/**
 * Command-line application for a set of queries
 * on the Chinook database 
 * 
 * SQLite database and supports five predefined queries 
 * that retrieve data from the Chinook database. The 
 * queries include retrieving employees, customers, 
 * invoices, and other relevant information.
 * 
 * @author Erdun E
 * @version 1.0
 * @since 2025-01-24
 */
public class ChinookApp {
	
	/**
	 * Allowed queries
	 */
	private enum QueryType {
		CUSTOMER_BY_COUNTRY(
			"Count customers in country",
			true
		),
		
		ALL_EMPLOYEES(
			"List all employees (sorted by employee id)",
			false
		),
		
		CUSTOMERS_BY_EMPLOYEE_ID(
			"Count customers supported by employee id",
			true
		),
		
		ALL_CUSTOMERS(
			"List all customers (sorted by customer id)",
			false
		),
		
		INVOICES_BY_CUSTOMER_ID(
			"List all invoices (sorted by invoice id, each line by invoice line id) for customer id",
			true
		);
		
		/**
		 * Query description
		 */
		public final String desc;
		
		/**
		 * Whether the query requires
		 * a parameter value
		 */
		public final boolean needsParam;
		
		private QueryType(String desc, boolean needsParam) {
			this.desc = desc;
			this.needsParam = needsParam;
		}
		
		@Override
		public String toString() {
			final String fmt;
			if (needsParam) {
				fmt = "%s [parameter value]";
			} else {
				fmt = "%s";
			}
			
			return String.format(fmt, desc);
		}
	}
	
	/**
	 * Query and associated parameter 
	 * value (which is null if not appropriate)
	 * 
	 * @author derbinsky
	 */
	private static class QueryData {
		public final QueryType queryType;
		public final String queryParam;
		
		public QueryData(QueryType qn, String qp) {
			queryType = qn;
			queryParam = qp;
		}
		
		@Override
		public String toString() {
			final String formatString;
			if (queryParam == null) {
				formatString = "%s";
			} else {
				formatString = "%s (%s)";
			}
			
			return String.format(formatString, queryType, queryParam);
		}
	}
	
	/**
	 * Read-Only configuration
	 */
	private static final SQLiteConfig roConfig;
	static {
		roConfig = new SQLiteConfig();
		roConfig.setReadOnly(true);
	}
	
	/**
	 * Usage statement
	 * 
	 * @return null (to make other code easier)
	 */
	private static QueryData usage() {
		System.out.printf(
			"Usage: java %s <path to Chinook database> <query #> [parameter value]%n%n", 
			ChinookApp.class.getCanonicalName()
		);
		
		for (QueryType qt : QueryType.values()) {
			System.out.printf(
				"%d) %s%n",
				qt.ordinal() + 1,
				qt
			);
		}
		System.out.println();
		
		return null;
	}
	
	/**
	 * Prefix JDBC info to SQLite database path
	 * 
	 * @param path path to database
	 * @return JDBC connection string
	 */
	private static String sqliteConnection(String path) {
		return String.format("jdbc:sqlite:%s", path);
	}
	
	/**
	 * Validates SQLite connection to supplied database path
	 * 
	 * @param path path to database
	 * @return true if can connect to SQLite database
	 * @throws ClassNotFoundException cannot find JDBC driver
	 */
	private static boolean validateSQLite(String path) throws ClassNotFoundException {
		// attempt connecting to the database
		Class.forName( "org.sqlite.JDBC" );
		try (final Connection connection = DriverManager.getConnection(sqliteConnection(path), roConfig.toProperties())) {
			// means connection was successful
			final String testSQL = "SELECT COUNT(*) AS ct FROM artist WHERE Name=?";
			final String testParam = "Brandi Carlile";
			final int testExpected = 0;
			
			try (final PreparedStatement stmt = connection.prepareStatement(testSQL)) {
				stmt.setString(1, testParam);
				
				try (final ResultSet res = stmt.executeQuery()) {
					final int ct = res.getInt("ct");
					
					if (ct != testExpected) {
						System.out.println("Invalid databse contents");
					}
					
					return (ct == testExpected);
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid database");
			return false;
		}
	}
	
	/**
	 * Validates command-line arguments
	 * 
	 * @param args command-line arguments
	 * @return query data, or null if invalid
	 * @throws ClassNotFoundException cannot find JDBC driver
	 */
	private static QueryData validateInputs(String[] args) throws ClassNotFoundException {
		// must have at least two arguments
		if (args.length < 2) {
			return usage();
		}
		
		// must be able to connect to the database
		if (!validateSQLite(args[0])) {
			return usage();
		}
		
		// attempt converting first supplied parameter
		// to a number
		final int queryNum;
		try {
			queryNum = Integer.parseInt(args[1]) - 1;
		} catch (NumberFormatException e) {
			return usage();
		}
		
		// make sure second argument is a valid query number
		// and third is appropriate to query
		if ((queryNum >= 0) && (queryNum < QueryType.values().length)) {
			final QueryType qt = QueryType.values()[queryNum];

			// parameter value in slot 3
			if (qt.needsParam && (args.length != 3)) {
				return usage();
			}
			
			// no parameter value
			if (!qt.needsParam && (args.length != 2)) {
				return usage();
			}
			
			if (qt.needsParam) {
				return new QueryData(qt, args[2]);
			} else {
				return new QueryData(qt, null);
			}
		} else {
			return usage();
		}
	}

	/**
	 * Command-line Chinook utility
	 * 
	 * @param args command-line arguments
	 * @throws ClassNotFoundException cannot find JDBC driver
	 * @throws SQLException SQL gone bad
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// validates the inputs, exits if bad
		final QueryData qd = validateInputs(args);
		if (qd == null) {
			System.exit(1);
		}
		
		// makes a connection to the database
		try (final Connection connection = DriverManager.getConnection(sqliteConnection(args[0]), roConfig.toProperties())) {
			if (qd.queryType == QueryType.CUSTOMER_BY_COUNTRY) {
				// Query to count the number of customers in a specific country.
				// Expects the country name as a parameter (qd.queryParam).
				final String sql = "SELECT COUNT(*) AS ct FROM Customer WHERE Country = ?";
				// Using try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically.
				try(final PreparedStatement stmt = connection.prepareStatement(sql)){
					// Sets the country parameter
					stmt.setString(1, qd.queryParam);
					// Executes the query and processes the result set.
					try(final ResultSet res = stmt.executeQuery()){
						if(res.next()) {
							// Retrieves the count from the query result and prints it to the console.
							final int count = res.getInt("ct");
							System.out.printf("%d%n", count);
						}
					}
				}
			} else if (qd.queryType == QueryType.ALL_EMPLOYEES) {
				// Query to list all employees, sorted by EmployeeId.
			    // Does not require any parameter.
				final String sql = "SELECT EmployeeId, FirstName, LastName, Title From Employee ORDER BY EmployeeId";
				// Using try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically.
				try(final PreparedStatement stmt = connection.prepareStatement(sql)){
					// Execute the query to retrieve all employees.
					try(final ResultSet res = stmt.executeQuery()){
						// Print each employee's details
						while (res.next()) {
						    System.out.printf(
						        "%d. %s %s (%s)%n",
						        res.getInt("EmployeeId"),
						        res.getString("FirstName"),
						        res.getString("LastName"),
						        res.getString("Title")
						    );
						}
					}
				}
			} else if (qd.queryType == QueryType.CUSTOMERS_BY_EMPLOYEE_ID) {
				// Query is parameterized to accept the SupportRepId
				final String sql = "SELECT COUNT(*) AS CustomerCount FROM Customer WHERE SupportRepId = ?";
				// Using try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically.
				try(final PreparedStatement stmt = connection.prepareStatement(sql)){
					// Setting the parameter SupportRepId using the value provided in qd.queryParam.
					stmt.setInt(1, Integer.parseInt(qd.queryParam));
					// Execute the query to retrieve the count of customers.
					try(final ResultSet res = stmt.executeQuery()){
						// Check if a result exists, which is expected since we are using COUNT(). If no result is found, default to 0.
						if (res.next()) {
			                System.out.printf("%d%n", res.getInt("CustomerCount"));
			            } else {
			                System.out.println("0");
			            }
					}
				}
			} else if (qd.queryType == QueryType.ALL_CUSTOMERS) {
				// Query to list all customers, including their ID, name, city, state, and country.
				final String sql = "SELECT CustomerId, FirstName, LastName, City, State, Country FROM Customer ORDER BY CustomerId";
				// Using try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically.
				try (final PreparedStatement stmt = connection.prepareStatement(sql)) {
					// Execute the query and retrieve the result set.
					try (final ResultSet res = stmt.executeQuery()) {
						while (res.next()) {
							// Extract customer details from the result set.
							final int customerId = res.getInt("CustomerId");
			                final String firstName = res.getString("FirstName");
			                final String lastName = res.getString("LastName");
			                final String city = res.getString("City");
			                final String state = res.getString("State");
			                final String country = res.getString("Country");

			                // Check if the state is null or empty, and format the output accordingly.
			                if (state == null || state.isEmpty()) {
			                    System.out.printf(
			                        "%d. %s %s (%s, %s)%n",
			                        customerId,
			                        firstName,
			                        lastName,
			                        city,
			                        country
			                    );
			                } else {
			                    System.out.printf(
			                        "%d. %s %s (%s, %s, %s)%n",
			                        customerId,
			                        firstName,
			                        lastName,
			                        city,
			                        state,
			                        country
			                    );
			                }
			            }
					}
				}
			} else if (qd.queryType == QueryType.INVOICES_BY_CUSTOMER_ID) {
				// Query to get all invoices for a specific customer
				final String sqlInvoice = 
				        "SELECT InvoiceId, Total " +
				        "FROM Invoice " +
				        "WHERE CustomerId = ? " +
				        "ORDER BY InvoiceId";
				
				// Query to get detailed invoice line items for a specific invoice
				final String sqlInvoiceLine = 
				        "SELECT InvoiceLineId, Track.Name AS TrackName, Album.Title AS AlbumTitle, Artist.Name AS ArtistName, " +
				        "       InvoiceLine.UnitPrice, InvoiceLine.Quantity " +
				        "FROM InvoiceLine " +
				        "INNER JOIN Track ON InvoiceLine.TrackId = Track.TrackId " +
				        "INNER JOIN Album ON Track.AlbumId = Album.AlbumId " +
				        "INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId " +
				        "WHERE InvoiceLine.InvoiceId = ? " +
				        "ORDER BY InvoiceLineId";
				// Using try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically.
				try (final PreparedStatement stmtInvoice = connection.prepareStatement(sqlInvoice)) {
					// Setting the CustomerId parameter
					stmtInvoice.setString(1, qd.queryParam);
			        try (final ResultSet resInvoice = stmtInvoice.executeQuery()) {
			            boolean hasInvoices = false;

			            // Loop through each invoice for the customer
			            while (resInvoice.next()) {
			                hasInvoices = true;
			                final int invoiceId = resInvoice.getInt("InvoiceId");
			                final double total = resInvoice.getDouble("Total");

			                // Print invoice summary
			                System.out.printf("Invoice #%d ($%.2f)%n", invoiceId, total);
		            
			                // Query invoice line items for the current invoice
			                try (final PreparedStatement stmtInvoiceLine = connection.prepareStatement(sqlInvoiceLine)) {
			                    stmtInvoiceLine.setInt(1, invoiceId);
			                    try (final ResultSet resInvoiceLine = stmtInvoiceLine.executeQuery()) {
			                    	// Loop through each line item and print details
			                    	while (resInvoiceLine.next()) {
			                            final int invoiceLineId = resInvoiceLine.getInt("InvoiceLineId");
			                            final String trackName = resInvoiceLine.getString("TrackName");
			                            final String albumTitle = resInvoiceLine.getString("AlbumTitle");
			                            final String artistName = resInvoiceLine.getString("ArtistName");
			                            final double unitPrice = resInvoiceLine.getDouble("UnitPrice");
			                            final int quantity = resInvoiceLine.getInt("Quantity");

			                            System.out.printf(
			                                " %d: '%s' on '%s' by '%s' (%d @ $%.2f)%n",
			                                invoiceLineId,
			                                trackName,
			                                albumTitle,
			                                artistName,
			                                quantity,
			                                unitPrice
			                            );
			                        }
			                    }
			                }
			                // Add a blank line after each invoice for better readability
			                System.out.println();
			            }
			            // If the customer has no invoices, print a blank line
			            if (!hasInvoices) {
			                System.out.println();
			            }
			        }
			    }
			}
		}
	}
}
