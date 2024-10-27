
# Angry Birds Space-Themed Game

A resizable and interactive game inspired by **Angry Birds Space**. The game offers an engaging level selection and gameplay experience, with a default aspect ratio of **1600x900** and a range of levels and game screens.

## Overview
- **Game Genre**: Action/Puzzle
- **Theme**: Inspired by Angry Birds Space
- **Default Aspect Ratio**: 1600x900
- **Source of Assets**: 
  - [spriters-resources.com](https://www.spriters-resource.com)
  - [wiki-Fandom.com](https://fandom.com)
  - AI-generated assets from **DALL-E**

## Table of Contents
- [Features](#features)
- [Classes Overview](#classes-overview)
- [Setup](#setup)
- [Running the Game](#running-the-game)
- [Testing](#testing)
- [License](#license)

## Features
1. **Loading Screen**: Shows a loading bar and transitions to the main screen.
2. **Main Screen**: Navigation buttons for **Play** and **Quit**.
3. **Menu Screen**: Level selection with interactive asteroid background.
4. **Level Screen**: Gameplay screen with win and lose options.
5. **Bird and Pig Characters**: Interactive characters with animations and sound effects.

## Classes Overview

### Main Class
- **Main**: Extends the `Game` class, responsible for initializing the game with a loading screen.

### Screen Classes
Each of these classes implements the `Screen` interface and provides unique functionality:

1. **LoadingScreen**
   - **Purpose**: Shows a loading bar and loads resources.
   - **Key Methods**:
     - `show()`: Initializes the camera, viewport, and shape renderer.
     - `render()`: Draws the loading background and bar. Transitions to `FirstScreen` once loading is complete.
   - **Constructors**:
     - `LoadingScreen(Game game)`: Sets up the loading screen with a reference to the main game.

2. **FirstScreen**
   - **Purpose**: Acts as the main menu screen with primary buttons.
   - **Key Methods**:
     - `show()`: Initializes the camera, viewport, and renderer.
     - `render()`: Draws the background, Play, and Quit buttons. 
       - **Play** button opens `MenuScreen`.
       - **Quit** button exits the game.
   - **Constructors**:
     - `FirstScreen(Game game)`: Sets up the main menu.
     - `FirstScreen(Game game, Sound sound)`: Sets up the main menu with background music.

3. **MenuScreen**
   - **Purpose**: Level selection screen with asteroid animations.
   - **Key Methods**:
     - `show()`: Sets up the camera, viewport, and asteroid objects.
     - `render()`: Draws the background with buttons for level selection and floating asteroids.
       - Buttons allow selecting levels, returning to the main menu, or loading previous games.
   - **Constructors**:
     - `MenuScreen(Game game)`: Sets up the level selection menu.
     - `MenuScreen(Game game, Sound sound)`: Retains music continuity from `FirstScreen`.

4. **Levelbg**
   - **Purpose**: Core gameplay screen.
   - **Key Methods**:
     - `show()`: Initializes the camera, viewport, and window objects.
     - `render()`: Draws the level background, including controls for saving, restarting, and quitting the level.
       - **W Key**: Triggers the win screen.
       - **L Key**: Triggers the lose screen.
   - **Constructors**:
     - `LevelScreen(Game game)`: Basic constructor.
     - `LevelScreen(Game game, Sound sound)`: Disposes of menu music and starts level music.

### Other Classes
- **Bird**: Represents the player's character. Includes functions for movement, interactions, and collisions.
- **Pig**: Enemy character with similar methods to the bird but modified to trigger events when collided with.
- **Block Subclasses**: `Stone`, `Ice`, and `Wood` classes each with unique characteristics and interaction methods. Adds variety in the gameplay environment.

## Setup

### Prerequisites
- **Java Development Kit (JDK 8 or higher)**
- **LibGDX**
- **Gradle** (recommended for project setup)
- **Git** (optional, for cloning the repository)

### Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/angrybirds.git
   cd angrybirds
   ```

2. **Build the Project**:
   Use Gradle to install dependencies and build the project.
   ```bash
   ./gradlew build
   ```

3. **Run Configurations (Optional in IDE)**:
   Configure `Lwjgl3Launcher` as the main entry point in your IDE if you're running the project from there.

## Running the Game

### Running from Terminal
Once dependencies are installed and the project is built, you can run the game from the terminal.

1. **Navigate to the Build Directory**:
   ```bash
   cd build/libs
   ```

2. **Execute the JAR File**:
   Run the compiled JAR file (replace `your-jar-file-name.jar` with the actual file name, which may vary).
   ```bash
   java -jar angrybirds.jar
   ```

3. **Control Summary**:
   - **Main Menu**: Click "Play" to start or "Quit" to exit.
   - **Menu Screen**: Select levels or return to the main screen.
   - **Level Screen**: Press **W** for win screen and **L** for lose screen.

## Testing
### Manual Testing
1. **Screen Transitions**:
   - Confirm smooth transitions between screens.
   - Verify music continuity between screens.

2. **Gameplay**:
   - Test button functionality on each screen.
   - Check win/lose conditions and the menu options in the level screen.

3. **Resizable Window**:
   - Resize the window to check that UI elements adjust properly.

4. **Save and Load**:
   - Verify that saved progress can be loaded from the menu screen.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
