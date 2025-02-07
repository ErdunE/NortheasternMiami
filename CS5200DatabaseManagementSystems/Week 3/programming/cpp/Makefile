UNAME_S := $(shell uname -s)

CC := gcc
CPP := g++

ifeq ($(UNAME_S),Linux)	
	EXTRA := -ldl -lpthread
else ifeq ($(UNAME_S),Darwin)
	EXTRA := 
endif

all:
	$(CC) -c -o sqlite3.o sqlite3.c
	$(CPP) -o sqlite_cpp_demo sqlite_cpp_demo.cpp sqlite3.o $(EXTRA)

clean:
	rm -f sqlite3.o sqlite_cpp_demo
