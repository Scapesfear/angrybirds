
package com.mygdx;

import com.badlogic.gdx.Game;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new MyGame(this)); // Set levelbg2 as the initial screen
    }
}
