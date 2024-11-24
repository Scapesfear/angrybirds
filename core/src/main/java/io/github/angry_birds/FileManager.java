package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.Bird.Bird;
import io.github.angry_birds.Bird.RedBird;
import io.github.angry_birds.Bird.Chuck;
import io.github.angry_birds.Bird.Bomb;
import io.github.angry_birds.Pig.AlienPig;
import io.github.angry_birds.Pig.HektorPorko;
import io.github.angry_birds.Pig.KingPig;
import io.github.angry_birds.Pig.Pig;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public Stack<Bird> loadBirds(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, Catapult catapult, int level) {
        Stack<Bird> aliveBirds = new Stack<>();
        String filepath = "data/level" + level + "birds.json";
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

    public ArrayList<Pig> loadPigs(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, int level) {
        ArrayList<Pig> pigs = new ArrayList<>();
        String filepath = "data/level" + level + "pigs.json";
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
           if (alive) {
                switch (pigType) {
                    case "Pig":
                        pigs.add(new Pig("ui/pig.png", world, shapeRenderer, batch, x, y, angle));
                        break;
                    case "KingPig":
                        pigs.add(new KingPig(world, shapeRenderer, batch, x, y, angle));
                        break;
                    case "AlienPig":
                        pigs.add(new AlienPig(world, shapeRenderer, batch, x, y, angle));
                        break;
                    case "HektorPorko":
                        pigs.add(new HektorPorko(world, shapeRenderer, batch, x, y, angle));
                        break;
                }
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pigs;
    }

    public void saveBirds(Stack<Bird> birds, int level) {
        JSONArray jsonArray = new JSONArray();
        for (Bird bird : birds) {
            JSONObject birdObject = new JSONObject();
            birdObject.put("type", bird.getClass().getSimpleName());
            birdObject.put("alive", bird.isAlive());
            jsonArray.put(birdObject);
        }

        String filepath = "data/level" + level + "birds.json";
        FileHandle fileHandle = Gdx.files.local(filepath);
        fileHandle.writeString(jsonArray.toString(), false);
    }

    public void savePigs(ArrayList<Pig> pigs, int level) {
        JSONArray jsonArray = new JSONArray();
        for (Pig pig : pigs) {
            JSONObject pigObject = new JSONObject();
            pigObject.put("type", pig.getClass().getSimpleName());
            pigObject.put("alive", true);
            pigObject.put("x", pig.getX());
            pigObject.put("y", pig.getY());
            pigObject.put("angle", pig.getAngle());
            jsonArray.put(pigObject);
        }

        String filepath = "data/level" + level + "pigs.json";
        FileHandle fileHandle = Gdx.files.local(filepath);
        fileHandle.writeString(jsonArray.toString(), false);
    }


}
