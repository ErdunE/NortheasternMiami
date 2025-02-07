Easiest build: load as project into Eclipse, run (expects argument: path to Chinook sqlite file)!

To build (from this directory):
make

To run (from this directory):
Mac/Linux: make db=path/to/chinook.sqlite run
Windows: run path\to\chinook.sqlite

To swap MariaDB/SQLite, change the USING_SQLITE constant.
