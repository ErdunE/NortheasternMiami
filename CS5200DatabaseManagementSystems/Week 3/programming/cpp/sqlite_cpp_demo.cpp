#include <iostream>
#include <string>
#include "sqlite3.h"
using namespace std;

int main(int argc, char *argv[])
{
	if ( argc != 2 )
	{
		cout << "Usage: " << argv[0] << " <path to chinook sqlite file>" << endl;
		return 0;
	}

	// connect to sqlite db
	sqlite3 *db = NULL;
	{
		int open_result = sqlite3_open_v2( argv[1], &( db ), SQLITE_OPEN_READONLY, NULL );
		if ( ( db == NULL ) || ( open_result != SQLITE_OK ) )
		{
			cout << "error connecting to db: " << sqlite3_errmsg( db ) << endl;
			return 0;
		}
	}

	// get input(s)
	string art_name;
	cout << "Enter an artist (type n/a for none): ";
	std::getline(cin, art_name);

	bool using_param = ( art_name != "n/a" );

	// generate parameterized sql
	string sql;
	if ( using_param )
	{
		sql = "SELECT art.Name AS art_name, alb.Title AS alb_title FROM artist art INNER JOIN album alb ON art.ArtistId=alb.ArtistId WHERE art.Name LIKE ? ORDER BY art_name ASC, alb_title ASC";
	}
	else
	{
		sql = "SELECT art.Name AS art_name, alb.Title AS alb_title FROM album alb INNER JOIN artist art ON alb.ArtistId=art.ArtistId ORDER BY art_name ASC, alb_title ASC";
	}

	cout << endl << "SQL: " << sql << endl;

	// prepare statement
	sqlite3_stmt *stmt;
	const char *tail;

	int rc = sqlite3_prepare_v2( db, sql.c_str(), -1, &stmt, &tail );
	if ( rc != SQLITE_OK )
	{
		cout << "Prepare error: " << sqlite3_errmsg( db ) << endl;
	}

	// bind parameter(s)
	if ( using_param )
	{
		sqlite3_bind_text( stmt, 1, art_name.c_str(), -1, SQLITE_STATIC );
	}

	// get results
	cout << endl;
	while ( sqlite3_step( stmt ) == SQLITE_ROW )
	{
		cout << "<" << sqlite3_column_text( stmt, 0 ) << "> " << sqlite3_column_text( stmt, 1 ) << endl;
	}
	cout << endl;

	// reset for next usage
	sqlite3_reset( stmt );

	// release memory from statement
	sqlite3_finalize( stmt );

	// close the database
	sqlite3_close( db );

	return 0;

}
