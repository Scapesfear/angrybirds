package io.github.angry_birds;

import com.badlogic.gdx.Game;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new levelbg(this)); // Set levelbg2 as the initial screen
    }
}
