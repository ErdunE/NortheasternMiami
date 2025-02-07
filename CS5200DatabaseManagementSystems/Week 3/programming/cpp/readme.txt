To build:
0) Use Makefile on Mac/Linux, make.bat on Windows (assumes gcc/g++ in path) OR
1) gcc -c -o sqlite3.o sqlite3.c
2) g++ -o sqlite_cpp_demo sqlite_cpp_demo.cpp sqlite3.o
   Linux: add "-ldl -lpthread" to the end
