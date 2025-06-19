% Facts from the knowledge base
apple(a).
apple(b).
banana(c).
mango(d).
carrot(e).
onion(f).
potato(g).

% Rule 3: Apple(x) ∨ Banana(x) ∨ Mango(x) ⇒ Fruit(x)
fruit(X) :- apple(X).
fruit(X) :- banana(X).
fruit(X) :- mango(X).

% Rule 4: Carrot(x) ∨ Potato(x) ⇒ Vegetable(x)
vegetable(X) :- carrot(X).
vegetable(X) :- potato(X).

% Rule 1: Fruit(x) ∨ Vegetable(x) - onion must be classified
vegetable(X) :- onion(X).

% Rule 2: Apple(x) ⇒ Red(x) ∨ Green(x)
red(a).     % Apple A is red
green(b).   % Apple B is green

% Rule 5: Fruit(x) ⇒ Tasty(x)
tasty(X) :- fruit(X).

% Rule 6: Carrot(x) ⇒ Tasty(x)
tasty(X) :- carrot(X).

% Rule 7: Onion(x) ⇒ ¬Tasty(x)
not_tasty(X) :- onion(X).

% Helper predicates
colored(X) :- red(X).
colored(X) :- green(X).
