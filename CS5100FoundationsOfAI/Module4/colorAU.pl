%% Map coloring Australia, with 3 colors

diff(red, green).
diff(red, blue).
diff(green, red).
diff(blue, red).
diff(blue, green).
diff(green, blue).


% A helper function to report results
report(State, Color) :- 
	write('State '), write(State), write(' can be colored '), 
        write(Color), nl.


% Here comes the main predicate
colorsolution() :-

diff(WAcolor, NTcolor),
diff(WAcolor, SAcolor),
diff(NTcolor, Qcolor),
diff(NTcolor,SAcolor),
diff(Qcolor,NSWcolor),
diff(Qcolor,SAcolor),
diff(NSWcolor,Vcolor),
diff(NSWcolor,SAcolor),
diff(Vcolor,SAcolor),
diff(Tcolor,_),

% When you have the solutions, print 'em out
report(wa, WAcolor), report(nt, NTcolor),report(sa, SAcolor),
                     report(q, Qcolor), report(nsw, NSWcolor), report(v, Vcolor),
                     report(t, Tcolor).

