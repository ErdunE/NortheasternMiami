## Chomp Game - Assignment 3

### Overview

This project recreates the classic Chomp game, where two players take 
turns chomp pieces from a grid of chocolate squares. The objective is 
to avoid the poisoned square in the upper left corner. The gameplay 
is simple that when a player chomps a square, all squares to the right 
and below are removed as well. The player who is forced to chomp the 
poisoned square loses. The code is organized using the MVC pattern, 
keeping each part of the game separate and easy to update.

The main classes include Driver, Controller ,Board ,and Piece. By 
dividing in this way, the code remains modular and easy to maintain, 
allowing each class to handle a specific aspect of the game.

### Reflection

Creating this Chomp game was both challenging and exciting. Initially, 
my goal was to follow the class diagram exactly, implementing each 
part as outlined. However, as I got into the details of coding, I saw 
that some changes would improve the game’s performance and user 
experience. For instance, instead of the continuous game loop suggested 
by the design, I chose an event driven approach, which worked better 
with Java Swing. This change made the game more responsive, with 
instant updates after each player action. I also added a poison icon 
to clearly mark the dangerous square and included a slider to adjust 
the board size, making the game more visually intuitive.

One of the most valuable aspects of this project was learning to find 
the right balance between sticking to the design and making practical 
adjustments for a smoother experience. Working on the Piece class 
required careful planning to handle real-time state changes in the game. 
This reinforced the benefits of the MVC pattern, which kept the code 
organized and easy to modify. Through this project, I saw that 
following a design blueprint provides a good foundation, but adapting 
it to fit real needs often results in a better final product. Overall, 
this experience deepened my understanding of design patterns, especially 
for creating interactive applications with a clear structure.