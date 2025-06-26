append([],Y,Y).
append([X|L],Y,[X|Z]) :- append(L,Y,Z). 
