package io.github.angry_birds;

import com.badlogic.gdx.Game;

import java.util.HashMap;

public class Main extends Game {


    @Override
    public void create() {

        setScreen(new LoadingScreen(this));

    }



}
