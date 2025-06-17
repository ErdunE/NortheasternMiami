:- discontiguous plastic/1.

% Rules
smooth(X) :- plastic(X).
smooth(X) :- varnished(X).
ceramic(X) :- block(X).
ceramic(X) :- white(X).
polished(X) :- ceramic(X).
airy(X) :- plastic(X).

% Facts
ball(x).
plastic(x).
block(y).
green(z).
plastic(z).
varnished(w).
