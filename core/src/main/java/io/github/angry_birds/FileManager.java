package io.github.angry_birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.Bird.Bird;
import io.github.angry_birds.Bird.RedBird;
import io.github.angry_birds.Bird.Chuck;
import io.github.angry_birds.Bird.Bomb;
import io.github.angry_birds.Block.*;
import io.github.angry_birds.Pig.AlienPig;
import io.github.angry_birds.Pig.HektorPorko;
import io.github.angry_birds.Pig.KingPig;
import io.github.angry_birds.Pig.Pig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class FileManager {
    private static FileManager instance;

    private FileManager() {}

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public static void setcompletedlevel(int level) {

            String filepath = "data/LevelMatrix.json";
            FileHandle fileHandle = Gdx.files.local(filepath);

            try {
                String content = fileHandle.readString();
                JSONArray jsonArray = new JSONArray(content);

                JSONObject levelObject = jsonArray.getJSONObject(level - 1);
                levelObject.put(String.valueOf(level), true);

                fileHandle.writeString(jsonArray.toString(4), false);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public static void setuncompletedlevel(int level) {

        String filepath = "data/LevelMatrix.json";
        FileHandle fileHandle = Gdx.files.local(filepath);

        try {
            String content = fileHandle.readString();
            JSONArray jsonArray = new JSONArray(content);

            JSONObject levelObject = jsonArray.getJSONObject(level - 1);
            levelObject.put(String.valueOf(level), false);

            fileHandle.writeString(jsonArray.toString(4), false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }











    public Stack<Bird> loadBirds(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, Catapult catapult, int level,boolean reset) {
        Stack<Bird> aliveBirds = new Stack<>();
        String filepath;
        if (!reset){
        filepath = "data/level" + level + "birds.json";}
        else{
           filepath = "data/defaultlevel" + level + "birds.json";
        }
        FileHandle fileHandle = Gdx.files.internal(filepath);

        try {
            String content = fileHandle.readString();
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject birdObject = jsonArray.getJSONObject(i);
                String birdType = birdObject.getString("type");
                boolean alive = birdObject.getBoolean("alive");
                if (alive) {
                    switch (birdType) {
                        case "RedBird":
                            aliveBirds.push(new RedBird(world, shapeRenderer, batch, catapult));
                            break;
                        case "Chuck":
                            aliveBirds.push(new Chuck(world, shapeRenderer, batch, catapult));
                            break;
                        case "Bomb":
                            aliveBirds.push(new Bomb(world, shapeRenderer, batch, catapult));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aliveBirds;
    }

    public ArrayList<Pig> loadPigs(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, int level,boolean  reset) {
        ArrayList<Pig> pigs = new ArrayList<>();
        String filepath;
        if (!reset){
            filepath = "data/level" + level + "pigs.json";
        }
        else{
            filepath = "data/defaultlevel" + level + "pigs.json";
        }
        FileHandle fileHandle = Gdx.files.internal(filepath);

        try {
            String content = fileHandle.readString();
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject pigObject = jsonArray.getJSONObject(i);
                String pigType = pigObject.getString("type");
                boolean alive = pigObject.getBoolean("alive");
                float x = (float)pigObject.getDouble("x");
                float y = (float)pigObject.getDouble("y");
                float angle = (float)pigObject.getDouble("angle");
                int hp=(int)pigObject.getInt("HP");
           if (alive) {
                switch (pigType) {
                    case "KingPig":
                        pigs.add(new KingPig(world, shapeRenderer, batch, x, y, angle,hp));
                        break;
                    case "AlienPig":
                        pigs.add(new AlienPig(world, shapeRenderer, batch, x, y, angle,hp));
                        break;
                    case "HektorPorko":
                        pigs.add(new HektorPorko(world, shapeRenderer, batch, x, y, angle,hp));
                        break;
                }
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pigs;
    }

    public ArrayList<Block> loadBlocks(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, int level,boolean  reset) {
        ArrayList<Block> blocks = new ArrayList<>();
        String filepath;
        if (!reset){
            filepath = "data/level" + level + "blocks.json";
        }
        else{
            filepath = "data/defaultlevel" + level + "blocks.json";
        }
        FileHandle fileHandle = Gdx.files.internal(filepath);

        try {
            String content = fileHandle.readString();
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject blockObject = jsonArray.getJSONObject(i);
                String blockType = blockObject.getString("type");
                boolean alive = blockObject.getBoolean("alive");
                float x = (float)blockObject.getDouble("x");
                float y = (float)blockObject.getDouble("y");
                float angle = (float)blockObject.getDouble("angle");
                int health= (int)blockObject.getInt("HP");
                if (alive) {
                    switch (blockType) {
                        case "Wood":
                            blocks.add(new Wood(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "Stone":
                            blocks.add(new Stone(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "Ice":
                            blocks.add(new Ice(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "Woodplank":
                            blocks.add(new Woodplank(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "StonePlank":
                            blocks.add(new StonePlank(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "IcePlank":
                            blocks.add(new IcePlank(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "Icehollow":
                            blocks.add(new Icehollow(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        case "Stonehollow":
                            blocks.add(new Stonehollow(x, y, world, shapeRenderer, batch, angle,health));
                            break;
                        default:
                            System.err.println("Unknown block type: " + blockType);
                            break;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blocks;
    }




    public void saveBirds(Stack<Bird> birds, int level, boolean reset) {
        JSONArray jsonArray = new JSONArray();

        if (reset) {
            try {

                String defaultFilepath = "data/defaultlevel" + level + "birds.json";
                FileHandle defaultFileHandle = Gdx.files.local(defaultFilepath);

                if (defaultFileHandle.exists()) {

                    String currentFilepath = "data/level" + level + "birds.json";
                    FileHandle currentFileHandle = Gdx.files.local(currentFilepath);
                    defaultFileHandle.copyTo(currentFileHandle);
                    return;
                }
            } catch (Exception e) {

                System.err.println("Error resetting birds: " + e.getMessage());
            }
        }


        for (Bird bird : birds) {
            JSONObject birdObject = new JSONObject();
            birdObject.put("type", bird.getClass().getSimpleName());
            birdObject.put("alive", true);
            jsonArray.put(birdObject);
        }

        String filepath = "data/level" + level + "birds.json";
        FileHandle fileHandle = Gdx.files.local(filepath);

        fileHandle.writeString(jsonArray.toString(4), false);
        setuncompletedlevel(level);
    }

    public void savePigs(ArrayList<Pig> pigs, int level, boolean reset) {
        if (reset) {
            try {

                String defaultFilepath = "data/defaultlevel" + level + "pigs.json";
                FileHandle defaultFileHandle = Gdx.files.local(defaultFilepath);

                if (defaultFileHandle.exists()) {

                    String currentFilepath = "data/level" + level + "pigs.json";
                    FileHandle currentFileHandle = Gdx.files.local(currentFilepath);
                    defaultFileHandle.copyTo(currentFileHandle);
                    return;
                }
            } catch (Exception e) {

                System.err.println("Error resetting pigs: " + e.getMessage());
            }
        }


        JSONArray jsonArray = new JSONArray();
        for (Pig pig : pigs) {
           if(pig.alive) {
               JSONObject pigObject = new JSONObject();
               pigObject.put("type", pig.getClass().getSimpleName());
               pigObject.put("alive", pig.alive);
               pigObject.put("x", pig.getX());
               pigObject.put("y", pig.getY());
               pigObject.put("angle", pig.getAngle());
               pigObject.put("HP", pig.Hp);
               jsonArray.put(pigObject);
           }
        }

        String filepath = "data/level" + level + "pigs.json";
        FileHandle fileHandle = Gdx.files.local(filepath);
        fileHandle.writeString(jsonArray.toString(4), false);
    }

   public void saveBlocks(ArrayList<Block> blocks, int level, boolean reset) {
    if (reset) {
        try {
            String defaultFilepath = "data/defaultlevel" + level + "blocks.json";
            FileHandle defaultFileHandle = Gdx.files.local(defaultFilepath);

            if (defaultFileHandle.exists()) {
                String currentFilepath = "data/level" + level + "blocks.json";
                FileHandle currentFileHandle = Gdx.files.local(currentFilepath);
                defaultFileHandle.copyTo(currentFileHandle);
                return;
            }
        } catch (Exception e) {
            System.err.println("Error resetting blocks: " + e.getMessage());
        }
    }

    JSONArray jsonArray = new JSONArray();
    for (Block block : blocks) {
        if(block.alive) {
        JSONObject blockObject = new JSONObject();
        blockObject.put("type", block.getClass().getSimpleName());
        blockObject.put("alive", block.alive);
        blockObject.put("x", block.getX());
        blockObject.put("y", block.getY());
        blockObject.put("angle", block.getDynamicFallingBody().getAngle());
        blockObject.put("HP", block.health);
        jsonArray.put(blockObject);
    }
    }

    String filepath = "data/level" + level + "blocks.json";
    FileHandle fileHandle = Gdx.files.local(filepath);
    fileHandle.writeString(jsonArray.toString(4), false);
}



}
