# Angry Birds Space-Themed Game

## Overview
This game is a faithful recreation of the classic Angry Birds gameplay, but with a space-themed twist. The core objective remains the same - use a slingshot to launch birds at structures housing evil pigs, with the goal of destroying all the pigs in each level.

What sets this version apart is the imaginative setting and enhanced visuals. The game takes place against the backdrop of a vast, starry sky, with levels set on various celestial bodies like planets and asteroids. The bird and pig characters have been redesigned with a sci-fi flair, adding to the immersive space theme.

In addition to the classic Angry Birds gameplay, this version introduces several new and exciting features:

## Key Features
2. **Main Screen**: The main menu screen provides two primary options - "Play" to start the game, and "Quit" to exit.

3. **Menu Screen**: The level selection menu displays all available stages in an interactive asteroid field. Players can browse through the levels and select the one they wish to play.

4. **Level Screen**: This is where the core gameplay takes place. The level screen presents the playing field, with structures housing the evil pigs. Players use the slingshot to launch birds and destroy the pigs' defenses.

5. **Bird and Pig Characters**: The classic Angry Birds characters have been reimagined with a space-themed design. The birds and pigs now have unique animations and sound effects that enhance the overall experience.

6. **Resizable Window**: The game window can be resized, and all user interface (UI) elements will automatically adjust to maintain the optimal layout and proportions.

7. **Save and Load**: Players can save their progress in the game and load their saved game from the menu screen, allowing them to pick up where they left off.

## Classes Overview
The game is structured using the following key classes:

1. **Main**: This is the entry point of the application, responsible for initializing the game and loading the initial screen.

2. **LoadingScreen**: This class handles the loading of game assets and displays a progress bar during the loading process. Once the assets are fully loaded, it transitions the game to the main screen.

3. **FirstScreen**: This class represents the main menu screen, providing the "Play" and "Quit" options for the user.

4. **MenuScreen**: The level selection screen, allowing players to choose the level they want to play. It also features the interactive asteroid field.

5. **LevelScreen**: This is the core gameplay screen, where players use the slingshot to launch birds and destroy the pigs' structures.

6. **Bird**: The player-controlled character, with methods for movement, interaction, and collision detection.

7. **Pig**: The enemy characters, with similar methods to the Bird class but modified to trigger specific events when collided with.

8. **Block Subclasses**: These classes (Stone, Ice, and Wood) represent the various types of blocks that make up the structures in the game levels, each with unique characteristics and interaction methods.

## Setup and Running
To set up and run the Angry Birds Space-Themed Game, follow these steps:

1. **Prerequisites**:
   - Ensure you have Java Development Kit (JDK) version 8 or higher installed on your system.
   - Install the LibGDX framework, which is used for building the game.
   - Set up Gradle, as it is the recommended build tool for this project.
   - If you're using a version control system, you can clone the repository from the provided URL.

2. **Build the Project**:
   - Open a terminal or command prompt and navigate to the project directory.
   - Run the following Gradle command to install dependencies and build the project:
     ```
     ./gradlew build
     ``` 

3. **Run the Game**:
   - You can run directly using 
     ```
     ./gradlew run 
     ```
5. **Controls**:
   - **Main Menu**: Click the "Play" button to start the game or the "Quit" button to exit.
   - **Menu Screen**: Select the desired level or click the back button to return to the main screen.

## Testing
To ensure the game is functioning correctly, perform the following manual tests:

1. **Screen Transitions**:
   - Verify that the transitions between screens (loading, main menu, level selection, and gameplay) are smooth and seamless.
   - Check that the music and sound effects transition properly between screens.

2. **Gameplay**:
   - Thoroughly test the functionality of all buttons and interactive elements on each screen.
   - Verify that the win and lose conditions are properly implemented in the level screen.
   - Ensure that the menu options (such as saving and loading progress) work as expected.

3. **Resizable Window**:
   - Resize the game window to different dimensions and observe that all UI elements adjust accordingly, maintaining the optimal layout and proportions.

4. **Save and Load**:
   - Test the save and load functionality by playing through a level, saving the progress, and then verifying that the saved game can be properly loaded from the menu screen.

By performing these tests, you can ensure that the Angry Birds Space-Themed Game is functioning as intended and provide a polished and enjoyable experience for players.
