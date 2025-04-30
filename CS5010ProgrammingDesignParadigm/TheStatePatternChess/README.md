# CS5010-The-State-Pattern-in-Chess
Recititation 9 of CS5010 in Fall 2024
## Table of Contents
* [Team member](#team-member)
* [Breakdown plan](#breakdown-plan)
  * [Erdun](#erdun)
  * [Raj](#raj)
  * [Kai](#kai)
  * [Hailee](#hailee)
  * [Nav](#nav)
* [Chess Project Structure](#chess-project-structure)

## Team member
* Erdun
* Raj
* Kai
* Hailee
* Nav

## Breakdown plan
### Erdun
* main package except Board
* state - checkMate
### Raj
* pieces - Rook, Pawn
* mian - Board
* state - playerTurn
### Kai
* state - check
### Hailee
* state - normalPlayState
* state - State
### Nav
* state - gameStart
* pieces - King, Logic, Piece

## Chess Project Structure
```aiignore
CS5010-The-State-Pattern-in-Chess
│
├── main               (The core logic package of the game)
│   ├── Board.java          (Raj)
│   ├── ChessDriver.java    (Erdun)
│   ├── ChessGame.java      (Erdun)
│   └── Node.java           (Erdun)
│
├── pieces             (Pieces and their logic)
│   ├── King.java           (Nav)
│   ├── Rook.java           (Raj)
│   ├── Pawn.java           (Raj)
│   ├── Logic.java          (Nav)
│   └── Piece.java          (Nav)
│
├── states             (Different states of the game)
│   ├── State.java          (Hailee)
│   ├── GameStartState.java (Nav)
│   ├── NormalPlayState.java (Hailee)
│   ├── PlayerTurnState.java (Raj)
│   ├── CheckState.java     (Kai)
│   └── CheckmateState.java (Erdun)
```