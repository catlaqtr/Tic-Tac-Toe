# Tic-Tac-Toe with AI (Java)

A feature-rich Tic-Tac-Toe game implementation with three AI difficulty levels using advanced algorithms.

## 📋 Description

Everybody remembers this paper-and-pencil game from childhood: Tic-Tac-Toe, also known as Noughts and Crosses or Xs and Os. This project implements a complete Tic-Tac-Toe game with intelligent AI opponents that can play at different skill levels.

## ✨ Features

- **User vs User**: Classic two-player mode
- **User vs AI**: Play against computer opponents
- **AI vs AI**: Watch AI opponents battle each other
- **Three AI Difficulty Levels**:
  - **Easy**: Makes random moves
  - **Medium**: Looks one move ahead to win or block opponent
  - **Hard**: Uses Minimax algorithm for perfect play (unbeatable!)

## 🎮 How to Play

### Starting the Game

Run the program and use the `start` command with two parameters:

```
Input command: start <player1> <player2>
```

Valid player types: `user`, `easy`, `medium`, `hard`

### Examples

- User vs Hard AI: `start user hard`
- Easy AI vs Medium AI: `start easy medium`
- User vs User: `start user user`
- Hard AI vs Hard AI: `start hard hard`

### Making Moves

When it's your turn, enter coordinates (1-3) for row and column:
```
Enter the coordinates: 2 2
```

### Exiting

Type `exit` to quit the program.

## 🧠 AI Implementation

### Easy Level
- Makes completely random moves
- No strategic thinking

### Medium Level
- Checks for winning moves and takes them
- Blocks opponent's winning moves
- Otherwise plays randomly

### Hard Level
- Implements the **Minimax algorithm**
- Evaluates entire game tree recursively
- Plays optimally - never makes mistakes
- Assumes opponent also plays perfectly
- Unbeatable when playing first or second

## 🛠️ Technical Details

- **Language**: Java
- **Algorithm**: Minimax for hard AI
- **Input Validation**: Handles invalid coordinates and occupied cells
- **Game States**: Detects wins, draws, and ongoing games

## 🏗️ Project Structure

```
Tic-Tac-Toe with AI (Java)/
├── task/
│   ├── src/tictactoe/
│   │   └── Main.java          # Main game implementation
│   └── test/
│       ├── TicTacToeTest.java # Test suite
│       └── utils/             # Test utilities
├── build.gradle               # Gradle build configuration
└── README.md                  # This file
```

## 🚀 Running the Project

### Prerequisites
- Java JDK 8 or higher
- Gradle (or use included gradlew)

### Build and Run

```bash
# Using Gradle wrapper (recommended)
./gradlew run

# Or build first, then run
./gradlew build
java -jar build/libs/Tic-Tac-Toe.jar
```

### Run Tests

```bash
./gradlew test
```

## 📚 Learning Resources

This project was created as part of the Hyperskill Tic-Tac-Toe with AI track.

- [Project Link](https://hyperskill.org/projects/81)
- [My Hyperskill Profile](https://hyperskill.org/profile/625186755)

## 🎓 What I Learned

- Implementing game logic and state management
- Recursive algorithms (Minimax)
- Input validation and error handling
- AI decision-making strategies
- Game tree evaluation
- Java programming best practices

## 📝 License

This project is open source and available for educational purposes.

---

**Note**: The Hard AI using Minimax is unbeatable when playing optimally. Good luck trying to win! 🎯
