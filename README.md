# How to Run *The Midnight Soccer Match* (Java)

## Overview
*The Midnight Soccer Match* is a text-based Java adventure game where the player explores locations, collects items, and competes in a penalty shootout. This guide explains how to compile, run, and play the game.

---

## Prerequisites

Make sure you have the following installed:

- **Java JDK 8 or later**
  - Verify installation by running:
    java -version
- A **terminal / command prompt**
  - Windows: Command Prompt or PowerShell  
  - macOS / Linux: Terminal

---

## Project Files

Ensure **all files are in the same directory**:

- App.java  
- CommandSystem.java  
- Gamemanager.java  
- GameState.java  
- Location.java  
- Player.java  
- Item.java  
- Entities.java  

---

## Compile the Program

1. Open a terminal and navigate to the project directory:
   cd path/to/your/project/folder

2. Compile all Java source files:
   javac *.java

3. If there are no errors, `.class` files will be generated automatically.

---

## Run the Game

After compiling successfully, start the game with:
java App

You should see the welcome message and game instructions appear in the console.

---

## How to Play

The game uses text-based commands. Type commands when prompted.

### Available Commands

- `?`  
  Displays the list of available commands.

- `look` or `l`  
  Look around your current location.

- `go <location>`  
  Move to another area.  
  Examples:  
  - go field  
  - go locker  
  - go bleachers  
  - go goal  

- `talk <character>` or `t <character>`  
  Speak with characters.  
  Examples:  
  - talk opponent  
  - t referee  
  - talk keeper  

- `retrieve <item>`  
  Pick up an item in the current location.  
  Example:  
  - retrieve amulet  

- `inventory` or `inv`  
  View your inventory.

- `use <item> player`  
  Use an item on yourself to gain skill points.  
  Examples:  
  - use cleats player  
  - use amulet player  

- `quit` or `q`  
  Exit the game.

---

## Game Objective

- Explore different locations  
- Retrieve useful items (ball, cleats, amulet)  
- Use items to increase your skill level  
- Enter the goal to start the penalty shootout  
- Score enough goals to win the match and unlock the best ending  

---

## Penalty Shootout

- You will be given **3 shots**
- Choose where to shoot:
  - TL (Top Left)
  - TR (Top Right)
  - M (Middle)
  - BL (Bottom Left)
  - BR (Bottom Right)
- Each shot result is shown immediately
- Your success depends on your skill level from collected items

---

## Troubleshooting

- **Unknown command**
  - Type `?` to see valid commands
  - Commands are case-insensitive

- **Compilation errors**
  - Make sure all `.java` files are in the same folder
  - Check for syntax errors or missing semicolons

- **Java not recognized**
  - Install the Java JDK and ensure it is added to your system PATH

---

## Enjoy the Game ⚽

Have fun in the penalty shootout, may your shots always hit the back of the net!

---

## Author

Marvin Ampofo  
Computer Science Major – Towson University

