package io.github.angry_birds;

import com.badlogic.gdx.Game;

import java.util.HashMap;

public class Main extends Game {
//    public static HashMap<Integer, Integer>  LevelMatrix = new HashMap<Integer, Integer>();
//
//
//    public void main() {
//        LevelMatrix.put(1, 1);
//        LevelMatrix.put(2, 1);
//        LevelMatrix.put(3, 1);
//
//    }

    @Override
    public void create() {

        setScreen(new FirstScreen(this));

    }



}
