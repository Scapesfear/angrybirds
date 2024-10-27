Code Explanation:

Speciality: fully resizable  
has 1600x900 default aspect ratio
the game is based on Angry birds Space theme.

All photos have been taken from:
a)spriters-resources.com
b)wiki-Fandom.com
c)Dalle AI

Main:--->extends game
        has method create: which sets the Loading Screen.

LoadingScreen:--->implements screen
                    has standard methods the default screen has (only important ones are explained)
                    used as a loading screen.
                    show: sets the camera, viewport, and shaperender objects
                    render: draws the background, the loading bar and when wait time is reached sets the screen to new firstscreen.
                    LoadingScreen(game): the default constructor

FirstScreen:--->implements screen
                has standard methods the default screen has (only important ones are explained)
                used as a main screen.
                show: sets the camera, viewport, and shaperender objects
                render: draws the background, the two primary buttons play and Quit button. Play button switches the screen to the MenuScreen while Quit button ends the application process.
                buttons have hardcoded event listeners.
                FirstScreen(game): the default constructor
                FirstScreen(game,sound): the constructor with sound parameter which ensures the continuity of the music when switched back to menuscreen.

MenuScreen:--->implements screen
                has standard methods the default screen has (only important ones are explained)
                used as a level selecting screen.
                show: sets the camera, viewport, shaperender  and asteroid objects
                render: draws the background, have 4 primary buttons. 3 are to choose from three different levels and sets the screen to levelbg and 1 backbutton which sets the screen back to FirstScreen.
                the render also features the 3 asteroids floating in the background. if you have played game before and left in between it autosaves and when you go to that level it asks whether you want to load the previos game or new game.
                the buttons have hardcoded eventlistners while the load window has the stage,window and scene2d buttons.
                MenuScreen(game): the default constructor
                MenuScreen(game,sound): the constructor with sound parameter which ensures the continuity of the music when switched back to firstscreen.

Levelbg:--->implements screen
            has standard methods the default screen has (only important ones are explained)
            game played here.
            show: sets the camera, viewport, shaperenderer  and window objects
            render: draws the background, have 1 primary button the menu button which is used to end the game, save and go back to menu screen,or restart ,or start the game.
            w key is linked to win window to show win screen have similar three buttons as GAME MENU.
            L key is linked to lose window to show lose screen have similar three buttons as GAME MENU.
            LevelScreen(game): the default constructor
            LevelScreen(game,sound): the constructor with sound parameter which ensures to dipose the menu sound and starts its own music.
           
Other classes used in the code:

Bird:--->
            has standard methods the default screen has (only important ones are explained)
            game played here.
            show: sets the camera, viewport, shaperenderer  and window objects
            render: draws the background, have 1 primary button the menu button which is used to end the game, save and go back to menu screen,or restart ,or start the game.
            w key is linked to win window to show win screen have similar three buttons as GAME MENU.
            L key is linked to lose window to show lose screen have similar three buttons as GAME MENU.
            LevelScreen(game): the default constructor
            LevelScreen(game,sound): the constructor with sound parameter which ensures to dipose the menu sound and starts its own music.
             

               
