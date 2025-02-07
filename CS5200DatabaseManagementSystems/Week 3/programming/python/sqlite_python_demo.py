#!/usr/bin/env python

from __future__ import print_function

import sys
import sqlite3

##

def main(dbpath):
    
    try:
        # connect to sqlite db
        db = sqlite3.connect('file:{}?mode=ro'.format(dbpath), uri=True)
        
        # get back results as rows
        db.row_factory = sqlite3.Row
        
        with db:
            cursor = db.cursor()
            
            art_name = input("Enter an artist (type n/a for none): ")
            using_param = ( art_name != "n/a" )
            if using_param:
                sql = "SELECT art.Name AS art_name, alb.Title AS alb_title FROM artist art INNER JOIN album alb ON art.ArtistId=alb.ArtistId WHERE art.Name LIKE ? ORDER BY art_name ASC, alb_title ASC"
            else:
                sql = "SELECT art.Name AS art_name, alb.Title AS alb_title FROM album alb INNER JOIN artist art ON alb.ArtistId=art.ArtistId ORDER BY art_name ASC, alb_title ASC"
                
            print("SQL: {}".format(sql))
            
            # execute SQL, possibly with parameter
            if using_param:
                cursor.execute(sql, (art_name,))
            else:
                cursor.execute(sql)

            # get results
            while True:
                row = cursor.fetchone()
                
                if row is None:
                    break
                
                print("<{}> {}".format(row['art_name'], row['alb_title']))
        
    except sqlite3.Error as err:
        print("Error connecting to db: {}".format(err))
        

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: {} <path to chinook sqlite file>".format(sys.argv[0]))
    else:
           main(sys.argv[1])
