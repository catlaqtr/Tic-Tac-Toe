# Tic-Tac-Toe with AI

A Tic-Tac-Toe game with three AI difficulty levels implemented in Java.

## Features

- **Easy AI**: Random moves
- **Medium AI**: Blocks opponent wins and takes winning moves
- **Hard AI**: Minimax algorithm - plays perfectly and is unbeatable

## How to Play

Start a game with: `start <player1> <player2>`

Players can be: `user`, `easy`, `medium`, or `hard`

Examples:
- `start user hard` - Play against hard AI
- `start easy medium` - Watch AI battle
- `start user user` - Two player mode

Enter coordinates (1-3) when prompted:
```
Enter the coordinates: 2 2
```

Type `exit` to quit.

## Running

```bash
./gradlew run
```

