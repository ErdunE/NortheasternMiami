% Assignment 3 Question 3(c) - Prolog Knowledge Base
% Knowledge Representation and Reasoning

% Declare discontiguous predicates to avoid warnings
:- discontiguous cat/1.
:- discontiguous mammal/1.
:- discontiguous non_nocturnal/1.

% Facts first (to avoid unknown procedure errors)
panther(panther1).
elephant(dumbo).
african(dumbo).
man(john).
man(bob).
cat(fluffy).
mammal(rabbit).
herbivorous(rabbit).

% 1. All panthers are cats and all cats are mammals
cat(X) :- panther(X).
mammal(X) :- cat(X).

% 2. All elephants are African or Asian
% Alternative approach: define a rule instead of constraint
african_or_asian(X) :- elephant(X), african(X).
african_or_asian(X) :- elephant(X), asian(X).

% 3. No elephants are ungulates
% Define as a rule rather than constraint
not_ungulate(X) :- elephant(X).

% 4. Some cats are not nocturnal
% We have cat(fluffy) defined above
% Define non-nocturnal property
non_nocturnal(fluffy).

% 5. Not all mammals are carnivorous
% We have mammal(rabbit) and herbivorous(rabbit) defined above
% This shows not all mammals are carnivorous

% 6. The barber shaves every man who does not shave himself
shaves(barber, X) :- man(X), \+ shaves(X, X).

% Helper predicates
non_nocturnal(X) :- \+ nocturnal(X).
does_not_shave_self(X) :- \+ shaves(X, X).

% End of knowledge base
