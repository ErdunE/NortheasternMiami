all:
	mkdir -p bin
	javac -d bin src/databases/SQLiteJavaDemo.java

run:
	java -cp bin:lib/sqlite-jdbc-3.47.2.0.jar:lib/mariadb-java-client-3.5.1.jar databases.SQLiteJavaDemo $(db)
	
clean:
	rm -rf bin
