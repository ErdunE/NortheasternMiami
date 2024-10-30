package main;

import states.*;
import pieces.*;

public class ChessGame {
	State state;
	
	//You can initialize the game in any state, but this is the default one
	//That I am passing through here.
	//You will also probably need to initialize any pieces and other objects
	//as part of starting the game.
	public ChessGame() {
		//Each state constructor should pass an object of the chess game through it.
		//The state is modifying the behavior of the core object (the chess game)
		//by overriding what methods do in that state.
		state = new NormalPlayState(this);
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	//In each state class, you will need to write methods that will do different actions based on what
	//state the game is in. For example, perhaps a "take turn" method that
	//changes who it prompts to go (player 1 versus player 2) depending
	//on whose turn it is in the game?
	
	//The state-specific behavior of each method ought to be specified in
	//each state. Reference the video linked in the lesson outline for details.
	
	//You will invoke the state methods in here as part of a simple loop
	//Refer to how we set up text commands in Assignment 1 to manage a game 
	//loop that takes instructions through command-line input.

	public void startGame(){

	}
	
	
}
