% it is a crime to sell weapons%   to hostile nations
criminal(X):-american(X),weapon(Y),sells(X,Y,Z),   hostile(Z).

% Nono ... has some missiles,
owns(nono,m1).
missile(m1).

% all of its missiles were sold to it%   by Colonel West
sells(west,X,nono) :- missile(X),
    owns(nono,X).

% Missiles are weapons
weapon(X) :- missile(X).

% An enemy of America counts as ``hostile''
hostile(X) :- enemy(X,america).

% The country Nono, an enemy of America ...
enemy(nono,america).

% West, who is American ...
american(west).
